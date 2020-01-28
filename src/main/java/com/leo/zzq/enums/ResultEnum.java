package com.leo.zzq.enums;

import lombok.Getter;

/**
 * @author chao.li
 * @date 2018/12/30 23:23
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "success"),
    CONNECT_ERROR(101, "连接出错"),
    PARAM_ERROR(102, "参数错误");

    private int code;

    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
