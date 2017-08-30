/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.schoolOrganizer.web;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.joda.time.DateTime;


/**
 *
 * @author Samir Rehmtulla
 */

public class Task {
    private DateTime dateTimeAtCreation;
    private DateTime dateTimeWhenStarted;
    private long activeWorkTime;
    private int priority;
    private int percentComplete;
    private String name;
    private Status status;
    
    public Task() {
        this.dateTimeAtCreation = DateTime.now();
    }
    
    public Task(Optional<String> name, Optional<Integer> priority, Optional<Status> status) {
        this.priority = priority.get();
        this.name = name.get();
        this.status = status.get();
        this.dateTimeAtCreation = DateTime.now();
    }
    
    public void startTask() {
        this.dateTimeWhenStarted = DateTime.now();
    }
    
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public List<Long> getTimeSinceStarting() {
        long secondsSince = (DateTime.now().getMillis() - this.dateTimeWhenStarted.getMillis())/1000;
        return convertMillisToDateList(secondsSince);
    }
    
    public List<Long> getTimeSinceCreation() {
        long secondsSince = (DateTime.now().getMillis() - this.dateTimeAtCreation.getMillis())/1000;
        return convertMillisToDateList(secondsSince);
    }
    
    public String getTimeSinceCreationString() {
        List<Long> timeList = getTimeSinceCreation();
        return convertTimeListToString(timeList);
    }
    
    private List<Long> convertMillisToDateList(long secondsSince) {
        long days = secondsSince/604800;
        secondsSince = secondsSince%604800;
        long hours = secondsSince/3600;
        secondsSince = secondsSince%3600;
        long minutes = secondsSince/60;
        List<Long> timeSince = new LinkedList<>();
        timeSince.add(days);
        timeSince.add(hours);
        timeSince.add(minutes);
        return timeSince;
    }
    
    private String convertTimeListToString(List<Long> timeList) {
        return "Task created " + timeList.get(0) + " days " + timeList.get(1) + " hours " + timeList.get(2) + " minutes ago";
    }
    
    public long workOnTask() {
        return DateTime.now().getMillis();
    }
    
    public void finishWorkOnTask(long startingTime) {
        this.activeWorkTime += DateTime.now().getMillis() - startingTime;
    }
    
    public List<Long> getActiveWorkTime() {
        return convertMillisToDateList(this.activeWorkTime);
    }
    
    public String getName() {
        return this.name;
    }
    
}

