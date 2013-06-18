/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cananolab/LICENSE.txt for details.
 */

package gov.nih.nci.calab.dto.remote;

/**
 * DTO object representing a grid node with a host name and a URL address.
 * 
 * @author pansu
 * 
 */
public class GridNodeBean {
	private String hostName;

	private String address;

	private String appServiceURL;

	public GridNodeBean(String hostName, String address) {

		this.hostName = hostName;
		this.address = address;
	}

	public GridNodeBean(String hostName, String address, String appServiceURL) {
		this(hostName, address);
		this.appServiceURL = appServiceURL;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAppServiceURL() {
		return this.appServiceURL;
	}

	public void setAppServiceURL(String appServiceURL) {
		this.appServiceURL = appServiceURL;
	}

}
