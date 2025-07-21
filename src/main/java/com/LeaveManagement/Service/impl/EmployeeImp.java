package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Dto.*;
import com.LeaveManagement.Entity.*;
import com.LeaveManagement.Repo.*;
import com.LeaveManagement.Service.AnnualLeaveLineService;
import com.LeaveManagement.Service.EmployeeService;
import com.LeaveManagement.response.LogInResponse;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    private ProfileRepo profileRepository;

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
    private DepartementRepo departementRepo;
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
        // Create a new employee and set basic properties
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

        // Handle optional relationships
        // Only try to set post if postId is not null
        if (employeeDTO.getPostId() != null) {
            Posts post = postsRepo.findById(employeeDTO.getPostId()).orElse(null);
            if (post != null) {
                employee.setPost(post);
            }
        }

        // Only try to set profile if profileId is not null
        if (employeeDTO.getProfileId() != null) {
            Profiles profile = profileRepo.findById(employeeDTO.getProfileId()).orElse(null);
            if (profile != null) {
                employee.setProfile(profile);
            }
        }

        // Only try to set department if filiereId is not null
        if (employeeDTO.getDepartementId() != null) {
            Departement departement = departementRepo.findById(employeeDTO.getDepartementId()).orElse(null);
            if (departement != null) {
                employee.setDepartement(departement);
            }
        }

        // Handle image upload if present
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

        // Save employee and return ID
        employeeRep.save(employee);
        return employee.getIdE();
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
    public Departement getDepartmentByIdEmployee(Long id){
        Employees employee= employeeRep.findById(id).orElseThrow(() ->new IllegalArgumentException("Employee not found"));
        return employee.getDepartement();

    }

    @Override
    public void updateEmployee(Long id, EmployeesDTO employeeDTO) throws IOException {

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

        // Handle optional relationships
        // Only try to set post if postId is not null
        if (employeeDTO.getPostId() != null) {
            Posts post = postsRepo.findById(employeeDTO.getPostId()).orElse(null);
            if (post != null) {
                employeesToUpdate.setPost(post);
            }
        }

        // Only try to set profile if profileId is not null
        if (employeeDTO.getProfileId() != null) {
            Profiles profile = profileRepo.findById(employeeDTO.getProfileId()).orElse(null);
            if (profile != null) {
                employeesToUpdate.setProfile(profile);
            }
        }

        // Only try to set department if filiereId is not null
        if (employeeDTO.getDepartementId() != null) {
            Departement departement = departementRepo.findById(employeeDTO.getDepartementId()).orElse(null);
            if (departement != null) {
                employeesToUpdate.setDepartement(departement);
            }
        }

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
        Long depId  = employeeDTO.getDepartementId();
        if (depId != null) {
            Departement departement= departementRepo.findById(depId).orElse(null);
            employeesToUpdate.setDepartement(departement);
        }

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

    @Override
    public ImportResult importEmployeesFromCSV(MultipartFile file) throws IOException {
        ImportResult result = new ImportResult();

        try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            // Configuration d'OpenCSV
            CsvToBean<EmployeeCSVDTO> csvToBean = new CsvToBeanBuilder<EmployeeCSVDTO>(reader)
                    .withType(EmployeeCSVDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            List<EmployeeCSVDTO> csvEmployees = csvToBean.parse();
            result.setTotalRecords(csvEmployees.size());

            for (int i = 0; i < csvEmployees.size(); i++) {
                try {
                    EmployeeCSVDTO csvEmployee = csvEmployees.get(i);

                    // Validation des données
                    if (!validateEmployee(csvEmployee, result, i + 1)) {
                        result.setErrorCount(result.getErrorCount() + 1);
                        continue;
                    }

                    // Conversion en entité Employee
                    Employees employee = convertCSVToEmployee(csvEmployee, result, i + 1);

                    if (employee != null) {
                        employeeRep.save(employee);
                        result.setSuccessCount(result.getSuccessCount() + 1);
                    } else {
                        result.setErrorCount(result.getErrorCount() + 1);
                    }

                } catch (Exception e) {
                    result.addError("Ligne " + (i + 1) + ": " + e.getMessage());
                    result.setErrorCount(result.getErrorCount() + 1);
                }
            }
        }

        return result;
    }

    private boolean validateEmployee(EmployeeCSVDTO csvEmployee, ImportResult result, int lineNumber) {
        boolean isValid = true;

        if (csvEmployee.getFirstName() == null || csvEmployee.getFirstName().trim().isEmpty()) {
            result.addError("Ligne " + lineNumber + ": Le prénom est obligatoire");
            isValid = false;
        }

        if (csvEmployee.getLastName() == null || csvEmployee.getLastName().trim().isEmpty()) {
            result.addError("Ligne " + lineNumber + ": Le nom est obligatoire");
            isValid = false;
        }

        if (csvEmployee.getEmail() == null || csvEmployee.getEmail().trim().isEmpty()) {
            result.addError("Ligne " + lineNumber + ": L'email est obligatoire");
            isValid = false;
        } else {
            // CORRECTION : Vérifier si l'email existe déjà
            Optional<Employees> existingEmployee = Optional.ofNullable(employeeRep.findByEmail(csvEmployee.getEmail()));
            if (existingEmployee.isPresent()) {
                result.addError("Ligne " + lineNumber + ": L'email " + csvEmployee.getEmail() + " existe déjà");
                isValid = false;
            }
        }

        if (csvEmployee.getHireDate() == null || csvEmployee.getHireDate().trim().isEmpty()) {
            result.addError("Ligne " + lineNumber + ": La date d'embauche est obligatoire");
            isValid = false;
        } else {
            try {
                LocalDate.parse(csvEmployee.getHireDate());
            } catch (DateTimeParseException e) {
                result.addError("Ligne " + lineNumber + ": Format de date invalide. Utilisez yyyy-MM-dd");
                isValid = false;
            }
        }

        return isValid;
    }

    private Employees convertCSVToEmployee(EmployeeCSVDTO csvEmployee, ImportResult result, int lineNumber) {
        try {
            Employees employee = new Employees();

            employee.setFirstName(csvEmployee.getFirstName().trim());
            employee.setLastName(csvEmployee.getLastName().trim());
            employee.setEmail(csvEmployee.getEmail().trim());
            employee.setPassword(csvEmployee.getPassword() != null ? csvEmployee.getPassword().trim() : "defaultPassword123");
            employee.setPhone(csvEmployee.getPhone() != null ? csvEmployee.getPhone().trim() : "");
            employee.setPpr(csvEmployee.getPpr() != null ? csvEmployee.getPpr().trim() : "");
            employee.setCin(csvEmployee.getCin() != null ? csvEmployee.getCin().trim() : "");
            employee.setAddress(csvEmployee.getAddress() != null ? csvEmployee.getAddress().trim() : "");
            employee.setHireDate(LocalDate.parse(csvEmployee.getHireDate()));
            employee.setWorkLocation(csvEmployee.getWorkLocation() != null ? csvEmployee.getWorkLocation().trim() : "");

            // Recherche et assignation du profil
            if (csvEmployee.getProfileName() != null && !csvEmployee.getProfileName().trim().isEmpty()) {
                Optional<Profiles> profile = profileRepository.findByProfileName(csvEmployee.getProfileName().trim());
                if (profile.isPresent()) {
                    employee.setProfile(profile.get());
                } else {
                    result.addError("Ligne " + lineNumber + ": Profil '" + csvEmployee.getProfileName() + "' non trouvé");
                }
            }

            // Recherche et assignation du département
            if (csvEmployee.getDepartmentName() != null && !csvEmployee.getDepartmentName().trim().isEmpty()) {
                Optional<Departement> department = departementRepo.findByDepartementName(csvEmployee.getDepartmentName().trim());
                if (department.isPresent()) {
                    employee.setDepartement(department.get());
                } else {
                    result.addError("Ligne " + lineNumber + ": Département '" + csvEmployee.getDepartmentName() + "' non trouvé");
                }
            }

            // Recherche et assignation du poste
            if (csvEmployee.getPostName() != null && !csvEmployee.getPostName().trim().isEmpty()) {
                Optional<Posts> post = postsRepo.findByPostName(csvEmployee.getPostName().trim());
                if (post.isPresent()) {
                    employee.setPost(post.get());
                } else {
                    result.addError("Ligne " + lineNumber + ": Poste '" + csvEmployee.getPostName() + "' non trouvé");
                }
            }

            return employee;

        } catch (Exception e) {
            result.addError("Ligne " + lineNumber + ": Erreur de conversion - " + e.getMessage());
            return null;
        }
    }
}

