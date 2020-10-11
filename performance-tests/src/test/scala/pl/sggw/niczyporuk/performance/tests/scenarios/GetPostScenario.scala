package pl.sggw.niczyporuk.performance.tests.scenarios

import io.gatling.core.Predef.scenario
import pl.sggw.niczyporuk.performance.tests.requests.PostRequest

object GetPostScenario {

  val getPostScenario = scenario("Get post scenario")
    .exec(PostRequest.create_post)
}
