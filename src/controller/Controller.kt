package controller

import threadpool.createThread
import java.io.*
import java.net.InetAddress
import java.net.ServerSocket
import java.net.SocketException
import java.util.Scanner

class Controller {

    fun listen() {

        // println("server listenoing")
        val server = ServerSocket(3000)

        while(true){

            try{
                val socket = server.accept()
                createThread(socket)
            }

            catch(err : SocketException){
                println(err.message)
            }
        }

    }
}


