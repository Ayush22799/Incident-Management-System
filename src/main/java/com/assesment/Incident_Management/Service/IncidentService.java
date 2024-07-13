package com.assesment.Incident_Management.Service;

import com.assesment.Incident_Management.Entity.IncidentDetails;
import com.assesment.Incident_Management.Entity.POJO.CreateIncidentRequest;
import com.assesment.Incident_Management.Entity.POJO.EditIncidentRequest;

import java.util.List;

public interface IncidentService {
    String createIncident(CreateIncidentRequest incidentDetails);
    String editIncident(EditIncidentRequest incidentDetails);
    List<IncidentDetails> getIncidentForUser(String username);
}
