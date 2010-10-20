package gov.nih.nci.cananolab.service.admin.impl;

import gov.nih.nci.cananolab.domain.common.Author;
import gov.nih.nci.cananolab.domain.common.Datum;
import gov.nih.nci.cananolab.domain.common.Finding;
import gov.nih.nci.cananolab.domain.common.Organization;
import gov.nih.nci.cananolab.domain.common.PointOfContact;
import gov.nih.nci.cananolab.domain.common.Protocol;
import gov.nih.nci.cananolab.domain.common.Publication;
import gov.nih.nci.cananolab.domain.particle.Characterization;
import gov.nih.nci.cananolab.domain.particle.ChemicalAssociation;
import gov.nih.nci.cananolab.domain.particle.FunctionalizingEntity;
import gov.nih.nci.cananolab.domain.particle.NanomaterialEntity;
import gov.nih.nci.cananolab.domain.particle.Sample;
import gov.nih.nci.cananolab.domain.particle.SampleComposition;
import gov.nih.nci.cananolab.dto.common.AccessibilityBean;
import gov.nih.nci.cananolab.exception.AdministrationException;
import gov.nih.nci.cananolab.exception.NoAccessException;
import gov.nih.nci.cananolab.service.BaseService;
import gov.nih.nci.cananolab.service.BaseServiceLocalImpl;
import gov.nih.nci.cananolab.service.admin.OwnershipTransferService;
import gov.nih.nci.cananolab.service.admin.UpdateCreatedByService;
import gov.nih.nci.cananolab.service.community.CommunityService;
import gov.nih.nci.cananolab.service.community.impl.CommunityServiceLocalImpl;
import gov.nih.nci.cananolab.service.protocol.ProtocolService;
import gov.nih.nci.cananolab.service.protocol.helper.ProtocolServiceHelper;
import gov.nih.nci.cananolab.service.protocol.impl.ProtocolServiceLocalImpl;
import gov.nih.nci.cananolab.service.publication.PublicationService;
import gov.nih.nci.cananolab.service.publication.helper.PublicationServiceHelper;
import gov.nih.nci.cananolab.service.publication.impl.PublicationServiceLocalImpl;
import gov.nih.nci.cananolab.service.sample.SampleService;
import gov.nih.nci.cananolab.service.sample.impl.SampleServiceLocalImpl;
import gov.nih.nci.cananolab.service.security.SecurityService;
import gov.nih.nci.cananolab.service.security.UserBean;
import gov.nih.nci.cananolab.system.applicationservice.CustomizedApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * Service methods for transfer ownership.
 *
 * @author lethai, pansu
 */
public class UpdateCreatedByServiceImpl {

	private static Logger logger = Logger
			.getLogger(UpdateCreatedByServiceImpl.class);

