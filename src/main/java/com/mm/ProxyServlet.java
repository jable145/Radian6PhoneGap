package com.mm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

/**
 * This class accepts a client request and then forwards the request to an appropriate Radian6 service.
 * An authentication token and an application key must have been previously acquired via an authentication
 * request, and these values are stored in the session. These values are added as headers to the Radian6
 * request. Incoming client requests do not need to supply these values.
 */
public class ProxyServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/xml");
        response.setStatus(HttpServletResponse.SC_OK);
		try {
			System.out.println("Proxy Request: " + request.getRequestURI());
			if (getAuthToken(request) == null) {
				// Looks like we're not authenticated. Perhaps a timeout.
				// We could set a URL in the session to indicate where we want to return,
				// but for now we just use whatever default authentication uses.
				System.out.print("Proxy: Not authenticated. ");
				System.out.println("Session id: " + request.getSession().getId());
				response.sendRedirect("index.html");
			} else {
				doStuff(response.getWriter(), request);
			}
		} catch (Exception ex) {
			System.out.println("Proxy: Exception calling Radian6: " + ex.toString());
			response.getWriter().println("Exception calling Radian6: " + ex.toString());
		}
    }
	
	protected void doStuff(PrintWriter writer, HttpServletRequest request) throws Exception {
        HttpClient httpclient = new HttpClient();
        // Construct Request URL for radian6
		StringBuffer url = new StringBuffer();
		url.append(Authenticate.BASE_SERVICE_URL);
		url.append(request.getRequestURI());
		if (request.getQueryString() != null) {
			url.append("?");
			url.append(request.getQueryString());
		}
		System.out.println("ProxyServlet: url sent to Radian6: " + url.toString());
        GetMethod httpget = new GetMethod(url.toString());
        httpget.addRequestHeader("Accept" , "*/xml");
        httpget.addRequestHeader("auth_token", getAuthToken(request));
        httpget.addRequestHeader("auth_appkey", getAuthAppkey(request));
        httpget.addRequestHeader("Content-Type", "text/*");
        try {
            httpclient.executeMethod(httpget);
            writer.println(httpget.getResponseBodyAsString());
        } finally {
            httpget.releaseConnection();
        }
		
	}

	
	protected String getAuthToken(HttpServletRequest request) {
		String result = null;
		HttpSession session = request.getSession(true);
		Object o = session.getAttribute("auth_token");
		if (o != null) {
			result = o.toString();
		}
		return result;
	}
	
	protected String getAuthAppkey(HttpServletRequest request) {
		String result = null;
		HttpSession session = request.getSession(true);
		Object o = session.getAttribute("auth_appkey");
		if (o != null) {
			result = o.toString();
		}
		return result;
	}
	
	



}