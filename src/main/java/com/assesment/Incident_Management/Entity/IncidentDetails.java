package com.assesment.Incident_Management.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "incident_detail")
public class IncidentDetails {
    @Id
    @Column(name = "incident_id")
    private String incidentId;
    @Column(name = "username")
    private String userName;
    @Column(name = "organization")
    private String organization;
    @Column(name = "incident_details")
    private String incidentDetails;
    @Column(name = "incident_priority")
    private String incidentPriority;
    @Column(name = "incident_status")
    private String incidentStatus;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "updated_date")
    private String updatedDate;

    public IncidentDetails(String incidentDetails, String incidentPriority, String incidentStatus){
       this.incidentDetails = incidentDetails;
       this.incidentPriority = incidentPriority;
       this.incidentStatus = incidentStatus;
    }
}
