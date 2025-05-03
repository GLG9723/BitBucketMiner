package aiss.proyecto.repository;

import aiss.proyecto.modelRepo.ZProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZProjectRepository extends JpaRepository<ZProject, Long> {
}
