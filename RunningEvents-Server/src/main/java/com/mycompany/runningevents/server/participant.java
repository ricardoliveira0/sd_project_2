/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.server;

import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author ricardo
 */

@Entity
@Table(name="participant_list")
public class participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dorsalID;
    
    private String eventID;
    private String participantName;
    private String participantEchelon;
    private String participantGender;
    private String participantLocation;
    private Timestamp startTime;
    private Timestamp P1Time;
    private Timestamp P2Time;
    private Timestamp P3Time;
    private Timestamp finishTime;
    
    public participant(){}
    
    public participant(String eventID, String participantName,
            String participantEchelon, String participantGender, 
            String participantLocation, Timestamp startTime, Timestamp P1Time, 
            Timestamp P2Time, Timestamp P3Time, Timestamp finishTime) {
        this.eventID = eventID;
        this.participantName = participantName;
        this.participantEchelon = participantEchelon;
        this.participantGender = participantGender;
        this.participantLocation = participantLocation;
        this.startTime = startTime;
        this.P1Time = P1Time;
        this.P2Time = P2Time;
        this.P3Time = P3Time;
        this.finishTime = finishTime;
    }
    
    public Integer getDorsal() {
        return dorsalID;
    }
    
    public String getParticipantName(){
        return participantName;
    }
    
    public void setParticipantName(String participantName){
        this.participantName = participantName;
    }
    
    
}
