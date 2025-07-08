package moneySwap;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;

import models.Currensie;

public class App {
        public static <Currencies> void main(String[] args) {
            Currensie currency = new Currensie("USD");
            System.out.println(currency.toString());
                  }

}

