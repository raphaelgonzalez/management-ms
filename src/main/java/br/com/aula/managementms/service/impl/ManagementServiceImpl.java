package br.com.aula.managementms.service.impl;

import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.dto.TasksDTO;
import br.com.aula.managementms.model.Management;
import br.com.aula.managementms.model.Task;
import br.com.aula.managementms.repository.ManagementRepository;
import br.com.aula.managementms.service.ManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ManagementServiceImpl implements ManagementService {

  @Autowired
  ManagementRepository repository;

  @Override
  @Transactional
  public Optional<ManagementDTO> create(ManagementDTO request) {
    log.info("create... {}", request);

    Management management = new Management();

    ArrayList<Task> taskList = new ArrayList<>();
    for (TasksDTO taskListDTO : request.getTasks()) {
      Task taskList1 = new Task();
      taskList1.setManagement(management);
      taskList1.setDescription(taskListDTO.getDescription());
      taskList1.setStep(taskListDTO.getStep());

      taskList.add(taskList1);
    }

    management.setName(request.getName());
    management.setTasks(taskList);

    management = repository.save(management);

    ManagementDTO response = new ManagementDTO();

    response.setName(management.getName());
    response.setTasks(request.getTasks());

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



      response.setTasks(response.getTasks());

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
      List<TasksDTO> tasksDTOList= new ArrayList<>();
      for (Task task : management.get().getTasks()) {
        TasksDTO tasksDTO = new TasksDTO();
        tasksDTO.setStep(task.getStep());
        tasksDTO.setDescription(task.getDescription());

        tasksDTOList.add(tasksDTO);
      }

      response.setName(management.get().getName());
      response.setTasks(tasksDTOList);


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

        ArrayList<TasksDTO> taskListResponse = new ArrayList<>();
        for (Task task : management.getTasks()) {
          TasksDTO tasksDTO = new TasksDTO();
          tasksDTO.setDescription(task.getDescription());
          tasksDTO.setStep(task.getStep());

          taskListResponse.add(tasksDTO);
        }

        response.setName(management.getName());
        response.setTasks(taskListResponse);
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
