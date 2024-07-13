package com.assesment.Incident_Management.Utility;

import com.assesment.Incident_Management.Entity.UserDetails;
import com.assesment.Incident_Management.Exception.DetailsNotFoundException;
import com.assesment.Incident_Management.Repository.UserDetailsRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

public class Utils {

    UserDetailsRepo user;

    /* Auto-generate Incident ID and ensure that the format for the Incident
ID should be the following format- RMG + Random 5-digit number+
Current year (2022) e.g. RMG345712022 */
    public static String generateIncidentId() {
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000); // Generates a random 5-digit number
        int currentYear = java.time.Year.now().getValue();
        return "RMG" + randomNumber + currentYear;
    }

    public static boolean validateOrg(String org){
        return org.equalsIgnoreCase("enterprise") || org.equalsIgnoreCase("government");
    }

    public static boolean validatePriority(String priority){
        return priority.equalsIgnoreCase("high") || priority.equalsIgnoreCase("medium") || priority.equalsIgnoreCase("low");
    }

    public static boolean validateStatus(String status){
        return status.equalsIgnoreCase("open") || status.equalsIgnoreCase("in progress") || status.equalsIgnoreCase("low");
    }
    public static String getCurrentDateTimeAsString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    public boolean validateUser(String username){
        Optional<UserDetails> userExists = user.findById(username);
        if(userExists.isEmpty()){
            throw new DetailsNotFoundException("No details found for user");
        }
        return true;
    }
}
