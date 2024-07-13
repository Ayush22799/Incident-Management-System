package com.assesment.Incident_Management.Repository;

import com.assesment.Incident_Management.Entity.IncidentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentDetailsRepo extends JpaRepository<IncidentDetails, String> {
    @Query(value = "select * from incident_detail i where i.username = ?1", nativeQuery=true)
    Optional<List<IncidentDetails>> findAllIncidentDetailsByUserId(String username);
}
