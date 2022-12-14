package br.com.aula.managementms.dto;


import br.com.aula.managementms.model.TaskList;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ManagementDTO {

  private Integer id;
  private String name;
  private ArrayList<TaskList> task;
}
