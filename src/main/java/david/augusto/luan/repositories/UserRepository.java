package david.augusto.luan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import david.augusto.luan.data.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(@Param("username") String username);
}
