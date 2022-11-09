package com.ichain.luigi2;

import static org.junit.Assert.assertEquals;
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
 * OG-520 test
 *
 * @author : [VJP] hale
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
class TestOg520 {

  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private TestScriptUtils testScriptUtils;

  /**
   * OG-520 setup
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-03
   * @updatedAt : 2021-08-03
   */
  @BeforeEach
  void setup() throws SQLException {
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript("sql/insert_OG210.sql");

    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();

  }

  /**
   * OG-52001 テスト実行
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-03
   * @updatedAt : 2021-08-03
   */
  @Test
  void test_Og52001() throws Exception {
    String requestUrlForGet = "json/request/og520/req_OG52001_data.json";
    String resultJson = "json/response/og520/res_OG52001_data.json";
    int actualIndex = 2;

    // APIコール
    MvcResult mvcResult = mockMvcForGet(requestUrlForGet);

    // 検索結果
    compareData(mvcResult, resultJson, actualIndex);
  }

  /**
   * OG-52002 テスト実行
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-03
   * @updatedAt : 2021-08-03
   */
  @Test
  void test_Og52002() throws Exception {
    String requestUrlForGet = "json/request/og520/req_OG52002_data.json";
    String resultJson = "json/response/og520/res_OG52002_data.json";
    int actualIndex = 1;

    // APIコール
    MvcResult mvcResult = mockMvcForGet(requestUrlForGet);

    // 検索結果
    compareData(mvcResult, resultJson, actualIndex);

  }

  /**
   * OG-52003 テスト実行
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-02
   * @updatedAt : 2021-08-02
   */
  @Test
  void test_Og52003() throws Exception {
    String requestUrlForGet = "json/request/og520/req_OG52003_data.json";
    // APIコール
    MvcResult mvcResult = mockMvcForGet(requestUrlForGet);

    ObjectMapper mapper = new ObjectMapper();
    @SuppressWarnings("unchecked")
    List<HashMap<String, String>> result = (List<HashMap<String, String>>) mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class).get("items");

    // 検索結果件数
    assertEquals(result.size(), 0);
  }

  /**
   * OG-52004 テスト実行
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-02
   * @updatedAt : 2021-08-02
   */
  @Test
  void test_Og52004() throws Exception {
    String requestUrlForGet = "json/request/og520/req_OG52004_data.json";
    String resultJson = "json/response/og520/res_OG52004_data.json";
    int actualIndex = 2;

    // APIコール
    MvcResult mvcResult = mockMvcForGet(requestUrlForGet);

    // 検索結果
    compareData(mvcResult, resultJson, actualIndex);

  }

  /**
   * OG-52005 テスト実行
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-02
   * @updatedAt : 2021-08-02
   */
  @Test
  void test_Og52005() throws Exception {
    String requestUrlForGet = "json/request/og520/req_OG52005_data.json";
    String resultJson = "json/response/og520/res_OG52005_data.json";
    int actualIndex = 1;

    // APIコール
    MvcResult mvcResult = mockMvcForGet(requestUrlForGet);

    // 検索結果
    compareData(mvcResult, resultJson, actualIndex);

  }

  /**
   * Mock request for get
   * 
   * @author : [VJP] hale
   * @param requestJson
   * @return
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws UnsupportedEncodingException
   * @throws IOException
   * @throws Exception
   * @createdAt : 2021-08-02
   * @updatedAt : 2021-08-02
   */
  private MvcResult mockMvcForGet(String requestJson) throws JsonParseException,
      JsonMappingException, UnsupportedEncodingException, IOException, Exception {
    // APIコール
    return mockMvc
        .perform(MockMvcRequestBuilders.get("/OG52001").header("x-frontend-domain", "localhost")
            .params(testScriptUtils.loadJsonToMultiValueMap(requestJson))
            .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();
  }

  /**
   * 色なテストケースを実行
   * 
   * @author : [VJP] hale
   * @param mvcResult
   * @param requestJson
   * @param actualIndex
   * @throws Exception
   * @createdAt : 2021-08-02
   * @updatedAt : 2021-08-02
   */
  @SuppressWarnings("unchecked")
  private void compareData(MvcResult mvcResult, String resultJson, int actualIndex)
      throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    List<HashMap<String, String>> result = (List<HashMap<String, String>>) mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class).get("items");

    // 予想結果ロード
    List<HashMap<String, String>> expectedResult =
        (List<HashMap<String, String>>) testScriptUtils.loadJsonToObject(resultJson);

    // 検索結果件数
    assertEquals(actualIndex, result.size());
    assertEquals(actualIndex, expectedResult.size());

    for (int i = 0; i < expectedResult.size(); i++) {
      assertEquals(result.get(i).get("customerId"), expectedResult.get(i).get("customerId"));
      assertEquals(result.get(i).get("nameKana"), expectedResult.get(i).get("nameKana"));
      assertEquals(result.get(i).get("nameKnj"), expectedResult.get(i).get("nameKnj"));
      assertEquals(result.get(i).get("dateOfBirth"), expectedResult.get(i).get("dateOfBirth"));
      assertEquals(result.get(i).get("sex"), expectedResult.get(i).get("sex"));
      assertEquals(result.get(i).get("zipCode"), expectedResult.get(i).get("zipCode"));
    }
  }



  /**
   * 各テストことにDB初期化
   *
   * @author : [VJP] hale
   * @throws SQLException
   * @throws ScriptException
   * @createdAt : 2021-08-03
   * @updatedAt : 2021-08-03
   */
  @AfterEach
  void clean() throws ScriptException, SQLException {
    testScriptUtils.cleanUpDatabase();
  }

}
