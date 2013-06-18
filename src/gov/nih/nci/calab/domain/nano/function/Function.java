/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cananolab/LICENSE.txt for details.
 */

/**
 * 
 */
package gov.nih.nci.calab.domain.nano.function;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zengje
 * 
 */
public class Function implements java.io.Serializable {
	private static final long serialVersionUID = 1234567890L;

	private Long id;

	private String type;

	private String description;

	private String activationMethod;

	private String identificationName; // view title

	private Collection<Linkage> linkageCollection = new ArrayList<Linkage>();

	/**
	 * 
	 */
	public Function() {

	}

	public String getActivationMethod() {
		return this.activationMethod;
	}

	public void setActivationMethod(String activationMethod) {
		this.activationMethod = activationMethod;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Linkage> getLinkageCollection() {
		return this.linkageCollection;
	}

	public void setLinkageCollection(Collection<Linkage> linkageCollection) {
		this.linkageCollection = linkageCollection;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdentificationName() {
		return this.identificationName;
	}

	public void setIdentificationName(String identificationName) {
		this.identificationName = identificationName;
	}

	public boolean equals(Object obj) {
		boolean eq = false;
		if (obj instanceof Function) {
			Function function = (Function) obj;
			Long thisId = getId();
			if (thisId != null && thisId.equals(function.getId())) {
				eq = true;
			}
		}
		return eq;
	}

	public int hashCode() {
		int h = 0;

		if (getId() != null) {
			h += getId().hashCode();
		}

		return h;
	}

}
