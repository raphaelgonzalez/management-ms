package br.com.aula.managementms.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tb_management")
public class Management {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Integer id;

  @Column
  private String name;

  @OneToMany(mappedBy = "management", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Task> tasks;

}
