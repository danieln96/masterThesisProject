package pl.sggw.niczyporuk.performance.tests.scenarios

import io.gatling.core.Predef.scenario
import pl.sggw.niczyporuk.performance.tests.requests.CommentRequest

object CommentScenario {

  val insertInvalidComment = scenario("insert invalid comment").exec(CommentRequest.invalid_comment)
  val insertValidComment = scenario("insert valid comment").exec(CommentRequest.valid_comment)

}
