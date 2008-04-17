package gov.nih.nci.cananolab.dto.particle.composition;

import gov.nih.nci.cananolab.domain.common.LabFile;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.Biopolymer;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.CarbonNanotube;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.ComposingElement;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.Dendrimer;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.Emulsion;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.Fullerene;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.Liposome;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.NanoparticleEntity;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.OtherNanoparticleEntity;
import gov.nih.nci.cananolab.domain.particle.samplecomposition.base.Polymer;
import gov.nih.nci.cananolab.util.ClassUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the view bean for the NanoparticleEntity domain object
 * 
 * @author pansu
 * 
 */
public class NanoparticleEntityBean {
	private String type;

	private String description;

	private String className;

	private Polymer polymer = new Polymer();

	private Biopolymer biopolymer = new Biopolymer();

	private Dendrimer dendrimer = new Dendrimer();

	private CarbonNanotube carbonNanotube = new CarbonNanotube();

	private Liposome liposome = new Liposome();

	private Emulsion emulsion = new Emulsion();

	private Fullerene fullerene = new Fullerene();

	private OtherNanoparticleEntity otherEntity = new OtherNanoparticleEntity();

	private List<ComposingElementBean> composingElements = new ArrayList<ComposingElementBean>();

	private Set<ComposingElement> domainComposingElements = new HashSet<ComposingElement>();

	private Set<LabFile> files = new HashSet<LabFile>();

	private NanoparticleEntity domainEntity = new NanoparticleEntity();

	public NanoparticleEntityBean() {
		ComposingElementBean ceb = new ComposingElementBean();
		composingElements.add(ceb);
	}

	public NanoparticleEntityBean(NanoparticleEntity nanoparticleEntity) {
		if (nanoparticleEntity instanceof Dendrimer) {			
			dendrimer = (Dendrimer) nanoparticleEntity;
			domainEntity = dendrimer;
			className = ClassUtils.getShortClassName(Dendrimer.class.getName());
		} else if (nanoparticleEntity instanceof CarbonNanotube) {
			carbonNanotube = (CarbonNanotube) nanoparticleEntity;
			domainEntity = carbonNanotube;
			className = ClassUtils.getShortClassName(CarbonNanotube.class
					.getName());
		} else if (nanoparticleEntity instanceof Emulsion) {
			emulsion = (Emulsion) nanoparticleEntity;
			domainEntity = emulsion;
			className = ClassUtils.getShortClassName(Emulsion.class.getName());
		} else if (nanoparticleEntity instanceof Fullerene) {
			fullerene = (Fullerene) nanoparticleEntity;
			domainEntity = fullerene;
			className = ClassUtils.getShortClassName(Fullerene.class.getName());
		} else if (nanoparticleEntity instanceof Biopolymer) {
			biopolymer = (Biopolymer) nanoparticleEntity;
			domainEntity = biopolymer;
			className = ClassUtils
					.getShortClassName(Biopolymer.class.getName());
		} else if (nanoparticleEntity instanceof OtherNanoparticleEntity) {
			otherEntity = (OtherNanoparticleEntity) nanoparticleEntity;
			domainEntity = otherEntity;
			className = ClassUtils
					.getShortClassName(OtherNanoparticleEntity.class.getName());
		}
		for (ComposingElement composingElement : nanoparticleEntity
				.getComposingElementCollection()) {
			composingElements.add(new ComposingElementBean(composingElement));
		}
	}

	public int compareTo(NanoparticleEntityBean other) {
		return type.compareTo(other.getType());
	}

	public String getType() {
		return type;
	}

	public void setType(String entityType) {
		this.type = entityType;
	}

	public String getClassName() {
		return className;
	}

	public Dendrimer getDendrimer() {
		domainEntity = dendrimer;
		setSharedInfo();
		return dendrimer;
	}

	public Biopolymer getBiopolymer() {
		domainEntity = biopolymer;
		setSharedInfo();
		return biopolymer;
	}

	public CarbonNanotube getCarbonNanotube() {
		domainEntity = carbonNanotube;
		setSharedInfo();
		return carbonNanotube;
	}

	public List<ComposingElementBean> getComposingElements() {
		return composingElements;
	}

	public Emulsion getEmulsion() {
		domainEntity = emulsion;
		setSharedInfo();
		return emulsion;
	}

	public Set<LabFile> getFiles() {
		return files;
	}

	public Fullerene getFullerene() {
		domainEntity = fullerene;
		setSharedInfo();
		return fullerene;
	}

	public Liposome getLiposome() {
		domainEntity = liposome;
		setSharedInfo();
		return liposome;
	}

	public Polymer getPolymer() {
		domainEntity = polymer;
		setSharedInfo();
		return polymer;
	}

	public OtherNanoparticleEntity getOtherEntity() {
		domainEntity=otherEntity;
		setSharedInfo();
		return otherEntity;
	}

	public void setClassName(String entityClassName) {
		this.className = entityClassName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NanoparticleEntity getDomainEntity() {
		return domainEntity;
	}

	public void setSharedInfo() {
		domainEntity.setDescription(description);
		for (ComposingElementBean composingElementBean : composingElements) {
			ComposingElement domainComposingElement = composingElementBean
					.getDomainComposingElement();
			domainComposingElement.setNanoparticleEntity(domainEntity);
			domainComposingElements.add(domainComposingElement);
		}
		domainEntity.setComposingElementCollection(domainComposingElements);
		domainEntity.setLabFileCollection(files);
	}
}
