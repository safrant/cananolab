<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table id="summarySection3" width="95%" align="center"
	style="display: block" class="summaryViewLayer2">
	<tr>
		<th align="left">
			Chemical Association
		</th>
	</tr>
	<logic:iterate name="compositionForm"
		property="comp.chemicalAssociations" id="assoc" indexId="ind">
		<c:set var="assocType" value="${assoc.type}" />
		<c:if test="${!empty assocType}">
			<tr>
				<td>
					<div class="indented4">
						<table class="summaryViewLayer3" width="95%" align="center">
							<tr>
								<th valign="top" align="left" colspan="2">
									${assocType}
								</th>
							</tr>
							<c:if
								test="${! empty assoc.attachment.id && ! empty assoc.attachment.bondType}">
								<tr>
									<td class="cellLabel">
										Bond Type
									</td>
									<td>
										${assoc.attachment.bondType}
									</td>
								</tr>
							</c:if>
							<c:if test="${!empty fn:trim(assoc.description)}">
								<tr>
									<td class="cellLabel" width="20%">
										Description
									</td>
									<td>
										<c:out value="${fn:replace(assoc.description, cr, '<br>')}"
											escapeXml="false" />
									</td>
								</tr>
							</c:if>
							<tr>
								<td class="cellLabel">
									Associated Elements
								</td>
								<td>
									<table>
										<tr>
											<td>
												${assoc.associatedElementA.compositionType}
												${assoc.associatedElementA.entityDisplayName}
												<c:choose>
													<c:when
														test="${! empty assoc.associatedElementA.composingElement.id }">
											composing element of type ${assoc.associatedElementA.composingElement.type} <br>(name: ${assoc.associatedElementA.composingElement.name})
														</c:when>
													<c:otherwise>
														<br>(name: ${assoc.associatedElementA.domainElement.name})
															</c:otherwise>
												</c:choose>
											</td>
											<td
												style="border: 0; vertical-align: top; text-align: center;">
												<img src="images/arrow_left_right_gray.gif" id="assocImg" />
												<br>
												<strong>associated with</strong>
											</td>
											<td>
												${assoc.associatedElementB.compositionType}
												${assoc.associatedElementB.entityDisplayName}
												<c:choose>
													<c:when
														test="${! empty assoc.associatedElementB.composingElement.id }">

composing element of type ${assoc.associatedElementB.composingElement.type} <br>(name: ${assoc.associatedElementB.composingElement.name})
														</c:when>
													<c:otherwise>
														<br> (name: ${assoc.associatedElementB.domainElement.name})
															</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<c:if test="${! empty assoc.files}">
								<tr>
									<td class="cellLabel">
										Files
									</td>
									<td>
										<c:forEach var="file" items="${assoc.files}">
											<c:choose>
												<c:when test="${file.hidden eq 'true'}">
												Private File
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${file.image eq 'true'}">
						 				${file.domainFile.title}
										<br>
															<a href="#"
																onclick="popImage(event, 'composition.do?dispatch=download&amp;fileId=${file.domainFile.id}&amp;location=${location}', ${file.domainFile.id}, 100, 100)"><img
																	src="composition.do?dispatch=download&amp;fileId=${file.domainFile.id}&amp;location=${location}"
																	border="0" width="150"> </a>
														</c:when>
														<c:otherwise>
															<a
																href="composition.do?dispatch=download&amp;fileId=${file.domainFile.id}&amp;location=${location}">
																${file.domainFile.title}</a>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
											<br>
										</c:forEach>
									</td>
								</tr>
							</c:if>
						</table>
		</c:if>
	</logic:iterate>
</table>
<div id="summarySeparator3">
	<br>
</div>


