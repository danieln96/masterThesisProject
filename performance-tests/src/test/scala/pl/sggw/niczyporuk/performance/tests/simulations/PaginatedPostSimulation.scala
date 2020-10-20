package pl.sggw.niczyporuk.performance.tests.simulations

import io.gatling.core.Predef.{Simulation, constantUsersPerSec, openInjectionProfileFactory}
import pl.sggw.niczyporuk.performance.tests.config.Config
import pl.sggw.niczyporuk.performance.tests.scenarios.PostScenario

import scala.concurrent.duration.DurationInt

class PaginatedPostSimulation extends Simulation {

  val getPostRequestExec = PostScenario.getPostsPaginated
    .inject(constantUsersPerSec(Config.throughput) during (60 seconds))

  setUp(getPostRequestExec)
}
