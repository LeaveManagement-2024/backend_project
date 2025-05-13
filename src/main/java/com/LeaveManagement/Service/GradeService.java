package com.LeaveManagement.Service;

import com.LeaveManagement.Entity.Grades;
import com.LeaveManagement.Entity.Profiles;

import java.util.List;

public interface GradeService {
    Long addGrade(Grades grade);

    public List<Grades> getAllGrades();
    public Grades GetGradesById(Long  id);
    void updateGrades(Long id, Grades grade);
    void deleteGrades(Long id);


}
