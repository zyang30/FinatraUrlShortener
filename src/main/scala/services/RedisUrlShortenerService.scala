package services


import javax.inject.Inject
import redis.clients.jedis.{Jedis => JedisClient}

import scala.util.Random

object RedisUrlShortenerService {
  val KeyPrefix = "url-"
}

class RedisUrlShortenerService @Inject()(client: JedisClient) extends UrlShortenerService {


  def create(url: String): String = {
    val addr = new Random().nextInt()
    client.set(
      "%s%s".format("url-", addr.toString),
      url.toString)
    java.lang.Long.toString(addr)
  }

  def get(id: String): Option[String] = {
    val value = java.lang.Long.valueOf(id)
    Option(
      client.get(
        "%s%s".format("url-", value.toString)))
  }
}
