package models.dto;

import lombok.Getter;
import lombok.Setter;
import models.Currensies;
import models.ExchangeRates;
import models.dao.CurrencyDao;
import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRatesDTO {
    int id;
    Currensies baseCurrensy;
    Currensies targetCurrensy;
    BigDecimal rate;

    public ExchangeRatesDTO(ExchangeRates exchangeRates) {
        id = exchangeRates.getId();
        baseCurrensy = new CurrencyDao().getById(exchangeRates.getBaseCurrency());
        targetCurrensy = new CurrencyDao().getById(exchangeRates.getTargetCurency());
        rate = exchangeRates.getRate();
    }

}
