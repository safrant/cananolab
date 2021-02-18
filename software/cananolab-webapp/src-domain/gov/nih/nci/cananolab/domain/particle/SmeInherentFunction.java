package gov.nih.nci.cananolab.domain.particle;
// Generated Apr 3, 2019, 8:32:55 PM by Hibernate Tools 5.4.2.Final

/**
 * SmeInherentFunction generated by hbm2java
 */
public class SmeInherentFunction implements java.io.Serializable {
	private static final long serialVersionUID = 1234567890L;
	private Long smeInherentFunctionPkId;
	private SynthesisMaterialElement synthesisMaterialElement;
	private String type;
	private String description;

	public SmeInherentFunction() {
	}

	public SmeInherentFunction(Long smeInherentFunctionPkId, SynthesisMaterialElement synthesisMaterialElement) {
		this.smeInherentFunctionPkId = smeInherentFunctionPkId;
		this.synthesisMaterialElement = synthesisMaterialElement;
	}

	public SmeInherentFunction(Long smeInherentFunctionPkId, SynthesisMaterialElement synthesisMaterialElement,
			String type, String description) {
		this.smeInherentFunctionPkId = smeInherentFunctionPkId;
		this.synthesisMaterialElement = synthesisMaterialElement;
		this.type = type;
		this.description = description;
	}

	public Long getId() {
		return this.smeInherentFunctionPkId;
	}

	public void setId(Long smeInherentFunctionPkId) {
		this.smeInherentFunctionPkId = smeInherentFunctionPkId;
	}

	public SynthesisMaterialElement getSynthesisMaterialElement() {
		return this.synthesisMaterialElement;
	}

	public void setSynthesisMaterialElement(SynthesisMaterialElement synthesisMaterialElement) {
		this.synthesisMaterialElement = synthesisMaterialElement;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Compares <code>obj</code> to it self and returns true if they both are same
	 *
	 * @param obj
	 **/
	public boolean equals(Object obj) {
		if (obj instanceof SmeInherentFunction) {
			SmeInherentFunction c = (SmeInherentFunction) obj;
			return getId() != null && getId().equals(c.getId());
		}
		return false;
	}

	/**
	 * Returns hash code for the primary key of the object
	 **/
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		}
		return 0;
	}
}