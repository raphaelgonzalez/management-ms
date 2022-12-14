package br.com.aula.managementms.service.impl;

import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.model.Management;
import br.com.aula.managementms.repository.ManagementRepository;
import br.com.aula.managementms.service.ManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ManagementServiceImpl implements ManagementService {

  @Autowired
  ManagementRepository repository;

  @Override
  public Optional<ManagementDTO> create(ManagementDTO request) {

    Management management = new Management();

    management.setName(request.getName());
    management.setTask(request.getTask());

    management = repository.save(management);

    ManagementDTO response = new ManagementDTO();

    response.setName(management.getName());
    response.setTask(management.getTask());

    return Optional.of(response);
  }

  @Override
  public Optional<ManagementDTO> update(ManagementDTO request, int id) {
    return Optional.empty();
  }

  @Override
  public Optional<ManagementDTO> get(int id) {
    return Optional.empty();
  }

  @Override
  public Optional<List<ManagementDTO>> getAll() {
    return null;
  }

  @Override
  public boolean delete(int id) {
    return false;
  }
}
