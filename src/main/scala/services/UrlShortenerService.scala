package services

trait UrlShortenerService {

  def create(url: String): String

  def get(id: String): Option[String]
}
