package classes.directory.Repository;

import classes.directory.Entity.TypeBike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeBikeRepository extends JpaRepository<TypeBike, Long> {
}
