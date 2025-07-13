package controllers.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currensies;
import models.dao.CurrencyDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/currencies/*")
public class CurrensyServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>MoneySwap</title></head>");
            out.println("<body>");
            out.println("<h1>Welcome to MoneySwap!</h1>");
            String pathInfo = request.getPathInfo();
            CurrencyDao cd = new CurrencyDao();
            if (pathInfo == null || pathInfo.equals("/")) {
                ArrayList<Currensies> currensies = new ArrayList<>(cd.getAll());
                for (Currensies currency : currensies) {
                    out.println("<p>Currency: " + currency.toString() + "</p>");
                }
            }
            else {
                String parm = pathInfo.substring(1);
                Currensies currency = new Currensies();
                currency = cd.getByCode(parm);
                out.println("<p>Currency: " + currency.toString() + "</p>");
            }
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
