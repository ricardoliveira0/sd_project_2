/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.server;

import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ricardo
 */

@RestController
@RequestMapping(path="/participant")
public class participantController {
    
    private final participantService service;
    
    @Autowired
    public participantController(participantService service){
        this.service = service;
    }
    
    @GetMapping(path="/getEventParticipant")
    public List<participant> getEPController(@RequestParam("eventSearchName") String data){
        System.out.println("[INFO] Received data as param: " + data);
        return service.getAllParticipantsFromEvent(data);
    }

    @GetMapping(path="/getParticipantsAtPos")
    public long getPAPController(@RequestParam("eventPosName") String data_eventName, @RequestParam("pos") Integer data_pos) {
        System.out.println("[INFO] Received data as param: " + data_eventName + " | " + data_pos);
        return service.getParticipantsAtPos(data_eventName, data_pos);
    }
    
    @GetMapping(path="/getScoreboard")
    public List<participant> getSBController(@RequestParam("eventSb") String data_eventName, @RequestParam("pos") String data_pos) {
        System.out.println("[INFO] Received data as param: " + data_eventName + " | " + data_pos);
        return service.getScoreboard(data_eventName, data_pos);
    }
    
    @PostMapping(path="/postRegisterParticipant", consumes = "application/json", produces = "application/json")
    public String postController(@RequestBody JSONObject data){
        System.out.println("[INFO] Received data as JSONObject: " + data);
        String output = service.registerNewParticipant(data);
        return output;
    }
}
