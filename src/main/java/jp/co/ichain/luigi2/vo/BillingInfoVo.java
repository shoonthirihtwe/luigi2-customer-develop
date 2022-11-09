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
 * BillingInfoVo
 * 画面ID:PPI_001
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
public class BillingInfoVo extends ObjectVo {

  /**
   * 充当月
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date premiumDueDate;
  /**
   * 着金日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date depositDate;
  /**
   * 払込方法
   */
  String paymentMethodCode;
  /**
   * 金融機関コード
   */
  String bankCode;
  /**
   * 支店コード
   */
  String bankBranchCode;
  /**
   * 口座種別
   */
  String bankAccountType;
  /**
   * 口座番号
   */
  String bankAccountNo;
}
