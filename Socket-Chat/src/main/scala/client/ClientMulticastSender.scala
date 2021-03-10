package client

import common.{ChatMessage, Config}

import java.net.{DatagramPacket, MulticastSocket}

case class ClientMulticastSender(client: Client) extends Thread {
  val clientSocket: MulticastSocket = client.clientMulticastSocket

  def send(msg: String): Unit = {
    val buffer = ChatMessage(client.id, msg).toBytesArray
    val datagramPacket = new DatagramPacket(
      buffer,
      buffer.length,
      Config.multicastAddress
    )
    clientSocket.send(datagramPacket)
  }
}
