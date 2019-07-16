package gov.nih.nci.cananolab.domain.common;
// Generated Apr 3, 2019, 8:32:55 PM by Hibernate Tools 5.4.2.Final

import gov.nih.nci.cananolab.domain.particle.SynthesisFuncPurification;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Purity generated by hbm2java
 */
public class Purity implements java.io.Serializable {

	private long purityPkId;
	private SynthesisFuncPurification synthesisFuncPurification;
	private String createdBy;
	private Date createdDate;
	private Set files = new HashSet(0);
	private Set purityDatums = new HashSet(0);

	public Purity() {
	}

	public Purity(long purityPkId, SynthesisFuncPurification synthesisFuncPurification, String createdBy,
			Date createdDate) {
		this.purityPkId = purityPkId;
		this.synthesisFuncPurification = synthesisFuncPurification;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public Purity(long purityPkId, SynthesisFuncPurification synthesisFuncPurification, String createdBy,
			Date createdDate, Set files, Set purityDatums) {
		this.purityPkId = purityPkId;
		this.synthesisFuncPurification = synthesisFuncPurification;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.files = files;
		this.purityDatums = purityDatums;
	}

	public long getId() {
		return this.purityPkId;
	}

	public void setId(long purityPkId) {
		this.purityPkId = purityPkId;
	}

	public SynthesisFuncPurification getSynthesisFuncPurification() {
		return this.synthesisFuncPurification;
	}

	public void setSynthesisFuncPurification(SynthesisFuncPurification synthesisFuncPurification) {
		this.synthesisFuncPurification = synthesisFuncPurification;
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

	public Set getFiles() {
		return this.files;
	}

	public void setFiles(Set files) {
		this.files = files;
	}

	public Set getPurityDatumCollection() {
		return this.purityDatums;
	}

	public void setPurityDatumCollection(Set purityDatums) {
		this.purityDatums = purityDatums;
	}

}
