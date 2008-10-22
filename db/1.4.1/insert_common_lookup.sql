USE canano;

ALTER TABLE canano.common_lookup
 CHANGE common_lookup_pk_id common_lookup_pk_id BIGINT(20) AUTO_INCREMENT NOT NULL;

insert into `common_lookup`(`name`,`attribute`,`value`) values ('Attachment','bondType','covalent');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Attachment','bondType','electrostatic');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Attachment','bondType','hydrogen');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Attachment','bondType','ionic');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Attachment','bondType','van der Waals');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Cytotoxicity','cellDeathMethod','apoptosis');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Cytotoxicity','cellDeathMethod','necrosis');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Cytotoxicity','cellLine','human hepatocarinoma');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Cytotoxicity','cellLine','porcine renal tubule');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Emulsion','composingElementType','bulk phase');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Emulsion','composingElementType','dispersed phase');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Emulsion','composingElementType','emulsifier');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','concentrationUnit','g/ml');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','concentrationUnit','mg/ml');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','concentrationUnit','ug/ml');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','concentrationUnit','ug/ul');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CellViability','derivedDatumName','LC50');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('MolecularWeight','derivedDatumName','molecular weight');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Size','derivedDatumName','PDI');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Size','derivedDatumName','peak1');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Size','derivedDatumName','RMS-size');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Size','derivedDatumName','Z-average');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Surface','derivedDatumName','charge');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Surface','derivedDatumName','surface area');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Surface','derivedDatumName','zeta potential');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','displayName','antibody');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','displayName','antigen');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Attachment','displayName','attachment');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Biopolymer','displayName','biopolymer');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('BloodContact','displayName','Blood Contact');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CarbonNanotube','displayName','carbon nanotube');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Caspase3Activation','displayName','Caspase 3 Activation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CellViability','displayName','Cell Viability');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CFU_GM','displayName','CFU GM');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Chemotaxis','displayName','Chemotaxis');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Coagulation','displayName','Coagulation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComplementActivation','displayName','Complement Activation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CytokineInduction','displayName','Cytokine Induction');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Cytotoxicity','displayName','Cytotoxicity');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Dendrimer','displayName','dendrimer');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Emulsion','displayName','emulsion');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Encapsulation','displayName','encapsulation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('EnzymeInduction','displayName','Enzyme Induction');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Fullerene','displayName','fullerene');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Gene','displayName','gene');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Hemolysis','displayName','Hemolysis');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','displayName','imaging');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImmuneCellFunction','displayName','Immune Cell Function');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Immunotoxicity','displayName','Immunotoxicity');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('InvitroCharacterization','displayName','In Vitro Characterization');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('LeukocyteProliferation','displayName','Leukocyte Proliferation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Liposome','displayName','liposome');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('MetalParticle','displayName','metal particle');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('MolecularWeight','displayName','Molecular Weight');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('NKCellCytotoxicActivity','displayName','NK Cell Cytotoxic Activity');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('OxidativeBurst','displayName','Oxidative Burst');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('OxidativeStress','displayName','Oxidative Stress');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Phagocytosis','displayName','Phagocytosis');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalCharacterization','displayName','Physical Characterization');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','displayName','Physical State');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PlasmaProteinBinding','displayName','Plasma Protein Binding');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PlateletAggregation','displayName','Platelet Aggregation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Polymer','displayName','polymer');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Purity','displayName','Purity');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('QuantumDot','displayName','quantum dot');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Receptor','displayName','receptor');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','displayName','Shape');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Size','displayName','Size');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SmallMolecule','displayName','small molecule');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Solubility','displayName','Solubility');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Surface','displayName','Surface');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('TargetingFunction','displayName','targeting');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('TherapeuticFunction','displayName','therapeutic');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Toxicity','displayName','Toxicity');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','isotype','IgA');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','isotype','IgD');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','isotype','IgE');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','isotype','IgG');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','isotype','IgM');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','bioluminescence');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','flurorescence');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','infrared');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','MRI');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','neutron scattering');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','PET');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','Raman spectroscopy');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','SPECT');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','ultrasound');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ImagingFunction','modality','X-ray');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','molecularFormulaType','SMARTS');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','molecularFormulaType','SMILES');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('FunctionalizingEntity','molecularFormulaType','SMARTS');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('FunctionalizingEntity','molecularFormulaType','SMILES');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SurfaceChemistry','molecularFormulaType','SMARTS');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SurfaceChemistry','molecularFormulaType','SMILES');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','quantityUnit','g');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','quantityUnit','mg');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','quantityUnit','ug');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Solubility','solvent','alcohol');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Solubility','solvent','phospate-buffered saline');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Solubility','solvent','saline');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Solubility','solvent','water');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','cat');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','cattle');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','dog');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','goat');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','guinea pig');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','hamster');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','horse');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','human');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','mouse');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','pig');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','rat');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','sheep');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','yeast');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','species','zebrafish');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','cat');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','cattle');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','dog');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','goat');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','guinea pig');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','hamster');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','horse');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','human');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','mouse');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','pig');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','rat');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','sheep');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','yeast');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antigen','species','zebrafish');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ActivationMethod','type','enzymatic cleavage');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ActivationMethod','type','infrared');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ActivationMethod','type','MRI');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ActivationMethod','type','NMR');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ActivationMethod','type','pH');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ActivationMethod','type','ultrasound');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ActivationMethod','type','ultraviolet');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','type','Fab');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','type','ScFv');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Antibody','type','whole');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Biopolymer','type','DNA');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Biopolymer','type','peptide');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Biopolymer','type','protein');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Biopolymer','type','RNA');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','type','coat');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','type','core');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','type','modifier');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','type','monomer');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','type','lipid');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','type','repeat unit');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','type','shell');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','type','terminal group');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('LabFile','type','document');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('LabFile','type','graph');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('LabFile','type','image');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('LabFile','type','spread sheet');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','colloid-emulsion');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','colloid-gel');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','colloid-sol');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','fluid-gas');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','fluid-liquid');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','fluid-vapor');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','solid-crystal');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','solid-glass');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','solid-granule');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','solid-powder');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PhysicalState','type','solid-fibril');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Protocol','type','in vitro assay');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Protocol','type','in vivo assay');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Protocol','type','physical assay');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Protocol','type','radio labeling');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Protocol','type','safety');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Protocol','type','sample preparation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Protocol','type','synthesis');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-circle');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-diamond');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-ellipse');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-parallelogram');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-polygon');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-retangle');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-square');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-trapezoid');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','2D-triangle');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-cone');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-cube');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-cylinder');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-disc');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-fibril');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-hexahedron');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-needle');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-oblate spheroid');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-polyhedron');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-prolate spheroid');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-rod');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-sphere');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-tetrahedron');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Shape','type','3D-tetrapod');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('StorageElement','type','box');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('StorageElement','type','freezer');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('StorageElement','type','room');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('StorageElement','type','shelf');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('charge','unit','a.u.');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('charge','unit','aC');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('charge','unit','Ah');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('charge','unit','C');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('charge','unit','esu');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('charge','unit','Fr');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('charge','unit','statC');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('molecular weight','unit','kDa');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('peak1','unit','nm');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('RMS-size','unit','nm');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('surface area','unit','nm^2');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Z-average','unit','nm');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('zeta potential','unit','mV');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('DerivedDatum','valueType','boolean');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('DerivedDatum','valueType','mean');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('DerivedDatum','valueType','median');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('DerivedDatum','valueType','mode');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('DerivedDatum','valueType','observed');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('DerivedDatum','valueType','standard deviation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','valueUnit','%');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','valueUnit','%mole');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','valueUnit','%vol');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ComposingElement','valueUnit','%wt/vol');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('FunctionalizingEntity','valueUnit','%');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('FunctionalizingEntity','valueUnit','%mole');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('FunctionalizingEntity','valueUnit','%vol');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('FunctionalizingEntity','valueUnit','%wt/vol');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','volumeUnit','ml');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('SampleContainer','volumeUnit','ul');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CarbonNanotube','wallType','DWNT');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CarbonNanotube','wallType','MWNT');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CarbonNanotube','wallType','SWNT');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('NanoparticleEntity','displayName','Nanoparticle Entity');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('FunctionalizingEntity','displayName','Functionalizing Entity');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('ChemicalAssociation','displayName','Chemical Association');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('LabFile','displayName','Lab File');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CarbonNanotube','diameterUnit','nm');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CarbonNanotube','averageLengthUnit','nm');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Fullerene','averageDiameterUnit','nm');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Hemolysis','derivedDatumName','is hemolytic');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('PlateletAggregation','derivedDatumName','is above threshold');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CFU_GM','derivedDatumName','number of CFU-GM colonies');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('CFU_GM','derivedDatumName','total number of bone marrow cells');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','category','peer review article');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','category','book chapter');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','category','review');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','category','editorial');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','status','published');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','status','in press');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','status','submitted');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','status','in preparation');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','researchArea','synthesis');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','researchArea','characterization');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','researchArea','cell line');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','researchArea','animal');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','researchArea','in vitro');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','researchArea','in vivo');
insert into `common_lookup`(`name`,`attribute`,`value`) values ('Publication','researchArea','clinical trials');

ALTER TABLE canano.common_lookup
 CHANGE common_lookup_pk_id common_lookup_pk_id BIGINT(20)  NOT NULL;
