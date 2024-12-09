package dto;

import java.io.Serial;
import java.io.Serializable;

public class Grade implements Serializable {
    @Serial
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

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Grade { subject: '" + subject + "', mark: " + mark + " }";
    }
}
