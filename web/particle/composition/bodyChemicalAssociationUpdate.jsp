<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html:form action="/chemicalAssociation">
	<table width="100%" align="center">
		<tr>
			<td>
				<h4>
					${particleName} Sample Composition - Chemical Association
				</h4>
			</td>
			<td align="right" width="15%">
				<a
					href="javascript:openHelpWindow('webHelp/index.html?single=true&amp;context=caNanoLab&amp;topic=composition_help')"
					class="helpText">Help</a>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h5 align="center">
					<%--					Entity#1:Dendrimer--%>
				</h5>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="/bodyMessage.jsp?bundle=particle" />
				<table width="100%" border="0" align="center" cellpadding="3"
					cellspacing="0" class="topBorderOnly" summary="" id="summary">
					<tr>
					<tr class="topBorder">
						<td class="formTitle" colspan="4">
							<div align="justify">
								Chemical Association Information
							</div>
						</td>
					</tr>
					<tr>
						<td class="leftLabel">
							<strong>Association Type</strong>
						</td>
						<td class="label">
							<html:select styleId="assoType"
								property="assoc.type"
								onchange="javascript:callPrompt('Association Type', 'assoType');">
								<option value=""></option>
								<html:options name="chemicalAssociationTypes"/>
								<option value="other">
									[Other]
								</option>
							</html:select>
						</td>
						<td class="label" valign="top">
							&nbsp;
							<Strong style="display:none">Bond Type</Strong>
						</td>
						<td class="rightLabel">
							&nbsp;
							<html:select styleId="bondType" style="display:none"
								property="assoc.attachment.bondType"
								onchange="javascript:callPrompt('Bond Type', 'bondType');">
								<option value=""></option>
<%--								<html:options name="bondTypes"/>--%>
								<option value="other">
									[Other]
								</option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td class="leftLabel" width="%33">
							<table width="100%" border="0" align="center" cellpadding="3"
								cellspacing="0" >
								<tr>
									<td class="completeLabelNoBottom">
										<strong>Element</strong>
									</td>
								</tr>
								<tr>
									<td class="completeLabelNoTopBottom">
										<html:select styleId="compositionTypeA"
											property="assoc.associatedElementA.compositionType"
											onchange="getAssociatedElementOptions('compositionTypeA', 'entityTypeA')">
											<option value=""></option>
											<option value="Nanoparticle Entity">
												Nanoparticle Entity
											</option>
											<option value="Functionalizing Entity">
												Functionalizing Entity
											</option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="completeLabelNoTopBottom">
										&nbsp;
										<html:select styleId="entityTypeA"
											property="assoc.associatedElementA.entityId">
											<option value="">
											</option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td class=completeLabelNoTop>
										&nbsp;
										<html:select styleId="compEleTypeA" style="display:none"
											property="assoc.associatedElementA.composingElement.type"
											onchange="javascript:callPrompt('Composing Element A', 'compEleTypeA');">
											<option value=""></option>
<%--											<html:options name="allComposingElementTypes" />--%>
											<option value="other">
												[Other]
											</option>
										</html:select>
									</td>
								</tr>
							</table>
							&nbsp;
						</td>
						<td class="label" colspan="2" align="center">
							<strong style="padding-left:20px">Associated With</strong>
						</td>
						<td class="rightLabel">
							<table width="100%" border="1" align="center" cellpadding="3"
								cellspacing="0" >
								<tr>
									<td class="completeLabelNoBottom">
										<strong>Element</strong>
									</td>
								</tr>
								<tr>
									<td class="completeLabelNoTopBottom">
										<html:select styleId="compositionTypeB"
											property="assoc.associatedElementB.compositionType"
											onchange="getAssociatedElementOptions('compositionTypeB', 'entityTypeB')">
											<option value="" />
											<option value="Nanoparticle Entity">
												Nanoparticle Entity
											</option>
											<option value="Functionalizing Entity">
												Functionalizing Entity
											</option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="completeLabelNoTopBottom">
										&nbsp;
										<html:select styleId="entityTypeB"
											property="assoc.associatedElementB.entityId">
											<option value="">
											</option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td class="completeLabelNoTop">
										&nbsp;
										<html:select styleId="compEleTypeB" style="display:none"
											property="assoc.associatedElementB.composingElement.type"
											onchange="javascript:callPrompt('Composing Element B', 'compEleTypeB');">
											<option value=""></option>
