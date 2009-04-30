<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
	<c:when
		test="${param.summary eq 'true'}">
		<c:choose>
			<c:when test="${! empty charBean.physicalState.type}">
				<table class="summaryViewLayer4" align="center" width="95%">
					<tr>
						<th>
							Type
						</th>
					</tr>
					<tr>
						<td>
							${charBean.physicalState.type}
						</td>
				</table>
			</c:when>
			<c:otherwise>N/A
	</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<table width="100%" align="center" class="submissionView">
			<tr>
				<th colspan="4">
					Physical State Properties
				</th>
			</tr>
			<tr>
				<td class="cellLabel" width="20%">
					Type
				</td>
				<td>
					<div id="physicalStateTypePrompt">
						<select name="achar.physicalState.type" id="physicalStateType"
							onchange="javascript:callPrompt('Type', 'physicalStateType', 'physicalStateTypePrompt');">
							<option value=""></option>
							<c:forEach var="type" items="${physicalStateTypes}">
								<c:choose>
									<c:when
										test="${type eq characterizationForm.map.achar.physicalState.type}">
										<option value="${type}" selected>
									</c:when>
									<c:otherwise>
										<option value="${type}">
									</c:otherwise>
								</c:choose>
						${type}
					</option>
							</c:forEach>
							<option value="other">
								[Other]
							</option>
						</select>
					</div>
				</td>
			</tr>
		</table>
		<br>
	</c:otherwise>
</c:choose>

