package com.assesment.Incident_Management.Service.Impl;

import com.assesment.Incident_Management.Entity.POJO.LoginRequest;
import com.assesment.Incident_Management.Entity.UserDetails;
import com.assesment.Incident_Management.Exception.DetailsNotFoundException;
import com.assesment.Incident_Management.Exception.InvalidValueProvidedException;
import com.assesment.Incident_Management.Repository.UserDetailsRepo;
import com.assesment.Incident_Management.Service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserDetailsRepo user;

    @Override
    public String registerUser(UserDetails userDetails) {
        //Check if the user exists in database
        Optional<UserDetails> fetchUser = user.findById(userDetails.getUserName());
    if(fetchUser.isPresent()){
    throw new InvalidValueProvidedException("User Already Exists");
    }
    user.save(userDetails);
        return "User registered successfully!";
    }

    @Override
    public String updatePassword(LoginRequest request) {
        Optional<UserDetails> fetchUser = user.findById(request.getUserName());
        if (fetchUser.isEmpty()){
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
        return "Password changed successfully";
    }

    @Override
    public String authenticateUser(LoginRequest request) {
        Optional<UserDetails> userDetails = user.findById(request.getUserName());
        if(userDetails.isEmpty()){
            throw new DetailsNotFoundException("User Doesn't Exists");
        }
        if(!Objects.equals(userDetails.get().getPassword(), request.getPassword())){
            throw new InvalidValueProvidedException("Incorrect Password provided");
        }
        return "User authenticate Successfully";
    }
}
