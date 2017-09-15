/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.schoolOrganizer.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Samir Rehmtulla
 */
public class Status implements Serializable {
    private String name;
    private List<Task> tasks;
    
    public Status() {
        this.tasks = new LinkedList<>();
    }
    
    public Status(String statusName) {
        this.name = statusName;
        this.tasks = new LinkedList<>();
    }
    
    public List<Task> getTasks() {
        return this.tasks;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("HOLA" + this.getTasks().get(0).getName());
        System.out.println("YOU ADDED THE TASK");
    }
}
