package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ExchangeRates;
import models.dao.CurrencyDao;
import models.dao.ExchangeRatesDAO;
import models.dto.ExchangeRatesDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/exchangeRates/*")
public class ExchangeRateServlet extends ExtendedHttpServlet {
    private ObjectMapper objectMapper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var params = request.getParameterMap();
        String baseCode = params.get("base")[0];
        String targetCode = params.get("target")[0];
        String rate = params.get("rate")[0];
        System.out.println(baseCode + " : " + targetCode + " : " + rate);
        new ExchangeRatesDAO().addToDb(baseCode, targetCode, rate);

    }

    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String baseCode = pathInfo.substring(1, 4);
        String targetCode = pathInfo.substring(4, 7);
        String body = request.getReader().lines().collect(Collectors.joining());
        String rate = body.split("rate=")[1].split("&")[0];
        rate = URLDecoder.decode(rate, StandardCharsets.UTF_8);
        new ExchangeRatesDAO().updateDb(baseCode, targetCode, rate);
        System.out.println( "PATCH Success in servler");

    }


}
