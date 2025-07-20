package controllers.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currensies;
import models.ExchangeRates;
import models.dao.CurrencyDao;
import models.dao.ExchangerRatesDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/exchangeRates/*")
public class ExchangeRateServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>MoneySwap</title></head>");
            out.println("<body>");
            out.println("<h1>Welcome to MoneySwap!</h1>");
            String pathInfo = request.getPathInfo();
            ExchangerRatesDAO cd = new ExchangerRatesDAO();
            if (pathInfo == null || pathInfo.equals("/")) {
                List<ExchangeRates> rates = new LinkedList<>(cd.getAll());
                for (ExchangeRates rate : rates) {
                    out.println("<p>Currency: " + rate.toString() + "</p>");
                }
            } else {
                //stub
                String parm = pathInfo.substring(1);
//                Currensies currency = new Currensies();
//                currency = cd.getByCode(parm);
//                out.println("<p>Currency: " + currency.toString() + "</p>");
            }
            out.println("<p>Это результат запроса к базе данных</p>");
            out.println("</body></html>");
        }

    }


}
