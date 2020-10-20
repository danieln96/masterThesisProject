package pl.sggw.niczyporuk.performance.tests.scenarios

import io.gatling.core.Predef.scenario
import pl.sggw.niczyporuk.performance.tests.requests.PostRequest

object PostScenario {

  val insertPost = scenario("insert post")
    .exec(PostRequest.create_post)

  val getPostsPaginated = scenario("get post paginated")
    .exec(PostRequest.get_post_random_page)

  val getAllPosts = scenario("get all posts")
    .exec(PostRequest.get_all_posts)
}
