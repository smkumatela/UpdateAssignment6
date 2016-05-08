package com.example.songezo.assignment6_android.services;

import java.util.Map;

/**
 * Created by Songezo on 2016-05-08.
 */
public interface View_LogStandings_Services {
    String viewLog(Map<String, Integer> values, Long id);

    Boolean isLogViewed();
    Boolean destroyLog();
}
