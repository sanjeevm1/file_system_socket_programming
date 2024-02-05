package threadpool

import controller.fileDataHandle
import controller.initReqHandle
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException

fun exec(server : Socket){


            while (server.isConnected) {
                //println("request conn")
                //println(server.isConnected)
                val inputReader = DataInputStream(server.getInputStream())


                //println("req before")
                var data = inputReader.readUTF()
//        println("req after")
                println(data)
                val outputSender = DataOutputStream(server.getOutputStream())
                val requestData: Map<String, String> = parser.parse(data)

                if (!requestData.containsKey("proto")) {
                    outputSender.writeUTF("err: not a proper format")
                    outputSender.flush()
                }

                val prototype = requestData.get("proto")!!

                if (prototype == "initSend") {
                    outputSender.writeUTF(initReqHandle(requestData))
                    outputSender.flush()
                } else if (prototype == "sendData") {
                    outputSender.writeUTF(fileDataHandle(requestData))
                    outputSender.flush()
                } else if (prototype == "endSend") {
                    println("hello $prototype")
                    break
                }

                //socket.close()

            }

    }

