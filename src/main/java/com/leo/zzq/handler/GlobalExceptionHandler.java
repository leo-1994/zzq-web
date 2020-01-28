package com.leo.zzq.handler;

import com.leo.zzq.exception.ServiceException;
import com.leo.zzq.pojo.vo.ResultVO;
import com.leo.zzq.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 *
 * @author chao.li
 * @date 2019/1/15 14:43
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> ResultVO<?> defaultExceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof ServiceException) {
            log.error("业务异常：" + e.getMessage());
            ServiceException serviceException = (ServiceException) e;
            return ResultUtil.fail(serviceException.getCode(), serviceException.getMessage());
        }
        //未知错误
        return ResultUtil.fail(-1, "系统异常：\\n" + e);
    }
}
