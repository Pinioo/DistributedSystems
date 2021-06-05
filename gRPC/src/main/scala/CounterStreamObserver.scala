import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver

case class CounterStreamObserver() extends StreamObserver[TestServices.Number] {
  override def onNext(value: TestServices.Number): Unit =
    println(value.getNumber)

  override def onError(t: Throwable): Unit =
    t.printStackTrace()

  override def onCompleted(): Unit =
    println("All number received")
}
