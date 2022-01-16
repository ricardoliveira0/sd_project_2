/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.client;

import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import net.minidev.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
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
    
    private static int opt, read;
    private static byte[] b = new byte[128];
    private static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static Scanner scanner = new Scanner(System.in);
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static String path = "";
    private static JSONObject data = new JSONObject();
    
    public static void sendGet(String path) throws Exception {

        HttpGet request = new HttpGet(path);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            System.out.println(entity);

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

            //System.out.println(EntityUtils.toString(response.getEntity()));
        }

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
            System.out.println("5. Set participant trial time");
            System.out.println("6. Get general scoreboard");
            System.out.println("7. Get PONTO INTERMÉDIO");
            System.out.println("8. Exit");
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
                    System.out.println("1- Track. 2- Road. 3- Trails");
                    int eventType = scanner.nextInt();
                    
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
                    System.out.println("ts: " + search_ts);
                    
                    path = "http://localhost:8080/event/getEvents?eventShortenDate=" + search_ts;
                    System.out.println("link: " + path);
                 
                    sendGet(path);
                    
                    break;

                    
                case 3: // Register a new participant
                    System.out.println("Submit the participant name:");
                    String participantName = buffer.readLine();

                    System.out.println("Submit the gender.");
                    System.out.println("1- Male. 2- Female:");
                    int participantGender = scanner.nextInt();

                    System.out.println("Submit the echelon.");
                    System.out.println("1- Juniors. 2- Seniors. 3- Veterans 35. 4- Veterans 40. 5- Veterans 45. 6- Veterans 50. 7- Veterans 55. 8- Veterans 60. 9- Veterans +65:");
                    int participantEchelon = scanner.nextInt();

                    System.out.println("Submit event name to register this participant:");
                    String eventNameToRegisterParticipant = buffer.readLine();
                    
                    path = "http://localhost:8080/event/postRegisterEvent";
                    
                    sendPost(path, data);

                    break;
            }
            
        }
        
    }
    
}
