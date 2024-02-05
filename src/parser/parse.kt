package parser

fun parse(request : String) : Map<String,String>{

    val reqData : MutableMap<String,String> = HashMap<String,String>()

    try{
        //println(reqData)
        val dataList : List<String> = request.split(";")

        for(data in dataList){

            val temp : List<String> = data.split(":");
            reqData.put(temp.get(0),temp.get(1))
        }

        return reqData
    }

    catch(err : Exception){
        println("parsing error")
        return reqData
    }

}