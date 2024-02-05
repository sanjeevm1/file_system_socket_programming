package fileHandler

import java.io.File

class Handler {

    fun createFile(filename : String) : String{

        val file = File(filename)

        if(file.exists())
            return "File exists rename the file"
        if(file.createNewFile())
            return "file created"
        return "error"
    }

    fun appendText(filename : String,data : ByteArray) : String{

        try{
            val file = File(filename)
            file.appendBytes(data)
            return "success"
        }

        catch(err : Exception){
            println("file append error")
            return "err:file data"
        }

    }
}