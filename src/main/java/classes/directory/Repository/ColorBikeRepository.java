package classes.directory.Repository;

import classes.directory.Entity.ColorBike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorBikeRepository extends JpaRepository<ColorBike, Long> {
}
