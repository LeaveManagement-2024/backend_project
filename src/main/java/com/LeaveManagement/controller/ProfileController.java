package com.LeaveManagement.controller;
import com.LeaveManagement.Entity.Profiles;
import com.LeaveManagement.Service.ProfileService;
import com.LeaveManagement.Service.impl.ProfileImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileImpl profileImpl;

    @PostMapping(path = "/save")
    public Long saveProfile(@RequestBody Profiles profilee){

        Long id =profileService.addProfile(profilee);
        return id;
    }

    @GetMapping(path="/getAll")
    public List<Profiles> getAllProfile(){

        return  profileService.getAllProfile();
    }

    @GetMapping(path="/getById/{Id}")
    public Profiles getProfileById(@PathVariable Long Id){

        return profileService.GetProfileById(Id);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable Long id, @RequestBody Profiles profile) {

        profileService.updateProfile(id,profile);
        return ResponseEntity.ok("Profile updated successfully");
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {

        profileService.deleteProfile(id);
        return ResponseEntity.ok("Profile deleted successfully");
    }

}


