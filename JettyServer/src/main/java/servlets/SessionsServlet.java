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

@WebServlet("SessionsServlet")
public class SessionsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseName = req.getParameter("courseName");

        String courseCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        Sessions sessions = new Sessions(courseName, courseCode, true);
        csvWrite.writeSession(sessions);

        resp.setContentType("text/plain");
        resp.getWriter().println("Sessions saved successfully");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String courseCode = req.getParameter("courseCode");

        List<Sessions> sessions = csvRead.readSessions();
        boolean found = false;

        for (Sessions s : sessions) {
            if (s.getCourseCode().equals(courseCode)) {
                s.setStatus(false);
                found = true;
                break;
            }
        }

        if (found){
            csvWrite.rewriteSessions(sessions);
            resp.getWriter().println("Status changed successfully");
        }
        else {
            resp.getWriter().println("Code invalid");
        }

    }


}
