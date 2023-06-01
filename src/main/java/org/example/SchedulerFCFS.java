package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SchedulerFCFS {

  private final String results;

  public SchedulerFCFS(List<Processo> processos) {
    List<Integer> temposRetornoTotal = new ArrayList<>();
    List<Integer> temposRespostaTotal = new ArrayList<>();
    List<Integer> temposEsperaTotal = new ArrayList<>();

    int tempoAtual = 0;

    // Ordena os processos por ordem de chegada
    processos.sort(Comparator.comparing(Processo::getChegada));

    while (!processos.isEmpty()) {  // para todos os processos
      Processo processo = processos.get(0); // pega o atual e remove a indexação
      processos.remove(0);

      int tempoEspera = tempoAtual - processo.getChegada();
      temposEsperaTotal.add(Math.max(tempoEspera, 0)); // adiciona no tempo de espera total para calculo da média

      int tempoTermino = tempoAtual + processo.getDuracao();

      int tempoRetorno = tempoTermino - processo.getChegada();
      temposRetornoTotal.add(Math.max(tempoRetorno, 0));  // adiciona no tempo de retorno total para calculo da média

      temposRespostaTotal.add(Math.max(tempoEspera, 0)); // adiciona no tempo de espera total para calculo da média

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