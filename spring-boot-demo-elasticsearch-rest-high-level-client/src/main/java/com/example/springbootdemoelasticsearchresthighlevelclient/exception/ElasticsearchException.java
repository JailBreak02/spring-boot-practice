package com.example.springbootdemoelasticsearchresthighlevelclient.exception;

import com.example.springbootdemoelasticsearchresthighlevelclient.common.ResultCode;
import lombok.Getter;

/**
 * ElasticsearchException
 *
 * @author BigBoss
 * @version v1.0
 * @since 20200810
 */
public class ElasticsearchException extends RuntimeException {

    @Getter
    private int errcode;

    @Getter
    private String errmsg;

    public ElasticsearchException() {
    }

    public ElasticsearchException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public ElasticsearchException(String message) {
        super(message);
    }

    public ElasticsearchException(int errcode, String errmsg) {
        super(errmsg);
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public ElasticsearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElasticsearchException(Throwable cause) {
        super(cause);
    }

    public ElasticsearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