	private int transferOwner(SampleService sampleService,
			List<String> sampleIds, String currentOwner,
			Boolean currentOwnerIsCurator, String newOwner,
			Boolean newOwnerIsCurator) throws AdministrationException,
			NoAccessException {
		SecurityService securityService = ((SampleServiceLocalImpl) sampleService)
				.getSecurityService();
		// user needs to be both curator and admin
		if (!(securityService.getUserBean().isCurator() && securityService
				.getUserBean().isAdmin())) {
			throw new NoAccessException();
		}
		int i = 0;
		try {
			CustomizedApplicationService appService = (CustomizedApplicationService) ApplicationServiceProvider
					.getApplicationService();
			for (String sampleId : sampleIds) {
				try {
					Sample domain = sampleService.findSampleById(sampleId,
							false).getDomain();
					String existingOwner = domain.getCreatedBy();
					System.out.println("ExistingOwner: " + existingOwner);
					if(existingOwner.substring(0, 3).equalsIgnoreCase("COPY")){
						domain.setCreatedBy(newOwner + ":" + existingOwner);
					}else{
						String test = existingOwner.substring(0, currentOwner.length());
						System.out.println("Test: " + test);
						if(test.equals(currentOwner)){
							domain.setCreatedBy(newOwner);
						}
					}
					SampleComposition sampleComposition = domain
							.getSampleComposition();
					appService.saveOrUpdate(domain);
					Collection<ChemicalAssociation> chemicalAssociation = new ArrayList<ChemicalAssociation>();
					Collection<FunctionalizingEntity> functionalizingEntity = new ArrayList<FunctionalizingEntity>();
					Collection<NanomaterialEntity> nanomaterialEntity = new ArrayList<NanomaterialEntity>();
					Collection<Characterization> characterization = new ArrayList<Characterization>();

					//point of contact
					PointOfContact poc = domain.getPrimaryPointOfContact();
					String existingPOC = poc.getCreatedBy();
					System.out.println("ExistingOwner: " + existingPOC);
					if(existingPOC.substring(0, 3).equalsIgnoreCase("COPY")){
						poc.setCreatedBy(newOwner + ":" + existingPOC);
					}else{
						String test = existingPOC.substring(0, currentOwner.length());
						System.out.println("Test: " + test);
						if(test.equals(currentOwner)){
							poc.setCreatedBy(newOwner);
						}
					}
					appService.saveOrUpdate(poc);
					//organization
					Organization organization = poc.getOrganization(); 
					String existingOrg = poc.getCreatedBy();
					System.out.println("ExistingOwner: " + existingOrg);
					if(existingOrg.substring(0, 3).equalsIgnoreCase("COPY")){
						organization.setCreatedBy(newOwner + ":" + existingOrg);
					}else{
						String test = existingOrg.substring(0, currentOwner.length());
						System.out.println("Test: " + test);
						if(test.equals(currentOwner)){
							organization.setCreatedBy(newOwner);
						}
					}
					appService.saveOrUpdate(organization);
					
					//updating Sample Composition
					if (sampleComposition != null) {
						chemicalAssociation = sampleComposition
								.getChemicalAssociationCollection();
						functionalizingEntity = sampleComposition
								.getFunctionalizingEntityCollection();
						nanomaterialEntity = sampleComposition
								.getNanomaterialEntityCollection();
						characterization = domain
								.getCharacterizationCollection();

						for (ChemicalAssociation ca : chemicalAssociation) {
							String existingChemicalAsso = ca.getCreatedBy();
							System.out.println("ExistingOwner: " + existingChemicalAsso);
							if(existingChemicalAsso.substring(0, 3).equalsIgnoreCase("COPY")){
								ca.setCreatedBy(newOwner + ":" + existingChemicalAsso);
							}else{
								String test = existingChemicalAsso.substring(0, currentOwner.length());
								System.out.println("Test: " + test);
								if(test.equals(currentOwner)){
									ca.setCreatedBy(newOwner);
								}
							}
							//ca.setCreatedBy(newOwner);
							appService.saveOrUpdate(ca);
						}
						for (FunctionalizingEntity fe : functionalizingEntity) {
							String existingFE = fe.getCreatedBy();
							System.out.println("ExistingOwner: " + existingFE);
							if(existingFE.substring(0, 3).equalsIgnoreCase("COPY")){
								fe.setCreatedBy(newOwner + ":" + existingFE);
							}else{
								String test = existingFE.substring(0, currentOwner.length());
								System.out.println("Test: " + test);
								if(test.equals(currentOwner)){
									fe.setCreatedBy(newOwner);
								}
							}
							//fe.setCreatedBy(newOwner);
							appService.saveOrUpdate(fe);
						}
						for (NanomaterialEntity ne : nanomaterialEntity) {
							String existingNE = ne.getCreatedBy();
							System.out.println("ExistingOwner: " + existingNE);
							if(existingNE.substring(0, 3).equalsIgnoreCase("COPY")){
								ne.setCreatedBy(newOwner + ":" + existingNE);
							}else{
								String test = existingNE.substring(0, currentOwner.length());
								System.out.println("Test: " + test);
								if(test.equals(currentOwner)){
									ne.setCreatedBy(newOwner);
								}
							}
							//ne.setCreatedBy(newOwner);
							appService.saveOrUpdate(ne);
						}

						for (Characterization c : characterization) {
							String existingChar = c.getCreatedBy();
							System.out.println("ExistingOwner: " + existingChar);
							if(existingChar.substring(0, 3).equalsIgnoreCase("COPY")){
								c.setCreatedBy(newOwner + ":" + existingChar);
							}else{
								String test = existingChar.substring(0, currentOwner.length());
								System.out.println("Test: " + test);
								if(test.equals(currentOwner)){
									c.setCreatedBy(newOwner);
								}
							}
							//c.setCreatedBy(newOwner);
							appService.saveOrUpdate(c);
						}
					}
					/*appService.saveOrUpdate(domain);
					this.assignAndRemoveAccessForSample(sampleService, domain,
							currentOwner, currentOwnerIsCurator, newOwner,
							newOwnerIsCurator);*/

				} catch (Exception e) {
					i++;
					String error = "Error transferring ownership for sample: "
							+ sampleId;
					logger.error(error, e);
				}
			}
		} catch (Exception e) {
			String error = "Error transferring ownership for samples";
			logger.error(error, e);
			throw new AdministrationException(error, e);
		}
		return i;
	}

