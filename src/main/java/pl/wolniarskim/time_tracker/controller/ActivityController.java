package pl.wolniarskim.time_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wolniarskim.time_tracker.model.Activity;
import pl.wolniarskim.time_tracker.service.ActivityService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ActivityController {

    private ActivityService activityService;

    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }

    @GetMapping("/newActivity")
    public String newActivity(Model model){
        model.addAttribute("activityModel", new Activity());
        return "addActivity.html";
    }

    @PostMapping("/newActivity")
    public String newActivity(@ModelAttribute(value = "name") String name, @ModelAttribute(value = "startDate") String startDate, @ModelAttribute(value = "endDate") String endDate){
        startDate = startDate.replaceAll("T", " ");
        endDate = endDate.replaceAll("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDate, formatter);

        Activity newActivity = new Activity(name, startTime, endTime);
        activityService.saveActivity(newActivity);
        return "redirect:/dashboard";
    }
}
