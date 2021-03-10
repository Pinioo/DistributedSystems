package client

import scala.io.StdIn

case object ClientMain extends App {
  val id: String = StdIn.readLine("Give your ID: ")
  val client = Client(id)

  ClientTCPReceiverThread(client).start()
  ClientUDPReceiverThread(client).start()
  ClientMulticastReceiverThread(client).start()
  ClientSenderThread(client).start()
}