package servlets;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Feedback;
import model.Sessions;
import csv.csvRead;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/statsServlet")
public class StatsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String courseCode = request.getParameter("courseCode");

        List<Sessions> sessions = csvRead.readSessions();
        boolean isCLosed = false;
        for (Sessions s : sessions) {
            if (s.getCourseCode().equals(courseCode) && !s.isStatus()) {
                isCLosed = true;
                break;
            }
        }

        if (!isCLosed) {
            response.getWriter().println("Session is open. You need to close the session for stats");
            return;
        }

        List<Feedback> feedbacks = csvRead.readFeedbacks();

        List<Feedback> filtered = feedbacks.stream()
                .filter(f -> f.getCourseCode().equals(courseCode))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            response.getWriter().println("No feedbacks for this course");
            return;
        }
        response.getWriter().println("Feedbacks for course: " + courseCode);
        response.getWriter().println("---------------------------------------");

        for (Feedback f : filtered) {
            response.getWriter().println(
                    "Grade: " + f.getGrade() + "\n" +
                            "Message: " + f.getFeedbackMessage() + "\n" +
                            "---------------------------------------"
            );
        }

        int total = filtered.size();
        double average = filtered.stream().mapToInt(Feedback::getGrade).average().orElse(0);

        Map<Integer, Long> countPerGrade = filtered.stream()
                .collect(Collectors.groupingBy(Feedback::getGrade, Collectors.counting()));





        response.getWriter().println("Total feedback-uri: " + total);
        response.getWriter().println("Media notelor: " + average);
        response.getWriter().println("Distributie pe note:");

        for (Map.Entry<Integer, Long> entry : countPerGrade.entrySet()) {
            response.getWriter().println("Nota " + entry.getKey() + ": " + entry.getValue() + " feedback-uri");
        }

    }


}
