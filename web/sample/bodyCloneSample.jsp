<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type='text/javascript' src='javascript/addDropDownOptions.js'></script>
<script type='text/javascript' src='/caNanoLab/dwr/engine.js'></script>
<script type='text/javascript' src='/caNanoLab/dwr/util.js'></script>
<script type="text/javascript" src="javascript/SampleManager.js"></script>
<script type="text/javascript"
	src="/caNanoLab/dwr/interface/SampleManager.js"></script>
<script type='text/javascript' src='/caNanoLab/dwr/engine.js'></script>
<script type='text/javascript' src='/caNanoLab/dwr/util.js'></script>
<link rel="StyleSheet" type="text/css" href="css/promptBox.css">
<c:set var="title" value="Clone" />
<jsp:include page="/bodyTitle.jsp">
	<jsp:param name="pageTitle" value="${title} Sample" />
	<jsp:param name="topic" value="submit_sample_help" />
	<jsp:param name="glossaryTopic" value="glossary_help" />
</jsp:include>
<html:form action="/sample"
	onsubmit="return validateSavingTheData('newPointOfContact', 'point of contact');">
	<jsp:include page="/bodyMessage.jsp?bundle=sample" />
	<table width="100%" align="center" class="submissionView">
		<tr>
			<td>
				<table>
					<tr>
						<td class="cellLabel">
							Clone from an Existing Sample *
						</td>
						<td width="100">
							<html:text property="sampleBean.cloningSampleName" size="50" styleId="cloningSampleName"/>
						</td>
						<td colspan="2">
							<a href="#" onclick="showMatchedSampleNameDropdown()"><img src="images/icon_browse.jpg" align="middle"
								alt="search existing samples" border="0"/></a>
						</td>						
					</tr>
					<tr>
						<td class="cellLabel" valign="top">
							New Sample Name *
						</td>
						<td colspan="2" valign="top">
							<html:text property="sampleBean.domain.name" size="50" />
						</td>
					</tr>
				</table>
			</td>
			<td width="40%">
				<table>
					<tr>
						<td valign="top"><img src="images/ajax-loader.gif" border="0" class="counts"
								id="loaderImg" style="display: none">							
							<html:select property="sampleBean.cloningSampleName"
								styleId="matchedSampleSelect" size="5" style="display:none" onclick="updateCloningSample()">							
							</html:select>							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<table width="100%" border="0" align="center" cellpadding="3"
		cellspacing="0">
		<tr>
			<td width="30%">
				<table width="498" height="32" border="0" align="right"
					cellpadding="4" cellspacing="0">
					<tr>
						<td width="490" height="32">
							<div align="right">
								<div align="right">
									<c:set var="origUrl"
										value="sample.do?page=0&dispatch=${param.dispatch}" />									
									<input type="reset" value="Reset"
										onclick="javascript:window.location.href='${origUrl}'">
									<%--
									<input type="hidden" name="dispatch" value="clone">
									<input type="hidden" name="page" value="1">
									<html:submit value="Clone" onclick="show('waitMessage')" />
									--%>
									<input type="button" value="Clone" onclick="submitAction(sampleForm, 'sample', 'clone', 1);show('waitMessage')">
								</div>
							</div>
						</td>
					</tr>
				</table>
				<div align="right"></div>
			</td>
		</tr>
	</table>
	<br />
	<span id="waitMessage" style="display: none" class="welcomeContent"><img
			src="images/ajax-loader.gif" border="0" class="counts"> Please
		wait. Cloning might take a while to finish if the original sample is fully curated.</span>
</html:form>

