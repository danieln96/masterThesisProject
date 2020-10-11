package pl.sggw.niczyporuk.performance.tests.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import pl.sggw.niczyporuk.performance.tests.config.Config.{reactive_app_url, servlet_app_url}

object PostRequest {

  val random = scala.util.Random

  val get_post_ = exec(http("get post request")
    .get(reactive_app_url + "/post")
    .check(status is 200))

  val create_post = exec(http("create post request")
    .post(reactive_app_url + "/post")
    .body(RawFileBody("post.json")).asJson
    .check(status.is(200)))

  val get_post = exec(http("get post request")
    .get(reactive_app_url + "/post/page")
    .queryParam("page", random.nextInt(10))
    .queryParam("size", 10)
    .check(status is 200))

  //.body(StringBody("{\n  \"title\": \"Everything is Illuminated\",\n  \"lead\": \"wbg9sgaeg5khq82dnxu2fztf3y1lcuepddc6v0qkjqlxyj0p4l8l2ppwx8of5k1f478q80vafi7akpkb5606x7g10dmrm8jqbpws2e1amiiuqbqzgmybnr6lzqjow1tp88l9qzknmk6ph1d57tjl1\",\n  \"signature\": \"Sooty\",\n  \"photoUrl\": \"www.miron-xn--sowik-k7a.org\",\n  \"createDate\": \"2019-06-15T21:44:48.669\",\n  \"updateDate\": \"2019-07-15T21:44:48.669\",\n  \"bodyPost\": {\n    \"paragraphs\": [\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Jynx\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Wigglytuff\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Nidoking\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Omanyte\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Jynx\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Sandslash\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Sandslash\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Dugtrio\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Arbok\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Caterpie\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Gengar\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Weedle\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Machoke\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Ekans\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Starmie\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Wartortle\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Tauros\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Seadra\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Magneton\"\n      },\n      {\n        \"paragraphType\": \"text\",\n        \"content\": \"Electabuzz\"\n      }\n    ]\n  }\n}"))
}
