package com.example.baselibrary.rx

import com.example.baselibrary.common.ResultCode
import com.example.baselibrary.data.protocol.BaseResponse
import rx.Observable
import rx.functions.Func1

class BaseFuncBoolean<T>:Func1<BaseResponse<T>,Observable<Boolean>>{
    override fun call(t: BaseResponse<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) return Observable.error(BaseException(t.status, t.message))
        return Observable.just(true)
    }

}