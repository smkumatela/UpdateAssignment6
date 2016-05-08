package com.example.songezo.assignment6_android.services;

import java.util.Map;

/**
 * Created by Songezo on 2016-05-07.
 *
 * This service is for viewing all the details about the Teams
 */
public interface View_Team_Service {
    String viewTeam(Map<String, String> values, Long id);

    Boolean isTeamViewed();
    Boolean destroyTeam();
}
