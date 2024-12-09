package interfaces;

import dto.Grade;
import dto.User;

public interface IGrade {
    Grade addGradeToUser();
    void updateGrade(Grade grade);
}
