package moneySwap;

import models.Currensie;
import models.dao.CurrencyDao;

public class App {
    public static void main(String[] args) {

        CurrencyDao currensyDao = new CurrencyDao();
        Currensie currency = new Currensie();
        currency = currensyDao.getByCode("RUB");
        System.out.println(currency.toString());
    }
}

