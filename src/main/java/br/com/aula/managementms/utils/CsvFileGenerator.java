package br.com.aula.managementms.utils;

import br.com.aula.managementms.dto.ManagementDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

@Component
public class CsvFileGenerator {
  public void writeStudentsToCsv(Optional<List<ManagementDTO>> managements, Writer writer) {
    if (managements.isPresent()) {
      try {

        //ADD HEADER NO CSV
        writer.append("Name;Tasks");
        writer.append("\n");


        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.newFormat(';'));
        for (ManagementDTO management : managements.get()) {
          printer.printRecord(management.getName(), management.getTasks());
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

