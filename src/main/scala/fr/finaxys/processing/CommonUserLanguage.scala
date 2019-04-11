package fr.finaxys.processing

import fr.finaxys.twitter.TweetStream
import fr.finaxys.accumulator.LongCounterFactory
import fr.finaxys.entrypoint.SparkEntryPoint
import org.apache.spark.streaming.{Seconds, StreamingContext}

// find the most common user language in the tweets
object CommonUserLanguage {

  def getCommonLanguage(): StreamingContext ={

    val ssc = SparkEntryPoint.createContext()
    val tweets = TweetStream.getTweetStream()

    val firstLang = new LongCounterFactory("First Lang").getInstance(ssc.sparkContext)
    val secondLang = new LongCounterFactory("Second Lang").getInstance(ssc.sparkContext)
    val thirdLang = new LongCounterFactory("Third Lang").getInstance(ssc.sparkContext)
    val tweetsLanguage = tweets.map(tweet => (tweet.getLang, 1))
    val languageCount = tweetsLanguage.reduceByKeyAndWindow( _ + _, Seconds(10)).map(_.swap).transform{
      elem => elem.sortByKey(ascending = false)
    }
    val top3 = languageCount.transform{ rdd =>
      rdd.filter(rdd.take(3).toList.contains)
    }
    top3.print()
    ssc
  }

}
