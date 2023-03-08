package br.com.aula.managementms.fixture;

import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.dto.TasksDTO;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class StudentTemplateLoader implements TemplateLoader {

  @Override
  public void load() {
    Fixture.of(TasksDTO.class).addTemplate("valid", new Rule() {{
      add("description", random("Criar serviço", "Criar controller", "Criar entidade"));
      add("step", random("Em progresso", "Em desenvolvimento", "Finalizado"));
    }});

    Fixture.of(ManagementDTO.class).addTemplate("valid", new Rule() {{
      add("name", "Raphael");
      add("tasks", has(1).of(TasksDTO.class, "valid"));
    }});

    Fixture.of(TasksDTO.class).addTemplate("valid-update", new Rule() {{
      add("description", random("Alterar serviço", "Alterar controller", "Alterar entidade"));
      add("step", random("Em regresso", "Em teste", "Testando"));
    }});

    Fixture.of(ManagementDTO.class).addTemplate("valid-update", new Rule() {{
      add("name", "Caio");
      add("tasks", has(1).of(TasksDTO.class, "valid-update"));
    }});
  }
}
