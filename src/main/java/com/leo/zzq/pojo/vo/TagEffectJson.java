package com.leo.zzq.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chao.li
 * @date 2019/1/21 17:54
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TagEffectJson implements Serializable {
    private static final long serialVersionUID = -8704351234945651173L;
    private String tagName;
    private Integer threshold;
    private String effectName;
    private String detail;
}
