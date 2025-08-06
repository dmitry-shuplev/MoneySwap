package services;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class SettingResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
