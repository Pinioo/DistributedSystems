import io.grpc.stub.StreamObserver

case class HelloServiceImpl() extends HelloServiceGrpc.HelloServiceImplBase {
  override def getHello(request: TestServices.Number, responseObserver: StreamObserver[TestServices.HelloMessage]): Unit = {
    println(s"Sending Hello to ${request.getNumber}")
    responseObserver.onNext(TestServices.HelloMessage.newBuilder().setMessage(s"Hello to ${request.getNumber}").build())
    responseObserver.onCompleted()
  }
}
