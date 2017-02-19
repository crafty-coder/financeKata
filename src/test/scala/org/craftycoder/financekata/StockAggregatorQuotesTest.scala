package org.craftycoder.financekata

import org.scalatest.{FunSuite, Matchers, WordSpec}

class StockAggregatorQuotesTest extends WordSpec with Matchers {

  "dailyPrices" should {
    "return empty list when no quotes" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def lastYearStockQuotes(ticker: String): List[String] = Nil
      }

      stockAggregatorQuotes.dailyPrices("Ticker") shouldBe Nil

    }

    "return empty list when just header" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def lastYearStockQuotes(ticker: String): List[String] =
          List("Date,Open,High,Low,Close,Volume,Adj Close")
      }

      stockAggregatorQuotes.dailyPrices("Ticker") shouldBe Nil

    }

    "return one element list when header and one element" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def lastYearStockQuotes(ticker: String): List[String] =
          List(
            "Date,Open,High,Low,Close,Volume,Adj Close",
            "2016-03-22,737.460022,745.00,737.460022,740.75,1269700,740.75"
          )
      }

      stockAggregatorQuotes.dailyPrices("Ticker") shouldBe List(740.75)

    }

    "return list of size N when header and N elements" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def lastYearStockQuotes(ticker: String): List[String] =
          List(
            "Date,Open,High,Low,Close,Volume,Adj Close",
            "2016-03-24,732.01001,737.747009,731.00,735.299988,1594900,735.299988",
            "2016-03-23,742.359985,745.719971,736.150024,738.059998,1432100,738.059998",
            "2016-03-22,737.460022,745.00,737.460022,740.75,1269700,740.75",
            "2016-03-21,736.50,742.50,733.515991,742.090027,1836500,742.090027"
          )
      }

      stockAggregatorQuotes.dailyPrices("Ticker") shouldBe List(735.299988, 738.059998, 740.75, 742.090027)

    }
  }

  "returns" should {

    "return empty seq when dailyPrices is empty" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def dailyPrices(ticker: String): List[Double] = Nil
      }

      stockAggregatorQuotes.returns("Ticker") shouldBe Nil

    }

    "return empty seq when there is one Quote" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def dailyPrices(ticker: String): List[Double] = List(740.40)
      }

      stockAggregatorQuotes.returns("Ticker") shouldBe Nil

    }

    "return daily returns when there is two Quotes" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def dailyPrices(ticker: String): List[Double] = List(740.40, 805.55)
      }

      stockAggregatorQuotes.returns("Ticker") shouldBe List(0.08799297676931386)

    }

    "return daily returns when there is three Quotes" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def dailyPrices(ticker: String): List[Double] = List(740.40, 805.55, 754.29)
      }

      stockAggregatorQuotes.returns("Ticker") shouldBe List(0.08799297676931386, -0.06363354230029172)

    }
  }

  "meanReturn" should {

    "return 0.0 when no quotes" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def returns(ticker: String): Seq[Double] = Nil
      }

      stockAggregatorQuotes.meanReturn("Ticker") shouldBe 0.0

    }

    "return the element list has just one element" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def returns(ticker: String): Seq[Double] = List(740.40)
      }

      stockAggregatorQuotes.meanReturn("Ticker") shouldBe 740.40

    }

    "return the mean of all the quotes" in {

      val stockAggregatorQuotes = new StockAggregatorQuotes {
        override def returns(ticker: String): Seq[Double] = List(10,20)
      }

      stockAggregatorQuotes.meanReturn("Ticker") shouldBe 15

    }


  }


}
