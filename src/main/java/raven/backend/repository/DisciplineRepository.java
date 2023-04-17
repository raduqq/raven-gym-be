package raven.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raven.backend.entity.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}
