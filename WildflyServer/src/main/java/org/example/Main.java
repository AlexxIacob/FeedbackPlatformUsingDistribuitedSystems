package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/wildfly")
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Gateway Servlet running!</h1>");
        resp.getWriter().println("<h2>Gateway Servlet running on Wildfly</h2>");
    }
}
