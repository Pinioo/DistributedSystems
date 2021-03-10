package client

import scala.io.StdIn

case class ClientSenderThread(client: Client) extends Thread {
  override def run(): Unit = {
    val clientSocket = client.clientTCPSocket
    val tcpSender = new ClientTCPSender(client)
    val udpSender = new ClientUDPSender(client)
    val multicastSender = new ClientMulticastSender(client)

    tcpSender.sendLogIn()

    while(!clientSocket.isClosed) {
      val input = StdIn.readLine()
      input match {
        case s"/q" => clientSocket.close()
        case s"/U $msg" => udpSender.send(msg)
        case s"/M $msg" => multicastSender.send(msg)
        case s"/$msg" => println(s"Unrecognized command $msg")
        case s"$msg" => tcpSender.send(msg)
      }
    }
  }
}
