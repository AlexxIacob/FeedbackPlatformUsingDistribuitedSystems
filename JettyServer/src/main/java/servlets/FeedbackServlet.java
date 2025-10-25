package servlets;

import csv.csvRead;
import csv.csvWrite;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Feedback;
import model.Sessions;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

import java.util.List;

@WebServlet("FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String courseCode = request.getParameter("courseCode");

            String message = request.getParameter("message");

            int grade = Integer.parseInt(request.getParameter("grade"));

            if (grade < 1 || grade > 10)
            {
                response.getWriter().println("Number of grade must be between 1 and 10");
                return;
            }


            List<Sessions> sessions = csvRead.readSessions();
            boolean isSessionOpen = false;

            for (Sessions s : sessions)
            {
                    if(s.getCourseCode().equals(courseCode) && s.isStatus())
                    {
                        isSessionOpen = true;
                        break;
                    }
            }

            if (isSessionOpen)
            {
                Feedback feedback = new Feedback(courseCode, message, grade);
                csvWrite.writeFeedback(feedback);
                response.getWriter().println("Feedback successfully written");
            }
            else
                response.getWriter().println("Feedback failed");
    }

}
