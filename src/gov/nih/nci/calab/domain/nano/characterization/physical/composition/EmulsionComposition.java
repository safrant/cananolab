package gov.nih.nci.calab.domain.nano.characterization.physical.composition;

import gov.nih.nci.calab.domain.nano.particle.Nanoparticle;
import gov.nih.nci.calab.service.util.CananoConstants;

import java.util.Collection;
import java.util.HashSet;

public class EmulsionComposition implements ParticleComposition {
	private Long id;
	private String source;
	private String description;
	private String identificationName;
	private String classification;
	private String name;
	private Collection<Nanoparticle> nanoparticleCollection;
	private Collection<ComposingElement> composingElementCollection = new HashSet<ComposingElement>();
	
	private String type;
	private String molecularFormula;
	private Float dropletSize;
	private boolean polymerized;
	private String polymerName;
	
	public EmulsionComposition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Float getDropletSize() {
		return dropletSize;
	}

	public void setDropletSize(Float dropletSize) {
		this.dropletSize = dropletSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMolecularFormula() {
		return molecularFormula;
	}

	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}

	public String getPolymerName() {
		return polymerName;
	}

	public void setPolymerName(String polymerName) {
		this.polymerName = polymerName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return this.source;
	}

	public String getClassification() {
		return CananoConstants.PHYSICAL_CHARACTERIZATION;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdentificationName() {
		return identificationName;
	}

	public void setIdentificationName(String identificationName) {
		this.identificationName = identificationName;
	}

	public String getName() {
		return CananoConstants.PHYSICAL_COMPOSITION;
	}

	public void setNanoparticleCollection(Collection<Nanoparticle> particleCollection) {
		this.nanoparticleCollection = particleCollection;
	}

	public Collection<Nanoparticle> getNanoparticleCollection() {
		return this.nanoparticleCollection;
	}
	
	public void setComposingElementCollection(Collection<ComposingElement> element){
		this.composingElementCollection = element;
	}
	
	public Collection<ComposingElement> getComposingElementCollection(){
		return this.composingElementCollection;
	}

	public boolean isPolymerized() {
		return polymerized;
	}

	public void setPolymerized(boolean polymerized) {
		this.polymerized = polymerized;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public void setName(String name) {
		this.name = name;
	}
}
