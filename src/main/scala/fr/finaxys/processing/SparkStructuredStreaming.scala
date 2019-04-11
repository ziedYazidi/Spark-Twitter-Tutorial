package fr.finaxys.processing

import org.apache.spark.sql.{Dataset, SparkSession}

object SparkStructuredStreaming {

//  For each class you can add the attributed that you need for your processing
//  Note that case class parameters are limited to 22 Parmeters
  case class User(id: Option[Long], id_str: String, name: String, screen_name: String, location :String, url: String, description: String, verified: Boolean,
                  followers_count: Option[Long], friends_count: Option[Long], listed_count: Option[Long], favourites_count: Option[Long], statuses_count: Option[Long], created_at: String, geo_enabled: Boolean,
                  lang: String, contributors_enabled: Boolean, profile_background_color: String, profile_background_image_url: String, profile_background_image_url_https: String)

  case class Coodinates(coordinates: List[Double], `type`: String)

  case class Place(id: String, url: String, place_type: String, name: String, full_name: String, country_code: String, country: String)

  case class Tweet(created_at: String, id: Option[Long], id_str	: String, text: String, source: String, truncated: Boolean, in_reply_to_status_id: Option[Long], in_reply_to_status_id_str: String,
                        in_reply_to_user_id: Option[Long], in_reply_to_user_id_str: String, in_reply_to_screen_name: String, user: User, coordinates: Coodinates, place: Place)


  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Spark batch").config("spark.master", "local").getOrCreate()
    import spark.implicits._
    val path = "/home/finaxys/Bureau/CodinGame/Hashtag-Correlation/src/main/resources/tweets.json"
    val tweets = spark.read.json(path)
    val ds: Dataset[Tweet] = tweets.as[Tweet]
    ds.filter(_.user.location == "Barcelona").select("coordinates").show()
//    val words = ds.map(_.text).flatMap(_.split(" "))

  }



}
