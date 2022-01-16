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
@Table(name="event_list")
public class event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventID;
    
    private String eventName;
    private Timestamp eventDate;
    private String shortDate;
    private Integer eventType;
    
    public event(){}
    
    public event(String eventName, Timestamp eventDate, String shortDate, Integer eventType) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.shortDate = shortDate;
        this.eventType = eventType;
    }
    
    public Integer getID() {
        return eventID;
    }
    
    public String getEventName(){
        return eventName;
    }
    
    public void setEventName(String eventName){
        this.eventName = eventName;
    }
    
    public Timestamp getEventDate(){
        return eventDate;
    }
    
    public void setEventDate(Timestamp eventDate){
        this.eventDate = eventDate;
    }
    
    public String getEventShortenDate(){
        return shortDate;
    }
    
    public void setEventShortenDate(String shortDate){
        this.shortDate = shortDate;
    }
    
    public Integer getEventType(){
        return eventType;
    }
    
    public void setEventType(Integer eventType){
        this.eventType = eventType;
        // TO DO: SWITCH
    }
}
