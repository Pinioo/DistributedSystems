package client

import common.{ChatMessage, UserLoggedInMessage}

import java.io.{IOException, PrintWriter}
import java.net.Socket

case class ClientTCPSender(client: Client)  {
  val clientSocket: Socket = client.clientTCPSocket
  val out = new PrintWriter(clientSocket.getOutputStream, true)

  @throws(classOf[IOException])
  def send(msg : String): Unit = {
    val chatMessage = ChatMessage(client.id, msg)
    out.println(chatMessage.toString)
  }

  @throws(classOf[IOException])
  def sendLogIn(): Unit = {
    val loginMessage = UserLoggedInMessage(client.id)
    out.println(loginMessage.toString)
  }
}
