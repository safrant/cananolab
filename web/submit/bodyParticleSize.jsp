<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html:form action="/nanoparticleSize">
	<table width="100%" align="center">
		<tr>
			<td>
				<h4>
					<br>
					Physical Characterization - Size
				</h4>
			</td>
			<td align="right" width="15%">
				<a href="javascript:openHelpWindow('webHelp/caLAB_0.5/index.html?single=true&amp;context=caLAB_0.5&amp;topic=create_nanoparticle')" class="helpText">Help</a>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h5 align="center">
					${nanoparticleSizeForm.map.particleName} (${nanoparticleSizeForm.map.particleType})
				</h5>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:set var="thisForm" value="${nanoparticleSizeForm}" />
				<jsp:include page="/bodyMessage.jsp?bundle=submit" />
				<jsp:include page="bodySharedCharacterizationSummary.jsp" />
				<jsp:include page="bodySharedCharacterizationInstrument.jsp" />
				<%-- size characterization specific --%>
				<table class="topBorderOnly" cellspacing="0" cellpadding="3" width="100%" align="center" summary="" border="0">
					<tbody>
						<tr class="topBorder">
							<td class="formTitle" colspan="4">
								<div align="justify">
									Size Distribution
								</div>
							</td>
						</tr>
						<tr>
							<td class="leftLabel">
								<strong>Number of Distributions</strong>
							</td>
							<td class="label">
								<c:choose>
									<c:when test="${canUserUpdateParticle eq 'true'}">
										<html:text property="achar.numberOfDerivedBioAssayData" />
									</c:when>
									<c:otherwise>
						${nanoparticleSizeForm.map.achar.numberOfDerivedBioAssayData}&nbsp;
					</c:otherwise>
								</c:choose>
							</td>
							<td class="rightLabel" colspan="2">
								&nbsp;
								<c:choose>
									<c:when test="${canUserUpdateParticle eq 'true'}">
										<input type="button" onclick="javascript:updateCharts(this.form, 'nanoparticleSize')" value="Update Distributions">
									</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="completeLabel" colspan="4">
								<c:forEach var="achar.derivedBioAssayDataList" items="${nanoparticleSizeForm.map.achar.derivedBioAssayDataList}" varStatus="status">
									<table class="topBorderOnly" cellspacing="0" cellpadding="3" width="100%" align="center" summary="" border="0">
										<tbody>
											<tr class="topBorder">
												<td class="formSubTitle" colspan="4">
													<div align="justify">
														Graph ${status.index+1}
													</div>
												</td>
											</tr>
											<tr>
												<td class="leftLabel">
													<strong>Graph Type </strong>
												</td>
												<td class="rightLabel" colspan="3">
													<c:choose>
														<c:when test="${canUserUpdateParticle eq 'true'}">
															<html:select name="achar.derivedBioAssayDataList" property="type" indexed="true">
																<html:options name="allSizeDistributionGraphTypes" />
															</html:select>
														</c:when>
														<c:otherwise>
						${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].type}&nbsp;
					</c:otherwise>
													</c:choose>
												</td>
											</tr>
											<tr>
												<td class="leftLabel">
													<strong>Characterization File Name</strong>
												</td>
												<td class="label">
													<c:choose>
														<c:when test="${canUserUpdateParticle eq 'true'}">
															<logic:present name="characterizationFile${status.index}">
																<bean:define id="fileId" name='characterizationFile${status.index}' property='id' type="java.lang.String"/>																
																<html:hidden name="achar.derivedBioAssayDataList" property="fileId" value="${fileId}" indexed="true" />																
																<a href="nanoparticleSize.do?dispatch=download&amp;fileId=${fileId}"><bean:write name="characterizationFile${status.index}" property="displayName" /></a>																
															</logic:present>
															<logic:notPresent name="characterizationFile${status.index}">
												Click on "Load File" button
											</logic:notPresent>
														</c:when>
														<c:otherwise>
						${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].file.displayName}&nbsp;
					</c:otherwise>
													</c:choose>													
												</td>
												<td class="rightLabel" colspan="2">
													<input type="button" onclick="javascript:loadFile(this.form, 'nanoparticleSize', '${nanoparticleSizeForm.map.particleName}', ${status.index})" value="Load File">
												</td>
											</tr>
											<tr>
												<td class="leftLabel">
													<strong>Average/Mean</strong>
												</td>
												<td class="rightlabel" colspan="3">
													<c:choose>
														<c:when test="${canUserUpdateParticle eq 'true'}">
															<html:text name="achar.derivedBioAssayDataList" indexed="true" property="datumList[0].value" />
													&nbsp; ${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[0].valueUnit}	
															&nbsp;&nbsp;&nbsp;&nbsp;<strong>Z-Average</strong> &nbsp;&nbsp;
															<html:text name="achar.derivedBioAssayDataList" indexed="true" property="datumList[1].value" />
													&nbsp; ${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[1].valueUnit}
														</c:when>
														<c:otherwise>
						${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[0].value} ${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[0].valueUnit}&nbsp;&nbsp;&nbsp;&nbsp;
						${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[1].value} ${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[1].valueUnit}&nbsp;
					</c:otherwise>
													</c:choose>
												</td>
												<!-- <td class="label">
													<strong>Z-Average</strong>
												</td>
												<td class="rightLabel">
													<c:choose>
														<c:when test="${canUserUpdateParticle eq 'true'}">
															<html:text name="achar.derivedBioAssayDataList" indexed="true" property="datumList[1].value" />
													&nbsp; ${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[1].valueUnit}
														</c:when>
														<c:otherwise>
						${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[1].value} ${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[1].valueUnit}&nbsp;
					</c:otherwise>
													</c:choose>
												</td> -->
											</tr>
											<tr>
												<td class="leftLabel">
													<strong>Polydispersity Index (PDI)</strong>
												</td>
												<td class="rightLabel" colspan="3">
													<c:choose>
														<c:when test="${canUserUpdateParticle eq 'true'}">
															<html:text name="achar.derivedBioAssayDataList" indexed="true" property="datumList[2].value" />
														</c:when>
														<c:otherwise>
						${nanoparticleSizeForm.map.achar.derivedBioAssayDataList[status.index].datumList[2].value}&nbsp;
					</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</tbody>
									</table>
								</c:forEach>
							</td>
						</tr>
				</table>
				<%-- end of size characterization specific --%>
				<jsp:include page="bodySharedCharacterizationSubmit.jsp" />
			</td>
		</tr>
	</table>
</html:form>
