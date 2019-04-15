package fr.finaxys.processing


import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

import org.mockito.Mock


object SparkStructuredStreamingTest {



  @Test
  def getUsersByCountry() = {
//    Arrange

//    Act
    val result = SparkStructuredStreaming.getUsersByCountry("Barcelona")

//    Assert
    assertEquals(4,  result.count())

  }
}
