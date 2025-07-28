package models.dao;

import models.Currencies;
import models.Exchange;
import models.ExchangeRates;

import java.math.BigDecimal;

public class ExchangeDao {
    private String base;
    private String target;
    private String amount;
    Exchange exchange;
    ExchangeRates exchangeRate;
    ExchangeRatesDao exchangeRatesDao;


    public ExchangeDao(String base, String target, String amount) {
        exchange = new Exchange();
        exchangeRate = new ExchangeRates();
        exchangeRatesDao = new ExchangeRatesDao();
        this.base = base;
        this.target = target;
        this.amount = amount;
    }

    public Exchange execute() {
        int i = 10;
        if (directExhange()) return exchange;
        if (reverseExchange()) return exchange;
        if (throughUSDExchange()) return exchange;

        return null;
    }

    private boolean directExhange() {
        exchangeRate = exchangeRatesDao.getExchangeRate(base, target);
        if (exchangeRate.getRate() != null){
            exchange.setBaseCurrency(new CurrencyDao().getByCode(base));
            exchange.setTargetCurrency(new CurrencyDao().getByCode(target));
            exchange.setRate(exchangeRate.getRate());
            exchange.setAmount(new BigDecimal(amount));
            exchange.setConvertedAmount(exchange.getAmount().multiply(exchange.getRate()));
            return true;
        }
            return false;
    }

    private boolean reverseExchange() {
        exchangeRate = exchangeRatesDao.getExchangeRate(target, base);
        if (exchangeRate.getRate() != null){
            exchange.setBaseCurrency(new CurrencyDao().getByCode(target));
            exchange.setTargetCurrency(new CurrencyDao().getByCode(base));
            exchange.setRate(new BigDecimal(1).divide(exchangeRate.getRate()));
            exchange.setAmount(new BigDecimal(amount));
            exchange.setConvertedAmount(exchange.getAmount().multiply(exchange.getRate()));
            return true;
        }
        return false;
    }

    private boolean throughUSDExchange() {
        exchange.setBaseCurrency(new CurrencyDao().getByCode(target));
        exchange.setTargetCurrency(new CurrencyDao().getByCode(base));
        BigDecimal baseToUsdRate = exchangeRatesDao.getExchangeRate(base, "USD").getRate();
        if (baseToUsdRate == null) return false;
        BigDecimal targetToUsdRate = exchangeRatesDao.getExchangeRate(target, "USD").getRate();
        if (targetToUsdRate == null) return false;
        exchange.setRate(baseToUsdRate.multiply(targetToUsdRate));
        exchange.setAmount(new BigDecimal(amount));
        exchange.setConvertedAmount(exchange.getAmount().multiply(exchange.getRate()));
        return true;
    }

}
