package gov.nih.nci.calab.dto.function;
/**
 * 
 */

/**
 * @author zengje
 *
 */
public class AgentBean {
	
	private String id;
	private String type;
	private String name;
	private String description;
	// otherValue can be "compoundName" for SamllMolecule; "type" for Probe and ImageContrastAgent;
	//"speicies" for Antibody; or "sequence" for DNA and Peptide
	
	private String otherValue;
	/**
	 * 
	 */
	public AgentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOtherValue() {
		return otherValue;
	}
	public void setOtherValue(String otherValue) {
		this.otherValue = otherValue;
	}
}
