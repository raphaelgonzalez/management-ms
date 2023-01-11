package br.com.aula.managementms.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Integer id;
  @Column
  private String description;
  @Column
  private String step;

  @ManyToOne
  @JoinColumn(name = "id_management", referencedColumnName = "id")
  private Management management;

}
