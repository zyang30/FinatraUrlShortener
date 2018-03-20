package com.twitter.urlshortener

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter

object UrlShortenerServerMain extends UrlShortenerServer

class UrlShortenerServer extends HttpServer{
  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[UrlShortenerController]
  }
}