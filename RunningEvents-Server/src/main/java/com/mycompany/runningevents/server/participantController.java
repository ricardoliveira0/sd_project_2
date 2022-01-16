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
    
    @PostMapping(path="/postRegisterParticipant", consumes = "application/json", produces = "application/json")
    public String postController(@RequestBody JSONObject data){
        String output = service.registerNewParticipant(data);
        return output;
    }
}
