package br.com.aula.managementms.model;


import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
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
