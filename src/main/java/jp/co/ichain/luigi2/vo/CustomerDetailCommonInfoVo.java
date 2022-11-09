package jp.co.ichain.luigi2.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
public class CustomerDetailCommonInfoVo extends ObjectVo {

  /**
   * 証券番号
   */
  String contractNo;
  /**
   * 証券番号枝番
   */
  String contractBranchNo;
  /**
   * 保険商品
   */
  String salesPlanName;
  /**
   * 契約者名
   */
  String contractorNameKnj;

}
