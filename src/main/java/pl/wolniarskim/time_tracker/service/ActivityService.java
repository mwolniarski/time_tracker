package pl.wolniarskim.time_tracker.service;

import org.springframework.stereotype.Service;
import pl.wolniarskim.time_tracker.model.Activity;
import pl.wolniarskim.time_tracker.repository.ActivityRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
        this.activityRepository.save(new Activity("Test1", LocalDateTime.of(2021,12,1,12,1,20), LocalDateTime.of(2021,12,1,13,1,20)));
        this.activityRepository.save(new Activity("Test2", LocalDateTime.of(2021,9,19,17,0,20), LocalDateTime.of(2021,9,19,18,0,20)));
        this.activityRepository.save(new Activity("Test3", LocalDateTime.of(2020,12,1,13,1,20), LocalDateTime.of(2020,12,1,14,1,20)));
    }

    public List<Activity> getAllActivities(){
        List<Activity> activities = activityRepository.findAll();
        Collections.sort(activities);
        return activities;
    }

    public List<Activity> findByDay(LocalDate localDate){
        List<Activity> activities = activityRepository.findAll().stream().filter(activity -> {
            if(activity.getStartTime().getYear() == localDate.getYear() &&
                    activity.getStartTime().getMonth() == localDate.getMonth() &&
                    activity.getStartTime().getDayOfMonth() == localDate.getDayOfMonth()){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Collections.sort(activities);
        return activities;
    }

    public List<Activity> todayActivities(){
        LocalDateTime now = LocalDateTime.now();
        List<Activity> activities = activityRepository.findAll().stream().filter(activity -> {
            if(activity.getStartTime().getYear() == now.getYear() &&
                    activity.getStartTime().getMonth() == now.getMonth() &&
                    activity.getStartTime().getDayOfMonth() == now.getDayOfMonth()){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        Collections.sort(activities);
        return activities;
    }

    public void saveActivity(Activity activity){
        activityRepository.save(activity);
    }
}
