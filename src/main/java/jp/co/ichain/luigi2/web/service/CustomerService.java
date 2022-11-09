package jp.co.ichain.luigi2.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.ichain.luigi2.dto.ResultListDto;
import jp.co.ichain.luigi2.dto.ResultOneDto;
import jp.co.ichain.luigi2.dto.ResultWebDto;
import jp.co.ichain.luigi2.exception.WebConditionException;
import jp.co.ichain.luigi2.exception.WebException;
import jp.co.ichain.luigi2.exception.WebParameterException;
import jp.co.ichain.luigi2.mapper.CommonMapper;
import jp.co.ichain.luigi2.mapper.CustomerMapper;
import jp.co.ichain.luigi2.resources.Luigi2ErrorCode;
import jp.co.ichain.luigi2.service.CommonContractService;
import jp.co.ichain.luigi2.vo.BillingInfoVo;
import jp.co.ichain.luigi2.vo.CustomerContractDetailVo;
import jp.co.ichain.luigi2.vo.CustomerDetailMaintenanceRequestInfoVo;
import jp.co.ichain.luigi2.vo.CustomerDetailVo;
import jp.co.ichain.luigi2.vo.CustomersListVo;
import jp.co.ichain.luigi2.vo.PaymentInfoVo;
import lombok.val;

/**
 * CustomerService
 *
 * @author : [AOT] g.kim
 * @createdAt : 2021-06-21
 * @updatedAt : 2021-06-21
 */
@Service
public class CustomerService {
  @Autowired
  CustomerMapper mapper;
  @Autowired
  CommonMapper commonMapper;
  @Autowired
  CommonContractService commonContractService;

  /**
   * クイック検索
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-21
   * @updatedAt : 2021-06-21
   * @return
   * @throws SecurityException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  @Transactional(transactionManager = "luigi2TransactionManager", readOnly = true)
  public ResultWebDto quickSearchCustomer(Map<String, Object> param) throws SecurityException,
      IllegalArgumentException, IllegalAccessException, InstantiationException, IOException {

    ResultWebDto result = null;
    if ((boolean) param.get("isSearchWithName")) {
      val searchedCustomer = mapper.getCustomerWithName(param);

      result = new ResultListDto<CustomersListVo>();
      ((ResultListDto<CustomersListVo>) result).setItems(searchedCustomer);
      ((ResultListDto<CustomersListVo>) result)
          .setTotalCount(mapper.searchContractsTotalCount(param));
      return result;
    } else {
      param.put("contractNo", param.get("keyword"));
      val searchedCustomer = mapper.getCustomerContractWithContractNo(param);
      if (searchedCustomer != null) {
        param.put("contractBranchNo", searchedCustomer.getCommonInfo().getContractBranchNo());
        searchedCustomer.setPaymentInfo(mapper.getCustomerPaymentInfoList(param));
        searchedCustomer.setBillingInfo(mapper.getCustomerBillingInfoList(param));
        searchedCustomer
            .setMaintenanceRequestInfo(mapper.getCustomerMaintenanceRequestInfoList(param));
      } else {
        throw new WebException(Luigi2ErrorCode.D0002);
      }
      result = new ResultOneDto<CustomerContractDetailVo>();
      ((ResultOneDto<CustomerContractDetailVo>) result).setItem(searchedCustomer);
      return result;
    }
  }

  /**
   * 顧客一覧検索
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-07-21
   * @updatedAt : 2021-07-21
   * @return
   * @throws SecurityException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException
   */
  @Transactional(transactionManager = "luigi2TransactionManager", readOnly = true)
  public ResultListDto<CustomersListVo> searchCustomer(Map<String, Object> param)
      throws SecurityException, IllegalArgumentException, IllegalAccessException,
      InstantiationException, IOException {

    val searchedCustomer = mapper.searchCustomers(param);

    val result = new ResultListDto<CustomersListVo>();
    result.setItems(searchedCustomer);
    result.setTotalCount(mapper.searchContractsTotalCount(param));
    return result;

  }

