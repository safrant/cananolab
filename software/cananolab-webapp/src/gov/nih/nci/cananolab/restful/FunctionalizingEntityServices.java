package gov.nih.nci.cananolab.restful;

import gov.nih.nci.cananolab.dto.particle.composition.FunctionalizingEntityBean;
import gov.nih.nci.cananolab.restful.sample.FunctionalizingEntityBO;
import gov.nih.nci.cananolab.restful.sample.NanomaterialEntityBO;
import gov.nih.nci.cananolab.restful.util.CommonUtil;
import gov.nih.nci.cananolab.restful.view.edit.SimpleFunctionalizingEntityBean;
import gov.nih.nci.cananolab.restful.view.edit.SimpleNanomaterialEntityBean;
import gov.nih.nci.cananolab.service.security.UserBean;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

@Path("/functionalizingEntity")
public class FunctionalizingEntityServices {

private Logger logger = Logger.getLogger(FunctionalizingEntityServices.class);
	
	@Inject
	ApplicationContext applicationContext;
	
	@GET
	@Path("/setup")
	@Produces ("application/json")
    public Response setup(@Context HttpServletRequest httpRequest, @DefaultValue("") @QueryParam("sampleId") String sampleId) {
				
		try { 
			FunctionalizingEntityBO functionalizingEntity = 
					(FunctionalizingEntityBO) applicationContext.getBean("functionalizingEntityBO");
			Map<String, Object> dropdownMap = functionalizingEntity.setupNew(sampleId, httpRequest);
			return Response.ok(dropdownMap).header("Access-Control-Allow-Credentials", "true").header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS").header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization").build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(CommonUtil.wrapErrorMessageInList("Error while setting up drop down lists" + e.getMessage())).build();

		}
	}
	
	@GET
	@Path("/edit")
	@Produces ("application/json")
    public Response edit(@Context HttpServletRequest httpRequest, @DefaultValue("") @QueryParam("sampleId") String sampleId, @DefaultValue("") @QueryParam("dataId") String dataId) {
				
		try { 
			FunctionalizingEntityBO functionalizingEntity = 
					(FunctionalizingEntityBO) applicationContext.getBean("functionalizingEntityBO");
			UserBean user = (UserBean) (httpRequest.getSession().getAttribute("user"));
			if (user == null) 
				return Response.status(Response.Status.UNAUTHORIZED)
						.entity("Session expired").build();
			
			FunctionalizingEntityBean bean = functionalizingEntity.setupUpdate(sampleId, dataId, httpRequest);
			SimpleFunctionalizingEntityBean funcBean = new SimpleFunctionalizingEntityBean();
			funcBean.tranferSimpleFunctionalizingBean(bean);
			List<String> errors = funcBean.getErrors();
			return (errors == null || errors.size() == 0) ?
					Response.ok(funcBean).build() :
						Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errors).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(CommonUtil.wrapErrorMessageInList("Error while viewing the NanoMaterial Entity" + e.getMessage())).build();

		}
	}
}