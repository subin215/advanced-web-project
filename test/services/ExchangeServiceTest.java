package services;

import assets.CurrencyConvert;
import conf.TestDataConfig;
import configs.AppConfig;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Subin Sapkota on 3/18/17.
 */
@ContextConfiguration(classes = {AppConfig.class, TestDataConfig.class})
public class ExchangeServiceTest {

  @Inject
  private ExchangeServiceImplementation exchangeService;

  @Before
  public void setUp() {
    exchangeService = new ExchangeServiceImplementation();
  }

  /* Successful exchange */
  @Test
  public void testSuccessfulCalculateCurrencyExchangeUSDEUR() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "USD", "EUR");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeUSDJPY() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "USD", "JPY");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeUSDNPR() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "USD", "NPR");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeEURUSD() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "EUR", "USD");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeEURJPY() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "EUR", "JPY");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeEURNPR() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "EUR", "NPR");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeJPYUSD() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "JPY", "USD");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeJPYEUR() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "JPY", "EUR");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeNRPUSD(){
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "NPR", "USD");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeNRPEUR(){
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "NPR", "EUR");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeNRPJPY(){
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "NPR", "JPY");
    Assert.assertTrue("Exchange calculation successful, result {}", result != null);
  }

  @Test
  public void testSuccessfulCalculateCurrencyExchangeJPYNPR() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, "JPY", "NPR");
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
  public void testEmptyParamCurrCalculateCurrencyExchange() {
    CurrencyConvert result = exchangeService.calculateCurrencyExchange(1.0, null, null);
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

