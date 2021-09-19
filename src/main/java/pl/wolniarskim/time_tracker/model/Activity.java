package pl.wolniarskim.time_tracker.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Activity implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;

    LocalDateTime startTime;
    LocalDateTime endTime;

    public Activity(String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Activity() {
    }

    //    @ManyToOne
//    Activity activity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

//    public Activity getActivity() {
//        return activity;
//    }
//
//    public void setActivity(Activity activity) {
//        this.activity = activity;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Activity){
            Activity activity = (Activity) o;
            if(this.startTime.isAfter(activity.startTime)){
                return 1;
            }
        }
        return -1;
    }
}
