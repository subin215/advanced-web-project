package services;

import assets.CurrencyConvert;
import java.io.IOException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import services.spi.ExchangeService;

/**
 * Created by Subin Sapkota on 3/18/17.
 */
@Service
public class ExchangeServiceImplementation implements ExchangeService {

  private final Logger logger = LoggerFactory.getLogger(ExchangeServiceImplementation.class);

  /**
   * Implementation of calculateCurrencyExchange.
   *
   * @param fromValue - double value of currency to be converted
   * @param fromCurr - String of currency to convert - Can be "USD", "EUR" or "JPY"
   * @param toCurr - String of currency to be converted to - Can be "USD", "EUR" or "JPY"
   * @return - ExchangeResult - if successful. null - if failed.
   */
  @Override
  public CurrencyConvert calculateCurrencyExchange(double fromValue, String fromCurr,
      String toCurr) {
    //Catch null values.
    if (fromCurr == null) {
      logger.error("INVALID! Null FROM currency specified.");
      return null;
    }
    if (toCurr == null) {
      logger.error("INVALID! Null TO currency specified.");
      return null;
    }
    // Generate ExchangeResult object to store results.
    CurrencyConvert currencyConvert = new CurrencyConvert();
    currencyConvert.setFromValue(fromValue);
    currencyConvert.setFromCurrency(fromCurr);
    currencyConvert.setToCurrency(toCurr);
    // Catch IOException if getting real time rate from yahoo quote API generates it.
    try {
      currencyConvert.setToValue(fromValue * getRate(fromCurr, toCurr));
    } catch (IOException e) {
      logger.error("Exception while getting rate from yahoo API: \n {}", e.getLocalizedMessage());
      return null;
    }
    logger.info("Successfully calculated exchange for currency.");
    return currencyConvert;
  }

  /**
   * Grab the real time exchange rate from yahoo quote API.
   *
   * @param fromCurr - from Currency
   * @param toCurr - to Currency
   * @return Rate from Yahoo quote
   * @throws IOException - catch IOException
   */
  private double getRate(String fromCurr, String toCurr) throws IOException {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpGet request = new HttpGet(
        "http://quote.yahoo.com/d/quotes.csv?s=" + fromCurr + toCurr + "=X&f=l1&e=.csv");
    ResponseHandler<String> responseHandler = new BasicResponseHandler();
    String responseBody = httpClient.execute(request, responseHandler);
    httpClient.close();
    return Double.parseDouble(responseBody);
  }
}

