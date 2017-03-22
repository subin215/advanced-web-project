package services.spi;

import assets.CurrencyConvert;

/**
 * Created by Subin Sapkota on 3/18/17.
 */
public interface ExchangeService {

  /**
   * Calculate exchange value from provided value of currency.
   *
   * @param fromValue - double value of currency to be converted
   * @param fromCurr - String of currency to convert - Can be "USD", "EUR" or "JPY"
   * @param toCurr - String of currency to be converted to - Can be "USD", "EUR" or "JPY"
   * @return - ExchangeResult object with results.
   */
  CurrencyConvert calculateCurrencyExchange(double fromValue, String fromCurr, String toCurr);
}
