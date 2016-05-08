package com.example.songezo.assignment6_android.services;

import java.util.Map;

/**
 * Created by Songezo on 2016-05-07.
 *
 * This service is for viewing all the details about the Sponsors
 */
public interface View_Sponsors_Service {
    String viewSponsor(Map<String, String> values, Long id);

    Boolean isSponsorViewed();
    Boolean destroySponsor();
}
