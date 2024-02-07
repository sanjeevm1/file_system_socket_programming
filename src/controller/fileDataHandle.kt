package controller

import fileHandler.Handler
import java.util.*

fun fileDataHandle(requestData : Map<String,String>) : String{

    val bytes : MutableList<Byte> = ArrayList<Byte>()

    val tempArr = requestData.get("data")!!.substring(IntRange(1,requestData.get("data")!!.length-2))

    //println(tempArr)
   //println(tempArr.split(", "))
    for(byte in tempArr.split(", "))
        bytes.add(byte.toByte())
    //println(Arrays.toString(bytes.toByteArray()))
    return Handler().appendText(requestData.get("fileName")!!, bytes.toByteArray())
}