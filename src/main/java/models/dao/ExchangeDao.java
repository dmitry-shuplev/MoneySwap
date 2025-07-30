package models.dao;

import models.dto.ExchangeDto;
import models.ExchangeRates;

import java.math.BigDecimal;

public class ExchangeDao {
    private String base;
    private String target;
    private String amount;
    ExchangeDto exchange;
    ExchangeRates exchangeRate;
    ExchangeRatesDao exchangeRatesDao;


    public ExchangeDao(String base, String target, String amount) {
        exchange = new ExchangeDto();
        exchangeRate = new ExchangeRates();
        exchangeRatesDao = new ExchangeRatesDao();
        this.base = base;
        this.target = target;
        this.amount = amount;
    }

    public ExchangeDto execute() {
        int i = 10;
        if (directExchange()) return exchange;
        if (reverceExchange()) return exchange;
        if (throughUSDExchange()) return exchange;
        return null;
    }

    private boolean directExchange() {
        exchangeRate = exchangeRatesDao.getExchangeRate(base, target);
        if (exchangeRate.getRate() != null) {
            setExchangeParametrs(base, target, exchangeRate.getRate());
            return true;
        }
        return false;
    }

    private boolean reverceExchange(){
        exchangeRate = exchangeRatesDao.getExchangeRate(target, base);
        if (exchangeRate.getRate() != null) {
            BigDecimal reverseRate = BigDecimal.ONE.divide(exchangeRate.getRate(), 4, BigDecimal.ROUND_HALF_UP);
            setExchangeParametrs(base, target, reverseRate);
            return true;
        }
        return false;
    }

    private boolean throughUSDExchange() {
        BigDecimal targetToUsdRate = exchangeRatesDao.getExchangeRate(target, "USD").getRate();
        BigDecimal baseToUsdRate = exchangeRatesDao.getExchangeRate(base, "USD").getRate();
        if (targetToUsdRate != null && baseToUsdRate != null) {
            BigDecimal rate = baseToUsdRate.divide(targetToUsdRate, 4, BigDecimal.ROUND_HALF_UP);
            setExchangeParametrs(base, target, rate);
            return true;
        }
        return false;
    }

    private void setExchangeParametrs(String from, String to, BigDecimal rate) {
        exchange.setBaseCurrency(new CurrencyDao().getByCode(from));
        exchange.setTargetCurrency(new CurrencyDao().getByCode(to));
        exchange.setAmount(new BigDecimal(amount));
        exchange.setRate(rate);
        exchange.setConvertedAmount(exchange.getAmount().multiply(exchange.getRate()));
    }

}
