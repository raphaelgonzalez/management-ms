package br.com.aula.managementms.service.impl;

import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.dto.TasksDTO;
import br.com.aula.managementms.model.Management;
import br.com.aula.managementms.model.Task;
import br.com.aula.managementms.repository.ManagementRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ManagementServiceImplTest {

  @Mock
  private ManagementRepository repository;

  @InjectMocks
  private ManagementServiceImpl managementServiceImpl;

  private ManagementDTO managementDTO;

  private Management management;
  private TasksDTO tasksDTO;

  @BeforeEach
  void up() {

    managementDTO = new ManagementDTO();
    tasksDTO = new TasksDTO();
    management = new Management();

    Task task1 = new Task();
    task1.setStep("In Progress");
    task1.setDescription("Realizar setup do projeto");
    ArrayList<Task> taskList = new ArrayList<>();
    taskList.add(task1);


    management.setId(1);
    management.setName("Raphael Gonzalez");
    management.setTasks(taskList);




    TasksDTO tasksDTO = new TasksDTO();
    tasksDTO.setStep("In Progress 2");
    tasksDTO.setDescription("Realizar setup do projeto 2");
    ArrayList<TasksDTO> list = new ArrayList<>();
    list.add(tasksDTO);

    managementDTO.setName("Caio");
    managementDTO.setTasks(list);
  }

//  @AfterEach
//  void down() {
//    repository.deleteAll();
//  }

  @Test
  @DisplayName("Test Create")
  void createTest() {


    Management save = repository.save(management);
    save.setName("Rapha");
    managementDTO.setName(save.getName());
    Mockito.when(managementServiceImpl.create(ArgumentMatchers.eq(managementDTO))).thenReturn(Optional.ofNullable(managementDTO));

  }



}