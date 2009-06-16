package gov.nih.nci.cananolab.ui.sample;

import gov.nih.nci.cananolab.service.sample.PointOfContactService;
import gov.nih.nci.cananolab.service.sample.impl.PointOfContactServiceLocalImpl;
import gov.nih.nci.cananolab.ui.core.InitSetup;
import gov.nih.nci.cananolab.ui.security.InitSecuritySetup;
import gov.nih.nci.cananolab.util.Constants;

import java.util.List;
import java.util.SortedSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.directwebremoting.impl.DefaultWebContextBuilder;

public class DWRSampleManager {

	Logger logger = Logger.getLogger(DWRSampleManager.class);

	public DWRSampleManager() {
	}

	/* remove organization associated with the POC from the visiblity group */
	public String[] removeOrgVisibility(String pocId) {

		PointOfContactService pocService = new PointOfContactServiceLocalImpl();

		DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
		org.directwebremoting.WebContext webContext = dwcb.get();
		HttpServletRequest request = webContext.getHttpServletRequest();
		try {
			List<String> visibilityGroup = InitSecuritySetup.getInstance()
					.getAllVisibilityGroups(request);
			if (!pocId.equalsIgnoreCase("other")) {
				String sampleOrg = pocService.findPointOfContactById(pocId)
						.getOrganization().getName();
				visibilityGroup.remove(sampleOrg);
			}
			String[] eleArray = new String[visibilityGroup.size()];
			return visibilityGroup.toArray(eleArray);

		} catch (Exception e) {
			System.out.println("removeOrgVisibility exception.");
			e.printStackTrace();
		}

		return new String[] { "" };
	}

	/* remove organization name from the visiblity group */
	public String[] removeOrgNameVisibility(String orgName) {
		DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
		org.directwebremoting.WebContext webContext = dwcb.get();
		HttpServletRequest request = webContext.getHttpServletRequest();
		try {
			List<String> visibilityGroup = InitSecuritySetup.getInstance()
					.getAllVisibilityGroups(request);
			if (!orgName.equalsIgnoreCase("other")) {
				visibilityGroup.remove(orgName);
			}
			String[] eleArray = new String[visibilityGroup.size()];
			return visibilityGroup.toArray(eleArray);

		} catch (Exception e) {
			System.out.println("removeOrgNameVisibility exception.");
			e.printStackTrace();
		}
		return new String[] { "" };
	}

	public String[] getNanomaterialEntityTypes(String searchLocations) {
		DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
		org.directwebremoting.WebContext webContext = dwcb.get();
		HttpServletRequest request = webContext.getHttpServletRequest();
		ServletContext appContext = request.getSession().getServletContext();
		try {
			boolean isLocal = false;
			if (Constants.LOCAL_SITE.equals(searchLocations)) {
				isLocal = true;
			}
			SortedSet<String> types = null;
			if (isLocal) {
				types = InitSetup
						.getInstance()
						.getReflectionDefaultAndOtherLookupTypes(
								request,
								"defaultNanomaterialEntityTypes",
								"nanomaterialEntityTypes",
								"gov.nih.nci.cananolab.domain.particle.NanomaterialEntity",
								"gov.nih.nci.cananolab.domain.nanomaterial.OtherNanomaterialEntity",
								true);
			} else {
				types = InitSetup
						.getInstance()
						.getServletContextDefaultTypesByReflection(appContext,
								"defaultNanomaterialEntityTypes",
								"gov.nih.nci.cananolab.domain.particle.NanomaterialEntity");
			}

			String[] eleArray = new String[types.size()];
			return types.toArray(eleArray);
		} catch (Exception e) {
			logger.error("Problem setting nanomaterial entity types: \n", e);
			e.printStackTrace();
		}
		return new String[] { "" };
	}

	public String[] getFunctionalizingEntityTypes(String searchLocations) {
		DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
		org.directwebremoting.WebContext webContext = dwcb.get();
		HttpServletRequest request = webContext.getHttpServletRequest();
		ServletContext appContext = request.getSession().getServletContext();
		try {
			boolean isLocal = false;
			if (Constants.LOCAL_SITE.equals(searchLocations)) {
				isLocal = true;
			}
			SortedSet<String> types = null;
			if (isLocal) {
				types = InitSetup
						.getInstance()
						.getReflectionDefaultAndOtherLookupTypes(
								request,
								"defaultFunctionalizingEntityTypes",
								"functionalizingEntityTypes",
								"gov.nih.nci.cananolab.domain.particle.FunctionalizingEntity",
								"gov.nih.nci.cananolab.domain.agentmaterial.OtherFunctionalizingEntity",
								true);
			} else {
				InitSetup
						.getInstance()
						.getServletContextDefaultTypesByReflection(appContext,
								"defaultFunctionalizingEntityTypes",
								"gov.nih.nci.cananolab.domain.particle.FunctionalizingEntity");
			}

			String[] eleArray = new String[types.size()];
			return types.toArray(eleArray);
		} catch (Exception e) {
			logger.error("Problem setting functionalizing entity types: \n", e);
			e.printStackTrace();
		}
		return new String[] { "" };
	}

	public String[] getFunctionTypes(String searchLocations) {
		DefaultWebContextBuilder dwcb = new DefaultWebContextBuilder();
		org.directwebremoting.WebContext webContext = dwcb.get();
		HttpServletRequest request = webContext.getHttpServletRequest();
		ServletContext appContext = request.getSession().getServletContext();

		try {
			boolean isLocal = false;
			if (Constants.LOCAL_SITE.equals(searchLocations)) {
				isLocal = true;
			}
			SortedSet<String> types = null;
			if (isLocal) {
				types = InitSetup
						.getInstance()
						.getReflectionDefaultAndOtherLookupTypes(
								request,
								"defaultFunctionTypes",
								"functionTypes",
								"gov.nih.nci.cananolab.domain.particle.Function",
								"gov.nih.nci.cananolab.domain.function.OtherFunction",
								true);
			} else {
				InitSetup
						.getInstance()
						.getServletContextDefaultTypesByReflection(appContext,
								"defaultFunctionTypes",
								"gov.nih.nci.cananolab.domain.particle.Function");
			}
			String[] eleArray = new String[types.size()];
			return types.toArray(eleArray);
		} catch (Exception e) {
			logger.error("Problem setting function types: \n", e);
			e.printStackTrace();
		}
		return new String[] { "" };
	}
}
