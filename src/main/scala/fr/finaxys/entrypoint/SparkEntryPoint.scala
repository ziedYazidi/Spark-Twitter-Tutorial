package fr.finaxys.entrypoint

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkEntryPoint {

  val checkpointDirectory = "file:///home/finaxys/Bureau/directory"

  def createContext(): StreamingContext = {
    val conf = new SparkConf().setAppName("Hashtags correlation").setMaster("local[3]")
    val ssc = new StreamingContext(conf, Seconds(1))
    ssc.checkpoint(checkpointDirectory)
    ssc
  }
}
