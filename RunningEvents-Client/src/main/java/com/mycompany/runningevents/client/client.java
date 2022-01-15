/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.client;

import java.io.*;
import java.sql.Timestamp;
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
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    
    private void sendGet() throws Exception {

        HttpGet request = new HttpGet("https://www.google.com/search?q=mkyong");

        // add request headers
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }

    private static void sendPost(String path, JSONObject data) throws Exception {

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
                    
                    Timestamp timestamp = new Timestamp(eventYear, eventMonth, eventDay, eventHours, eventMinutes, eventSeconds, eventMiliseconds);

                    System.out.println("Submit the event type.");
                    System.out.println("1- Track. 2- Road. 3- Trails");
                    int eventType = scanner.nextInt();
                    
                    String path = "https://localhost:8080/event/post";
                    
                    JSONObject data = new JSONObject();
                    data.put("eventName", eventName);
                    data.put("eventDate", timestamp);
                    data.put("eventType", eventType);
                    
                    sendPost(path, data);
            }
            
        }
        
    }
    
}
