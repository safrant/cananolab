/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cananolab/LICENSE.txt for details.
 */

package gov.nih.nci.calab.dto.particle;

import gov.nih.nci.calab.dto.common.SortableName;
import gov.nih.nci.calab.service.util.StringUtils;

import java.net.URLEncoder;

import org.displaytag.decorator.TableDecorator;

/**
 * This decorator is used to for decorate different properties of a nanoparticle
 * to be shown properly in the view page using display tag lib.
 * 
 * @author pansu
 * 
 */
public class NanoparticleDecorator extends TableDecorator {
	public SortableName getEditParticleURL() throws Exception {
		ParticleBean particle = (ParticleBean) getCurrentRowObject();
		String particleType = URLEncoder.encode(particle.getSampleType(),
				"UTF-8");
		String particleName = URLEncoder.encode(particle.getSampleName(),
				"UTF-8");
		String particleSource = URLEncoder.encode(particle.getSampleSource(),
				"UTF-8");
		String editParticleURL = "nanoparticleGeneralInfo.do?dispatch=setupUpdate&particleType="
				+ particleType
				+ "&particleName="
				+ particleName
				+ "&particleSource=" + particleSource;
		String link = "<a href=" + editParticleURL + ">"
				+ particle.getSampleName() + "</a>";

		SortableName sortableLink = new SortableName(particle.getSampleName(),
				link);
		return sortableLink;
	}

	public SortableName getViewParticleURL() throws Exception {
		ParticleBean particle = (ParticleBean) getCurrentRowObject();
		String particleType = URLEncoder.encode(particle.getSampleType(),
				"UTF-8");
		String particleName = URLEncoder.encode(particle.getSampleName(),
				"UTF-8");
		String particleSource = URLEncoder.encode(particle.getSampleSource(),
				"UTF-8");
		String viewParticleURL = "nanoparticleGeneralInfo.do?dispatch=setupView&particleType="
				+ particleType
				+ "&particleName="
				+ particleName
				+ "&particleSource=" + particleSource;
		;
		String link = "<a href=" + viewParticleURL + ">"
				+ particle.getSampleName() + "</a>";
		SortableName sortableLink = new SortableName(particle.getSampleName(),
				link);
		return sortableLink;
	}

	public SortableName getRemoteViewURL() throws Exception {
		ParticleBean particle = (ParticleBean) getCurrentRowObject();
		String particleType = URLEncoder.encode(particle.getSampleType(),
				"UTF-8");
		String particleName = URLEncoder.encode(particle.getSampleName(),
				"UTF-8");
		String remoteViewURL = "remoteNanoparticleGeneralInfo.do?dispatch=view&particleType="
				+ particleType
				+ "&particleName="
				+ particleName
				+ "&gridNodeHost=" + particle.getGridNode();
		String link = "<a href=" + remoteViewURL + ">"
				+ particle.getSampleName() + "</a>";
		SortableName sortableLink = new SortableName(particle.getSampleName(),
				link);
		return sortableLink;
	}

	public String getKeywordsStr() {
		ParticleBean particle = (ParticleBean) getCurrentRowObject();
		return StringUtils.join(particle.getKeywords(), "<br>");
	}

	public String getFunctionTypesStr() {
		ParticleBean particle = (ParticleBean) getCurrentRowObject();
		return StringUtils.join(particle.getFunctionTypes(), "<br>");
	}

	public String getCharacterizationsStr() {
		ParticleBean particle = (ParticleBean) getCurrentRowObject();
		return StringUtils.join(particle.getCharacterizations(), "<br>");
	}
}
