package gov.nih.nci.cananolab.service.publication;

import gov.nih.nci.cananolab.domain.particle.Sample;
import gov.nih.nci.cananolab.dto.common.PublicationBean;
import gov.nih.nci.cananolab.dto.common.UserBean;
import gov.nih.nci.cananolab.exception.DuplicateEntriesException;
import gov.nih.nci.cananolab.exception.NoAccessException;
import gov.nih.nci.cananolab.exception.PublicationException;

import java.util.List;

/**
 * Interface defining methods invovled in submiting and searching publications.
 *
 * @author tanq
 *
 */
public interface PublicationService {

	/**
	 * Persist a new publication or update an existing publication
	 *
	 * @param publication
	 * @param sampleNames
	 * @param fileData
	 * @param authors
	 *
	 * @throws Exception
	 */
	public void savePublication(PublicationBean publicationBean, UserBean user)
			throws PublicationException, NoAccessException, DuplicateEntriesException;

	public List<PublicationBean> findPublicationsBy(String publicationTitle,
			String publicationCategory, String sampleName,
			String[] researchAreas, String[] keywords, String pubMedId,
			String digitalObjectId, String[] authors,
			String[] nanomaterialEntityClassNames,
			String[] otherNanoparticleTypes,
			String[] functionalizingEntityClassNames,
			String[] otherFunctionalizingEntityTypes,
			String[] functionClassNames, String[] otherFunctionTypes,
			UserBean user) throws PublicationException;

	public PublicationBean findPublicationById(String publicationId,
			UserBean user) throws PublicationException, NoAccessException;

	public List<PublicationBean> findPublicationsBySampleId(String sampleId,
			UserBean user) throws PublicationException;

	public int getNumberOfPublicPublications() throws PublicationException;

	public void removePublicationFromSample(Sample particle, Long dataId)
			throws PublicationException, NoAccessException;
}
