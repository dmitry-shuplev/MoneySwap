package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRates {
 private int id;
 private int baseCurrency;
 private int targetCurency;
 private BigDecimal rate;

 public ExchangeRates(){
 }

 @Override
 public String toString() {
  return "ExchangeRates{" +
          "id : " + id +
          ", baseCurrency : " + baseCurrency +
          ", targetCurency : " + targetCurency +
          ", rate : " + rate +
          '}';
 }
}
