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
 * CustomerDetailProducInfoVo
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
public class CustomerDetailPaymentInfoVo extends ObjectVo {

  Integer id;
  /**
   * 受付日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date receivedDate;

  /**
   * 処理日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date secondUnderwritingDate;
  /**
   * 支払日
   */
  @JsonSerialize(using = JsonDateSerializer.class)
  Date dueDate;
}
