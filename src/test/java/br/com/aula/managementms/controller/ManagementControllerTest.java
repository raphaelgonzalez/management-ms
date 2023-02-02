package br.com.aula.managementms.controller;

import br.com.aula.managementms.model.Management;
import br.com.aula.managementms.model.Task;
import br.com.aula.managementms.repository.ManagementRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ManagementControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ManagementRepository repository;


  @BeforeEach
  void up() {

    Task task1 = new Task();
    task1.setStep("In Progress");
    task1.setDescription("Realizar setup do projeto");
    ArrayList<Task> taskList = new ArrayList<>();
    taskList.add(task1);

    Management management = new Management();
    management.setName("Raphael Gonzalez");


    management.setTasks(taskList);
    repository.save(management);
  }

  @AfterEach
  void down() {
    repository.deleteAll();
  }

  @Test
  @DisplayName("Listar tudo")
  void getAllTest() throws Exception {

      mockMvc.perform(MockMvcRequestBuilders.get("/managers"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());

  }

}
