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
    public participantService(participantRepository repository) {
        this.repository = repository;
    }
    
    @Autowired
    public void eventService(eventRepository event_repository) {
        this.event_repository = event_repository;
    }
    
    @GetMapping
    public String getAllParticipantsFromEvent(String data) {
        
        evento = event_repository.findByEventName(data);
        List<participant> participantsList;
        participant tempParticipant;
        String output = "";

        if(evento != null) {
            int eventID = evento.getID();
            System.out.println("[INFO] Event ID found: " + eventID);
            participantsList = repository.findAllByEventID(eventID);
            
            while (!participantsList.isEmpty()) {
                tempParticipant = participantsList.get(0);
                output = output + tempParticipant.getParticipantName() + " [" + tempParticipant.getDorsal() + "] | " + tempParticipant.getParticipantEchelon() + " | " + tempParticipant.getParticipantGender() + "\n";
                participantsList.remove(0);
            }
            
            return output;
        }
        else System.out.println("[ERROR] Event not found.");

        return null;
        
    }

    @GetMapping
    public long getParticipantsAtPos(String data_eventName, String data_pos) {

        evento = event_repository.findByEventName(data_eventName);

        if (evento != null) {
            int eventID = evento.getID();
            System.out.println("[INFO] Event ID found: " + eventID);
            return repository.countByParticipantLocation(data_pos);
        }
        else System.out.println("[ERROR] Event not found.");

        return -1;
        
    }
    
    @GetMapping
    public String getScoreboard(String data_eventName, String data_pos) {

        evento = event_repository.findByEventName(data_eventName);
        List<participant> participantsList;
        participant tempParticipant;
        String output = "";
        
        if (evento != null) {
            int eventID = evento.getID();
            System.out.println("[INFO] Event ID found: " + eventID);
            System.out.println("[INFO] Retrieving scoreboard from " + data_pos);
            if (data_pos.equals("1")) {
                participantsList = repository.findAllByParticipantLocationOrderByPos1TimeAsc(data_pos);
                
                while (!participantsList.isEmpty()) {
                    tempParticipant = participantsList.get(0);
                    output = output + tempParticipant.getParticipantName() + " [" + tempParticipant.getDorsal() + "] | " + tempParticipant.getParticipantP1Time() + "\n";
                    participantsList.remove(0);
                }
                
                return output;
            } 
            else if (data_pos.equals("2")) {
                participantsList = repository.findAllByParticipantLocationOrderByPos2TimeAsc(data_pos);
                
                while (!participantsList.isEmpty()) {
                    tempParticipant = participantsList.get(0);
                    output = output + tempParticipant.getParticipantName() + " [" + tempParticipant.getDorsal() + "] | " + tempParticipant.getParticipantP2Time() + "\n";
                    participantsList.remove(0);
                }
                
                return output;
            }
            else if (data_pos.equals("3")) {
                participantsList = repository.findAllByParticipantLocationOrderByPos3TimeAsc(data_pos);
                
                while (!participantsList.isEmpty()) {
                    tempParticipant = participantsList.get(0);
                    output = output + tempParticipant.getParticipantName() + " [" + tempParticipant.getDorsal() + "] | " + tempParticipant.getParticipantP3Time() + "\n";
                    participantsList.remove(0);
                }
                
                return output;
            }
            else {
                participantsList = repository.findAllByParticipantLocationOrderByFinishTimeAsc(data_pos);
                
                while (!participantsList.isEmpty()) {
                    tempParticipant = participantsList.get(0);
                    output = output + tempParticipant.getParticipantName() + " [" + tempParticipant.getDorsal() + "] | " + tempParticipant.getParticipantFinishTime() + "\n";
                    participantsList.remove(0);
                }
                
                return output; 
            }
            
        }
        else System.out.println("[ERROR] Event not found.");

        return null;
        
    }
    
    @PostMapping
    public String registerNewParticipant(@RequestBody JSONObject data) {
        
        evento = event_repository.findByEventName((String)data.get("participantEvent"));
        
        if(evento != null) {
            int eventID = evento.getID();
            System.out.println("[INFO] Event ID found: " + eventID);
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
    
    @PostMapping
    public String registerNewSensorRead(@RequestBody JSONObject data) {
        
        String temp = (String)data.get("chipID");
        String tempEventID = temp.split("_")[0];
        int indexSplitter = temp.indexOf("_") + 1;
        String tempParticipantID = temp.substring(indexSplitter);
        Integer eventID = Integer.parseInt(tempEventID);
        Integer participantID = Integer.parseInt(tempParticipantID);
        
        String readTime = (String)data.get("readTS");
        Timestamp timerRead = Timestamp.valueOf(readTime);
        
        String pos = (String)data.get("positionID");
        
        evento = event_repository.findByEventID(eventID);
        
        if(evento != null) {
            
            System.out.println("[INFO] Event ID found: " + eventID);
            participante = repository.findByDorsalID(participantID);
            
            if(participante != null && participante.getEvent() == eventID) {
                
                participante.setParticipantLocation(pos);
                
                if(pos.equals("start")) {
                    participante.setParticipantStartTime(timerRead);
                }
                else if(pos.equals("1")) {
                    participante.setParticipantP1Time(timerRead);
                }
                else if(pos.equals("2")){
                    participante.setParticipantP2Time(timerRead);
                }
                else if(pos.equals("3")){
                    participante.setParticipantP3Time(timerRead);
                }
                else if(pos.equals("finish")){
                    participante.setParticipantFinishTime(timerRead);
                }
                
                repository.save(participante);
                
            }
            
            else return "[ERROR] The specified chip does not exist.";
        }
        
        else return "[ERROR] The specified chip does not exist.";
        
        return "";
        
    }
    
}
