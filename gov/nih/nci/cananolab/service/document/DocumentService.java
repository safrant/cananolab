package gov.nih.nci.cananolab.service.document;

import gov.nih.nci.cananolab.domain.common.LabFile;
import gov.nih.nci.cananolab.domain.particle.NanoparticleSample;
import gov.nih.nci.cananolab.dto.common.DocumentSummaryBean;
import gov.nih.nci.cananolab.dto.particle.ParticleBean;
import gov.nih.nci.cananolab.exception.DocumentException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Interface defining methods invovled in searching documents.
 * 
 * @author tanq
 * 
 */
public interface DocumentService {

	public int getNumberOfPublicDocuments() throws DocumentException;

	public void removeDocumentFromParticle(NanoparticleSample particle,
			Long dataId) 	throws DocumentException;


	public void exportFullSummary(DocumentSummaryBean summaryBean,
			OutputStream out) throws IOException ;

	public void exportSummary(ParticleBean particleBean,
			OutputStream out) throws IOException ;


}
