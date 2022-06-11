package edu.school21.cinema.filters;

import edu.school21.cinema.models.Administrator;
import edu.school21.cinema.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/panel/*")
public class AdministratorOnlyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Administrator administrator = AdministratorService.getFromSession(((HttpServletRequest) servletRequest).getSession());
        if (administrator == null) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
