package jp.co.ichain.luigi2.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * CustomerDetailAgencyInfoVo
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
public class CustomerDetailAgencyInfoVo extends ObjectVo {

  /**
   * 主代理店名
   */
  String agencyName1;
  /**
   * 主募集人名
   */
  String agentName1;
  /**
   * 主募集割合
   */
  Integer agentShare1;
  /**
   * 従代理店名
   */
  String agencyName2;
  /**
   * 従募集人名
   */
  String agentName2;
  /**
   * 従募集割合
   */
  Integer agentShare2;

}
