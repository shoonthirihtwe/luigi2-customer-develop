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
 * CustomerDetailMaintenanceRequestInfoVo
 * 画面ID:CHI_001
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
public class CustomerDetailMaintenanceRequestInfoVo extends ObjectVo {

  Integer id;
  /**
   * 処理名
   */
  String transactionCode;
  /**
   * 受付日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date receivedDate;
  /**
   * 処理日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date applyDate;
  /**
   * 申出経路
   */
  String applicationMethod;
  /**
   * 添付書類
   */
  String documentTitle;
  /**
   * 添付書類
   */
  String documentUrl;
  /**
   * 受付者コメント
   */
  String commentUnderweiter1;
  /**
   * 査定者コメント
   */
  String commentUnderweiter2;
  /**
   * 解除日以降の支払有無, 保険料の返還有無
   */
  String refundYn;
  /**
   * 変更内容
   */
  String description;
  /**
   * 保全申請番号
   */
  String requestNo;
  /**
   * 証券番号
   */
  String contractNo;

  String contractBranchNo;

  String requestStatus;
}
