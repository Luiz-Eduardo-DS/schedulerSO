package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileReaderTXT {

  public static List<Processo> leitor(String path) throws IOException {
    List<Processo> processos = new ArrayList<>();

    try {
      Scanner scanner = new Scanner(new File(path));

      while (scanner.hasNextLine()) {
        int tempoChegada = scanner.nextInt();
        int duracao = scanner.nextInt();
        processos.add(new Processo(tempoChegada, duracao));
      }

      scanner.close();

    } catch (FileNotFoundException e) {
      System.out.println("Arquivo não encontrado.");
    }
    return processos;
  }
}
