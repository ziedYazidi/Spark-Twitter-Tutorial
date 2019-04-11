package fr.finaxys.processing

import fr.finaxys.twitter.TweetStream
import fr.finaxys.accumulator.LongCounterFactory
import fr.finaxys.entrypoint.SparkEntryPoint
import org.apache.spark.streaming.StreamingContext

object AverageWordsPerTweet {

  def getAvgWordsPerTweet(): StreamingContext ={


    val ssc = SparkEntryPoint.createContext()
    val tweets = TweetStream.getTweetStream()

    val hashtagCounter = new LongCounterFactory("HashTag Counter").getInstance(ssc.sparkContext)
    val allTweetsCounter = new LongCounterFactory("HashTag Counter").getInstance(ssc.sparkContext)
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
