package moneySwap;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;

import models.Currensie;

public class App {
        public static void main(String[] args) {
            try {
                InetAddress inetAddress = InetAddress.getByName("google.com");
                var socket = new Socket(inetAddress, 80); // Используем var для определения типа сокета
                System.out.println("Socket created: " + socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}

