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
 * CustomerDetailCommonInfoVo
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
public class CustomerDetailBasicInfoVo extends ObjectVo {

  /**
   * 証券番号
   */
  String contractNo;
  /**
   * 証券番号枝番
   */
  String contractBranchNo;
  /**
   * 契約ステータス
   */
  String contractStatus;
  /**
   * 申込方法
   */
  String salesMethod;
  /**
   * 申込日時
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date applicationDate;
  /**
   * 成立日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date completeDate;
  /**
   * 責任開始日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date effectiveDate;
  /**
   * 更新回数
   */
  Integer updateCnt;
  /**
   * 現契約日（更新日）
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date issueDate;
  /**
   * 契約終了（予定）日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date expirationDate;

}
