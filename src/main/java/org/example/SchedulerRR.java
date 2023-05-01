package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SchedulerRR {

  private final String results;

  public SchedulerRR(List<Processo> processos) {
    List<Integer> temposRetornoTotal = new ArrayList<>();
    List<Integer> temposRespostaTotal = new ArrayList<>();
    List<Integer> temposEsperaTotal = new ArrayList<>();

    PriorityQueue<Processo> fila = new PriorityQueue<>(Comparator.comparing(Processo::getChegada));

    List<Processo> naoExecutados = new ArrayList<>(processos);

    int tempoAtual = 0;
    int quantum = 2;

    while (!fila.isEmpty() || !naoExecutados.isEmpty()) {
      // Adiciona os processos que chegaram durante a execução do processo atual
      while (!naoExecutados.isEmpty() && naoExecutados.get(0).getChegada() <= tempoAtual) {
        Processo processo = naoExecutados.remove(0);
        processo.setTempoEspera(tempoAtual - processo.getChegada());
        fila.offer(processo);
      }

      if (fila.isEmpty()) {
        tempoAtual++;
        continue;
      }

      Processo processo = fila.poll();

      processo.setTempoEspera(tempoAtual - processo.getChegada());
      if (processo.getTempoEspera() < 0) {
        processo.setTempoEspera(0);
      }

      if (processo.getDuracao() > quantum) {
        processo.setDuracao(processo.getDuracao() - quantum);
        tempoAtual += quantum;
        fila.offer(processo);

      } else {
        processo.setTempoRetorno(tempoAtual + processo.getDuracao() - processo.getChegada());
        temposRetornoTotal.add(processo.getTempoRetorno());
        temposEsperaTotal.add(processo.getTempoEspera());
        temposRespostaTotal.add(processo.getTempoRetorno() - processo.getTempoEspera());
        tempoAtual += processo.getDuracao();
      }

      // Atualiza o tempo de retorno para todos os processos na fila
      for (Processo p : fila) {
        p.setTempoRetorno(tempoAtual - p.getChegada());
      }
    }

    double tempoRetornoMedio = temposRetornoTotal.stream().mapToInt(Integer::intValue)
        .average()
        .orElse(0.0);

    double tempoRespostaMedio = temposRespostaTotal.stream().mapToInt(Integer::intValue)
        .average().orElse(0.0);

    double tempoEsperaMedio = temposEsperaTotal.stream().mapToInt(Integer::intValue).average()
        .orElse(0.0);

    this.results = String.format("%.1f %.1f %.1f", tempoRetornoMedio, tempoRespostaMedio,
        tempoEsperaMedio);
  }

  public String getResults() {
    return this.results;
  }
}
