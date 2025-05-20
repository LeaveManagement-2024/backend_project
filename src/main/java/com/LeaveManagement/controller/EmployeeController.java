package com.LeaveManagement.controller;

import com.LeaveManagement.Dto.EmployeesDTO;
import com.LeaveManagement.Dto.LogInDTO;
import com.LeaveManagement.Dto.UpdatePassword;
import com.LeaveManagement.Entity.*;
import com.LeaveManagement.Service.EmployeeService;
import com.LeaveManagement.response.LogInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping(path="/login")
        public ResponseEntity<?> loginUser(@RequestBody LogInDTO logInDTO) {
        LogInResponse logInResponse = employeeService.loginEmployee(logInDTO);
        return ResponseEntity.ok(logInResponse);
    }

    @PostMapping(path = "/save", consumes = {"multipart/form-data"})
    public Long saveEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("ppr") String ppr,
            @RequestParam("cin") String cin,
            @RequestParam("address") String address,
            @RequestParam("hireDate") String hireDate, // Format: "yyyy-MM-dd"
            @RequestParam("workLocation") String workLocation,
            @RequestParam(value = "image",required = false) MultipartFile image,
            @RequestParam(value = "postId" ,required = false) Long postId,
            @RequestParam(value = "departmentId",required = false) Long departmentId,
            @RequestParam(value = "profileId",required = false) Long profileId
            ) throws IOException {

        EmployeesDTO employeesDTO = new EmployeesDTO();
        employeesDTO.setFirstName(firstName);
        employeesDTO.setLastName(lastName);
        employeesDTO.setEmail(email);
        employeesDTO.setPassword(password);
        employeesDTO.setPhone(phone);
        employeesDTO.setPpr(ppr);
        employeesDTO.setCin(cin);
        employeesDTO.setAddress(address);
        employeesDTO.setHireDate(LocalDate.parse(hireDate));
        employeesDTO.setWorkLocation(workLocation);
        employeesDTO.setImage(image);
        employeesDTO.setPostId(postId);
        employeesDTO.setProfileId(profileId);
        employeesDTO.setDepartementId(departmentId);
        employeesDTO.setProfileId(profileId);

        Long id  = employeeService.addEmployee(employeesDTO);
        return id;
    }

    @GetMapping(path="/getAll")
    public List<Employees> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path="/getById/{Id}")
    public Employees getEmployeeById(@PathVariable Long Id) {
        return employeeService.GetEmployeeById(Id);
    }

    @GetMapping(path="/getDepartment/{Id}")
    public Departement getFiliere(@PathVariable Long Id) {
        return employeeService.getDepartmentByIdEmployee(Id);
    }
    @PutMapping(path = "/update/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("ppr") String ppr,
            @RequestParam("cin") String cin,
            @RequestParam("address") String address,
            @RequestParam("hireDate") String hireDate, // Format: "yyyy-MM-dd"
            @RequestParam("workLocation") String workLocation,
            @RequestParam(value = "image",required = false) MultipartFile image,
            @RequestParam(value = "postId",required = false) Long postId,
            @RequestParam(value = "profileId",required = false ) Long profileId,
            @RequestParam(value = "departmentId",required = false) Long departmentId) throws IOException {

        EmployeesDTO employeesDTO = new EmployeesDTO();
        employeesDTO.setFirstName(firstName);
        employeesDTO.setLastName(lastName);
        employeesDTO.setEmail(email);
        employeesDTO.setPassword(password);
        employeesDTO.setPhone(phone);
        employeesDTO.setPpr(ppr);
        employeesDTO.setCin(cin);
        employeesDTO.setAddress(address);
        employeesDTO.setHireDate(LocalDate.parse(hireDate));
        employeesDTO.setWorkLocation(workLocation);
        employeesDTO.setImage(image);
        employeesDTO.setPostId(postId);
        employeesDTO.setProfileId(profileId);
        employeesDTO.setDepartementId(departmentId);
        employeeService.updateEmployee(id, employeesDTO);
        return ResponseEntity.ok("Employee updated successfully");
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
    @PostMapping(path="/updatePassword/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable Long id,@RequestBody UpdatePassword updatePassword) {
        try {
            employeeService.updatePassword(id, updatePassword);
            return ResponseEntity.ok("Mot de passe mis à jour avec succès");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Une erreur est survenue");
        }
    }



    @PostMapping("/image/{id}")
    public ResponseEntity<String> updateImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile imageFile) {

        try {
            // Crée un DTO pour transporter l'image
            EmployeesDTO employeeDTO = new EmployeesDTO();
            employeeDTO.setImage(imageFile);

            // Appelle le service pour mettre à jour l'image
            employeeService.updateImage(id, employeeDTO);
            return ResponseEntity.ok("Image mise à jour avec succès");

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'image");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue");
        }
    }
    @GetMapping(path="/AllLeaveE/{Id}")
    public List<Leave>AllLeaveE(@PathVariable Long Id) {
        return employeeService.AllLeaveE(Id);
    }
    @GetMapping(path="/ConfermedLeaveE/{Id}")
    public List<Leave>ConfermedLeaveE(@PathVariable Long Id) {
        return employeeService.ConfermedLeaveE(Id);
    }
    @GetMapping(path="/getLeavesToConfirm/{Id}")
    public List<Leave>getLeavesToConfirm(@PathVariable Long Id) {
        return employeeService.getLeavesToConfirm(Id);
    }
    @GetMapping(path="/getConfirmedLeaves/{Id}")
    public List<Leave>getConfirmedLeaves(@PathVariable Long Id) {
        return employeeService.getConfirmedLeaves(Id);
    }
    @GetMapping(path="/UnconfermedLeaveE/{Id}")
    public List<Leave>UnconfermedLeaveE(@PathVariable Long Id) {
        return employeeService.UnconfermedLeaveE(Id);
    }
    @GetMapping(path="/UnconfermedLeaveByManagerE/{Id}")
    public List<Leave>UnconfermedLeaveByManagerE(@PathVariable Long Id) {
        return employeeService.UnconfermedLeaveByManagerE(Id);
    }
    @GetMapping(path="/UnconfermedLeaveByResponsibleE/{Id}")
    public List<Leave>UnconfermedLeaveByResponsibleE(@PathVariable Long Id) {
        return employeeService.UnconfermedLeaveByResponsibleE(Id);
    }
    @GetMapping(path="/UnconfermedLeaveByRemplacmentE/{Id}")
    public List<Leave>UnconfermedLeaveByRemplacmentE(@PathVariable Long Id) {
        return employeeService.UnconfermedLeaveByRemplacmentE(Id);
    }
    @GetMapping(path="/getAnnualLeavesLines/{Id}")
    public List<AnnualLeaveLine>getAnnualLeavesLines(@PathVariable Long Id) {
        return employeeService.getAnnualLeavesLines(Id);
    }

    @PostMapping(path="/LeavesToConfirmE/{idE}/{idL}")
    public void LeavesToConfirmE(@PathVariable Long idE,@PathVariable Long idL) {
        employeeService.LeavesToConfirmE(idE,idL);

    }
    @PostMapping(path="/LeavesToUnconfirmE/{idE}/{idL}")
    public void LeavesToUnconfirmE(@PathVariable Long idE,@PathVariable Long idL) {
        employeeService.LeavesToUnconfirmE(idE,idL);

    }
    @GetMapping("/without-leave")
    public List<Employees> getEmployeesWithoutLeave(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                    @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return employeeService.getEmployeesWithoutLeave(startDate, endDate);
    }

}
