package client

import common.{ChatMessage, UserDisconnectedMessage, UserLoggedInMessage}

import java.io.{IOException, PrintWriter}
import java.net.Socket

case class ClientTCPSender(client: Client)  {
  val clientSocket: Socket = client.clientTCPSocket
  val out = new PrintWriter(clientSocket.getOutputStream, true)

  def send(msg : String): Unit = {
    val chatMessage = ChatMessage(client.id, msg)
    out.println(chatMessage.toString)
  }

  def sendLogIn(): Unit = {
    val loginMessage = UserLoggedInMessage(client.id)
    out.println(loginMessage.toString)
  }

  def sendLogOut(): Unit = {
    val logoutMessage = UserDisconnectedMessage(client.id)
    out.println(logoutMessage.toString)
  }
}
