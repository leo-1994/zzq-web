package com.leo.zzq.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chao.li
 * @date 2019/1/15 11:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultVO<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -8249918486342407739L;
    private Integer code;
    private String msg;
    private T data;
}
