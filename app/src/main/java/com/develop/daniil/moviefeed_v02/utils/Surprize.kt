package com.develop.daniil.moviefeed_v02.utils

import com.develop.daniil.moviefeed_v02.utils.Crypto
class funk{

     fun getKluch():String{
        return Crypto.getHash(getSecretWord())
    }
}


private fun getSecretWord(): String{
    return "VerySecret_key&Adsf34RFsf4r8UGHAUDAHeji43ut8gjdi9384289_&!PRIVATEKLUCH"
}
