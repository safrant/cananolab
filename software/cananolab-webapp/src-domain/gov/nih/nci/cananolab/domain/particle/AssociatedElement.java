package gov.nih.nci.cananolab.domain.particle;
// Generated Oct 17, 2017 2:18:23 PM by Hibernate Tools 4.0.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AssociatedElement generated by hbm2java
 */
public class AssociatedElement implements java.io.Serializable {

	private Long id;
	private String createdBy;
	private Date createdDate;
	private String description;
	private String molecularFormula;
	private String molecularFormulaType;
	private String name;
	private Float value;
	private String valueUnit;
	private String pubChemDataSourceName;
	private Long pubChemId;
	private Set chemicalAssociationACollection = new HashSet(0);
	private Set chemicalAssociationBCollection = new HashSet(0);

	public AssociatedElement() {
	}

	public AssociatedElement(String createdBy, Date createdDate, String description, String molecularFormula,
			String molecularFormulaType, String name, float value, String valueUnit, String pubChemDataSourceName,
			long pubChemId, Set chemicalAssociationACollection, Set chemicalAssociationBCollection) {
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.description = description;
		this.molecularFormula = molecularFormula;
		this.molecularFormulaType = molecularFormulaType;
		this.name = name;
		this.value = value;
		this.valueUnit = valueUnit;
		this.pubChemDataSourceName = pubChemDataSourceName;
		this.pubChemId = pubChemId;
		this.chemicalAssociationACollection = chemicalAssociationACollection;
		this.chemicalAssociationBCollection = chemicalAssociationBCollection;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMolecularFormula() {
		return this.molecularFormula;
	}

	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}

	public String getMolecularFormulaType() {
		return this.molecularFormulaType;
	}

	public void setMolecularFormulaType(String molecularFormulaType) {
		this.molecularFormulaType = molecularFormulaType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getValue() {
		return this.value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public String getValueUnit() {
		return this.valueUnit;
	}

	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}

	public String getPubChemDataSourceName() {
		return this.pubChemDataSourceName;
	}

	public void setPubChemDataSourceName(String pubChemDataSourceName) {
		this.pubChemDataSourceName = pubChemDataSourceName;
	}

	public Long getPubChemId() {
		return this.pubChemId;
	}

	public void setPubChemId(Long pubChemId) {
		this.pubChemId = pubChemId;
	}

	public Set getChemicalAssociationACollection() {
		return this.chemicalAssociationACollection;
	}

	public void setChemicalAssociationACollection(Set chemicalAssociationACollection) {
		this.chemicalAssociationACollection = chemicalAssociationACollection;
	}

	public Set getChemicalAssociationBCollection() {
		return this.chemicalAssociationBCollection;
	}

	public void setChemicalAssociationBCollection(Set chemicalAssociationBCollection) {
		this.chemicalAssociationBCollection = chemicalAssociationBCollection;
	}

}