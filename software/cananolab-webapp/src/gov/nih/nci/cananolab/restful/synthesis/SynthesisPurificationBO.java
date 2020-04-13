package gov.nih.nci.cananolab.restful.synthesis;

import gov.nih.nci.cananolab.domain.characterization.physical.Purity;
import gov.nih.nci.cananolab.domain.common.File;
import gov.nih.nci.cananolab.domain.common.Protocol;
import gov.nih.nci.cananolab.domain.common.PurificationConfig;
import gov.nih.nci.cananolab.domain.particle.Synthesis;
import gov.nih.nci.cananolab.domain.particle.SynthesisPurification;
import gov.nih.nci.cananolab.domain.particle.SynthesisPurity;
import gov.nih.nci.cananolab.dto.common.ProtocolBean;
import gov.nih.nci.cananolab.dto.particle.synthesis.SynthesisPurificationBean;
import gov.nih.nci.cananolab.dto.particle.synthesis.SynthesisPurityBean;
import gov.nih.nci.cananolab.restful.core.BaseAnnotationBO;
import gov.nih.nci.cananolab.restful.sample.InitSampleSetup;
import gov.nih.nci.cananolab.restful.util.SynthesisUtil;
import gov.nih.nci.cananolab.restful.view.edit.SimpleExperimentBean;
import gov.nih.nci.cananolab.restful.view.edit.SimpleFileBean;
import gov.nih.nci.cananolab.restful.view.edit.SimpleProtocol;
import gov.nih.nci.cananolab.restful.view.edit.SimplePurificationConfigBean;
import gov.nih.nci.cananolab.restful.view.edit.SimplePurityBean;
import gov.nih.nci.cananolab.restful.view.edit.SimpleSynthesisPurificationBean;
import gov.nih.nci.cananolab.security.service.SpringSecurityAclService;
import gov.nih.nci.cananolab.service.curation.CurationService;
import gov.nih.nci.cananolab.service.protocol.ProtocolService;
import gov.nih.nci.cananolab.service.sample.SampleService;
import gov.nih.nci.cananolab.service.sample.SynthesisService;
import gov.nih.nci.cananolab.ui.form.SynthesisForm;
import gov.nih.nci.cananolab.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=false, propagation= Propagation.REQUIRED)
@Component("synthesisPurificationBO")
public class SynthesisPurificationBO extends BaseAnnotationBO {
    //TODO write
    Logger logger = Logger.getLogger(SynthesisPurificationBO.class);
    @Autowired
    private SynthesisService synthesisService;

    @Autowired
    private SpringSecurityAclService springSecurityAclService;

    @Autowired
    private CurationService curationServiceDAO;

    @Autowired
    private SampleService sampleService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ProtocolService protocolService;


    @Override
    public CurationService getCurationServiceDAO() {
        return this.curationServiceDAO;
    }

    @Override
    public SampleService getSampleService() {
        return this.sampleService;
    }

    @Override
    public SpringSecurityAclService getSpringSecurityAclService() {
        return this.springSecurityAclService;
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }

    public List<String> savePurification(SimpleSynthesisPurificationBean editBean, HttpServletRequest httpRequest) {
        //TODO write
        return null;
    }

    /**
     * Returns dropdown and other information for creating a new Purification object
     *
     * @param sampleId
     * @param httpRequest
     * @return
     * @throws Exception
     */
    public Map<String, Object> setupNew(String sampleId, HttpServletRequest httpRequest) throws Exception {
        SynthesisPurificationBean synthesisPurificationBean = new SynthesisPurificationBean();
        List<String> otherNames = InitSampleSetup.getInstance().getOtherSampleNames(httpRequest, sampleId, sampleService);
        this.setLookups(httpRequest, synthesisPurificationBean);
        this.checkOpenForms(synthesisPurificationBean, httpRequest);
        //TODO write
        Map<String,Object> testLookup = new HashMap<String, Object>();
        testLookup.put("protocolLookup", this.setProtocolLookup(httpRequest));
        testLookup.putAll(SynthesisUtil.reformatLocalSearchDropdownsInSessionForSynthesisPurification(httpRequest.getSession()));
        return testLookup;

    }

    public List<SimpleProtocol> setProtocolLookup(HttpServletRequest request)
            throws Exception {
        List<SimpleProtocol> protocolLookup = new ArrayList<SimpleProtocol>();
        List<ProtocolBean> protoBeans = protocolService.getPurificationProtocols(request);

        if (protoBeans == null)
            return protocolLookup;

        for (ProtocolBean protoBean : protoBeans) {
            SimpleProtocol simpleProto = new SimpleProtocol();
            simpleProto.transferFromProtocolBean(protoBean);
            protocolLookup.add(simpleProto);
        }
        return protocolLookup;
    }

    /**
     * Crafts the lookups for the purification web form
     *
     * @param httpRequest
     * @param elementBean
     * @throws Exception
     */
    private void setLookups(HttpServletRequest httpRequest, SynthesisPurificationBean elementBean) throws Exception {
        ServletContext appContext = httpRequest.getSession().getServletContext();
        InitSynthesisSetup.getInstance().setSynthesisPurificationDropdowns(httpRequest);
    }

