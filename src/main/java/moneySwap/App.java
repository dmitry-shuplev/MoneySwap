package moneySwap;

import java.sql.*;
import models.Currensie;

public class App {
    public static void main(String[] args) {

        Currensie currency = new Currensie();
    currency.getByCode("RUB");
        System.out.println(currency);
    }
}

