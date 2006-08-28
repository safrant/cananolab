package gov.nih.nci.calab.dto.characterization.composition;

import java.util.ArrayList;
import java.util.List;

public class DendrimerBean extends CompositionBean {
	private String branch;

	private String repeatUnit;

	private String generation;

	private String numberOfSurfaceGroups;

	private String molecularFormula;

	private List<SurfaceGroupBean> surfaceGroups;
	
	private ComposingElementBean core;

	public DendrimerBean() {
		super();
		surfaceGroups = new ArrayList<SurfaceGroupBean>();		
		List composingElements = getComposingElements();
		core = new ComposingElementBean();
		core.setElementType("core");
		composingElements.add(core);
		setComposingElements(composingElements);
		setNumberOfElements("1");
	}

	public String getBranch() {
		return branch;
	}

	public String getGeneration() {
		return generation;
	}

	public String getRepeatUnit() {
		return repeatUnit;
	}

	public String getNumberOfSurfaceGroups() {
		return numberOfSurfaceGroups;
	}

	public String getMolecularFormula() {
		return molecularFormula;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}

	public void setNumberOfSurfaceGroups(String numberOfSurfaceGroups) {
		this.numberOfSurfaceGroups = numberOfSurfaceGroups;
	}

	public void setRepeatUnit(String repeatUnit) {
		this.repeatUnit = repeatUnit;
	}

	public List<SurfaceGroupBean> getSurfaceGroups() {
		return surfaceGroups;
	}

	public void setSurfaceGroups(List<SurfaceGroupBean> surfaceGroups) {
		this.surfaceGroups = surfaceGroups;
	}

	public SurfaceGroupBean getSurfaceGroup(int ind) {
		return surfaceGroups.get(ind);
	}

	public void setSurfaceGroup(int ind, SurfaceGroupBean surfaceGroup) {
		surfaceGroups.set(ind, surfaceGroup);
	}

	public ComposingElementBean getCore() {
		return core;
	}

	public void setCore(ComposingElementBean core) {
		this.core = core;
	}
}
