package moneySwap;

import models.Currensies;
import models.dao.CurrencyDao;

public class App {
    public static void main(String[] args) {

        CurrencyDao currensyDao = new CurrencyDao();
        Currensies currency = new Currensies();
        currency = currensyDao.getByCode("RUB");
        System.out.println(currency.toString());
    }
}

