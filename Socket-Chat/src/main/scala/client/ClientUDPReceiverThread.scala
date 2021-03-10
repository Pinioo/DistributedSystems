package client

import common.{ChatMessage, Message, Config, UserDisconnectedMessage, UserLoggedInMessage}

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.{DatagramPacket, DatagramSocket, Socket}
import scala.util.{Failure, Success, Try}

case class ClientUDPReceiverThread(client: Client) extends Thread {
  override def run(): Unit = {
    val clientSocket: DatagramSocket = client.clientUDPSocket
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
            case ChatMessage(id, msg) => println(s"[UDP] $id: $msg")
            case _ => ()
          }
        case Failure(e) => ()
      }
    }
  }
}
