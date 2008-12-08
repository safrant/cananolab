
function setupOrganization(form, selectEleId) {
	var selectEle = document.getElementById(selectEleId);
	var otext = selectEle.options[selectEle.options.selectedIndex].text;
	if(otext == "[Other]") {
		form.action = "submitNanoparticleSample.do?dispatch=setupOrganization&page=0&location=local";
		form.submit();
	}
	return false;
}

function setupOrgDetailView(form, selectEleId) {
	var selectEle = document.getElementById(selectEleId);
	var org = selectEle.options[selectEle.options.selectedIndex].text;
	if(org != "[Other]") {
		form.action = "submitNanoparticleSample.do?dispatch=organizationDetailView&page=0&location=local" + 
			"&organizationName=" + org;
		form.submit();
	}
	return false;
}