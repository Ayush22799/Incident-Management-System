package com.assesment.Incident_Management.Service.Impl;

import com.assesment.Incident_Management.Entity.IncidentDetails;
import com.assesment.Incident_Management.Entity.POJO.CreateIncidentRequest;
import com.assesment.Incident_Management.Entity.POJO.EditIncidentRequest;
import com.assesment.Incident_Management.Entity.UserDetails;
import com.assesment.Incident_Management.Exception.DetailsNotFoundException;
import com.assesment.Incident_Management.Exception.InvalidValueProvidedException;
import com.assesment.Incident_Management.Repository.IncidentDetailsRepo;
import com.assesment.Incident_Management.Repository.UserDetailsRepo;
import com.assesment.Incident_Management.Service.IncidentService;
import com.assesment.Incident_Management.Utility.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    IncidentDetailsRepo repo;

    @Autowired
    UserDetailsRepo userRepo;

    @Override
    public String createIncident(CreateIncidentRequest incidentDetails) {
        log.info("Incident creation has started!");
        Optional<UserDetails> user = userRepo.findById(incidentDetails.getUsername());
        if(!Utils.validateOrg(incidentDetails.getOrganization())){
            log.error("Provided organization is invalid");
            throw new InvalidValueProvidedException("Please enter valid organization");
        }
        if(!Utils.validatePriority(incidentDetails.getIncidentPriority())){
            log.error("Provided priority is invalid");
            throw new InvalidValueProvidedException("Please enter valid priority");
        }
        if(!Utils.validateStatus(incidentDetails.getIncidentStatus())){
            log.error("Provided status is invalid");
            throw new InvalidValueProvidedException("Please enter valid status");
        }
        if(user.isEmpty()){
            log.error("Provided user is invalid");
            throw new InvalidValueProvidedException("Provided user is invalid");
        }

        IncidentDetails details = new IncidentDetails(
                Utils.generateIncidentId(),
                incidentDetails.getUsername(),
                incidentDetails.getOrganization(),
                incidentDetails.getIncidentDetails(),
                incidentDetails.getIncidentPriority(),
                incidentDetails.getIncidentStatus(),
                Utils.getCurrentDateTimeAsString(),
                Utils.getCurrentDateTimeAsString()
        );
        repo.save(details);
        log.info("Incident successfully created");
        return "Incident created successfully";
    }

    @Override
    public String editIncident(EditIncidentRequest incidentDetails) {
        log.info("Incident edit has started!");
        Optional<IncidentDetails> incident = repo.findById(incidentDetails.getIncidentId());
        if(incident.isEmpty()){
            log.error("Provided organization is invalid");
            throw new DetailsNotFoundException("No incident exist for given incident id");
        }
        if(!Utils.validateOrg(incidentDetails.getOrganization())){
            log.error("Provided organization is invalid");
            throw new InvalidValueProvidedException("Please enter valid organization");
        }
        if(!Utils.validatePriority(incidentDetails.getIncidentPriority())){
            log.error("Provided priority is invalid");
            throw new InvalidValueProvidedException("Please enter valid priority");
        }
        if(!Utils.validateStatus(incidentDetails.getIncidentStatus())){
            log.error("Provided status is invalid");
            throw new InvalidValueProvidedException("Please enter valid status");
        }
        if(incidentDetails.getUsername().equalsIgnoreCase(incident.get().getUserName())){
            log.error("Provided User is invalid");
            throw new InvalidValueProvidedException("User provided in request is invalid");
        }
        IncidentDetails newIncident = new IncidentDetails(
                incident.get().getIncidentId(),
                incident.get().getUserName(),
                incidentDetails.getOrganization(),
                incidentDetails.getIncidentDetails(),
                incidentDetails.getIncidentPriority(),
                incidentDetails.getIncidentStatus(),
                incident.get().getCreatedDate(),
                Utils.getCurrentDateTimeAsString()
        );
        repo.save(newIncident);
        log.info("Incident updated created");
        return "Incident updated successfully";
    }

    @Override
    public List<IncidentDetails> getIncidentForUser(String username) {
        log.info("Fetching Incident list for user!");
        Optional<List<IncidentDetails>> incidentList = repo.findAllIncidentDetailsByUserId(username);
        if(incidentList.isEmpty()){
            log.error("No incident available for user");
            throw new DetailsNotFoundException("No incident for user");
        }
        log.info("Incident list fetched successfully!");
        return incidentList.get();
    }
}
