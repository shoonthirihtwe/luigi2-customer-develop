package jp.co.ichain.luigi2.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jp.co.ichain.luigi2.config.web.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * CustomerDetailProducInfoVo
 *
 * @author : [AOT] g.kim
 * @createdAt : 2021-05-31
 * @updatedAt : 2021-05-31
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailInsuredInfoVo extends ObjectVo {

  /**
   * 被保険者ID
   */
  String insuredCustomerId;
  /**
   * 被保険者名
   */
  String insuredNameKnj;
  /**
   * 被保険者名（カナ）
   */
  String insuredNameKana;
  /**
   * 生年月日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date insuredDateOfBirth;
  /**
   * 性別
   */
  String insuredSex;
  /**
   * 契約者から見た続柄
   */
  String relationship;
  /**
   * 住所（郵便番号）
   */
  String insuredZipcode;
  /**
   * 電話番号
   */
  String insuredTel;
  
}
