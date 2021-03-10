package client

import common.{ChatMessage, Message, UserDisconnectedMessage, UserLoggedInMessage}

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.Socket

case class ClientTCPReceiverThread(client: Client) extends Thread {
  override def run(): Unit = {
    val clientSocket: Socket = client.clientTCPSocket
    val in = new BufferedReader(
      new InputStreamReader(clientSocket.getInputStream)
    )

    while(!clientSocket.isClosed) {
      try {
        val chatMessage = in.readLine()
        Message(chatMessage) foreach {
          case ChatMessage(id, msg) => println(s"$id: $msg")
          case UserLoggedInMessage(id) => println(s"User $id joined the chat")
          case UserDisconnectedMessage(id) => println(s"User $id left the chat")
          case _ => ()
        }
      }
      catch {
        case e: IOException =>
          println("Server not responding")
          clientSocket.close()
      }
    }
  }
}
