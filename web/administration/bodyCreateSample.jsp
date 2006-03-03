<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<script type="text/javascript" src="javascript/calendar2.js"></script>		  
<form name="createSample">
<tr>
   <td valign="top" width="100%">
   <!-- target of anchor to skip menus -->
   <table height="100%" cellspacing="0" cellpadding="0" width="600" summary="" border="0">
   <!-- banner begins --><!-- banner begins -->
   <tbody>
     <tr>
       <td height="100%">
         <table class="contentPage" cellspacing="0" cellpadding="0" width="600" summary="" border="0">
           <tbody>
             <tr>
               <td valign="top" width="100%">
                 <table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                   <tbody>
                     <tr>
                       <td class="contentPage">
                         <h2><br>
                         Create Sample</h2>

                         <blockquote>
                           <table class="topBorderOnly" cellspacing="0" cellpadding="3" width="90%" align="center" summary="" border="0">
                             <tbody>
                               <tr class="topBorder">
                                 <td class="dataTablePrimaryLabel">
                                   <div align="justify">
                                     <em>DESCRIPTION</em>
                                   </div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabel">
                                   <div align="justify">
                                     <strong>Sample ID* <span class="formField"><span class="formFieldWhite"><input size="10" value="NCL6" name="Input22"></span></span>
                                     &nbsp; &nbsp; Type <span class="formFieldWhite"><select name="select12">
                                       <option value="sample type1" selected>
                                         sample type 1
                                       </option>

                                       <option value="baseline">
                                         baseline
                                       </option>

                                       <option value="bp1">
                                         Dendirmer
                                       </option>

                                       </select></span> &nbsp; &nbsp; SOP <span class="formFieldWhite"><select name="select7">
                                       <option value="bp1" selected>
                                         sample procedure 1
                                       </option>

                                       <option value="baseline">
                                         &nbsp;
                                       </option>

                                       <option value="baseline">
                                         &nbsp;
                                       </option>

                                       <option value="bp2">
                                         &nbsp;
                                       </option>

                                       <option value="bp3">
                                         &nbsp;
                                       </option>

                                       <option value="bp4">
                                         &nbsp;
                                       </option>
                                     </select></span></strong>
                                   </div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabelWhite style1">
                                   <div align="justify">
                                     Description <span class="formField"><span class="formFieldWhite"><input size="40" value="sample description 1" name=
                                     "Input222"></span></span>
                                   </div>

                                   <div align="justify"></div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabel">
                                   <div align="justify">
                                     <strong>Source <span class="formFieldWhite"><select name="select5">
                                       <option value="baseline" selected>
                                         vendorX
                                       </option>

                                       <option value="baseline">
                                         vendorY
                                       </option>

                                       <option value="bp1">
                                    
vendorZ
                                       </option>
                                     </select></span> &nbsp; &nbsp; Source ID <span class="formFieldWhite"><select name="select6">
                                       <option value="baseline" selected>
                                         vendorX123
                                       </option>

                                       <option value="baseline">
                                         &nbsp;
                                       </option>

                                       <option value="bp1">
                                         &nbsp;
                                       </option>

                                       <option value="bp2">
                                         &nbsp;
                                       </option>

                                       <option value="bp3">
                                         &nbsp;
                                       </option>

                                       <option value="bp4">
                                         &nbsp;
                                       </option>
                                     </select></span> &nbsp; &nbsp;</strong>
                                   </div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabelWhite">
                                   <div align="justify">
                                     <strong>Date Recieved <input size="10" name="Input22628"> <span class="formFieldWhite">
															  				
															  <a href="javascript:cal.popup();"><img height="18" src=
                                     "images/calendar-icon.gif" width="22" border="0" alt="Click Here to Pick up the date"></a></span>&nbsp; &nbsp; Solubility <span class="formFieldWhite"><input size="30" value=
                                     "solubility text for the sample" name="Input226222"></span> &nbsp;</strong>
                                   </div>
                                 </td>
                               </tr>
														
                               <tr>
                                 <td class="formLabel">
                                   <div align="justify">
                                     <strong>Lot ID*&nbsp; <input size="8" value="7105" name="Input2262"> &nbsp; &nbsp; &nbsp; Lot Description <span class=
                                     "formFieldWhite"><input size="15" value="lot description" name="Input22622"></span> &nbsp; &nbsp;
                                     &nbsp;</strong> <strong>Number of Containers* <span class="formFieldWhite"><input size="3" value="3" name="Input226223">
                                     &nbsp;</span></strong>
                                   </div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabelWhite">
                                   <div align="left">
                                     <strong>General Comments</strong><br>
                                     <br>
                                     <span class="formField"><span class="formFieldWhite">
                                     <textarea name="textarea" cols="70">
general comments about the sample
</textarea></span></span>
                                   </div>
                                 </td>
                               </tr>
                             </tbody>
                           </table><br>

                           <table class="topBorderOnly" cellspacing="0" cellpadding="3" width="90%" align="center" summary="" border="0">
                             <tbody>
                               <tr class="topBorder">
                                 <td class="dataTablePrimaryLabel" width="30%">
                                   <div align="justify">
                                     <em>CONTAINER 1</em>
                                   </div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabel">
                                   <div align="justify">
                                     <strong>Container Type* <span class="formFieldWhite"><select name="select">
                                       <option value="bp1" selected>
                                         Tube
                                       </option>

                                       <option value="bp2">
                                         Vial
                                       </option>

                                       <option value="bp3">
                                         Other
                                       </option>
                                     </select></span> &nbsp; &nbsp; &nbsp;Other <span class="formFieldWhite"><input size="10" name="Input226224"></span> &nbsp; &nbsp;
                                     &nbsp;</strong>
                                   </div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabelWhite style1">
                                   <div align="left">
                                     Quantity <strong><span class="formFieldWhite"><input size="4" value="10" name="Input2262232"></span></strong> <strong><span class=
                                     "formFieldWhite"><select name="select8">
                                       <option value="baseline">
                                         &nbsp;
                                       </option>

                                       <html>
