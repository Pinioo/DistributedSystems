import io.grpc.ManagedChannelBuilder

import scala.io.StdIn
import scala.jdk.CollectionConverters.IteratorHasAsScala

object Client extends App {
  val channelBuilder = ManagedChannelBuilder.forAddress("localhost", 50000)
  channelBuilder.usePlaintext()
  val channel = channelBuilder.build()
  val fibBlockingStub = FibonacciServiceGrpc.newBlockingStub(channel)
  val fibAsyncStub = FibonacciServiceGrpc.newStub(channel)
  val helloBlockingStub = HelloServiceGrpc.newBlockingStub(channel)
  println("Options")
  println("\t1 {number} : blocking")
  println("\t2 {number} : asynchronous")
  println("\t3 {number} : hello")
  println("\tq : quit")
  Iterator.continually(StdIn.readLine("Your option: ")).takeWhile(_ != "q").foreach{
    case s"1 $numberOpt" => numberOpt
      .toLongOption
      .foreach{
        number => fibBlockingStub
          .fibStream(toNumber(number)).asScala
          .foreach(response => println(response.getNumber))
      }
    case s"2 $numberOpt" => numberOpt
      .toLongOption
      .foreach{
        number => fibAsyncStub
          .fibStream(toNumber(number), CounterStreamObserver())
      }
    case s"3 $numberOpt" => numberOpt
      .toLongOption
      .foreach{
        number =>
          println(helloBlockingStub.getHello(toNumber(number)).getMessage)
      }
    case _ => println("Unknown command")
  }

  def toNumber(number: Long) = TestServices.Number.newBuilder().setNumber(number).build()
}
