package dto;

import java.io.Serializable;

public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;
    private String subject;
    private double mark;

    public Grade(String subject, double mark) {
        this.subject = subject;
        this.mark = mark;
    }

    public String getSubject() {
        return subject;
    }

    public double getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "Grade { subject: '" + subject + "', mark: " + mark + " }";
    }
}
