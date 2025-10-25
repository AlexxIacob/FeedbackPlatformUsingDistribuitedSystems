

import csv.csvRead;
import csv.csvWrite;
import model.Feedback;
import model.Sessions;

import java.util.List;

public class csvTest {

    public static void main(String[] args) {
        Sessions s1 = new Sessions("Java", "JAV123", true);
        Sessions s2 = new Sessions("Baze de date", "DB456", false);
        csvWrite.writeSession(s1);
        csvWrite.writeSession(s2);
        System.out.println("Sessions written successfully");

        Feedback f1 = new Feedback("JAV123", "Curs foarte bun!", 5);
        Feedback f2 = new Feedback("DB456", "Cam greu de urmarit, dar util.", 3);
        csvWrite.writeFeedback(f1);
        csvWrite.writeFeedback(f2);
        System.out.println("Feedbacks written successfully");

        List<Sessions> sessions = csvRead.readSessions();
        System.out.println("\nSessions read successfully");
        for (Sessions s : sessions) {
            System.out.println("Curs: " + s.getCourseName() + " | Cod: " + s.getCourseCode() + " | Deschis: " + s.isStatus());
        }

        List<Feedback> feedbacks = csvRead.readFeedbacks();
        System.out.println("\nFeedbacks read successfully");
        for (Feedback f : feedbacks) {
            System.out.println("Cod curs: " + f.getCourseCode() + " | Nota: " + f.getGrade() + " | Mesaj: " + f.getFeedbackMessage());
        }
    }
}
