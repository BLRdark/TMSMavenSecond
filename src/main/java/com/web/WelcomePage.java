package com.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/welcome")
public class WelcomePage extends HttpServlet {
    private Repository repository;

    @Override
    public void init() throws ServletException {
        repository = new Repository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (repository.checkAuthorisation((String)req.getParameter("login"),(String) req.getParameter("password"))) {
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.println("<h1>Login successfull</h1>");
            pw.println("<a href=\"/welcome\">Main Page <a>");
        }
        else{
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.println("<h1>Invlid parameters</h1>");
            resp.sendRedirect("/welcome");
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("repository", repository);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(" <div>  <form action=\"/welcome\" method=\"POST\">\n" +
                "      <input type=\"text\" id=\"login\" class=\"second\" name=\"login\" placeholder=\"login\">\n" +
                "      <input type=\"text\" id=\"password\" class=\"third\" name=\"password\" placeholder=\"password\">\n" +
                "      <input type=\"submit\" class=\"fourth\" value=\"Log In\">\n" +
                "    </form> </div> <div> <a href=\"/register\"> Register </a> </div>");
    }
}
