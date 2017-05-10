package org.jon.lv.interceptors;

import org.jon.lv.annotation.UnLoginLimit;
import org.jon.lv.exception.AppWebException;
import org.jon.lv.exception.ErrorConstant;
import org.jon.lv.redis.RedisUtil;
import org.jon.lv.utils.AuthSessionUtils;
import org.jon.lv.utils.HttpSessionUtils;
import org.jon.lv.utils.ValidateHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Description: AppInterceptor 拦截器
 * Author lv bin
 * @date 2017/5/4 16:53
 * version V1.0.0
 */
public class AppInterceptor extends HandlerInterceptorAdapter {

    @Value("${customer.app.version}")
    private String customerAppVersion;
    /**
     * X-token 有效时间 30分钟
     * 单位秒
     */
    @Value("${customer.app.active.time}")
    private Long ACTIVE_TIME;

    /**
     * 默认请求request header 头部存放 token 名称
     */
    public String DEFAULT_TOKEN_NAME = "X-token";

    public static String REQUEST_TIME = "http_request_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute(REQUEST_TIME, new Date());

        String uri = request.getRequestURI();
        if (!uri.endsWith(customerAppVersion)) {
            // 版本号错误
            throw new AppWebException(ErrorConstant.ERROR_VERSION.getCode(),
                    ErrorConstant.ERROR_VERSION.getMsg());
        }

        // 判断是否为json 请求
        String contentType = request.getContentType();
        if (!MediaType.APPLICATION_JSON_UTF8_VALUE.contains(contentType)) {
            throw new AppWebException(ErrorConstant.ERROR_MEDIA_TYPE.getCode(),
                    ErrorConstant.ERROR_MEDIA_TYPE.getMsg());
        }
        // 内容长度
        int contentLength = request.getContentLength();
        if (contentLength == 0) {
            throw new AppWebException(ErrorConstant.EMPTY_REQUEST_BOYD.getCode(),
                    ErrorConstant.EMPTY_REQUEST_BOYD.getMsg());
        }

        String tokenKey = request.getHeader(DEFAULT_TOKEN_NAME);

        if(!ValidateHelper.isEmpty(tokenKey) &&
                RedisUtil.get(tokenKey) != null){
            // 用户存在
            RedisUtil.set(tokenKey, RedisUtil.get(tokenKey), ACTIVE_TIME);

            HttpSessionUtils.putObject(AuthSessionUtils.APP_CURRENT_USER_ID,
                    RedisUtil.get(tokenKey));
        }

        if(handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            UnLoginLimit unlimited = method.getMethodAnnotation(UnLoginLimit.class);
            if(unlimited != null){
                // 免登陆接口
                return true;
            }else{
                // 需要登录接口
                if(ValidateHelper.isEmpty(tokenKey) || RedisUtil.get(tokenKey) == null){
                    // 需要登录接口
                    throw new AppWebException(ErrorConstant.LOGIN_FIRST.getCode(),
                            ErrorConstant.LOGIN_FIRST.getMsg());
                }
            }
        }

        return super.preHandle(request, response, handler);
    }
}
