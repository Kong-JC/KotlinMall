package com.example.usercenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.usercenter.data.repository.UploadRepository
import com.example.usercenter.service.UploadService
import rx.Observable
import javax.inject.Inject

class UploadServiceImpl @Inject constructor() : UploadService {
    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }


}