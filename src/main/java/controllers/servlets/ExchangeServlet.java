package controllers.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config)throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
       String from = request.getParameter("from");
       String to = request.getParameter("to");
       String amount = request.getParameter("amount");
       ExchangeDAO exchange = new ExchangeDao;
    }
}
