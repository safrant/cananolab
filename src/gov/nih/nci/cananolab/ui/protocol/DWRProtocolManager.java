package gov.nih.nci.cananolab.ui.protocol;

import gov.nih.nci.cananolab.dto.common.ProtocolBean;
import gov.nih.nci.cananolab.dto.common.UserBean;
import gov.nih.nci.cananolab.service.protocol.ProtocolService;
import gov.nih.nci.cananolab.service.protocol.helper.ProtocolServiceHelper;
import gov.nih.nci.cananolab.service.protocol.impl.ProtocolServiceLocalImpl;
import gov.nih.nci.cananolab.service.protocol.impl.ProtocolServiceRemoteImpl;
import gov.nih.nci.cananolab.ui.core.InitSetup;
import gov.nih.nci.cananolab.util.Constants;
import gov.nih.nci.cananolab.util.StringUtils;

import java.util.SortedSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.impl.DefaultWebContextBuilder;

/**
 * This class loads protocol data for ajax
 *
 * @author tanq, pansu
 *
 */
public class DWRProtocolManager {

	Logger logger = Logger.getLogger(DWRProtocolManager.class);
	ProtocolService service = new ProtocolServiceLocalImpl();
	ProtocolServiceHelper helper = new ProtocolServiceHelper();

	public DWRProtocolManager() {
	}

	public String[] getProtocolTypes(String searchLocations) {
		DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
		org.directwebremoting.WebContext webContext = dwcb.get();
		HttpServletRequest request = webContext.getHttpServletRequest();
		try {
			boolean isLocal = false;
			if (Constants.LOCAL_SITE.equals(searchLocations)) {
				isLocal = true;
			}
			SortedSet<String> types = null;
			if (isLocal) {
				types = InitSetup.getInstance()
						.getDefaultAndOtherTypesByLookup(request,
								"protocolTypes", "protocol", "type",
								"otherType", true);
			} else {
				types = InitSetup.getInstance().getDefaultTypesByLookup(
						webContext.getServletContext(), "defaultProtocolTypes",
						"protocol", "type");
			}
			types.add("");
			String[] eleArray = new String[types.size()];
			return types.toArray(eleArray);
		} catch (Exception e) {
			logger.error("Problem setting protocol types: \n", e);
			e.printStackTrace();
		}
		return new String[] { "" };
	}

	public SortedSet<String> getProtocolNames(String protocolType) {
		try {
			if (StringUtils.isEmpty(protocolType)) {
				return null;
			}
			DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
			org.directwebremoting.WebContext webContext = dwcb.get();
			UserBean user = (UserBean) webContext.getHttpServletRequest()
					.getSession().getAttribute("user");
			SortedSet<String> protocolNames = helper.getProtocolNamesBy(
					protocolType, user);
			return protocolNames;
		} catch (Exception e) {
			return null;
		}
	}

	public SortedSet<String> getProtocolVersions(String protocolType,
			String protocolName) {
		try {
			if (StringUtils.isEmpty(protocolName)) {
				return null;
			}
			DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
			org.directwebremoting.WebContext webContext = dwcb.get();
			UserBean user = (UserBean) webContext.getHttpServletRequest()
					.getSession().getAttribute("user");
			SortedSet<String> protocolVersions = helper.getProtocolVersionsBy(
					protocolType, protocolName, user);
			return protocolVersions;
		} catch (Exception e) {
			return null;
		}
	}

	public ProtocolBean getProtocol(String protocolType, String protocolName,
			String protocolVersion) {
		// all three have to be present
		if (StringUtils.isEmpty(protocolType)
				|| StringUtils.isEmpty(protocolName)
				|| StringUtils.isEmpty(protocolVersion)) {
			return null;
		}
		try {
			DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
			org.directwebremoting.WebContext webContext = dwcb.get();
			UserBean user = (UserBean) webContext.getHttpServletRequest()
					.getSession().getAttribute("user");
			ProtocolBean protocolBean = service.findProtocolBy(protocolType,
					protocolName, protocolVersion, user);
			return protocolBean;
		} catch (Exception e) {
			return null;
		}
	}

	public String getPublicCounts(String[] locations) {
		WebContext wctx = WebContextFactory.get();
		HttpServletRequest request = wctx.getHttpServletRequest();
		if (locations.length == 0) {
			locations = new String[1];
			locations[0] = Constants.APP_OWNER;
			// return null;
		}
		Integer counts = 0;
		ProtocolService service = null;

		for (String location : locations) {
			if (location.equals(Constants.LOCAL_SITE)) {
				try {
					service = new ProtocolServiceLocalImpl();
					counts += service.getNumberOfPublicProtocols();
				} catch (Exception e) {
					logger
							.error("Error obtaining counts of public protocols from local site.");
				}
			} else {
				try {
					String serviceUrl = InitSetup.getInstance()
							.getGridServiceUrl(request, location);

					service = new ProtocolServiceRemoteImpl(serviceUrl);
					counts += service.getNumberOfPublicProtocols();
				} catch (Exception e) {
					logger
							.error("Error obtaining counts of public protocols from "
									+ location);
				}
			}
		}
		return counts.toString() + " Protocols";
	}
}
