/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.client;

import java.io.*;
import java.sql.Timestamp;
import java.util.Scanner;
import net.minidev.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ricardo
 */

public class client {
    
    private static int opt, option;
    private static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static Scanner scanner = new Scanner(System.in);
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static String path = "";
    private static JSONObject data = new JSONObject();
    
    public static void sendGet(String path) throws Exception {

        HttpGet request = new HttpGet(path);
        
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void sendPost(String path, JSONObject data) throws Exception {

        HttpPost post = new HttpPost(path);
        
        StringWriter out = new StringWriter();
        data.writeJSONString(out);

        String sendObj = out.toString();

        StringEntity entity = new StringEntity(sendObj, "UTF-8");

        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    
    private static String echelonInterpreter(int echelon) {
        
        String fullEchelon = null;
        
        switch(echelon) {
            
            case 1:
                
                fullEchelon = "Juniors";
                
                break;
                
            case 2:
                
                fullEchelon = "Seniors";
                
                break;
                
            case 3:
                
                fullEchelon = "Veterans 35";
                
                break;
                
            case 4:
                
                fullEchelon = "Veterans 40";
                
                break;
                
            case 5:
                
                fullEchelon = "Veterans 45";
                
                break;
                
            case 6:
                
                fullEchelon = "Veterans 50";
                
                break;
                
            case 7:
                
                fullEchelon = "Veterans 55";
                
                break;
                
            case 8:
                
                fullEchelon = "Veterans 60";
                
                break;
                
            case 9:
                
                fullEchelon = "Veterans 65+";
                
                break;  
                
        }
        
        return fullEchelon;
        
    }
    
    private static String genderInterpreter(int gender) {
        
        String fullGender = null;
        
        switch(gender) {
            
            case 1:
                
                fullGender = "Male";
                
                break;
                
            case 2:
                
                fullGender = "Female";
                
                break;
                
        }
        
        return fullGender;
        
    }

    private static String typeInterpreter(int type) {
        
        String fullType = null;
        
        switch(type) {
            
            case 1:
                
                fullType = "Track";
                
                break;
                
            case 2:
                
                fullType = "Road";
                
                break;
                
            case 3:
                
                fullType = "Trails";
                
                break;
                
        }
        
        return fullType;
        
    }
    
    public static void main(String[] args) throws IOException, Exception {

        while(true) {
            
            System.out.println("");
            System.out.println("==============================================");
            System.out.println("==================== MENU ====================");
            System.out.println("1. Register new event");
            System.out.println("2. Get all events list for a day");
            System.out.println("3. Register new participant");
            System.out.println("4. List participants from an event");
            System.out.println("5. Get number of participants at position");
            System.out.println("6. Get general scoreboard");
            System.out.println("7. Exit");
            System.out.println("==============================================");
            
            opt = scanner.nextInt();
            
            switch(opt) {
                
                case 1: // Register new event
                    
                    System.out.println("Submit the event name:");
                    String eventName = buffer.readLine();
                    
                    System.out.println("Submit the event year:");
                    Integer eventYear = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the event month:");
                    Integer eventMonth = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the event day:");
                    Integer eventDay = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the event hours:");
                    Integer eventHours = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the event minutes:");
                    Integer eventMinutes = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the event seconds:");
                    Integer eventSeconds = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the event miliseconds:");
                    Integer eventMiliseconds = Integer.parseInt(buffer.readLine());
                    
                    Timestamp tempEvent_ts = new Timestamp(eventYear-1900, eventMonth-1, eventDay, eventHours, eventMinutes, eventSeconds, eventMiliseconds);
                    String event_ts = tempEvent_ts.toString();
                    String shorten_event_ts = event_ts.split(" ")[0];
                    
                    System.out.println("Submit the event type.");
                    System.out.println("1- Track. 2- Road. 3- Trails:");
                    option = scanner.nextInt();
                    
                    while (option < 1 || option > 3) {
                        System.out.println("Invalid option. Please choose one of the listed options.");
                        System.out.println("1- Track. 2- Road. 3- Trails:");
                        option = scanner.nextInt();
                    }
                    
                    String eventType = typeInterpreter(option);
                    
                    data.clear();
                    data.put("eventName", eventName);
                    data.put("eventDate", event_ts);
                    data.put("eventShortenDate", shorten_event_ts);
                    data.put("eventType", eventType);
                    
                    path = "http://localhost:8080/event/postRegisterEvent";
                    
                    sendPost(path, data);
                    
                    break;
                    
                case 2: // Get events at day
                    
                    System.out.println("Submit the event year:");
                    Integer searchYear = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the event month:");
                    Integer searchMonth = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the event day:");
                    Integer searchDay = Integer.parseInt(buffer.readLine());
                    
                    Timestamp tempSearch_ts = new Timestamp(searchYear-1900, searchMonth-1, searchDay, 00, 00, 00, 000);
                    String search_ts = tempSearch_ts.toString().split(" ")[0];
                    
                    path = "http://localhost:8080/event/getEvents?eventShortenDate=" + search_ts;
                 
                    sendGet(path);
                    
                    break;

                case 3: // Register a new participant
                    
                    System.out.println("Submit the participant name:");
                    String participantName = buffer.readLine();
                    
                    System.out.println("Submit the gender.");
                    System.out.println("1- Male. 2- Female:");
                    option = scanner.nextInt();
                    
                    while (option < 1 || option > 2){
                        System.out.println("Invalid option. Please choose one of the listed options.");
                        System.out.println("1- Male. 2- Female:");
                        option = scanner.nextInt();
                    }
                    
                    String participantGender = genderInterpreter(option);
                    
                    System.out.println("Submit the echelon.");
                    System.out.println("1- Juniors. 2- Seniors. 3- Veterans 35. 4- Veterans 40. 5- Veterans 45. 6- Veterans 50. 7- Veterans 55. 8- Veterans 60. 9- Veterans +65:");
                    option = scanner.nextInt();
                    
                    while (option < 1 || option > 9) {
                        System.out.println("Invalid option. Please choose one of the listed options.");
                        System.out.println("1- Juniors. 2- Seniors. 3- Veterans 35. 4- Veterans 40. 5- Veterans 45. 6- Veterans 50. 7- Veterans 55. 8- Veterans 60. 9- Veterans +65:");
                        option = scanner.nextInt();
                    }
                    
                    String participantEchelon = echelonInterpreter(option);
                    
                    
                    System.out.println("Submit event name to register this participant:");
                    String eventNameToRegisterParticipant = buffer.readLine();
                    
                    data.clear();
                    data.put("participantName", participantName);
                    data.put("participantGender", participantGender);
                    data.put("participantEchelon", participantEchelon);
                    data.put("participantEvent", eventNameToRegisterParticipant);
                    
                    path = "http://localhost:8080/participant/postRegisterParticipant";
                    
                    sendPost(path, data);

                    break;
                    
                case 4: // List participants -> event
                    
                    System.out.println("Submit the event name:");
                    String eventSearchName = buffer.readLine();
                    
                    path = "http://localhost:8080/participant/getEventParticipant?eventSearchName=" + eventSearchName;
                    
                    sendGet(path);

                    break;
                    
                case 5: // List participants -> Position
                    
                    System.out.println("Submit the event name:");
                    String eventPosName = buffer.readLine();
                    
                    System.out.println("Submit the position.");
                    System.out.println("1- P1. 2- P2. 3- P3:");
                    option = scanner.nextInt();
                    
                    while (option < 1 || option > 3) {
                        System.out.println("Invalid option. Please choose one of the listed options.");
                        System.out.println("1- P1. 2- P2. 3- P3:");
                        option = scanner.nextInt();
                    }
                        String fullOption = String.valueOf(option);
                    
                    path = "http://localhost:8080/participant/getParticipantsAtPos?eventPosName=" + eventPosName + "&pos=" + fullOption;
                    
                    sendGet(path);

                    break;
                
                case 6: // Get scoreboard
                    
                    System.out.println("Submit the event name:");
                    String eventScoreboard = buffer.readLine();
                    
                    System.out.println("Submit the position.");
                    System.out.println("1- P1. 2- P2. 3- P3. 4- Final:");
                    option = scanner.nextInt();
                    String fullPos = "";
                    
                    while (option < 1 || option > 4) {
                        System.out.println("Invalid option. Please choose one of the listed options.");
                        System.out.println("1- P1. 2- P2. 3- P3. 4- finish:");
                        option = scanner.nextInt();
                    }
                    if(option == 4)
                        fullPos = "finish";
                    else fullPos = String.valueOf(option);
                    
                    path = "http://localhost:8080/participant/getScoreboard?eventSb=" + eventScoreboard + "&pos=" + fullPos;
                    
                    sendGet(path);

                    break;
                
                case 7: //Exit
                    
                    System.exit(0);
                    
                    break;
                    
                default: 
                    System.err.println("Invalid option.");
       
                    break;
                    
            }
            
        }
        
    }
    
}
