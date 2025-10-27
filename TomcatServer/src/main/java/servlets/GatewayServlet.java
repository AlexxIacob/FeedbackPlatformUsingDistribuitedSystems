package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GatewayServlet extends HttpServlet {

    private static final String JETTY_URL = "http://localhost:8081/JettyServletApp-1.0-SNAPSHOT";
    private static final String WILDFLY_URL = "http://127.0.0.1:8082/WildflyServer-1.0-SNAPSHOT";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("createSession".equals(action)) {
            String courseName = req.getParameter("courseName");
            sendPost(JETTY_URL + "/SessionsServlet", "action=createSession&courseName=" + courseName, resp);

        } else if ("addFeedback".equals(action)) {
            String courseCode = req.getParameter("courseCode");
            String message = req.getParameter("message");
            String grade = req.getParameter("grade");
            sendPost(JETTY_URL + "/FeedbackServlet",
                    "action=addFeedback&courseCode=" + courseCode + "&message=" + message + "&grade=" + grade,
                    resp);

        } else if ("stopSession".equals(action)) {
            String courseCode = req.getParameter("courseCode");
            sendPost(JETTY_URL + "/SessionsServlet",
                    "action=stopSession&courseCode=" + courseCode,
                    resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("viewStats".equals(action)) {
            String courseCode = req.getParameter("courseCode");
            sendGet(WILDFLY_URL + "/statsServlet?courseCode=" + courseCode, resp);
        }
    }

    private void sendPost(String urlString, String urlParams, HttpServletResponse resp)
            throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.getOutputStream().write(urlParams.getBytes());
        conn.getOutputStream().flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder fullResponse = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            fullResponse.append(line).append("\n");
        }
        in.close();

        System.out.println("🔹 Response from " + urlString + ":\n" + fullResponse);

        resp.getWriter().print(fullResponse.toString().trim());
    }


    private void sendGet(String urlString, HttpServletResponse resp) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            resp.getWriter().println(line);
        }
        in.close();
    }
}