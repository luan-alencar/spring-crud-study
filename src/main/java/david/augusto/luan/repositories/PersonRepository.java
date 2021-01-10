package david.augusto.luan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import david.augusto.luan.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
