package gov.nih.nci.cananolab.domain.particle;
// Generated Apr 3, 2019, 8:32:55 PM by Hibernate Tools 5.4.2.Final


import gov.nih.nci.cananolab.domain.common.File;
import gov.nih.nci.cananolab.domain.common.Protocol;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SynthesisFunctionalization generated by hbm2java
 */
public class SynthesisFunctionalization implements Serializable {

	private Long synthesisFunctionalizationPkId;
	private Synthesis synthesis;
	private Protocol protocol;
	private String description;
	private Date createdDate;
	private String createdBy;
	private Set<SynthesisFunctionalizationElement> synthesisFunctionalizationElements = new HashSet<SynthesisFunctionalizationElement>();
	private Set<File> files = new HashSet<File>();
//	private String type;


	public SynthesisFunctionalization() {
	   // System.out.println("MHL 000 SynthesisFunctionalization. Forcing this.synthesisFunctionalizationPkId = 1000L");
		// this.synthesisFunctionalizationPkId = 1000L; //FIXME TESTING ONLY!!!!!!
	}


	public SynthesisFunctionalization(Long synthesisFuntionalizationPkId, SynthesisFunctionalization sf) {
        System.out.println("MHL 001 SynthesisFunctionalization");
		this.synthesisFunctionalizationPkId = synthesisFuntionalizationPkId ;
		this.synthesis = sf.getSynthesis() ;
		this.protocol = sf.getProtocol() ;
		this.description = sf.getDescription() ;
		this.createdDate = sf.getCreatedDate() ;
		this.createdBy = sf.getCreatedBy() ;
		this.synthesisFunctionalizationElements = sf.getSynthesisFunctionalizationElements() ;
		this.files = sf.getFiles() ;
	}


	public SynthesisFunctionalization(Long synthesisFuntionalizationPkId) {
        System.out.println("MHL 002 SynthesisFunctionalization");
		this.synthesisFunctionalizationPkId = synthesisFuntionalizationPkId;
	}

	public SynthesisFunctionalization(Long synthesisFuntionalizationPkId, Synthesis synthesis,
			Set synthesisFuncPurifications) {
        System.out.println("MHL 003 SynthesisFunctionalization");
		this.synthesisFunctionalizationPkId = synthesisFuntionalizationPkId;
		this.synthesis = synthesis;

	}

	public Long getId() {
		return this.synthesisFunctionalizationPkId;
	}

//    public String getType() {
//		return this.type;
//    }
//
//    public void setType(String type){
//		this.type=type;
//	}

	public void addSynthesisFunctionalizationElement(SynthesisFunctionalizationElement domainEntity) {
		synthesisFunctionalizationElements.add(domainEntity);
	}

	public void setId(Long synthesisFuntionalizationPkId) {
		this.synthesisFunctionalizationPkId = synthesisFuntionalizationPkId;
	}

	public Synthesis getSynthesis() {
		return this.synthesis;
	}

	public void setSynthesis(Synthesis synthesis) {
		this.synthesis = synthesis;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Set<SynthesisFunctionalizationElement> getSynthesisFunctionalizationElements() {
		return synthesisFunctionalizationElements;
	}

	public void setSynthesisFunctionalizationElements(Set<SynthesisFunctionalizationElement> synthesisFunctionalizationElements) {
		this.synthesisFunctionalizationElements = synthesisFunctionalizationElements;
	}



	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}
}
