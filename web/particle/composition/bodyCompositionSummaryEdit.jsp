<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="animatedtabs" id="summaryTabALL">
	<ul>
		<li class="selected">
			<a href="javascript:showSummary('ALL')" title="Summary"><span>Summary</span>
			</a>
		</li>
		<li>
			<a href="javascript:showSummary('1')" title="Nanoparticle Entity">
				<span>Nanoparticle Entity</span> </a>
		</li>
		<li>
			<a href="javascript:showSummary('2')" title="Functionalizing Entity">
				<span>Functionalizing Entity</span> </a>
		</li>
		<li>
			<a href="javascript:showSummary('3')" title="Chemical Association"><span>
					Chemical Association</span> </a>
		</li>
		<li>
			<a href="javascript:showSummary('4')" title="Composition File"><span>Composition
					File</span> </a>
		</li>
	</ul>
</div>
<c:forEach var='item' begin='1' end='4'>
	<div class="animatedtabs" id="summaryTab${item}" style="display: none;">
		<ul>
			<li>
				<a href="javascript:showSummary('ALL')" title="All"><span>All</span>
				</a>
			</li>
			<li class="selected">
				<a href="javascript:showSummary('1')" title="Nanoparticle Entity">
					<span>Nanoparticle Entity</span> </a>
			</li>
			<li>
				<a href="javascript:showSummary('2')" title="Functionalizing Entity">
					<span>Functionalizing Entity</span> </a>
			</li>
			<li>
				<a href="javascript:showSummary('3')" title="Chemical Association"><span>
						Chemical Association</span> </a>
			</li>
			<li>
				<a href="javascript:showSummary('4')" title="Composition File"><span>Composition
						File</span> </a>
			</li>
		</ul>
	</div>
</c:forEach>
<br>
<br>
<jsp:include page="nanoparticleEntity/bodyNanoparticleEntityView.jsp">
	<jsp:param name="particleId" value="${param.particleId}" />
</jsp:include>
<jsp:include
	page="functionalizingEntity/bodyFunctionalizingEntityView.jsp">
	<jsp:param name="particleId" value="${param.particleId}" />
</jsp:include>
<jsp:include page="bodyChemicalAssociationView.jsp">
	<jsp:param name="particleId" value="${param.particleId}" />
</jsp:include>
<jsp:include page="bodyCompositionFileView.jsp">
	<jsp:param name="particleId" value="${param.particleId}" />
</jsp:include>