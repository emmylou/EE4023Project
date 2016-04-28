/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statics;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmylou Flores 12132403
 */
@WebFilter("/RequestFilter")
public class RequestFilter implements Filter {
private static ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<HttpServletRequest>();

/**
 * Default constructor. 
 */
public RequestFilter() {
    // TODO Auto-generated constructor stub
}

/**
 * @see Filter#destroy()
 */
public void destroy() {
    // TODO Auto-generated method stub
}

public static HttpServletRequest getRequest() {
    System.out.println("Fetching the Request!!!");
    return localRequest.get();
}

public static HttpSession getSession() {
    System.out.println("Fetching the Session!!!");
    HttpServletRequest request = localRequest.get();
    return (request != null) ? request.getSession() : null;
}

/**
 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
 */
@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    // TODO Auto-generated method stub
    // place your code here

    // pass the request along the filter chain
    chain.doFilter(request, response);

    if (request instanceof HttpServletRequest) {
        localRequest.set((HttpServletRequest) request);
    }

    try {
        chain.doFilter(request, response);
    }
    finally {
        localRequest.remove();
    }
}


/**
 * @see Filter#init(FilterConfig)
 */


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    }
