package com.sanver.example.service

import com.sanver.example.core.BaseCallBack

class ApiCallback<T>(baseCallBack: BaseCallBack<T>?) : BaseRetrofitCallback<T>(baseCallBack)
