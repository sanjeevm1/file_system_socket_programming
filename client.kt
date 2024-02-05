
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    while (true) {
        println("Enter the path of the file to send")
        val filePath = readln()
        println("Enter file name")
        val fileName = readln()
        val file = File(filePath)
        if (!file.exists()) {
            println("File doesn't exists try selecting different file...")
            continue
        }

        // connecting socket
        val socket = Socket("0.0.0.0", 3000)
        val output = DataOutputStream(socket.getOutputStream())
        val input = DataInputStream(socket.getInputStream())
        println("connected with server via socket")

        initializeSocket(output, file.length(),fileName)
        println("initalized with init protocol")

        val initAck = verifyInitAck(input)
        println("verified acknowledgement")
        if (initAck == -1) {
            println("Error connecting to socket, trying to reconnect...")
            continue
        }

        sendFile(output, input, file, DataInputStream(socket.getInputStream()),DataOutputStream(socket.getOutputStream()),fileName)
        output.writeUTF("proto:endSend")
        println("Finished Sending the file ${filePath}")
    }
}

fun initializeSocket(output: DataOutputStream, fileLength: Long,fileName : String) {
    output.writeUTF("proto:initSend;fileName:${fileName};fileLength:${fileLength}")
//    output.close()
//    output.writeUTF("fileName:sample.txt;")
//    output.writeUTF("fileLength:${fileLength}")

}

fun verifyInitAck(input: DataInputStream): Int {
    val ack = input.readUTF()
    println("ack now")
    return if (ack.startsWith("err:")) {
        -1
    } else {
        1
    }
}

fun sendFile(output: DataOutputStream, input: DataInputStream, file: File, dataInputStream: DataInputStream,dataOutputStream: DataOutputStream,fileName: String) {
    var bytes = 0
    val fileInputStream = FileInputStream(file)
    val data = FileInputStream(file).readAllBytes()

    //val scanner = Scanner(file)
    println(file.absolutePath)
    //output.write(data)
    println(Arrays.toString(data))

    var ind=0

    while(ind <=data.count()-1000){
        output.writeUTF("proto:sendData;data:${Arrays.toString(data.sliceArray(ind until ind+1000))};fileName:${fileName}")
        ind+=1000
    }

    output.writeUTF("proto:sendData;data:${Arrays.toString(data.sliceArray(ind until data.count()))};fileName:${fileName}")

//    val buffer = ByteArray(4 * 1024)
//    while (scanner.hasNextLine()) {
////        bytes = fileInputStream.read(buffer)
////        if (bytes == -1) break
//        val data = scanner.nextLine()
//        output.writeUTF("proto:sendData;data:${data};fileName:download.jpeg")
//
////        dataOutputStream.write(buffer,0,bytes)
////        dataOutputStream.flush()
////        var ack = verifySendAck(input)
////        while (ack == -1) {
////            dataOutputStream.write(buffer,0,bytes)
////            dataOutputStream.flush()
////            ack = verifySendAck(input)
////        }
//    }
}

fun verifySendAck(input: BufferedReader) : Int {
//    val ack = input.readLine()
//    return if (ack.startsWith("err:")){
//        -1
//    } else {
    return 1
//    }
}