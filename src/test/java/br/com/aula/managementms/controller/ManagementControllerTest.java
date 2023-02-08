package br.com.aula.managementms.controller;

import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.dto.TasksDTO;
import br.com.aula.managementms.model.Management;
import br.com.aula.managementms.model.Task;
import br.com.aula.managementms.repository.ManagementRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ManagementControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ManagementRepository repository;

  private ManagementDTO managementDTO;

  private Gson gson;

  @BeforeEach
  void up() {

    gson = new Gson();
    managementDTO = new ManagementDTO();

    Task task1 = new Task();
    task1.setStep("In Progress");
    task1.setDescription("Realizar setup do projeto");
    ArrayList<Task> taskList = new ArrayList<>();
    taskList.add(task1);

    Management management = new Management();
    management.setName("Raphael Gonzalez");
    management.setTasks(taskList);

    repository.save(management);

    TasksDTO tasksDTO = new TasksDTO();
    tasksDTO.setStep("In Progress 2");
    tasksDTO.setDescription("Realizar setup do projeto 2");
    ArrayList<TasksDTO> list = new ArrayList<>();
    list.add(tasksDTO);

    managementDTO.setName("Caio");
    managementDTO.setTasks(list);

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

  @Test
  @DisplayName("Pegar por ID")
  void getByIdTest() throws Exception {

    var id = 1;

    mockMvc.perform(MockMvcRequestBuilders.get("/managers/{id}", id))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("Pegar por ID porém nao é encontrado")
  void getByIdNotFoundTest() throws Exception {

    var id = 333;

    mockMvc.perform(MockMvcRequestBuilders.get("/managers/{id}", id))
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("Atualizando por ID")
  void updateTest() throws Exception {

    Integer id = repository.findAll().get(0).getId();

    mockMvc.perform(MockMvcRequestBuilders.put("/managers/{id}", id)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(gson.toJson(managementDTO))
        .accept(MediaType.APPLICATION_JSON)
      )

      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("Create")
  void createTest() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.post("/managers")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(gson.toJson(managementDTO))
        .accept(MediaType.APPLICATION_JSON)
      )

      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("DELETE")
  void deleteTest() throws Exception {

    Integer id = repository.findAll().get(0).getId();


    mockMvc.perform(MockMvcRequestBuilders.delete("/managers/{id}", id))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("EXPORT TO CSV")
  void exportCsvTest() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/managers/export-to-csv"));
  }

  @Test
  @DisplayName("CONVERT TO XLS")
  void convertTest() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/managers/converter-to-excel")
      .param("strSource","C:/Users/gonza/Downloads/managements.csv")
      .param("strDestination","C:/Users/gonza/Downloads")
      .param("extension", ".xlsx"));

  }



}
