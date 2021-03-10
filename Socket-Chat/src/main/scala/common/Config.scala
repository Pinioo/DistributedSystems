package common

import java.net.{InetAddress, InetSocketAddress, NetworkInterface}

case object Config {
  val port: Int =
    9000
  val address: InetAddress =
    InetAddress.getLocalHost
  val multicastAddress: InetSocketAddress =
    new InetSocketAddress(InetAddress.getByName("234.56.78.90"), 9001)
  val interface: NetworkInterface =
    NetworkInterface.getByName("lo")
}
