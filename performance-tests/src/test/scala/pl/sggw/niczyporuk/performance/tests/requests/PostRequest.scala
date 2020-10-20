package pl.sggw.niczyporuk.performance.tests.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import pl.sggw.niczyporuk.performance.tests.config.Config.{reactive_app_url, servlet_app_url}

object PostRequest {

  val random = scala.util.Random

  val get_all_posts = exec(http("get post request")
    .get(servlet_app_url + "/post")
    .check(status is 200))

  val create_post = exec(http("create post request")
    .post(servlet_app_url + "/post")
    .body(RawFileBody("post.json")).asJson
    .check(status.is(201)))

  val get_post_random_page = exec(http("get post request")
    .get(servlet_app_url + "/post/page")
    .queryParam("page", random.nextInt(10))
    .queryParam("size", 10)
    .check(status is 200))
}
