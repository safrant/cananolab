package gov.nih.nci.cananolab.domain.agentmaterial;
// Generated Oct 17, 2017 2:18:23 PM by Hibernate Tools 4.0.1.Final

import gov.nih.nci.cananolab.domain.particle.ActivationMethod;
import gov.nih.nci.cananolab.domain.particle.SampleComposition;
import java.util.Date;
import java.util.HashSet;

/**
 * Biopolymer generated by hbm2java
 */
public class Biopolymer extends gov.nih.nci.cananolab.domain.particle.FunctionalizingEntity
		implements java.io.Serializable {

	private String sequence;
	private String type;

	public Biopolymer() {
	}

	public Biopolymer(String createdBy, Date createdDate, String description, String molecularFormula,
			String molecularFormulaType, String name, float value, String valueUnit, String pubChemDataSourceName,
			long pubChemId, HashSet chemicalAssociationACollection, HashSet chemicalAssociationBCollection, HashSet fileCollection,
			SampleComposition sampleComposition, ActivationMethod activationMethod, HashSet functionCollection,
			String sequence, String type) {
		super(createdBy, createdDate, description, molecularFormula, molecularFormulaType, name, value, valueUnit,
				pubChemDataSourceName, pubChemId, chemicalAssociationACollection, chemicalAssociationBCollection,
				fileCollection, sampleComposition, activationMethod, functionCollection);
		this.sequence = sequence;
		this.type = type;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
