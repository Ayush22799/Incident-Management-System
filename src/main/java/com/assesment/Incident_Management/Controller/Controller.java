package com.assesment.Incident_Management.Controller;

import com.assesment.Incident_Management.Entity.IncidentDetails;
import com.assesment.Incident_Management.Entity.POJO.CreateIncidentRequest;
import com.assesment.Incident_Management.Entity.POJO.EditIncidentRequest;
import com.assesment.Incident_Management.Entity.POJO.LoginRequest;
import com.assesment.Incident_Management.Entity.UserDetails;
import com.assesment.Incident_Management.Service.IncidentService;
import com.assesment.Incident_Management.Service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident/v1")
public class Controller {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    IncidentService incidentService;

    @PostMapping("/login/register")
    public String registerNewUser(@RequestBody UserDetails userDetails){
        return userLoginService.registerUser(userDetails);
    }

    @PutMapping("/login/reset")
    public String updatePassword(@RequestBody LoginRequest loginRequest){
        return userLoginService.updatePassword(loginRequest);
    }

    @PostMapping("/login/authorize")
    public String authorizeRequest(@RequestBody LoginRequest loginRequest){
        return userLoginService.authenticateUser(loginRequest);
    }

    @PostMapping("/incident/create")
    public String createIncident(@RequestBody CreateIncidentRequest incidentDetails){
        return incidentService.createIncident(incidentDetails);
    }

    @PutMapping("/incident/edit")
    public String editIncident(@RequestBody EditIncidentRequest incidentRequest){
        return incidentService.editIncident(incidentRequest);
    }

    @GetMapping("/incident/fetch/{userName}")
    public List<IncidentDetails> fetchIncident(@PathVariable("userName") String username){
        return incidentService.getIncidentForUser(username);
    }
}
