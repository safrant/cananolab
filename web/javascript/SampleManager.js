var emptyOption = [ {
	label : "",
	value : ""
} ];

function retrieveParticleNames() {
	var sampleType = document.getElementById("sampleType").value;
	ParticleManager
			.getNewParticleNamesByType(sampleType, populateParticleNames);
}
function resetParticleNames() {
	dwr.util.removeAllOptions("sampleName");
	dwr.util.addOptions("sampleName", emptyOption, "value", "label");
}
function populateParticleNames(sampleNames) {
	// get previous selection
	var selectedParticleName = dwr.util.getValue("sampleName");
	// remove option that's the same as previous selection
	var updatedParticleNames = new Array();
	if (sampleNames != null) {
		for (i = 0; i < sampleNames.length; i++) {
			if (sampleNames[i] != selectedParticleName) {
				updatedParticleNames.push(sampleNames[i]);
			}
		}
	}
	dwr.util.addOptions("sampleName", updatedParticleNames);
}

function setSampleDropdowns() {
	var searchLocations = getSelectedOptions(document
			.getElementById("searchLocations"));
	SampleManager.getNanomaterialEntityTypes(searchLocations, function(data) {
		dwr.util.removeAllOptions("nanomaterialEntityTypes");
		dwr.util.addOptions("nanomaterialEntityTypes", data);
	});
	SampleManager.getFunctionalizingEntityTypes(searchLocations,
			function(data) {
				dwr.util.removeAllOptions("functionalizingEntityTypes");
				dwr.util.addOptions("functionalizingEntityTypes", data);
			});

	SampleManager.getFunctionTypes(searchLocations, function(data) {
		dwr.util.removeAllOptions("functionTypes");
		dwr.util.addOptions("functionTypes", data);
	});
	return false;
}

function setCompositionEntityOptions() {
	var compositionType = dwr.util.getValue("compType");
	if (compositionType == "nanomaterial entity") {
		SampleManager.getNanomaterialEntityTypes(null, function(data) {
			dwr.util.removeAllOptions("entityType");
			dwr.util.addOptions("entityType", emptyOption, "value", "label");
			dwr.util.addOptions("entityType", data);
		});
	} else if (compositionType = "functionalizing entity") {
		SampleManager.getFunctionalizingEntityTypes(null, function(data) {
			dwr.util.removeAllOptions("entityType");
			dwr.util.addOptions("entityType", emptyOption, "value", "label");
			dwr.util.addOptions("entityType", data);
		});
	}
}

var compositionQueryCache = {};
var numberOfCompQueries = null;
var currentSearchBean = null;

function clearCompositionQuery() {
	dwr.util.setValue("compQueryId", "");
	dwr.util.setValue("compType", "");
	dwr.util.setValue("entityType", "");
	dwr.util.setValue("compOperand", "");
	dwr.util.setValue("chemicalName", "");
	hide("deleteCompositionQuery");
}

function addCompositionQuery() {
	var queryId = dwr.util.getValue("compQueryIdId");
	if (queryId == null || queryId == "") {
		queryId = -1000 - numberOfCompQueries;
	}
	var theQuery = {
		id : queryId,
		compositionType : dwr.util.getValue("compType"),
		entityType : dwr.util.getValue("entityType"),
		chemicalName : dwr.util.getValue("chemicalName"),
		operand : dwr.util.getValue("compOperand")
	};
	if (theQuery.entityType != "" && theQuery.operand != ""
			&& theQuery.chemicalName != "") {
		SampleManager.addCompositionQuery(theQuery, function(searchBean) {
			if (searchBean != null) {
				currentSearchBean = searchBean;
				populateCompositionQueries();
				show("compositionLogicalOperator");
			} else {
				sessionTimeout();
			}
		});
	} else {
		alert("Please fill in values");
	}
}

function populateCompositionQueries() {
	var queries = currentSearchBean.compositionQueries;
	dwr.util.removeAllRows("compositionQueryRows", {
		filter : function(tr) {
			return (tr.id != "compPattern");
		}
	});
	var theQuery, id;
	if (queries.length > 0) {
		show("compositionQueryTable");
	} else {
		hide("compositionQueryTable");
		hide("compositionLogicalOperator");
	}
	for ( var i = 0; i < queries.length; i++) {
		theQuery = queries[i];
		if (theQuery.id == null || theQuery.id == "") {
			theQuery.id = -i - 1;
		}
		id = theQuery.id;
		dwr.util.cloneNode("compPattern", {
			idSuffix : id
		});
		dwr.util.setValue("compTypeValue" + id, theQuery.compositionType);
		dwr.util.setValue("entityTypeValue" + id, theQuery.entityType);
		dwr.util.setValue("compOperandValue" + id, theQuery.operand);
		dwr.util.setValue("chemicalNameValue" + id, theQuery.chemicalName);
		dwr.util.setValue("compQueryId", id);
		$("compPattern" + id).style.display = "";
		if (compositionQueryCache[id] == null) {
			numberOfCompQueries++;
		}
		compositionQueryCache[id] = theQuery;
	}
	clearCompositionQuery();
}

function editCompositionQuery(eleid) {
	// we were an id of the form "edit{id}", eg "compEdit42". We lookup the "42"
	var query = compositionQueryCache[eleid.substring(8)];
	dwr.util.setValue("compType", query.compositionType);
	dwr.util.setValue("entityType", query.entityType);
	dwr.util.setValue("compOperand", query.operand);
	dwr.util.setValue("chemicalName", query.chemicalName);
	dwr.util.setValue("compQueryId", query.id);
	show("deleteCompositionQuery");
}

