package com.mm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Radian6 Proxy</h1>");
        response.getWriter().println("<br>session=" + request.getSession(true).getId());
		response.getWriter().println("<br>requestURI=" + request.getRequestURI());
		response.getWriter().println("<br>queryString=" + request.getQueryString());
    }
}