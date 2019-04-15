package fr.finaxys.processing.sentimentAnalysis

import fr.finaxys.twitter.TweetStream
import fr.finaxys.entrypoint.SparkEntryPoint
import org.apache.spark.streaming.StreamingContext

object SentimentAnalysis {
  import SentimentAnalysisUtil._

  val ssc = SparkEntryPoint.createContext()
  val tweets = TweetStream.getTweetStream(ssc)

  def makeSentimentAnalysis(): StreamingContext ={

    //    Load words
    val uselessWords = ssc.sparkContext.broadcast(loadFile("/stop-words.dat" ))
    val positiveWords = ssc.sparkContext.broadcast(loadFile("/pos-words.dat" ))
    val negativeWords = ssc.sparkContext.broadcast(loadFile("/neg-words.dat"))

//    Extraire les mots des tweets
    val textWordsTuple = tweets
        .filter(_.getLang == "en")
        .map(_.getText)
        .map(tweetText => (tweetText, tweetText.split(" ")))

//    keeps meaningfull words (remove useless words) (add removing empty text and character letters)
    val textUsefullWordsTuple = textWordsTuple
        .mapValues(_.map(_.toLowerCase))
        .mapValues(_.filter(_.matches("[a-z]+")))
        .mapValues(_.filterNot(word => uselessWords.value.contains(word)))

//    calculate score
    val textScoreTuple = textUsefullWordsTuple
        .mapValues(words => computeScore(words, positiveWords.value, negativeWords.value))
        .filter{_._2 != 0}


    val formattedResult = textScoreTuple.map{
      case(x, y) => x + " => score " + y
    }

    formattedResult.print()

    ssc
  }

}
