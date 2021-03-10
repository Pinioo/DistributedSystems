package server

case object ServerMain extends App {
  val serverTCP = ServerTCP()

  ServerUDP(serverTCP).start()
  ServerTCPListeningThread(serverTCP).start()
}
