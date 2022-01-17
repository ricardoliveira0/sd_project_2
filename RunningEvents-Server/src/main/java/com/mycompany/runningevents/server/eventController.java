/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.server;

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
@RequestMapping(path="/event")
public class eventController {
    
    private final eventService service;
    
    @Autowired
    public eventController(eventService service){
        this.service = service;
    }
    
    @GetMapping(path="/getEvents")
    public String getController(@RequestParam("eventShortenDate") String data){
        System.out.println("[INFO] Received data as param: " + data);
        return service.getDayEvents(data);
    }
    
    
    @PostMapping(path="/postRegisterEvent", consumes = "application/json", produces = "application/json")
    public String postController(@RequestBody JSONObject data){
        System.out.println("[INFO] Received data as JSONObject: " + data);
        String output = service.registerNewEvent(data);
        return output;
    }
}
