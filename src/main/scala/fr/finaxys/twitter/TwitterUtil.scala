package fr.finaxys.twitter

import twitter4j.auth.OAuthAuthorization
import twitter4j.conf.ConfigurationBuilder

object TwitterUtil {

  //    Twitter Infos
  val consumerKey = "rTgzqkahZpgtBHg2R5zShGQRE"
  val consumerSecret = "7874mcgpAZwvqA4Gc59Q0ox93aM46uSxXEZy0VQIJtccPN8Eek"
  val accessToken ="982257313166671872-wBmGPbiLbWTY6tNFWl361dtTHaKzdRx"
  val accessTokenSecret = "vYIU8Euhjt139K0h1EkL9ncW0z7874STcJFDJ2tmaphTr"


  //    Twitter Config
  val cb = new ConfigurationBuilder
  cb.setDebugEnabled(true)
    .setOAuthConsumerKey(consumerKey)
    .setOAuthConsumerSecret(consumerSecret)
    .setOAuthAccessToken(accessToken)
    .setOAuthAccessTokenSecret(accessTokenSecret)


  def getAuth() ={
    Some(new OAuthAuthorization(cb.build))
  }


}
