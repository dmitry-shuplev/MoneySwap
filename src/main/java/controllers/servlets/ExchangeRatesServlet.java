package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ExchangeRates;
import models.dao.ExchangeRatesDao;
import models.dto.ExchangeRatesDto;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/exchangeRates/*")
public class ExchangeRatesServlet extends ExtendedServlet {
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
            String baseCode = pathInfo.substring(1, 4);
            String targetCode = pathInfo.substring(4, 7);
            ExchangeRatesDto rateDTO = new ExchangeRatesDto(new ExchangeRatesDao().getExchangeRate(baseCode, targetCode));
            objectMapper.writeValue(response.getWriter(), rateDTO);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var params = request.getParameterMap();
        String baseCode = params.get("base")[0];
        String targetCode = params.get("target")[0];
        String rate = params.get("rate")[0];
        System.out.println(baseCode + " : " + targetCode + " : " + rate);
        //stub
        //new ExchangeRatesDao().addToDb(baseCode, targetCode, rate);

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
        System.out.println( "PATCH Success in servler");

    }


}
