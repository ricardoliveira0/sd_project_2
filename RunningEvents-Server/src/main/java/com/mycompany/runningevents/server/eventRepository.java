/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.server;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ricardo
 */

@Repository
public interface eventRepository extends JpaRepository<event, Integer> {
    event findByEventID(Integer eventID);
    event findByEventName(String eventName);
    List<event> findAllByShortDate(String shortDate);
}
