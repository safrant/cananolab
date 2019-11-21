package gov.nih.nci.cananolab.dto.particle.synthesis;

import gov.nih.nci.cananolab.domain.particle.SfeInherentFunction;
import gov.nih.nci.cananolab.util.StringUtils;

public class SfeInherentFunctionBean {
    public String getType() {
        return domain.getType();
    }

    public void setType(String type) {
        //TODO write a proper transfer to domain
        this.type = type;
    }

    public String getDescription() {
        return domain.getDescription();
    }

    public void setDescription(String description) {
        //TODO write a proper transfer to domain
        this.description = description;
    }

    public SfeInherentFunction getDomain() {
        return domain;
    }

    public void setDomain(SfeInherentFunction domain) {
        this.domain = domain;
//TODO write a proper transfer to domain
    }

    //todo write
    private String type;
    private String description;
    private SfeInherentFunction domain;

    public SfeInherentFunctionBean(){

    }

    public SfeInherentFunctionBean(SfeInherentFunction domain){
        this.domain = domain;
    }

    public String getDisplayName() {
        StringBuffer buffer = new StringBuffer();
        if (!StringUtils.isEmpty(getType())) {
            buffer.append(getType());
        }
        if (!StringUtils.isEmpty(getDescription())) {
            buffer.append(": " + getDescription());
        }

        return buffer.toString();
    }
}
