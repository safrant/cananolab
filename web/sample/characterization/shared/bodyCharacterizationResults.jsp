<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
<!--//
function confirmDeletion()
{
	answer = confirm("Are you sure you want to delete the data set?")
	if (answer !=0)
	{
		this.document.forms[0].dispatch.value="deleteFinding";
		this.document.forms[0].page.value="1";
		this.document.forms[0].submit();
		return true;
	}
}
//-->
</script>
<table width="100%" align="center" class="submissionView">
	<tr>
		<th>
			Results
		</th>
	</tr>
	<tr>
		<td class="cellLabel">
			Finding&nbsp;&nbsp;
			<a id="addFinding" href="javascript:resetTheFinding(true);"><img
					align="top" src="images/btn_add.gif" border="0" /> </a>
		</td>
	</tr>
	<tr>
		<td>
			<c:if test="${! empty characterizationForm.map.achar.findings }">
				<c:set var="charBean" value="${characterizationForm.map.achar}" />
				<c:set var="edit" value="true" />
				<%@ include file="bodyFindingView.jsp"%>
			</c:if>
		</td>
	<tr>
		<td>
			<div id="newFinding" style="display: block;">
				<table class="subSubmissionView" width="85%" align="center">
					<tr>
						<th colspan="2">
							New Finding
						</th>
					</tr>
					<tr>
						<td colspan="2" height="5">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" class="cellLabel">
							File
							<a id="addFile" href="javascript:clearFile(); show('newFile');"><img
									align="top" src="images/btn_add.gif" border="0" /> </a>
							<br>
						</td>
					</tr>
					<tr>
						<td valign="top" colspan="2">
							<c:if
								test="${! empty characterizationForm.map.achar.theFinding.files}">
								<table class="summaryViewLayer4" width="95%" align="center">
									<tbody>
										<c:forEach var="file"
											items="${characterizationForm.map.achar.theFinding.files}">
											<tr>
												<td>
													${file.domainFile.uri}
												</td>
												<td>
													<div align="right">
														<a href="">Edit</a>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<br>
							</c:if>
							<div style="display: none" id="newFile">
								<jsp:include page="bodySubmitCharacterizationFile.jsp" />
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" height="5">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" class="cellLabel">
							Data
						</td>
					</tr>
					<tr>
						<td valign="top" colspan="2">
							<div style="display: block" id="submitDatum">
								<jsp:include page="bodySubmitData.jsp" />
								&nbsp;
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="Delete"
								onclick="javascript:confirmDeletion()">
						</td>
						<td>
							<div align="right">
								<input type="button" value="Cancel"
									onclick="javascript:hide('newFinding'); show('existingFinding');">
								<input type="button" value="Save"
									onclick="javascript:saveFinding('characterization');">
							</div>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</td>
	</tr>
</table>
<br>
