package threadpool

import java.net.ServerSocket
import java.net.Socket

class ThreadManager(val socket : Socket) : Thread() {


    override fun run() {
        exec(socket)
    }

}