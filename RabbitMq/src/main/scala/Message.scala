package rabbit

sealed trait Message {
    def lift: Option[Message] = Some(this)
    def getBytes: Array[Byte] = this.toString.getBytes
    def logMessage: Unit
}

case class OrderMessage(crewID: String, stuff: String) extends Message {
    override def toString = s"o{$crewID}{$stuff}"
    override def logMessage = println(s"New order:\n\t> CrewID: $crewID\n\t> Ordered: $stuff")
}

case class OrderProcessedMessage(crewID: String, orderID: String, stuff: String) extends Message {
    override def toString = s"c{$crewID}{$orderID}{$stuff}"
    override def logMessage = println(s"Order Confirmation:\n\t> CrewID: $crewID\n\t> OrderID: $orderID\n\t> Ordered: $stuff")
}

sealed trait AdminMessage extends Message {
    val who: String
    val msg: String
    override def toString = s"a{$who}{$msg}"
    override def logMessage = println(s"Admin Message:\n\t> To: $who\n\t> Message: $msg")
}

object AdminMessage {
    def apply(who: String, msg: String): Option[Message] = {
        who match {
            case "crews" =>     AdminCrewMessage(msg).lift            
            case "suppliers" => AdminSupplierMessage(msg).lift
            case "all" =>       AdminAllMessage(msg).lift
            case _ =>           None
        }
    }
}

case class AdminCrewMessage(msg: String) extends AdminMessage {
    val who = "crews"
}

case class AdminSupplierMessage(msg: String) extends AdminMessage {
    val who = "suppliers"
}

case class AdminAllMessage(msg: String) extends AdminMessage {
    val who = "all"
}

object Message {
    def apply(msg: String): Option[Message] = {
        msg match {
            case s"o{$crewID}{$stuff}" =>           OrderMessage(crewID, stuff).lift
            case s"c{$crewID}{$orderID}{$stuff}" => OrderProcessedMessage(crewID, orderID, stuff).lift
            case s"a{$who}{$m}" =>                  AdminMessage(who, m)
            case _ =>                               None
        }
    }
}