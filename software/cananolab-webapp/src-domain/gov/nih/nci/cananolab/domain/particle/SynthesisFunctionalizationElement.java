package gov.nih.nci.cananolab.domain.particle;

import gov.nih.nci.cananolab.domain.common.File;
import gov.nih.nci.cananolab.domain.common.Supplier;

import java.util.*;

public class SynthesisFunctionalizationElement {
    private Long synthesisFunctionalizationElementPkId;
    private SynthesisFunctionalization synthesisFunctionalization;
    private Long  synthesisFunctionalizationId;

    public SynthesisFunctionalization getSynthesisFunctionalization() {
        return synthesisFunctionalization;
    }

    public void setSynthesisFunctionalization(SynthesisFunctionalization synthesisFunctionalization) {
        this.synthesisFunctionalization = synthesisFunctionalization;
    }

    private String molecularFormula;
    private String molecularFormulaType;
    private String description;
    private String createdBy;
    private Date createdDate;
    private String chemicalName;
    private Float value;
    private String valueUnit;
    private String pubChemDatasourceName;
    private Long pubChemId;
    private Set<SfeInherentFunction> sfeInherentFunctions = new HashSet<SfeInherentFunction>(0); // TODO
    private Set<File> files = new HashSet<File>(0);
    private Supplier supplier;
    private String type;
    private String activationMethod;
    private String activationEffect;
 //   private Set<SynthesisFunctionalizationElement> synthesisFunctionalizationElements = new HashSet<SynthesisFunctionalizationElement>(0);


    public SynthesisFunctionalizationElement() {
        System.out.println("MHL 000 SynthesisFunctionalizationElement");
      //  synthesisFunctionalizationElementPkId = 1000L;
    }

    public SynthesisFunctionalizationElement(Long synthesisFunctionalizationElementPkId, SynthesisFunctionalization synthesisFunctionalization,
                                    String createdBy, Date createdDate) {
        System.out.println("MHL 001 SynthesisFunctionalizationElement synthesisFunctionalizationElementPkId: " + synthesisFunctionalizationElementPkId);
        this.synthesisFunctionalizationElementPkId = synthesisFunctionalizationElementPkId;
        this.synthesisFunctionalization = synthesisFunctionalization;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public SynthesisFunctionalizationElement(Long synthesisFunctionalizationElementPkId, SynthesisFunctionalization synthesisFunctionalization,
                                    String molecularFormula, String molecularFormulaType, String description, String createdBy,
                                    Date createdDate, String chemicalName, Float value, String valueUnit, String pubChemDatasourceName,
                                    Long pubChemId, Set<SfeInherentFunction> sfeInherentFunctions, Set<File> files, Supplier supplier, String type) {
        System.out.println("MHL 002 SynthesisFunctionalizationElement synthesisFunctionalizationElementPkId: " + synthesisFunctionalizationElementPkId);
        this.synthesisFunctionalizationElementPkId = synthesisFunctionalizationElementPkId;
        this.synthesisFunctionalization = synthesisFunctionalization;
        this.molecularFormula = molecularFormula;
        this.molecularFormulaType = molecularFormulaType;
        this.description = description;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.chemicalName = chemicalName;
        this.value = value;
        this.valueUnit = valueUnit;
        this.pubChemDatasourceName = pubChemDatasourceName;
        this.pubChemId = pubChemId;
        this.sfeInherentFunctions = sfeInherentFunctions;
        this.files = files;
        this.supplier = supplier;
        this.type = type;
    }



    public Long getId() {
        System.out.println("MHL 004 getId synthesisFunctionalizationElementPkId: " + synthesisFunctionalizationElementPkId);
        return this.synthesisFunctionalizationElementPkId;
    }

    public void setId(Long synthesisFunctionalizationElementPkId) {
        System.out.println("MHL 003 setId synthesisFunctionalizationElementPkId: " + synthesisFunctionalizationElementPkId);
        this.synthesisFunctionalizationElementPkId = synthesisFunctionalizationElementPkId;
    }


    public void setSynthesisFunctionalizationId(Long synthesisFunctionalizationId) {
        System.out.println("MHL 001 setSynthesisFunctionalizationId: " + synthesisFunctionalizationId);
        this.synthesisFunctionalizationId = synthesisFunctionalizationId;
    }

    public Long getSynthesisFunctionalizationId() {
        System.out.println("MHL 002getSynthesisFunctionalizationId: " + synthesisFunctionalizationId);
        return synthesisFunctionalizationId;
    }

    public String getActivationMethod() {
        return activationMethod;
    }

    public void setActivationMethod(String activationMethod) {
        this.activationMethod = activationMethod;
    }

    public String getActivationEffect() {
        return activationEffect;
    }

    public void setActivationEffect(String activationEffect) {
        this.activationEffect = activationEffect;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getChemicalName() {
        return this.chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
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

    public String getPubChemDatasourceName() {
        return this.pubChemDatasourceName;
    }

    public void setPubChemDatasourceName(String pubChemDatasourceName) {
        this.pubChemDatasourceName = pubChemDatasourceName;
    }

    public Long getPubChemId() {
        return this.pubChemId;
    }

    public void setPubChemId(Long pubChemId) {
        this.pubChemId = pubChemId;
    }

    public Set<SfeInherentFunction> getSfeInherentFunctions() {
        return this.sfeInherentFunctions;
    }

    public void setSfeInherentFunctions(Set<SfeInherentFunction> sfeInherentFunctions) {
        this.sfeInherentFunctions = sfeInherentFunctions;
    }

    public void addSfeInherentFunction(SfeInherentFunction function){
        this.sfeInherentFunctions.add(function);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<File> getFiles() {
        return this.files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}