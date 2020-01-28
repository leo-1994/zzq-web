package com.leo.zzq.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chao.li
 * @date 2019/1/22 22:48
 */
@Data
public class HeroVO implements Serializable {
    private static final long serialVersionUID = 1650382230191665113L;
    private Integer id;
    private String name;
    private Integer level;
    private String tags;
    private String color;
}
