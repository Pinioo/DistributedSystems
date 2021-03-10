package server

import common.{ChatMessage, Message, UserLoggedInMessage}

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

case class ServerTCPClientThread(server: ServerTCP)(clientSocket: Socket) extends Thread{
  override def run(): Unit = {
    val in = new BufferedReader(
      new InputStreamReader(clientSocket.getInputStream)
    )
    while(!clientSocket.isClosed) {
      try {
        Message(in.readLine) foreach {
          case msg @ (_: ChatMessage | _: UserLoggedInMessage) =>
            server.clientSocketsSemaphore.acquire()
            server.clientSockets
              .filter{case (key, _) => key ne clientSocket}
              .foreach{case (_, pw) => pw.println(msg.toString)}
            server.clientSocketsSemaphore.release()
          case _ => println("fff")
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
