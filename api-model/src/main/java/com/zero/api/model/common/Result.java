package com.zero.api.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author zero
 */
@Data
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -8689684860724020717L;
    private int code = ResultCode.SUCCESS.code();

    private String msg = "success";

    private T data;

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        super();
        this.code = resultCode.code();
    }

    public Result(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public Result(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = ResultCode.FAILURE.code();
    }

    public Result(ResultCode resultCode, String msg) {
        super();
        this.code = resultCode.code();
        this.msg = msg;
    }
}
