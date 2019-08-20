package gov.nih.nci.cananolab.domain.particle;
// Generated Oct 17, 2017 2:18:23 PM by Hibernate Tools 4.0.1.Final

import gov.nih.nci.cananolab.domain.common.Keyword;
import gov.nih.nci.cananolab.domain.common.PointOfContact;
import gov.nih.nci.cananolab.domain.common.Publication;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;

/**
 * Sample generated by hbm2java
 */
public class Sample implements java.io.Serializable {
	
	private Long id;
	private String createdBy;
	private Date createdDate;
	private String name;
	private PointOfContact primaryPointOfContact;
	private Set<Characterization> characterizationCollection = new HashSet<Characterization>(0);
	private SampleComposition sampleComposition;
	private Set<Publication> publicationCollection = new HashSet<Publication>(0);
	private Set<PointOfContact> otherPointOfContactCollection = new HashSet<PointOfContact>(0);
	private Set<Keyword> keywordCollection = new HashSet<Keyword>(0);
	private Set<Synthesis> synthesisCollection = new HashSet<Synthesis>();

	public Sample() {
	}

	@Deprecated
	public Sample(String createdBy, Date createdDate, String name, PointOfContact primaryPointOfContact,
			Set characterizationCollection, SampleComposition sampleComposition, Set publicationCollection,
			Set otherPointOfContactCollection, Set keywordCollection) {
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.name = name;
		this.primaryPointOfContact = primaryPointOfContact;
		this.characterizationCollection = characterizationCollection;
		this.sampleComposition = sampleComposition;
		this.publicationCollection = publicationCollection;
		this.otherPointOfContactCollection = otherPointOfContactCollection;
		this.keywordCollection = keywordCollection;
	}

	public Sample(String createdBy, Date createdDate, String name, PointOfContact primaryPointOfContact,
				  Set characterizationCollection, SampleComposition sampleComposition, Set publicationCollection,
				  Set otherPointOfContactCollection, Set keywordCollection, Set synthesisCollection) {
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.name = name;
		this.primaryPointOfContact = primaryPointOfContact;
		this.characterizationCollection = characterizationCollection;
		this.sampleComposition = sampleComposition;
		this.publicationCollection = publicationCollection;
		this.otherPointOfContactCollection = otherPointOfContactCollection;
		this.keywordCollection = keywordCollection;
		this.synthesisCollection = synthesisCollection;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PointOfContact getPrimaryPointOfContact() {
		return this.primaryPointOfContact;
	}

	public void setPrimaryPointOfContact(PointOfContact primaryPointOfContact) {
		this.primaryPointOfContact = primaryPointOfContact;
	}

	public Set<Characterization> getCharacterizationCollection() {
		return this.characterizationCollection;
	}

	public void setCharacterizationCollection(Set<Characterization> characterizationCollection) {
		this.characterizationCollection = characterizationCollection;
	}

	public SampleComposition getSampleComposition() {
		return this.sampleComposition;
	}

	public void setSampleComposition(SampleComposition sampleComposition) {
		this.sampleComposition = sampleComposition;
	}

	public Set<Publication> getPublicationCollection() {
		return this.publicationCollection;
	}

	public void setPublicationCollection(Set<Publication> publicationCollection) {
		this.publicationCollection = publicationCollection;
	}

	public Set<PointOfContact> getOtherPointOfContactCollection() {
		return this.otherPointOfContactCollection;
	}

	public void setOtherPointOfContactCollection(Set<PointOfContact> otherPointOfContactCollection) {
		this.otherPointOfContactCollection = otherPointOfContactCollection;
	}

	public Set<Keyword> getKeywordCollection() {
		return this.keywordCollection;
	}

//	@ManyToMany(cascade = { CascadeType.ALL })
//	@JoinTable(name = "keyword_sample", joinColumns = { @JoinColumn(name = "sample_pk_id") }, inverseJoinColumns = { @JoinColumn(name = "keyword_pk_id") })
	public void setKeywordCollection(Set<Keyword> keywordCollection) {
		this.keywordCollection = keywordCollection;
	}

	public Set<Synthesis> getSynthesisCollection(){return this.synthesisCollection;}

	public void setSynthesisCollection(Set<Synthesis> synthesisCollection){
		this.synthesisCollection=synthesisCollection;
	}
}
