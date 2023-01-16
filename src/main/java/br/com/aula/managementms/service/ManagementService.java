package br.com.aula.managementms.service;

import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.model.Management;

import java.util.List;
import java.util.Optional;

public interface ManagementService {

  Optional<ManagementDTO> create(ManagementDTO request);

  Optional<ManagementDTO> update(ManagementDTO request, int id);

  Optional<ManagementDTO> get(int id);

  Optional<List<ManagementDTO>> getAll();

  boolean delete(int id);

  /*
   * CSV
   */
  void addManagement(Management management);
  List<Management> getManagementOfList();
}
