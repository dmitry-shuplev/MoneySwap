package servlets;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Currensie;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/currencies/*")
public class CurrenciesServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo==null||pathInfo.equals("/")){
//            getJsonCurrensiesList
            System.out.println(pathInfo+"if");

        }
            else{
int i = 10;
    Currensie currensy = new Currensie("UAH");
            System.out.println(pathInfo+"else");
        }
        response.setContentType("application/json");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
