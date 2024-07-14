package com.assesment.Incident_Management.Service.Impl;

import com.assesment.Incident_Management.Entity.POJO.LoginRequest;
import com.assesment.Incident_Management.Entity.UserDetails;
import com.assesment.Incident_Management.Exception.DetailsNotFoundException;
import com.assesment.Incident_Management.Exception.InvalidValueProvidedException;
import com.assesment.Incident_Management.Repository.UserDetailsRepo;
import com.assesment.Incident_Management.Service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserDetailsRepo user;

    @Override
    public String registerUser(UserDetails userDetails) {
      log.info("Register user process started!");
        //Check if the user exists in database
        Optional<UserDetails> fetchUser = user.findById(userDetails.getUserName());
    if(fetchUser.isPresent()){
        log.error("User already exists");
    throw new InvalidValueProvidedException("User Already Exists");
    }
    user.save(userDetails);
    log.info("user registered successfully");
        return "User registered successfully!";
    }

    @Override
    public String updatePassword(LoginRequest request) {
        log.info("update password process started!");
        Optional<UserDetails> fetchUser = user.findById(request.getUserName());
        if (fetchUser.isEmpty()){
            log.error("No user exists with given username");
            throw new DetailsNotFoundException("No user exists with given username");
        }
        UserDetails userDetails = new UserDetails(
                fetchUser.get().getUserName(),
                request.getPassword(),
                fetchUser.get().getUserEmailId(),
                fetchUser.get().getPhoneNumber(),
                fetchUser.get().getPincode(),
                fetchUser.get().getCity(),
                fetchUser.get().getCountry()
                );
        user.save(userDetails);
        log.info("Password changed successfully");
        return "Password changed successfully";
    }

    @Override
    public String authenticateUser(LoginRequest request) {
        log.info("User authentication process started!");
        Optional<UserDetails> userDetails = user.findById(request.getUserName());
        if(userDetails.isEmpty()){
            log.error("User Doesn't Exists");
            throw new DetailsNotFoundException("User Doesn't Exists");
        }
        if(!Objects.equals(userDetails.get().getPassword(), request.getPassword())){
            log.error("Incorrect Password provided");
            throw new InvalidValueProvidedException("Incorrect Password provided");
        }
        log.info("User authenticate Successfully");
        return "User authenticate Successfully";
    }
}
