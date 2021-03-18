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
        
        val consumer = new DefaultConsumer(channel) {
            override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]) {
                val message = new String(body, "UTF-8")
                println("Received: " + message)
            }
        }

        val queueName = channel.queueDeclare().getQueue()
        channel.queueBind(queueName, exchangeName, "#.crew.#")
        channel.basicConsume(queueName, true, consumer)

        breakable { while(true){
            StdIn.readLine.strip match {
                case "/q" => break
                case m   => channel.basicPublish(exchangeName, m, null, m.getBytes)
            }
        }}
        channel.close()
        connection.close()
    }
}