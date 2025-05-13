package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@EnableJpaRepositories
@Repository
public interface LeaveRepo extends JpaRepository<Leave,Long> {
    @Query("SELECT COUNT(l) FROM Leave l WHERE l.startDate >= CURRENT_DATE")
    long countNewLeaveRequests();

   /* @Query("SELECT l FROM Leave l WHERE l.responsibleVisa = False or l.managerVisa = False or l.remplecementVisa = False")
    List<Leave> UnconfirmedLeaves();
*/
    @Query("SELECT COUNT(l) FROM Leave l WHERE l.managerVisa = 'false' ")
    long countUnconfirmedLeavesByManager();
    @Query("SELECT l FROM Leave l WHERE l.managerVisa = 'false' ")
    List<Leave> unconfirmedLeavesByManager();

    @Query("SELECT l FROM Leave l WHERE :currentDate BETWEEN l.startDate AND l.endDate")
    List<Leave> findLeavesByDate(LocalDate currentDate);
    @Query("SELECT l FROM Leave l WHERE l.endDate = :yesterday")
    List<Leave> findEmployeesReturningToWorkTomorrow(LocalDate yesterday);
    @Query("SELECT l FROM Leave l WHERE l.lmanager.idE = :id OR l.responsible.idE = :id OR l.replacement.idE = :id")
    List<Leave> findLeavesById(@Param("id") Long id);


}
