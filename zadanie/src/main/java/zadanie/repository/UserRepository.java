package zadanie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zadanie.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
