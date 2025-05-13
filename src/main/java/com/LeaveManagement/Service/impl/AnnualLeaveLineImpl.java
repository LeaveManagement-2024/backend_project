package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Dto.AnnualLeaveLineDTO;
import com.LeaveManagement.Entity.*;
import com.LeaveManagement.Repo.AnnualLeaveLineRepo;
import com.LeaveManagement.Repo.AnnualLeaveRepo;
import com.LeaveManagement.Repo.EmployeeRepo;
import com.LeaveManagement.Service.AnnualLeaveLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
public class AnnualLeaveLineImpl implements AnnualLeaveLineService {
    @Autowired
    private AnnualLeaveLineRepo annualLeaveLineRepo;
    @Autowired
    private StatistiqueImp statistiqueService;
    @Autowired
    private EmployeeRepo  employeeRepo;
    @Autowired
    private AnnualLeaveRepo annualLeaveRepo;


    @Override
    public void addAnnualLeaveLine(AnnualLeaveLineDTO annualLeaveLineDTO) {
        Employees employee = employeeRepo.findById(annualLeaveLineDTO.getIdE()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        AnnualLeave annualLeave=annualLeaveRepo.findById(annualLeaveLineDTO.getAnnualLeaveId()).orElseThrow(() -> new IllegalArgumentException("Annual Leave not found"));
        AnnualLeaveLine annualLeaveLine=new AnnualLeaveLine();
        annualLeaveLine.setDeclaredDays(annualLeaveLineDTO.getDeclaredDays());
        annualLeaveLine.setRemainingDays(annualLeaveLineDTO.getRemainingDays());
        annualLeaveLine.setEmployee(employee);
        annualLeaveLine.setAnnualLeave(annualLeave);
        annualLeaveLineRepo.save(annualLeaveLine);
        System.out.println("dsf");
    }
    @Override
    public AnnualLeaveLine getAnnualLeaveLineById47(Long idE, Long annualLeaveId) {
        return annualLeaveLineRepo.findById(new AnnualLeaveLineId(idE, annualLeaveId)).orElse(null);
    }
    @Override
    public void addAnnualLeaveLineForAllOldEmp(Long annualLeaveId, int declaredDays) {

        List<Employees> employees = statistiqueService.oldEmployees();

        AnnualLeave annualLeave = annualLeaveRepo.findById(annualLeaveId)
                .orElseThrow(() -> new IllegalArgumentException("Annual Leave not found"));

        for (Employees employee : employees) {

            AnnualLeaveLine existingAnnualLeaveLine = getAnnualLeaveLineById47(employee.getIdE(), annualLeaveId);

            if (existingAnnualLeaveLine == null) {

                AnnualLeaveLine annualLeaveLine = new AnnualLeaveLine();
                annualLeaveLine.setDeclaredDays(declaredDays);
                annualLeaveLine.setRemainingDays(declaredDays);
                annualLeaveLine.setEmployee(employee);
                annualLeaveLine.setAnnualLeave(annualLeave);

                annualLeaveLineRepo.save(annualLeaveLine);
            } else {
                System.out.println("Employee " + employee.getIdE() + " already has an annual leave line.");
            }
        }

        System.out.println("Annual leave lines have been added for all employees.");
    }
    @Override
    public void addAnnualLeaveLineForAllNewEmp(Long annualLeaveId) {

        List<Employees> employees = statistiqueService.newEmployees();

        AnnualLeave annualLeave = annualLeaveRepo.findById(annualLeaveId)
                .orElseThrow(() -> new IllegalArgumentException("Annual Leave not found"));

        for (Employees employee : employees) {

            AnnualLeaveLine existingAnnualLeaveLine = getAnnualLeaveLineById47(employee.getIdE(), annualLeaveId);

            if (existingAnnualLeaveLine == null) {

                // Calculer le nombre de jours entre la date d'embauche et la date actuelle
                LocalDate hireDate = employee.getHireDate(); // Supposons que vous avez un champ hireDate dans Employees
                LocalDate currentDate = LocalDate.now();
                long daysBetween = ChronoUnit.DAYS.between(hireDate, currentDate);

                // Calculer le nombre de jours de congé
                int declaredDays = (int) ((daysBetween / 30.0) * 1.5); // Diviser par 30 et multiplier par 1.5

                AnnualLeaveLine annualLeaveLine = new AnnualLeaveLine();
                annualLeaveLine.setDeclaredDays(declaredDays);
                annualLeaveLine.setRemainingDays(declaredDays);
                annualLeaveLine.setEmployee(employee);
                annualLeaveLine.setAnnualLeave(annualLeave);

                // Enregistrer la ligne de congé
                annualLeaveLineRepo.save(annualLeaveLine);
            } else {
                System.out.println("Employee " + employee.getIdE() + " already has an annual leave line.");
            }
        }

        System.out.println("Annual leave lines have been added for all employees.");
    }

    @Override
    public List<AnnualLeaveLine> getAllAnnualLeaveLines() {
        return annualLeaveLineRepo.findAll();
    }

    @Override
    public AnnualLeaveLine getAnnualLeaveLineById(Long idE, Long annualLeaveId) {
        return annualLeaveLineRepo.findById(new AnnualLeaveLineId(idE, annualLeaveId))
                .orElseThrow(() -> new IllegalArgumentException("Annual Leave Line not found"));
    }


    @Override
    public void updateAnnualLeaveLine(Long idE, Long annualLeaveId, AnnualLeaveLineDTO annualLeaveLineDTO) {
        AnnualLeaveLine annualLeaveLineToUpdate = annualLeaveLineRepo.findById(new AnnualLeaveLineId(idE, annualLeaveId))
                .orElseThrow(() -> new IllegalArgumentException("Annual Leave Line not found"));
        Employees employee = employeeRepo.findById(annualLeaveLineDTO.getIdE()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        AnnualLeave annualLeave=annualLeaveRepo.findById(annualLeaveLineDTO.getAnnualLeaveId()).orElseThrow(() -> new IllegalArgumentException("Annual Leave not found"));
        annualLeaveLineToUpdate.setDeclaredDays(annualLeaveLineDTO.getDeclaredDays());
        annualLeaveLineToUpdate.setRemainingDays(annualLeaveLineDTO.getRemainingDays());
        annualLeaveLineToUpdate.setEmployee(employee);
        annualLeaveLineToUpdate.setAnnualLeave(annualLeave);
        annualLeaveLineRepo.save(annualLeaveLineToUpdate);

    }

    @Override
    public void deleteAnnualLeaveLine(Long idE, Long annualLeaveId) {
        annualLeaveLineRepo.deleteById(new AnnualLeaveLineId(idE, annualLeaveId));
    }
    @Override
    public List<AnnualLeaveLine> getAnnualLeaveLineByEmpId(Long ide) {

        Employees employee = employeeRepo.findById(ide).orElse(null);

        if (employee != null) {

            return employee.getAnnualLeaveLines();
        } else {

            return Collections.emptyList();
        }
    }
}
