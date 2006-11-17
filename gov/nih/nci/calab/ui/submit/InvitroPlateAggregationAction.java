package gov.nih.nci.calab.ui.submit;

/**
 * This class sets up input form for InVitro PlateAggregation characterization. 
 *  
 * @author beasleyj
 */

import gov.nih.nci.calab.domain.nano.characterization.Characterization;
import gov.nih.nci.calab.domain.nano.characterization.invitro.PlateAggregation;
import gov.nih.nci.calab.dto.characterization.CharacterizationFileBean;
import gov.nih.nci.calab.dto.characterization.DerivedBioAssayDataBean;
import gov.nih.nci.calab.dto.characterization.invitro.PlateAggregationBean;
import gov.nih.nci.calab.dto.common.UserBean;
import gov.nih.nci.calab.service.submit.SubmitNanoparticleService;
import gov.nih.nci.calab.service.util.CananoConstants;
import gov.nih.nci.calab.ui.core.BaseCharacterizationAction;
import gov.nih.nci.calab.ui.core.InitSessionSetup;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;

public class InvitroPlateAggregationAction extends BaseCharacterizationAction {

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
		ActionForward forward = null;

		DynaValidatorForm theForm = (DynaValidatorForm) form;
		String particleType = (String) theForm.get("particleType");
		String particleName = (String) theForm.get("particleName");
		PlateAggregationBean plateAggregationChar = (PlateAggregationBean) theForm.get("achar");

		if (plateAggregationChar.getId() == null || plateAggregationChar.getId() == "") {			
			plateAggregationChar.setId( (String) theForm.get("characterizationId") );			
		}
		
		int fileNumber = 0;
		for (DerivedBioAssayDataBean obj : plateAggregationChar.getDerivedBioAssayData()) {
			CharacterizationFileBean fileBean = (CharacterizationFileBean) request.getSession().getAttribute("characterizationFile" + fileNumber);
			if (fileBean != null) {		
				obj.setFile(fileBean);
			}
			fileNumber++;
		}
		
		// set createdBy and createdDate for the composition
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		Date date = new Date();
		plateAggregationChar.setCreatedBy(user.getLoginName());
		plateAggregationChar.setCreatedDate(date);

		request.getSession().setAttribute("newCharacterizationCreated", "true");
		SubmitNanoparticleService service = new SubmitNanoparticleService();
		service.addPlateAggregation(particleType, particleName, plateAggregationChar);

		ActionMessages msgs = new ActionMessages();
		ActionMessage msg = new ActionMessage("message.addInvitroPlateAggregation");
		msgs.add("message", msg);
		saveMessages(request, msgs);
		forward = mapping.findForward("success");

		InitSessionSetup.getInstance().setSideParticleMenu(request,
				particleName, particleType);
				
		HttpSession session = request.getSession();
		InitSessionSetup.getInstance().setAllInstrumentTypes(session);
		String selectedInstrumentType = null;
		
		if (plateAggregationChar.getInstrument().getOtherInstrumentType() != null && plateAggregationChar.getInstrument().getOtherInstrumentType() != "")
			selectedInstrumentType = plateAggregationChar.getInstrument().getOtherInstrumentType();
		else
			selectedInstrumentType = plateAggregationChar.getInstrument().getType();
		
		InitSessionSetup.getInstance().setManufacturerPerType(session, selectedInstrumentType);

		return forward;
	}

	protected void clearMap(HttpSession session, DynaValidatorForm theForm,
			ActionMapping mapping) throws Exception {
		String particleType = (String) theForm.get("particleType");
		String particleName = (String) theForm.get("particleName");

		// clear session data from the input forms
		theForm.getMap().clear();

		theForm.set("particleName", particleName);
		theForm.set("particleType", particleType);
		theForm.set("achar", new PlateAggregationBean());
	}

	protected void initSetup(HttpServletRequest request, DynaValidatorForm theForm)
			throws Exception {
		HttpSession session = request.getSession();
		String particleType = (String) theForm.get("particleType");
		String particleName = (String) theForm.get("particleName");
		String firstOption = InitSessionSetup.getInstance().setAllInstrumentTypes(session);
		InitSessionSetup.getInstance().setAllSizeDistributionGraphTypes(session);
		InitSessionSetup.getInstance().setAllControlTypes(session);
		InitSessionSetup.getInstance().setAllConditionTypes(session);
		InitSessionSetup.getInstance().setAllConcentrationUnits(session);
		InitSessionSetup.getInstance().setSideParticleMenu(request,
				particleName, particleType);
		if (firstOption == "")
			firstOption =  CananoConstants.OTHER;
		InitSessionSetup.getInstance().setManufacturerPerType(session, firstOption);
		session.setAttribute("selectedInstrumentType", "");
	}


	/**
	 * Update multiple children on the same form
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaValidatorForm theForm = (DynaValidatorForm) form;
		String particleType = (String) theForm.get("particleType");
		String particleName = (String) theForm.get("particleName");
		PlateAggregationBean achar = (PlateAggregationBean) theForm.get("achar");
		String index=(String)request.getParameter("index");	
		String type = (String)request.getParameter("type");
		
		if ( type != null && !type.equals("") && type.equals("charTables") ) {
			updateCharacterizationTables(achar);
		}
		if ( type != null && !type.equals("") && type.equals("addControl") ) {
			addControl(achar, index);
		}
		if ( type != null && !type.equals("") && type.equals("addConditions") ) {
			addConditions(achar, index);
		}
		if ( type != null && !type.equals("") && type.equals("updateConditions") ) {
			updateConditions(achar, index);
		}
		
		theForm.set("achar", achar);
		InitSessionSetup.getInstance().setSideParticleMenu(request,
				particleName, particleType);
		
		return mapping.getInputForward();
	}

	public boolean loginRequired() {
		return true;
	}

	@Override
	protected void setFormCharacterizationBean(DynaValidatorForm theForm, Characterization aChar) throws Exception {
		PlateAggregationBean charBean=new PlateAggregationBean((PlateAggregation)aChar);
		theForm.set("achar", charBean);		
	}

	@Override
	protected void setLoadFileRequest(HttpServletRequest request) {
		request.setAttribute("characterization", "plateAggregation");
		request.setAttribute("loadFileForward", "invitroPlateAggregationForm");
		
	}
}
