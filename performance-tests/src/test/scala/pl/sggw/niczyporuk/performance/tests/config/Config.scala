package pl.sggw.niczyporuk.performance.tests.config

object Config {
  val servlet_app_url = "http://localhost:8080"
  val reactive_app_url = "http://localhost:8070"

  val users = Integer.getInteger("users", 10).toInt
  val rampUp = Integer.getInteger("rampup", 1).toInt
  val duration = Integer.getInteger("duration", 60).toInt
  val throughput = Integer.getInteger("throughput", 100).toInt
}
