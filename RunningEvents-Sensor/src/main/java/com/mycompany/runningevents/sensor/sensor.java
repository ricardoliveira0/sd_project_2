/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.sensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Scanner;
import net.minidev.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ricardo
 */
public class sensor {
    
    private static Integer opt, option;
    private static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static Scanner scanner = new Scanner(System.in);
    private static JSONObject data = new JSONObject();
    
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
    
    public static void main(String[] args) throws IOException, Exception {
        while (true) {
            System.out.println("");
            System.out.println("==============================================");
            System.out.println("==================== MENU ====================");
            System.out.println("1 - Register time read");
            System.out.println("2 - Exit");
            System.out.println("==============================================");
            
            opt = scanner.nextInt();
            
            switch(opt) {
                
                case 1:
                    
                    String fullPos;
                    
                    System.out.println("Submit the event identifier:");
                    String eventID = buffer.readLine();
                    
                    System.out.println("Submit the participant dorsal:");
                    String participantID = buffer.readLine();
                    
                    System.out.println("Submit the position.");
                    System.out.println("1- start. 2- P1. 3- P2. 4- P3. 5- finish:");
                    option = scanner.nextInt();
                    
                    while (option < 1 || option > 5) {
                        System.out.println("Invalid option. Please choose one of the listed options.");
                        System.out.println("1- start. 2- P1. 3- P2. 4- P3. 5- finish:");
                        option = scanner.nextInt();
                    }
                    
                    if(option == 1)
                        fullPos = "start";
                    else if (option == 5)
                        fullPos = "finish";
                    else fullPos = String.valueOf(option);
                    
                    System.out.println("Submit the read year:");
                    Integer readYear = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the read month:");
                    Integer readMonth = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the read day:");
                    Integer readDay = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the read hours:");
                    Integer readHours = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the read minutes:");
                    Integer readMinutes = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the read seconds:");
                    Integer readSeconds = Integer.parseInt(buffer.readLine());
                    
                    System.out.println("Submit the read miliseconds:");
                    Integer readMiliseconds = Integer.parseInt(buffer.readLine());
                    
                    String chipID = eventID.concat("_").concat(participantID);
                    
                    Timestamp tempRead_ts = new Timestamp(readYear-1900, readMonth-1, readDay, readHours, readMinutes, readSeconds, readMiliseconds);
                    String read_ts = tempRead_ts.toString();
                    
                    data.clear();
                    data.put("chipID", chipID);
                    data.put("readTS", read_ts);
                    data.put("positionID", fullPos);
                    
                    String path = "http://localhost:8080/participant/postSensorReadTime";
                    
                    sendPost(path, data);
                    
                    break;
                
                case 2:
                    
                    System.exit(0);
                    
                    break;
                    
                default:
                    
                    System.err.println("Invalid option.");
       
                    break;
                
            }
            
        }
            
    }
        
}
