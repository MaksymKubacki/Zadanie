package zadanie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import zadanie.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
//	List<User> findByName(String name);
//
	User findById(long id);
//
//	List<User> findAll();

}
