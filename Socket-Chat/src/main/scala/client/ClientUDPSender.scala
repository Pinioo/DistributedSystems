package client

import common.{ChatMessage, Config}

import java.net.{DatagramPacket, DatagramSocket}

case class ClientUDPSender(client: Client) {
  val clientSocket: DatagramSocket = client.clientUDPSocket

  def send(msg: String): Unit = {
    val buffer = ChatMessage(client.id, msg).toBytesArray
    val datagramPacket = new DatagramPacket(
      buffer,
      buffer.length,
      Config.address,
      Config.port
    )
    clientSocket.send(datagramPacket)
  }
}
