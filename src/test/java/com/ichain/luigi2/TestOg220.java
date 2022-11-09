package com.ichain.luigi2;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
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
import jp.co.ichain.luigi2.Luigi2Application;
import jp.co.ichain.luigi2.test.TestScriptUtils;

/**
 * OG-220 SG-003 保険料収納情報
 *
 * @author : [VJP] HoangNH
 * @createdAt : 2021-08-06
 * @updatedAt : 2021-08-06
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = Luigi2Application.class)
@TestPropertySource("classpath:application-common-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class TestOg220 {

  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private TestScriptUtils testScriptUtils;

  private String sql = "sql/insert_OG210.sql";

  /**
   * OG-220 SG-003 保険料収納情報setup
   *
   * @author : [VJP] HoangNH
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @BeforeEach
  void setup() throws SQLException {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript(sql);
    // 追加SQL
    testScriptUtils.executeSqlScript("sql/insert_OG220.sql");
  }

  /**
   * OG-220 SG-003 保険料収納情報 テスト実行
   * 
   * #1_OG-220_SG-003
   * 
   * @author : [VJP] HoangNH
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @Test
  void testOg22001() throws Exception {
    String requestJson = "json/request/og220/req_OG220_01_data.json";
    String url = "/OG22001";
    String resultJson = "json/response/og220/res_OG220_01_data.json";
    runTestCase(requestJson, url, resultJson);
  }

  /**
   * OG-220 SG-003 保険料収納情報 テスト実行
   * 
   * #2_OG-220_SG-003
   * 
   * @author : [VJP] HoangNH
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @Test
  void testOg22002() throws Exception {
    String requestJson = "json/request/og220/req_OG220_02_data.json";
    String url = "/OG22001";
    String resultJson = "json/response/og220/res_OG220_02_data.json";
    runTestCase(requestJson, url, resultJson);
  }

  /**
   * Mock request
   * 
   * @author : [VJP] HoangNH
   * @param sql
   * @param url
   * @param requestJson
   * @return
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
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
   * @author : [VJP] HoangNH
   * @param requestJson
   * @param url
   * @param resultJson
   * @throws Exception
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @SuppressWarnings("unchecked")
  private void runTestCase(String requestJson, String url, String resultJson) throws Exception {
    MvcResult mvcResult = mockMvc(url, requestJson);

    ObjectMapper mapper = new ObjectMapper();

    // APIコール
    HashMap<String, String> target = (HashMap<String, String>) mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class).get("item");

    // 予想結果ロード
    HashMap<String, String> expected =
        (HashMap<String, String>) testScriptUtils.loadJsonToObject(resultJson);
    // 結果比較
    // 着金日
    assertEquals(expected.get("depositDate"), target.get("depositDate"));
    // 充当月
    assertEquals(expected.get("premiumDueDate"), target.get("premiumDueDate"));
    // 払込方法
    assertEquals(expected.get("paymentMethodCode"), target.get("paymentMethodCode"));
  }

  /**
   * 各テストことにDB初期化
   *
   * @author : [VJP] HoangNH
   * @throws SQLException
   * @throws ScriptException
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @AfterEach
  void clean() throws ScriptException, SQLException {
    testScriptUtils.cleanUpDatabase();
  }
}
