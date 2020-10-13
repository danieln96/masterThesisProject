package pl.sggw.niczyporuk.performance.tests.simulations

import io.gatling.core.Predef.{Simulation, constantUsersPerSec, openInjectionProfileFactory}
import pl.sggw.niczyporuk.performance.tests.scenarios.CommentScenario

import scala.concurrent.duration.DurationInt

class InvalidCommentSimulation extends Simulation {

  val insertInvalidCommentsExec = CommentScenario.insertInvalidComment
    .inject(constantUsersPerSec(400) during (60 seconds))

  setUp(insertInvalidCommentsExec)
}