package gov.nih.nci.calab.ui.workflow;

import gov.nih.nci.calab.service.util.CalabConstants;
import gov.nih.nci.calab.service.workflow.MaskService;
import gov.nih.nci.calab.ui.core.AbstractBaseAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;


/** 
* The MastAction sets is a generalized MaskAction class that is designed to Mask any caLAB object.
* The strMaskType checks the hidden field on a Mask form and uses this to determine the appropriate action. 
* @author      doswellj 
*/
public class MaskAction extends AbstractBaseAction
{

	private static Logger logger = Logger.getLogger(MaskAction.class);

	public ActionForward executeTask(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward forward = null;
		String strDescription = null;
		String strMaskType=null;
		String strId = null;
        String runId = null;
        
		ActionMessages msgs = new ActionMessages();
		try 
		{
            HttpSession session = request.getSession();
			DynaValidatorForm theForm = (DynaValidatorForm) form;
			
			strMaskType = (String) theForm.get("maskType");
			strDescription = theForm.getString("description");
            
			//Check mask type
			if (strMaskType.equals("aliquot"))
			{
			    strId = (String) theForm.get("aliquotId");
			}
			if (strMaskType.equals("file"))
			{
				strId = (String) theForm.get("fileId");
			}
			
            //1.Call MaskService to mask caLab component based on type(e.g., Aliquot, File, etc.)
		    //2.Display message that masking was successful
			MaskService maskservice = new MaskService();
			
			strDescription = strDescription + "   Masked by " + session.getAttribute("creator");
			maskservice.setMask(strMaskType, strId, strDescription);
			msgs = new ActionMessages();
			ActionMessage msg = null;
			if (strMaskType.equals("aliquot"))
			{
			       msg = new ActionMessage("message.maskAliquot", strId);
				   msgs.add("message", msg);
				   saveMessages(request, msgs);
				   forward = mapping.findForward("success");
			}
			if (strMaskType.equals("file"))
			{
				runId = (String)theForm.get("runId");
	            theForm.set("method","setup");
	            theForm.set("inout",(String)theForm.get("inout"));
	            request.setAttribute("inout",(String)theForm.get("inout"));
	              session.setAttribute("newWorkflowCreated","true");
	  			  forward = mapping.findForward("success");
			}
			forward = mapping.findForward("success");
			
			
		} catch (Exception e) {
			logger.error("Error Authenticating the user", e);
			ActionMessage error = null;
			ActionMessages errors = new ActionMessages();
			
			if (strMaskType.equals("aliquot"))
			{
				error = new ActionMessage("error.unexpectedMaskError", strId);
			}
			if (strMaskType.equals("file"))
			{
				error = new ActionMessage("error.unexpectedMaskError", strId);
			}
			errors.add("error", error);
			saveMessages(request, errors);
			forward = mapping.getInputForward();
			forward = mapping.findForward("failure");			
		}
		return forward;
	}

	public boolean loginRequired() 
	{
		// temporarily set to false until login module is working
		return true;
		// return true;
	}
	
}
