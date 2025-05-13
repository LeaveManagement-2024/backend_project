package com.LeaveManagement.Service.impl;
import com.LeaveManagement.Entity.Profiles;
import com.LeaveManagement.Repo.ProfileRepo;
import com.LeaveManagement.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public Long addProfile(Profiles profile) {

        profileRepo.save(profile);
        return profile.getIdProfile();
    }



    @Override
    public List<Profiles> getAllProfile() {
        return profileRepo.findAll();
    }

    @Override
    public Profiles GetProfileById(Long id) {
        return profileRepo.findById(id).get();
    }

    @Override
    public void updateProfile(Long id, Profiles profile) {
        Profiles profileToUpdate = profileRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Profile not found"));
        profileToUpdate.setProfileName(profile.getProfileName());
        profileRepo.save(profileToUpdate);
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepo.deleteById(id);

    }
}


