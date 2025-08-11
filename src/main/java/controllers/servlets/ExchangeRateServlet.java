package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dao.ExchangeRatesDao;
import models.dto.ExchangeRatesDto;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import java.util.stream.Collectors;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends ExtendedServlet {
    private ObjectMapper objectMapper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String baseCode = pathInfo.substring(1, 4);
        String targetCode = pathInfo.substring(4, 7);
        ExchangeRatesDto rateDTO = new ExchangeRatesDto(new ExchangeRatesDao().getExchangeRate(baseCode, targetCode));
        objectMapper.writeValue(response.getWriter(), rateDTO);

    }


    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String baseCode = pathInfo.substring(1, 4);
        String targetCode = pathInfo.substring(4, 7);
        String body = request.getReader().lines().collect(Collectors.joining());
        String rate = body.split("rate=")[1].split("&")[0];
        rate = URLDecoder.decode(rate, StandardCharsets.UTF_8);
        new ExchangeRatesDao().updateDb(baseCode, targetCode, rate);
        System.out.println("PATCH Success in servler");

    }

}
