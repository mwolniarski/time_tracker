package pl.wolniarskim.time_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wolniarskim.time_tracker.model.Activity;
import pl.wolniarskim.time_tracker.service.ActivityService;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DashboardController {

    private ActivityService activityService;

    public DashboardController(ActivityService activityService){
        this.activityService = activityService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        List<Activity> activities = activityService.findByDay(LocalDate.now());
        model.addAttribute("activities", activities);
        model.addAttribute("date", LocalDate.now());
        int counter = 0;
        int activeIndex = -1;
        for(Activity activity : activities){
            if(activity.getStartTime().isBefore(LocalDateTime.now()) && activity.getEndTime().isAfter(LocalDateTime.now())){
                activeIndex = counter;
                break;
            }
            counter++;
        }
        model.addAttribute("active", activeIndex);
        return "dashboard.html";
    }

    @PostMapping("/dashboard")
    public String dashboard(Model model, @ModelAttribute(value = "date") String dateAsString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateAsString, formatter);
        List<Activity> activities = activityService.findByDay(date);
        model.addAttribute("activities", activities);
        model.addAttribute("date", date);
        int counter = 0;
        int activeIndex = -1;
        for(Activity activity : activities){
            if(activity.getStartTime().isBefore(LocalDateTime.now()) && activity.getEndTime().isAfter(LocalDateTime.now())){
                activeIndex = counter;
                break;
            }
            counter++;
        }
        model.addAttribute("active", activeIndex);
        return "dashboard.html";
    }
}
