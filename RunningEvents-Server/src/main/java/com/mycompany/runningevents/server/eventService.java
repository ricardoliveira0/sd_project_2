/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.server;

import java.sql.Timestamp;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author ricardo
 */

@Service
public class eventService {
    
    private final eventRepository repository;
    private event evento;
    
    @Autowired
    public eventService(eventRepository repository){
        this.repository = repository;
    }
    
    @GetMapping
    public String getDayEvents(String data){
        
        List<event> eventList;
        event tempEvent;
        String output="";
       
        eventList = repository.findAllByShortDate(data);
        
        while (!eventList.isEmpty()) {
            tempEvent = eventList.get(0);
            output = output + tempEvent.getEventName() + " | " + tempEvent.getEventShortenDate() + "\n";
            eventList.remove(0);
        }
        
        return output;
    }
    
    @PostMapping
    public String registerNewEvent(@RequestBody JSONObject data){
        evento = repository.findByEventName((String)data.get("eventName"));
        if(evento == null){
            String tempDate = (String)data.get("eventDate");
            Timestamp eventDate = Timestamp.valueOf(tempDate);
            evento = new event((String)data.get("eventName"), eventDate, (String)data.get("eventShortenDate"),(Integer)data.get("eventType"));
            repository.save(evento);
            return "[INFO] Successfully registered event '" + (String)data.get("eventName") + "'.";
        }
        else return "[ERROR] An event with that name already exists.";
    }
}