function deleteTheCompositionQuery() {
	var eleid = document.getElementById("compQueryId").value;
	if (eleid != "") {
		var query = compositionQueryCache[eleid];
		if (confirm("Are you sure you want to delete this query?")) {
			SampleManager.deleteCompositionQuery(query, function(searchBean) {
				if (searchBean != null) {
					currentSearchBean = searchBean;
					populateCompositionQueries();
				} else {
					sessionTimeout();
				}
			});
		}
	}
}

//characterization specific
function setCharacterizationOptions() {
	var charType = dwr.util.getValue("charType");
	CharacterizationManager.getCharacterizationOptions(charType, function (data) {
		dwr.util.removeAllOptions("charName");
		dwr.util.addOptions("charName", emptyOption, "value", "label");
		dwr.util.addOptions("charName", data);
	});
}

function setDatumNameOptionsByCharName() {
	var charType=dwr.util.getValue("charType");
	var charName=dwr.util.getValue("charName");	
	FindingManager.getDatumNameOptions(charType, charName, function(data) {
		dwr.util.removeAllOptions("datumName");
		dwr.util.addOptions("datumName", emptyOption, "value", "label");		
		dwr.util.addOptions("datumName", data);
	});
}

function setDatumValueUnitOptions() {
	var datumName=dwr.util.getValue("datumName");
	FindingManager.getColumnValueUnitOptions(datumName, null, function(data) {
		dwr.util.removeAllOptions("datumValueUnit");
		dwr.util.addOptions("datumValueUnit", emptyOption, "value", "label");		
		dwr.util.addOptions("datumValueUnit", data);		
	});
}

var characterizationQueryCache = {};
var numberOfCharQueries = null;
var currentSearchBean = null;

function clearCharacterizationQuery() {
	dwr.util.setValue("charQueryId", "");
	dwr.util.setValue("charType", "");
	dwr.util.setValue("charName", "");
	dwr.util.setValue("datumName", "");
	dwr.util.setValue("charOperand", "");
	dwr.util.setValue("datumValue", "");
	dwr.util.setValue("datumValueUnit", "");
	hide("deleteCharacterizationQuery");
}

function addCharacterizationQuery() {
	var queryId = dwr.util.getValue("charQueryId");
	if (queryId == null || queryId == "") {
		queryId = -10000 - numberOfCharQueries;
	}
	var theQuery = {
		id : queryId,
		characterizationType : dwr.util.getValue("charType"),
		characterizationName : dwr.util.getValue("charName"),
		datumName : dwr.util.getValue("datumName"),
		operand : dwr.util.getValue("charOperand"),
		datumValue : dwr.util.getValue("datumValue"),
		datumValueUnit : dwr.util.getValue("datumValueUnit")
	};
	if (theQuery.datumName != "" && theQuery.operand != ""
			&& theQuery.datumValue != "") {
		SampleManager.addCharacterizationQuery(theQuery, function(searchBean) {
			if (searchBean != null) {
				currentSearchBean = searchBean;
				populateCharacterizationQueries();
				show("characterizationLogicalOperator");
			} else {
				sessionTimeout();
			}
		});
	} else {
		alert("Please fill in values");
	}
}

function populateCharacterizationQueries() {
	var queries = currentSearchBean.characterizationQueries;
	dwr.util.removeAllRows("characterizationQueryRows", {
		filter : function(tr) {
			return (tr.id != "charPattern");
		}
	});
	var theQuery, id;
	if (queries.length > 0) {
		show("characterizationQueryTable");
	} else {
		hide("characterizationQueryTable");
		hide("characterizationLogicalOperator");
	}
	for ( var i = 0; i < queries.length; i++) {
		theQuery = queries[i];
		if (theQuery.id == null || theQuery.id == "") {
			theQuery.id = -i - 1;
		}
		id = theQuery.id;
		dwr.util.cloneNode("charPattern", {
			idSuffix : id
		});		
		dwr.util.setValue("charTypeValue" + id, theQuery.characterizationType);
		dwr.util.setValue("charNameValue" + id, theQuery.characterizationName);
		dwr.util.setValue("datumNameValue" + id, theQuery.datumName);
		dwr.util.setValue("charOperandValue" + id, theQuery.operand);
		dwr.util.setValue("datumValueValue" + id, theQuery.datumValue);
		dwr.util.setValue("datumValueUnitValue" + id, theQuery.datumValueUnit);
		dwr.util.setValue("charQueryId", id);
		$("charPattern" + id).style.display = "";
		if (characterizationQueryCache[id] == null) {
			numberOfCharQueries++;
		}
		characterizationQueryCache[id] = theQuery;
	}
	clearCharacterizationQuery();
}

function editCharacterizationQuery(eleid) {
	// we were an id of the form "edit{id}", eg "charEdit42". We lookup the "42"
	var query = characterizationQueryCache[eleid.substring(8)];
	dwr.util.setValue("charType", query.characterizationType);
	dwr.util.setValue("charName", query.characterizationName);
	dwr.util.setValue("datumName", query.datumName);
	dwr.util.setValue("charOperand", query.operand);
	dwr.util.setValue("datumValue",  query.datumValue);
	dwr.util.setValue("datumValueUnit", query.datumValueUnit);
	dwr.util.setValue("charQueryId", query.id);
	show("deleteCharacterizationQuery");
}

function deleteTheCharacterizationQuery() {
	var eleid = document.getElementById("charQueryId").value;
	if (eleid != "") {
		var query = characterizationQueryCache[eleid];
		if (confirm("Are you sure you want to delete this query?")) {
			SampleManager.deleteCharacterizationQuery(query, function(searchBean) {
				if (searchBean != null) {
					currentSearchBean = searchBean;
					populateCharacterizationQueries();
				} else {
					sessionTimeout();
				}
			});
		}
	}
}
