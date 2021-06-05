import io.grpc.{Status, StatusRuntimeException}
import io.grpc.stub.StreamObserver
case class FibonacciServiceImpl() extends FibonacciServiceGrpc.FibonacciServiceImplBase {
  override def fibStream(request: TestServices.Number, responseObserver: StreamObserver[TestServices.Number]): Unit = {
    println(s"Request with value ${request.getNumber} received")
    lazy val fibs: LazyList[Long] = LazyList[Long](0, 1) #::: fibs.zip(fibs.tail).map(n => n._1 + n._2)
    fibs.takeWhile(_ < request.getNumber).foreach {
      n =>
        Thread.sleep(100)
        responseObserver.onNext(TestServices.Number.newBuilder().setNumber(n).build())
    }
    responseObserver.onCompleted()
  }
}
