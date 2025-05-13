package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Entity.PublicHoliday;
import com.LeaveManagement.Repo.PublicHolidayRepo;
import com.LeaveManagement.Service.PublicHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicHolidayImpl implements PublicHolidayService {
    @Autowired
    private PublicHolidayRepo publicHolidayRepo;

    @Override
    public Long addPublicHoliday(PublicHoliday publicHoliday) {
        publicHolidayRepo.save(publicHoliday);
        return publicHoliday.getId();
    }

    @Override
    public List<PublicHoliday> getAllPublicHolidays() {
        return publicHolidayRepo.findAll();
    }

    @Override
    public PublicHoliday getPublicHolidayById(Long id) {
        return publicHolidayRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Public Holiday not found"));
    }

    @Override
    public void updatePublicHoliday(Long id, PublicHoliday publicHoliday) {
        PublicHoliday publicHolidayToUpdate = publicHolidayRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Public Holiday not found"));
        publicHolidayToUpdate.setName(publicHoliday.getName());
        publicHolidayToUpdate.setDescription(publicHoliday.getDescription());
        publicHolidayToUpdate.setStartDate(publicHoliday.getStartDate());
        publicHolidayToUpdate.setEndDate(publicHoliday.getEndDate());
        publicHolidayRepo.save(publicHolidayToUpdate);
    }

    @Override
    public void deletePublicHoliday(Long id) {
        publicHolidayRepo.deleteById(id);
    }
}
