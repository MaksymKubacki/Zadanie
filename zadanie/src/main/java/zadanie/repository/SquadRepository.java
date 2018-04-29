package zadanie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import zadanie.entity.Squad;

public interface SquadRepository extends JpaRepository<Squad, Long>{
//	List<Squad> findByName(String name);

	Squad findById(long id);

	List<Squad> findAll();

}
