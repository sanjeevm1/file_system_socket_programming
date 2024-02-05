package controller

import fileHandler.Handler

fun initReqHandle(requestData : Map<String,String>) : String{

    println(requestData)
    if(!requestData.containsKey("fileName")){
        return "err:Wrong format data"
    }

    else{
        return Handler().createFile(requestData.get("fileName")!!)
    }
}

