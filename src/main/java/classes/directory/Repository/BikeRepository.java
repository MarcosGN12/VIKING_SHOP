package classes.directory.Repository;

import classes.directory.Entity.Bike;
import classes.directory.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {


}
