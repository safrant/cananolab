<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="StyleSheet" type="text/css" href="css/promptBox.css">
<script type="text/javascript" src="javascript/addDropDownOptions.js"></script>
<script type="text/javascript" src="javascript/CompositionManager.js"></script>
<script type='text/javascript'
	src='/caNanoLab/dwr/interface/CompositionManager.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<c:set var="fileParent" value="comp" />
<c:set var="theFile" value="${compositionForm.map.comp.theFile}" />
<html:form action="/compositionFile" enctype="multipart/form-data">
	<table width="100%" align="center">
		<tr>
			<td>
				<h4>
					${sampleName} Sample Composition - Composition File
				</h4>
			</td>
			<td align="right" width="15%">
				<jsp:include page="/helpGlossary.jsp">
					<jsp:param name="topic" value="function_entity_help" />
					<jsp:param name="glossaryTopic" value="glossary_help" />
				</jsp:include>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="/bodyMessage.jsp?bundle=particle" />
			</td>
		</tr>
	</table>
	<c:set var="fileForm" value="compositionForm" />
	<c:set var="theFile" value="${compositionForm.map.comp.theFile}" />
	<c:set var="actionName" value="compositionFile" />
	<%@include file="../bodySubmitFile.jsp"%>
	<br>
	<c:set var="type" value="composition file" />
	<c:set var="actionName" value="compositionFile" />
	<c:set var="formName" value="compositionForm" />
	<c:set var="dataId"
		value="${compositionForm.map.comp.theFile.domainFile.id}" />
	<%@include file="../bodySubmitButtons.jsp"%>
</html:form>