	private AccessibilityBean[] assignAndRemoveAccess(BaseService service,
			String dataId, String currentOwner, Boolean currentOwnerIsCurator,
			String newOwner, Boolean newOwnerIsCurator) throws Exception {
		SecurityService securityService = ((BaseServiceLocalImpl) service)
				.getSecurityService();
		List<AccessibilityBean> userAccesses = service
				.findUserAccessibilities(dataId);
		List<AccessibilityBean> groupAccesses = service
				.findGroupAccessibilities(dataId);
		// an array to store assign and remove accessibility for the data
		AccessibilityBean[] assignRemoveAccesses = new AccessibilityBean[2];
		// ----assign access to the owner
		// if newOwner is a curator, don't need to assign access to new owner
		if (newOwnerIsCurator) {
			assignRemoveAccesses[0] = null;
		}
		// if newOwner is not a curator, need to assign user access to new owner
		else {
			if (userAccesses != null && !userAccesses.isEmpty()) {
				assignRemoveAccesses[0] = this
						.getNewUserAccessFromUserAccesses(userAccesses,
								currentOwner, newOwner);
			}
			if (assignRemoveAccesses[0]==null) {
				assignRemoveAccesses[0] = this
						.getNewUserAccessFromGroupAccesses(securityService,
								groupAccesses, currentOwner, newOwner);
			}
		}

		// ---remove access from the owner
		// if currentOwner is a curator, don't need to remove access from
		// current owner
		if (currentOwnerIsCurator) {
			assignRemoveAccesses[1] = null;
		} else {
			// remove currentOwner access if user accesses contains user
			if (userAccesses != null && !userAccesses.isEmpty()) {
				for (AccessibilityBean access : userAccesses) {
					if (access.getUserBean().getLoginName()
							.equals(currentOwner)) {
						assignRemoveAccesses[1] = access;
						break;
					}
				}
			}
			// if only group accesses don't need to remove.
			else {
				assignRemoveAccesses[1] = null;
			}
		}
		return assignRemoveAccesses;
	}

	// take care of accessibility when ownership has been transfered for Sample.
	private void assignAndRemoveAccessForSample(SampleService sampleService,
			Sample sample, String currentOwner, Boolean currentOwnerIsCurator,
			String newOwner, Boolean newOwnerIsCurator) throws Exception {
		String dataId = sample.getId().toString();
		AccessibilityBean[] assignRemoveAccesses = this.assignAndRemoveAccess(
				sampleService, dataId, currentOwner, currentOwnerIsCurator,
				newOwner, newOwnerIsCurator);
		// assign
		if (assignRemoveAccesses[0] != null) {
			sampleService.assignAccessibility(assignRemoveAccesses[0], sample);
		}
		// remove
		if (assignRemoveAccesses[1] != null) {
			sampleService.removeAccessibility(assignRemoveAccesses[1], sample);
		}
	}

