package zadanie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zadanie.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{

}
