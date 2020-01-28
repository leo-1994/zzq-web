package com.leo.zzq.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.leo.zzq.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chao.li
 * @Description:
 * @Date 2017/12/16 15:31
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    // 单例多线程 开始时间绑定在线程上
    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        long start = System.currentTimeMillis();
        startTimeThreadLocal.set(start);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        // 获取请求错误码
        int status = response.getStatus();
        long start = startTimeThreadLocal.get();
        long end = System.currentTimeMillis();
        // 请求路径
        String url = request.getRequestURI();
        // 请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(), SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        // 客户端ip
        String clientIp = IPUtil.getIpAddr(request);
        // 请求方法
        String method = request.getMethod();
        // 浏览器信息
        String userAgent = request.getHeader("user-Agent");
        log.info("url:{}|paramData:{}|clientIp:{}|method:{}|userAgent:{}|status:{}|useTime:{}ms", url, paramData, clientIp, method, userAgent, status, end - start);
    }
}
