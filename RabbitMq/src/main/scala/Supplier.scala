package rabbit

import com.rabbitmq.client.{AMQP, Channel, Connection, ConnectionFactory, Consumer, DefaultConsumer, Envelope, BuiltinExchangeType}

import java.io.IOException;
import java.util.UUID

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
                val messageStr = new String(body, "UTF-8")
                Message(messageStr) match {
                    case Some(msg) => msg match {
                        case OrderMessage(crewID, stuff) =>
                            val orderID = UUID.randomUUID.toString
                            msg.logMessage
                            channel.basicPublish("", crewID, null, OrderProcessedMessage(crewID, orderID, stuff).getBytes)
                            println(s">>> Confirmation sent for order $orderID")
                        case _: AdminSupplierMessage | _: AdminAllMessage =>
                            msg.logMessage
                        case _ =>
                            print("[???] ")
                            msg.logMessage
                    }
                    case None => println(s"Received imcompatible message: $messageStr")
                }
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
        channel.queueBind(queueName, exchangeName, "#.supplier.#")
        channel.basicConsume(queueName, true, consumer)

        StdIn.readLine
        channel.close()
        connection.close()
    }
}