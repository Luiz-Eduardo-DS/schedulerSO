package org.example;

import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    String path = "C:/Users/luizd/IdeaProjects/SchedulerSO/src/main/java/org/example/dados.txt";

    List<Processo> processos = FileReaderTXT.leitor(path);
    SchedulerFCFS schedulerFCFS = new SchedulerFCFS(processos);
    System.out.println("FCFS " + schedulerFCFS.getResults());

    List<Processo> processosSJF = FileReaderTXT.leitor(path);
    SchedulerSJF schedulerSJF = new SchedulerSJF(processosSJF);
    System.out.println("SJF " + schedulerSJF.getResults());

    List<Processo> processosRR = FileReaderTXT.leitor(path);
    SchedulerRR schedulerRR = new SchedulerRR(processosRR);
    System.out.println("RR " + schedulerRR.getResults());

  }
}
