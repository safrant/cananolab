package gov.nih.nci.cananolab.ui.sample;

import gov.nih.nci.cananolab.domain.common.PointOfContact;
import gov.nih.nci.cananolab.dto.common.UserBean;
import gov.nih.nci.cananolab.service.sample.SampleService;
import gov.nih.nci.cananolab.service.sample.impl.SampleServiceLocalImpl;
import gov.nih.nci.cananolab.ui.core.InitSetup;
import gov.nih.nci.cananolab.ui.security.InitSecuritySetup;

import java.util.Collection;
import java.util.SortedSet;

import javax.servlet.http.HttpServletRequest;

/**
 * This class sets up information required for organization forms.
 *
 * @author tanq
 *
 */
public class InitPOCSetup {
	private SampleService service = new SampleServiceLocalImpl();

	private InitPOCSetup() {
	}

	public static InitPOCSetup getInstance() {
		return new InitPOCSetup();
	}

	public void setPOCDropdowns(HttpServletRequest request) throws Exception {
		InitSetup.getInstance()
				.getDefaultAndOtherLookupTypes(request, "contactRoles",
						"PointOfContact", "role", "otherRole", true);
		InitSecuritySetup.getInstance().getAllVisibilityGroups(request);
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		getAllOrganizationNames(request, user);
	}

	public void persistPOCDropdowns(HttpServletRequest request,
			PointOfContact primaryPointOfContact,
			Collection<PointOfContact> otherPointOfContactCollection ) throws Exception {
		UserBean userBean = (UserBean) request.getSession().getAttribute("user");
		String user = userBean.getLoginName();
		if (primaryPointOfContact!=null) {
			//TODO add to service
//			service.saveOrganization(primaryPointOfContact.getOrganization(), user);
		}
		InitSetup.getInstance().persistLookup(request, "PointOfContact", "role",
				"otherRole",
				(primaryPointOfContact.getRole()));
		if (otherPointOfContactCollection!=null) {
			for (PointOfContact otherPoc: otherPointOfContactCollection) {
				if (otherPoc!=null) {
					//TODO add to service
//					pocService.saveOrganization(otherPoc.getOrganization(), user);
				}
				InitSetup.getInstance().persistLookup(request, "PointOfContact", "role",
						"otherRole",
						(otherPoc.getRole()));
			}
		}
		setPOCDropdowns(request);
	}

	public SortedSet<String> getAllOrganizationNames(
			HttpServletRequest request, UserBean user) throws Exception {
		SortedSet<String> organizationNames = service
				.getAllOrganizationNames(user);
		request.getSession().setAttribute("allOrganizationNames", organizationNames);
		return organizationNames;
	}
}
