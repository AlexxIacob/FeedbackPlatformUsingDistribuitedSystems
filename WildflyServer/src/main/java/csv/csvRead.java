package csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import model.Feedback;
import model.Sessions;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvRead {

    private static final String FEEDBACK_FILE = "C:\\Projects\\FeedbackPlatform\\data\\feedback.csv";
    private static final String SESSIONS_FILE = "C:\\Projects\\FeedbackPlatform\\data\\sessions.csv";

    public static List<Feedback> readFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(FEEDBACK_FILE))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 3) {
                    feedbacks.add(new Feedback(
                            nextLine[0],
                            nextLine[1],
                            Integer.parseInt(nextLine[2])
                    ));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return feedbacks;
    }

    public static List<Sessions> readSessions() {
        List<Sessions> sessions = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(SESSIONS_FILE))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 3) {
                    sessions.add(new Sessions(
                            nextLine[0],
                            nextLine[1],
                            Boolean.parseBoolean(nextLine[2])
                    ));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return sessions;
    }
}
