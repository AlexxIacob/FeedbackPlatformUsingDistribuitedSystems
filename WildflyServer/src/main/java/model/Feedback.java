package model;

public class Feedback {

    private String courseCode;
    private String feedbackMessage;
    private int grade;

    public Feedback() {

    }

    public Feedback(String courseCode, String feedbackMessage, int grade) {
        this.courseCode = courseCode;
        this.feedbackMessage = feedbackMessage;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
