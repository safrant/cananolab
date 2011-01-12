package gov.nih.nci.cananolab.service.study;

import gov.nih.nci.cananolab.dto.common.StudyBean;
import gov.nih.nci.cananolab.exception.NoAccessException;
import gov.nih.nci.cananolab.exception.StudyException;
import gov.nih.nci.cananolab.service.BaseService;

import java.util.List;

/**
 * 
 * @author lethai
 *
 */
public interface StudyService extends BaseService{
	
	/**
	 * find study detail information
	 * @param id
	 * @param loadAccessInfo
	 * @return
	 * @throws StudyException
	 * @throws NoAccessException
	 */
	public StudyBean findStudyById(String id, Boolean loadAccessInfo)
		throws StudyException, NoAccessException;
	/**
	 * Find the study ids with the given criteria
	 * @param studyName
	 * @param studyPointOfContact
	 * @param studyType
	 * @param studyDesignType
	 * @param sampleName
	 * @param isAnimalStudy
	 * @param diseases
	 * @param text - study descriptions and outcomes
	 * @param studyOwner - owner of the study
	 * @return
	 * @throws StudyException
	 */
	public List<String> findStudyIdsBy(String studyName, String studyPointOfContact, String studyType, String studyDesignType,
			String sampleName, Boolean isAnimalStudy, String diseases, String[] wordList, String studyOwner) 
		throws Exception;
	
	public int getNumberOfPublicStudies() throws StudyException;
	public void deleteStudy(String id) throws StudyException;
	public void saveStudy(StudyBean studyBean) throws StudyException;
	public StudyBean cloneStudy(String orginalName,String newName) throws StudyException;
}
