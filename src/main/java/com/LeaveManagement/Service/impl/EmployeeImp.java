package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Dto.EmployeesDTO;
import com.LeaveManagement.Dto.LogInDTO;
import  com.LeaveManagement.Dto.UpdatePassword;
import com.LeaveManagement.Entity.*;
import com.LeaveManagement.Repo.*;
import com.LeaveManagement.Service.AnnualLeaveLineService;
import com.LeaveManagement.Service.EmployeeService;
import com.LeaveManagement.response.LogInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeImp implements EmployeeService {



    public int calculateWorkingDays(LocalDate startDate, LocalDate endDate, List<PublicHoliday> publicHolidays) {
        // Vérifier que la date de début n'est pas après la date de fin
        if (startDate.isAfter(endDate)) {
            return 0;
        }

        int workingDays = 0;

        // Parcourir chaque jour entre startDate et endDate inclusivement
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            // Vérifier si le jour n'est ni un samedi ni un dimanche
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && !isPublicHoliday(date, publicHolidays)) {
                workingDays++;
            }
        }

        return workingDays;
    }

    private boolean isPublicHoliday(LocalDate date, List<PublicHoliday> publicHolidays) {
        for (PublicHoliday holiday : publicHolidays) {
            // Convertir les dates de PublicHoliday en LocalDate pour la comparaison
            LocalDate holidayStart = new java.sql.Date(holiday.getStartDate().getTime()).toLocalDate();
            LocalDate holidayEnd = new java.sql.Date(holiday.getEndDate().getTime()).toLocalDate();

            // Vérifier si la date tombe pendant un jour férié
            if (!date.isBefore(holidayStart) && !date.isAfter(holidayEnd)) {
                return true;
            }
        }
        return false;
    }
    @Autowired
    private EmployeeRepo employeeRep;
    @Autowired
    private PublicHolidayRepo publicHolidayRepo;
    @Autowired
    private AnnualLeaveLineRepo annualLeaveLineRepo;

    @Autowired
    private AnnualLeaveLineService annualLeaveLineService;

    @Autowired
    private LeaveRepo leaveRepo;

    @Autowired
    private PostsRepo postsRepo;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private GradesRepo gradesRepo;
    @Autowired
    private FiliereRepo filiereRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final String storageDirectoryPath = Paths.get("uploaded-images").toAbsolutePath().toString();
    String baseUrl = "http://localhost:8093/uploaded-images/";


    @Override
    public LogInResponse loginEmployee(LogInDTO logInDTO) {
        String msg ="";
        Employees user1 = employeeRep.findByEmail(logInDTO.getEmail());
        if (user1 != null) {
            String password = logInDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Employees> employee = employeeRep.findOneByEmailAndPassword(logInDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LogInResponse("login Success",true,user1.getIdE());
                }else {
                    return new LogInResponse("login Failed",false,null);
                }
            }else {
                return new LogInResponse("Password not match",false,null);
            }
        }else {
            return new LogInResponse("Email Not Exist",false,null);
        }
    }



    @Override
    public Long addEmployee(EmployeesDTO employeeDTO) throws IOException {
        //Posts post = postsRepo.findById(employeeDTO.getPostId()).orElse(null);
        //Grades grade = gradesRepo.findById(employeeDTO.getGradeId()).orElse(null);
       //Profiles profile = profileRepo.findById(employeeDTO.getProfileId()).orElse(null);
        //Filiere filiere= filiereRepo.findById(employeeDTO.getFiliereId()).orElse(null);
        Employees employee = new Employees();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        employee.setPhone(employeeDTO.getPhone());
        employee.setPpr(employeeDTO.getPpr());
        employee.setCin(employeeDTO.getCin());
        employee.setAddress(employeeDTO.getAddress());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setWorkLocation(employeeDTO.getWorkLocation());
        MultipartFile file = employeeDTO.getImage();

        if (file != null && !file.isEmpty()) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path storageDirectory = Paths.get(storageDirectoryPath);
            if (!Files.exists(storageDirectory)) {
                Files.createDirectories(storageDirectory);
            }
            Path destinationPath = storageDirectory.resolve(Path.of(filename));
            file.transferTo(destinationPath);
            employee.setImage(baseUrl + filename);
        }
        //employee.setGrade(grade);
        //employee.setPost(post);
        //employee.setProfile(profile);
        //employee.setFiliere(filiere);
        employeeRep.save(employee);
        return  employee.getIdE();
    }
    @Override
    public List<Employees> getAllEmployees() {
        return employeeRep.findAll();
    }

    @Override
    public Employees GetEmployeeById(Long id) {
        return employeeRep.findById(id).get();

    }
    @Override
    public Filiere getFiliereByIdEmployee(Long id){
        Employees employee= employeeRep.findById(id).orElseThrow(() ->new IllegalArgumentException("Employee not found"));
        return employee.getFiliere();

    }



    @Override
    public void updateEmployee(Long id, EmployeesDTO employeeDTO) throws IOException {
        Posts post = postsRepo.findById(employeeDTO.getPostId()).orElse(null);
        Grades grade = gradesRepo.findById(employeeDTO.getGradeId()).orElse(null);
        Profiles profile = profileRepo.findById(employeeDTO.getProfileId()).orElse(null);

        Employees employeesToUpdate = employeeRep.findById(id).orElseThrow(() ->new IllegalArgumentException("Employee not found"));
        employeesToUpdate.setFirstName(employeeDTO.getFirstName());
        employeesToUpdate.setLastName(employeeDTO.getLastName());
        employeesToUpdate.setEmail(employeeDTO.getEmail());
        employeesToUpdate.setPhone(employeeDTO.getPhone());
        employeesToUpdate.setPpr(employeeDTO.getPpr());
        employeesToUpdate.setCin(employeeDTO.getCin());
        employeesToUpdate.setAddress(employeeDTO.getAddress());
        employeesToUpdate.setHireDate(employeeDTO.getHireDate());
        employeesToUpdate.setWorkLocation(employeeDTO.getWorkLocation());

        MultipartFile file = employeeDTO.getImage();
        if (file != null && !file.isEmpty()) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path storageDirectory = Paths.get(storageDirectoryPath);
            if (!Files.exists(storageDirectory)) {
                Files.createDirectories(storageDirectory);
            }            Path destinationPath = storageDirectory.resolve(Path.of(filename));
            file.transferTo(destinationPath);
            employeesToUpdate.setImage(baseUrl + filename);
        }

        String newPassword = employeeDTO.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            employeesToUpdate.setPassword(passwordEncoder.encode(newPassword));
        }
        Long filieret  = employeeDTO.getFiliereId();
        if (filieret != null) {
            Filiere filiere= filiereRepo.findById(filieret).orElse(null);
            employeesToUpdate.setFiliere(filiere);
        }

        employeesToUpdate.setProfile(profile);
        employeesToUpdate.setGrade(grade);
        employeesToUpdate.setPost(post);

        employeeRep.save(employeesToUpdate);
    }
    @Override
    public void deleteEmployee(Long id) {
        employeeRep.deleteById(id);

    }
    @Override
    public void updatePassword(Long id,UpdatePassword updatePassword){
        Employees employeesToUpdate = employeeRep.findById(id).orElseThrow(() ->new IllegalArgumentException("Employee not found"));
        if (employeesToUpdate != null) {
            String password = updatePassword.getOldPassword();
            String encodedPassword = employeesToUpdate.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                employeesToUpdate.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
                employeeRep.save(employeesToUpdate);
            }
            else  throw new IllegalArgumentException("Ancien mot de passe incorrect");


        }


    }
    @Override
    public void updatePasswordByAdmin(Long id,UpdatePassword updatePassword){
        Employees employeesToUpdate = employeeRep.findById(id).orElseThrow(() ->new IllegalArgumentException("Employee not found"));
                employeesToUpdate.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
                employeeRep.save(employeesToUpdate);
            }
    @Override
    public void updateImage(Long id,EmployeesDTO employeeDTO) throws IOException {
        Employees employeesToUpdate = employeeRep.findById(id).orElseThrow(() ->new IllegalArgumentException("Employee not found"));
        MultipartFile file = employeeDTO.getImage();

        if (file != null && !file.isEmpty()) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path storageDirectory = Paths.get(storageDirectoryPath);
            if (!Files.exists(storageDirectory)) {
                Files.createDirectories(storageDirectory);
            }
            Path destinationPath = storageDirectory.resolve(Path.of(filename));
            file.transferTo(destinationPath);
            employeesToUpdate.setImage(baseUrl + filename);
            employeeRep.save(employeesToUpdate);
        }
        }
    @Override
    public List<Leave> AllLeaveE(Long id) {
        Employees employee = employeeRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return employee.getLeaves();
    }

    @Override
    public List<Leave> ConfermedLeaveE(Long id) {
        Employees employee = employeeRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        List<Leave> leaves = employee.getLeaves();

        // Filtrer les congés qui ne sont pas encore confirmés (visa "false")
        List<Leave> confirmedLeaves = leaves.stream()
                .filter(leave -> "true".equals(leave.getManagerVisa()))
                .collect(Collectors.toList());

        return confirmedLeaves;
    }

    @Override
    public List<Leave> UnconfermedLeaveE(Long id) {
        Employees employee = employeeRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        List<Leave> leaves = employee.getLeaves();

        // Filtrer les congés qui ne sont pas encore confirmés (visa "false")
        List<Leave> unconfirmedLeaves = leaves.stream()
                .filter(leave -> "false".equals(leave.getManagerVisa()))
                .collect(Collectors.toList());

        return unconfirmedLeaves;
    }

    @Override
    public List<Leave> UnconfermedLeaveByManagerE(Long id) {
        Employees employee = employeeRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        List<Leave> leaves = employee.getLeaves();

        // Filtrer les congés qui ne sont pas encore confirmés (visa "false")
        List<Leave> unconfirmedLeaves = leaves.stream()
                .filter(leave -> "false".equals(leave.getManagerVisa()))
                .collect(Collectors.toList());

        return unconfirmedLeaves;
    }

    @Override
    public List<Leave> UnconfermedLeaveByResponsibleE(Long id) {
        Employees employee = employeeRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        List<Leave> leaves = employee.getLeaves();

        // Filtrer les congés qui ne sont pas encore confirmés (visa "false")
        List<Leave> unconfirmedLeaves = leaves.stream()
                .filter(leave -> "false".equals(leave.getResponsibleVisa()))
                .collect(Collectors.toList());

        return unconfirmedLeaves;
    }

    @Override
    public List<Leave> UnconfermedLeaveByRemplacmentE(Long id) {
        Employees employee = employeeRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        List<Leave> leaves = employee.getLeaves();

        // Filtrer les congés qui ne sont pas encore confirmés (visa "false")
        List<Leave> unconfirmedLeaves = leaves.stream()
                .filter(leave ->  "false".equals(leave.getRemplecementVisa()))
                .collect(Collectors.toList());

        return unconfirmedLeaves;
    }
    @Override
    public List<Leave> getLeavesToConfirm(Long id) {
        Employees employee = employeeRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Récupérer tous les congés où cet employé est soit manager, soit responsable, soit remplaçant
        List<Leave> leavesToConfirm = leaveRepo.findAll().stream()
                .filter(leave -> (leave.getLmanager() != null && leave.getLmanager().getIdE().equals(id) && leave.getManagerVisa().equals("false"))
                        || (leave.getResponsible() != null && leave.getResponsible().getIdE().equals(id) && leave.getResponsibleVisa() .equals("false"))
                        || (leave.getReplacement() != null && leave.getReplacement().getIdE().equals(id) && leave.getRemplecementVisa().equals("false")))
                .collect(Collectors.toList());

        return leavesToConfirm;
    }
    @Override
    public List<Leave> getConfirmedLeaves(Long id) {
        Employees employee = employeeRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Récupérer tous les congés où cet employé est soit manager, soit responsable, soit remplaçant
        List<Leave> leavesConfirmedLeaves = leaveRepo.findAll().stream()
                .filter(leave -> (leave.getLmanager() != null && leave.getLmanager().getIdE().equals(id) && leave.getManagerVisa().equals("true"))
                        || (leave.getResponsible() != null && leave.getResponsible().getIdE().equals(id) && leave.getResponsibleVisa() .equals("true"))
                        || (leave.getReplacement() != null && leave.getReplacement().getIdE().equals(id) && leave.getRemplecementVisa().equals("true")))
                .collect(Collectors.toList());

        return leavesConfirmedLeaves;
    }
    @Override
    public List<AnnualLeaveLine> getAnnualLeavesLines(Long id) {
        Employees employee = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
       return employee.getAnnualLeaveLines();
    }
    @Override
    public void LeavesToConfirmE(Long id, Long idL) {


        Employees employee = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        Leave leavesToConfirm = leaveRepo.findById(idL).orElseThrow(() -> new IllegalArgumentException("Leave not found"));
        List<PublicHoliday> publicHolidays = publicHolidayRepo.findAll();

        Long idM=leavesToConfirm.getLmanager().getIdE();
        Long idE= employee.getIdE();
        Long idR=leavesToConfirm.getResponsible().getIdE();
        Long idRe=leavesToConfirm.getReplacement().getIdE();

      if (idE == idM){
          AnnualLeaveLine annualLeaveLine =  annualLeaveLineService.getAnnualLeaveLineById(leavesToConfirm.getEmployee().getIdE(),leavesToConfirm.getAnnualLeave().getAnnualLeaveId());
          int rm = calculateWorkingDays(leavesToConfirm.getStartDate(),leavesToConfirm.getEndDate(),publicHolidays);
          if(annualLeaveLine.getRemainingDays()>rm){
              leavesToConfirm.setManagerVisa("true");
              LocalDate today = LocalDate.now();
              leavesToConfirm.setManagerVisaDate(today);
              leaveRepo.save(leavesToConfirm);
              System.out.println("1");
              System.out.println(rm);
              int nm = (annualLeaveLine.getRemainingDays())-(rm);
              annualLeaveLine.setRemainingDays(nm);
              annualLeaveLineRepo.save(annualLeaveLine);
              System.out.println("1");
          }

      }
      if (idE == idR ){
            leavesToConfirm.setResponsibleVisa("true");
            LocalDate today = LocalDate.now();
            leavesToConfirm.setResponsibleVisaDate(today);
            leaveRepo.save(leavesToConfirm);
            System.out.println("1");

      }
      if(idE == idRe){
            leavesToConfirm.setRemplecementVisa("true");
            LocalDate today = LocalDate.now();
            leavesToConfirm.setRemplecementVisaDate(today);
            leaveRepo.save(leavesToConfirm);
            System.out.println("1");
      }
    }
    @Override
    public void LeavesToUnconfirmE(Long id, Long idL) {


        Employees employee = employeeRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        Leave leavesToConfirm = leaveRepo.findById(idL).orElseThrow(() -> new IllegalArgumentException("Leave not found"));
        List<PublicHoliday> publicHolidays = publicHolidayRepo.findAll();

        Long idM=leavesToConfirm.getLmanager().getIdE();
        Long idE= employee.getIdE();
        Long idR=leavesToConfirm.getResponsible().getIdE();
        Long idRe=leavesToConfirm.getReplacement().getIdE();

        if (idE == idM){
            AnnualLeaveLine annualLeaveLine =  annualLeaveLineService.getAnnualLeaveLineById(leavesToConfirm.getEmployee().getIdE(),leavesToConfirm.getAnnualLeave().getAnnualLeaveId());
            int rm = calculateWorkingDays(leavesToConfirm.getStartDate(),leavesToConfirm.getEndDate(),publicHolidays);

                leavesToConfirm.setManagerVisa("false");
                LocalDate today = LocalDate.now();
                leavesToConfirm.setManagerVisaDate(today);
                leaveRepo.save(leavesToConfirm);
                System.out.println("1");
                System.out.println(rm);
                int nm = (annualLeaveLine.getRemainingDays())+(rm);
                annualLeaveLine.setRemainingDays(nm);
                annualLeaveLineRepo.save(annualLeaveLine);
                System.out.println("1");

        }
        if (idE == idR){
            leavesToConfirm.setResponsibleVisa("false");
            LocalDate today = LocalDate.now();
            leavesToConfirm.setResponsibleVisaDate(today);
            leaveRepo.save(leavesToConfirm);
            System.out.println("1");

        }
        if(idE == idRe){
            leavesToConfirm.setRemplecementVisa("false");
            LocalDate today = LocalDate.now();
            leavesToConfirm.setRemplecementVisaDate(today);
            leaveRepo.save(leavesToConfirm);
            System.out.println("1");
        }
    }

    @Override
    public List<Employees> getEmployeesWithoutLeave(LocalDate startDate, LocalDate endDate) {
        return employeeRep.findEmployeesWithoutLeave(startDate, endDate);
    }
}

