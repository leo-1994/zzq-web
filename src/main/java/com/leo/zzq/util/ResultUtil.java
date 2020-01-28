package com.leo.zzq.util;


import com.leo.zzq.enums.ResultEnum;
import com.leo.zzq.pojo.vo.ResultVO;

import java.io.Serializable;

/**
 * @author chao.li
 * @date 2018/12/30 16:06
 */
public class ResultUtil {

    public static <T> ResultVO success() {
        return new ResultVO<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    public static <T extends Serializable> ResultVO success(T data) {
        return new ResultVO<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static ResultVO fail(int code, String msg) {
        return new ResultVO<>(code, msg, null);
    }

    public static ResultVO fail(ResultEnum resultEnum) {
        return new ResultVO<>(resultEnum.getCode(), resultEnum.getMsg(), null);
    }
}