  /**
   * 顧客契約詳細
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-21
   * @updatedAt : 2021-06-21
   * @return
   * @throws SecurityException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException
   */
  @Transactional(transactionManager = "luigi2TransactionManager", readOnly = true)
  public ResultOneDto<CustomerContractDetailVo> getCustomerContract(Map<String, Object> param)
      throws SecurityException, IllegalArgumentException, IllegalAccessException,
      InstantiationException, IOException {

    val searchedContract = mapper.getCustomerContractWithContractNo(param);

    if (searchedContract != null) {
      param.put("contractBranchNo", searchedContract.getBasicInfo().getContractBranchNo());
      param.put("baseDate", ((Date) param.get("onlineDate")).getTime());
      searchedContract.setPaymentInfo(mapper.getCustomerPaymentInfoList(param));
      searchedContract.setBillingInfo(mapper.getCustomerBillingInfoList(param));
      searchedContract
          .setMaintenanceRequestInfo(mapper.getCustomerMaintenanceRequestInfoList(param));

      searchedContract.getProductInfo()
          .setBenefitInfo(commonContractService.getRiskHeaders(param).getBenefits());
    } else {
      List<WebException> exList = new ArrayList<WebException>();
      exList.add(new WebConditionException(Luigi2ErrorCode.D0002, "contractNo"));
      throw new WebParameterException(Luigi2ErrorCode.V0000, exList);
    }

    val result = new ResultOneDto<CustomerContractDetailVo>();
    result.setItem(searchedContract);
    return result;
  }

  /**
   * 顧客詳細
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-28
   * @updatedAt : 2021-06-28
   * @return
   * @throws SecurityException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException
   */
  @Transactional(transactionManager = "luigi2TransactionManager", readOnly = true)
  public ResultOneDto<CustomerDetailVo> getCustomer(Map<String, Object> param)
      throws SecurityException, IllegalArgumentException, IllegalAccessException,
      InstantiationException, IOException {

    val searchedCustomer = mapper.getCustomerWithCustomerId(param);
    if (searchedCustomer != null) {
      param.put("customerId", searchedCustomer.getCommonInfo().getContractorCustomerId());
      searchedCustomer.setContractList(mapper.getContractInfo(param));

      searchedCustomer.setInsuredContractList(mapper.getInsuredContractInfo(param));
    }

    val result = new ResultOneDto<CustomerDetailVo>();
    result.setItem(searchedCustomer);
    return result;
  }

  /**
   * 保険料収納情報
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-25
   * @updatedAt : 2021-06-25
   * @return
   * @throws SecurityException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException
   */
  @Transactional(transactionManager = "luigi2TransactionManager", readOnly = true)
  public ResultOneDto<BillingInfoVo> getBillingInfo(Map<String, Object> param)
      throws SecurityException, IllegalArgumentException, IllegalAccessException,
      InstantiationException, IOException {

    val billingInfo = mapper.getBillingInfo(param);

    val result = new ResultOneDto<BillingInfoVo>();
    result.setItem(billingInfo);
    return result;
  }

  /**
   * 支払情報取得
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-30
   * @updatedAt : 2021-06-30
   * @return
   * @throws SecurityException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException
   */
  @Transactional(transactionManager = "luigi2TransactionManager", readOnly = true)
  public ResultOneDto<PaymentInfoVo> getPaymentInfo(Map<String, Object> param)
      throws SecurityException, IllegalArgumentException, IllegalAccessException,
      InstantiationException, IOException {

    val result = new ResultOneDto<PaymentInfoVo>();
    result.setItem(mapper.getPaymentInfo(param));
    return result;
  }

  /**
   * 異動履歴詳細
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-07-26
   * @updatedAt : 2021-07-26
   * @return
   * @throws SecurityException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException
   */
  @Transactional(transactionManager = "luigi2TransactionManager", readOnly = true)
  public ResultOneDto<CustomerDetailMaintenanceRequestInfoVo> getMaintenanceRequest(
      Map<String, Object> param) throws SecurityException, IllegalArgumentException,
      IllegalAccessException, InstantiationException, IOException {

    val result = new ResultOneDto<CustomerDetailMaintenanceRequestInfoVo>();
    result.setItem(mapper.getMaintenanceRequestInfo(param));
    return result;
  }
}
