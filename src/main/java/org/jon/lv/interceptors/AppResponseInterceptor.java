package org.jon.lv.interceptors;

import com.alibaba.fastjson.JSON;
import com.shfc.common.base.Logger;
import com.shfc.common.result.ResultDO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Package com.shfc.cloud.interceptors.CloudResponseInterceptor
 * @Description: 统一响应结果处理
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/3/17 10:45
 * version V1.0.0
 */
@ControllerAdvice
public class AppResponseInterceptor implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 响应结果执行
        if(mediaType != null && o != null
                && mediaType.includes(MediaType.APPLICATION_JSON)
                && o instanceof ResultDO){

            if(serverHttpRequest instanceof ServletServerHttpRequest){

                ServletServerHttpRequest request = (ServletServerHttpRequest)serverHttpRequest;

                HttpServletRequest httpServletRequest = request.getServletRequest();

                Date requestTime = (Date) httpServletRequest.getAttribute(AppInterceptor.REQUEST_TIME);

                long useTime = System.currentTimeMillis() - requestTime.getTime();

                Method method = methodParameter.getMethod();

                Logger.debug(AppResponseInterceptor.class, "请求控制器:" + method.getDeclaringClass() + " 请求方法:" + method.getName());

                Logger.debug(AppResponseInterceptor.class, "请求链接:" + serverHttpRequest.getURI() + " 耗时（毫秒）:" + useTime);
            }


            Logger.debug(AppResponseInterceptor.class, "响应内容:" + JSON.toJSONString(o));
        }

        return o;
    }
}
