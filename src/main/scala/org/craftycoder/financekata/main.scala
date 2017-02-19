package org.craftycoder.financekata

object main extends StockAggregatorQuotes {


  def main(args: Array[String]): Unit = {

    lastYearStockQuotes("GOOG").foreach(println)

//
//    println("Hello, world!")
//    dailyPrices("GOOG").foreach(println)
//    returns("GOOG").foreach(println)
//    println(meanReturn("GOOG"))
//
//    dailyPrices("GOOG").zip(returns("GOOG")).foreach(println)
  }


}

