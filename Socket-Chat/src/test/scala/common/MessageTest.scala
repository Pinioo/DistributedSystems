package common

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MessageTest extends AnyFlatSpec with should.Matchers {
  "Message unapply" should "recognize message type of valid string" in {
    Message.unapply("l{id}{}") should be (Some(UserLoggedInMessage("id")))
    Message.unapply("d{id}{}") should be (Some(UserDisconnectedMessage("id")))
    Message.unapply("m{id}{msg}") should be (Some(ChatMessage("id", "msg")))
  }

  it should "recognize invalid string" in {
    Message.unapply("x{id}{msg}") should be (None)
    Message.unapply("m{id}") should be (None)
    Message.unapply("{id}{msg}") should be (None)
  }
}
