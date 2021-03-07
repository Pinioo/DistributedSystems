package server

import common.ServerConfig

import java.net.ServerSocket

case object ServerMain extends App {
  val serverSocket = new ServerSocket(ServerConfig.port)
  while(true) {
    ServerClientThread(serverSocket.accept()).start()
  }
}
