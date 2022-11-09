package jp.co.ichain.luigi2.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.ichain.luigi2.dto.ResultWebDto;
import jp.co.ichain.luigi2.resources.Luigi2Endpoint;
import jp.co.ichain.luigi2.util.ControllerUtils;
import jp.co.ichain.luigi2.web.service.CustomerService;

/**
 * 顧客コントロール
 * 
 * @author : [AOT] g.kim
 * @createdAt : 2021-06-21
 * @updatedAt : 2021-06-21
 */
@Controller
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @Autowired
  ControllerUtils controllerUtils;

  /**
   * 顧客クイック検索
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-21
   * @updatedAt : 2021-06-21
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Produces(MediaType.APPLICATION_JSON)
  @RequestMapping(value = "/OG00101", method = {RequestMethod.GET})
  public @ResponseBody ResultWebDto getCustomer(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return controllerUtils.makeGetControllerHandler(request, Luigi2Endpoint.OG01001, (param) -> {
      return customerService.quickSearchCustomer(param);
    });
  }

  /**
   * 顧客一覧検索
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-07-21
   * @updatedAt : 2021-07-21
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Produces(MediaType.APPLICATION_JSON)
  @RequestMapping(value = "/OG52001", method = {RequestMethod.GET})
  public @ResponseBody ResultWebDto searchCustomer(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return controllerUtils.makeGetControllerHandler(request, Luigi2Endpoint.OG52001, (param) -> {
      return customerService.searchCustomer(param);
    });
  }

  /**
   * 顧客契約詳細
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-21
   * @updatedAt : 2021-06-21
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Produces(MediaType.APPLICATION_JSON)
  @RequestMapping(value = "/OG21001", method = {RequestMethod.GET})
  public @ResponseBody ResultWebDto getCustomerContractDetail(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return controllerUtils.makeGetControllerHandler(request, Luigi2Endpoint.OG21001, (param) -> {
      return customerService.getCustomerContract(param);
    });
  }

  /**
   * 顧客詳細
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-28
   * @updatedAt : 2021-06-28
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Produces(MediaType.APPLICATION_JSON)
  @RequestMapping(value = "/OG61001", method = {RequestMethod.GET})
  public @ResponseBody ResultWebDto getCustomerDetail(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return controllerUtils.makeGetControllerHandler(request, Luigi2Endpoint.OG61001, (param) -> {
      return customerService.getCustomer(param);
    });
  }

  /**
   * 保険料収納情報
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-25
   * @updatedAt : 2021-06-25
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Produces(MediaType.APPLICATION_JSON)
  @RequestMapping(value = "/OG22001", method = {RequestMethod.GET})
  public @ResponseBody ResultWebDto getBillingInfo(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return controllerUtils.makeGetControllerHandler(request, Luigi2Endpoint.OG21001, (param) -> {
      return customerService.getBillingInfo(param);
    });
  }

  /**
   * 支払情報取得
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-30
   * @updatedAt : 2021-06-30
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Produces(MediaType.APPLICATION_JSON)
  @RequestMapping(value = "/OG23001", method = {RequestMethod.GET})
  public @ResponseBody ResultWebDto getPaymentInfo(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return controllerUtils.makeGetControllerHandler(request, Luigi2Endpoint.OG21001, (param) -> {
      return customerService.getPaymentInfo(param);
    });
  }

  /**
   * 異動履歴詳細
   *
   * @author : [AOT] g.kim
   * @createdAt : 2021-06-30
   * @updatedAt : 2021-06-30
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  @Produces(MediaType.APPLICATION_JSON)
  @RequestMapping(value = "/OG24001", method = {RequestMethod.GET})
  public @ResponseBody ResultWebDto getMaintenanceRequest(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return controllerUtils.makeGetControllerHandler(request, Luigi2Endpoint.OG21001, (param) -> {
      return customerService.getMaintenanceRequest(param);
    });
  }
}
