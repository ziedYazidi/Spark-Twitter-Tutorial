package fr.finaxys.processing

import fr.finaxys.twitter.TweetStream
import fr.finaxys.accumulator.LongCounterFactory
import fr.finaxys.entrypoint.SparkEntryPoint
import fr.finaxys.entrypoint.SparkEntryPoint.checkpointDirectory
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

// This program aims to get the correlation between two words from the tweets
object CorrelationClass {


  def getCorrelation(): StreamingContext ={
    val ssc = SparkEntryPoint.createContext()
    val tweets = TweetStream.getTweetStream(ssc)
    val hashtagCounter = new LongCounterFactory("HashTag Counter").getInstance(ssc.sparkContext)
    val allTweetsCounter = new LongCounterFactory("All Tweets HashTag").getInstance(ssc.sparkContext)

    tweets.foreachRDD { rdd =>
      println(rdd.count())
      rdd.foreach { line =>
        if(line.getSource.contains("Android")){
          hashtagCounter.add(1)
        }
        allTweetsCounter.add(1)
        println(hashtagCounter.value + " Android tweets from " + allTweetsCounter.value + " tweets")

      }
    }
    ssc
  }

}
