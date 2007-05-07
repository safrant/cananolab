<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html:form action="/submitProtocol" enctype="multipart/form-data">
	<table width="100%" align="center">
		<tr>
			<td>
				<h3>
					<br>
					Submit Protocol
				</h3>
			</td>
			<td align="right" width="15%">
				<a href="javascript:openHelpWindow('webHelp/index.html?single=true&amp;context=caNanoLab&amp;topic=nano_protocol_help')" class="helpText">Help</a>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="/bodyMessage.jsp?bundle=submit" />
				<table class="topBorderOnly" cellspacing="0" cellpadding="3" width="100%" align="center" summary="" border="0">
					<tbody>
						<tr class="topBorder">
							<td class="formTitle" colspan="4">
								<div align="justify">
									Description
								</div>
							</td>
						</tr>
						<tr>
							<td class="leftLabel">
								<strong>Protocol Type*</strong>
							</td>
							<td class="rightLabel">
								<html:select property="protocolType">
									<option value="physical assay">Physical Assay</option>
									<option value="invitro assay">In Vitro Assay</option>
									<option value="invivo array">In Vivo Assay</option>
									<option value="safety">Safety</option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="leftLabel" >
								<strong>Protocol Name* </strong>
							</td>
							<td class="rightLabel">
								<html:select property="nameSource" onchange="javascript:updateOtherField(submitProtocolForm, 'nameSource', 'otherNameSource')">
									<option value="GTA-3">GTA-3</option>
									<option value="ITA-6"> ITA-6</option>
									<option value="STE-3">STE-3</option>
									<option value="other">Other</option>
								</html:select> &nbsp; &nbsp; Other Name
								<html:text property="otherNameSource" disabled="true" /> &nbsp; &nbsp; &nbsp; Protocol Version* &nbsp; <html:text property="protocolVersion" size="10"/>
							</td>
						</tr>
						<tr>
							<td class="leftLabel">
								<strong>Protocol File*</strong>
							</td>
							<td class="rightLabel">
								<html:file property="uploadedFile" />
							</td>
						</tr>
						<tr>
							<td class="leftLabel">
								<strong>File Title*</strong>
							</td>
							<td class="rightLabel">
								<html:text property="fileTitle" size="80"/>
							</td>
						</tr>
						<tr>
							<td class="leftLabel">
								<strong>Description</strong>
							</td>
							<td class="rightLabel">
								<html:textarea property="description" rows="3" cols="80" />
							</td>
						</tr>
						<tr>
							<td class="leftLabel">
								<strong>Visibility</strong>
							</td>
							<td class="rightLabel">
								<html:select property="visibility" multiple="true" size="6">
									<html:options name="allVisibilityGroups" />
								</html:select>
								<br>
								<i>(${applicationOwner}_Researcher and ${applicationOwner}_PI are defaults if none of above is selected.)</i>						
							</td>
						</tr>
					</tbody>
				</table>
				<br>
				<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0" class="topBorderOnly" summary="">
					<tr>
						<td width="30%">
							<span class="formMessage"> </span>
							<br>
							<table width="498" height="15" border="0" align="right" cellpadding="4" cellspacing="0">
								<tr>
									<td width="490" height="15">
										<div align="right">
											<div align="right">
												<input type="reset" value="Reset" onclick="javascript:location.href='publishReport.do?dispatch=setup&page=0'">
												<input type="hidden" name="dispatch" value="submit">
												<input type="hidden" name="page" value="2">
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
