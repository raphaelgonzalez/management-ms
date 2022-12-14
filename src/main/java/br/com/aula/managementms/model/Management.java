package br.com.aula.managementms.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Management {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column
  private String name;

  @OneToMany(mappedBy = "management", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Tasks> task;
}
