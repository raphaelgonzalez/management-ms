package br.com.aula.managementms.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Data
public class Management {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private String name;

  @Column
  private ArrayList<TaskList> task;
}
