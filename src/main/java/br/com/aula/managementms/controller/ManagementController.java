package br.com.aula.managementms.controller;


import br.com.aula.managementms.csv.CsvFileGenerator;
import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.service.impl.ManagementServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/managers")
@Slf4j
public class ManagementController {

  @Autowired
  private ManagementServiceImpl service;

  @Autowired
  private CsvFileGenerator csvGenerator;

  @PostMapping
  private ResponseEntity<ManagementDTO> create(@RequestBody ManagementDTO managementDTO) {
    log.info("Criando..");
    Optional<ManagementDTO> response = service.create(managementDTO);

    if (response.isPresent()) {
      return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping
  private ResponseEntity<List<ManagementDTO>> getAll() {
    log.info("Recuperando tudo..");
    Optional<List<ManagementDTO>> response = service.getAll();

    return new ResponseEntity<>(response.get(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  private ResponseEntity<ManagementDTO> getById(@PathVariable("id") int id) {
    log.info("Recuperando por ID..");
    Optional<ManagementDTO> getById = service.get(id);

    if (getById.isPresent()) {
      return new ResponseEntity<>(getById.get(), HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  private ResponseEntity<ManagementDTO> update(@RequestBody ManagementDTO managementDTO, @PathVariable("id") int id) {
    log.info("Atualizando..");
    Optional<ManagementDTO> update = service.update(managementDTO, id);

    if (update.isPresent()) {
      return new ResponseEntity<>(update.get(), HttpStatus.CREATED);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  private ResponseEntity<ManagementDTO> delete(@PathVariable("id") int id) {
    log.info("Deletando..");
    boolean delete = service.delete(id);

    if (delete) {
      return ResponseEntity.status(HttpStatus.OK).build();

    }
    return ResponseEntity.notFound().build();

  }
  @GetMapping("/export-to-csv")
  public void exportIntoCSV(HttpServletResponse response) throws IOException {
    response.setContentType("text/csv");
    response.addHeader("Content-Disposition", "attachment; filename=\"management.csv\"");
    csvGenerator.writeStudentsToCsv(service.getManagementOfList(), response.getWriter());
  }
}
