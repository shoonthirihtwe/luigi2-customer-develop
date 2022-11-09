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
 * OG-240 SG-005 異動履歴詳細
 *
 * @author : [VJP] タン
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
class TestOg240 {

  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private TestScriptUtils testScriptUtils;

  private String sql = "sql/insert_OG210.sql";

  /**
   * OG-240 SG-005 異動履歴詳細 setup
   *
   * @author : [VJP] タン
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @BeforeEach
  void setup() throws SQLException {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript(sql);
  }

  /**
   * OG-240 SG-005 異動履歴詳細 テスト実行
   * 
   * #1_OG-240_SG-005
   * 
   * @author : [VJP] タン
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @Test
  void testOg24001() throws Exception {
    String requestJson = "json/request/og240/req_OG240_01_data.json";
    String url = "/OG24001";
    String resultJson = "json/response/og240/res_OG240_01_data.json";
    runTestCase(requestJson, url, resultJson);
  }

  /**
   * OG-240 SG-005 異動履歴詳細 テスト実行
   * 
   * #2_OG-240_SG-005
   * 
   * @author : [VJP] タン
   * @createdAt : 2021-08-06
   * @updatedAt : 2021-08-06
   */
  @Test
  void testOg24002() throws Exception {
    String requestJson = "json/request/og240/req_OG240_02_data.json";
    String url = "/OG24001";
    String resultJson = "json/response/og240/res_OG240_02_data.json";
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
   * @author : [VJP] タン
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
    // 処理名
    assertEquals(expected.get("transactionCode"), target.get("transactionCode"));
    // 受付日
    assertEquals(expected.get("receivedDate"), target.get("receivedDate"));
    // 処理日
    assertEquals(expected.get("applyDate"), target.get("applyDate"));
    // 申出経路
    assertEquals(null, target.get("applicationMethod"));
    // 添付書類
    assertEquals(null, target.get("documentTitle"));
    // 受付者コメント
    assertEquals(expected.get("commentUnderweiter1"), target.get("commentUnderweiter1"));
    // 査定者コメント
    assertEquals(expected.get("commentUnderweiter2"), target.get("commentUnderweiter2"));
    // 変更内容
    assertEquals(null, target.get("description"));
  }

  /**
   * 各テストことにDB初期化
   *
   * @author : [VJP] タン
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
