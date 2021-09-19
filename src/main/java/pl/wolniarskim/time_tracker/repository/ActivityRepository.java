package pl.wolniarskim.time_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wolniarskim.time_tracker.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
