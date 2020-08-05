package pl.sggw.niczyporuk.performance.tests.requests

import pl.sggw.niczyporuk.performance.tests.config.Config.servlet_app_url
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetPostRequest {

  val get_post = exec(http("get post request")
    .get(servlet_app_url + "/post")
    .check(status is 200))
}
