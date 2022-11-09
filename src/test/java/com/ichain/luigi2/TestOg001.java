package com.ichain.luigi2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
import org.springframework.test.web.servlet.ResultMatcher;
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
 * OG-001 クイック検索
 *
 * @author : [VJP] N.H.Hoang
 * @createdAt : 2021-07-29
 * @updatedAt : 2021-07-29
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = Luigi2Application.class)
@TestPropertySource("classpath:application-common-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class TestOg001 {

  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private TestScriptUtils testScriptUtils;

  private String sql = "sql/insert_OG001.sql";

  /**
   * OG-001 クイック検索 setup
   *
   * @author : [VJP] N.H.Hoang
   * @createdAt : 2021-07-29
   * @updatedAt : 2021-07-29
   */
  @BeforeEach
  void setup() throws SQLException {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript(sql);
  }

  /**
   * OG-001 クイック検索 テスト実行
   * 
   * 結果：エラー（証券番号該当無し）
   * 
   * @author : [VJP] N.H.Hoang
   * @createdAt : 2021-07-29
   * @updatedAt : 2021-07-29
   */
  @Test
  void testOd001DataResponseNull() throws Exception {
    // ケース１；要件：顧客/契約情報検索 （入力無し） 結果：エラー（証券番号該当無し）
    testResponseNull("json/request/og001/req_OG001_01_data.json");
    // ケース２；要件：0000000100 結果：エラー（証券番号該当無し）
    testResponseNull("json/request/og001/req_OG001_02_data.json");
    // ケース３；要件：A 結果：エラー（証券番号該当無し）
    testResponseNull("json/request/og001/req_OG001_03_data.json");
  }

  /**
   * ケース４；顧客/契約情報検索 0000000101 結果； 「契約詳細表示画面：SG-002」へ
   * 
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  @Test
  void testOd00104() throws Exception {
    String requestJson = "json/request/og001/req_OG001_04_data.json";
    String url = "/OG00101";
    MvcResult mvcResult = mockMvc(url, requestJson, MockMvcResultMatchers.status().isOk());
    ObjectMapper mapper = new ObjectMapper();
    // 予想結果ロード
    HashMap<String, String> result = (HashMap<String, String>) mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class).get("item");
    // 検索結果件数
    assertEquals(result.size() > 0, true);
  }

  /**
   * ケース５；顧客/契約情報検索 イ 結果；「顧客一覧表示画面：SG-006」へ
   * 
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  @Test
  void testOd00105() throws Exception {
    String requestJson = "json/request/og001/req_OG001_05_data.json";
    String url = "/OG00101";
    MvcResult mvcResult = mockMvc(url, requestJson, MockMvcResultMatchers.status().isOk());
    ObjectMapper mapper = new ObjectMapper();
    // 予想結果ロード
    List<HashMap<String, String>> result = (List<HashMap<String, String>>) mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class).get("items");
    // 検索結果件数
    assertEquals(result.size() > 0, true);

  }

  /**
   * OG-001 クイック検索 テスト実行
   * 
   * ケース６；要件：顧客/契約情報検索 イト 結果：エラー（証券番号該当無し）
   * 
   * @author : [VJP] N.H.Hoang
   * @createdAt : 2021-07-29
   * @updatedAt : 2021-07-29
   */
  @Test
  @SuppressWarnings("unchecked")
  void testOd00106() throws Exception {
    String requestJson = "json/request/og001/req_OG001_06_data.json";
    String url = "/OG00101";
    String resultJson = "json/response/og001/res_OG001_06_data.json";
    MvcResult mvcResult = mockMvc(url, requestJson, MockMvcResultMatchers.status().isOk());
    ObjectMapper mapper = new ObjectMapper();
    // 予想結果ロード
    HashMap<String, String> expected = mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class);

    HashMap<String, String> target =
        (HashMap<String, String>) testScriptUtils.loadJsonToObject(resultJson);
    // 検索結果件数
    assertEquals(expected.get("code"), target.get("code"));
    assertEquals(expected.get("count"), target.get("count"));
    assertEquals(0, target.get("count"));
    assertEquals(expected.get("totalCount"), target.get("totalCount"));
  }

  /**
   * Mock request
   * 
   * @param sql
   * @param url
   * @param requestJson
   * @return
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   */
  private MvcResult mockMvc(String url, String requestJson, ResultMatcher resultStatus)
      throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException,
      Exception {
    // APIコール
    return mockMvc.perform(MockMvcRequestBuilders.get(url).header("x-frontend-domain", "localhost")
        .params(testScriptUtils.loadJsonToMultiValueMap(requestJson))
        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .accept(MediaType.APPLICATION_JSON)).andExpect(resultStatus).andDo(print()).andReturn();
  }

  /**
   * Mock request when response none
   * 
   * @param requestJson
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private void testResponseNull(String requestJson) throws JsonParseException, JsonMappingException,
      UnsupportedEncodingException, IOException, Exception {
    String url = "/OG00101";
    String resultJson = "json/response/og001/res_OG001_No_Data.json";
    MvcResult mvcResult =
        mockMvc(url, requestJson, MockMvcResultMatchers.status().is5xxServerError());
    ObjectMapper mapper = new ObjectMapper();
    // 予想結果ロード
    HashMap<String, String> result = mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class);

    HashMap<String, String> target =
        (HashMap<String, String>) testScriptUtils.loadJsonToObject(resultJson);
    // 検索結果件数
    assertEquals(1, result.size());
    assertTrue(target.equals(result));
  }

  /**
   * 各テストことにDB初期化
   *
   * @author : [AOT] g.kim
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
