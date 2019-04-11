package fr.finaxys.processing.sentimentAnalysis

import scala.io.Source

object SentimentAnalysisUtil {

  def computeScore(words: Array[String], positiveWords: Set[String], negativeWords: Set[String]): Int = {
    words.map(word => computeWordScore(word, positiveWords, negativeWords)).sum
  }

  def computeWordScore(word: String, positiveWords: Set[String], negativeWords: Set[String]): Int = {
    if (positiveWords.contains(word)) 1
    else if (negativeWords.contains(word)) -1
    else 0
  }

  def loadFile(resourcePath: String): Set[String] = {
    val source = Source.fromInputStream(getClass.getResourceAsStream(resourcePath))
    val words = source.getLines.toSet
    source.close()
    words
  }

}
