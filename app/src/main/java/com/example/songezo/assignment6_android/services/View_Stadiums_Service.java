package com.example.songezo.assignment6_android.services;

import java.util.Map;

/**
 * Created by Songezo on 2016-05-08.
 *
 * This service is for viewing all the details about the Stadiums
 */
public interface View_Stadiums_Service {
    String viewStadium(Map<String, String> values, Long id);

    Boolean isStadiumViewed();
    Boolean destroyStadium();

}
