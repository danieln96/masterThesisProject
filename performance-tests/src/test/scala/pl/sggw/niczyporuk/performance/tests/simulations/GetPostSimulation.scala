package pl.sggw.niczyporuk.performance.tests.simulations

import pl.sggw.niczyporuk.performance.tests.config.Config.users
import io.gatling.core.Predef.{Simulation, atOnceUsers, openInjectionProfileFactory}
import pl.sggw.niczyporuk.performance.tests.scenarios.GetPostScenario

class GetPostSimulation extends Simulation {

  private val getPostExec = GetPostScenario.getPostScenario
    .inject(atOnceUsers(users))

  setUp(getPostExec)
}
