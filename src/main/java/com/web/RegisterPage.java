package com.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/register")
public class RegisterPage extends HttpServlet {

    private Repository repository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("repository", (Repository) req.getSession().getAttribute("repository"));
        repository = (Repository) req.getAttribute("repository");
        if (!repository.checkAuthorisation((String)req.getParameter("login"),(String) req.getParameter("password"))) {
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            repository.register((String)req.getParameter("login"), (String)req.getParameter("password"));
            pw.println("<h1>Success!</h1>");
            pw.println("<div> <a href=\"/welcome\"> Go to login page </a> </div>");
        }
        else{
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.println("<h1>Invlid parameters</h1>");
            pw.println("<div> <a href=\"/welcome\"> Go to login page </a> </div>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(" <div>  <form action=\"/register\" method=\"POST\">\n" +
                "      <input type=\"text\" id=\"login\" class=\"second\" name=\"login\" placeholder=\"login\">\n" +
                "      <input type=\"text\" id=\"password\" class=\"third\" name=\"password\" placeholder=\"password\">\n" +
                "      <input type=\"submit\" class=\"fourth\" value=\"Register\">\n" +
                "    </form> </div> <div> <a href=\"/welcome\"> Main Page </a> </div>");
    }
}
