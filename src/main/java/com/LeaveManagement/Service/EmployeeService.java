package com.LeaveManagement.Service;

import com.LeaveManagement.Dto.EmployeesDTO;
import com.LeaveManagement.Dto.ImportResult;
import com.LeaveManagement.Dto.LogInDTO;
import com.LeaveManagement.Dto.UpdatePassword;
import com.LeaveManagement.Entity.*;
import com.LeaveManagement.response.LogInResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public interface EmployeeService {

    LogInResponse loginEmployee(LogInDTO logInDTO);
    Long addEmployee(EmployeesDTO employeeDTO) throws IOException;
    public List<Employees> getAllEmployees();
    public Employees GetEmployeeById(Long  id);
    void updateEmployee(Long id, EmployeesDTO employeeDTO ) throws IOException;
    void deleteEmployee(Long id);
    void updatePassword(Long id, UpdatePassword updatePassword);
    void updateImage(Long id,EmployeesDTO employeeDTO)throws IOException;
    void updatePasswordByAdmin(Long id,UpdatePassword updatePassword);
    public Departement getDepartmentByIdEmployee(Long id);
    List<Leave> AllLeaveE(Long id);
    List<Leave> ConfermedLeaveE(Long id);
    List<Leave> UnconfermedLeaveE(Long id);
    List<Leave> UnconfermedLeaveByManagerE(Long id);
    List<Leave> UnconfermedLeaveByRemplacmentE(Long id);
    List<Leave> getLeavesToConfirm(Long id);
    void LeavesToConfirmE(Long id, Long idL);
    List<AnnualLeaveLine> getAnnualLeavesLines(Long id);
    List<Leave> getConfirmedLeaves(Long id);
    void LeavesToUnconfirmE(Long id, Long idL);
    public List<Employees> getEmployeesWithoutLeave(LocalDate startDate, LocalDate endDate);
    public ImportResult importEmployeesFromCSV(MultipartFile file) throws IOException;
}
