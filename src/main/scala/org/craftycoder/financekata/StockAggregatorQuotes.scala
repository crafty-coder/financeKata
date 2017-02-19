package org.craftycoder.financekata


trait StockAggregatorQuotes extends StockPriceQuotesProvider {

  def dailyPrices(ticker: String): List[Double] = {

    val lines: List[String] = lastYearStockQuotes(ticker)

    lastYearStockQuotes(ticker) match {
      case _ :: Nil => Nil
      case Nil => Nil
      case header :: body =>
        val indexClose = header.split(",").indexWhere(_ == "Close")
        val extraxtCloseQuote: String => Double = line => line.split(",")(indexClose).toDouble
        lines.tail.map(extraxtCloseQuote)

    }

  }

  def returns(ticker: String): Seq[Double] = {

    def calculateDailyReturn(dailyPrices: List[Double]): Seq[Double] = {

      if (dailyPrices.size < 2) return Nil

      dailyPrices
        .sliding(2)
        .map({
          case priceYesterday :: priceToday :: Nil => (priceToday - priceYesterday) / priceYesterday
          case _ => throw new RuntimeException("Incorrect Sliding")
        })
        .toSeq

    }

    (dailyPrices _ andThen calculateDailyReturn) (ticker)

  }

  def meanReturn(ticker: String): Double = {

    def calculateMean(yearPrices: Seq[Double]): Double = {
      yearPrices
        .foldLeft((0.0, 1)) { case ((avg, idx), next) => (avg + (next - avg) / idx, idx + 1) }._1

    }

    (returns _ andThen calculateMean) (ticker)

  }
}
