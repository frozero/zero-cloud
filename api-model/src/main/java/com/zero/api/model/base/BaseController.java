package com.zero.api.model.base;

import com.zero.api.model.common.Result;
import com.zero.api.model.common.ResultCode;

/**
 * @author zero
 */
public class BaseController {

    protected Result result(Object obj){
        return new Result<>(obj);
    }

    protected Result result(Object obj, String msg){
        return new Result<>(obj, msg);
    }

    protected Result result(Throwable e){
        return new Result<>(e);
    }

    protected Result result(ResultCode resultCode){
        return new Result<>(resultCode);
    }
}
