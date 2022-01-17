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
    
    private Integer eventID;
    private String participantName;
    private String participantEchelon;
    private String participantGender;
    private String participantLocation;
    private Timestamp startTime;
    private Timestamp pos1Time;
    private Timestamp pos2Time;
    private Timestamp pos3Time;
    private Timestamp finishTime;
    
    public participant(){}
    
    public participant(Integer eventID, String participantName,
            String participantEchelon, String participantGender, 
            String participantLocation, Timestamp startTime, Timestamp P1Time, 
            Timestamp P2Time, Timestamp P3Time, Timestamp finishTime) {
        this.eventID = eventID;
        this.participantName = participantName;
        this.participantEchelon = participantEchelon;
        this.participantGender = participantGender;
        this.participantLocation = participantLocation;
        this.startTime = startTime;
        this.pos1Time = P1Time;
        this.pos2Time = P2Time;
        this.pos3Time = P3Time;
        this.finishTime = finishTime;
    }
    
    public Integer getDorsal() {
        return dorsalID;
    }
    
    public Integer getEvent() {
        return eventID;
    }
    
    public String getParticipantName(){
        return participantName;
    }
    
    public void setParticipantName(String participantName){
        this.participantName = participantName;
    }
    
    public String getParticipantEchelon(){
        return participantEchelon;
    }
    
    public void setParticipantEchelon(String participantEchelon){
        this.participantEchelon = participantEchelon;
    }
    
    public String getParticipantGender(){
        return participantGender;
    }
    
    public void setParticipantGender(String participantGender){
        this.participantGender = participantGender;
    }
    
    public String getParticipantLocation(){
        return participantLocation;
    }
    
    public void setParticipantLocation(String participantLocation){
        this.participantLocation = participantLocation;
    }
    
    public Timestamp getParticipantStartTime(){
        return startTime;
    }
    
    public void setParticipantStartTime(Timestamp startTime){
        this.startTime = startTime;
    }

    public Timestamp getParticipantP1Time(){
        return pos1Time;
    }
    
    public void setParticipantP1Time(Timestamp P1Time){
        this.pos1Time = P1Time;
    }

    public Timestamp getParticipantP2Time(){
        return pos2Time;
    }
    
    public void setParticipantP2Time(Timestamp P2Time){
        this.pos2Time = P2Time;
    }

    public Timestamp getParticipantP3Time(){
        return pos3Time;
    }
    
    public void setParticipantP3Time(Timestamp P3Time){
        this.pos3Time = P3Time;
    }

    public Timestamp getParticipantFinishTime(){
        return finishTime;
    }
    
    public void setParticipantFinishTime(Timestamp finishTime){
        this.finishTime = finishTime;
    }
    
}
