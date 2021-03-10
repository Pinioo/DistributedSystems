package common

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MessageTest extends AnyFlatSpec with should.Matchers {
  val id = "id"
  val msg = "msg"

  "Message unapply" should "recognize message type of valid string" in {
    Message(s"l{$id}{}") should be (Some(UserLoggedInMessage(id)))
    Message(s"d{$id}{}") should be (Some(UserDisconnectedMessage(id)))
    Message(s"m{$id}{$msg}") should be (Some(ChatMessage(id, msg)))
  }

  it should "recognize invalid string" in {
    Message(s"x{$id}{$msg}") should be (None)
    Message(s"m{$id}") should be (None)
    Message(s"{$id}{$msg}") should be (None)
  }

  "Message toString" should "give valid $type{$id}{$msg} strings" in {
    ChatMessage(id, msg).toString should be (s"m{$id}{$msg}")
    UserLoggedInMessage(id).toString should be (s"l{$id}{}")
    UserDisconnectedMessage(id).toString should be (s"d{$id}{}")
  }
}
