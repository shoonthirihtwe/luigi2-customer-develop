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
 * CustomerContractInfoVo
 *
 * @author : [AOT] g.kim
 * @createdAt : 2021-06-28
 * @updatedAt : 2021-06-28
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContractInfoVo extends ObjectVo {

  /**
   * 証券番号
   */
  String contractNo;
  /**
   * 被保険者名, 契約者名
   */
  String nameKnj;
  /**
   * 被保険者名（カナ）,契約者名（カナ）
   */
  String nameKana;
  /**
   * 契約者生年月日, 被保険者生年月日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date dateOfBirth;
  /**
   * 契約者性別, 被保険者性別
   */
  String sex;
  /**
   * ステータス
   */
  String contractStatus;
  /**
   * 商品名
   */
  String productName;
  /**
   * 契約日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date issueDate;

}
