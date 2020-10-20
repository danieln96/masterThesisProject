package pl.sggw.niczyporuk.performance.tests.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import pl.sggw.niczyporuk.performance.tests.config.Config.{reactive_app_url, servlet_app_url}

object CommentRequest {

  val invalid_comment = exec(http("insert invalid comment request")
    .post(servlet_app_url + "/comment")
    .body(RawFileBody("comment_invalid.json")).asJson
    .check(status.is(403)))

  val valid_comment = exec(http("insert valid comment request")
    .post(servlet_app_url + "/comment")
    .body(RawFileBody("comment_valid.json")).asJson
    .check(status.is(200)))

}
