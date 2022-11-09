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
 * MaintenanceRequestInfoVo
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
public class MaintenanceRequestInfoVo extends ObjectVo {

  Integer id;

  String transactionCode;

  @JsonSerialize(using = JsonDateSerializer.class)
  Date receivedDate;

  @JsonSerialize(using = JsonDateSerializer.class)
  Date applyDate;

  String applicationMethod;

  String attached1Title;

  String attached1Url;

  String attached2Title;

  String attached2Url;

  String attached3Title;

  String attached3Url;

  String attached4Title;

  String attached4Url;

  String attached5Title;
  
  String attached5Url;

  String attached6Title;
  
  String attached6Url;
  
  String attached7Title;
  
  String attached7Url;
  
  String attached8Title;
  
  String attached8Url;
  
  String attached9Title;
  
  String attached9Url;
  
  String attached10Title;
  
  String attached10Url;
  
  String commentUnderweiter1;
  
  String commentUnderweiter2;
}
