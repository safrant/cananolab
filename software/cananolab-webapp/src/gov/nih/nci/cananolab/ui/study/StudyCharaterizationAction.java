package gov.nih.nci.cananolab.ui.study;

/**
 * This class sets up the study sample submission page and submits a new sample.
 *
 * @author lethai
 */
import gov.nih.nci.cananolab.dto.particle.characterization.CharacterizationBean;
import gov.nih.nci.cananolab.dto.particle.characterization.CharacterizationSummaryViewBean;
import gov.nih.nci.cananolab.service.sample.CharacterizationService;
import gov.nih.nci.cananolab.service.sample.impl.CharacterizationServiceLocalImpl;
import gov.nih.nci.cananolab.service.security.SecurityService;
import gov.nih.nci.cananolab.ui.core.BaseAnnotationAction;
import gov.nih.nci.cananolab.util.StringUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class StudyCharaterizationAction extends BaseAnnotationAction {
	// logger
	// private static Logger logger = Logger.getLogger(StudyAction.class);

	public ActionForward input(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("inputForm");
	}

	public ActionForward summaryEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("summaryEdit");
	}

	public ActionForward summaryView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		
		request.getSession().removeAttribute("studyCharacterizationSummaryView");
		String studyId = request.getSession().getAttribute("studyId").toString();
		if (!StringUtils.isEmpty(studyId)) {
			theForm.set("studyId", studyId);
		} else {
			studyId = (String) request.getAttribute("studyId");			
		}
		
		
		CharacterizationService service = this.setServiceInSession(request);
		
		List<CharacterizationBean> charBeans = service
				.findCharacterizationsByStudyId(studyId);
		CharacterizationSummaryViewBean summaryView = new CharacterizationSummaryViewBean(
				charBeans);
		// Save result bean in session for later use - export/print.
		request.getSession().setAttribute("studyCharacterizationSummaryView",
				summaryView);
	
		request.setAttribute("studyCharacterizations",charBeans);
		return mapping.findForward("summaryView");
	}

	public ActionForward setupNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.getInputForward();
	}

	public ActionForward setupUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.getInputForward();
	}
	
	private CharacterizationService setServiceInSession(
			HttpServletRequest request) throws Exception {
		SecurityService securityService = super
				.getSecurityServiceFromSession(request);
		CharacterizationService service = (CharacterizationService) request
				.getSession().getAttribute("characterizationService");
		if (service == null) {
			service = new CharacterizationServiceLocalImpl(securityService);
		}
		request.getSession().setAttribute("characterizationService", service);
		return service;

	}
}