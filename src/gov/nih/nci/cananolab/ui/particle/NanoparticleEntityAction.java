package gov.nih.nci.cananolab.ui.particle;

/**
 * This class allows users to submit nanoparticle entity data under sample composition.
 *  
 * @author pansu
 */

/* CVS $Id: NanoparticleEntityAction.java,v 1.46 2008-05-13 20:23:48 cais Exp $ */

import gov.nih.nci.cananolab.domain.common.LabFile;
import gov.nih.nci.cananolab.domain.particle.NanoparticleSample;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.NanoparticleEntity;
import gov.nih.nci.cananolab.dto.common.UserBean;
import gov.nih.nci.cananolab.dto.particle.ParticleBean;
import gov.nih.nci.cananolab.dto.particle.composition.ComposingElementBean;
import gov.nih.nci.cananolab.dto.particle.composition.FunctionBean;
import gov.nih.nci.cananolab.dto.particle.composition.NanoparticleEntityBean;
import gov.nih.nci.cananolab.service.common.FileService;
import gov.nih.nci.cananolab.service.particle.NanoparticleCompositionService;
import gov.nih.nci.cananolab.ui.core.BaseAnnotationAction;
import gov.nih.nci.cananolab.ui.core.InitSetup;
import gov.nih.nci.cananolab.util.CaNanoLabConstants;
import gov.nih.nci.cananolab.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;

public class NanoparticleEntityAction extends BaseAnnotationAction {

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
		NanoparticleCompositionService compositionService = new NanoparticleCompositionService();
		ParticleBean particleBean = setupParticle(theForm, request);
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		NanoparticleEntityBean entityBean = (NanoparticleEntityBean) theForm
				.get("entity");
		// setup domainFile uri for fileBeans
		String internalUriPath = CaNanoLabConstants.FOLDER_PARTICLE
				+ "/"
				+ particleBean.getDomainParticleSample().getName()
				+ "/"
				+ StringUtils
						.getOneWordLowerCaseFirstLetter("Nanoparticle Entity");
		entityBean.setupDomainEntity(InitSetup.getInstance()
				.getDisplayNameToClassNameLookup(
						request.getSession().getServletContext()), user
				.getLoginName(), internalUriPath);
		
		if(!validateInherentFunctionType(request, entityBean)) {
			return mapping.getInputForward();
		}
		
		compositionService.saveNanoparticleEntity(particleBean
				.getDomainParticleSample(), entityBean.getDomainEntity());
		// save file data to file system and set visibility
		saveFilesToFileSystem(entityBean.getFiles());

		// save to other particles
		FileService service = new FileService();
		NanoparticleSample[] otherSamples = prepareCopy(request, theForm);
		if (otherSamples != null) {
			NanoparticleEntity copy = entityBean.getDomainCopy();
			for (NanoparticleSample sample : otherSamples) {
				compositionService.saveNanoparticleEntity(sample, copy);
				// update copied filename and save content and set visibility
				if (copy.getLabFileCollection() != null) {
					for (LabFile file : copy.getLabFileCollection()) {
						service.saveCopiedFileAndSetVisibility(file, user,
								particleBean.getDomainParticleSample()
										.getName(), sample.getName());
					}
				}
			}
		}

