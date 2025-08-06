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
import services.Validator;
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
            try {
                List<Currencies> currenciesList = new ArrayList<>(new CurrencyDao().getAll());
                response.setStatus(HttpServletResponse.SC_OK);
                objectMapper.writeValue(response.getWriter(), currenciesList);
            } catch (Exception e) {
             response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var params = request.getParameterMap();
        Currencies currnesy = new Currencies();
        currnesy.setFullName(params.get("name")[0]);
        String code = params.get("code")[0];
        if (new Validator().isCodeValid(code)) {
            currnesy.setCode(code);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        currnesy.setSign(params.get("sign")[0]);
        CurrencyDao cd = new CurrencyDao();
        cd.addToDb(currnesy);
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}





