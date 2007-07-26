package gov.nih.nci.calab.dto.common;

import gov.nih.nci.calab.domain.LabFile;
import gov.nih.nci.calab.service.util.StringUtils;

import java.util.Date;

import org.apache.struts.upload.FormFile;

/**
 * This class represents attributes of a lab file to be viewed in a view page.
 * 
 * @author pansu
 * 
 */
public class LabFileBean {
	private String title;

	private String description;

	private String comments;

	private String[] visibilityGroups = new String[0];

	private Date createdDate;

	private String createdBy;

	private String id;
	
	private String name;

	private String type;

	private String visibilityStr;

	private String gridNode;

	private String version;

	private String uri; //value saved in the db

	private byte[] fileContent;

	/*
	 * name to be displayed as a part of the drop-down list
	 */
	private String displayName;

	private String instanceType; // type of instance, protocol, output,
									// report, associatedFile, etc

	private String timeStampedName;
	
	private boolean hidden;
	
	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public LabFileBean() {
	}

	public LabFileBean(LabFile charFile) {
		this.id = charFile.getId().toString();
		this.name = charFile.getFilename();
		this.uri = charFile.getUri();
		this.title = charFile.getTitle();
		this.description = charFile.getDescription();
		this.createdBy = charFile.getCreatedBy();
		this.version = charFile.getVersion();
		this.createdDate = charFile.getCreatedDate();
		this.type = charFile.getType();
		this.fileContent=charFile.getContent();
	}

	public LabFileBean(LabFile charFile, String gridNodeHost) {
		this(charFile);
		this.gridNode = gridNodeHost;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getVisibilityGroups() {
		return visibilityGroups;
	}

	public void setVisibilityGroups(String[] visibilityGroups) {
		this.visibilityGroups = visibilityGroups;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public LabFile getDomainObject() {
		LabFile labfile = new LabFile();
		if (id != null && id.length() > 0) {
			labfile.setId(new Long(id));
		}
		labfile.setCreatedBy(createdBy);
		labfile.setCreatedDate(createdDate);
		labfile.setDescription(description);
		labfile.setComments(comments);
		labfile.setFilename(name);
		labfile.setUri(uri);		
		labfile.setTitle(title);
		labfile.setVersion(version);
		labfile.setType(type);
		labfile.setContent(fileContent);
		return labfile;
	}

	public String getDisplayName() {
		if (uri != null) {
			displayName = uri.replaceAll("/decompressedFiles", "");
		} else {
			displayName = "";
		}
		return displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVisibilityStr() {
		visibilityStr = StringUtils.join(visibilityGroups, "<br>");
		return visibilityStr;
	}

	public String getGridNode() {
		return gridNode;
	}

	public void setGridNode(String gridNode) {
		this.gridNode = gridNode;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setVisibilityStr(String visibilityStr) {
		this.visibilityStr = visibilityStr;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getTimeStampedName() {
		return timeStampedName;
	}

	public void setTimeStampedName(String timeStampedName) {
		this.timeStampedName = timeStampedName;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}
