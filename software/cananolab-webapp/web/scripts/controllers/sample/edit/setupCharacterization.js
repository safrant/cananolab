'use strict';
var app = angular.module('angularApp')
	.controller('SetupCharacterizationCtrl', function ($scope,$http,$modal,sampleService,$location,$anchorScroll,$filter,$upload,$timeout) {

    // define variables //
    $scope.sampleData = sampleService.sampleData;
    $scope.sampleId = sampleService.sampleId.data;
    $scope.domainFileUri = "";
    $scope.data = {};  
    $scope.PE = {};
    // can remove this after done testing local data
    $scope.dataCopy = angular.copy($scope.data);
    $scope.type = sampleService.type.data;
    $scope.isEdit = sampleService.isEdit.data;
    $scope.charId = sampleService.charId.data;
    $scope.charClassName = sampleService.charClassName.data;
    $scope.techniqueInstrument = {};
    $scope.loader = true;
    $scope.loaderMessage = "Loading";
    
    /* File Variables */
    $scope.usingFlash = FileAPI && FileAPI.upload != null;
    $scope.fileReaderSupported = window.FileReader != null && (window.FileAPI == null || FileAPI.html5 != false);
    $scope.fileForm = {};
    $scope.fileForm.uriExternal = false;
    $scope.externalUrlEnabled = false;
    $scope.addNewFile = false;
    $scope.selectedFileName = '';    

    // $scope.data =  [{"type":"physico-chemical characterization","charsByAssayType":{"molecular weight":[{"charId":21867776,"charType":"physico-chemical characterization","charClassName":"MolecularWeight","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"molecular weight"},{"name":"Point of Contact","value":"DC"},{"name":"Characterization Date","value":"09/10/2014"},{"name":"Protocol","value":"NIST - NCL Joint Assay Protocol, PCC-9 (PCC-9), version 1.1"},{"name":"Design Description","value":"N/A"},{"name":"Experiment Configurations","value":[{"Technique":["matrix assisted laser desorption ionisation - time of flight(MALDI-TOF)","asymmetrical flow field-flow fractionation with multi-angle laser light scattering"]},{"Instruments":["asdsadsd (Aerotech, asdsd)","control module (ACT GmbH, NONO)"]},{"Description":["",""]}]},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"","fileId":21900544,"description":"Mass spectra for (A) NCL 22 and (B) NCL23. The theoretical molecular weight of NCL22 is 26.28 kDa. The actual/experimental result based on MS was 22 kDa for both samples. The experimental details are as follows: DHB matrix, 10 mg/mL. CH CN/H O = 3/7 (v/v). Molecular weight spectra obtained by MALDI-TOF, waith a major peak at 22 kDa and minor peaks centered around 43 kDa and 64 kDa, are consistent with the information provided by DNT for G4.5 NaCOO dendrimer samples NCL22 and NCL23. Incorporating Magnevist did not change the spectrum of NCL23."}],"Data and Conditions":[{"name":"colTitles","value":["molecular weight<br>(observed,kDa)"]},{"name":"colValues","value":["22.0"]}]},{"Data and Conditions":[{"name":"colTitles","value":["molecular weight"]},{"name":"colValues","value":["4.0"]}]}]},{"name":"Analysis and Conclusion","value":"Association with Magnevist did not change the spectrum of NCL23."}]}],"other_pc":[{"charId":87326720,"charType":"physico-chemical characterization","charClassName":"OtherCharacterization","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"N/A"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"N/A"},{"name":"Characterization Results","value":[]},{"name":"Analysis and Conclusion","value":"N/A"}]}],"physical state":[{"charId":21867779,"charType":"physico-chemical characterization","charClassName":"PhysicalState","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"physical state"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Properties","value":[{"name":"colTitles","value":["Type"]},{"name":"colValues","value":["solid-powder"]}]},{"name":"Design Description","value":"DNT 082006"},{"name":"Characterization Results","value":[]},{"name":"Analysis and Conclusion","value":"N/A"}]}],"purity":[{"charId":87457793,"charType":"physico-chemical characterization","charClassName":"Purity","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"N/A"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"N/A"},{"name":"Characterization Results","value":[{"Data and Conditions":[{"name":"colTitles","value":["amount of cyclodextrin<br>(mode,asd)","centrifugation media type<br>(boolean,asd)"]},{"name":"colValues","value":["",""]},{"name":"colValues","value":["",""]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":21867781,"charType":"physico-chemical characterization","charClassName":"Purity","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"purity"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"DNT 082006 (HPLC)"},{"name":"Experiment Configurations","value":[{"Technique":["high performance liquid chromatography(HPLC)"]},{"Instruments":[""]},{"Description":[""]}]},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 17","fileId":21900548,"description":"HPLC chromatogram for NCL23. The chromatogram and elution profile for NCL23 are shown. The retention time for the main peak is 38.0 min and corresponds to 79% +- 1% of the total area (based on peaks eluted between 36.9 and 39.9 min). The UV spectra (data not shown) for these peaks are consistent with that of NCL23 measured with a UV-Vis spectrophotometer. The nature of the broad peak at 47.1 min is unknown."}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":21867782,"charType":"physico-chemical characterization","charClassName":"Purity","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"purity"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"DNT 082006 (CE)"},{"name":"Experiment Configurations","value":[{"Technique":["capillary electrophoresis"]},{"Instruments":[""]},{"Description":[""]}]},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 22","fileId":21900549,"description":"Typical electropherogram of (A) NCL22 and (B) NCL23. Sample concentration: 0.1 mg/mL in water; capillary: 40 cm x 50 um I.D; buffer: 20 mM sodium phosphate (ph = 7.4); separation voltage: -14kV; injection pressure: 0.5 psi/20s; detector: UV (wavelength 200 nm). CE is a powerful chromatorgraphic technique that separates analytes on the basis of electrophoretic mobility differences. Mobility is determined by the mass-to-charge ratio of the analyte. CE has high separation efficiencies, high sensitivity, short run time and high automation capability. DE is extensively used to evaluate the molecular distribution of dendirmers, since the charge distibution and electrophoretic mobility often change upon dendirmer surface conjugation. Figure 22 shows that NCL22 and NCL23 have very similar electrophoretic mobilities (both are in the range of [4.3-5.2] x 10 cm V S), which indicates they have the same charge/mass ratio."}]}]},{"name":"Analysis and Conclusion","value":"NCL23 has very similar electrophoretic mobilities to NCL22, which indicates that they have the same charge/mass ratio. NCL23 is NCL22 with Magnevist."}]},{"charId":23768327,"charType":"physico-chemical characterization","charClassName":"Purity","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"purity"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"DNT 122006 UV-Vis"},{"name":"Experiment Configurations","value":[{"Technique":["spectrophotometry"]},{"Instruments":["spectrophotometer (Thermo Electron, Evolution 300)"]},{"Description":[""]}]},{"name":"Characterization Results","value":[{"Files":[{"fileId":23801100,"title":"UV-Vis spectra for the dendrimers studied.","description":"UV-Vis spectra were recorded using a Thermo Electron Evolution 300 spectrophotometer (Waltham, MA). Samples were prepared in HPLC-grade water and measured in quartz microcuvettes (b = 10 mm, QS109.004, Hellma, Plainview, NY). The UV-Vis spectra are consistent with the dendrimers in that no absorption above 230 nm was observed."}]}]},{"name":"Analysis and Conclusion","value":"The UV-Vis sprectra are consistent with the dendrimers in that no absorption above 230 nm was observed."}]}],"relaxivity":[{"charId":87457792,"charType":"physico-chemical characterization","charClassName":"Relaxivity","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"N/A"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"N/A"},{"name":"Experiment Configurations","value":[{"Technique":["atomic force microscopy"]},{"Instruments":[""]},{"Description":[""]}]},{"name":"Characterization Results","value":[]},{"name":"Analysis and Conclusion","value":"N/A"}]}],"size":[{"charId":21376286,"charType":"physico-chemical characterization","charClassName":"Size","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"size"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"The effect of size based on 25 degree Celsius and Saline solvent"},{"name":"Experiment Configurations","value":[{"Technique":["dynamic light scattering(DLS)"]},{"Instruments":["dynamic light scattering instrument (Malvern)"]},{"Description":[""]}]},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 4","fileId":21540118,"description":"Statistics graph based on size distribution by volume for NCL23 in saline at 25 degrees Celsius"}],"Data and Conditions":[{"name":"colTitles","value":["PDI<br>(observed)","size<br>(Z-average,nm)","temperature<br>(observed,Celsius)","solvent media<br>(observed)","peak1<br>(observed,nm)"]},{"name":"colValues","value":["0.235","7.4","25","saline","5.3"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":21376287,"charType":"physico-chemical characterization","charClassName":"Size","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"size"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"The effect of size based on 25 degree Celsius and PBS solvent"},{"name":"Experiment Configurations","value":[{"Technique":["dynamic light scattering(DLS)"]},{"Instruments":["dynamic light scattering instrument (Malvern)"]},{"Description":[""]}]},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 5","fileId":21540119,"description":"Statistics graph based on size distribution by volume for NCL23 in PBS at 37 degrees Celsius"}],"Data and Conditions":[{"name":"colTitles","value":["peak1<br>(observed,nm)","size<br>(Z-average,nm)","temperature<br>(observed,Celsius)","solvent media<br>(observed)","PDI<br>(observed)"]},{"name":"colValues","value":["6.1","8.4","25","PBS","0.265"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":21376288,"charType":"physico-chemical characterization","charClassName":"Size","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"size"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"The effect of size based on 37 degree Celsius and PBS solvent"},{"name":"Experiment Configurations","value":[{"Technique":["dynamic light scattering(DLS)"]},{"Instruments":["dynamic light scattering instrument (Malvern)"]},{"Description":[""]}]},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 6","fileId":21540120,"description":"Statistics graph based on size distribution by volume for NCL23 in PBS at 37 degrees Celsius"}],"Data and Conditions":[{"name":"colTitles","value":["PDI<br>(observed)","size<br>(Z-average,nm)","temperature<br>(observed,Celsius)","solvent media<br>(observed)","peak1<br>(mean,nm)"]},{"name":"colValues","value":["0.358","9.8","37","PBS","5.6"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":21376289,"charType":"physico-chemical characterization","charClassName":"Size","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"size"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"Intensity and Volume weighted graphs for NCL22 and NCL23"},{"name":"Experiment Configurations","value":[{"Technique":["dynamic light scattering(DLS)"]},{"Instruments":["dynamic light scattering instrument (Malvern)"]},{"Description":[""]}]},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 10","fileId":21540121,"description":"The intensity-weighted (A) and volume-weighted size distribution (B) plots for NCL22 and NCL23. Multiple DLS measurements of NCL22 and NCL23 at 2000 ppm (2 mg/mL) in 10 mM NaCl were averaged and presented as intensity (Figure 10, A) and volume (Figure 10, B) distributions calculated using a non-negative least squares (NNLS) fit to the inverse Laplace transform. Limited data suggest a slight increase in size occurs because of the presence of Gd-complex, but more extensive data are needed to confirm this suggestion. For particles in the sub-100 nm range, the scattered intensity exhibits a d dependence, where d is the diameter. In other words, a single 100-nm particle will scatter roughly the same amount of light as 1,000,000 particles with a diameter of 1 nm. That is why the conversion to volume from intensity indicates that the smaller mode is predominant on a volume (or number) basis, and the large mode virtually disappears. The smaller particle size peak below 10 nm is identified as the &quot;primary&quot; size."}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":23768321,"charType":"physico-chemical characterization","charClassName":"Size","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"size"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"DNT 122006 DLS"},{"name":"Experiment Configurations","value":[{"Technique":["dynamic light scattering(DLS)"]},{"Instruments":["dynamic light scattering instrument (Malvern)"]},{"Description":[""]}]},{"name":"Characterization Results","value":[{"Files":[{"fileId":23801091,"title":"Statistics graph based on size distribution by volume for NCL23 in saline at 25 Â°C.","description":"Statistics graph based on size distribution by volume for NCL23 in saline at 25 &#194;&#176;C."}],"Data and Conditions":[{"name":"colTitles","value":["average<br>(nm)","size<br>(Z-average,nm)","temperature<br>(observed,Celsius)","solvent media<br>(observed)","PDI"]},{"name":"colValues","value":["5.3","7.4","25","saline","0.235"]}]},{"Files":[{"fileId":23801092,"title":"Statistics graph based on size distribution by volume for NCL23 in PBS at 25 Â°C.","description":"Statistics graph based on size distribution by volume for NCL23 in PBS at 25 &#194;&#176;C."}],"Data and Conditions":[{"name":"colTitles","value":["average<br>(nm)","size<br>(Z-average,nm)","temperature<br>(observed,Celsius)","solvent media<br>(observed)","PDI"]},{"name":"colValues","value":["6.1","8.4","25","PBS","0.265"]}]},{"Files":[{"fileId":23801093,"title":"Statistics graph based on size distribution by volume for NCL23 in PBS at 37 Â°C.","description":"Statistics graph based on size distribution by volume for NCL23 in PBS at 37 &#194;&#176;C."}],"Data and Conditions":[{"name":"colTitles","value":["average<br>(nm)","size<br>(Z-average,nm)","temperature<br>(observed,Celsius)","solvent media<br>(observed)","PDI"]},{"name":"colValues","value":["5.6","9.8","37","PBS","0.358"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]}],"solubility":[{"charId":21867780,"charType":"physico-chemical characterization","charClassName":"Solubility","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"solubility"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Properties","value":[{"name":"colTitles","value":["Solvent","Is Soluble?","Critical Concentration"]},{"name":"colValues","value":["water","true",""]}]},{"name":"Design Description","value":"DNT 082006"},{"name":"Characterization Results","value":[]},{"name":"Analysis and Conclusion","value":"N/A"}]}],"zeta potential":[{"charId":74317824,"charType":"physico-chemical characterization","charClassName":"OtherCharacterization","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"N/A"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"N/A"},{"name":"Characterization Results","value":[{"Files":[{"fileId":74219520,"title":"findings.rdf","description":"description","keywordsString":"KEYWORD"}],"Data and Conditions":[{"name":"colTitles","value":["SomeCol<br>(mean)","ceramide concentration"]},{"name":"colValues","value":["1.0","2"]},{"name":"colValues","value":["3.0","4"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]}]},"parentSampleId":20917507},{"type":"in vitro characterization","charsByAssayType":{"blood contact":[{"charId":22424833,"charType":"in vitro characterization","charClassName":"BloodContact","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"coagulation"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-12 (ITA-12), version 1.0"},{"name":"Design Description","value":"N/A"},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"ANALYSIS OF NANOPARTICLE EFFECT ON COAGULATION - DONOR GROUP 3 (ITA&#8209;12). ","fileId":22457601,"description":"NCL22 and NCL23 at low concentrations (0.0156 and 0.25 mg/mL, respectively) and NCL22, NCL23 and NCL24 at high concentration (1mg/mL) were used to evaluate potential particle effects on blood coagulation. For each nanoparticle concentration, two independent samples were prepared and analyzed in duplicate (%CV &lt; 5%). Each bar represents the mean of duplicate results. The normal plasma standard (N) and abnormal plasma standard (ABN) were used for the instrument control. Plasma pooled from at least three donors was either untreated (Unt.) or treated with nanoparticle preparations NCL22, NCL23, and NCL24. The dotted red line indicates the clinical standard cut-off for normal coagulation time for each of the tests. The results demonstrate that, in this group of donors, neither nanoparticle test sample interfered with coagulation."}]},{"Files":[{"fileId":23801110,"title":"Analysis of nanoparticle effect on coagulation - donor group 1 (ITA&#8209;12). ","description":"NCL22, NCL23, and NCL24 at high (1mg/mL) concentrations were used to evaluate potential particle effects on the biochemical component of the blood coagulation cascade (prothrombin time [PT]; activated partial thromboplastin time [APTT]; Thrombin time and Reptilase time). For each nanoparticle, three independent samples were prepared and analyzed in duplicate (%CV &lt; 5%). Each bar represents the mean of duplicate results. Normal plasma standard (N) and abnormal plasma standard (ABN) were used for the instrument control. Plasma pooled from at least three donors was either untreated (Unt.) or treated with nanoparticle preparations NCL22, NCL23, or NCL24. The dotted red line indicates the clinical standard cut-off for normal coagulation time for each of the tests. The results demonstrate that high concentrations of each nanoparticle sample delay coagulation time of plasma derived from donor group 1 above clinically acceptable standard in APTT, thrombin time and reptilase time tests."}],"Data and Conditions":[{"name":"colTitles","value":["PT<br>(observed,Seconds)","APTT<br>(observed,Seconds)","Thrombin<br>(observed,Seconds)","Reptilase<br>(observed,Seconds)"]},{"name":"colValues","value":["13.3","35.0","25.0","24.0"]}]},{"Files":[{"fileId":23801111,"title":"Analysis of nanoparticle effect on coagulation - donor group 2 (ITA&#8209;12).","description":"NCL22 and NCL23 at low concentrations (0.0156 and 0.25 mg/mL, respectively) were used to evaluate potential particle effects on blood coagulation. For each nanoparticle concentration, three independent samples were prepared and analyzed in duplicate (%CV &lt; 5%). Each bar represents the mean of duplicate results. The normal plasma standard (N) and abnormal plasma standard (ABN) were used for the instrument control. Plasma pooled from at least three donors was either untreated (Unt.) or treated with nanoparticle preparations NCL22 or NCL23. Plasma samples exposed to high concentrations of NCL22, NCL23, and NCL24 were also included in the analysis; one sample of each nanoparticle formulation at high concentration was prepared and analyzed in duplicate (%CV &lt; 5%). Each bar represents the mean of duplicate results. The dotted red line indicates the clinical standard cut-off for normal coagulation time for each of the tests. The results demonstrate that, in this group of donors, neither nanoparticle test sample interferes with coagulation."}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":23768335,"charType":"in vitro characterization","charClassName":"BloodContact","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"complement activation"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-5 (ITA-5), version 1.0"},{"name":"Design Description","value":"N/A"},{"name":"Characterization Results","value":[{"Files":[{"fileId":23801118,"title":"Analysis of complement activation (ITA-5)","description":"Analysis of complement activation (ITA-5). NCL22, NCL23 and NCL24 were tested for their ability to activate a complement. PBS and cobra venom factor (CVF) were used as the negative and positive control, respectively. NCL22 at both concentrations and NCL23 at low concentration did not induce complement activation, evidenced by an intensity of bands A and C that was similar to that of the negative control. NCL23 and NCL24 at 1mg/mL induced complement activation, evidenced by the appearance of split product (bands B and D) that was similar to that of the positive control."}],"Data and Conditions":[{"name":"colTitles","value":["complement activation induced<br>(boolean)","sample concentration<br>(observed,mg/mL)"]},{"name":"colValues","value":["1","1"]},{"name":"colValues","value":["0",".25"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":21867796,"charType":"in vitro characterization","charClassName":"BloodContact","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"hemolysis"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-1 (ITA-1), version 1.0"},{"name":"Design Description","value":"N/A"},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 39","fileId":21900562,"description":"Analysis of nanoparticle hemolytic properties (ITA-1). NCL22 and NCL23 at either high (1 mg/mL) or low (0.0156 and 0.25 mg/mL, respectively) concentration, and NCL24 at 1 mg/mL were used to evaluate potential particle effects on the integrity of red blood cells. Three independent samples were prepared for each nanoparticle concentration and analyzed in duplicate (%CV &lt; 20). Each bar represents the mean of duplicate results. Triton X-100 was used as a positive control. PBS was used to reconstitute nanoparticle and represented the negative control. Neither nanoparticle sample revealed hemolytic properties."}],"Data and Conditions":[{"name":"colTitles","value":["is hemolytic<br>(boolean)"]},{"name":"colValues","value":["0"]}]}]},{"name":"Analysis and Conclusion","value":"Neither NCL23 or NCL22 revealed hemolytic properties. NCL23 is NCL22 with associated Magnevist."}]},{"charId":21867800,"charType":"in vitro characterization","charClassName":"BloodContact","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"platelet aggregation"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-2 (ITA-2), version 1.0"},{"name":"Design Description","value":"Nanoparticle ability to induce platelet aggregation"},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 40A&B","fileId":21900566,"description":"Analysis of nanoparticle ability to induce platelet aggregation (ITA-2). NCL22 and NCL23 at either high (1 mg/mL) or low (0.0156 and 0.25 mg/mL, respectively) concentration, and NCL24 at 1 mg/mL were used to evaluate potential particle effects on the cellular component of the blood coagulation cascade. Fore each nanoparticle concentration, three independent samples were prepared and analyzed in duplicate (%CV &lt; 20%). Each bar represents to mean of duplicate results. The results demonstrate that neither nanoparticle sample is capable of inducing platelet aggregation. Collagen and PBS were used as a positive and negative control, respectively. (B) Analysis of nanoparticle effect on collagen-induced platelet aggregation (ITA-2). NCL22 and NCL23 at either high (1 mg/mL) or low (0.0156 and 0.25 mg/mL, respectively) concentration, and NCL24 at 1 mg/mL were used to evaluate potential particle interference with platelet aggregation caused by a known activation. For each nanoparticle concentration, three independent samples were prepared and analyzed in duplication (%CV &lt; 20%). Each bar represents the mean of duplicate results. The results demonstrate the high doses of NCL23 andNCL24 enhance collagen-induced platelet aggregation, while low concentration of these particles did no disturb the process. NCL22 at both high and low doses did not interfere with collagen-induce platelet aggregation."}],"Data and Conditions":[{"name":"colTitles","value":["sample concentration<br>(observed,mg/mL)","interferes with collagen-induced platelett aggregation?<br>(boolean)","induces platelet aggregation?<br>(boolean)","% platelet aggregation<br>(mean)","% Collagen Induced Platelet Aggregation<br>(mean)"]},{"name":"colValues","value":["1","1","0","-5.67","0.706"]},{"name":"colValues","value":[".25","0","0","-9.03","26.27"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]}],"cytotoxicity":[{"charId":21867789,"charType":"in vitro characterization","charClassName":"Cytotoxicity","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"cell viability"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Properties","value":[{"name":"colTitles","value":["Cell Line"]},{"name":"colValues","value":[null]}]},{"name":"Design Description","value":"DNT 082006 LDH LLC"},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 32","fileId":21900555,"description":"LDH cytotoxicity assay in LLC-PK1 cells. Porcine renal proximal tubule cells were treated for 6, 24, and 48 h with 0.004-1.0 mg/mL of test sample. Cytotoxicity was determined at each time point as described in the LLC-PK1 Kidney Cytotoxicity Assay (GTA-1). Dashed red line indicates 0% LDH leakage."}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":21867790,"charType":"in vitro characterization","charClassName":"Cytotoxicity","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"cell viability"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Properties","value":[{"name":"colTitles","value":["Cell Line"]},{"name":"colValues","value":[null]}]},{"name":"Design Description","value":"Cell lines were treated for 6, 24, and 48 h with 0.004-1.0 mg/ML of sample"},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 33","fileId":21900556,"description":"MTT cytotoxicity assay in Hep-G2 cells. Hep-G2 cells were treated for 6, 24, and 48 h with 0.02-5.0 mg/mL of test sample. Cytotoxicity was determined at each time point as described in the HEP G2 Hepatocarcinoma Cytotoxicity Assay (GTA-2). Dashed red line indicates 100% control viability."}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":21867788,"charType":"in vitro characterization","charClassName":"Cytotoxicity","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"cell viability"},{"name":"Point of Contact","value":"BB_SH_KCI_DFCI_WCMC_BWH_MIT (weq33)"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Properties","value":[{"name":"colTitles","value":["Cell Line"]},{"name":"colValues","value":["\t\t\r\n\t\t\t\r\n\t\t\t\r\n\t\t\t\r\n\t\t\t"]}]},{"name":"Design Description","value":"Cell lines were treated for 6, 24, and 48 h with 0.004-1.0 mg/ML of sample"},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 31","fileId":21900554,"description":"MTT cytotoxicity assay in LLC-PK1 cells. Porcine renal proximal tubule cells were treated for 6, 24, and 48 h with 0.004-1.0 mg/mL of test sample. Cytotoxicity was determined at each time point as described in the LLC-PK1 Kidney Cytotoxicity Assay (GTA-1). Dashed red line indicates 100% control viability."}],"Data and Conditions":[{"name":"colTitles","value":["percent cell viability<br>(observed,%)","time<br>(observed,hours)","sample concentration<br>(observed,mg/mL)"]},{"name":"colValues","value":["122.4","0",".004"]},{"name":"colValues","value":["99.1","6",".004"]},{"name":"colValues","value":["108.4","24",".004"]},{"name":"colValues","value":["112.8","48",".004"]},{"name":"colValues","value":["96.6","0",".008"]},{"name":"colValues","value":["106.8","6",".008"]},{"name":"colValues","value":["98.1","24",",008"]},{"name":"colValues","value":["166.5","48",".008"]},{"name":"colValues","value":["124.3","0",".016"]},{"name":"colValues","value":["96.0","6",".016"]},{"name":"colValues","value":["112.4","24",".016"]},{"name":"colValues","value":["91.3","48",".016"]},{"name":"colValues","value":["114.6","0",".031"]},{"name":"colValues","value":["109.3","6",".031"]},{"name":"colValues","value":["103.6","24",".031"]},{"name":"colValues","value":["136.3","48",".031"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]}],"immune cell function":[{"charId":21867802,"charType":"in vitro characterization","charClassName":"ImmuneCellFunction","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"CFU-GM"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-3 (ITA-3), version 1.0"},{"name":"Design Description","value":"Analysis of nanoparticle toxicity to bone marrow cells"},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 41","fileId":21900568,"description":"Analysis of nanoparticle toxicity to bone marrow cells (ITA-3). NCL22 and NCL23 at either high (1 mg/mL) or low (0.0156 and 0.25 mg/mL, respectively) concentration, and NCL24 at 1 mg/mL were used to evaluate potential particle effects on the formation of granulocyte-macrophage colonies from bone marrow precursors. For each nanoparticle concentration, three independent samples were prepared and analyzed in duplicate (%CV &lt; 20%). Each bar represents to mean of duplicate results. The results demonstrate that NCL22 and a low concentration of NCL23 are not myelosuppressive, while a high concentration of NCL23 and NCL24 suppresses CFU-GM formation (p&lt;0.05). Cisplatin and PBS were used as a positive and negative control, respectively."}],"Data and Conditions":[{"name":"colTitles","value":["sample concentration<br>(observed,mg/mL)","number of CFU-GM colonies<br>(mean)"]},{"name":"colValues","value":[".25","46.0"]},{"name":"colValues","value":["1","40.0"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":23768338,"charType":"in vitro characterization","charClassName":"ImmuneCellFunction","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"chemotaxis"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-8 (ITA-8), version 1.0"},{"name":"Design Description","value":"N/A"},{"name":"Characterization Results","value":[{"Files":[{"fileId":23801121,"title":"Analysis of nanoparticle effect on chemotaxis (ITA-8)","description":"Analysis of nanoparticle effect on chemotaxis (ITA-8). NCL22, NCL23 and NCL24 did not induce chemotaxis of HL-60 macrophage-like cells. PBS and FBS were used as negative and positive controls, respectively."}],"Data and Conditions":[{"name":"colTitles","value":["% chemotaxis<br>(mean)","sample concentration<br>(observed,mg/mL)"]},{"name":"colValues","value":["1.63","1"]},{"name":"colValues","value":["1.87",".25"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":22424841,"charType":"in vitro characterization","charClassName":"ImmuneCellFunction","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"cytokine induction"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-10 (ITA-10), version 1.0"},{"name":"Design Description","value":"N/A"},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 49B","fileId":22457609,"description":"Cytokine secretion by peripheral blood mononuclear cells (PBMCs) (ITA-10) 48 hrs. PBMCs isolated from healthy donors were either untreated or treated for 48 h with bacterial LPS, LPS +IFNx, NCL22 at 0.2 mg/mL, NCL23 at 0.2 mg/mL or NCL24 at 0.2 mg/mL. Two independent samples were analyzed for each concentration (%CV &lt; 25%). Each bar represents the mean of duplicate results. Cell culture supernatants were diluted 1:5 and analyzed by flow cytometry using a CBA inflammation kit for quantitative determination of cytokines IL-10, TNF, IL-1, IL-6 and IL-8. Shown are concentrations measured in individual samples after dilution. None of the nanoparticle formulations resulted in cytokine induction."}],"Data and Conditions":[{"name":"colTitles","value":["IL10<br>(observed,pg/mL)","IL1B<br>(observed,pg/mL)","IL8<br>(observed,pg/mL)","IL6<br>(observed,pg/mL)","TNFA<br>(mode,pg/mL)"]},{"name":"colValues","value":["2.7","2.7","608.4","9.7","2.7"]}]},{"Files":[{"fileId":23801127,"title":"Cytokine secretion by peripheral blood mononuclear cells (PBMCs) (ITA-10) 24 hrs.","description":"Cytokine secretion by peripheral blood mononuclear cells (PBMCs) (ITA&amp;#8209;10) 24 hrs. PBMCs isolated from healthy donors were either untreated or treated for 24 h with bacterial lipopolysaccharide (LPS), LPS +IFNg, NCL22 at 0.2 mg/mL, NCL23 at 0.2 mg/mL or NCL24 at 0.2 mg/mL. Two independent samples were analyzed for each concentration (%CV &lt; 25%). Each bar represents the mean of duplicate results. Cell culture supernatants were diluted 1:5 and analyzed by flow cytometry using a Cytometric Bead Array (CBA) inflammation kit for quantitative determination of cytokines IL-10, TNF, IL-1, IL-6 and IL-8. Shown are concentrations measured in individual samples after dilution. None of the nanoparticle formulations resulted in cytokine induction."}],"Data and Conditions":[{"name":"colTitles","value":["IL10<br>(observed,pg/mL)","IL1B<br>(observed,pg/mL)","IL8<br>(observed,pg/mL)","IL6<br>(observed,pg/mL)","TNFA<br>(observed,pg/mL)"]},{"name":"colValues","value":["5.7","6.2","562.6","22.1","5.7"]}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":22424845,"charType":"in vitro characterization","charClassName":"ImmuneCellFunction","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"cytotoxic activity of NK cells"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"N/A"},{"name":"Design Description","value":"DNT 082006 "},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig50C","fileId":22457613,"description":"Analysis of cytotoxic activity of NK cells by RT-CES. The NK cell line NK92 (source ATCC) and tumor cell line HepG2 (source DTP) were used as effectors and targets, respectively. The effector and target cells were co-cultured at an effector-to-target (E:T) ratio of 1:5 untreated or in the presence of NCL22, NCL23, or NCL24 at a concentration of 0.2 mg/mL. Cell viability was continuously monitored in real time for 48 h. The percentage of cytoxicty was calculated by comparing the AIC values of untreated cells or of cells co-cultured in the presence of nanoparticles with that of the targets growth curve. Results from duplicate samples are shown (%CV &lt; 20%). Each bar represents the mean of duplicate results. NCL22, NCL23, and NCL24 did not interfere with the cytotoxicity of NK cells towards tumor targets."}]},{"Files":[{"fileId":23801128,"title":"Analysis of cytotoxic activity of NK cells by 51Cr-release Assay (ITA-11A).","description":"Analysis of cytotoxic activity of NK cells by 51Cr-release Assay (ITA-11A). The NK cell line NK92 (source Laboratory of Experimental Immunology [LEI]) and tumor cell line K562 (source Developmental Therapeutics Program [DTP]) were used as effectors and targets, respectively. The effector and 51Cr-loaded target cells were co-cultured at different effector-to-target (E:T) ratios without, or in the presence of, NCL22, NCL23, and NCL24. For each concentration, two independent samples were prepared and analyzed in triplicate (%CV &lt; 20%). Each data point represents the mean of triplicate results. Additional samples were included to control for nanoparticle-induced chromium release from target cells. NCL22 at both concentrations and NCL23 at a low concentration did not interfere with the cytotoxicity of NK cells, while high doses of NCL23 and NCL24 slightly inhibited the cytotoxicity of NK cells towards K562 targets."}]},{"Files":[{"fileId":23801129,"title":"Analysis of cytotoxic activity of NK cells by (RT-CES).","description":"Analysis of cytotoxic activity of NK cells by (RT-CES). The NK cell line NK92 (source American Type Culture Collection [ATCC]) and tumor cell line HepG2 (source DTP) were used as effectors and targets, respectively. Effector and target cells were co-cultured at an effector-to-target (E:T) ratio of 1:5 without, or in the presence of, NCL22, NCL23, and NCL24 (%CV &lt; 20%). Additional samples were included to control for nanoparticle-associated toxicity to target cells. Cell viability was continuously monitored in real time for 48 h. Data were collected every 30 min during the first 22 h, every 2 min from 22 to 25 h, and every 10 min from 26 to 48 h. Nanoparticles did not interfere with the instrument detection system and were not toxic to tumor targets. NCL22, NCL23, and NCL24 did not interfere with cytotoxicity of NK cells towards tumor targets. NCL22 and NCL24 slightly inhibited the viability of effector NK92 cells."}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":23768332,"charType":"in vitro characterization","charClassName":"ImmuneCellFunction","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"leukocyte proliferation"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-6 (ITA-6), version 1.0"},{"name":"Design Description","value":"DNT 122006 "},{"name":"Characterization Results","value":[{"Files":[{"fileId":23801115,"title":"Analysis of nanoparticle effect on leuckocyte proliferation (ITA-6).","description":"Analysis of nanoparticle effect on leuckocyte proliferation (ITA-6). NCL22, NCL23, and NCL24 did not induce leukocyte proliferation. Phytohemaglutinin-M (PHM) was used as a positive control for proliferation induction. For each nanoparticle concentration, three independent samples were prepared and analyzed in duplicate (%CV &lt; 25%). Each bar represents the mean of duplicate results. NCL22 did not suppress proliferation induced by PHA-M, while NCL23 at 1mg/mL suppressed PHA-M&#195;&#8218;&#226;&#8364;&#8220;induced proliferation. PBS was used as a negative control."}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]},{"charId":22424839,"charType":"in vitro characterization","charClassName":"ImmuneCellFunction","parentSampleId":20917507,"displayableItems":[{"name":"Assay Type","value":"phagocytosis"},{"name":"Point of Contact","value":"N/A"},{"name":"Characterization Date","value":"N/A"},{"name":"Protocol","value":"ITA-9 (ITA-9), version 1.0"},{"name":"Design Description","value":"DNT 082006 "},{"name":"Characterization Results","value":[{"Files":[{"imageTitle":"August 2006 DNT NCL200612A Fig 48","fileId":22457607,"description":"Phagocytosis assay (ITA-9). NCL22 and NCL23 were analyzed at high (1 mg/mL) and low (0.0156 and 0.25 mg/mL respectively) Concentrations; NCL24 was analyzed at 1 mg/mL. For each concentration, three independent samples were prepared and analyzed in duplicate (%CV &lt; 25%). Each bar represents the mean of duplicate results. Zymosan A was used as positive control. NCL22, NCL23, and NCL24 were not phagocytosed by macrophages. NCL22 did no affect phagocytic uptake of Zymosan A, while NCL23 and NCL24 suppressed phagocytosis of Zymosan A. All particles were tested at a concentration of 1 mg/mL."}]}]},{"name":"Analysis and Conclusion","value":"N/A"}]}]},"parentSampleId":20917507},{"type":"in vivo characterization","charsByAssayType":{},"parentSampleId":20917507},{"type":"Canano_char_type","charsByAssayType":{},"parentSampleId":20917507},{"type":"SY-New-Char-Type","charsByAssayType":{},"parentSampleId":20917507},{"type":"TEST111","charsByAssayType":{},"parentSampleId":20917507},{"type":"ex vivo","charsByAssayType":{},"parentSampleId":20917507},{"type":"other","charsByAssayType":{},"parentSampleId":20917507}] ;
    $scope.fileTypes = ["document","graph","image","movie","spread sheet"];


    // scrolls to section on page with provided id //
    $scope.scroll = function(id) {
        var old = $location.hash();
        $location.hash(id);
        $anchorScroll();
        $location.hash(old);
    };

    // popup calendar functions //
    $scope.setupCalendar = function() {
        $scope.today = function() {
            $scope.data.characterizationDate = new Date();
        };
        $scope.clear = function () {
            $scope.data.characterizationDate = null;
        };
        $scope.toggleMin = function() {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        // $scope.toggleMin();
        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.opened = true;
        };
        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };
        $scope.initDate = new Date('2016-15-20');
        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = 'shortDate';
    }


    $scope.setupCalendar();

    // initial rest call to get master data object //
    if ($scope.isEdit) {
        // run initial rest call to setup characterization edit form //
        $scope.title = "Edit";
        $scope.saveButton = "Update";
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/setupUpdate?sampleId='+$scope.sampleId+'&charId='+$scope.charId+'&charClassName='+$scope.charClassName+'&charType='+$scope.type}).
            success(function(data, status, headers, config) {
            $scope.data = data;
            $scope.sampleName = sampleService.sampleName($scope.sampleId);

            if (!$scope.data.characterizationDate) {
                $scope.data.characterizationDate = null;
            };
            $scope.loader = false;
            $scope.dataCopy = angular.copy($scope.data);   
        }).
            error(function(data, status, headers, config) {
            $scope.loader = false;        
        });     
    }
    else {
        // run initial rest call to setup characterization add form //
        $scope.title = "Add";
        $scope.saveButton = "Submit";        
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/setupAdd?sampleId='+$scope.sampleId+'&charType='+$scope.type}).
            success(function(data, status, headers, config) {
            $scope.data = data;
            $scope.sampleName = sampleService.sampleName($scope.sampleId);

            $scope.today();
            $scope.loader = false;
            $scope.dataCopy = angular.copy($scope.data);    
        }).
            error(function(data, status, headers, config) {
            $scope.loader = false;        
        });         
    };
    if($scope.data.characterizationDate) { 
        $scope.data.characterizationDate = new Date($scope.data.characterizationDate);
    };    

    // gets characterization names when characterization type dropdown is changed //
    $scope.characterizationTypeDropdownChanged = function() {
        $scope.data.assayTypesByCharNameLookup = [];
        delete $scope.data.assayType;
        delete $scope.data.name;
        delete $scope.data.protocolId;
        delete $scope.data.characterizationSourceId;
        delete $scope.domainFileUri;

        $scope.loader = true;
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/getCharNamesByCharType?charType='+$scope.data.type}).
            success(function(data, status, headers, config) {
            $scope.data.charNamesForCurrentType = data;
            $scope.loader = false;
        }).
            error(function(data, status, headers, config) {
            $scope.loader = false;        
        });  
    };

    // gets assay types when characterization name dropdown is changed //
    $scope.characterizationNameDropdownChanged = function() {
        delete $scope.data.assayType;

        $scope.loader = true;
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/getAssayTypesByCharName?charName='+ $scope.data.name}).
            success(function(data, status, headers, config) {
            $scope.data.assayTypesByCharNameLookup = data;
            $scope.loader = false;
        }).
            error(function(data, status, headers, config) {
            $scope.loader = false;        
        }); 

        // gets property block //
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/getCharProperties?charName='+ $scope.data.name}).
            success(function(data, status, headers, config) {
            $scope.data.property = data;
        }).
            error(function(data, status, headers, config) {
            $scope.loader = false;        
        });         
    };

    // looks to see if technique type has abbreviation // 
    $scope.techniqueTypeInstrumentDropdownChanged = function() {
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/getInstrumentTypesByTechniqueType?techniqueType='+$scope.techniqueInstrument.techniqueType}).
            success(function(data, status, headers, config) {
            $scope.instrumentTypesLookup = data;
        }).
            error(function(data, status, headers, config) {
        }); 
    };

    // gets URL for protocol name //
    $scope.getDomainFileUri = function() {
        for (var x = 0; x < $scope.data.protocolLookup.length;x++) {
            if ($scope.data.protocolId==$scope.data.protocolLookup[x].domainId) {
                $scope.domainFileUri = $scope.data.protocolLookup[x].domainFileUri;
            };  
        };
    };

    // open new experiment section //
    $scope.openNewExperimentConfig = function() {
        $scope.updateExperimentConfig = 1;
        $scope.isNewExperimentConfig = 1;
        $scope.techniqueInstrument = {};
        $scope.techniqueInstrument.instruments = [];
        $scope.scroll('editTechniqueInstrumentInfo');
    };

    // open update experiment section //
    $scope.openUpdateExperimentConfig = function(item) {
        $scope.updateExperimentConfig = 1;
        $scope.isNewExperimentConfig = 0;
        $scope.techniqueInstrument = item;
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/getInstrumentTypesByTechniqueType?techniqueType='+$scope.techniqueInstrument.techniqueType}).
            success(function(data, status, headers, config) {
            $scope.instrumentTypesLookup = data;
        }).
            error(function(data, status, headers, config) {
        });         
        // $scope.instrumentTypesLookup = ["control module","guard column","multi angle light scattering detector","photometer","refractometer","separation column","separations module","spectrophotometer","other"];
        $scope.techniqueInstrumentCopy = angular.copy(item);        
        $scope.scroll('editTechniqueInstrumentInfo');
    };  

    // save experiment config and close section //
    $scope.saveExperimentConfig = function() {
        $scope.loader = true;
        if ($scope.isNewExperimentConfig) {
            $scope.data.techniqueInstruments.experiments.push($scope.techniqueInstrument);
        };
        $scope.techniqueInstrument.parentCharType = $scope.data.type;
        $scope.techniqueInstrument.parentCharName = $scope.data.name;
        $scope.techniqueInstrument.dirty = 1;
        $http({method: 'POST', url: '/caNanoLab/rest/characterization/saveExperimentConfig',data: $scope.data}).
        success(function(data, status, headers, config) {   
            $scope.saveButton = "Update";        
            $scope.loader = false;
            $scope.data=data;
        }).
        error(function(data, status, headers, config) {
            $scope.loader = false;
        });  
        $scope.updateExperimentConfig = 0; 
    };  

    // removes experiment config and close section //
    $scope.removeExperimentConfig = function() {
        $scope.loader = true;
        $http({method: 'POST', url: '/caNanoLab/rest/characterization/removeExperimentConfig',data: $scope.techniqueInstrument}).
        success(function(data, status, headers, config) {            
            $scope.loader = false;
            $scope.data=data;
        }).
        error(function(data, status, headers, config) {
            $scope.loader = false;
        });  
        $scope.updateExperimentConfig = 0;              
    };

    // cancel experiment config and close section //
    $scope.cancelExperimentConfig = function() {
        $scope.updateExperimentConfig = 0;
        angular.copy($scope.techniqueInstrumentCopy,$scope.techniqueInstrument);
        $scope.updateInstrument = 0;

    };        

    // open new instrument section //  
    $scope.openNewInstrument = function() {
        $scope.updateInstrument = 1;
        $scope.instrument = {"manufacturer":null,"modelName":null,"type":null};
        $scope.isNewInstrument = 1;
    };

    // open existing instrument section //  
    $scope.openExistingInstrument = function(instrument) {
        $scope.updateInstrument = 1;
        $scope.instrument = instrument;   
        $scope.isNewInstrument = 0;
        $scope.instrumentCopy = angular.copy(instrument);
        if ($scope.instrumentTypesLookup.indexOf($scope.instrument.type)==-1) {
            $scope.instrument.type="";
        };                
    };    

    // saves instrument. checks if it is new. if it is add it to techniqueInstrument //  
    $scope.saveInstrument = function(instrument) {
        if ($scope.isNewInstrument) {
            $scope.techniqueInstrument.instruments.push($scope.instrument);
        };
        $scope.updateInstrument = 0;

    };     

    // removes instrument from list //
    $scope.removeInstrument = function(instrument) {
        $scope.techniqueInstrument.instruments.splice($scope.techniqueInstrument.instruments.indexOf(instrument),1);
        $scope.updateInstrument = 0;        
    };       

    // closes instrument section, reset instrument if it exists //
    $scope.cancelInstrument = function(instrument) {
        $scope.updateInstrument = 0;
        angular.copy($scope.instrumentCopy, $scope.instrument);
    };

    // opens new finding dialog //
    $scope.addNewFinding = function() {
        var old = $location.hash();
        $scope.currentFinding = {};
        $scope.currentFinding.dirty = 1;
        $scope.updateFinding = 1;
        $scope.finding = {};
        $scope.scroll('editFindingInfo');
        $scope.isNewFinding = 1; 
        $scope.currentFindingCopy = angular.copy($scope.currentFinding);

    };

    // open finding dialog with existing finding //
    $scope.updateExistingFinding = function(finding) {
        var old = $location.hash();
        $scope.updateFinding = 1;        
        $scope.currentFinding = finding;
        $scope.scroll('editFindingInfo');
        $scope.isNewFinding = 0;  
        $scope.currentFinding.dirty = 1;
        $scope.currentFindingCopy = angular.copy(finding);
    };

    // updates rows and columns and runs rest call update //
    $scope.updateRowsColsForFinding = function() {
        $scope.loader = true;        
        $http({method: 'POST', url: '/caNanoLab/rest/characterization/updateDataConditionTable',data: $scope.currentFinding}).
        success(function(data, status, headers, config) {            
            $scope.loader = false;
            $scope.currentFinding=data;
        }).
        error(function(data, status, headers, config) {
            $scope.loader = false;
        });        
    };

    // opens column form to change properties for column //
    $scope.openColumnForm = function(cell) {
        $scope.findingsColumn = cell;
        $scope.columnForm = 1;
        $scope.findingsColumnCopy = angular.copy($scope.findingsColumn);
        if ($scope.findingsColumn.columnType) {
            $scope.onColumnTypeDropdownChange(1);
        }
    };

    // close column form without saving //
    $scope.closeColumnForm = function() {
        angular.copy($scope.findingsColumnCopy, $scope.findingsColumn);
        $scope.columnForm = 0;
    };

    // close column form with saving //
    $scope.saveColumnForm = function() {
        $scope.columnForm = 0;
        
        var columnIndex = 0;
        if( $scope.findingsColumn.columnOrder != null ) {
            columnIndex = parseInt($scope.findingsColumn.columnOrder) - 1;
            var headerName = $scope.findingsColumn.columnName;

            if( $scope.findingsColumn.valueType != null ) {
                headerName = headerName + ' (' + $scope.findingsColumn.valueType + ')';
            }
            $scope.currentFinding.columnHeaders[columnIndex].displayName = headerName;

        }
    };    

    // remove column data //
    $scope.removeColumnForm = function() {
        angular.copy($scope.findingsColumnCopy, $scope.findingsColumn);
        $scope.columnForm = 0;

    };    

    // opens column form to change order for columns. Does not actually order columns //
    $scope.openColumnOrderForm = function() {
        $scope.columnOrder = 1;
    }; 

    // called when columnType dropdown is changed on column form //
    $scope.onColumnTypeDropdownChange = function(newOpen) {
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/getColumnNameOptionsByType?columnType='+$scope.findingsColumn.columnType+'&charName='+$scope.data.name+'&assayType='+$scope.data.assayType}).
            success(function(data, status, headers, config) {
            $scope.columnNameLookup = data;
            $scope.loader = false;
        }).
            error(function(data, status, headers, config) {
            $scope.loader = false;        
        });  

        if (newOpen) {
            $scope.getColumnValueUnitOptions();
        };        
    };

    // gets column value unit options based on  name and condition //
    $scope.getColumnValueUnitOptions = function() {
       $http({method: 'GET', url: '/caNanoLab/rest/characterization/getColumnValueUnitOptions?columnName='+$scope.findingsColumn.columnName+'&conditionProperty='+$scope.findingsColumn.conditionProperty}).
            success(function(data, status, headers, config) {
            $scope.valueUnitsLookup = data;
            $scope.loader = false;
        }).
            error(function(data, status, headers, config) {
            $scope.loader = false;        
        }); 
    };

    // called when columnType dropdown is changed on column form //
    $scope.onColumnNameDropdownChange = function() {
        $scope.getColumnValueUnitOptions();
        $http({method: 'GET', url: '/caNanoLab/rest/characterization/getConditionPropertyOptions?columnName='+$scope.findingsColumn.columnName}).
            success(function(data, status, headers, config) {
            $scope.conditionTypeLookup = data;
            $scope.loader = false;
        }).
            error(function(data, status, headers, config) {
            $scope.loader = false;        
        });        
    };    

    // sets the column order //
    $scope.updateColumnOrder = function() {
        $scope.loader = true;        
        $http({method: 'POST', url: '/caNanoLab/rest/characterization/setColumnOrder',data: $scope.currentFinding}).
        success(function(data, status, headers, config) {            
            $scope.loader = false;
            $scope.currentFinding=data;
            $scope.columnOrder = 0;
        }).
        error(function(data, status, headers, config) {
            $scope.loader = false;
            $scope.columnOrder = 0;
        });         
    }; 

    // saves finding info //
    $scope.saveFindingInfo = function() {
        $scope.loader = true;
        if ($scope.isNewFinding) {
            $scope.data.finding.push($scope.currentFinding);            
        };

        for (var x=0; x<$scope.data.finding.length;x++) {
            if ($scope.data.finding[x].findingId==$scope.currentFinding.findingId) {
                $scope.data.finding.splice(x,1);
                $scope.data.finding.push($scope.currentFinding);
                break;
            }
        };

        $http({method: 'POST', url: '/caNanoLab/rest/characterization/saveFinding',data: $scope.data}).
        success(function(data, status, headers, config) { 
            $scope.saveButton = "Update";                           
            $scope.loader = false;
            $scope.data=data;
        }).
        error(function(data, status, headers, config) {
            $scope.loader = false;
        }); 
        $scope.updateFinding = 0;        
    };

    // removes finding info //
    $scope.removeFindingInfo = function() {
    	if (confirm("Delete the Finding?")) {
    		$scope.loader = true;
            $http({method: 'POST', url: '/caNanoLab/rest/characterization/removeFinding',data: $scope.currentFinding}).
            success(function(data, status, headers, config) {            
                $scope.loader = false;
                $scope.data=data;
            }).
            error(function(data, status, headers, config) {
                $scope.loader = false;
            }); 
    		$scope.updateFinding = 0;        
    	}
    };    

    $scope.cancelFindingInfo = function() {
        $scope.updateFinding = 0; 
        angular.copy($scope.currentFindingCopy, $scope.currentFinding);
    };   

    // deletes data and condition row //
    $scope.deleteDataConditionRow = function(row) {
        $scope.currentFinding.rows.splice($scope.currentFinding.rows.indexOf(row),1);
    };

    // save characterization record. //
    $scope.save = function() {
        $scope.loader = true;    
        $http({method: 'POST', url: '/caNanoLab/rest/characterization/saveCharacterization',data: $scope.data}).
        success(function(data, status, headers, config) {  
            $location.path("/editCharacterization").search({'sampleId':$scope.sampleId}).replace();
            $scope.loader = false;
        }).
        error(function(data, status, headers, config) {
            $scope.loader = false;
        }); 
    };

    // remove characterization record. //
    $scope.remove = function() {
        $scope.loader = true;
        $http({method: 'POST', url: '/caNanoLab/rest/characterization/removeCharacterization',data: $scope.data}).
        success(function(data, status, headers, config) {            
            $scope.loader = false;
            $location.path("/editCharacterization").search({'sampleId':$scope.sampleId}).replace();
        }).
        error(function(data, status, headers, config) {
            $scope.loader = false;
        }); 
    };    

    // reset form //
    $scope.reset = function() {
        $scope.data = angular.copy($scope.dataCopy);
        $scope.domainFileUri = "";
        $scope.updateExperimentConfig = 0;
    };    
    
    /* File Section */
    $scope.onFileSelect = function($files) {
        $scope.selectedFiles = [];
        $scope.selectedFiles = $files;
    };

    $scope.editFile = function(fileId) {
    	$scope.selectedFileName = ''; 
        for (var k = 0; k < $scope.currentFinding.files.length; ++k) {
            var element = $scope.currentFinding.files[k];
            if (element.id == fileId ) {
                $scope.fileForm.externalUrl = element.externalUrl;
                $scope.fileForm.uri = element.uri;
                $scope.fileForm.uriExternal = element.uriExternal;
                $scope.fileForm.type = element.type;
                $scope.fileForm.title = element.title;
                $scope.fileForm.keywordsStr = element.keywordsStr;
                $scope.fileForm.description = element.description;
                $scope.fileForm.id = element.id;
                $scope.fileForm.createdBy = element.createdBy;
                $scope.fileForm.createdDate = element.createdDate;

                $scope.addNewFile = true;
                
                
                if( $scope.fileForm.externalUrl != null && $scope.fileForm.externalUrl != '') {
                    $scope.externalUrlEnabled = true;
                    $scope.fileForm.uriExternal = 'true';
                }
                else {
                    $scope.externalUrlEnabled  = false;
                    $scope.fileForm.uriExternal = 'false';
                }

                break;
            }
        }
    }

    $scope.removeFile = function(fileId) {
        if (confirm("Are you sure you want to delete the File?")) {
            $scope.loader = true;

            for (var k = 0; k < $scope.currentFinding.files.length; ++k) {
                var element = $scope.currentFinding.files[k];
                if (element.id == $scope.fileForm.id) {
                    $scope.currentFinding.files.splice(k,1);
                }
            }
            //$scope.currentFinding.files = $scope.files;

            if( $scope.currentFinding.theFile == null ) {
                $scope.currentFinding.theFile = {};
            }

            $scope.currentFinding.theFile.externalUrl = $scope.fileForm.externalUrl;
            $scope.currentFinding.theFile.uri = $scope.fileForm.uri;
            $scope.currentFinding.theFile.uriExternal = $scope.fileForm.uriExternal;
            $scope.currentFinding.theFile.type = $scope.fileForm.type;
            $scope.currentFinding.theFile.title = $scope.fileForm.title;
            $scope.currentFinding.theFile.keywordsStr = $scope.fileForm.keywordsStr;
            $scope.currentFinding.theFile.description = $scope.fileForm.description;
            $scope.currentFinding.theFile.id = $scope.fileForm.id;
            $scope.currentFinding.theFile.theAccess = $scope.fileForm.theAccess;
            $scope.currentFinding.theFile.isPublic = $scope.fileForm.isPublic;
            $scope.currentFinding.theFile.createdBy = $scope.fileForm.createdBy;
            $scope.currentFinding.theFile.createdDate = $scope.fileForm.createdDate;

            if( $scope.sampleId != null ) {
                $scope.currentFinding.theFile.sampleId = $scope.sampleId;
            }


            $http({method: 'POST', url: '/caNanoLab/rest/characterization/removeFile',data: $scope.currentFinding}).
                success(function(data, status, headers, config) {
                	$scope.currentFinding = data;
                    $scope.addNewFile = false;
                    $scope.loader = false;
                }).
                error(function(data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    // $rootScope.sampleData = data;
                    $scope.loader = false;
                    $scope.data.errors = data;
                });
        }
    };

    $scope.saveFile = function() {
        $scope.loader = true;
        var index = 0;
        $scope.upload = [];
        if ($scope.selectedFiles != null && $scope.selectedFiles.length > 0 ) {
            $scope.selectedFileName = $scope.selectedFiles[0].name;
            $scope.upload[index] = $upload.upload({
                url: '/caNanoLab/rest/core/uploadFile',
                method: 'POST',
                headers: {'my-header': 'my-header-value'},
                data : $scope.fileForm,
                file: $scope.selectedFiles[index],
                fileFormDataName: 'myFile'
            });
            $scope.upload[index].then(function(response) {
                $timeout(function() {
                    //$scope.uploadResult.push(response.data);
                    //alert(response.data);
                    //$scope.nanoEntityForm = response.data;
                    $scope.saveFileData();
                    //$scope.loader = false;
                });
            }, function(response) {
                if (response.status > 0) {
                	$scope.data.errors = response.status + ': ' + response.data;
                    $scope.loader = false;
                }
            }, function(evt) {
                // Math.min is to fix IE which reports 200% sometimes
                // $scope.progress[index] = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
            });
            $scope.upload[index].xhr(function(xhr){
//			xhr.upload.addEventListener('abort', function() {console.log('abort complete')}, false);
            });
        }
        else {
            $scope.saveFileData();
        }
    };

    $scope.saveFileData = function() {
        $scope.loader = true;

        var k=0;
        if ( $scope.fileForm.id != null && $scope.fileForm.id != '' ) {
            for (k = 0; k < $scope.currentFinding.files.length; ++k) {
                var element = $scope.currentFinding.files[k];
                if (element.id == $scope.fileForm.id) {
                    $scope.currentFinding.files[k] = $scope.fileForm;
                }
            }
        }
        /*            else {
         $scope.files.push($scope.fileForm);
         }
         */
        //$scope.currentFinding.files = $scope.files;

        if( $scope.currentFinding.theFile == null ) {
            $scope.currentFinding.theFile = {};
        }

        $scope.currentFinding.theFile.externalUrl = $scope.fileForm.externalUrl;
        if( $scope.selectedFileName != null && $scope.selectedFileName != '' ) {
        	$scope.currentFinding.theFile.uri = $scope.selectedFileName;
        } else {
        	$scope.currentFinding.theFile.uri = $scope.fileForm.uri;
        }        
        $scope.currentFinding.theFile.uriExternal = $scope.fileForm.uriExternal;
        $scope.currentFinding.theFile.type = $scope.fileForm.type;
        $scope.currentFinding.theFile.title = $scope.fileForm.title;
        $scope.currentFinding.theFile.keywordsStr = $scope.fileForm.keywordsStr;
        $scope.currentFinding.theFile.description = $scope.fileForm.description;
        $scope.currentFinding.theFile.id = $scope.fileForm.id;
        $scope.currentFinding.theFile.theAccess = $scope.fileForm.theAccess;
        $scope.currentFinding.theFile.isPublic = $scope.fileForm.isPublic;
        $scope.currentFinding.theFile.createdBy = $scope.fileForm.createdBy;
        $scope.currentFinding.theFile.createdDate = $scope.fileForm.createdDate;

        if( $scope.fileForm.id == null || $scope.fileForm.id == '') {
            $scope.currentFinding.theFileIndex = -1;
        } else {
            $scope.currentFinding.theFileIndex = k;
        }

        if( $scope.sampleId != null ) {
            $scope.currentFinding.theFile.sampleId = $scope.sampleId;
        }

        $scope.messages = [];
        $http({method: 'POST', url: '/caNanoLab/rest/characterization/saveFile',data: $scope.currentFinding}).
            success(function(data, status, headers, config) {
                $scope.currentFinding = data;
                $scope.addNewFile = false;
                $scope.loader = false;
            }).
            error(function(data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                // $rootScope.sampleData = data;
                $scope.loader = false;
                $scope.data.errors = data;
                $scope.addNewFile = true;
            });
    };

    $scope.getAddNewFile = function() {
        return $scope.addNewFile;
    };

    $scope.closeAddNewFile = function() {
        $scope.addNewFile = false;
    };


    $scope.openAddNewFile = function() {
        $scope.addNewFile = true;
        $scope.fileForm = {};
        
        $scope.fileForm.uriExternal = 'false';
        $scope.externalUrlEnabled = false;          
    };

    /* End File Section */

 });