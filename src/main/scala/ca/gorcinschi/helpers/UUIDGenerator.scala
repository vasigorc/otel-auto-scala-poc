package ca.gorcinschi.helpers

import java.util.UUID

trait UUIDGenerator {
  def uuid(): UUID = UUID.randomUUID()
  def stringifiedUUID(): String = uuid().toString
}
