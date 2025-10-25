package model;

public class Sessions {

    private String courseName;
    private String courseCode;
    private boolean status;

    public Sessions() {

    }

    public Sessions(String courseName, String courseCode, boolean status) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
