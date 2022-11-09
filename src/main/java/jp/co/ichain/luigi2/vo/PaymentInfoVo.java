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
 * PaymentInfoVo
 * 画面ID:BRI_001
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
public class PaymentInfoVo extends ObjectVo {

  /*
   * 受付日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date receivedDate;
  /*
   * 支払日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date dueDate;
  /*
   * 受取人
   */
  String claimantKnj;
  /*
   * 金融機関コード
   */
  String bankCode;
  /*
   * 支店コード
   */
  String bankBranchCode;
  /*
   * 口座種別
   */
  String bankAccountType;
  /*
   * 口座番号
   */
  String bankAccountNo;
}
