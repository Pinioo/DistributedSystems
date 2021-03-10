package server

import common.Config

import java.io.PrintWriter
import java.net.{ServerSocket, Socket}
import java.util.concurrent.Semaphore

case class ServerTCP(){
  val serverSocket = new ServerSocket(Config.port)
  val clientSocketsSemaphore = new Semaphore(1)
  var clientSockets = Map.empty[Socket, PrintWriter]
}
