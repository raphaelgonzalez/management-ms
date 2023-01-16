package br.com.aula.managementms;

import br.com.aula.managementms.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementMsApplication {

	@Autowired
	private ManagementService managementService;

	public static void main(String[] args) {
		SpringApplication.run(ManagementMsApplication.class, args);
	}

}
