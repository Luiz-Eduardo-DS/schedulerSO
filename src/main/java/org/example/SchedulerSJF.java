package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SchedulerSJF {

  private final String results;

  public SchedulerSJF(List<Processo> processos) {
    List<Integer> temposRetornoTotal = new ArrayList<>();
    List<Integer> temposRespostaTotal = new ArrayList<>();
    List<Integer> temposEsperaTotal = new ArrayList<>();

    PriorityQueue<Processo> fila = new PriorityQueue<>(Comparator.comparing(Processo::getDuracao));

    fila.addAll(processos);

    int tempoAtual = 0;

    while (!fila.isEmpty()) {
      Processo processo = fila.poll();

      int tempoEspera = tempoAtual - processo.getChegada();
      if (tempoEspera < 0) {
        tempoEspera = 0;
      }
      for (int i = 0; i < processos.size(); i++) {
        if (processo.getDuracao() <= tempoAtual && processo.getDuracao() < tempoEspera) {
          tempoEspera = processos.get(0).getDuracao();
          break;
        }
      }
      int tempoResposta = tempoEspera;
      temposRespostaTotal.add(Math.max(0, tempoResposta));
      temposEsperaTotal.add(tempoEspera);

      int tempoRetorno = tempoEspera + processo.getDuracao();
      temposRetornoTotal.add(tempoRetorno);

      tempoAtual += processo.getDuracao();
    }

    double tempoRetornoMedio = temposRetornoTotal.stream().mapToInt(Integer::intValue)
        .average()
        .orElse(0.0);

    double tempoRespostaMedio = temposRespostaTotal.stream().mapToInt(Integer::intValue)
        .average().orElse(0.0);

    double tempoEsperaMedio = temposEsperaTotal.stream().mapToInt(Integer::intValue).average()
        .orElse(0.0);

    this.results = String.format("%.1f %.1f %.1f", tempoRetornoMedio, tempoRespostaMedio, tempoEsperaMedio);
  }

  public String getResults() {
    return this.results;
  }
}