package models.dto;

import lombok.Getter;
import lombok.Setter;
import models.Currencies;
import models.ExchangeRates;
import models.dao.CurrencyDao;
import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRatesDto {
    int id;
    Currencies baseCurrensy;
    Currencies targetCurrensy;
    BigDecimal rate;

    public ExchangeRatesDto(ExchangeRates exchangeRates) {
        id = exchangeRates.getId();
        baseCurrensy = new CurrencyDao().getById(exchangeRates.getBaseCurrency());
        targetCurrensy = new CurrencyDao().getById(exchangeRates.getTargetCurency());
        rate = exchangeRates.getRate();
    }

}
