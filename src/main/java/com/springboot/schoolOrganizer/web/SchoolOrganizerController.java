package com.springboot.schoolOrganizer.web;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SchoolOrganizerController {
    
    private List<Status> statuses;
    
    @Autowired
    private SchoolOrganizerFacade schoolOrganizerFacade;
    
    public SchoolOrganizerController() {
        statuses = new LinkedList<>();
    }
    
    @RequestMapping("/")
    public String reloadHomePage(Model model) {
        model.addAttribute("Statuses", this.getStatuses());
        return "dragAndDrop";
    }
    
    @RequestMapping("/addStatus")
    public String addNewStatus(@RequestParam String statusName)  {
        Status newStatus = schoolOrganizerFacade.createStatus(statusName);
        this.statuses.add(newStatus);
        schoolOrganizerFacade.serializeStatuses(this.getStatuses());
        return "hello";
    }
    
    @RequestMapping("/addTask")
    public String addTask(@RequestParam String taskName, @RequestParam int priority, @RequestParam String statusName) throws MalformedURLException, IOException {
        Status status = schoolOrganizerFacade.getStatusFromName(this.getStatuses(), statusName);
        Task newTask = schoolOrganizerFacade.createTask(taskName, priority, status);
        schoolOrganizerFacade.addTaskToStatus(newTask, status);
        schoolOrganizerFacade.serializeStatuses(this.getStatuses());
        return "hello";
    }
    
    @RequestMapping("/moveTask")
    public String moveToStatus(@RequestParam String taskName, @RequestParam String destinationStatusName) {
        Task task = schoolOrganizerFacade.getTaskFromName(this.getStatuses(), taskName);
        Status destinationStatus = schoolOrganizerFacade.getStatusFromName(this.getStatuses(), destinationStatusName);
        Status sourceStatus = task.getStatus();
        schoolOrganizerFacade.addTaskToStatus(task, destinationStatus);
        schoolOrganizerFacade.removeTaskFromStatus(task, sourceStatus);
        task.setStatus(destinationStatus);
        schoolOrganizerFacade.serializeStatuses(this.getStatuses());
        return "hello";
    }
    
    @RequestMapping("/removeTask")
    public String removeTask(@RequestParam String taskName) {
        Task task = schoolOrganizerFacade.getTaskFromName(this.getStatuses(), taskName);
        Status status = task.getStatus();
        schoolOrganizerFacade.removeTaskFromStatus(task, status);
        schoolOrganizerFacade.serializeStatuses(this.getStatuses());
        return "hello";
    }
    
    @RequestMapping("/clearStatus")
    public String clearStatus(@RequestParam String statusName) {
        Status status = schoolOrganizerFacade.getStatusFromName(this.getStatuses(), statusName);
        status.getTasks().clear();
        schoolOrganizerFacade.serializeStatuses(this.getStatuses());
        return "hello";
    }
    
    @RequestMapping("/load")
    public String readInStatuses() {
        this.statuses = schoolOrganizerFacade.readStatuses();
        return "hello";
    }
    
    public List<Status> getStatuses() {
        return this.statuses;
    }
}
