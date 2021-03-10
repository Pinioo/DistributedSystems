package server

import java.io.PrintWriter

case class ServerTCPListeningThread(server: ServerTCP) {
  def start(): Unit = {
    while (true) {
      val newSocket = server.serverSocket.accept()

      server.clientSocketsSemaphore.acquire()
      server.clientSockets += newSocket -> new PrintWriter(newSocket.getOutputStream, true)
      server.clientSocketsSemaphore.release()

      ServerTCPClientThread(server)(newSocket).start()
    }
  }
}
