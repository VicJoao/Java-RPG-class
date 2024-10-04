package Jogo;

public interface Combate {
    // Método para executar um ataque
    int atacar();

    // Método para defender um ataque
    int defender(int dano, int velocidade);
}

