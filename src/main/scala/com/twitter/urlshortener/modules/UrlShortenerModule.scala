package com.twitter.urlshortener.modules

import com.twitter.inject.TwitterModule
import services.{RedisUrlShortenerService, UrlShortenerService}

object UrlShortenerModule extends TwitterModule{
  flag("secure", false, "Use HTTPS shortened URLS")

  override def configure(): Unit = {
    bind[UrlShortenerService].to[RedisUrlShortenerService]
  }
}