	private int transferOwner(PublicationService publicationService,
			List<String> publicationIds, String currentOwner,
			Boolean currentOwnerIsCurator, String newOwner,
			Boolean newOwnerIsCurator) throws AdministrationException,
			NoAccessException {
		SecurityService securityService = ((PublicationServiceLocalImpl) publicationService)
				.getSecurityService();
		// user needs to be both curator and admin
		if (!(securityService.getUserBean().isCurator() && securityService
				.getUserBean().isAdmin())) {
			throw new NoAccessException();
		}
		int i = 0;
		try {
			CustomizedApplicationService appService = (CustomizedApplicationService) ApplicationServiceProvider
					.getApplicationService();
			PublicationServiceHelper helper = new PublicationServiceHelper(
					securityService);
			for (String publicationId : publicationIds) {
				try {
					Publication publication = helper
							.findPublicationById(publicationId);
					String existingOwner = publication.getCreatedBy();
					System.out.println("ExistingOwner: " + existingOwner);
					if(existingOwner.substring(0, 3).equalsIgnoreCase("COPY")){
						publication.setCreatedBy(newOwner + ":" + existingOwner);
					}else{
						String test = existingOwner.substring(0, currentOwner.length());
						if(test.equals(currentOwner)){
							publication.setCreatedBy(newOwner);
						}
					}
										
					appService.saveOrUpdate(publication);
					//datum
					Collection<Datum> datumCollection = publication.getDatumCollection();
					for(Datum d : datumCollection){
						String existingDatum = d.getCreatedBy();
						System.out.println("ExistingOwner: " + existingDatum);
						if(existingDatum.substring(0, 3).equalsIgnoreCase("COPY")){
							d.setCreatedBy(newOwner + ":" + existingOwner);
						}else{
							String test = existingDatum.substring(0, currentOwner.length());
							if(test.equals(currentOwner)){
								d.setCreatedBy(newOwner);
							}
						}
						appService.saveOrUpdate(d);
					}
					//finding
					Collection<Finding> findingCollection = publication.getFindingCollection();
					for(Finding f : findingCollection){
						String existingFinding = f.getCreatedBy();
						System.out.println("ExistingOwner: " + existingFinding);
						if(existingFinding.substring(0, 3).equalsIgnoreCase("COPY")){
							f.setCreatedBy(newOwner + ":" + existingOwner);
						}else{
							String test = existingFinding.substring(0, currentOwner.length());
							if(test.equals(currentOwner)){
								f.setCreatedBy(newOwner);
							}
						}
						appService.saveOrUpdate(f);
					}
					//author
					Collection<Author> authorCollection = publication.getAuthorCollection();
					for(Author a : authorCollection){
						String existingAuthor = a.getCreatedBy();
						System.out.println("ExistingOwner: " + existingAuthor);
						if(existingAuthor.substring(0, 3).equalsIgnoreCase("COPY")){
							a.setCreatedBy(newOwner + ":" + existingOwner);
						}else{
							String test = existingAuthor.substring(0, currentOwner.length());
							if(test.equals(currentOwner)){
								a.setCreatedBy(newOwner);
							}
						}
						appService.saveOrUpdate(a);
					}
					/*this.assignAndRemoveAccessForPublication(
							publicationService, publication, currentOwner,
							currentOwnerIsCurator, newOwner, newOwnerIsCurator);*/
				} catch (Exception e) {
					i++;
					String error = "Error transferring ownership for publication: "
							+ publicationId;
					logger.error(error, e);
				}
			}
		} catch (Exception e) {
			String error = "Error transferring ownership for publications";
			logger.error(error, e);
			throw new AdministrationException(error, e);
		}
		return i;
	}

	private void assignAndRemoveAccessForPublication(
			PublicationService service, Publication publication,
			String currentOwner, Boolean currentOwnerIsCurator,
			String newOwner, Boolean newOwnerIsCurator) throws Exception {
		String dataId = publication.getId().toString();
		AccessibilityBean[] assignRemoveAccesses = this.assignAndRemoveAccess(
				service, dataId, currentOwner, currentOwnerIsCurator, newOwner,
				newOwnerIsCurator);
		// assign
		if (assignRemoveAccesses[0] != null) {
			service.assignAccessibility(assignRemoveAccesses[0], publication);
		}
		// remove
		if (assignRemoveAccesses[1] != null) {
			service.removeAccessibility(assignRemoveAccesses[1], publication);
		}
	}

