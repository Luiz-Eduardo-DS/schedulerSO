package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class Processo {

  private int id;
  private int chegada;
  private int duracao;
  private int tempoEspera;
  private int tempoRetorno;


  public Processo(int chegada, int duracao) {
    this.chegada = chegada;
    this.duracao = duracao;
    this.tempoEspera = 0;
    this.tempoRetorno = 0;
  }

  public int getChegada() {
    return this.chegada;
  }

  public int getDuracao() {
    return this.duracao;
  }


  public int compareTo(Processo outro) {
    return Integer.compare(chegada, outro.chegada);
  }
}