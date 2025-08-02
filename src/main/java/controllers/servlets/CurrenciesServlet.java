package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currencies;
import models.dao.CurrencyDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/currencies/*")
public class CurrenciesServlet extends HttpServlet {
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Currencies> currenciesList = new ArrayList<>(new CurrencyDao().getAll());
            if (currenciesList.isEmpty()) {
                response.getWriter().write("[]");
            } else {
                objectMapper.writeValue(response.getWriter(), currenciesList);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var params = request.getParameterMap();
        Currencies currnesy = new Currencies();
        currnesy.setFullName(params.get("name")[0]);
        currnesy.setCode(params.get("code")[0]);
        currnesy.setSign(params.get("sign")[0]);
        CurrencyDao cd = new CurrencyDao();
        cd.addToDb(currnesy);
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}





