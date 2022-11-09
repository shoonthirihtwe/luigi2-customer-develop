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
 * CustomerDetailContractorInfoVo
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
public class CustomerDetailContractorInfoVo extends ObjectVo {

  /**
   * 顧客ID
   */
  String contractorCustomerId;
  /**
   * 顧客名
   */
  String contractorNameKnj;
  /**
   * 顧客名（カナ）
   */
  String contractorNameKana;
  /**
   * 性別
   */
  String contractorSex;
  /**
   * 生年月日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date contractorDateOfBirth; 
  /**
   * 住所（郵便番号）
   */
  String contractorZipcode;
  /**
   * 電話番号
   */
  String contractorTel;
  /**
   * メールアドレス
   */
  String contractorEmail;
  
}
