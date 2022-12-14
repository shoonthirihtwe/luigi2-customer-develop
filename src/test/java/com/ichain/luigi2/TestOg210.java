package com.ichain.luigi2;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import jp.co.ichain.luigi2.Luigi2Application;
import jp.co.ichain.luigi2.dto.ResultOneDto;
import jp.co.ichain.luigi2.test.TestScriptUtils;
import jp.co.ichain.luigi2.vo.CustomerContractDetailVo;
import jp.co.ichain.luigi2.vo.CustomerDetailAgencyInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailBasicInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailBillingInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailCommonInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailContractorInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailInsuredInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailMaintenanceRequestInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailNoticeInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailPaymentInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailPremiumInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailProductInfoVo;
import jp.co.ichain.luigi2.vo.RiskHeadersVo;


/**
 * OG210
 *
 * @author : [AOT] ?????????
 * @createdAt : 2021-08-03
 * @updatedAt : 2021-08-03
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = Luigi2Application.class)
@TestPropertySource("classpath:application-common-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class TestOg210 {

  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private TestScriptUtils testScriptUtils;

  private String sql = "sql/insert_OG210.sql";
  private String url = "/OG21001";

  /**
   * OG-210 ???????????????????????? setup
   *
   * @author : [AOT] ?????????
   * @createdAt : 2021-08-03
   * @updatedAt : 2021-08-03
   */
  @BeforeEach
  void setup() throws SQLException {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
    // ?????????????????????SQL??????
    testScriptUtils.executeSqlScript(sql);
  }

  /**
   * OG-210 #1 ?????????????????? ???????????????
   * 
   * ??????????????????????????????????????????????????????
   * 
   * @author : [AOT] ?????????
   * @createdAt : 2021-08-04
   * @updatedAt : 2021-08-04
   */
  @Test
  void test_Og21001() throws Exception {

    String requestJson = "json/request/og210/req_OG21001_data.json";
    String resultJson = "json/response/og210/res_OG21001_data.json";

    testResponseError(requestJson, resultJson);
  }

  /**
   * OG-210 #2 ?????????????????? ???????????????
   * 
   * ????????????????????????????????????????????????
   * 
   * @author : [VJP] ?????????
   * @createdAt : 2021-08-04
   * @updatedAt : 2021-08-04
   */
  @Test
  void test_Og21002() throws Exception {
    String requestJson = "json/request/og210/req_OG21002_data.json";
    String resultJson = "json/response/og210/res_OG21002_data.json";

    testResponseError(requestJson, resultJson);
  }

  /**
   * OG-210 #3 ??????????????????????????? ???????????????
   * 
   * ????????????????????????????????????????????????
   * 
   * @author : [VJP] ?????????
   * @createdAt : 2021-08-04
   * @updatedAt : 2021-08-04
   */
  @Test
  void test_Og21003() throws Exception {
    String requestJson = "json/request/og210/req_OG21003_data.json";
    String resultJson = "json/response/og210/res_OG21003_data.json";

    testResponseError(requestJson, resultJson);
  }

  /**
   * OG210 #4
   *
   * @author : [VJP] ?????????
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @Test
  void test_Og21004() throws Exception {
    String requestJson = "json/request/og210/req_OG21004_data.json";
    String resultJson = "json/response/og210/res_OG21004_data.json";

    // ?????????????????????SQL??????
    testScriptUtils.executeSqlScript(sql);

    // ???????????????
    testResponseOk(requestJson, resultJson);
  }

  /**
   * OG210 #5
   *
   * @author : [VJP] ?????????
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @Test
  void test_Og21005() throws Exception {
    String requestJson = "json/request/og210/req_OG21005_data.json";
    String resultJson = "json/response/og210/res_OG21005_data.json";

    testScriptUtils.updateOnlineDate("2021-09-01", 1);
    // ?????????????????????SQL??????
    testScriptUtils.executeSqlScript(sql);

    // ???????????????
    testResponseOk(requestJson, resultJson);
  }

  /**
   * Mock request
   * 
   * @author : [VJP] ?????????
   * @param requestJson
   * @return
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  private MvcResult mockMvc(String requestJson) throws JsonParseException, JsonMappingException,
      UnsupportedEncodingException, IOException, Exception {
    // API?????????
    return mockMvc
        .perform(MockMvcRequestBuilders.get(url).header("x-frontend-domain", "localhost")
            .params(testScriptUtils.loadJsonToMultiValueMap(requestJson))
            .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();
  }

  /**
   * Mock request error
   * 
   * @author : [VJP] ?????????
   * @param requestJson
   * @return
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   * @createdAt : 2021-09-28
   * @updatedAt : 2021-09-28
   */
  private MvcResult mockMvcError(String requestJson) throws JsonParseException,
      JsonMappingException, UnsupportedEncodingException, IOException, Exception {
    // API?????????
    return mockMvc.perform(MockMvcRequestBuilders.get(url).header("x-frontend-domain", "localhost")
        .params(testScriptUtils.loadJsonToMultiValueMap(requestJson))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .accept(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
  }

  /**
   * create Gson
   * 
   * @author : [VJP] ?????????
   * @param mvcResult
   * @return
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  private Gson createGson(MvcResult mvcResult)
      throws UnsupportedEncodingException, IOException, Exception {
    JsonDeserializer<Date> deser = (json, typeOfT, context) -> json == null ? null
        : new Date(Long.valueOf(json.getAsString().replaceAll("\\D", "")));
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, deser).create();
    return gson;
  }

  /**
   * Mock request when response return error
   * 
   * @author : [VJP] ?????????
   * @param requestJson
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @SuppressWarnings("unchecked")
  private void testResponseError(String requestJson, String resultJson) throws JsonParseException,
      JsonMappingException, UnsupportedEncodingException, IOException, Exception {

    MvcResult mvcResult = mockMvcError(requestJson);
    ObjectMapper mapper = new ObjectMapper();

    List<HashMap<String, String>> target = (List<HashMap<String, String>>) mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class).get("items");

    // ?????????????????????
    HashMap<String, String> expected =
        (HashMap<String, String>) testScriptUtils.loadJsonToObject(resultJson);

    // ??????????????????
    assertEquals(1, target.size());
    assertEquals(expected.get("code"), target.get(0).get("code"));
    assertEquals(expected.get("errArgs"), target.get(0).get("errArgs"));
  }

  /**
   * Mock request when response return error
   * 
   * @author : [VJP] ?????????
   * @param requestJson
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  private void testResponseOk(String requestJson, String resultJson) throws JsonParseException,
      JsonMappingException, UnsupportedEncodingException, IOException, Exception {

    // API?????????
    MvcResult mvcResult = mockMvc(requestJson);

    Gson gson = createGson(mvcResult);

    Type responseType = new TypeToken<ResultOneDto<CustomerContractDetailVo>>() {}.getType();
    ResultOneDto<CustomerContractDetailVo> res =
        gson.fromJson(mvcResult.getResponse().getContentAsString(), responseType);
    CustomerContractDetailVo result = res.getItem();

    // ?????????????????????
    CustomerContractDetailVo expected =
        gson.fromJson(testScriptUtils.loadJsonToString(resultJson), CustomerContractDetailVo.class);

    CustomerDetailCommonInfoVo resultCommonInfo = result.getCommonInfo();
    CustomerDetailCommonInfoVo expectedCommonInfo = expected.getCommonInfo();
    // ????????????
    assertEquals(expectedCommonInfo.getContractNo(), resultCommonInfo.getContractNo());
    // ??????
    assertEquals(expectedCommonInfo.getContractBranchNo(), resultCommonInfo.getContractBranchNo());
    // ????????????
    assertEquals(expectedCommonInfo.getSalesPlanName(), resultCommonInfo.getSalesPlanName());
    // ????????????
    assertEquals(expectedCommonInfo.getContractorNameKnj(),
        expectedCommonInfo.getContractorNameKnj());

    // ????????????-????????????
    CustomerDetailBasicInfoVo resultBasicInfo = result.getBasicInfo();
    CustomerDetailBasicInfoVo expectedBasicInfo = expected.getBasicInfo();
    // ????????????
    assertEquals(expectedBasicInfo.getContractNo(), resultBasicInfo.getContractNo());
    // ??????????????????
    assertEquals(expectedBasicInfo.getContractBranchNo(), resultBasicInfo.getContractBranchNo());
    // ?????????????????????
    assertEquals(expectedBasicInfo.getContractStatus(), resultBasicInfo.getContractStatus());
    // ????????????
    assertEquals(expectedBasicInfo.getSalesMethod(), resultBasicInfo.getSalesMethod());
    // ????????????
    assertEquals(expectedBasicInfo.getApplicationDate(), resultBasicInfo.getApplicationDate());
    // ?????????
    assertEquals(expectedBasicInfo.getCompleteDate(), resultBasicInfo.getCompleteDate());
    // ???????????????
    assertEquals(expectedBasicInfo.getEffectiveDate(), resultBasicInfo.getEffectiveDate());
    // ????????????
    assertEquals(expectedBasicInfo.getUpdateCnt(), resultBasicInfo.getUpdateCnt());
    // ???????????????????????????
    assertEquals(expectedBasicInfo.getIssueDate(), resultBasicInfo.getIssueDate());
    // ???????????????????????????
    assertEquals(expectedBasicInfo.getExpirationDate(), resultBasicInfo.getExpirationDate());

    // ????????????-????????????
    CustomerDetailProductInfoVo resultProductInfo = result.getProductInfo();
    CustomerDetailProductInfoVo expectedProductInfo = expected.getProductInfo();
    // ????????????
    assertEquals(expectedProductInfo.getSalesPlanNameDisplay(),
        expectedProductInfo.getSalesPlanNameDisplay());

    // ????????????
    List<RiskHeadersVo> resultCustomerBenefitInfo = resultProductInfo.getBenefitInfo();
    List<RiskHeadersVo> expectedCustomerBenefitInfo = expectedProductInfo.getBenefitInfo();

    if (expectedCustomerBenefitInfo != null) {
      assertEquals(expectedCustomerBenefitInfo.size(), resultCustomerBenefitInfo.size());
      for (int i = 0; i < expectedCustomerBenefitInfo.size(); i++) {
        // ?????????????????????
        assertEquals(expectedCustomerBenefitInfo.get(i).getBenefitText(),
            resultCustomerBenefitInfo.get(i).getBenefitText());
        // ????????????
        assertEquals(expectedCustomerBenefitInfo.get(i).getBenefitBaseAmount(),
            resultCustomerBenefitInfo.get(i).getBenefitBaseAmount());
      }
    } else {
      assertEquals(expectedCustomerBenefitInfo, resultCustomerBenefitInfo);
    }

    // ????????????-???????????????
    CustomerDetailContractorInfoVo resultContractorInfo = result.getContractorInfo();
    CustomerDetailContractorInfoVo expectedContractorInfo = expected.getContractorInfo();
    // ?????????ID
    assertEquals(expectedContractorInfo.getContractorCustomerId(),
        resultContractorInfo.getContractorCustomerId());
    // ????????????
    assertEquals(expectedContractorInfo.getContractorNameKnj(),
        resultContractorInfo.getContractorNameKnj());
    // ????????????????????????
    assertEquals(expectedContractorInfo.getContractorNameKana(),
        resultContractorInfo.getContractorNameKana());
    // ????????????
    assertEquals(expectedContractorInfo.getContractorDateOfBirth(),
        resultContractorInfo.getContractorDateOfBirth());
    // ????????????????????????
    assertEquals(expectedContractorInfo.getContractorZipcode(),
        resultContractorInfo.getContractorZipcode());
    // ????????????
    assertEquals(expectedContractorInfo.getContractorTel(),
        resultContractorInfo.getContractorTel());
    // ?????????????????????
    assertEquals(expectedContractorInfo.getContractorEmail(),
        resultContractorInfo.getContractorEmail());

    // ????????????-??????????????????
    CustomerDetailInsuredInfoVo resultInsuredInfo = result.getInsuredInfo();
    CustomerDetailInsuredInfoVo expectedInsuredInfo = expected.getInsuredInfo();
    // ????????????ID
    assertEquals(expectedInsuredInfo.getInsuredCustomerId(),
        expectedInsuredInfo.getInsuredCustomerId());
    // ???????????????
    assertEquals(expectedInsuredInfo.getInsuredNameKnj(), resultInsuredInfo.getInsuredNameKnj());
    // ???????????????????????????
    assertEquals(expectedInsuredInfo.getInsuredNameKana(), resultInsuredInfo.getInsuredNameKana());
    // ????????????
    assertEquals(expectedInsuredInfo.getInsuredDateOfBirth(),
        expectedInsuredInfo.getInsuredDateOfBirth());
    // ??????
    assertEquals(expectedInsuredInfo.getInsuredSex(), resultInsuredInfo.getInsuredSex());
    // ???????????????????????????
    assertEquals(expectedInsuredInfo.getRelationship(), resultInsuredInfo.getRelationship());
    // ????????????????????????
    assertEquals(expectedInsuredInfo.getInsuredZipcode(), resultInsuredInfo.getInsuredZipcode());
    // ????????????
    assertEquals(expectedInsuredInfo.getInsuredTel(), resultInsuredInfo.getInsuredTel());

    // ????????????-???????????????
    CustomerDetailPremiumInfoVo resultPremiumInfo = result.getPremiumInfo();
    CustomerDetailPremiumInfoVo expectedPremiumInfo = expected.getPremiumInfo();
    // ?????????
    assertEquals(expectedPremiumInfo.getPremium(), resultPremiumInfo.getPremium());
    // ????????????
    assertEquals(expectedPremiumInfo.getFrequency(), resultPremiumInfo.getFrequency());
    // ????????????
    assertEquals(expectedPremiumInfo.getPaymentMethod(), resultPremiumInfo.getPaymentMethod());

    // ????????????-?????????????????????
    CustomerDetailAgencyInfoVo resultAgencyInfo = result.getAgencyInfo();
    CustomerDetailAgencyInfoVo expectedAgencyInfo = expected.getAgencyInfo();
    // ???????????????
    assertEquals(expectedAgencyInfo.getAgencyName1(), resultAgencyInfo.getAgencyName1());
    // ???????????????
    assertEquals(expectedAgencyInfo.getAgentName1(), resultAgencyInfo.getAgentName1());
    // ???????????????
    assertEquals(expectedAgencyInfo.getAgentShare1(), resultAgencyInfo.getAgentShare1());
    // ???????????????
    assertEquals(expectedAgencyInfo.getAgencyName2(), resultAgencyInfo.getAgencyName2());
    // ???????????????
    assertEquals(expectedAgencyInfo.getAgentName2(), resultAgencyInfo.getAgentName2());
    // ???????????????
    assertEquals(expectedAgencyInfo.getAgentShare2(), resultAgencyInfo.getAgentShare2());

    // ????????????
    CustomerDetailNoticeInfoVo resultNoticeInfo = result.getNoticeInfo();
    CustomerDetailNoticeInfoVo expectedNoticeInfo = expected.getNoticeInfo();
    // ??????1???????????????1
    assertEquals(expectedNoticeInfo.getNoticeText1(), resultNoticeInfo.getNoticeText1());
    // ??????2???????????????2
    assertEquals(expectedNoticeInfo.getNoticeText2(), resultNoticeInfo.getNoticeText1());

    // ???????????????
    List<CustomerDetailMaintenanceRequestInfoVo> resultMaintenanceRequestInfo =
        result.getMaintenanceRequestInfo();
    List<CustomerDetailMaintenanceRequestInfoVo> expectedMaintenanceRequestInfo =
        expected.getMaintenanceRequestInfo();

    if (expectedMaintenanceRequestInfo != null) {
      assertEquals(expectedMaintenanceRequestInfo.size(), resultMaintenanceRequestInfo.size());
      for (int i = 0; i < expectedMaintenanceRequestInfo.size(); i++) {
        // ?????????
        assertEquals(expectedMaintenanceRequestInfo.get(i).getTransactionCode(),
            resultMaintenanceRequestInfo.get(i).getTransactionCode());
        // ?????????
        assertEquals(expectedMaintenanceRequestInfo.get(i).getReceivedDate(),
            resultMaintenanceRequestInfo.get(i).getReceivedDate());
        // ?????????
        assertEquals(expectedMaintenanceRequestInfo.get(i).getApplyDate(),
            resultMaintenanceRequestInfo.get(i).getApplyDate());
      }
    } else {
      assertEquals(expectedMaintenanceRequestInfo, resultMaintenanceRequestInfo);
    }

    // ????????????
    List<CustomerDetailPaymentInfoVo> resultPaymentInfo = result.getPaymentInfo();
    List<CustomerDetailPaymentInfoVo> expectedPaymentInfo = expected.getPaymentInfo();
    if (expectedPaymentInfo != null) {
      assertEquals(expectedPaymentInfo.size(), resultPaymentInfo.size());
      for (int i = 0; i < expectedPaymentInfo.size(); i++) {
        // ?????????
        assertEquals(expectedPaymentInfo.get(i).getReceivedDate(),
            resultPaymentInfo.get(i).getReceivedDate());
        // ?????????
        assertEquals(expectedPaymentInfo.get(i).getSecondUnderwritingDate(),
            resultPaymentInfo.get(i).getSecondUnderwritingDate());
        // ?????????
        assertEquals(expectedPaymentInfo.get(i).getDueDate(),
            resultPaymentInfo.get(i).getDueDate());
      }
    } else {
      assertEquals(expectedPaymentInfo, resultPaymentInfo);
    }

    // ????????????
    List<CustomerDetailBillingInfoVo> resultBillingInfo = result.getBillingInfo();
    List<CustomerDetailBillingInfoVo> expectedBillingInfo = expected.getBillingInfo();

    if (expectedBillingInfo != null) {
      assertEquals(expectedBillingInfo.size(), resultBillingInfo.size());
      for (int i = 0; i < expectedBillingInfo.size(); i++) {
        // ?????????
        assertEquals(expectedBillingInfo.get(i).getPremiumDueDate(),
            resultBillingInfo.get(i).getPremiumDueDate());
        // ????????????
        assertEquals(expectedBillingInfo.get(i).getFrequency(),
            resultBillingInfo.get(i).getFrequency());
        // ????????????
        assertEquals(expectedBillingInfo.get(i).getPaymentMethodCode(),
            resultBillingInfo.get(i).getPaymentMethodCode());
        // ?????????
        assertEquals(expectedBillingInfo.get(i).getDepositDate(),
            resultBillingInfo.get(i).getDepositDate());
      }
    } else {
      assertEquals(expectedBillingInfo, resultBillingInfo);
    }
  }

  /**
   * ?????????????????????DB?????????
   *
   * @author : [VJP] ?????????
   * @throws SQLException
   * @throws ScriptException
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @AfterEach
  void clean() throws ScriptException, SQLException {
    testScriptUtils.cleanUpDatabase();
  }
}
