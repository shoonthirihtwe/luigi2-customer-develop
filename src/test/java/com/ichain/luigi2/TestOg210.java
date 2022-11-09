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
 * @author : [AOT] アイン
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
   * OG-210 契約詳細表示画面 setup
   *
   * @author : [AOT] アイン
   * @createdAt : 2021-08-03
   * @updatedAt : 2021-08-03
   */
  @BeforeEach
  void setup() throws SQLException {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript(sql);
  }

  /**
   * OG-210 #1 クイック検索 テスト実行
   * 
   * 結果：エラー（証券番号未入力エラー）
   * 
   * @author : [AOT] アイン
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
   * OG-210 #2 証券番号検索 テスト実行
   * 
   * 結果：エラー（証券番号該当無し）
   * 
   * @author : [VJP] アイン
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
   * OG-210 #3 証券番号と枝番検索 テスト実行
   * 
   * 結果：エラー（証券番号該当無し）
   * 
   * @author : [VJP] アイン
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
   * @author : [VJP] アイン
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @Test
  void test_Og21004() throws Exception {
    String requestJson = "json/request/og210/req_OG21004_data.json";
    String resultJson = "json/response/og210/res_OG21004_data.json";

    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript(sql);

    // テスト実行
    testResponseOk(requestJson, resultJson);
  }

  /**
   * OG210 #5
   *
   * @author : [VJP] アイン
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @Test
  void test_Og21005() throws Exception {
    String requestJson = "json/request/og210/req_OG21005_data.json";
    String resultJson = "json/response/og210/res_OG21005_data.json";

    testScriptUtils.updateOnlineDate("2021-09-01", 1);
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript(sql);

    // テスト実行
    testResponseOk(requestJson, resultJson);
  }

  /**
   * Mock request
   * 
   * @author : [VJP] アイン
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
    // APIコール
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
   * @author : [VJP] アイン
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
    // APIコール
    return mockMvc.perform(MockMvcRequestBuilders.get(url).header("x-frontend-domain", "localhost")
        .params(testScriptUtils.loadJsonToMultiValueMap(requestJson))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .accept(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
  }

  /**
   * create Gson
   * 
   * @author : [VJP] アイン
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
   * @author : [VJP] アイン
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

    // 予想結果ロード
    HashMap<String, String> expected =
        (HashMap<String, String>) testScriptUtils.loadJsonToObject(resultJson);

    // 検索結果件数
    assertEquals(1, target.size());
    assertEquals(expected.get("code"), target.get(0).get("code"));
    assertEquals(expected.get("errArgs"), target.get(0).get("errArgs"));
  }

  /**
   * Mock request when response return error
   * 
   * @author : [VJP] アイン
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

    // APIコール
    MvcResult mvcResult = mockMvc(requestJson);

    Gson gson = createGson(mvcResult);

    Type responseType = new TypeToken<ResultOneDto<CustomerContractDetailVo>>() {}.getType();
    ResultOneDto<CustomerContractDetailVo> res =
        gson.fromJson(mvcResult.getResponse().getContentAsString(), responseType);
    CustomerContractDetailVo result = res.getItem();

    // 予想結果ロード
    CustomerContractDetailVo expected =
        gson.fromJson(testScriptUtils.loadJsonToString(resultJson), CustomerContractDetailVo.class);

    CustomerDetailCommonInfoVo resultCommonInfo = result.getCommonInfo();
    CustomerDetailCommonInfoVo expectedCommonInfo = expected.getCommonInfo();
    // 証券番号
    assertEquals(expectedCommonInfo.getContractNo(), resultCommonInfo.getContractNo());
    // 枝番
    assertEquals(expectedCommonInfo.getContractBranchNo(), resultCommonInfo.getContractBranchNo());
    // 保険商品
    assertEquals(expectedCommonInfo.getSalesPlanName(), resultCommonInfo.getSalesPlanName());
    // 契約者名
    assertEquals(expectedCommonInfo.getContractorNameKnj(),
        expectedCommonInfo.getContractorNameKnj());

    // 契約情報-基本情報
    CustomerDetailBasicInfoVo resultBasicInfo = result.getBasicInfo();
    CustomerDetailBasicInfoVo expectedBasicInfo = expected.getBasicInfo();
    // 証券番号
    assertEquals(expectedBasicInfo.getContractNo(), resultBasicInfo.getContractNo());
    // 証券番号枝番
    assertEquals(expectedBasicInfo.getContractBranchNo(), resultBasicInfo.getContractBranchNo());
    // 契約ステータス
    assertEquals(expectedBasicInfo.getContractStatus(), resultBasicInfo.getContractStatus());
    // 申込方法
    assertEquals(expectedBasicInfo.getSalesMethod(), resultBasicInfo.getSalesMethod());
    // 申込日時
    assertEquals(expectedBasicInfo.getApplicationDate(), resultBasicInfo.getApplicationDate());
    // 成立日
    assertEquals(expectedBasicInfo.getCompleteDate(), resultBasicInfo.getCompleteDate());
    // 責任開始日
    assertEquals(expectedBasicInfo.getEffectiveDate(), resultBasicInfo.getEffectiveDate());
    // 更新回数
    assertEquals(expectedBasicInfo.getUpdateCnt(), resultBasicInfo.getUpdateCnt());
    // 現契約日（更新日）
    assertEquals(expectedBasicInfo.getIssueDate(), resultBasicInfo.getIssueDate());
    // 契約終了（予定）日
    assertEquals(expectedBasicInfo.getExpirationDate(), resultBasicInfo.getExpirationDate());

    // 契約情報-商品情報
    CustomerDetailProductInfoVo resultProductInfo = result.getProductInfo();
    CustomerDetailProductInfoVo expectedProductInfo = expected.getProductInfo();
    // 保険商品
    assertEquals(expectedProductInfo.getSalesPlanNameDisplay(),
        expectedProductInfo.getSalesPlanNameDisplay());

    // 保障情報
    List<RiskHeadersVo> resultCustomerBenefitInfo = resultProductInfo.getBenefitInfo();
    List<RiskHeadersVo> expectedCustomerBenefitInfo = expectedProductInfo.getBenefitInfo();

    if (expectedCustomerBenefitInfo != null) {
      assertEquals(expectedCustomerBenefitInfo.size(), resultCustomerBenefitInfo.size());
      for (int i = 0; i < expectedCustomerBenefitInfo.size(); i++) {
        // 保障事故・特約
        assertEquals(expectedCustomerBenefitInfo.get(i).getBenefitText(),
            resultCustomerBenefitInfo.get(i).getBenefitText());
        // 保障金額
        assertEquals(expectedCustomerBenefitInfo.get(i).getBenefitBaseAmount(),
            resultCustomerBenefitInfo.get(i).getBenefitBaseAmount());
      }
    } else {
      assertEquals(expectedCustomerBenefitInfo, resultCustomerBenefitInfo);
    }

    // 契約情報-契約者情報
    CustomerDetailContractorInfoVo resultContractorInfo = result.getContractorInfo();
    CustomerDetailContractorInfoVo expectedContractorInfo = expected.getContractorInfo();
    // 契約者ID
    assertEquals(expectedContractorInfo.getContractorCustomerId(),
        resultContractorInfo.getContractorCustomerId());
    // 契約者名
    assertEquals(expectedContractorInfo.getContractorNameKnj(),
        resultContractorInfo.getContractorNameKnj());
    // 契約者名（カナ）
    assertEquals(expectedContractorInfo.getContractorNameKana(),
        resultContractorInfo.getContractorNameKana());
    // 生年月日
    assertEquals(expectedContractorInfo.getContractorDateOfBirth(),
        resultContractorInfo.getContractorDateOfBirth());
    // 住所（郵便番号）
    assertEquals(expectedContractorInfo.getContractorZipcode(),
        resultContractorInfo.getContractorZipcode());
    // 電話番号
    assertEquals(expectedContractorInfo.getContractorTel(),
        resultContractorInfo.getContractorTel());
    // メールアドレス
    assertEquals(expectedContractorInfo.getContractorEmail(),
        resultContractorInfo.getContractorEmail());

    // 契約情報-被保険者情報
    CustomerDetailInsuredInfoVo resultInsuredInfo = result.getInsuredInfo();
    CustomerDetailInsuredInfoVo expectedInsuredInfo = expected.getInsuredInfo();
    // 被保険者ID
    assertEquals(expectedInsuredInfo.getInsuredCustomerId(),
        expectedInsuredInfo.getInsuredCustomerId());
    // 被保険者名
    assertEquals(expectedInsuredInfo.getInsuredNameKnj(), resultInsuredInfo.getInsuredNameKnj());
    // 被保険者名（カナ）
    assertEquals(expectedInsuredInfo.getInsuredNameKana(), resultInsuredInfo.getInsuredNameKana());
    // 生年月日
    assertEquals(expectedInsuredInfo.getInsuredDateOfBirth(),
        expectedInsuredInfo.getInsuredDateOfBirth());
    // 性別
    assertEquals(expectedInsuredInfo.getInsuredSex(), resultInsuredInfo.getInsuredSex());
    // 契約者から見た続柄
    assertEquals(expectedInsuredInfo.getRelationship(), resultInsuredInfo.getRelationship());
    // 住所（郵便番号）
    assertEquals(expectedInsuredInfo.getInsuredZipcode(), resultInsuredInfo.getInsuredZipcode());
    // 電話番号
    assertEquals(expectedInsuredInfo.getInsuredTel(), resultInsuredInfo.getInsuredTel());

    // 契約情報-保険料情報
    CustomerDetailPremiumInfoVo resultPremiumInfo = result.getPremiumInfo();
    CustomerDetailPremiumInfoVo expectedPremiumInfo = expected.getPremiumInfo();
    // 保険料
    assertEquals(expectedPremiumInfo.getPremium(), resultPremiumInfo.getPremium());
    // 払込回数
    assertEquals(expectedPremiumInfo.getFrequency(), resultPremiumInfo.getFrequency());
    // 払込経路
    assertEquals(expectedPremiumInfo.getPaymentMethod(), resultPremiumInfo.getPaymentMethod());

    // 契約情報-募集代理店情報
    CustomerDetailAgencyInfoVo resultAgencyInfo = result.getAgencyInfo();
    CustomerDetailAgencyInfoVo expectedAgencyInfo = expected.getAgencyInfo();
    // 主代理店名
    assertEquals(expectedAgencyInfo.getAgencyName1(), resultAgencyInfo.getAgencyName1());
    // 主募集人名
    assertEquals(expectedAgencyInfo.getAgentName1(), resultAgencyInfo.getAgentName1());
    // 主募集割合
    assertEquals(expectedAgencyInfo.getAgentShare1(), resultAgencyInfo.getAgentShare1());
    // 従代理店名
    assertEquals(expectedAgencyInfo.getAgencyName2(), resultAgencyInfo.getAgencyName2());
    // 従募集人名
    assertEquals(expectedAgencyInfo.getAgentName2(), resultAgencyInfo.getAgentName2());
    // 従募集割合
    assertEquals(expectedAgencyInfo.getAgentShare2(), resultAgencyInfo.getAgentShare2());

    // 告知情報
    CustomerDetailNoticeInfoVo resultNoticeInfo = result.getNoticeInfo();
    CustomerDetailNoticeInfoVo expectedNoticeInfo = expected.getNoticeInfo();
    // 告知1・告知内容1
    assertEquals(expectedNoticeInfo.getNoticeText1(), resultNoticeInfo.getNoticeText1());
    // 告知2・告知内容2
    assertEquals(expectedNoticeInfo.getNoticeText2(), resultNoticeInfo.getNoticeText1());

    // 異動等情報
    List<CustomerDetailMaintenanceRequestInfoVo> resultMaintenanceRequestInfo =
        result.getMaintenanceRequestInfo();
    List<CustomerDetailMaintenanceRequestInfoVo> expectedMaintenanceRequestInfo =
        expected.getMaintenanceRequestInfo();

    if (expectedMaintenanceRequestInfo != null) {
      assertEquals(expectedMaintenanceRequestInfo.size(), resultMaintenanceRequestInfo.size());
      for (int i = 0; i < expectedMaintenanceRequestInfo.size(); i++) {
        // 処理名
        assertEquals(expectedMaintenanceRequestInfo.get(i).getTransactionCode(),
            resultMaintenanceRequestInfo.get(i).getTransactionCode());
        // 受付日
        assertEquals(expectedMaintenanceRequestInfo.get(i).getReceivedDate(),
            resultMaintenanceRequestInfo.get(i).getReceivedDate());
        // 処理日
        assertEquals(expectedMaintenanceRequestInfo.get(i).getApplyDate(),
            resultMaintenanceRequestInfo.get(i).getApplyDate());
      }
    } else {
      assertEquals(expectedMaintenanceRequestInfo, resultMaintenanceRequestInfo);
    }

    // 支払情報
    List<CustomerDetailPaymentInfoVo> resultPaymentInfo = result.getPaymentInfo();
    List<CustomerDetailPaymentInfoVo> expectedPaymentInfo = expected.getPaymentInfo();
    if (expectedPaymentInfo != null) {
      assertEquals(expectedPaymentInfo.size(), resultPaymentInfo.size());
      for (int i = 0; i < expectedPaymentInfo.size(); i++) {
        // 受付日
        assertEquals(expectedPaymentInfo.get(i).getReceivedDate(),
            resultPaymentInfo.get(i).getReceivedDate());
        // 処理日
        assertEquals(expectedPaymentInfo.get(i).getSecondUnderwritingDate(),
            resultPaymentInfo.get(i).getSecondUnderwritingDate());
        // 支払日
        assertEquals(expectedPaymentInfo.get(i).getDueDate(),
            resultPaymentInfo.get(i).getDueDate());
      }
    } else {
      assertEquals(expectedPaymentInfo, resultPaymentInfo);
    }

    // 収納情報
    List<CustomerDetailBillingInfoVo> resultBillingInfo = result.getBillingInfo();
    List<CustomerDetailBillingInfoVo> expectedBillingInfo = expected.getBillingInfo();

    if (expectedBillingInfo != null) {
      assertEquals(expectedBillingInfo.size(), resultBillingInfo.size());
      for (int i = 0; i < expectedBillingInfo.size(); i++) {
        // 充当月
        assertEquals(expectedBillingInfo.get(i).getPremiumDueDate(),
            resultBillingInfo.get(i).getPremiumDueDate());
        // 払込回数
        assertEquals(expectedBillingInfo.get(i).getFrequency(),
            resultBillingInfo.get(i).getFrequency());
        // 払込方法
        assertEquals(expectedBillingInfo.get(i).getPaymentMethodCode(),
            resultBillingInfo.get(i).getPaymentMethodCode());
        // 着金日
        assertEquals(expectedBillingInfo.get(i).getDepositDate(),
            resultBillingInfo.get(i).getDepositDate());
      }
    } else {
      assertEquals(expectedBillingInfo, resultBillingInfo);
    }
  }

  /**
   * 各テストことにDB初期化
   *
   * @author : [VJP] アイン
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
