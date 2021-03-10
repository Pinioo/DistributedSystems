package client

import common.{ChatMessage, Config, Message}

import java.net.{DatagramPacket, MulticastSocket}
import scala.util.{Failure, Success, Try}

case class ClientMulticastReceiverThread(client: Client) extends Thread {
  override def run(): Unit = {
    val clientSocket: MulticastSocket = client.clientMulticastSocket
    clientSocket.joinGroup(Config.multicastAddress, Config.interface)
    clientSocket.setSoTimeout(2000)

    while(!client.clientTCPSocket.isClosed) {
      val bufferSize = 4096
      val buffer = Array.fill[Byte](bufferSize)(0)
      val datagram = new DatagramPacket(
        buffer,
        buffer.length
      )

      Try{
        clientSocket.receive(datagram)
      } match {
        case Success(_) =>
          val chatMessage = new String(datagram.getData, 0, datagram.getLength)
          Message(chatMessage).foreach{
            case ChatMessage(id, msg) => println(s"[Multicast] $id: $msg")
            case _ => ()
          }
        case Failure(e) => ()
      }
    }
  }
}
