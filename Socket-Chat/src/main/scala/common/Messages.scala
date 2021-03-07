package common

sealed trait Message {
  def typeChar(): Char
  def idString(): String
  def msgString(): String
  override def toString: String = s"${typeChar()}{${idString()}}{${msgString()}}"
}

case class ChatMessage(senderID: String, msg: String) extends Message {
  override def typeChar(): Char = 'm'
  override def idString(): String = senderID
  override def msgString(): String = msg
}

case class UserLoggedInMessage(userID: String) extends Message {
  override def typeChar(): Char = 'l'
  override def idString(): String = userID
  override def msgString(): String = ""
}

case class UserDisconnectedMessage(userID: String) extends Message {
  override def typeChar(): Char = 'd'
  override def idString(): String = userID
  override def msgString(): String = ""
}


object Message {
  def unapply(s: String): Option[Message] = {
    s match {
      case s"l{$id}{$_}"   => Some(UserLoggedInMessage(id))
      case s"d{$id}{$_}"   => Some(UserDisconnectedMessage(id))
      case s"m{$id}{$msg}" => Some(ChatMessage(id, msg))
      case _               => None
    }
  }
}

