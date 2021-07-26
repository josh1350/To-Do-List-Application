package nice.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {

    List<Task> findByOwnerOrderByDateDesc(User user);
}
