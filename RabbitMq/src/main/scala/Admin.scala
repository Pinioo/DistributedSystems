package rabbit

import com.rabbitmq.client.{AMQP, Channel, Connection, ConnectionFactory, Consumer, DefaultConsumer, Envelope, BuiltinExchangeType}
import java.io.IOException;
import scala.io.StdIn
import scala.util.control.Breaks._

object Admin {
    def main(args: Array[String]): Unit = {
        val factory = new ConnectionFactory()
        val exchangeName = "exchange"
        factory.setHost("localhost")

        val connection = factory.newConnection()
        val channel = connection.createChannel()
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC)

        val consumer = new DefaultConsumer(channel) {
            override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]) {
                val message = new String(body, "UTF-8")
                println("Received: " + message)
            }
        }

        val queueName = channel.queueDeclare().getQueue()
        channel.queueBind(queueName, exchangeName, "#")
        channel.basicConsume(queueName, true, consumer)

        breakable { while(true){
            StdIn.readLine match {
                case "q" => break
                case m   => channel.basicPublish(exchangeName, "admin", null, m.getBytes)
            }
        }}
        channel.close()
        connection.close()
    }
}