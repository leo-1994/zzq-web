package com.leo.zzq.util;

/**
 * <pre>
 * </pre>
 *
 * @author jianming.jiangjm
 * @date Jul 21, 2012
 */
public enum DateformatEnum {
    /***/
    yyyy("yyyy"),
    /***/
    yyyyMM("yyyyMM"),
    /***/
    yyyyMMddSplit("yyyy-MM-dd"),
    /***/
    yyyyMMddHHSplit("yyyy-MM-dd HH"),
    /***/
    yyyyMMdd("yyyyMMdd"),
    /***/
    yyyyMMddHHmmss("yyyyMMddHHmmss"),
    /***/
    yyyyMMddHHmm("yyyyMMddHHmm"),
    /***/
    yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"),
    /***/
    yyyyMMddHHmmssSplit("yyyy-MM-dd HH:mm:ss"),
    yyyyMMddHHmmChiness("yyyy-MM-dd HH时mm分"),
    /***/
    yyyyMMSplit("yyyy-MM"),
    /***/
    yyyyMMddHHmmssSSSplit("yyyy-MM-dd HH:mm:ss.SSS"),
    /***/
    HHmmss("HHmmss"),
    /***/
    HHmmssSSS("HHmmssSSS"),
    /***/
    HH("HH"),
    /***/
    yyyyMMddHH("yyyyMMddHH"),

    /***/
    yyyy_MM("yyyy_MM"),
    ddMMMyyyyHHmmssSSSSSplit("dd/MMM/yyyy:HH:mm:ss +SSSS");

    public String format;

    DateformatEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
