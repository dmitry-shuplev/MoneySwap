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
import java.util.LinkedList;
import java.util.List;

@WebServlet("/exchangeRates/*")
public class ExchangeRateServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<ExchangeRates> rates = new ExchangeRatesDAO().getAll();
            List<ExchangeRatesDTO> ratesDTO = new LinkedList<>();
            for (ExchangeRates rate : rates) {
                ratesDTO.add(new ExchangeRatesDTO(rate));
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            try {
                System.out.println("Rates size: " + ratesDTO.size());
                new ObjectMapper().writeValue(response.getWriter(), ratesDTO);
            } catch (IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating JSON response");
                e.printStackTrace();
            }
        }

    }


}
