-- MySQL dump 10.11
--
-- Host: localhost    Database: canano
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `common_lookup`
--

DROP TABLE IF EXISTS `common_lookup`;
CREATE TABLE `common_lookup` (
  `common_lookup_pk_id` bigint(20) NOT NULL auto_increment,
  `name` varchar(200) NOT NULL,
  `attribute` varchar(200) NOT NULL,
  `value` varchar(200) NOT NULL,
  PRIMARY KEY  (`common_lookup_pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `common_lookup`
--

LOCK TABLES `common_lookup` WRITE;
/*!40000 ALTER TABLE `common_lookup` DISABLE KEYS */;
INSERT INTO `common_lookup` VALUES (1,'Attachment','bondType','covalent\r'),(2,'Attachment','bondType','electrostatic\r'),(3,'Attachment','bondType','hydrogen\r'),(4,'Attachment','bondType','ionic\r'),(5,'Attachment','bondType','van der Waals\r'),(6,'Report','category','associated file\r'),(7,'Report','category','report\r'),(8,'Cytotoxicity','cellDeathMethod','apoptosis\r'),(9,'Cytotoxicity','cellDeathMethod','necrosis\r'),(10,'Cytotoxicity','cellLine','human hepatocarinoma\r'),(11,'Cytotoxicity','cellLine','porcine renal tubule\r'),(12,'Emulsion','composingElementType','bulk phase\r'),(13,'Emulsion','composingElementType','dispersed phase\r'),(14,'Emulsion','composingElementType','emulsifier\r'),(15,'SampleContainer','concentrationUnit','g/ml\r'),(16,'SampleContainer','concentrationUnit','mg/ml\r'),(17,'SampleContainer','concentrationUnit','ug/ml\r'),(18,'SampleContainer','concentrationUnit','ug/ul\r'),(19,'CellViability','derivedDatumName','LC50\r'),(20,'MolecularWeight','derivedDatumName','molecular weight\r'),(21,'Size','derivedDatumName','PDI\r'),(22,'Size','derivedDatumName','peak1\r'),(23,'Size','derivedDatumName','RMS-size\r'),(24,'Size','derivedDatumName','Z-average\r'),(25,'Surface','derivedDatumName','charge\r'),(26,'Surface','derivedDatumName','surface area\r'),(27,'Surface','derivedDatumName','zeta potential\r'),(28,'Antibody','displayName','antibody\r'),(29,'Antigen','displayName','antigen\r'),(30,'Attachment','displayName','attachment\r'),(31,'Biopolymer','displayName','biopolymer\r'),(32,'BloodContact','displayName','Blood Contact\r'),(33,'CarbonNanotube','displayName','carbon nanotube\r'),(34,'Caspase3Activation','displayName','Caspase 3 Activation\r'),(35,'CellViability','displayName','Cell Viability\r'),(36,'CFU_GM','displayName','CFU GM\r'),(37,'Chemotaxis','displayName','Chemotaxis\r'),(38,'Coagulation','displayName','Coagulation\r'),(39,'ComplementActivation','displayName','Complement Activation\r'),(40,'CytokineInduction','displayName','Cytokine Induction\r'),(41,'Cytotoxicity','displayName','Cytotoxicity\r'),(42,'Dendrimer','displayName','Dendrimer\r'),(43,'Emulsion','displayName','Emulsion\r'),(44,'Encapsulation','displayName','encapsulation\r'),(45,'EnzymeInduction','displayName','Enzyme Induction\r'),(46,'Fullerene','displayName','Fullerene\r'),(47,'Gene','displayName','gene\r'),(48,'Hemolysis','displayName','Hemolysis\r'),(49,'ImagingFunction','displayName','imaging\r'),(50,'ImmuneCellFunction','displayName','Immune Cell Function\r'),(51,'Immunotoxicity','displayName','Immunotoxicity\r'),(52,'LeukocyteProliferation','displayName','Leukocyte Proliferation\r'),(53,'Liposome','displayName','Liposome\r'),(54,'MetalParticle','displayName','Metal Particle\r'),(55,'MolecularWeight','displayName','Molecular Weight\r'),(56,'NKCellCytotoxicActivity','displayName','NK Cell Cytotoxic Activity\r'),(57,'OxidativeBurst','displayName','Oxidative Burst\r'),(58,'OxidativeStress','displayName','Oxidative Stress\r'),(59,'Phagocytosis','displayName','Phagocytosis\r'),(60,'PhysicalState','displayName','Physical State\r'),(61,'PlasmaProteinBinding','displayName','Plasma Protein Binding\r'),(62,'PlateletAggregation','displayName','Platelet Aggregation\r'),(63,'Polymer','displayName','Polymer\r'),(64,'Purity','displayName','Purity\r'),(65,'QuantumDot','displayName','Quantum Dot\r'),(66,'Receptor','displayName','receptor\r'),(67,'Shape','displayName','Shape\r'),(68,'Size','displayName','Size\r'),(69,'SmallMolecule','displayName','small molecule\r'),(70,'Solubility','displayName','Solubility\r'),(71,'Surface','displayName','Surface\r'),(72,'TargetingFunction','displayName','targeting\r'),(73,'TherapeuticFunction','displayName','therapeutic\r'),(74,'Toxicity','displayName','Toxicity\r'),(75,'Antibody','isotype','IgA\r'),(76,'Antibody','isotype','IgD\r'),(77,'Antibody','isotype','IgE\r'),(78,'Antibody','isotype','IgG\r'),(79,'Antibody','isotype','IgM\r'),(80,'ImagingFunction','modality','bioluminescence\r'),(81,'ImagingFunction','modality','flurorescence\r'),(82,'ImagingFunction','modality','infrared\r'),(83,'ImagingFunction','modality','MRI\r'),(84,'ImagingFunction','modality','neutron scattering\r'),(85,'ImagingFunction','modality','PET\r'),(86,'ImagingFunction','modality','Raman spectroscopy\r'),(87,'ImagingFunction','modality','SPECT\r'),(88,'ImagingFunction','modality','ultrasound\r'),(89,'ImagingFunction','modality','X-ray\r'),(90,'ComposingElement','molecularFormulaType','SMARTS\r'),(91,'ComposingElement','molecularFormulaType','SMILES\r'),(92,'FunctionalizingEntity','molecularFormulaType','SMARTS\r'),(93,'FunctionalizingEntity','molecularFormulaType','SMILES\r'),(94,'SurfaceChemistry','molecularFormulaType','SMARTS\r'),(95,'SurfaceChemistry','molecularFormulaType','SMILES\r'),(96,'SampleContainer','quantityUnit','g\r'),(97,'SampleContainer','quantityUnit','mg\r'),(98,'SampleContainer','quantityUnit','ug\r'),(99,'Solubility','solvent','alcohol\r'),(100,'Solubility','solvent','phospate-buffered saline\r'),(101,'Solubility','solvent','saline\r'),(102,'Solubility','solvent','water\r'),(103,'Antibody','species','cat\r'),(104,'Antibody','species','cattle\r'),(105,'Antibody','species','dog\r'),(106,'Antibody','species','goat\r'),(107,'Antibody','species','guinea pig\r'),(108,'Antibody','species','hamster\r'),(109,'Antibody','species','horse\r'),(110,'Antibody','species','human\r'),(111,'Antibody','species','mouse\r'),(112,'Antibody','species','pig\r'),(113,'Antibody','species','rat\r'),(114,'Antibody','species','sheep\r'),(115,'Antibody','species','yeast\r'),(116,'Antibody','species','zebrafish\r'),(117,'Antigen','species','cat\r'),(118,'Antigen','species','cattle\r'),(119,'Antigen','species','dog\r'),(120,'Antigen','species','goat\r'),(121,'Antigen','species','guinea pig\r'),(122,'Antigen','species','hamster\r'),(123,'Antigen','species','horse\r'),(124,'Antigen','species','human\r'),(125,'Antigen','species','mouse\r'),(126,'Antigen','species','pig\r'),(127,'Antigen','species','rat\r'),(128,'Antigen','species','sheep\r'),(129,'Antigen','species','yeast\r'),(130,'Antigen','species','zebrafish\r'),(131,'ActivationMethod','type','enzymatic cleavage\r'),(132,'ActivationMethod','type','infrared\r'),(133,'ActivationMethod','type','MRI\r'),(134,'ActivationMethod','type','NMR\r'),(135,'ActivationMethod','type','pH\r'),(136,'ActivationMethod','type','ultrasound\r'),(137,'ActivationMethod','type','ultraviolet\r'),(138,'Antibody','type','Fab\r'),(139,'Antibody','type','ScFv\r'),(140,'Antibody','type','whole\r'),(141,'Biopolymer','type','DNA\r'),(142,'Biopolymer','type','peptide\r'),(143,'Biopolymer','type','protein\r'),(144,'Biopolymer','type','RNA\r'),(145,'ComposingElement','type','coat\r'),(146,'ComposingElement','type','core\r'),(147,'ComposingElement','type','modifier\r'),(148,'ComposingElement','type','monomer\r'),(149,'ComposingElement','type','lipid\r'),(150,'ComposingElement','type','repeat unit\r'),(151,'ComposingElement','type','shell\r'),(152,'ComposingElement','type','terminal group\r'),(153,'LabFile','type','document\r'),(154,'LabFile','type','graph\r'),(155,'LabFile','type','image\r'),(156,'LabFile','type','spread sheet\r'),(157,'PhysicalState','type','colloid-emulsion\r'),(158,'PhysicalState','type','colloid-gel\r'),(159,'PhysicalState','type','colloid-sol\r'),(160,'PhysicalState','type','fluid-gas\r'),(161,'PhysicalState','type','fluid-liquid\r'),(162,'PhysicalState','type','fluid-vapor\r'),(163,'PhysicalState','type','solid-crystal\r'),(164,'PhysicalState','type','solid-glass\r'),(165,'PhysicalState','type','solid-granule\r'),(166,'PhysicalState','type','solid-powder\r'),(167,'PhysicalState','type','solid-fibril\r'),(168,'Protocol','type','in vitro assay\r'),(169,'Protocol','type','in vivo assay\r'),(170,'Protocol','type','physical assay\r'),(171,'Protocol','type','radio labeling\r'),(172,'Protocol','type','safety\r'),(173,'Protocol','type','sample preparation\r'),(174,'Protocol','type','synthesis\r'),(175,'Shape','type','2D-circle\r'),(176,'Shape','type','2D-diamond\r'),(177,'Shape','type','2D-ellipse\r'),(178,'Shape','type','2D-parallelogram\r'),(179,'Shape','type','2D-polygon\r'),(180,'Shape','type','2D-retangle\r'),(181,'Shape','type','2D-square\r'),(182,'Shape','type','2D-trapezoid\r'),(183,'Shape','type','2D-triangle\r'),(184,'Shape','type','3D-cone\r'),(185,'Shape','type','3D-cube\r'),(186,'Shape','type','3D-cylinder\r'),(187,'Shape','type','3D-disc\r'),(188,'Shape','type','3D-fibril\r'),(189,'Shape','type','3D-hexahedron\r'),(190,'Shape','type','3D-needle\r'),(191,'Shape','type','3D-oblate spheroid\r'),(192,'Shape','type','3D-polyhedron\r'),(193,'Shape','type','3D-prolate spheroid\r'),(194,'Shape','type','3D-rod\r'),(195,'Shape','type','3D-sphere\r'),(196,'Shape','type','3D-tetrahedron\r'),(197,'Shape','type','3D-tetrapod\r'),(198,'StorageElement','type','box\r'),(199,'StorageElement','type','freezer\r'),(200,'StorageElement','type','room\r'),(201,'StorageElement','type','shelf\r'),(202,'charge','unit','a.u.\r'),(203,'charge','unit','aC\r'),(204,'charge','unit','Ah\r'),(205,'charge','unit','C\r'),(206,'charge','unit','esu\r'),(207,'charge','unit','Fr\r'),(208,'charge','unit','statC\r'),(209,'molecular weight','unit','kDa\r'),(210,'peak1','unit','nm\r'),(211,'RMS-size','unit','nm\r'),(212,'surface area','unit','nm^2\r'),(213,'Z-average','unit','nm\r'),(214,'zeta potential','unit','mV\r'),(215,'DerivedDatum','valueType','boolean\r'),(216,'DerivedDatum','valueType','mean\r'),(217,'DerivedDatum','valueType','median\r'),(218,'DerivedDatum','valueType','mode\r'),(219,'DerivedDatum','valueType','observed\r'),(220,'DerivedDatum','valueType','standard deviation\r'),(221,'ComposingElement','valueUnit','%\r'),(222,'ComposingElement','valueUnit','%mole\r'),(223,'ComposingElement','valueUnit','%vol\r'),(224,'ComposingElement','valueUnit','%wt/vol\r'),(225,'FunctionalzingEntity','valueUnit','%\r'),(226,'FunctionalzingEntity','valueUnit','%mole\r'),(227,'FunctionalzingEntity','valueUnit','%vol\r'),(228,'FunctionalzingEntity','valueUnit','%wt/vol\r'),(229,'SampleContainer','volumeUnit','ml\r'),(230,'SampleContainer','volumeUnit','ul\r'),(231,'CarbonNanotube','wallType','DWNT\r'),(232,'CarbonNanotube','wallType','MWNT\r'),(233,'CarbonNanotube','wallType','SWNT\r');
/*!40000 ALTER TABLE `common_lookup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-04-07 19:02:52
