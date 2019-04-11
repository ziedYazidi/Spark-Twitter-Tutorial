package fr.finaxys.twitter

import fr.finaxys.entrypoint.SparkEntryPoint
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.Status

object TweetStream {
  val ssc= SparkEntryPoint.createContext()
  def getTweetStream(): ReceiverInputDStream[Status] ={
    val tweets = TwitterUtils.createStream(ssc, TwitterUtil.getAuth())
    tweets
  }
}