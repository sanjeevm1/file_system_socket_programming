package threadpool

import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

fun createThread(socket : Socket){

    val thread : Thread = ThreadManager(socket)
    thread.start()

}

