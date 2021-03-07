package server

import common.{ChatMessage, Message}

import java.net.Socket
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

case class ServerClientThread(clientSocket: Socket) extends Thread{
  override def run(): Unit = {
    println(Message.unapply(new String(clientSocket.getInputStream.readAllBytes(), StandardCharsets.UTF_8)).get.toString)
    clientSocket.close()
  }
}