<%--											<html:options name="allComposingElementTypes" />--%>
											<option value="other">
												[Other]
											</option>
										</html:select>
									</td>
								</tr>
							</table>
							&nbsp;
						</td>
					</tr>

					<tr>
						<td class="leftLabel" valign="top">
							<strong>Association Description</strong>
						</td>
						<td class="rightLabel" colspan="3">
							<html:textarea property="assoc.description" rows="2"
								cols="60" />
						</td>
					</tr>
				</table>
				<%-- File Information --%>
				<br>
				<table class="topBorderOnly" cellspacing="0" cellpadding="3"
					width="100%" align="center" summary="" border="0">
					<tbody>
						<tr class="topBorder">
							<td class="formTitle" colspan="4">
								<div align="justify" id="peFileTitle">
									Chemical Association File Information
								</div>
							</td>
						</tr>
						<tr>
							<td class="completeLabel" colspan="4">
								<table border="0" width="100%">
									<tr>
										<td valign="bottom">
											<a href="#"
												onclick="javascript:addComponent(chemicalAssociationForm, 'chemicalAssociation', 'addFile'); return false;">
												<span class="addLink">Add File</span> </a>
										</td>
										<td id="fileTd">

											<logic:iterate name="chemicalAssociationForm"
												property="assoc.files" id="assocFile" indexId="fileInd">
												<jsp:include
													page="/particle/bodyLoadFileUpdate.jsp">
													<jsp:param name="fileInd" value="${fileInd}" />
													<jsp:param name="form" value="chemicalAssociationForm" />
													<jsp:param name="action" value="chemicalAssociation" />
													<jsp:param name="fileBean" value="assoc.files[${fileInd}]" />
													<jsp:param name="domainFile" value="assoc.files[${fileInd}].domainFile" />
													<jsp:param name="fileId" value="${chemicalAssociationForm.map.assoc.files[fileInd].domainFile.id}" />
													<jsp:param name="fileUri" value="${chemicalAssociationForm.map.assoc.files[fileInd].domainFile.uri}" />
													<jsp:param name="fileDisplayName" value="${chemicalAssociationForm.map.assoc.files[fileInd].displayName}" />
													<jsp:param name="fileHidden" value="${chemicalAssociationForm.map.assoc.files[fileInd].hidden}" />
													<jsp:param name="fileExternal" value="${chemicalAssociationForm.map.assoc.files[fileInd].external}" />
													<jsp:param name="fileImage" value="${chemicalAssociationForm.map.assoc.files[fileInd].image}" />
												</jsp:include>
												
												<br>
											</logic:iterate>
										</td>
									</tr>
								</table>
							</td>
						</tr>
				</table>

				<br>
				<table width="100%" border="0" align="center" cellpadding="3"
					cellspacing="0" class="topBorderOnly" summary="">
					<tr>
						<td width="30%">
							<span class="formMessage"> </span>
							<br>
							<c:choose>
								<c:when
									test="${param.dispatch eq 'setupUpdate' && canUserDelete eq 'true'}">
									<table height="32" border="0" align="left" cellpadding="4"
										cellspacing="0">
										<tr>
											<td height="32">
												<div align="left">
													<input type="button" value="Delete"
														onclick="confirmDeletion();">
												</div>
											</td>
										</tr>
									</table>
								</c:when>
							</c:choose>
							<table width="498" height="32" border="0" align="right"
								cellpadding="4" cellspacing="0">
								<tr>
									<td width="490" height="32">
										<div align="right">
											<div align="right">
												<input type="reset" value="Reset" onclick="">
												<input type="hidden" name="dispatch" value="create">
												<input type="hidden" name="page" value="2">
												<input type="hidden" name="submitType"
													value="${param.submitType}" />
<%--												<html:hidden property="particle.sampleId" />--%>
<%--												<html:hidden property="particle.sampleName" />--%>
<%--												<html:hidden property="particle.sampleSource" />--%>
<%--												<html:hidden property="particle.sampleType" />--%>
												<html:submit />
											</div>
										</div>
									</td>
								</tr>
							</table>
							<div align="right"></div>
						</td>
					</tr>
				</table>

			</td>
		</tr>
	</table>
</html:form>
