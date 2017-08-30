package com.springboot.schoolOrganizer.web;

import java.io.IOException;
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
    public String addStatus(Model model, @RequestParam String statusName)  {
        Status newStatus = schoolOrganizerFacade.createStatus(statusName);
        this.statuses.add(newStatus);
        return "hello";
    }
    
    @RequestMapping("/addTask")
    public String addTask(Model model, @RequestParam String taskName, @RequestParam int priority, @RequestParam String statusName) throws MalformedURLException, IOException {
        Status status = schoolOrganizerFacade.getStatusFromName(this.getStatuses(), statusName);
        Task newTask = schoolOrganizerFacade.createTask(taskName, priority, status);
        schoolOrganizerFacade.addTaskToStatus(newTask, status);
        return "hello";
    }
    
    @RequestMapping("/moveTask")
    public String moveToStatus(Model model, @RequestParam String taskName, @RequestParam String destinationStatusName) {
        Task task = schoolOrganizerFacade.getTaskFromName(this.getStatuses(), taskName);
        Status destinationStatus = schoolOrganizerFacade.getStatusFromName(this.getStatuses(), destinationStatusName);
        task.setStatus(destinationStatus);
        schoolOrganizerFacade.addTaskToStatus(task, destinationStatus);
        //schoolOrganizerFacade.removeTaskFromStatus(task, task.getStatus());
        return "hello";
    }
    
    public List<Status> getStatuses() {
        return this.statuses;
    }
}
