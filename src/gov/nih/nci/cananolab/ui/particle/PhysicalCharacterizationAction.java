package gov.nih.nci.cananolab.ui.particle;

/**
 * This class allows user to submit physical characterization data. 
 *  
 * @author pansu
 */

/* CVS $Id: PhysicalCharacterizationAction.java,v 1.17 2008-05-07 10:31:16 pansu Exp $ */

import gov.nih.nci.cananolab.domain.particle.NanoparticleSample;
import gov.nih.nci.cananolab.domain.particle.characterization.Characterization;
import gov.nih.nci.cananolab.domain.particle.characterization.physical.PhysicalCharacterization;
import gov.nih.nci.cananolab.dto.common.UserBean;
import gov.nih.nci.cananolab.dto.particle.ParticleBean;
import gov.nih.nci.cananolab.dto.particle.characterization.CharacterizationBean;
import gov.nih.nci.cananolab.dto.particle.characterization.PhysicalCharacterizationBean;
import gov.nih.nci.cananolab.service.particle.NanoparticleCharacterizationService;
import gov.nih.nci.cananolab.ui.core.InitSetup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;

public class PhysicalCharacterizationAction extends BaseCharacterizationAction {

	/**
	 * Add or update the data to database
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		PhysicalCharacterizationBean charBean = (PhysicalCharacterizationBean) theForm
				.get("achar");
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		charBean.setupDomainChar(InitSetup.getInstance()
				.getDisplayNameToClassNameLookup(
						request.getSession().getServletContext()), user
				.getLoginName());
		ParticleBean particleBean = setupParticle(theForm, request);
		NanoparticleCharacterizationService charService = new NanoparticleCharacterizationService();
		charService.saveCharacterization(
				particleBean.getDomainParticleSample(), charBean
						.getDomainChar());

		// save to other particles
		NanoparticleSample[] otherSamples = prepareCopy(request, theForm);
		if (otherSamples != null) {
			Boolean copyData = (Boolean) theForm.get("copyData");
			Characterization copy = charBean.getDomainCopy(copyData);
			for (NanoparticleSample sample : otherSamples) {
				charService.saveCharacterization(sample, copy);
			}
		}
		InitCharacterizationSetup.getInstance()
				.persistCharacterizationDropdowns(request, charBean);
		InitCharacterizationSetup.getInstance()
				.persistPhysicalCharacterizationDropdowns(request, charBean);

		ActionMessages msgs = new ActionMessages();
		ActionMessage msg = new ActionMessage(
				"message.addPhysicalCharacterization");
		msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
		saveMessages(request, msgs);
		ActionForward forward = mapping.findForward("success");
		setupDataTree(theForm, request);
		return forward;
	}

	protected CharacterizationBean getCharacterizationBean(
			DynaValidatorForm theForm, Characterization chara, UserBean user)
			throws Exception {
		PhysicalCharacterizationBean charBean = new PhysicalCharacterizationBean(
				(PhysicalCharacterization) chara);
		// set file visibility
		NanoparticleCharacterizationService charService = new NanoparticleCharacterizationService();
		charService.retrieveVisiblity(charBean, user);
		theForm.set("achar", charBean);
		return charBean;
	}

	protected void setLookups(HttpServletRequest request, CharacterizationBean charBean)
			throws Exception {
		super.setLookups(request, charBean);
		InitCharacterizationSetup.getInstance()
				.setPhysicalCharacterizationDropdowns(request);
	}

	protected void clearForm(DynaValidatorForm theForm) {
		theForm.set("achar", new PhysicalCharacterizationBean());
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		PhysicalCharacterizationBean charBean = (PhysicalCharacterizationBean) theForm
				.get("achar");
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		charBean.setupDomainChar(InitSetup.getInstance()
				.getDisplayNameToClassNameLookup(
						request.getSession().getServletContext()), user
				.getLoginName());
		NanoparticleCharacterizationService charService = new NanoparticleCharacterizationService();
		charService.deleteCharacterization(charBean.getDomainChar());
		ActionMessages msgs = new ActionMessages();
		ActionMessage msg = new ActionMessage(
				"message.deletePhysicalCharacterization");
		msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
		saveMessages(request, msgs);
		ActionForward forward = mapping.findForward("success");
		request.setAttribute("updateDataTree", "true");
		String particleId = theForm.getString("particleId");
		InitNanoparticleSetup.getInstance().getDataTree(particleId, request);
		return forward;
	}
}
