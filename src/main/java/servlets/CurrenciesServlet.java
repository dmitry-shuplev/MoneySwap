package servlets;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currensie;
import models.dao.CurrencyDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/currencies/*")
public class CurrenciesServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
//            getJsonCurrensiesList
            System.out.println(pathInfo + "if");
        } else {

//            try {
//                Currensie currensy = new Currensie("UAH");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println(pathInfo + "else");
        }
        response.setContentType("text/html;charset=UTF-8");
        CurrencyDao currensyDao = new CurrencyDao();
        Currensie currency = new Currensie();
        currency = currensyDao.getByCode("USD");

        System.out.println(currency.toString());
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>MoneySwap</title></head>");
            out.println("<body>");
            out.println("<h1>Welcome to MoneySwap!</h1>");
            out.println("<p>Currency: " + currency.toString() + "</p>");
            out.println("<p>Это результат запроса к базе данных</p>");

            out.println("</body></html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
