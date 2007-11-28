<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="charTitle">
	<tr class="titleRow">
		<td class="${param.charTypeLabelStyle}">
		    <c:set var="url" value="#"/>
			<c:if test="${!empty charaLeafToCharacterizations[param.charType]}">
				<c:url var="url" value="${charaLeafActionName[param.charType]}.do">
					<c:param name="particleId" value="${particleId}" />
					<c:param name="submitType" value="${param.charType}" />
					<c:param name="page" value="0" />
					<c:param name="dispatch" value="summaryView" />
				</c:url>
			</c:if>
			<a href="${url}" class="${param.charTypeStyle}">${param.charType}</a>
		</td>
		<td>
			&nbsp;
		</td>
		<c:if test="${canCreateNanoparticle eq 'true'}">
			<c:url var="submitUrl"
				value="${charaLeafActionName[param.charType]}.do">
				<c:param name="particleId" value="${particleId}" />
				<c:param name="submitType" value="${param.charType}" />
				<c:param name="page" value="0" />
				<c:param name="dispatch" value="setup" />
			</c:url>
			<td class="addCell">
				<a href="${submitUrl}" class="addlink"><img
						src="images/btn_add.gif" border="0" /> </a>
			</td>
		</c:if>
		<c:if
			test="${canUserDeleteChars eq 'true' &&
												!empty charaLeafToCharacterizations[param.charType]}">
			<td>
				&nbsp;
			</td>
			<td class="addCell">
				<c:url var="deleteUrl" value="deleteAction.do">
					<c:param name="particleId" value="${particleId}" />
					<c:param name="submitType" value="${param.charType}" />
					<c:param name="page" value="0" />
					<c:param name="dispatch" value="setup" />
				</c:url>
				<a href="${deleteUrl}" class="addlink"><img
						src="images/btn_delete.gif" border="0" /> </a>
			</td>
		</c:if>
	</tr>
</table>
