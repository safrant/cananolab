package gov.nih.nci.cananolab.restful.view;

import gov.nih.nci.cananolab.dto.common.AccessibilityBean;

import java.util.Date;
import java.util.List;

public class SimpleWorkspaceItem {
	
	List<String> actions;
	String name;
	long id;
	String submisstionStatus;
	Date createdDate;
	
	String comments;
	
	String pubMedId;
	
	//List<AccessibilityBean> groupAccesses;
	//List<AccessibilityBean> userAccesses; 
	
	String access;

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubmisstionStatus() {
		return submisstionStatus;
	}

	public void setSubmisstionStatus(String submisstionStatus) {
		this.submisstionStatus = submisstionStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPubMedId() {
		return pubMedId;
	}

	public void setPubMedId(String pubMedId) {
		this.pubMedId = pubMedId;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

}