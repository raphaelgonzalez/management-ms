package br.com.aula.managementms.service.impl;

import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.dto.TasksDTO;
import br.com.aula.managementms.model.Management;
import br.com.aula.managementms.model.Tasks;
import br.com.aula.managementms.repository.ManagementRepository;
import br.com.aula.managementms.service.ManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ManagementServiceImpl implements ManagementService {

  @Autowired
  ManagementRepository repository;

  @Override
  public Optional<ManagementDTO> create(ManagementDTO request) {
    log.info("create... {}", request);

    Management management = new Management();

    ArrayList<Tasks> taskList = new ArrayList<>();
    for (TasksDTO taskListDTO : request.getTask()) {
      Tasks taskList1 = new Tasks();
      taskList1.setDescription(taskListDTO.getDescription());
      taskList1.setStep(taskListDTO.getStep());

      taskList.add(taskList1);
    }

    management.setName(request.getName());
    management.setTask(taskList);

    management = repository.save(management);

    ManagementDTO response = new ManagementDTO();

    response.setName(management.getName());
    response.setTask(request.getTask());

    return Optional.of(response);
  }

  @Override
  public Optional<ManagementDTO> update(ManagementDTO request, int id) {
    log.info("update... {} {}", request, id);
    Optional<Management> management = repository.findById(id);

    if (management.isPresent()) {
      if (request.getName().isEmpty()) {
        management.get().setName(request.getName());
      }

      Management updated = repository.save(management.get());

      ManagementDTO response = new ManagementDTO();

      response.setName(updated.getName());
      response.setTask(response.getTask());

      return Optional.of(response);

    }
    return Optional.empty();
  }

  @Override
  public Optional<ManagementDTO> get(int id) {
    log.info("get... {}", id);
    Optional<Management> management = repository.findById(id);

    if (management.isPresent()) {

      ManagementDTO  response = new ManagementDTO();

      response.setName(management.get().getName());
      response.setTask(response.getTask());

      return Optional.of(response);
    }

    return Optional.empty();
  }

  @Override
  public Optional<List<ManagementDTO>> getAll() {
    log.info("getAll...");
    List<Management> managements = repository.findAll();

    List<ManagementDTO> responses = new ArrayList<>();

    if (!managements.isEmpty()) {
      for (Management management : managements) {

        ManagementDTO response = new ManagementDTO();

        response.setName(management.getName());
        response.setTask(response.getTask());
        responses.add(response);
      }
    }
    return Optional.of(responses);
  }

  @Override
  public boolean delete(int id) {
    log.info("delete... {}", id);
    Optional<Management> management = repository.findById(id);

    if (management.isPresent()) {
      repository.delete(management.get());
      return true;
    }
    return false;
  }
}
