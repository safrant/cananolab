/**
 * 
 */
package gov.nih.nci.calab.dto.particle;

/**
 * @author Zeng
 *
 */
public class MetalParticleBean extends ParticleBean {
	private String core;

	private String shell;
	
	private String composition;

	public MetalParticleBean() {		
	}

	public String getCore() {
		return core;
	}

	public void setCore(String core) {
		this.core = core;
	}

	public String getShell() {
		return shell;
	}

	public void setShell(String shell) {
		this.shell = shell;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}
	
	
}
