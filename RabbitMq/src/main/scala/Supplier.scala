package rabbit

import com.rabbitmq.client.{AMQP, Channel, Connection, ConnectionFactory, Consumer, DefaultConsumer, Envelope, BuiltinExchangeType}
import java.io.IOException;
import scala.io.StdIn
import scala.util.control.Breaks._

object Supplier {
    def main(args: Array[String]): Unit = {
        val availableStuff = Set("shoes", "oxygen", "backpack")
        val factory = new ConnectionFactory()
        val exchangeName = "exchange"
        factory.setHost("localhost")
        val keys = args.toSet.filter(availableStuff.contains)

        val connection = factory.newConnection()
        val channel = connection.createChannel()
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC)

        val consumer = new DefaultConsumer(channel) {
            override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]) {
                val message = new String(body, "UTF-8")
                println("Received: " + message)
            }
        }

        keys.foreach{
            typeOfStuff => 
                val queueName = typeOfStuff
                channel.queueDeclare(queueName, false, false, false, null);
                channel.queueBind(queueName, exchangeName, typeOfStuff)
                channel.basicConsume(queueName, true, consumer)
        }

        val queueName = channel.queueDeclare().getQueue
        channel.queueBind(queueName, exchangeName, "admin")
        channel.basicConsume(queueName, true, consumer)

        StdIn.readLine
        channel.close()
        connection.close()
    }
}