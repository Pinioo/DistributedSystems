package rabbit

import com.rabbitmq.client.{AMQP, Channel, Connection, ConnectionFactory, Consumer, DefaultConsumer, Envelope, BuiltinExchangeType}
import java.io.IOException;
import scala.util.control.Breaks._
import scala.io.StdIn

object Crew {
    def main(args: Array[String]): Unit = {
        // RABBITMQ SETUP
        val factory = new ConnectionFactory()
        factory.setHost("localhost")
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        val exchangeName = "exchange"
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);
        
        val consumer = new DefaultConsumer(channel) {
            override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]) {
                val messageStr = new String(body, "UTF-8")
                Message(messageStr) match {
                    case Some(msg) => msg match {
                        case _: OrderProcessedMessage | _: AdminCrewMessage | _: AdminAllMessage =>
                            msg.logMessage
                        case _ =>
                            print("[???] ")
                            msg.logMessage
                    }
                    case None => println(s"Received imcompatible message: $messageStr")
                }
            }
        }

        val queueName = channel.queueDeclare().getQueue()
        channel.queueBind(queueName, exchangeName, "#.crew.#")
        ////

        channel.basicConsume(queueName, true, consumer)

        breakable { while(true){
            StdIn.readLine.strip match {
                case "/q" => break
                case m   => channel.basicPublish(exchangeName, m, null, OrderMessage(queueName, m).getBytes)
            }
        }}
        channel.close()
        connection.close()
    }
}