    /**
     * Returns current purifcation information and fields for editing that purification
     * @param sampleId
     * @param dataId
     * @param httpRequest
     * @return
     * @throws Exception
     */
    public SimpleSynthesisPurificationBean setupUpdate(String sampleId, String dataId, HttpServletRequest httpRequest) throws Exception {
        SynthesisForm form = new SynthesisForm();
        // set up other particles with the same primary point of contact
//        InitSampleSetup.getInstance().getOtherSampleNames(httpRequest, sampleId, sampleService);

        try {
            SynthesisPurificationBean synBean = synthesisService.findSynthesisPurificationById(new Long(sampleId), new Long(dataId));

            form.setSynthesisPurificationBean(synBean);
            this.checkOpenForms(synBean, httpRequest);
            httpRequest.getSession().setAttribute("sampleId", sampleId);
            SimpleSynthesisPurificationBean simpleSynthesisPurificationBean = new SimpleSynthesisPurificationBean();
            simpleSynthesisPurificationBean.transferSynthesisPurificationBeanToSimple(synBean, httpRequest, springSecurityAclService);
            return simpleSynthesisPurificationBean;
        } catch (IllegalFormatConversionException e){
            logger.error("Either sample id or data id is not an appropriate identifier. ",e);
            throw e;
        }
    }


    /**
     * Create a new purification
     *
     * @param purificationBean
     * @param httpRequest
     * @return
     */
    public List<String> create(SimpleSynthesisPurificationBean purificationBean, HttpServletRequest httpRequest){
        //TODO write
        List<String> msgs = new ArrayList<String>();
        SynthesisPurificationBean synthesisPurificationBean = transferSimplePurification(purificationBean, httpRequest);
        msgs = validatePurification(httpRequest,synthesisPurificationBean);

        if (msgs.size() > 0) {
            return msgs;
        }

        this.saveEntity( synthesisPurificationBean,httpRequest);
        InitSynthesisSetup.getInstance().persistPurificationDropdowns(
                httpRequest, synthesisPurificationBean);
        msgs.add("success");
        httpRequest.getSession().setAttribute("tab", "1");
        return msgs;

    }

    /**
     * Save changes to an existing purification
     *
     * @param synthesisPurificationBean
     * @param httpRequest
     */
    private List<String> saveEntity(SynthesisPurificationBean synthesisPurificationBean, HttpServletRequest httpRequest) {
        //TODO write
        return null;
    }

    /** delete an existing purification
     *
     * @param editBean
     * @param httpRequest
     * @return
     */
    public List<String> delete(SimpleSynthesisPurificationBean editBean, HttpServletRequest httpRequest){
        //TODO write
        return null;
    }

    /**
     * Add a new purity to an existing purification
     * @param editBean
     * @param httpRequest
     * @return
     */
    public List<String> createPurity(SimpleSynthesisPurificationBean editBean, HttpServletRequest httpRequest){
        //TODO write
        return null;
    }

    /**
     * Delete and existing purity from a purification
     * @param editBean
     * @param httpRequest
     * @return
     */
    public List<String> deletePurity(SimpleSynthesisPurificationBean editBean, HttpServletRequest httpRequest){
        //TODO write
        return null;
    }

    /**
     * add or edit a file on and existing purification
     * @param editBean
     * @param httpRequest
     * @return
     */
    public List<String> saveFile(SimpleSynthesisPurificationBean editBean, HttpServletRequest httpRequest){
        //TODO write
        return null;
    }

    /**
     * Delete a file from an existing purification
     *
     * @param editBean
     * @param httpRequest
     * @return
     */
    public List<String> deleteFile(SimpleSynthesisPurificationBean editBean, HttpServletRequest httpRequest){
        //TODO write
        return null;
    }

    public void setupView(){
        //TODO write
    }

    /**
     * Add or edit a technique and instrument to a purification
     * @param editBean
     * @param httpRequest
     * @return
     */
    public List<String> saveTechniqueAndEquipment(SimpleSynthesisPurificationBean editBean, HttpServletRequest httpRequest){
        //TODO write
        return null;
    }

    /**
     * Remove a technique and instrument from a purification
     *
     * @param editBean
     * @param httpRequest
     * @return
     */
    public List<String> deleteTechniqueAndEquipment(SimpleSynthesisPurificationBean editBean, HttpServletRequest httpRequest){
        //TODO write
        return null;
    }

    /**
     *
     * @param synBean
     * @param httpRequest
     */
    private void checkOpenForms(SynthesisPurificationBean synBean, HttpServletRequest httpRequest) {
        //TODO write
    }

    /**
     *
     * @param httpRequest
     * @param synthesisPurificationBean
     * @return
     */
    private List<String> validatePurification(HttpServletRequest httpRequest, SynthesisPurificationBean synthesisPurificationBean){
        //TODO write
        return null;
    }

