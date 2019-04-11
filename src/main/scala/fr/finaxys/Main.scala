package fr.finaxys

import fr.finaxys.entrypoint.SparkEntryPoint
import fr.finaxys.entrypoint.SparkEntryPoint.checkpointDirectory
import fr.finaxys.processing.CorrelationClass
import fr.finaxys.processing.sentimentAnalysis.SentimentAnalysis
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Main {
  def main(args: Array[String]): Unit = {
//    Count the average words per tweet
//    val context = StreamingContext.getOrCreate(SparkEntryPoint.checkpointDirectory, AverageWordsPerTweet.getAvgWordsPerTweet)

//    Count the most common user language
//    val context = StreamingContext.getOrCreate(SparkEntryPoint.checkpointDirectory, CommonUserLanguage.getCommonLanguage)
//    Get the correlation betweeen two hashtags
    val context = StreamingContext.getOrCreate(checkpointDirectory, CorrelationClass.getCorrelation)

//    Get a Sentiment analysis of each tweet
//    val context = StreamingContext.getOrCreate(SparkEntryPoint.checkpointDirectory, SentimentAnalysis.makeSentimentAnalysis)
    context.stop();

    context.start()
    context.awaitTermination()

  }
}
