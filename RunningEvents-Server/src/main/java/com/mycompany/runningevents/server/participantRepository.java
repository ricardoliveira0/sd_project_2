/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.runningevents.server;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ricardo
 */

@Repository
public interface participantRepository extends JpaRepository<participant, Integer> {
    participant findByDorsalID(Integer participantID);
    List<participant> findAllByEventID(Integer eventID);
    long countByParticipantLocation(String pos);
    List<participant> findAllByParticipantLocationOrderByPos1TimeAsc(String data_pos);
    List<participant> findAllByParticipantLocationOrderByPos2TimeAsc(String data_pos);
    List<participant> findAllByParticipantLocationOrderByPos3TimeAsc(String data_pos);
    List<participant> findAllByParticipantLocationOrderByFinishTimeAsc(String data_pos);
}
