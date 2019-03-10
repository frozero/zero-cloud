package com.zero.api.model.common;

/**
 * @author zero
 */
public enum ResultCode {

    SUCCESS(0),

    FAILURE(1);

    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return this.code;
    }
}
