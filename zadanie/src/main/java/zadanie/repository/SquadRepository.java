package zadanie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zadanie.entity.Squad;

public interface SquadRepository extends JpaRepository<Squad, Long>{

}
