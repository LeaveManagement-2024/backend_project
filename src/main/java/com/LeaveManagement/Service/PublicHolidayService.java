package com.LeaveManagement.Service;

import com.LeaveManagement.Entity.PublicHoliday;
import java.util.List;

public interface PublicHolidayService {
    Long addPublicHoliday(PublicHoliday publicHoliday);
    List<PublicHoliday> getAllPublicHolidays();
    PublicHoliday getPublicHolidayById(Long id);
    void updatePublicHoliday(Long id, PublicHoliday publicHoliday);
    void deletePublicHoliday(Long id);
}
