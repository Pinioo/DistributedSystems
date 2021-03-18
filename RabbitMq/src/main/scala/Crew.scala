package rabbit

import com.rabbitmq.client.{AMQP, Channel, Connection, ConnectionFactory, Consumer, DefaultConsumer, Envelope, BuiltinExchangeType}
import java.io.IOException;
import scala.util.control.Breaks._
import scala.io.StdIn

object Crew {
    def main(args: Array[String]): Unit = {
        val factory = new ConnectionFactory()
        factory.setHost("localhost")
        println("Crew")
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        val exchangeName = "exchange"
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        
        val queueName = channel.queueDeclare().getQueue()
        channel.queueBind(queueName, exchangeName, "admin")
        channel.basicConsume(queueName, true, consumer)

        breakable { while(true){
            StdIn.readLine match {
                case "q" => break
                case m   => channel.basicPublish(exchangeName, m, null, m.getBytes)
            }
        }}
        channel.close()
        connection.close()
    }
}