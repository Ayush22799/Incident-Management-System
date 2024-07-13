package com.assesment.Incident_Management.Entity.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditIncidentRequest {
    private String incidentId;
    private String username;
    private String organization;
    private String incidentDetails;
    private String incidentPriority;
    private String incidentStatus;
}
