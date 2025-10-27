package servlets;

import csv.csvRead;
import csv.csvWrite;
import jakarta.servlet.annotation.WebServlet;
import model.Sessions;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

@WebServlet("/SessionsServlet")
public class SessionsServlet extends HttpServlet {

    @Override
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        resp.setContentType("text/plain");

        if ("createSession".equals(action)) {
            String courseName = req.getParameter("courseName");
            String courseCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

            Sessions session = new Sessions(courseName, courseCode, true);
            csvWrite.writeSession(session);

            resp.getWriter().println("Sessions saved successfully");
            resp.getWriter().println("Course code: " + courseCode);

        } else if ("stopSession".equals(action)) {
            String courseCode = req.getParameter("courseCode");

            synchronized (this) {
                List<Sessions> sessions = csvRead.readSessions();
                boolean found = false;

                for (Sessions s : sessions) {
                    if (s.getCourseCode().equalsIgnoreCase(courseCode) && s.isStatus()) {
                        s.setStatus(false);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    csvWrite.rewriteSessions(sessions);
                    resp.getWriter().println("Session stopped successfully.");
                } else {
                    resp.getWriter().println("Invalid course code or session already closed.");
                }
            }
        } else {
            resp.getWriter().println("Invalid action.");
        }
    }
}