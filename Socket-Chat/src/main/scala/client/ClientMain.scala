package client

import common.{ChatMessage, ServerConfig}

import java.io.IOException
import java.net.Socket

case object ClientMain extends App {
  val clientSocket = new Socket(ServerConfig.address, ServerConfig.port)
  try {
    clientSocket.getOutputStream.write(ChatMessage("id", "msg").toString.getBytes)
  } catch {
    case e: IOException => println("Koniec")
  } finally {
    clientSocket.close()
  }
}
