package br.com.aula.managementms.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ManagementDTO {

  private String name;
  private List<TasksDTO> tasks;
}
