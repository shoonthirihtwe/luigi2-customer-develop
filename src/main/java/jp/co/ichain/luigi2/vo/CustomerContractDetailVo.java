package jp.co.ichain.luigi2.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jp.co.ichain.luigi2.config.web.JsonDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * CustomerDetailVo
 * 画面ID:SP_001
 *
 * @author : [AOT] g.kim
 * @createdAt : 2021-05-31
 * @updatedAt : 2021-05-31
 */
@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContractDetailVo extends ObjectVo {

  /**
   * 共通情報
   */
  CustomerDetailCommonInfoVo commonInfo;
  /**
   * 契約情報-基本情報
   */
  CustomerDetailBasicInfoVo basicInfo;
  /**
   * 契約情報-商品情報
   */
  CustomerDetailProductInfoVo productInfo;
  /**
   * 契約情報-契約者情報
   */
  CustomerDetailContractorInfoVo contractorInfo;
  /**
   * 契約情報-被保険者情報
   */
  CustomerDetailInsuredInfoVo insuredInfo;
  /**
   * 契約情報-保険料情報
   */
  CustomerDetailPremiumInfoVo premiumInfo;
  /**
   * 契約情報-募集代理店情報
   */
  CustomerDetailAgencyInfoVo agencyInfo;
  /**
   * 告知情報
   */
  CustomerDetailNoticeInfoVo noticeInfo;
  /**
   * 異動等情報
   */
  List<CustomerDetailMaintenanceRequestInfoVo> maintenanceRequestInfo;
  /**
   * 支払情報
   */
  List<CustomerDetailPaymentInfoVo> paymentInfo;
  /**
   * 収納情報
   */
  List<CustomerDetailBillingInfoVo> billingInfo;


  @JsonSerialize(using = JsonDateSerializer.class)
  Date cardUnavailableFlag;
}
