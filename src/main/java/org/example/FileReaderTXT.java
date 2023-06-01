package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileReaderTXT {

  public static List<Processo> leitor(String path) throws IOException {
    List<Processo> processos = new ArrayList<>();

    InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("dados.txt");
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      int tempoChegada = scanner.nextInt();
      int duracao = scanner.nextInt();
      processos.add(new Processo(tempoChegada, duracao));
    }

    scanner.close();

    return processos;
  }
}
