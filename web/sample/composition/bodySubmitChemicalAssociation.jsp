<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="StyleSheet" type="text/css" href="css/promptBox.css">
<script type="text/javascript" src="javascript/addDropDownOptions.js"></script>
<script type="text/javascript" src="javascript/CompositionManager.js"></script>
<script type='text/javascript'
	src='/caNanoLab/dwr/interface/CompositionManager.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<c:choose>
	<c:when test="${compositionForm.map.assoc.type eq 'attachment'}">
		<c:set var="style" value="display:inline" />
	</c:when>
	<c:otherwise>
		<c:set var="style" value="display:none" />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${! empty ceListA }">
		<c:set var="ceStyleA" value="display:inline" />
	</c:when>
	<c:otherwise>
		<c:set var="ceStyleA" value="display:none" />

	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${! empty ceListB }">
		<c:set var="ceStyleB" value="display:inline" />
	</c:when>
	<c:otherwise>
		<c:set var="ceStyleB" value="display:none" />
	</c:otherwise>
</c:choose>

<table width="100%" align="center">
	<tr>
		<td>
			<h4>
				${sampleName} Sample Composition - Chemical Association
				<c:if
					test="${!empty compositionForm.map.assoc.domainAssociation.id}">
						- ${compositionForm.map.assoc.type}
				</c:if>
			</h4>
		</td>
		<td align="right" width="20%">
			<jsp:include page="/helpGlossary.jsp">
				<jsp:param name="topic" value="nano_entity_help" />
				<jsp:param name="glossaryTopic" value="glossary_help" />
			</jsp:include>
		</td>
	</tr>
</table>
<html:form action="/chemicalAssociation" enctype="multipart/form-data">
	<jsp:include page="/bodyMessage.jsp?bundle=particle" />
	<table width="100%" align="center" class="submissionView">
		<tr>
			<th colspan="4">
				Summary
			</th>
		</tr>
		<c:if test="${empty compositionForm.map.assoc.domainAssociation.id}">
			<tr>
				<td class="cellLabel">
					Association Type*
				</td>
				<td>
					<div id="assocTypePrompt">
						<html:select styleId="assoType" property="assoc.type"
							onchange="javascript:callPrompt('Association Type', 'assoType', 'assocTypePrompt');
											displayBondType();">
							<option value=""></option>
							<html:options name="chemicalAssociationTypes" />
							<option value="other">
								[Other]
							</option>
						</html:select>
					</div>
				</td>
			</tr>
		</c:if>
		<tr>
			<c:set var="bondTypeStyle" value="display:none" />
			<c:if test="${compositionForm.map.assoc.type eq 'attachment' }">
				<c:set var="bondTypeStyle" value="display:block" />
			</c:if>
			<td class="cellLabel">
				<span id="bondTypeLabel" style="">Bond Type*</span>
			</td>
			<td>
				<div id="bondTypePrompt" style="">
					<html:select styleId="bondType"
						property="assoc.attachment.bondType"
						onchange="javascript:callPrompt('Bond Type', 'bondType', 'bondTypePrompt');">
						<option value=""></option>
						<html:options name="bondTypes" />
						<option value="other">
							[Other]
						</option>
					</html:select>
				</div>
			</td>
		</tr>
		<tr>
			<td class="cellLabel">
				Description
			</td>
			<td>
				<html:textarea property="assoc.description" rows="3" cols="60" />
			</td>
		</tr>
	</table>
	<br>
	<table width="100%" align="center" class="submissionView">
		<tr>
			<th colspan="3">
				Associated Elements Information
			</th>
		</tr>
		<tr>
			<td>
				<c:set var="elementNumber" value="A" />
				<c:set var="entitySelectStyle" value="display:none" />
				<c:set var="composingElementSelectStyle" value="display:none" />
				<c:if test="${!empty entityListA }">
					<c:set var="entityList" value="${entityListA}" />
				</c:if>
				<c:if test="${!empty ceListA }">
					<c:set var="ceList" value="${ceListA}" />
				</c:if>
				<c:if
					test="${! empty compositionForm.map.assoc.associatedElementA.entityId}">
					<c:set var="entitySelectStyle" value="display:block" />
				</c:if>
				<c:if
					test="${compositionForm.map.assoc.associatedElementA.compositionType eq 'Nanomaterial Entity'}">
					<c:set var="composingElementSelectStyle" value="display:block" />
				</c:if>
				<%@include file="bodySubmitAssociatedElement.jsp"%>
			</td>
			<td>
				<img src="images/arrow_left_right_gray.gif" id="assocImg" />
				<br>
				associated with
			</td>
			<td>
				<c:set var="elementNumber" value="B" />
				<c:set var="entitySelectStyle" value="display:none" />
				<c:set var="composingElementSelectStyle" value="display:none" />
				<c:if test="${!empty entityListB }">
					<c:set var="entityList" value="${entityListB}" />
				</c:if>
				<c:if test="${!empty ceListB }">
					<c:set var="ceList" value="${ceListB}" />
				</c:if>
				<c:if
					test="${! empty compositionForm.map.assoc.associatedElementA.entityId}">
					<c:set var="entitySelectStyle" value="display:block" />
				</c:if>
				<c:if
					test="${compositionForm.map.assoc.associatedElementB.compositionType eq 'Nanomaterial Entity'}">
					<c:set var="composingElementSelectStyle" value="display:block" />
				</c:if>
				<%@include file="bodySubmitAssociatedElement.jsp"%>
			</td>
		</tr>
	</table>
	<br>
	<%--Chemical Association File Information --%>
	<c:set var="fileParent" value="assoc" />
	<a name="file">
		<table width="100%" align="center" class="submissionView">
			<tr>
				<th colspan="2">
					Chemical Association File
				</th>
			</tr>
			<tr>
				<td class="cellLabel" colspan="2">
					File&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:clearFile('${fileParent }'); show('newFile');"><img
							align="top" src="images/btn_add.gif" border="0" /> </a>
				</td>
			</tr>
			<tr>
				<td>
					<c:if test="${! empty compositionForm.map.assoc.files }">
						<c:set var="files" value="${compositionForm.map.assoc.files}" />
						<c:set var="edit" value="true" />
						<%@ include file="bodyFileView.jsp"%>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;&nbsp;
					<div style="display: none" id="newFile">
						<c:set var="fileForm" value="compositionForm" />
						<c:set var="theFile" value="${compositionForm.map.assoc.theFile}" />
						<%@include file="../bodySubmitFile.jsp"%>
						&nbsp;
					</div>
				</td>
			</tr>
		</table> </a>
	<br>
	<c:set var="type" value="chemical association" />
	<c:set var="actionName" value="chemicalAssociation" />
	<c:set var="formName" value="compositionForm" />
	<c:set var="dataId"
		value="${compositionForm.map.assoc.domainAssociation.id}" />
	<%@include file="../bodySubmitButtons.jsp"%>

</html:form>
