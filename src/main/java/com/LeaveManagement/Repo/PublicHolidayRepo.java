package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.PublicHoliday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicHolidayRepo extends JpaRepository<PublicHoliday,Long> {
}
