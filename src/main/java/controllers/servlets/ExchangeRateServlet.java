package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ExchangeRates;
import models.dao.ExchangeRatesDAO;
import models.dto.ExchangeRatesDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/exchangeRates/*")
public class ExchangeRateServlet extends HttpServlet {
    private ObjectMapper objectMapper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<ExchangeRates> rates = new ExchangeRatesDAO().getAll();
            List<ExchangeRatesDTO> ratesDTO = new ArrayList<>();
            for (ExchangeRates rate : rates) {
                ratesDTO.add(new ExchangeRatesDTO(rate));
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if (ratesDTO.isEmpty()) {
                response.getWriter().write("[]");
            } else {
                objectMapper.writeValue(response.getWriter(), ratesDTO);
            }
        } else {
            String baseCode = pathInfo.substring(1, 4);
            String targetCode = pathInfo.substring(4, 7);
            System.out.println(baseCode + " : " + targetCode);
            ExchangeRatesDTO rateDTO = new ExchangeRatesDTO(new ExchangeRatesDAO().getExchangeRate(baseCode, targetCode));
            objectMapper.writeValue(response.getWriter(), rateDTO);
        }

    }


}
