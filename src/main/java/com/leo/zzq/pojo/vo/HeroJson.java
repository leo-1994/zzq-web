package com.leo.zzq.pojo.vo;

import com.leo.zzq.pojo.entity.Attribute;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chao.li
 * @date 2019/1/23 23:29
 */
@Data
public class HeroJson implements Serializable {
    private static final long serialVersionUID = 308708160103038646L;
    private String name;
    private Integer level;
    private String color;
    private String[] tags;
    private Attribute attribute;
    private String nickname;
}
