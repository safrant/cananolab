<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type='text/javascript' src='javascript/addDropDownOptions.js'></script>
<script type='text/javascript' src='/caNanoLab/dwr/engine.js'></script>
<script type='text/javascript' src='/caNanoLab/dwr/util.js'></script>
<script type='text/javascript' src='javascript/SampleManager.js'></script>
<script type="text/javascript"
	src="/caNanoLab/dwr/interface/SampleManager.js"></script>
<link rel="StyleSheet" type="text/css" href="css/promptBox.css">
<c:set var="title" value="Submit" />
<c:if test="${!empty updateStudy}">
	<c:set var="title" value="Update" />
</c:if>
<jsp:include page="/bodyTitle.jsp">
	<jsp:param name="pageTitle" value="${title} Study" />
	<jsp:param name="topic" value="submit_sample_help" />
	<jsp:param name="glossaryTopic" value="glossary_help" />
</jsp:include>

<html:form action="/study"
	onsubmit="return validateSavingTheData('newPointOfContact', 'point of contact');">
	<jsp:include page="/bodyMessage.jsp?bundle=study" />
	<table width="100%" align="center" class="submissionView" border="0">
		<tr>
			<td class="cellLabel" width="15%">
				Study Name *
			</td>
			<td colspan="3">
				<html:text property="studyBean.name" size="50" value="MIT_MGH-KKellyIB"/>
				<%--
				<c:if test="${!empty sampleForm.map.sampleBean.domain.id}">
					<html:hidden styleId="sampleId" property="sampleBean.domain.id"
						value="${sampleForm.map.sampleBean.domain.id}" />
				</c:if>
				--%>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Study Title *
			</td>
			<td colspan="3">
				<html:text property="studyBean.title" size="100" value="Unbiased discovery of in vivo imaging probes through in vitro profiling of nanoparticle libraries"/>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Study Type
			</td>
			<td width="20%">
				<html:select property="studyBean.type">
					<option value="" />
						<option value="Reproductive">
							reproductive
						</option>
						<option value="Continuous Breeding">
							continuous breeding
						</option>
						<option value="Developmental">
							developmental
						</option>
						<option value="Cancer Bioassay" selected>
							cancer bioassay
						</option>
						<option value="other">
							[other]
						</option>
				</html:select>
			</td>
			<td class="cellLabel" width="15%">
				Is Animal Study?
			</td>
			<td>
				<input type="checkbox">
			</td>
		</tr>

		<tr>
			<td class="cellLabel">
				Study Design Types
			</td>
			<td>
				<html:textarea property="studyBean.designTypes" cols="30" rows="2" value="parallel group"/>
				<br>
				<em>one type per line</em>
			</td>
			<td colspan="2">
				<table class="invisibleTable">
					<tr>
						<td>
							<a href="#sampleNameField" onclick="()"><img
									src="images/icon_browse.jpg" align="middle"
									alt="search existing design types" border="0" /> </a>
						</td>
						<td>
							<img src="images/ajax-loader.gif" border="0" class="counts"
								id="loaderImg" style="display: none">
						</td>
						<td>
							<select multiple="true" size="2" style="display: none">
								<option>
									parallel group
								</option>
								<option>
									crossover
								</option>
							</select>
						</td>
						<td>
							<a href="#" id="selectMatchedSampleButton" style="display: none">select</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Disease Type
			</td>
			<td colspan="3">
				<html:select property="studyBean.diseaseType">
					<option value=""></option>
					<option value="" selected>
						cancer
					</option>
					<option value="other">
						[other]
					</option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Disease Names
			</td>
			<td>
				<html:textarea property="studyBean.diseaseNames" cols="30" rows="2" value="lung cancer" />
				<br>
				<em>one name per line</em>
			</td>
			<td colspan="3">
				<table class="invisibleTable">
					<tr>
						<td>
							<a href="#sampleNameField" onclick="()"><img
									src="images/icon_browse.jpg" align="middle"
									alt="search EVS disease names" border="0" /> </a><br><em>browse EVS</em>
						</td>
						<td>
							<img src="images/ajax-loader.gif" border="0" class="counts"
								id="loaderImg" style="display: none">
						</td>
						<td>
							<select size="3" style="display: none">
								<option>
									EVS choices
								</option>
								<option>
									EVS choices
								</option>
							</select>
						</td>
						<td>
							<a href="#" id="selectMatchedSampleButton" style="display: none">select</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Start Date
			</td>
			<td>
				<html:text property="studyBean.startDate" size="10" value="12-15-2010"/>
				<a href="javascript:cal1.popup();"><img
						src="images/calendar-icon.gif" width="22" height="18" border="0"
						alt="Click Here to Pick up the date"
						title="Click Here to Pick up the date" align="top"> </a>
			</td>
			<td class="cellLabel">
				End Date
			</td>
			<td>
				<html:text property="studyBean.endDate" size="10" value="12-15-2010"/>
				<a href="javascript:cal1.popup();"><img
						src="images/calendar-icon.gif" width="22" height="18" border="0"
						alt="Click Here to Pick up the date"
						title="Click Here to Pick up the date" align="top"> </a>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Public Release Date
			</td>
			<td>
				<html:text property="studyBean.publicReleaseDate" size="10" />
				<a href="javascript:cal1.popup();"><img
						src="images/calendar-icon.gif" width="22" height="18" border="0"
						alt="Click Here to Pick up the date"
						title="Click Here to Pick up the date" align="top"> </a>
			</td>
			<td class="cellLabel">
				Submission Date
			</td>
			<td>
				<html:text property="studyBean.submissionDate" size="10" />
				<a href="javascript:cal1.popup();"><img
						src="images/calendar-icon.gif" width="22" height="18" border="0"
						alt="Click Here to Pick up the date"
						title="Click Here to Pick up the date" align="top"> </a>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Study Description
			</td>
			<td colspan="3">
				<html:textarea property="studyBean.description" rows="6" cols="80" value="Unbiased discovery of in vivo imaging probes through in vitro profiling of nanoparticle libraries"/>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Study Outcome
			</td>
			<td colspan="3">
				<html:textarea property="studyBean.outcome" rows="6" cols="80" />
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Factor
			</td>
			<td colspan="3">
				<a href="#" onclick="javascript:openSubmissionForm('Factor');"
					id="addFactor" style="display: block"><img align="top"
						src="images/btn_add.gif" border="0" /> </a>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<div style="display: none" id="newFactor">
					<a name="submitFactor"><%@ include
							file="bodyStudySubmitFactor.jsp"%></a>
				</div>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Point of Contact
			</td>
			<td colspan="3">
				<a href="#"
					onclick="javascript:openSubmissionForm('PointOfContact');"
					id="addPointOfContact" style="display: block"><img align="top"
						src="images/btn_add.gif" border="0" /> </a>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<table class="editTableWithGrid" width="95%" align="center">
					<tr>
						<th>
							Primary Contact?
						</th>
						<th>
							Contact Person
						</th>
						<th>
							Organization
						</th>
						<th>
							Role
						</th>
						<th></th>
					</tr>

					<tr>
						<td>
							Yes
						</td>
						<td>
							Stanley Y Shaw
							<br>
							shaw.stanley@mgh.harvard.edu
						</td>
						<td>
							MIT_MGH
							<br>
							MA
						</td>
						<td>
							investigator
						</td>

						<td align="right">
							<a href="javascript:setThePointOfContact(15695892, true);">Edit</a>&nbsp;
						</td>

					</tr>



					<tr>
						<td>
							No
						</td>
						<td>
							Ralph Weissleder
							<br>
							weissleder@helix.mgh.harvard.edu
						</td>
						<td>
							MIT_MGH
							<br>
							MA
						</td>
						<td>
							investigator
						</td>

						<td align="right">
							<a href="javascript:setThePointOfContact(15695885, false);">Edit</a>&nbsp;
						</td>

					</tr>


				</table>


			</td>
		</tr>

		<tr>
			<td colspan="4">
				<div style="display: none" id="newPointOfContact">
					<a name="submitPointOfContact"><jsp:include
							page="bodyStudySubmitPointOfContact.jsp" /></a>
				</div>
			</td>
		</tr>
	</table>
	<br>
	<table width="100%" align="center" class="submissionView"
		id="accessBlock">
		<c:set var="groupAccesses"
			value="${studyForm.map.studyBean.groupAccesses}" />
		<c:set var="userAccesses"
			value="${studyForm.map.studyBean.userAccesses}" />
		<c:set var="accessParent" value="studyBean" />
		<c:set var="dataType" value="Study" />
		<c:set var="parentForm" value="studyForm" />
		<c:set var="parentAction" value="study" />
		<c:set var="parentPage" value="2" />
		<c:set var="isPublic" value="true" />
		<c:set var="isOwner" value="true" />
		<c:set var="ownerName" value="pansu" />
		<c:set var="newData" value="true" />
		<%@include file="../bodyManageAccessibility.jsp"%>
	</table>
	<br />
	<c:if test="${!empty updateStudy}">
		<c:set var="updateId" value="0" />
	</c:if>
	<%@include file="../bodySubmitButtons.jsp"%>
</html:form>