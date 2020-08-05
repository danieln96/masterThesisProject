package pl.sggw.niczyporuk.performance.tests.scenarios

import io.gatling.core.Predef.scenario
import pl.sggw.niczyporuk.performance.tests.requests.GetPostRequest

object GetPostScenario {

  val getPostScenario = scenario("Get post scenario")
    .exec(GetPostRequest.get_post)
}
