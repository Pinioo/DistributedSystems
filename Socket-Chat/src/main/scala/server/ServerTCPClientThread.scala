package server

import common.{ChatMessage, Message, UserDisconnectedMessage, UserLoggedInMessage}

import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import java.net.Socket

case class ServerTCPClientThread(server: ServerTCP)(clientSocket: Socket) extends Thread{
  def getOtherSockets: Map[Socket, PrintWriter] =
    server.clientSockets.filter{case (key, _) => key ne clientSocket}

  override def run(): Unit = {
    val in = new BufferedReader(
      new InputStreamReader(clientSocket.getInputStream)
    )
    while(!clientSocket.isClosed) {
      try {
        val msgString = in.readLine
        Message(msgString) foreach {
          case msg @ (_: ChatMessage | _: UserLoggedInMessage) =>
            server.clientSocketsSemaphore.acquire()
            getOtherSockets foreach {case (_, pw) => pw.println(msg.toString)}
            server.clientSocketsSemaphore.release()
          case msg: UserDisconnectedMessage  =>
            clientSocket.close()
            server.clientSocketsSemaphore.acquire()
            server.clientSockets -= clientSocket
            getOtherSockets foreach {case (_, pw) => pw.println(msg.toString)}
            server.clientSocketsSemaphore.release()
          case _ => println(s"Invalid message received: $msgString")
        }
      } catch {
        case _: Exception =>
          println("Client disconnected")
          clientSocket.close()
          server.clientSocketsSemaphore.acquire()
          server.clientSockets -= clientSocket
          server.clientSocketsSemaphore.release()
      }
    }
  }
}
