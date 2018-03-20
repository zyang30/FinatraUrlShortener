package com.twitter.urlshortener

import java.net.URL

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.response.ResponseBuilder
import services._
import com.twitter.inject.annotations.Flag
import javax.inject.Inject

class UrlShortenerController@Inject()(@Flag("secure") secure: Boolean, urlShortenerService: RedisUrlShortenerService, response: ResponseBuilder) extends Controller  {

  get("/:id") { request: Request =>
    urlShortenerService.get(request.params.getOrElse("id", "")) match {
      case Some(url) =>
        info(s"Redirecting to resolved URL for id: ${request.params.getOrElse("id", "")} -> $url")
        response.movedPermanently.location(url)
      case _ => response.notFound
    }
  }


  post("/"){ request: Request =>

//    val url = new URL(request.params.getOrElse("url", ""))
    val url: String = request.params.getOrElse("url", "")
//    val addr = new Random().nextInt()
    val addr = urlShortenerService.create(url)
    val protocol = "http"
    val host = request.host getOrElse "localhost"
    s"Your URL:$url \nYour Shorten URL: $protocol://$host/$addr"
}
}
