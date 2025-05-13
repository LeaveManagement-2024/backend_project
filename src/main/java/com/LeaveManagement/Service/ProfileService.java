package com.LeaveManagement.Service;

import com.LeaveManagement.Entity.Profiles;

import java.util.List;

public interface ProfileService {

        Long addProfile(Profiles profile);
        public List<Profiles> getAllProfile();
        public Profiles GetProfileById(Long  id);
        void updateProfile(Long id, Profiles profile);
        void deleteProfile(Long id);
    }

