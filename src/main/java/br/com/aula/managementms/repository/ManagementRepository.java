package br.com.aula.managementms.repository;

import br.com.aula.managementms.model.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementRepository extends JpaRepository<Management, Integer> {

}