<option value="baseline">
                                         &nbsp;
                                       </option>

                                       <option value="bp1" selected>
                                         &nbsp; mg
                                       </option>

                                       <option value="bp2">
                                         &nbsp;
                                       </option>

                                       <option value="bp3">
                                         &nbsp;
                                       </option>

                                       <option value="bp4">
                                         &nbsp;
                                       </option>
                                     </select></span></strong> &nbsp; Concentration <strong><span class="formFieldWhite"><input size="4" value="2" name=
                                     "Input2262233"></span></strong> <strong><span class="formFieldWhite"><select name="select9">
                                       <option value="baseline">
                                         &nbsp;
                                       </option>

                                       <option value="baseline">
                                         &nbsp;
                                       </option>

                                       <option value="bp1" selected>
                                         &nbsp; mg/ml
                                       </option>

                                       <option value="bp2">
                                         &nbsp;
                                       </option>

                                       <option value="bp3">
                                         &nbsp;
                                       </option>

                                       <option value="bp4">
                                         &nbsp;
                                       </option>
                                     </select></span></strong> &nbsp; Volume <strong><span class="formFieldWhite"><input size="4" value="5" name=
                                     "Input2262234"></span></strong> <strong><span class="formFieldWhite"><select name="select10">
                                       <option value="baseline">
                                         &nbsp;
                                       </option>

                                       <option value="baseline">
                                         &nbsp;
                                       </option>

                                       <option value="bp1" selected>
                                         &nbsp; ml
                                       </option>

                                       <option value="bp2">
                                         &nbsp;
                                       </option>

                                       <option value="bp3">
                                         &nbsp;
                                       </option>

                                       <option value="bp4">
                                         &nbsp;
                                       </option>
                                     </select></span></strong> &nbsp;&nbsp;&nbsp;
                                   </div>

                                   <div align="justify"></div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabel">
                                   <div align="justify">
                                     <strong>Diluents/Solvent <input size="10" value="solvent1" name="Input22626"> &nbsp; &nbsp; &nbsp; Safety Precautions <input value=
                                     "safety precautions text" name="Input22627"> &nbsp; &nbsp; &nbsp;</strong>
                                   </div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabelWhite style1">
                                   <div align="justify">
                                     <strong>Storage Conditions <span class="formField"><span class="formFieldWhite"><input size="50" value="storage conditions text" name=
                                     "Input222232"></span></span></strong>
                                   </div>

                                   <div align="justify"></div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabel">
                                   <div align="left">
                                     <strong>Storage Location<br>
                                     <br>
                                     Room&nbsp; <select name="select4">
                                       <option value="baseline" selected>
                                         117
                                       </option>

                                       <option value="baseline">
                                         118
                                       </option>

                                       <option value="bp1">
                                         119
                                       </option>
                                     </select> &nbsp; Freezer&nbsp;<select name="select4">
                                       <option value="baseline">
                                         1
                                       </option>

                                       <option value="baselin<head>
e">
                                         2
                                       </option>

                                       <option value="bp1" selected>
                                         3
                                       </option>

                                       <option value="bp2">
                                         &nbsp;
                                       </option>
                                     </select> &nbsp; Shelf &nbsp; <input size="8" value="A" name="Input22632"> &nbsp; Box &nbsp; <input size="8" value="1" name="Input22722">
                                     &nbsp;</strong>
                                   </div>
                                 </td>
                               </tr>

                               <tr>
                                 <td class="formLabelWhite">
                                   <div align="left">
                                     <strong>Comments</strong><br>
                                     <br>
                                     <span class="formField"><span class="formFieldWhite">
                                     <textarea name="Input222222" cols="70">
Comments about container 1
</textarea></span></span><br>
                                   </div>
                                 </td>
                               </tr>
                             </tbody>
                           </table><br>

                           <table class="topBorderOnly" cellspacing="0" cellpadding="3" width="90%" align="center" summary="" border="0">
                             <tbody>
                               <tr>
                                 <td width="30%">
                                   Accessioned by: Jane Doe Accession Date: 02/06/2006<br>
                                   <br>

                                   <table height="32" cellspacing="0" cellpadding="4" width="200" align="right" border="0">
                                     <tbody>
                                       <tr>
                                         <td width="198" height="32">
                                           <div align="right">
																	  <input onclick="javascript:location.href='createSampleReset.html';" type="button" value="Clear" name="Submit2222">
                                             <input onclick="javascript:location.href='createSampleSuccess.html';" type="button" value="Submit" name="Submit2223"> 
                                           </div>
                                         </td>
                                       </tr>
                                     </tbody>
                                   </table>

                                   <div align="right"></div>
                                 </td>
                               </tr>
                             </tbody>
                           </table>

                           <p>&nbsp;</p>

                           <p>&nbsp;</p>

                           <p>&nbsp;</p>
                         </blockquote>
                       </td>
                       </tr>
                   </tbody>
                 </table>
               </td>
             </tr>
           </tbody>
         </table>
       </td>
     </tr>
   </tbody>
 </table>
    </td>
  </tr><!--_____ main content ends _____-->
					  </form>
					  <script language="JavaScript">
					  <!-- //
					  var cal = new calendar2(document.forms['createSample'].elements['Input22628']);
	            	  cal.year_scroll = true;
				      cal.time_comp = false;
  				      //-->
					  </script>
