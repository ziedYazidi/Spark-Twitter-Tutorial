package fr.finaxys.accumulator

import org.apache.spark.SparkContext
import org.apache.spark.util.LongAccumulator

class LongCounterFactory(name: String){

  @volatile private var instance: LongAccumulator = null

  def getInstance(sc: SparkContext): LongAccumulator = {
    if (instance == null) {
      synchronized {
        if (instance == null) {
          instance = sc.longAccumulator(name)
        }
      }
    }
    instance
  }
}