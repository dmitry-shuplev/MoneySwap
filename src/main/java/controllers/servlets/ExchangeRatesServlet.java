package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ExchangeRates;
import models.dao.ExchangeRatesDao;
import models.dto.ExchangeRatesDto;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/exchangeRates/*")
public class ExchangeRatesServlet extends HttpServlet {
    private ObjectMapper objectMapper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<ExchangeRates> rates = new ExchangeRatesDao().getAll();
            List<ExchangeRatesDto> ratesDTO = new ArrayList<>();
            for (ExchangeRates rate : rates) {
                ratesDTO.add(new ExchangeRatesDto(rate));
            }
            if (ratesDTO.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("[]");
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
                objectMapper.writeValue(response.getWriter(), ratesDTO);
            }
        } else {
            //stub
            //Ошибка неверные URL
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var params = request.getParameterMap();
        String baseCode = params.get("baseCurrencyCode")[0];
        String targetCode = params.get("targetCurrencyCode")[0];
        String rate = params.get("rate")[0];
        System.out.println(baseCode + " : " + targetCode + " : " + rate);
        try {
            new ExchangeRatesDao().addToDb(baseCode, targetCode, rate);
            ExchangeRatesDto rateDTO = new ExchangeRatesDto(new ExchangeRatesDao().getExchangeRate(baseCode, targetCode));
            objectMapper.writeValue(response.getWriter(), rateDTO);
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (SQLException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

    }
}
