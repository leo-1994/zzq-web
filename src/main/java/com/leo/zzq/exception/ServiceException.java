package com.leo.zzq.exception;


import com.leo.zzq.enums.ResultEnum;

/**
 * @author chao.li
 * @date 2019/1/15 14:46
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1480342523423116968L;
    /**
     * 错误码
     */
    private int code;

    public ServiceException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ServiceException(ResultEnum resultEnum, String msg) {
        super(msg);
        this.code = resultEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}
