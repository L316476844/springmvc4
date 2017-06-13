package org.jon.lv.result;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @ClassName: ResultDO
 * @Description: 结果基类，用于返回外部系统调用结果
 * @author lvbin
 * @date 2016年9月5日 下午7:25:09
 *
 * @param <T>
 */
public class ResultDO<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4227833010077730477L;

    /**
     * 是否成功，默认失败
     */
    private boolean success = false;

    /**
     * 返回消息
     */
    private String errMsg;

    /**
     * 返回CODE
     */
    private int errCode;

    /**
     * 返回结果封装器
     */
    private T data;

    /**
     * 异常
     */
    private Exception exception;

    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}