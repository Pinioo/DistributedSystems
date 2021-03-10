package server

import common.Config

import java.net.{DatagramPacket, DatagramSocket}

case class ServerUDP(serverTCP: ServerTCP) extends Thread {
  val socket = new DatagramSocket(Config.port)

  override def run(): Unit = {
    while (true) {
      val bufferSize = 1024
      val buffer = Array.fill[Byte](bufferSize)(0)
      val datagram = new DatagramPacket(buffer, bufferSize)

      socket.receive(datagram)
      val port = datagram.getPort
      val address = datagram.getAddress
      datagram.setLength(datagram.getLength)

      serverTCP.clientSocketsSemaphore.acquire()
      serverTCP.clientSockets
        .map{case (sock, _) => sock}
        .filterNot(sock => sock.getPort == port && sock.getInetAddress.equals(address))
        .foreach{
          sock => {
            datagram.setAddress(sock.getInetAddress)
            datagram.setPort(sock.getPort)
            socket.send(datagram)
            println(s"Datagram sent to ${sock.getPort}")
          }
        }
      serverTCP.clientSocketsSemaphore.release()
    }
  }
}
