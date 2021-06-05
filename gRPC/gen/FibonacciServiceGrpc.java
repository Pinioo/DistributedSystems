import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: TestServices.proto")
public final class FibonacciServiceGrpc {

  private FibonacciServiceGrpc() {}

  public static final String SERVICE_NAME = "FibonacciService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<TestServices.Number,
      TestServices.Number> getFibStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "fibStream",
      requestType = TestServices.Number.class,
      responseType = TestServices.Number.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<TestServices.Number,
      TestServices.Number> getFibStreamMethod() {
    io.grpc.MethodDescriptor<TestServices.Number, TestServices.Number> getFibStreamMethod;
    if ((getFibStreamMethod = FibonacciServiceGrpc.getFibStreamMethod) == null) {
      synchronized (FibonacciServiceGrpc.class) {
        if ((getFibStreamMethod = FibonacciServiceGrpc.getFibStreamMethod) == null) {
          FibonacciServiceGrpc.getFibStreamMethod = getFibStreamMethod =
              io.grpc.MethodDescriptor.<TestServices.Number, TestServices.Number>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "fibStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  TestServices.Number.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  TestServices.Number.getDefaultInstance()))
              .setSchemaDescriptor(new FibonacciServiceMethodDescriptorSupplier("fibStream"))
              .build();
        }
      }
    }
    return getFibStreamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FibonacciServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceStub>() {
        @java.lang.Override
        public FibonacciServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FibonacciServiceStub(channel, callOptions);
        }
      };
    return FibonacciServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FibonacciServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceBlockingStub>() {
        @java.lang.Override
        public FibonacciServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FibonacciServiceBlockingStub(channel, callOptions);
        }
      };
    return FibonacciServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FibonacciServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceFutureStub>() {
        @java.lang.Override
        public FibonacciServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FibonacciServiceFutureStub(channel, callOptions);
        }
      };
    return FibonacciServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FibonacciServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void fibStream(TestServices.Number request,
        io.grpc.stub.StreamObserver<TestServices.Number> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFibStreamMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFibStreamMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                TestServices.Number,
                TestServices.Number>(
                  this, METHODID_FIB_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class FibonacciServiceStub extends io.grpc.stub.AbstractAsyncStub<FibonacciServiceStub> {
    private FibonacciServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FibonacciServiceStub(channel, callOptions);
    }

    /**
     */
    public void fibStream(TestServices.Number request,
        io.grpc.stub.StreamObserver<TestServices.Number> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getFibStreamMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FibonacciServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<FibonacciServiceBlockingStub> {
    private FibonacciServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FibonacciServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<TestServices.Number> fibStream(
        TestServices.Number request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getFibStreamMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FibonacciServiceFutureStub extends io.grpc.stub.AbstractFutureStub<FibonacciServiceFutureStub> {
    private FibonacciServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FibonacciServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_FIB_STREAM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FibonacciServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FibonacciServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIB_STREAM:
          serviceImpl.fibStream((TestServices.Number) request,
              (io.grpc.stub.StreamObserver<TestServices.Number>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FibonacciServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FibonacciServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return TestServices.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FibonacciService");
    }
  }

  private static final class FibonacciServiceFileDescriptorSupplier
      extends FibonacciServiceBaseDescriptorSupplier {
    FibonacciServiceFileDescriptorSupplier() {}
  }

  private static final class FibonacciServiceMethodDescriptorSupplier
      extends FibonacciServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FibonacciServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FibonacciServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FibonacciServiceFileDescriptorSupplier())
              .addMethod(getFibStreamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
