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
public class participantService {
    
    private final participantRepository repository;
    private eventRepository event_repository;
    private participant participante;
    private event evento;
    
    @Autowired
    public participantService(participantRepository repository){
        this.repository = repository;
    }
    
    @Autowired
    public void eventService(eventRepository event_repository){
        this.event_repository = event_repository;
    }
    
    @PostMapping
    public String registerNewParticipant(@RequestBody JSONObject data){
        
        evento = event_repository.findByEventName((String)data.get("participantEvent"));
        
        if(evento != null){
            int eventID = evento.getID();
            participante = new participant(eventID, (String)data.get("participantName"),
                    (String)data.get("participantEchelon"), (String)data.get("participantGender"), 
                    null, null, null, null, null, null);
            repository.save(participante);
        }
        else return "[ERROR] The specified event does not exist.";
        
        return "Successfully registered " + (String)data.get("participantName") 
                + " with dorsal [" + participante.getDorsal() + "] at event '"
                + (String)data.get("participantEvent") + "'.";
        
    }
}
