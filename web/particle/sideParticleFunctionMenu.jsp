<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
	<c:when test="${displaytype == 'Therapeutic' ||
				displaytype == 'Targeting' ||
				displaytype == 'Diagnostic Imaging' ||
				displaytype == 'Diagnostic Reporting'}">
		<c:set var="funcDisplay" value="display: block;" />
	</c:when>
	<c:otherwise>
		<c:set var="funcDisplay" value="display: none;" />
	</c:otherwise>
</c:choose>

<li class="toplist"><a href="#" class="subMenuSecondary" >FUNCTION</a>
	<ul class="sublist_4" style="${funcDisplay}">
	<c:forEach var="funcType" items="${allFunctionTypes}">
	   <c:choose>
		<c:when test="${!empty allFuncTypeFuncs[funcType]}" >
			<li><a href="#">${funcType}</a>
        		<ul class="sublist_5" style="${funcDisplay}">
				<c:forEach var="aFunc" items="${allFuncTypeFuncs[funcType]}">
						<c:url var="url" value="nanoparticleFunction.do">
							<c:param name="page" value="0" />
							<c:param name="dispatch" value="${dispatchValue}" />
							<c:param name="particleName" value="${particleName}" />
							<c:param name="particleType" value="${particleType}" />
							<c:param name="functionId" value="${aFunc.id}" />
							<c:param name="submitType" value="${funcType}" />
						</c:url>
						<li><a href=${url}><span class="data_anchar">>&nbsp;</span>${aFunc.viewTitle}</a></li>
				</c:forEach>
				<c:if test="${canCreateNanoparticle eq 'true'}">
					<c:url var="addUrl" value="nanoparticleFunction.do">
							<c:param name="page" value="0" />
							<c:param name="dispatch" value="setup" />
							<c:param name="particleName" value="${particleName}" />
							<c:param name="particleType" value="${particleType}" />
							<c:param name="submitType" value="${funcType}" />
					</c:url>
					<li><a href="${addUrl}">Enter ${funcType}</a></li>
				</c:if>
        		</ul>
        	</li>
       </c:when>
       <c:otherwise>
			<c:if test="${canCreateNanoparticle eq 'true'}">
       			<li><a href="#">${funcType}</a>
       				<ul class="sublist_5" style="${funcDisplay}">
       					<c:url var="addUrl" value="nanoparticleFunction.do">
							<c:param name="page" value="0" />
							<c:param name="dispatch" value="setup" />
							<c:param name="particleName" value="${particleName}" />
							<c:param name="particleType" value="${particleType}" />
							<c:param name="submitType" value="${funcType}" />
						</c:url>
						<li><a href="${addUrl}">Enter ${funcType}</a></li>
					</ul>
				</li>
			</c:if>
       </c:otherwise>
      </c:choose>
	</c:forEach>
	</ul>
</li>