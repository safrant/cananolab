<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="StyleSheet" type="text/css" href="css/promptBox.css">
<script type="text/javascript" src="javascript/addDropDownOptions.js"></script>

<table border="0" align="center" cellpadding="3" cellspacing="0"
	class="topBorderOnly" summary="">
	<tr>
		<td class="leftLabelWithTop">
			<strong>Technique*</strong>
		</td>
		<td class="rightLabelWithTop">
			<html:select
				property="achar.theExperimentConfig.domain.technique.type"
				styleId="techniqueType"
				onchange="javascript:callPrompt('Technique Type', 'techniqueType');retrieveTechniqueAbbreviation();">
				<option value=""></option>
				<html:options collection="allTechniques" labelProperty="type"
					property="type" />
				<option value="other">
					[Other]
				</option>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="leftLabel">
			<strong>Abbreviation</strong>
		</td>
		<td class="rightLabel">
			<html:text styleId="techniqueAbbr"
				property="achar.theExperimentConfig.domain.technique.abbreviation"
				size="30" />
		</td>
	</tr>
	<tr>
		<td class="leftLabel" valign="top">
			<strong>Instrument</strong>
		</td>
		<td class="rightLabel" valign="top">
			<table id="instrumentTable" class="smalltable" border="0"
				width="100%">
				
				<tbody id="instrumentRows">
					<tr id="patternHeader" class="smallTableHeader">
						<th class="greyFont">
							Manufacturer
						</th>
						<th class="greyFont">
							Model Name
						</th>
						<th class="greyFont" colspan="2">
							Type
						</th>
					</tr>
					<tr id="pattern" style="display: none;">
						<td>
							<span id="instrumentManufacturer">Manufacturer</span>
						</td>
						<td>
							<span id="instrumentModelName">ModelName</span>
						</td>
						<td>
							<span id="instrumentType">Type</span>
						</td>
						<td>
							EDIT
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td class="leftLabel" valign="top">
			<strong>&nbsp;</strong>
		</td>
		<td class="rightLabel" valign="top">
			<table>
				<tr>
					<td>
						<html:hidden property="achar.theInstrument.id"
							styleId="newInstrumentId" />
						<html:select property="achar.theInstrument.manufacturer"
							styleId="newInstrumentManufacturer"
							onchange="javascript:callPrompt('Manufacturer', 'instrumentManufacturer${instrumentInd}');">
							<option value=""></option>
							<html:options name="allManufacturers" />
							<option value="other">
								[Other]
							</option>
						</html:select>
					</td>
					<td>
						<html:text property="achar.theInstrument.modelName" size="17"
							styleId="newInstrumentModelName" />
					</td>
					<td>
						<html:select property="achar.theInstrument.type"
							styleId="newInstrumentType"
							onchange="javascript:callPrompt('Instrment Type', 'instrumentType${instrumentInd}');">
							<option value=""></option>
							<option value="other">
								[Other]
							</option>
						</html:select>
					</td>
					<td>
						<a href="javascript:addInstrument();"> <span class="addLink">Add
								Row</span></a>&nbsp;
					</td>
				</tr>
			</table>
		</td>
	</tr>


	<tr>
		<td class="leftLabelNoBottom" valign="top">
			<strong>Description</strong>
		</td>
		<td class="rightLabelNoBottom">
			<html:textarea styleId="configDescription"
				property="achar.theExperimentConfig.domain.description" rows="3"
				cols="95" />
			<br>
		</td>
	</tr>
	<tr>
		<td class="leftLabel" valign="top">
			<input type="button" value="Delete"
				onclick="javascript:submitAction(document.forms[0],
										'${actionName}', 'deleteExperimentConfig');">
		</td>
		<td class="rightLabel" align="right">
			<div align="right">
				<input type="reset" value="Cancel"
					onclick="javascript:resetTheExperimentConfig(false);">
				<input type="reset" value="Reset"
					onclick="javascript:window.location.href='${origUrl}'">
				<input type="button" value="Save"
					onclick="javascript:submitAction(document.forms[0],
										'${actionName}', 'saveExperimentConfig');">
			</div>
		</td>
	</tr>
	<html:hidden styleId="configId"
		property="achar.theExperimentConfig.domain.id" />

</table>