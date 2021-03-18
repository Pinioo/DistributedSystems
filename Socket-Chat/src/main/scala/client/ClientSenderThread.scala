package client

import scala.io.StdIn

case class ClientSenderThread(client: Client) extends Thread {
  override def run(): Unit = {
    val clientSocket = client.clientTCPSocket
    val tcpSender = ClientTCPSender(client)
    val udpSender = ClientUDPSender(client)
    val multicastSender = ClientMulticastSender(client)

    tcpSender.sendLogIn()

    while(!clientSocket.isClosed) {
      val input = StdIn.readLine()
      input match {
        case s"/q" =>
          tcpSender.sendLogOut()
          clientSocket.close()
        case s"/U $msg" => udpSender.send(msg)
        case s"/UB" => udpSender.send("Simulation".repeat(4096))
        case s"/TB" => tcpSender.send("Simulation".repeat(4096))
        case s"/M $msg" => multicastSender.send(msg)
        case s"/$msg" => println(s"Unrecognized command $msg")
        case s"$msg" => tcpSender.send(msg)
      }
    }
  }
}
