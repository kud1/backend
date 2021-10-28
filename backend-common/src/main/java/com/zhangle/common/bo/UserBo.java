package com.zhangle.common.bo;

import lombok.Data;

@Data
public class UserBo {
    private String name;
    private String token;
    private Integer status;
    private String message;
}
