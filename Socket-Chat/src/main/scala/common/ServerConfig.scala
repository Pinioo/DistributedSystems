package common

import java.net.InetAddress

case object ServerConfig {
  val port: Int            = 9000
  val address: InetAddress = InetAddress.getLocalHost
}
