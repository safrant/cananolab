package gov.nih.nci.cananolab.restful;

import gov.nih.nci.cananolab.domain.common.Supplier;
import gov.nih.nci.cananolab.domain.particle.SynthesisMaterialElement;
import gov.nih.nci.cananolab.restful.util.RestTestLoginUtil;
import gov.nih.nci.cananolab.restful.view.edit.SimpleFileBean;
import gov.nih.nci.cananolab.restful.view.edit.SimpleSynthesisMaterialBean;
import gov.nih.nci.cananolab.restful.view.edit.SimpleSynthesisMaterialElementBean;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static io.restassured.RestAssured.given;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SynthesisMaterialServicesTest {
private static RequestSpecification specification;
    @Before
    public void setUp() {
        //Get login session
        String jsessionId = RestTestLoginUtil.testLogin();

        //Create spec for all test logins in this class
        specification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/caNanoLab/rest/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .setSessionId(jsessionId)
                .build();

    }

    @After
    public void tearDown(){
//        RestTestLoginUtil.logoutTest();
    }



    @Test
    public void testSetup() {
//Test the initial setup of a synthesis material item - including the pass through of drop-down values
        try {
            Response response = given().spec(specification).queryParam("sampleId", "1000")
                    .when().get("synthesisMaterial/setup")
                    .then().statusCode(200).extract().response();

            assertNotNull(response);


            ArrayList<String> materialTypes = response.path("materialTypes");
            assertTrue(materialTypes.contains("coat"));
            ArrayList<String> pubChemTypes = response.path("pubChemDataSources");
            assertTrue(pubChemTypes.contains("Substance"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testEdit() {
        //Test the edit form retrieval of an existing material element
        try {
            Response response = given().spec(specification)
                    .queryParam("sampleId", "1000")
                    .queryParam("synMaterialId","1000")
                    .when().get("synthesisMaterial/edit")
                    .then().statusCode(200).extract().response();

            assertNotNull(response);
            String id = response.path("id").toString();
            assertTrue(id.equals("1000"));
            ArrayList<SimpleSynthesisMaterialElementBean> materialElements = response.path("materialElements");
            assertTrue(materialElements.size()>0);
       }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveSynthesisMaterialElement() {
        SimpleSynthesisMaterialBean materialBean = new SimpleSynthesisMaterialBean();
        materialBean.setSampleId("1000");
        materialBean.setId(new Long(1000));
        SimpleSynthesisMaterialElementBean elementBean = new SimpleSynthesisMaterialElementBean();
        elementBean.setDescription("New Description");
        elementBean.setMolecularFormulaType("Hill");
        elementBean.setMolecularFormula("C1B2A3");
        elementBean.setChemicalName("New Chemical");
        elementBean.setType("Reflexivity");
        elementBean.setValue(new Float(22.4));
        elementBean.setValueUnit("g");
        Map<String, String> supplierMap = new HashMap<String, String>();
        supplierMap.put("Lot","AB#$");
        supplierMap.put("SupplierName","New Supplier");
        supplierMap.put("id","1000");
        elementBean.setSupplier(supplierMap);
        List<SimpleSynthesisMaterialElementBean> elementBeans = new ArrayList<SimpleSynthesisMaterialElementBean>();
        elementBeans.add(elementBean);
        materialBean.setMaterialElements(elementBeans);

        try {
            Response response = given().spec(specification)
                    .body(materialBean).when().post("synthesisMaterial/saveSynthesisMaterialElement")
                    .then().statusCode(200).extract().response();

            assertNotNull(response);
            String debug = response.asString();
            System.out.println(debug);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveSynthesisMaterialElement() {
        SimpleSynthesisMaterialBean materialBean = new SimpleSynthesisMaterialBean();
        materialBean.setSampleId("1000");
        materialBean.setId(new Long(1000));
        SimpleSynthesisMaterialElementBean elementBean = new SimpleSynthesisMaterialElementBean();
        elementBean.setDescription("New Description");
        elementBean.setMolecularFormulaType("Hill");
        elementBean.setMolecularFormula("C1B2A3");
        elementBean.setChemicalName("New Chemical");
        elementBean.setType("Reflexivity");
        elementBean.setValue(new Float(22.4));
        elementBean.setValueUnit("g");
        Map<String, String> supplierMap = new HashMap<String, String>();
        supplierMap.put("Lot","AB#$");
        supplierMap.put("SupplierName","New Supplier");
        supplierMap.put("id","1000");
        elementBean.setSupplier(supplierMap);
        List<SimpleSynthesisMaterialElementBean> elementBeans = new ArrayList<SimpleSynthesisMaterialElementBean>();
        elementBeans.add(elementBean);
        materialBean.setMaterialElements(elementBeans);

        try {
            Response response = given().spec(specification)
                    .body(materialBean).when().post("synthesisMaterial/removeSynthesisMaterialElement")
                    .then().statusCode(200).extract().response();

            assertNotNull(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveFile() {
        SimpleSynthesisMaterialBean materialBean = new SimpleSynthesisMaterialBean();
        materialBean.setSampleId("1000");
        materialBean.setId(new Long(1000));
        SimpleFileBean fileBean = new SimpleFileBean();
        fileBean.setType("TestType");
        fileBean.setTitle("TestTitle");
        fileBean.setUriExternal(true);
        fileBean.setExternalUrl("https:///somewhere.com");
        fileBean.setSampleId("1000");
        List<SimpleFileBean> fileBeans = new ArrayList<SimpleFileBean>();
        fileBeans.add(fileBean);
        materialBean.setFileElements(fileBeans);

        try {
            Response response = given().spec(specification).queryParam("fileId", "1000")
                    .body(materialBean).when().post("synthesisMaterial/saveFile")
                    .then().statusCode(200).extract().response();

            assertNotNull(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveFile() {
        SimpleSynthesisMaterialBean materialBean = new SimpleSynthesisMaterialBean();
        materialBean.setSampleId("1000");
        materialBean.setId(new Long(1000));
        SimpleFileBean fileBean = new SimpleFileBean();
        fileBean.setType("TestType");
        fileBean.setTitle("TestTitle");
        fileBean.setUriExternal(true);
        fileBean.setExternalUrl("https:///somewhere.com");
        fileBean.setSampleId("1000");
        List<SimpleFileBean> fileBeans = new ArrayList<SimpleFileBean>();
        fileBeans.add(fileBean);
        materialBean.setFileElements(fileBeans);

        try {
            Response response = given().spec(specification).
                    body(materialBean).when().post("synthesisMaterial/removeFile")
                    .then().statusCode(200).extract().response();


            assertNotNull(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testSubmit() {
        SimpleSynthesisMaterialBean materialBean = new SimpleSynthesisMaterialBean();
        materialBean.setSampleId("1000");
        SimpleSynthesisMaterialElementBean elementBean = new SimpleSynthesisMaterialElementBean();
        elementBean.setChemicalName("TestChem");
        elementBean.setMolecularFormula("CHO2Si");
        elementBean.setMolecularFormulaType("Hill");
        elementBean.setDescription("Test material element");
        elementBean.setCreatedBy("");
        List<SimpleSynthesisMaterialElementBean> elementBeans = new ArrayList<SimpleSynthesisMaterialElementBean>();
        elementBeans.add(elementBean);
        materialBean.setMaterialElements(elementBeans);


        try {
            Response response = given().spec(specification).
                    body(materialBean).when().post("synthesisMaterial/submit")
                    .then().statusCode(200).extract().response();

            assertNotNull(response);
            String debug = response.asString();
            System.out.println(debug);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        SimpleSynthesisMaterialBean materialBean = new SimpleSynthesisMaterialBean();
        materialBean.setSampleId("1000");
        materialBean.setId(new Long(1000));

        try {
            Response response = given().spec(specification).
                    body(materialBean).when().post("synthesisMaterial/delete")
                    .then().statusCode(200).extract().response();

            assertNotNull(response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testViewDetails() {
        SimpleSynthesisMaterialBean materialBean = new SimpleSynthesisMaterialBean();
        materialBean.setSampleId("1000");
        materialBean.setId(new Long(1000));

        try {
            Response response = given().spec(specification).
                    body(materialBean).when().post("synthesisMaterial/viewDetails")
                    .then().statusCode(200).extract().response();

            assertNotNull(response);
            String debug = response.asString();
            System.out.println(debug);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String toJson(Object o){
    ObjectMapper Obj = new ObjectMapper();

        try {

        String jsonStr = Obj.writeValueAsString(o);
        return jsonStr;

    }        catch (Exception e) {

        e.printStackTrace();
    }
        return null;
    }
}