package jp.co.ichain.luigi2.condition;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jp.co.ichain.luigi2.validity.Condition;

/**
 * 固有一般条件検証サービス
 * 
 * @author : [AOT] s.paku
 * @createdAt : 2021-06-10
 * @updatedAt : 2021-06-10
 */
@Service
@Condition
public class InherentNomalCondition {

  /**
   * 顧客検索パラメーターチェック
   * 
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-22
   * @updatedAt : 2021-06-22
   * @param data
   * @param max
   * @return
   */
  @SuppressWarnings("unchecked")
  public boolean checkSearchKeyword(Object data, Integer tenantId, List<Object> argsList) {

    Map<String, Object> paramMap = ((Map<String, Object>) data);
    String keyword = paramMap.get("keyword").toString();
    String englishAndNumberRex = argsList.get(0).toString();
    String kanaRex = argsList.get(1).toString();

    if (!keyword.matches(englishAndNumberRex + "|" + kanaRex)) {
      return false;
    }

    // 英数字チェック
    if (keyword.matches(englishAndNumberRex)) {
      paramMap.put("isSearchWithName", false);
      return true;
      // カナチェック
    } else if (keyword.matches(kanaRex)) {
      paramMap.put("isSearchWithName", true);
      return true;
    }

    return false;
  }

}
