package org.craftycoder.financekata
import java.time.LocalDate

trait StockPriceQuotesProvider {

  private def pricesURL(today: LocalDate, ticker: String): String = {
    val lastYear = today.minusYears(1)
    val url = f"http://real-chart.finance.yahoo.com/table.csv?s=$ticker&a=${lastYear.getMonthValue}&b=${lastYear.getDayOfMonth}&c=${lastYear.getYear}&d=${today.getMonthValue}&e=${today.getDayOfMonth}&f=${today.getYear}&g=d&ignore=.csv"
    url
  }

  def lastYearStockQuotes(ticker: String): List[String] = {
    import scala.io.Source
    val today = LocalDate.now()
    val html = Source.fromURL(pricesURL(today, ticker))
    val lines = html.getLines().toList
    lines
  }
}
