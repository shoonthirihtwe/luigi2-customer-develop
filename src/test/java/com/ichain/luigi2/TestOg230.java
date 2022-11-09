package com.ichain.luigi2;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.ichain.luigi2.Luigi2Application;
import jp.co.ichain.luigi2.test.TestScriptUtils;

/**
 * OE002 test
 *
 * @author : [VJP] hale
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
class TestOg230 {

  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private TestScriptUtils testScriptUtils;

  /**
   * OG-230 setup
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @BeforeEach
  void setup() throws SQLException {

    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
  }

  /**
   * OG-23001 テスト実行
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @SuppressWarnings("unchecked")
  @Test
  void test_Og23001() throws Exception {
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript("sql/insert_OG210.sql");

    // APIコール
    MvcResult mvcResult =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/OG23001").header("x-frontend-domain", "localhost")
                .params(testScriptUtils
                    .loadJsonToMultiValueMap("json/request/og230/req_OG23001_data.json"))
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();

    ObjectMapper mapper = new ObjectMapper();
    HashMap<String, String> result = (HashMap<String, String>) mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class).get("item");

    // 予想結果ロード
    List<HashMap<String, String>> expectedResult = (List<HashMap<String, String>>) testScriptUtils
        .loadJsonToObject("json/response/og230/res_OG23001_data.json");

    // // 検索結果件数
    assertEquals(1, expectedResult.size());
    //
    for (int i = 0; i < expectedResult.size(); i++) {
      assertEquals(result.get("receivedDate"), expectedResult.get(i).get("receivedDate"));
      assertEquals(result.get("dueDate"), expectedResult.get(i).get("dueDate"));
      assertEquals(result.get("claimantKnj"), expectedResult.get(i).get("claimantKnj"));
      assertEquals(result.get("bankCode"), expectedResult.get(i).get("bankCode"));
      assertEquals(result.get("bankBranchCode"), expectedResult.get(i).get("bankBranchCode"));
      assertEquals(result.get("bankAccountType"), expectedResult.get(i).get("bankAccountType"));
      assertEquals(result.get("bankAccountNo"), expectedResult.get(i).get("bankAccountNo"));
    }
  }

  /**
   * OG-23002 テスト実行
   *
   * @author : [VJP] hale
   * @createdAt : 2021-08-05
   * @updatedAt : 2021-08-05
   */
  @SuppressWarnings("unchecked")
  @Test
  void test_Og23002() throws Exception {
    // テストに必要なSQL実行
    testScriptUtils.executeSqlScript("sql/insert_OG210.sql");

    // APIコール
    MvcResult mvcResult =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/OG23001").header("x-frontend-domain", "localhost")
                .params(testScriptUtils
                    .loadJsonToMultiValueMap("json/request/og230/req_OG23002_data.json"))
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();

    ObjectMapper mapper = new ObjectMapper();
    HashMap<String, String> result = (HashMap<String, String>) mapper
        .readValue(mvcResult.getResponse().getContentAsString(), HashMap.class).get("item");

    // 予想結果ロード
    List<HashMap<String, String>> expectedResult = (List<HashMap<String, String>>) testScriptUtils
        .loadJsonToObject("json/response/og230/res_OG23002_data.json");

    // 検索結果件数
    assertEquals(1, expectedResult.size());

    for (int i = 0; i < expectedResult.size(); i++) {
      assertEquals(result.get("receivedDate"), expectedResult.get(i).get("receivedDate"));
      assertEquals(result.get("dueDate"), expectedResult.get(i).get("dueDate"));
      assertEquals(result.get("claimantKnj"), expectedResult.get(i).get("claimantKnj"));
      assertEquals(result.get("bankCode"), expectedResult.get(i).get("bankCode"));
      assertEquals(result.get("bankBranchCode"), expectedResult.get(i).get("bankBranchCode"));
      assertEquals(result.get("bankAccountType"), expectedResult.get(i).get("bankAccountType"));
      assertEquals(result.get("bankAccountNo"), expectedResult.get(i).get("bankAccountNo"));
    }
  }

  /**
   * 各テストことにDB初期化
   *
   * @author : [VJP] hale
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
