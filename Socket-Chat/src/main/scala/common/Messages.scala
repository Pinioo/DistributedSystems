package common

import java.nio.charset.StandardCharsets

sealed trait Message {
  def typeChar(): Char
  def idString(): String
  def msgString(): String

  override def toString: String = s"${typeChar()}{${idString()}}{${msgString()}}"
  def toBytesArray: Array[Byte] = toString.getBytes(StandardCharsets.UTF_8)
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
  def apply(s: String): Option[Message] = {
    s match {
      case s"l{$id}{$_}"   => Some(UserLoggedInMessage(id))
      case s"d{$id}{$_}"   => Some(UserDisconnectedMessage(id))
      case s"m{$id}{$msg}" => Some(ChatMessage(id, msg))
      case _               => None
    }
  }
}

