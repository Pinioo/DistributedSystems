package client

import common.Config

import java.io.PrintWriter
import java.net.{DatagramSocket, MulticastSocket, Socket}

case class Client(id: String){
  val clientTCPSocket = new Socket(Config.address, Config.port)
  val clientUDPSocket = new DatagramSocket(clientTCPSocket.getLocalPort)
  val clientMulticastSocket = new MulticastSocket(Config.multicastAddress.getPort)

  val out = new PrintWriter(clientTCPSocket.getOutputStream, true)
}

