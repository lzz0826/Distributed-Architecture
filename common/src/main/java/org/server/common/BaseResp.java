package org.server.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResp<T> {

  private T data;

  private StatusCode statusCode;

  public Integer getStatusCode(){
    if(statusCode == null){
      return null;
    }
    return statusCode.code;
  }

  public String getMst(){

    if(statusCode == null){
      return null;
    }

    return statusCode.msg ;
  }

  public static <T> BaseResp<T> ok(T data){
    return (BaseResp<T>) BaseResp
        .builder()
        .data(data)
        .statusCode(StatusCode.Success)
        .build();

  }

  public static <T> BaseResp<T> ok (StatusCode statusCode){
    return (BaseResp<T>) BaseResp
        .builder()
        .statusCode(statusCode)
        .build();
  }



  public static <T> BaseResp<T> ok (T data ,StatusCode statusCode){
    return (BaseResp<T>) BaseResp
        .builder()
        .data(data)
        .statusCode(statusCode)
        .build();
  }


  public static <T> BaseResp<T> fall(T data , StatusCode statusCode){
    return (BaseResp<T>) BaseResp
        .builder()
        .data(data)
        .data(statusCode)
        .build();
  }





}












