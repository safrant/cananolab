/**
 * 
 */
package gov.nih.nci.calab.dto.workflow;

/**
 * @author zengje
 *
 */
public class OutputFileBean {

	private String id;
	private String path;
	
	/**
	 * 
	 */
	public OutputFileBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OutputFileBean(String id, String path) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
