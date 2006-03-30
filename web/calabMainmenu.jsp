<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@	taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@	taglib uri="/WEB-INF/fn.tld" prefix="fn"%>

<table cellspacing="0" cellpadding="0" summary="" border="0">
	<tbody>
		<tr>
		<%--
			<td width="1" valign="top">
				<!-- anchor to skip main menu -->
				<a href="#content"><img height="1" alt="Skip Menu" src="images/shim.gif" width="1" border="0"></a>
			</td>
        --%>
			<logic:iterate name="items" id="item" type="org.apache.struts.tiles.beans.MenuItem">
				<c:choose>
					<c:when test="${fn:toUpperCase(menu) eq item.value}">
						<c:set var="style" value="mainMenuItemOver" />
					</c:when>
					<c:otherwise>
						<c:set var="style" value="mainMenuItem" />
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${item.link eq ''}">
						<td class="${style}" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'${style}'),hideCursor()" height="20">
							<a class="mainMenuLink" href="#" onmouseover="s_show('${item.value}',event)" onmouseout="s_hide()">${item.value}</a>
						</td>
					</c:when>
					<c:otherwise>
						<td class="${style}" onmouseover="changeMenuStyle(this,'mainMenuItemOver'),showCursor()" onclick="document.location.href='${item.link}'" onmouseout="changeMenuStyle(this,'${style}'),hideCursor()" height="20">
							<a class="mainMenuLink" href="${item.link}" onmouseover="s_show('${item.value}',event)" onmouseout="s_hide()">${item.value}</a>
						</td>
					</c:otherwise>
				</c:choose>
				<td>
					<img height="16" alt="" src="images/mainMenuSeparator.gif" width="1">
				</td>
			</logic:iterate>
		</tr>
	</tbody>
</table>
