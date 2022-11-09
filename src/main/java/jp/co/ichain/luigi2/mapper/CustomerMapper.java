package jp.co.ichain.luigi2.mapper;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.ichain.luigi2.config.datasource.Luigi2Mapper;
import jp.co.ichain.luigi2.vo.BillingInfoVo;
import jp.co.ichain.luigi2.vo.CustomerBenefitInfoVo;
import jp.co.ichain.luigi2.vo.CustomerContractDetailVo;
import jp.co.ichain.luigi2.vo.CustomerContractInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailBillingInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailMaintenanceRequestInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailPaymentInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailVo;
import jp.co.ichain.luigi2.vo.CustomersListVo;
import jp.co.ichain.luigi2.vo.PaymentInfoVo;

/**
 * CustomerMapper
 *
 * @author : [AOT] g.kim
 * @createdAt : 2021-05-31
 * @updatedAt : 2021-05-31
 */
@Repository
@Luigi2Mapper
public interface CustomerMapper {

  List<CustomersListVo> getCustomerWithName(Map<String, Object> param);

  List<CustomersListVo> searchCustomers(Map<String, Object> param);

  int searchContractsTotalCount(Map<String, Object> param);

  CustomerContractDetailVo getCustomerContractWithContractNo(Map<String, Object> param);

  List<CustomerDetailPaymentInfoVo> getCustomerPaymentInfoList(Map<String, Object> param);

  List<CustomerDetailBillingInfoVo> getCustomerBillingInfoList(Map<String, Object> param);

  List<CustomerDetailMaintenanceRequestInfoVo> getCustomerMaintenanceRequestInfoList(
      Map<String, Object> param);

  CustomerDetailMaintenanceRequestInfoVo getMaintenanceRequestInfo(Map<String, Object> param);

  PaymentInfoVo getPaymentInfo(Map<String, Object> param);

  BillingInfoVo getBillingInfo(Map<String, Object> param);

  CustomerDetailVo getCustomerWithCustomerId(Map<String, Object> param);

  List<CustomerContractInfoVo> getContractInfo(Map<String, Object> param);

  List<CustomerContractInfoVo> getInsuredContractInfo(Map<String, Object> param);
  
  List<CustomerBenefitInfoVo> getBenefitInfo(Map<String, Object> param);
}
