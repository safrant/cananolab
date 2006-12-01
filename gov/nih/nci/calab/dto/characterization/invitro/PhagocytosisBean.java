package gov.nih.nci.calab.dto.characterization.invitro;

import gov.nih.nci.calab.domain.nano.characterization.Characterization;
import gov.nih.nci.calab.domain.nano.characterization.invitro.Phagocytosis;

import gov.nih.nci.calab.dto.characterization.*;

import java.util.List;


/**
 * This class represents the phagocytosis characterization information to be shown in
 * the view page.
 * 
 * @author beasleyj
 * 
 */
public class PhagocytosisBean extends CharacterizationBean {
	public PhagocytosisBean() {
		super();
	}
	
	public PhagocytosisBean(Characterization aChar) {
		super(aChar);
	}
	
	public void setDerivedBioAssayDataList(
			List<DerivedBioAssayDataBean> derivedBioAssayData) {
		super.setDerivedBioAssayDataList(derivedBioAssayData);

		for (DerivedBioAssayDataBean table : getDerivedBioAssayDataList()) {
			for (DatumBean datum : table.getDatumList()) {
				datum.setType("Fold Induction");
				datum.setValueUnit("Fold");
			}
		}
	}
	
	public Phagocytosis getDomainObj() {
		Phagocytosis phagocytosis = new Phagocytosis();
		super.updateDomainObj(phagocytosis);
		return phagocytosis;
	}
}
