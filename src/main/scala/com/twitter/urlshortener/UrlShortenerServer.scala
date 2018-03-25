package com.twitter.urlshortener

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.urlshortener.modules.{JedisClientModule, UrlShortenerModule}

object UrlShortenerServerMain extends UrlShortenerServer

class UrlShortenerServer extends HttpServer{
  override def modules = Seq(
    JedisClientModule,
    UrlShortenerModule)

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[UrlShortenerController]
  }
}