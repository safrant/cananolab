package gov.nih.nci.cananolab.dto.particle;

import gov.nih.nci.cananolab.domain.common.Keyword;
import gov.nih.nci.cananolab.domain.common.PointOfContact;
import gov.nih.nci.cananolab.domain.particle.Sample;
import gov.nih.nci.cananolab.dto.common.PointOfContactBean;
import gov.nih.nci.cananolab.util.Comparators;
import gov.nih.nci.cananolab.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class represents shared properties of samples to be shown in the view
 * pages.
 *
 * @author pansu
 *
 */
public class SampleBean {
	private String keywordsStr;

	private String[] visibilityGroups = new String[0];

	private Sample domain = new Sample();

	private SortedSet<String> keywordSet = new TreeSet<String>();

	private String location; // e.g. local, caNanoLab-WashU, etc

	private String[] nanomaterialEntityClassNames = new String[0];

	private String[] functionalizingEntityClassNames = new String[0];

	private String[] functionClassNames = new String[0];

	private String[] characterizationClassNames = new String[0];

	private PointOfContactBean primaryPOCBean = new PointOfContactBean();

	private List<PointOfContactBean> otherPOCBeans = new ArrayList<PointOfContactBean>();

	private Boolean hasComposition = false;

	private Boolean hasCharacterizations = false;

	private Boolean hasPublications = false;

	private PointOfContactBean thePOC = new PointOfContactBean();

	public SampleBean() {
	}

	public SampleBean(Sample sample) {
		this.domain = sample;

		if (sample.getKeywordCollection() != null) {
			for (Keyword keyword : sample.getKeywordCollection()) {
				keywordSet.add(keyword.getName());
			}
		}
		keywordsStr = StringUtils.join(keywordSet, "\r\n");
		if (domain != null) {
			PointOfContact primaryPOC = domain.getPrimaryPointOfContact();
			primaryPOCBean = new PointOfContactBean(primaryPOC);
			for (PointOfContact poc : domain.getOtherPointOfContactCollection()) {
				PointOfContactBean pocBean = new PointOfContactBean(poc);
				pocBean.setPrimaryStatus(false);
				otherPOCBeans.add(pocBean);
			}
			Collections.sort(otherPOCBeans,
					new Comparators.PointOfContactBeanNameOrgComparator());
			thePOC = primaryPOCBean;
		}
		if (sample.getSampleComposition() != null
				&& sample.getSampleComposition().getId() != null) {
			hasComposition = true;
		}
		if (sample.getCharacterizationCollection() != null
				&& !sample.getCharacterizationCollection().isEmpty()) {
			hasCharacterizations = true;
		}
		if (sample.getPublicationCollection() != null
				&& !sample.getPublicationCollection().isEmpty()) {
			hasPublications = true;
		}
	}

	public String[] getVisibilityGroups() {
		return this.visibilityGroups;
	}

	public void setVisibilityGroups(String[] visibilityGroups) {
		this.visibilityGroups = visibilityGroups;
	}

	public String getKeywordsStr() {
		return this.keywordsStr;
	}

	public Sample getDomain() {
		return domain;
	}

	public void setupDomain(String createdBy) {
		// always update createdBy and createdDate
		if (domain.getId() == null) {
			domain.setCreatedBy(createdBy);
			domain.setCreatedDate(new Date());
		}
		if (domain.getKeywordCollection() != null) {
			domain.getKeywordCollection().clear();
		} else {
			domain.setKeywordCollection(new HashSet<Keyword>());
		}
		if (keywordsStr.length() > 0) {
			String[] strs = keywordsStr.split("\r\n");
			for (String str : strs) {
				// change to upper case
				Keyword keyword = new Keyword();
				keyword.setName(str.toUpperCase());
				domain.getKeywordCollection().add(keyword);
			}
		}
		if (primaryPOCBean != null) {
			domain.setPrimaryPointOfContact(primaryPOCBean.getDomain());
		} else {
			domain.setPrimaryPointOfContact(null);
		}
		if (domain.getOtherPointOfContactCollection() != null) {
			domain.getOtherPointOfContactCollection().clear();
		} else {
			domain
					.setOtherPointOfContactCollection(new HashSet<PointOfContact>());
		}
		for (PointOfContactBean pocBean : otherPOCBeans) {
			domain.getOtherPointOfContactCollection().add(pocBean.getDomain());
		}
	}

	public void setKeywordsStr(String keywordsStr) {
		this.keywordsStr = keywordsStr;
	}

	public SortedSet<String> getKeywordSet() {
		return keywordSet;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDomain(Sample domainSample) {
		this.domain = domainSample;
	}

	public String[] getNanomaterialEntityClassNames() {
		return nanomaterialEntityClassNames;
	}

	public void setNanomaterialEntityClassNames(
			String[] nanomaterialEntityClassNames) {
		this.nanomaterialEntityClassNames = nanomaterialEntityClassNames;
	}

	public String[] getFunctionalizingEntityClassNames() {
		return functionalizingEntityClassNames;
	}

	public void setFunctionalizingEntityClassNames(
			String[] functionalizingEntityClassNames) {
		this.functionalizingEntityClassNames = functionalizingEntityClassNames;
	}

	public String[] getFunctionClassNames() {
		return functionClassNames;
	}

	public void setFunctionClassNames(String[] functionClassNames) {
		this.functionClassNames = functionClassNames;
	}

	public String[] getCharacterizationClassNames() {
		return characterizationClassNames;
	}

	public void setCharacterizationClassNames(
			String[] characterizationClassNames) {
		this.characterizationClassNames = characterizationClassNames;
	}

	public PointOfContactBean getPrimaryPOCBean() {
		return primaryPOCBean;
	}

	public void setPrimaryPOCBean(PointOfContactBean primaryPOCBean) {
		this.primaryPOCBean = primaryPOCBean;
	}

	public List<PointOfContactBean> getOtherPOCBeans() {
		return otherPOCBeans;
	}

	public void setOtherPOCBeans(List<PointOfContactBean> otherPOCBeans) {
		this.otherPOCBeans = otherPOCBeans;
	}

	public Boolean getHasComposition() {
		return hasComposition;
	}

	public void setHasComposition(Boolean hasComposition) {
		this.hasComposition = hasComposition;
	}

	public Boolean getHasCharacterizations() {
		return hasCharacterizations;
	}

	public void setHasCharacterizations(Boolean hasCharacterizations) {
		this.hasCharacterizations = hasCharacterizations;
	}

	public Boolean getHasPublications() {
		return hasPublications;
	}

	public void setHasPublications(Boolean hasPublications) {
		this.hasPublications = hasPublications;
	}

	public PointOfContactBean getThePOC() {
		return thePOC;
	}

	public void setThePOC(PointOfContactBean thePOC) {
		this.thePOC = thePOC;
	}

	public void addPointOfContact(PointOfContactBean poc) {
		if (poc.getPrimaryStatus()) {
			primaryPOCBean = poc;
		} else {
			// if an old one exists, remove it first
			int index = otherPOCBeans.indexOf(poc);
			if (index != -1) {
				otherPOCBeans.remove(poc);
				// retain the original order
				otherPOCBeans.add(index, poc);
			} else {
				otherPOCBeans.add(poc);
			}
		}
	}

	public void removePointOfContact(PointOfContactBean poc) {
		// ignore if it's primary POC, can't delete primary POC
		if (!poc.getPrimaryStatus()) {
			otherPOCBeans.remove(poc);
		}
	}
}
