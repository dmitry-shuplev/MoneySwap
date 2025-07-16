package controllers.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/test.html";
        int i = 10;
        if (request.getServletContext().getResource(page) != null) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
        System.out.println(entry.getKey()+":"+ Arrays.toString(entry.getValue()));}

    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