		InitCompositionSetup.getInstance().persistNanoparticleEntityDropdowns(
				request, entityBean);
		ActionMessages msgs = new ActionMessages();
		ActionMessage msg = new ActionMessage("message.addNanoparticleEntity");
		msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
		saveMessages(request, msgs);
		ActionForward forward = mapping.findForward("success");
		setupDataTree(theForm, request);
		return forward;
	}

	private boolean validateInherentFunctionType(
			HttpServletRequest request, NanoparticleEntityBean entityBean) 
		throws Exception {
		
		for (ComposingElementBean composingElementBean : entityBean.getComposingElements()) {
			for (FunctionBean functionBean : composingElementBean.getInherentFunctions()) {
				if(functionBean.getType() == null ||
					functionBean.getType().trim().length() == 0) {
					
					ActionMessages msgs = new ActionMessages();
					ActionMessage msg = new ActionMessage("errors.required", "inherent function type");
					msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
					this.saveErrors(request, msgs);
					
					return false;
				}
			}
		}
		return true;
	}
	
	private void setLookups(HttpServletRequest request) throws Exception {
		InitNanoparticleSetup.getInstance().setSharedDropdowns(request);
		InitCompositionSetup.getInstance().setNanoparticleEntityDropdowns(
				request);
	}

	/**
	 * Set up the input form for adding new nanoparticle entity
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("nanoparticleEntityForm");
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		setupParticle(theForm, request);
		setLookups(request);
		return mapping.getInputForward();
	}

	public ActionForward setupUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		setupParticle(theForm, request);
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		String entityId = request.getParameter("dataId");
		NanoparticleCompositionService compService = new NanoparticleCompositionService();
		NanoparticleEntityBean entityBean = compService
				.findNanoparticleEntityById(entityId);
		compService.retrieveVisibility(entityBean, user);
		entityBean.updateType(InitSetup.getInstance()
				.getClassNameToDisplayNameLookup(session.getServletContext()));
		theForm.set("entity", entityBean);
		setLookups(request);
		theForm.set("otherParticles", new String[0]);
		return mapping.getInputForward();
	}

	public ActionForward setupView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return setupUpdate(mapping, form, request, response);
	}

	public ActionForward addComposingElement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		NanoparticleEntityBean entity = (NanoparticleEntityBean) theForm
				.get("entity");
		entity.addComposingElement();
		InitCompositionSetup.getInstance().persistNanoparticleEntityDropdowns(
				request, entity);

		return mapping.getInputForward();
	}

	public ActionForward removeComposingElement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String indexStr = request.getParameter("compInd");
		int ind = Integer.parseInt(indexStr);
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		NanoparticleEntityBean entity = (NanoparticleEntityBean) theForm
				.get("entity");

		ComposingElementBean ceBean = entity.getComposingElements().get(ind);
		NanoparticleCompositionService compService = new NanoparticleCompositionService();
		boolean canRemove = compService
				.checkChemicalAssociationBeforeDelete(ceBean);
		InitCompositionSetup.getInstance().persistNanoparticleEntityDropdowns(
				request, entity);

		if (!canRemove) {
			ActionMessages msgs = new ActionMessages();
			ActionMessage msg = new ActionMessage(
					"error.removeComposingElementWithChemicalAssociation");
			msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			saveErrors(request, msgs);
			return mapping.getInputForward();
		}

		entity.removeComposingElement(ind);

		return mapping.getInputForward();
	}

	public ActionForward addInherentFunction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		NanoparticleEntityBean entity = (NanoparticleEntityBean) theForm
				.get("entity");

		String compEleIndexStr = (String) request.getParameter("compInd");
		int compEleIndex = Integer.parseInt(compEleIndexStr);
		ComposingElementBean compElement = (ComposingElementBean) entity
				.getComposingElements().get(compEleIndex);

		compElement.addFunction();
		InitCompositionSetup.getInstance().persistNanoparticleEntityDropdowns(
				request, entity);

		return mapping.getInputForward();
	}

	public ActionForward removeInherentFunction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String compEleIndexStr = (String) request.getParameter("compInd");
		int compEleIndex = Integer.parseInt(compEleIndexStr);

		String functionIndexStr = (String) request.getParameter("childCompInd");
		int functionIndex = Integer.parseInt(functionIndexStr);
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		NanoparticleEntityBean entity = (NanoparticleEntityBean) theForm
				.get("entity");
		ComposingElementBean compElement = (ComposingElementBean) entity
				.getComposingElements().get(compEleIndex);
		compElement.removeFunction(functionIndex);
		InitCompositionSetup.getInstance().persistNanoparticleEntityDropdowns(
				request, entity);

		return mapping.getInputForward();
	}

	public ActionForward addFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		NanoparticleEntityBean entity = (NanoparticleEntityBean) theForm
				.get("entity");
		entity.addFile();
		InitCompositionSetup.getInstance().persistNanoparticleEntityDropdowns(
				request, entity);

		return mapping.getInputForward();
	}

	public ActionForward removeFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String indexStr = request.getParameter("compInd");
		int ind = Integer.parseInt(indexStr);
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		NanoparticleEntityBean entity = (NanoparticleEntityBean) theForm
				.get("entity");
		entity.removeFile(ind);
		InitCompositionSetup.getInstance().persistNanoparticleEntityDropdowns(
				request, entity);

		return mapping.getInputForward();
	}

	public ActionForward input(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*
		 * DynaValidatorForm theForm = (DynaValidatorForm) form;
		 * NanoparticleEntityBean entity = (NanoparticleEntityBean) theForm
		 * .get("entity"); // update editable dropdowns HttpSession session =
		 * request.getSession();
		 * InitNanoparticleSetup.getInstance().updateEditableDropdown(session,
		 * composition.getCharacterizationSource(), "characterizationSources");
		 * 
		 * PolymerBean polymer = (PolymerBean) theForm.get("polymer");
		 * updatePolymerEditable(session, polymer);
		 * 
		 * DendrimerBean dendrimer = (DendrimerBean) theForm.get("dendrimer");
		 * updateDendrimerEditable(session, dendrimer);
		 */
		return mapping.findForward("setup");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		setLookups(request);
		NanoparticleCompositionService compositionService = new NanoparticleCompositionService();
		NanoparticleEntityBean entityBean = (NanoparticleEntityBean) theForm
				.get("entity");
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		ParticleBean particleBean = setupParticle(theForm, request);
		// setup domainFile uri for fileBeans
		String internalUriPath = CaNanoLabConstants.FOLDER_PARTICLE
				+ "/"
				+ particleBean.getDomainParticleSample().getName()
				+ "/"
				+ StringUtils
						.getOneWordLowerCaseFirstLetter("Nanoparticle Entity");
		entityBean.setupDomainEntity(InitSetup.getInstance()
				.getDisplayNameToClassNameLookup(
						request.getSession().getServletContext()), user
				.getLoginName(), internalUriPath);
		boolean canDelete = compositionService
				.checkChemicalAssociationBeforeDelete(entityBean);
		ActionMessages msgs = new ActionMessages();
		if (canDelete) {
			compositionService.deleteNanoparticleEntity(entityBean
					.getDomainEntity());
			ActionMessage msg = new ActionMessage(
					"message.deleteNanoparticleEntity");
			msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			saveMessages(request, msgs);
			setupDataTree(theForm, request);
			return mapping.findForward("success");
		} else {
			ActionMessage msg = new ActionMessage(
					"error.deleteNanoparticleEntityWithChemicalAssociation",
					entityBean.getClassName());
			msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			saveErrors(request, msgs);
			return mapping.getInputForward();
		}
	}

	protected boolean checkDelete(HttpServletRequest request,
			ActionMessages msgs, String id) throws Exception {
		NanoparticleCompositionService compService = new NanoparticleCompositionService();
		NanoparticleEntityBean entityBean = compService
				.findNanoparticleEntityById(id);
		if (!compService.checkChemicalAssociationBeforeDelete(entityBean)) {
			ActionMessage msg = new ActionMessage(
					"error.deleteNanoparticleEntityWithChemicalAssociation",
					entityBean.getClassName());
			msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			saveErrors(request, msgs);
			return false;
		}
		return true;
	}
}
