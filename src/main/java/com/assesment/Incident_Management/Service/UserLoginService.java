package com.assesment.Incident_Management.Service;

import com.assesment.Incident_Management.Entity.POJO.LoginRequest;
import com.assesment.Incident_Management.Entity.UserDetails;

public interface UserLoginService {
    String registerUser(UserDetails userDetails);
    String updatePassword(LoginRequest request);
    String authenticateUser(LoginRequest request);
}
