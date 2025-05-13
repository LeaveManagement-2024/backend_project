package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.Employees;
import com.LeaveManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Long> {

    Optional<Employees> findOneByEmailAndPassword(String email, String password);

    Employees findByEmail(String email);
    @Query("SELECT e FROM Employees e WHERE e.hireDate >= :startDate")
    List<Employees> findNewEmployees(@Param("startDate") LocalDate startDate);

    @Query("SELECT e FROM Employees e WHERE e.hireDate < :startDate")
    List<Employees> findOldEmployees(@Param("startDate") LocalDate startDate);
    @Query("SELECT e FROM Employees e WHERE e.idE NOT IN " +
            "(SELECT l.employee.idE FROM Leave l WHERE l.startDate <= :endDate AND l.endDate >= :startDate)")
    List<Employees> findEmployeesWithoutLeave(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
