package br.com.aula.managementms.dto;


import lombok.Data;

import java.util.ArrayList;

@Data
public class ManagementDTO {

  private String name;
  private ArrayList<TasksDTO> task;
}
