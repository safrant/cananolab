package gov.nih.nci.calab.domain.nano.characterization;

import gov.nih.nci.calab.domain.Measurement;

public class TableDataCondition {

	private Long id;
	private String type;
	private Measurement value;
	
	public TableDataCondition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Measurement getValue() {
		return value;
	}

	public void setValue(Measurement value) {
		this.value = value;
	}

}