    /**
     * Transfer from SimplePurityBean to SynthesisPurityBean
     * @param simplePurityBean
     * @param httpRequest
     * @return
     */
    private SynthesisPurityBean transferSimplePurity(SimplePurityBean simplePurityBean, HttpServletRequest httpRequest){
        //TODO write all the transfer stuff
        SynthesisPurityBean purityBean = new SynthesisPurityBean();
        Purity purity = new Purity();

        //id
        if((simplePurityBean.getId()!=null)&&(simplePurityBean.getId()>0)){
            purity.setId(simplePurityBean.getId());
            purity.setCreatedBy(simplePurityBean.getCreatedBy());
            purity.setCreatedDate(simplePurityBean.getCreatedDate());
        }else {
            purity.setCreatedBy(simplePurityBean.getCreatedBy());
            purity.setCreatedDate(simplePurityBean.getCreatedDate());
        }

        //Columns
        purityBean.setColumnHeaders(simplePurityBean.getColumnHeaders());





        //Column type (datum or condition)
        //column name
        //condition column properties
        //value type
        //value unit
        //value

        //File being edited (if any)
        //Files

        return purityBean;
    }

    /**
     * Transfer from SimpleSynthesisPurificationBean to SynthesisPurificationBean
     * @param sSynPurificationBean
     * @param httpRequest
     * @return
     */
    private SynthesisPurificationBean transferSimplePurification(SimpleSynthesisPurificationBean sSynPurificationBean, HttpServletRequest httpRequest){
        //TODO write;
        //id

        SynthesisPurification purification = new SynthesisPurification();

        if((sSynPurificationBean.getId()!=null)&& (sSynPurificationBean.getId()>0)){
            purification.setId(sSynPurificationBean.getId());
        }
        //Name
        purification.setMethodName(sSynPurificationBean.getMethodName());

        //Set protocol
        try {
            SimpleProtocol sProtocol = sSynPurificationBean.getSimpleProtocol();
            if (sProtocol != null) {
                ProtocolBean protocolBean = protocolService.findProtocolById(sProtocol.getDomainId().toString());
                Protocol protocol = protocolBean.getDomain();
                purification.setProtocol(protocol);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }

        //type
        purification.setType(sSynPurificationBean.getType());
        //source and date
        purification.setCreatedBy(sSynPurificationBean.getCreatedBy());
        purification.setCreatedDate(sSynPurificationBean.getcreatedDate());
        //design and methods description
        purification.setDesignMethodDescription(sSynPurificationBean.getDesignMethodDescription());
        //Technique and instrument
        //TODO
        //yield
        purification.setYield(sSynPurificationBean.getYield());
        //Analysis and conclusions
        purification.setAnalysis(sSynPurificationBean.getAnalysis());
        //Purity
        //TODO
        Set<SynthesisPurity> purities = new HashSet<SynthesisPurity>();
        List<SimplePurityBean> simplePurityBeans = sSynPurificationBean.getPurityBeans();
        if(simplePurityBeans != null){
            for(SimplePurityBean sPurityBean: simplePurityBeans){
                SynthesisPurity purity = new SynthesisPurity();
                purity.setId(sPurityBean.getId());
                purity.setCreatedBy(sPurityBean.getCreatedBy());
                purity.setCreatedDate(sPurityBean.getCreatedDate());
                purity.setSynthesisPurificationId(sSynPurificationBean.getId());
                //TODO files
                sPurityBean.getFiles();
                //check for Files
                List<SimpleFileBean> sfileBeans = sPurityBean.getFiles();
                Set<File> files = new HashSet<File>();
                if(sfileBeans!=null){
                    for(SimpleFileBean simpleFileBean: sfileBeans){

                        File file1 = new File();
                        file1.setUriExternal(simpleFileBean.getUriExternal());
                        file1.setUri(simpleFileBean.getUri());
                        file1.setType(simpleFileBean.getType());
                        file1.setDescription(simpleFileBean.getDescription());
                        file1.setTitle(simpleFileBean.getTitle());
                        file1.setCreatedDate(simpleFileBean.getCreatedDate());
                        file1.setCreatedBy(simpleFileBean.getCreatedBy());
                        files.add(file1);
                    }}
                purity.setFiles(files);

                //TODO datum
                sPurityBean.getColumnHeaders();

                purities.add(purity);
            }
        }
        purification.setPurities(purities);

        List<SimplePurificationConfigBean> simpleExperimentBeans = sSynPurificationBean.getSimpleExperimentBeans();
        if(simpleExperimentBeans!=null){
            for (SimplePurificationConfigBean sExperimentBean: simpleExperimentBeans) {
                PurificationConfig config = new PurificationConfig();
                config.setPurificationConfigPkId(sExperimentBean.getId());
                config.setDescription(sExperimentBean.getDescription());
                //TODO create by and date
                //Technique

//                config.setTechnique();
//                config.setInstrumentCollection();
            }
        }

        SynthesisPurificationBean synthesisPurificationBean = new SynthesisPurificationBean(purification);
        return synthesisPurificationBean;
    }

}
