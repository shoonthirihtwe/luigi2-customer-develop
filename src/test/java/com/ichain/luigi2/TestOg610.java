package com.ichain.luigi2;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Date;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import jp.co.ichain.luigi2.Luigi2Application;
import jp.co.ichain.luigi2.dto.ResultOneDto;
import jp.co.ichain.luigi2.test.TestScriptUtils;
import jp.co.ichain.luigi2.vo.CustomerDetailVo;

/**
 * OG-610 SG-007 顧客詳細
 *
 * @author : [VJP] タン
 * @createdAt : 2021-08-05
 * @updatedAt : 2021-08-05
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = Luigi2Application.class)
@TestPropertySource("classpath:application-common-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class TestOg610 {

  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private TestScriptUtils testScriptUtils;

  private String sql = "sql/insert_OG210.sql";

  /**
   * OG-610 SG-007 顧客詳細 setup
   *
   * @author : [VJP] タン
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @BeforeEach
  void setup() throws SQLException {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript(sql);
  }

  /**
   * OG-610 SG-007 顧客詳細 テスト実行
   * 
   * #1_OG-610_SG-007
   * 
   * @author : [VJP] タン
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @Test
  void testOg61001() throws Exception {
    String requestJson = "json/request/og610/req_OG610_01_data.json";
    String url = "/OG61001";
    String resultJson = "json/response/og610/res_OG610_01_data.json";
    runTestCase(requestJson, url, resultJson);
  }

  /**
   * OG-610 SG-007 顧客詳細 テスト実行
   * 
   * #2_OG-610_SG-007
   * 
   * @author : [VJP] タン
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @Test
  void testOg61002() throws Exception {
    String requestJson = "json/request/og610/req_OG610_02_data.json";
    String url = "/OG61001";
    String resultJson = "json/response/og610/res_OG610_02_data.json";
    runTestCase(requestJson, url, resultJson);
  }

  /**
   * OG-610 SG-007 顧客詳細 テスト実行
   * 
   * #3_OG-610_SG-007
   * 
   * @author : [VJP] タン
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @Test
  void testOg61003() throws Exception {
    String requestJson = "json/request/og610/req_OG610_03_data.json";
    String url = "/OG61001";
    String resultJson = "json/response/og610/res_OG610_03_data.json";
    runTestCase(requestJson, url, resultJson);
  }

  /**
   * Mock request
   * 
   * @author : [VJP] タン
   * @param sql
   * @param url
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
  private MvcResult mockMvc(String url, String requestJson) throws JsonParseException,
      JsonMappingException, UnsupportedEncodingException, IOException, Exception {
    // APIコール
    return mockMvc
        .perform(MockMvcRequestBuilders.get(url).header("x-frontend-domain", "localhost")
            .params(testScriptUtils.loadJsonToMultiValueMap(requestJson))
            .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();
  }

  /**
   * 色なテストケースを実行
   * 
   * @author : [VJP] タン
   * @param requestJson
   * @param url
   * @param resultJson
   * @throws Exception
   * @createdAt : 2021-07-13
   * @updatedAt : 2021-07-13
   */
  private void runTestCase(String requestJson, String url, String resultJson) throws Exception {
    MvcResult mvcResult = mockMvc(url, requestJson);

    // 日付型を設定
    JsonDeserializer<Date> deser = (json, typeOfT, context) -> json == null ? null
        : new Date(Long.valueOf(json.getAsString().replaceAll("\\D", "")));
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, deser).create();
    Type responseType = new TypeToken<ResultOneDto<CustomerDetailVo>>() {}.getType();

    // APIコール
    ResultOneDto<CustomerDetailVo> res =
        gson.fromJson(mvcResult.getResponse().getContentAsString(), responseType);
    CustomerDetailVo actual = res.getItem();

    // 予想結果ロード
    CustomerDetailVo expected =
        gson.fromJson(testScriptUtils.loadJsonToString(resultJson), CustomerDetailVo.class);

    // 共通情報比較
    // 顧客ID
    assertEquals(expected.getCommonInfo().getContractorCustomerId(),
        actual.getCommonInfo().getContractorCustomerId());
    // 顧客名
    assertEquals(expected.getCommonInfo().getContractorNameKnj(),
        actual.getCommonInfo().getContractorNameKnj());
    // 顧客名（カナ）
    assertEquals(expected.getCommonInfo().getContractorNameKana(),
        actual.getCommonInfo().getContractorNameKana());
    // 生年月日
    assertEquals(expected.getCommonInfo().getContractorDateOfBirth(),
        actual.getCommonInfo().getContractorDateOfBirth());
    // 性別
    assertEquals(expected.getCommonInfo().getContractorSex(),
        actual.getCommonInfo().getContractorSex());

    // 顧客情報比較
    // 住所（郵便番号）
    assertEquals(expected.getCommonInfo().getContractorZipcode(),
        actual.getCommonInfo().getContractorZipcode());
    // メールアドレス
    assertEquals(expected.getCommonInfo().getContractorEmail(),
        actual.getCommonInfo().getContractorEmail());
    // 電話番号
    assertEquals(expected.getCommonInfo().getContractorTel(),
        actual.getCommonInfo().getContractorTel());

    // 保有契約一覧比較
    int sizeExpectedContracts = expected.getContractList().size();
    int sizeActualContracts = actual.getContractList().size();
    assertEquals(sizeExpectedContracts, sizeActualContracts);
    for (int i = 0; i < sizeExpectedContracts; i++) {
      // 証券番号
      assertEquals(expected.getContractList().get(i).getContractNo(),
          actual.getContractList().get(i).getContractNo());
      // 商品名
      assertEquals(expected.getContractList().get(i).getProductName(),
          actual.getContractList().get(i).getProductName());
      // ステータス
      assertEquals(expected.getContractList().get(i).getContractStatus(),
          actual.getContractList().get(i).getContractStatus());
      // 契約日
      assertEquals(expected.getContractList().get(i).getIssueDate(),
          actual.getContractList().get(i).getIssueDate());
      // 被保険者名
      assertEquals(expected.getContractList().get(i).getNameKnj(),
          actual.getContractList().get(i).getNameKnj());
      // 被保険者名（カナ）
      assertEquals(expected.getContractList().get(i).getNameKana(),
          actual.getContractList().get(i).getNameKana());
      // 被保険者生年月日
      assertEquals(expected.getContractList().get(i).getDateOfBirth(),
          actual.getContractList().get(i).getDateOfBirth());
      // 被保険者性別
      assertEquals(expected.getContractList().get(i).getSex(),
          actual.getContractList().get(i).getSex());
    }

    // 保障対象契約一覧比較
    int sizeExpectedInsureds = expected.getInsuredContractList().size();
    int sizeActualInsureds = actual.getInsuredContractList().size();
    assertEquals(sizeExpectedInsureds, sizeActualInsureds);
    for (int i = 0; i < sizeExpectedInsureds; i++) {
      // 証券番号
      assertEquals(expected.getInsuredContractList().get(i).getContractNo(),
          actual.getInsuredContractList().get(i).getContractNo());
      // 商品名
      assertEquals(expected.getInsuredContractList().get(i).getProductName(),
          actual.getInsuredContractList().get(i).getProductName());
      // ステータス
      assertEquals(expected.getInsuredContractList().get(i).getContractStatus(),
          actual.getInsuredContractList().get(i).getContractStatus());
      // 契約日
      assertEquals(expected.getInsuredContractList().get(i).getIssueDate(),
          actual.getInsuredContractList().get(i).getIssueDate());
      // 契約者名
      assertEquals(expected.getInsuredContractList().get(i).getNameKnj(),
          actual.getInsuredContractList().get(i).getNameKnj());
      // 契約者名（カナ）
      assertEquals(expected.getInsuredContractList().get(i).getNameKana(),
          actual.getInsuredContractList().get(i).getNameKana());
      // 契約者生年月日
      assertEquals(expected.getInsuredContractList().get(i).getDateOfBirth(),
          actual.getInsuredContractList().get(i).getDateOfBirth());
      // 契約者性別
      assertEquals(expected.getInsuredContractList().get(i).getSex(),
          actual.getInsuredContractList().get(i).getSex());
    }
  }

  /**
   * 各テストことにDB初期化
   *
   * @author : [VJP] タン
   * @throws SQLException
   * @throws ScriptException
   * @createdAt : 2021-07-13
   * @updatedAt : 2021-07-13
   */
  @AfterEach
  void clean() throws ScriptException, SQLException {
    testScriptUtils.cleanUpDatabase();
  }
}
