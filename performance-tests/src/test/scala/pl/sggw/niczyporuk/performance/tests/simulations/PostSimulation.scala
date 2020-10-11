package pl.sggw.niczyporuk.performance.tests.simulations

import io.gatling.core.Predef.{Simulation, constantUsersPerSec, openInjectionProfileFactory}
import pl.sggw.niczyporuk.performance.tests.scenarios.GetPostScenario

import scala.concurrent.duration.DurationInt

class PostSimulation extends Simulation {

  private val getPostRequestExec = GetPostScenario.getPostScenario
    .inject(constantUsersPerSec(400) during (60 seconds))

  setUp(getPostRequestExec)

}
