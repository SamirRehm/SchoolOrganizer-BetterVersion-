/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.schoolOrganizer.web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;

/*
 *
 * @author Samir Rehmtulla
 */
public class SchoolOrganizerFacade {

    public SchoolOrganizerFacade() {

    }

    public Status createStatus(String statusName) {
        return new Status(statusName);
    }

    public Task createTask(String taskName, int priority, Status status) {
        return new Task(Optional.of(taskName), Optional.of(priority), Optional.of(status));
    }

    public void addTaskToStatus(Task newTask, Status status) {
        status.addTask(newTask);
    }

    public void removeTaskFromStatus(Task task, Status status) {
        int index = status.getTasks().indexOf(task);
        status.getTasks().remove(index);
    }

    public Status getStatusFromName(List<Status> statuses, String statusName) {
        for (Status status : statuses) {
            if (status.getName().equalsIgnoreCase(statusName)) {
                return status;
            }
        }
        return null;
    }

    public Task getTaskFromName(List<Status> statuses, String taskName) {
        for (Status status : statuses) {
            for (Task task : status.getTasks()) {
                if (task.getName().equalsIgnoreCase(taskName)) {
                    return task;
                }
            }
        }
        return null;
    }

    public void serializeStatuses(List<Status> statuses) {
        try {
            FileOutputStream fileOut = new FileOutputStream("school.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(statuses);
            out.close();
            fileOut.close();
        } catch (Exception e) {
        }
    }
    
    public List<Status> readStatuses() {
        try {
            FileInputStream streamIn = new FileInputStream("school.ser");
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            List<Status> readCase = (List<Status>) objectinputstream.readObject();
            return readCase;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