	private int transferOwner(ProtocolService protocolService,
			List<String> protocolIds, String currentOwner,
			Boolean currentOwnerIsCurator, String newOwner,
			Boolean newOwnerIsCurator) throws AdministrationException,
			NoAccessException {
		SecurityService securityService = ((ProtocolServiceLocalImpl) protocolService)
				.getSecurityService();
		// user needs to be both curator and admin
		if (!(securityService.getUserBean().isCurator() && securityService
				.getUserBean().isAdmin())) {
			throw new NoAccessException();
		}
		int i = 0;
		try {
			CustomizedApplicationService appService = (CustomizedApplicationService) ApplicationServiceProvider
					.getApplicationService();
			ProtocolServiceHelper helper = new ProtocolServiceHelper(
					securityService);
			for (String protocolId : protocolIds) {
				try {
					Protocol protocol = helper.findProtocolById(protocolId);
					String existingOwner = protocol.getCreatedBy();
					System.out.println("ExistingOwner: " + existingOwner);
					if(existingOwner.substring(0, 4).equalsIgnoreCase("COPY")){
						protocol.setCreatedBy(newOwner + ":" + existingOwner);
					}else{
						String test = existingOwner.substring(0, currentOwner.length());
						if(test.equals(currentOwner)){
							protocol.setCreatedBy(newOwner);
						}						
					}
				
					appService.saveOrUpdate(protocol);
					/*this.assignAndRemoveAccessForProtocol(protocolService,
							protocol, currentOwner, currentOwnerIsCurator,
							newOwner, newOwnerIsCurator);*/
				} catch (Exception e) {
					i++;
					String error = "Error transferring ownership for protocol: "
							+ protocolId;
					logger.error(error, e);
				}
			}
		} catch (Exception e) {
			String error = "Error transferring ownership for protocols";
			logger.error(error, e);
			throw new AdministrationException(error, e);
		}
		return i;
	}

	private void assignAndRemoveAccessForProtocol(ProtocolService service,
			Protocol protocol, String currentOwner,
			Boolean currentOwnerIsCurator, String newOwner,
			Boolean newOwnerIsCurator) throws Exception {
		String dataId = protocol.getId().toString();
		AccessibilityBean[] assignRemoveAccesses = this.assignAndRemoveAccess(
				service, dataId, currentOwner, currentOwnerIsCurator, newOwner,
				newOwnerIsCurator);
		// assign
		if (assignRemoveAccesses[0] != null) {
			service.assignAccessibility(assignRemoveAccesses[0], protocol);
		}
		// remove
		if (assignRemoveAccesses[1] != null) {
			service.removeAccessibility(assignRemoveAccesses[1], protocol);
		}
	}

	private int transferOwner(CommunityService communityService,
			List<String> collaborationGroupIds, String currentOwner,
			Boolean currentOwnerIsCurator, String newOwner,
			Boolean newOwnerIsCurator) throws AdministrationException,
			NoAccessException {
		SecurityService securityService = ((CommunityServiceLocalImpl) communityService)
				.getSecurityService();
		// user needs to be both curator and admin
		if (!(securityService.getUserBean().isCurator() && securityService
				.getUserBean().isAdmin())) {
			throw new NoAccessException();
		}
		int i = 0;
		try {
			for (String id : collaborationGroupIds) {
				try {
					communityService.assignOwner(id, newOwner);
					AccessibilityBean[] assignRemoveAccesses = this
							.assignAndRemoveAccess(
									communityService,
									AccessibilityBean.CSM_COLLABORATION_GROUP_PREFIX
											+ id, currentOwner,
									currentOwnerIsCurator, newOwner,
									newOwnerIsCurator);
					// assign
					if (assignRemoveAccesses[0] != null) {
						communityService.assignAccessibility(
								assignRemoveAccesses[0], id);
					}
					// remove
					if (assignRemoveAccesses[1] != null) {
						communityService.removeAccessibility(
								assignRemoveAccesses[1], id);
					}
				} catch (Exception e) {
					i++;
					String error = "Error transferring ownership for collaboration group: "
							+ id;
					logger.error(error, e);
				}
			}
		} catch (Exception e) {
			String error = "Error changing the collaboration group owner";
			throw new AdministrationException(error, e);
		}
		return i;
	}

	public int update(SecurityService securityService,
			List<String> dataIds, String dataType, String currentOwner,
			String newOwner) throws AdministrationException, NoAccessException {
		int numFailures = 0;
		try {
			BaseService service = null;
			if (dataType
					.equalsIgnoreCase(OwnershipTransferService.DATA_TYPE_SAMPLE)) {
				service = new SampleServiceLocalImpl(securityService);
			} else if (dataType
					.equalsIgnoreCase(OwnershipTransferService.DATA_TYPE_PROTOCOL)) {
				service = new ProtocolServiceLocalImpl(securityService);
			} else if (dataType
					.equalsIgnoreCase(OwnershipTransferService.DATA_TYPE_PUBLICATION)) {
				service = new PublicationServiceLocalImpl(securityService);
			} else if (dataType
					.equalsIgnoreCase(OwnershipTransferService.DATA_TYPE_GROUP)) {
				service = new CommunityServiceLocalImpl(securityService);
			} else {
				throw new AdministrationException(
						"No such transfer data type is supported.");
			}
			numFailures = this.update(service, dataIds, currentOwner,
					newOwner);
		} catch (Exception e) {
			String error = "Error in transfering ownership for type "
					+ dataType;
			logger.error(error, e);
			throw new AdministrationException(error, e);
		}
		return numFailures;
	}

