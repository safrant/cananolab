/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cananolab/LICENSE.txt for details.
 */

package gov.nih.nci.calab.dto.sample;

import org.displaytag.decorator.TableDecorator;

/**
 * This decorator is used to for decorate different properties of a sample
 * container to be shown properly in the view page using display tag lib.
 * 
 * @author pansu
 * 
 */
public class ContainerDecorator extends TableDecorator {
	public String getContainerId() throws Exception {
		ContainerBean container = (ContainerBean) getCurrentRowObject();
		String checked = "";
		if (getPageContext().getAttribute("containerId") != null
				&& ((String) getPageContext().getAttribute("containerId"))
						.equals(container.getContainerId())) {
			checked = "checked";
		} else if (getListIndex() == 0) {
			checked = "checked";
		} else {
			checked = "";
		}
		String containerIdstr = "<input type='radio' name='containerId' value='"
				+ container.getContainerId() + "' " + checked + ">";

		return containerIdstr;
	}
}
