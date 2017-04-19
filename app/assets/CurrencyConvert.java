package assets;

import play.data.validation.Constraints;

/**
 * Created by Subin Sapkota on 3/18/17.
 */
public class CurrencyConvert {

  @Constraints.Required
  private String fromCurrency;
  @Constraints.Required
  private String toCurrency;
  @Constraints.Required
  private double fromValue;
  private double toValue;

  public String getFromCurrency() {
    return fromCurrency;
  }

  public void setFromCurrency(String fromCurrency) {
    this.fromCurrency = fromCurrency;
  }

  public String getToCurrency() {
    return toCurrency;
  }

  public void setToCurrency(String toCurrency) {
    this.toCurrency = toCurrency;
  }

  public double getFromValue() {
    return fromValue;
  }

  public void setFromValue(double fromValue) {
    this.fromValue = fromValue;
  }

  public double getToValue() {
    return toValue;
  }

  public void setToValue(double toValue) {
    this.toValue = toValue;
  }

  @Override
  public String toString() {
    return "CurrencyConvert{" +
        "fromCurrency='" + fromCurrency + '\'' +
        ", toCurrency='" + toCurrency + '\'' +
        ", fromValue=" + fromValue +
        ", toValue=" + toValue +
        '}';
  }
}
