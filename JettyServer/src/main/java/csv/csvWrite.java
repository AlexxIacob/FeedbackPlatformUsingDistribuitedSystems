package csv;

import com.opencsv.CSVWriter;
import model.Feedback;
import model.Sessions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class csvWrite {

    private static final String FEEDBACK_FILE = "C:\\Projects\\FeedbackPlatform\\data\\feedback.csv";
    private static final String SESSIONS_FILE = "C:\\Projects\\FeedbackPlatform\\data\\sessions.csv";

    public static void writeFeedback(Feedback feedback) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FEEDBACK_FILE, true))) {
            String[] record = {
                    feedback.getCourseCode(),
                    feedback.getFeedbackMessage(),
                    String.valueOf(feedback.getGrade())
            };
            writer.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeSession(Sessions session) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(SESSIONS_FILE, true))) {
            String[] record = {
                    session.getCourseName(),
                    session.getCourseCode(),
                    String.valueOf(session.isStatus())
            };
            writer.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void rewriteSessions(List<Sessions> sessions) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(SESSIONS_FILE))) {
            for (Sessions s : sessions) {
                String[] record = {
                        s.getCourseName(),
                        s.getCourseCode(),
                        String.valueOf(s.isStatus())
                };
                writer.writeNext(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
