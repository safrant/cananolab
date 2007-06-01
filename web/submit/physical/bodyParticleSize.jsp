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
				<a href="javascript:openHelpWindow('webHelp/index.html?single=true&amp;context=caNanoLab&amp;topic=nano_size_help')" class="helpText">Help</a>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h5 align="center">
					${nanoparticleCharacterizationForm.map.particleName} (${nanoparticleCharacterizationForm.map.particleType})
				</h5>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="/bodyMessage.jsp?bundle=submit" />
				<jsp:include page="/submit/bodySharedCharacterizationSummary.jsp" />
				<jsp:include page="/submit/bodySharedCharacterizationInstrument.jsp" />
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
									<c:when test="${canUserSubmit eq 'true'}">
										<html:text property="achar.numberOfDerivedBioAssayData" />
									</c:when>
									<c:otherwise>
						${nanoparticleCharacterizationForm.map.achar.numberOfDerivedBioAssayData}&nbsp;
					</c:otherwise>
								</c:choose>
							</td>
							<td class="rightLabel" colspan="2">
								&nbsp;
								<c:choose>
									<c:when test="${canUserSubmit eq 'true'}">
										<input type="button" onclick="javascript:updateCharts(this.form, 'nanoparticleSize')" value="Update Distributions">
									</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="completeLabel" colspan="4">
								<logic:iterate name="nanoparticleCharacterizationForm" property="achar.derivedBioAssayDataList" id="derivedBioAssayData" indexId="chartInd">
									<table class="topBorderOnly" cellspacing="0" cellpadding="3" width="100%" align="center" summary="" border="0">
										<tbody>
											<tr class="topBorder">
												<td class="formSubTitle" colspan="4">
													<div align="justify">
														Graph ${chartInd+1}
													</div>
												</td>
											</tr>
											<tr>
												<td class="leftLabel">
													<strong>Graph Type </strong>
												</td>
												<td class="rightLabel" colspan="3">
													<c:choose>
														<c:when test="${canUserSubmit eq 'true'}">
															<html:select property="achar.derivedBioAssayDataList[${chartInd}].type">
																<html:options name="allSizeDistributionGraphTypes" />
															</html:select>
														</c:when>
														<c:otherwise>
						${nanoparticleCharacterizationForm.map.achar.derivedBioAssayDataList[chartInd].type}&nbsp;
					</c:otherwise>
													</c:choose>
												</td>
											</tr>
											<jsp:include page="/submit/bodySharedCharacterizationFile.jsp?chartInd=${chartInd}&actionName=nanoparticleSize" />
											<tr>
												<td class="leftLabel">
													<strong>Average/Mean</strong>
												</td>
												<td class="rightlabel" colspan="3">
													<c:choose>
														<c:when test="${canUserSubmit eq 'true'}">
															<html:text property="achar.derivedBioAssayDataList[${chartInd}].datumList[0].value" />
													&nbsp; ${nanoparticleCharacterizationForm.map.achar.derivedBioAssayDataList[chartInd].datumList[0].valueUnit}	
															&nbsp;&nbsp;&nbsp;&nbsp;<strong>Z-Average</strong> &nbsp;&nbsp;
															<html:text property="achar.derivedBioAssayDataList[${chartInd}].datumList[1].value" />
													&nbsp; ${nanoparticleCharacterizationForm.map.achar.derivedBioAssayDataList[chartInd].datumList[1].valueUnit}
														</c:when>
														<c:otherwise>
						${nanoparticleCharacterizationForm.map.achar.derivedBioAssayDataList[chartInd].datumList[0].value} ${nanoparticleCharacterizationForm.map.achar.derivedBioAssayDataList[chartInd].datumList[0].valueUnit}
&nbsp;&nbsp;&nbsp;&nbsp;<strong>Z-Average</strong> &nbsp;&nbsp;
						${nanoparticleCharacterizationForm.map.achar.derivedBioAssayDataList[chartInd].datumList[1].value} ${nanoparticleCharacterizationForm.map.achar.derivedBioAssayDataList[chartInd].datumList[1].valueUnit}&nbsp;
					</c:otherwise>
													</c:choose>
												</td>
											</tr>
											<tr>
												<td class="leftLabel">
													<strong>Polydispersity Index (PDI)</strong>
												</td>
												<td class="rightLabel" colspan="3">
													<c:choose>
														<c:when test="${canUserSubmit eq 'true'}">
															<html:text property="achar.derivedBioAssayDataList[${chartInd}].datumList[2].value" />
														</c:when>
														<c:otherwise>
						${nanoparticleCharacterizationForm.map.achar.derivedBioAssayDataList[chartInd].datumList[2].value}&nbsp;
					</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</tbody>
									</table>
									<br>
								</logic:iterate>
							</td>
						</tr>
				</table>
				<%-- end of size characterization specific --%>
				<br>
				<jsp:include page="/submit/bodySharedCharacterizationCopy.jsp" />
				<jsp:include page="/submit/bodySharedCharacterizationSubmit.jsp" />
			</td>
		</tr>
	</table>
</html:form>
