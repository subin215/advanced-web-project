package services;

import assets.CurrencyConvert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Subin Sapkota on 3/18/17.
 */
@RunWith(SpringRunner.class)
public class ExchangeServiceTest {

  private ExchangeServiceImplementation exchangeService;

  @Before
  public void setUp() {
    exchangeService = new ExchangeServiceImplementation();
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchange() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "USD", "EUR");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testEmptyFromCurrCalculateCurrencyExchange() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, null, "EUR");
    Assert.assertEquals(result, null);
  }

  @Test
  public void testEmptyToCurrCalculateCurrencyExchange() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "EUR", null);
    Assert.assertEquals(result, null);
  }

  @Test
  public void testMaxDoubleCalculateCurrencyExchange() {
    CurrencyConvert result = exchangeService
        .calculateCurrencyExchange(Double.MAX_VALUE, "EUR", "USD");
    Assert.assertTrue("Exchange calculation with MAX Double value successful", result != null);
  }

  @Test
  public void testMinDoubleCalculateCurrencyExchange() {
    CurrencyConvert result = exchangeService
        .calculateCurrencyExchange(-Double.MAX_VALUE, "EUR", "USD");
    Assert.assertTrue("Exchange calculation with MIN Double value successful", result != null);
  }

  @Test
  public void testZeroCalculateCurrencyExchange() {
    CurrencyConvert result = exchangeService
        .calculateCurrencyExchange(Double.MIN_VALUE, "EUR", "USD");
    Assert.assertEquals(result.getToValue(), Double.MIN_VALUE, 0.0);
  }
}

