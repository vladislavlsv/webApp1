package myApp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")

public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);
        Cookie[] cookies = req.getCookies();
        boolean cookieExist = false;
            for(Cookie cookie : cookies)
            {
               if  (cookie.getName().equals("userName"))
                   cookieExist = true;
            }
        if ((uri.endsWith("index.jsp")) || (uri.endsWith("error.jsp")) || (uri.endsWith("LoginSuccess.jsp"))){
            cookieExist = true;
        }

        System.out.println("FILTER" + req.getRequestURL());

        if(!cookieExist){
            res.sendRedirect("error.jsp");
        }else{
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }
    public void destroy() {
        //close any resources here
    }

}