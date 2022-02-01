package fr.ut1.espacemembre.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "NotConnectedFilter", urlPatterns = "/user/*")
public class NotConnectedFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (((HttpServletRequest) request).getSession().getAttribute("auth") == null) {
            ((HttpServletResponse) response).sendRedirect("/");
        } else {
            chain.doFilter(request, response);
        }
    }
}
