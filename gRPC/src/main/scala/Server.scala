import io.grpc.ServerBuilder

object Server1 extends App {
  val port = args.headOption.flatMap(_.toIntOption).getOrElse(10001)
  val servBuilder = ServerBuilder.forPort(port)
  servBuilder.addService(FibonacciServiceImpl())
  val server = servBuilder.build().start()
  if(server != null)
    server.awaitTermination()
}

object Server2 extends App {
  val port = args.headOption.flatMap(_.toIntOption).getOrElse(10002)
  val servBuilder = ServerBuilder.forPort(port)
  servBuilder.addService(FibonacciServiceImpl())
  servBuilder.addService(HelloServiceImpl())
  val server = servBuilder.build().start()
  if(server != null)
    server.awaitTermination()
}
