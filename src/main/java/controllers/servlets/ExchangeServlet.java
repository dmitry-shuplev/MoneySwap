package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dto.ExchangeDto;
import models.dao.ExchangeDao;

import java.io.IOException;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {
public ObjectMapper objectMapper;
    @Override
    public void init(ServletConfig config)throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String baseCode = request.getParameter("base");
       String targetCode = request.getParameter("target");
       String amount = request.getParameter("amount");
       ExchangeDao exchangeDao = new ExchangeDao(baseCode, targetCode, amount);
       ExchangeDto exchange = exchangeDao.execute();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
       objectMapper.writeValue(response.getWriter(), exchange);
    }
}
