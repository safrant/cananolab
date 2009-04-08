<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="javascript/ProtocolManager.js"></script>
<script type="text/javascript"
	src="/caNanoLab/dwr/interface/ProtocolManager.js"></script>
<script type='text/javascript' src='/caNanoLab/dwr/engine.js'></script>
<script type='text/javascript' src='/caNanoLab/dwr/util.js'></script>

<html:form action="searchProtocol">
	<table align="center" width="100%">
		<tr>
			<td>
				<h3>
					<br>
					Search Protocols
				</h3>
			</td>
			<td align="right" width="25%">
				<jsp:include page="/helpGlossary.jsp">
					<jsp:param name="topic" value="search_protocol_help" />
					<jsp:param name="glossaryTopic" value="glossary_help" />
				</jsp:include>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="/bodyMessage.jsp?bundle=protocol" />
				<table width="100%" align="center" class="submissionView">
					<tr>
						<td class="cellLabel" width="20%">
							Search Location
						</td>
						<td>
							<html:select styleId="searchLocations" property="searchLocations"
								onchange="javascript:setProtocolNameDropdown()" multiple="true"
								size="4">
								<html:option value="local">
										Local
									</html:option>
								<c:if test="${! empty allGridNodes}">
									<html:options collection="allGridNodes" property="hostName"
										labelProperty="hostName" />
								</c:if>
							</html:select>
						</td>
					</tr>
				</table>
				<br>
				<table width="100%" align="center" class="submissionView">
					<tr>
						<td class="cellLabel">
							Protocol Type
						</td>
						<td>
							<html:select styleId="protocolType" property="protocolType">
								<option />
									<html:options name="protocolTypes" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td class="cellLabel">
							Protocol Name
						</td>
						<td>
							<html:text property="protocolName" size="100" />
							<br>
							<em>* for wildcard search</em>
						</td>
					</tr>
					<tr>
						<td class="cellLabel">
							Protocol File Title
						</td>
						<td>
							<html:text property="fileTitle" size="100" />
							<br>
							<em>* for wildcard search</em>
						</td>
					</tr>
				</table>
				<br>
				<table width="100%" border="0" align="center" cellpadding="3"
					cellspacing="0" class="topBorderOnly" summary="">
					<tr>
						<td>
							<span class="formMessage"> </span>
							<br>
							<table border="0" align="right" cellpadding="4" cellspacing="0">
								<tr>
									<td>
										<div align="right">
											<input type="reset" value="Reset"
												onclick="javascript:location.href='searchProtocol.do?dispatch=setup&page=0'">
											<input type="hidden" name="dispatch" value="search">
											<input type="hidden" name="page" value="1">
											<html:submit value="Search" />
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
<!--_____ main content ends _____-->
