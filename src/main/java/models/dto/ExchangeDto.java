package models.dto;

import lombok.Getter;
import lombok.Setter;
import models.Currencies;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeDto {
    private Currencies baseCurrency;
    private Currencies targetCurrency;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;


}