	// replace the current owner userAccess userBean with new owner userBean
	// when the loginName match
	private AccessibilityBean getNewUserAccessFromUserAccesses(
			List<AccessibilityBean> userAccesses, String currentOwner,
			String newOwner) {
		for (AccessibilityBean access : userAccesses) {
			UserBean currentOwnerBean = access.getUserBean();
			if (currentOwnerBean.getLoginName().equals(currentOwner)) {
				UserBean newOwnerBean = new UserBean(newOwner);
				AccessibilityBean newAccess = new AccessibilityBean();
				newAccess.setAccessBy(access.getAccessBy());
				newAccess.setRoleName(access.getRoleName());
				newAccess.setUserBean(newOwnerBean);
				return newAccess;
			}
		}
		return null;
	}

	// replace the current owner groupAccess userBean with new owner userBean
	// when the loginName match
	private AccessibilityBean getNewUserAccessFromGroupAccesses(
			SecurityService securityService,
			List<AccessibilityBean> groupAccesses, String currentOwner,
			String newOwner) throws Exception {
		String newRoleName = null;
		for (AccessibilityBean access : groupAccesses) {
			String groupName = access.getGroupName();
			if (!groupName.equals(AccessibilityBean.CSM_PUBLIC_GROUP)) {
				// check if currentOwner a member of the group, if yes obtain
				// the role. If role is CURD, break, otherwise continue
				if (securityService.isUserInGroup(currentOwner, groupName)) {
					String roleName = access.getRoleName();
					if (roleName.equals(AccessibilityBean.CSM_CURD_ROLE)) {
						newRoleName = roleName;
						break;
					} else {
						newRoleName = roleName;
					}
				}
			}
		}
		if (newRoleName != null) {
			UserBean newOwnerBean = new UserBean(newOwner);
			AccessibilityBean newAccess = new AccessibilityBean();
			newAccess.setAccessBy(AccessibilityBean.ACCESS_BY_USER);
			newAccess.setRoleName(newRoleName);
			newAccess.setUserBean(newOwnerBean);
			return newAccess;
		}

		return null;
	}

	public int update(BaseService dataService, List<String> dataIds,
			String currentOwner, String newOwner)
			throws AdministrationException, NoAccessException {
		SecurityService securityService = ((BaseServiceLocalImpl) dataService)
				.getSecurityService();
		// check whether currentOwner and newOwner are curators
		boolean currentOwnerIsCurator = false;
		boolean newOwnerIsCurator = false;
		if (securityService.isCurator(currentOwner)) {
			currentOwnerIsCurator = true;
		}
		if (securityService.isCurator(newOwner)) {
			newOwnerIsCurator = true;
		}
		int numFailures = 0;
		if (dataService instanceof SampleService) {
			SampleService sampleService = (SampleService) dataService;
			numFailures = this.transferOwner(sampleService, dataIds,
					currentOwner, currentOwnerIsCurator, newOwner,
					newOwnerIsCurator);
		} else if (dataService instanceof ProtocolService) {
			ProtocolService protocolService = (ProtocolService) dataService;
			numFailures = this.transferOwner(protocolService, dataIds,
					currentOwner, currentOwnerIsCurator, newOwner,
					newOwnerIsCurator);
		} else if (dataService instanceof PublicationService) {
			PublicationService publicationService = (PublicationService) dataService;
			numFailures = this.transferOwner(publicationService, dataIds,
					currentOwner, currentOwnerIsCurator, newOwner,
					newOwnerIsCurator);
		} else if (dataService instanceof CommunityService) {
			CommunityService communityService = (CommunityService) dataService;
			numFailures = this.transferOwner(communityService, dataIds,
					currentOwner, currentOwnerIsCurator, newOwner,
					newOwnerIsCurator);
		} else {
			throw new AdministrationException(
					"Not a supported service for transferring ownership");
		}
		return numFailures;
	}
	
	
}
