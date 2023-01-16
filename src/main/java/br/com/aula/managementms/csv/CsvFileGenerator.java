package br.com.aula.managementms.csv;

import br.com.aula.managementms.model.Management;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CsvFileGenerator {
  public void writeStudentsToCsv(List<Management> managements, Writer writer) {
    try {

      CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
      for (Management management : managements) {
        printer.printRecord(management.getId(), management.getName());
//          , management.getTasks());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

