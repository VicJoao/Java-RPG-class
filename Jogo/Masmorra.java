package Jogo;

import Monstros.Dragao;
import Monstros.Esqueleto;
import Monstros.Monstro;
import Monstros.Zumbi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Classe que representa uma masmorra contendo monstros
public class Masmorra {
    private List<Monstro> monstros = new ArrayList<>(); // Lista de monstros na masmorra

    // Método para criar os monstros na masmorra
    public void criarMonstros() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Monstro monstro;
            if (i < 6) {
                // Cria um Esqueleto com atributos aleatórios
                monstro = new Esqueleto("Esqueleto " + (i + 1),
                        (int) (random.nextInt(51) + 50 * 0.4), // 60% da saúde original
                        random.nextInt(6) + 5,  // Força entre 5 e 10
                        random.nextInt(4) + 1,  // Habilidade entre 1 e 4
                        random.nextInt(4) + 1,  // Velocidade entre 1 e 4
                        random.nextInt(6) + 5   // Defesa entre 5 e 10
                );
            } else if (i < 14) {
                // Cria um Zumbi com atributos aleatórios
                monstro = new Zumbi("Zumbi " + (i + 1),
                        (int) (random.nextInt(81) + 80 * 0.4), // 60% da saúde original
                        random.nextInt(11) + 10, // Força entre 10 e 20
                        random.nextInt(4) + 4,   // Habilidade entre 4 e 7
                        random.nextInt(5) + 2,   // Velocidade entre 2 e 6
                        random.nextInt(11) + 5,  // Defesa entre 5 e 15
                        random.nextInt(6)       // Decomposição entre 0 e 5
                );
            } else {
                // Cria um Dragão com atributos aleatórios
                monstro = new Dragao("Dragão " + (i + 1),
                        (int) (random.nextInt(121) + 120 * 0.4), // 60% da saúde original
                        random.nextInt(16) + 15,   // Força entre 15 e 30
                        random.nextInt(4) + 7,     // Habilidade entre 7 e 10
                        random.nextInt(7) + 3,     // Velocidade entre 3 e 9
                        random.nextInt(16) + 10,   // Defesa entre 10 e 25
                        random.nextInt(6) + 5      // Fogo entre 5 e 10
                );
            }
            monstros.add(monstro); // Adiciona o monstro à lista de monstros
        }
    }

    // Método para imprimir os nomes dos monstros na masmorra
    public void printMasmorra() {
        for (Monstro monstro : monstros) {
            System.out.println(monstro.getNome());
        }
    }

    // Getters e setters para a lista de monstros
    public List<Monstro> getMonstros() {
        return monstros;
    }

    // Método para eliminar o primeiro monstro da lista (após ser derrotado)
    public void matarMonstro(){
        monstros.remove(0);
    }

    // Método para aplicar dano ao monstro atual na masmorra
    public void recebeDano(int dano, int velocidade){
        monstros.get(0).defender(dano,velocidade);
    }

    // Método para verificar se o jogador venceu a masmorra (nenhum monstro restante)
    public boolean venceu(){
        return monstros.isEmpty();
    }

    // Método para verificar se o monstro atual na masmorra está morto e aplicar o drop
    public boolean verificaMortos(Item drop){
        Monstro monstroAtual = monstros.get(0);
        if(monstroAtual.getSaude() <= 0){
            System.out.println("MONSTRO " + monstroAtual.getNome() + " MORTO!!");
            drop.setDrop(monstroAtual.drop()); // Aplica o drop do monstro
            matarMonstro(); // Remove o monstro morto da lista
            return true;
        }
        return false;
    }

    // Método para retornar o status do monstro atual na masmorra
    public String status(){
        Monstro monstro = monstros.get(0);
        int saude = monstro.getSaude();
        String nome = monstro.getNome();
        int habilidade = monstro.getHabilidade();
        return "\n" + nome + " LV." + habilidade + " Saude: " + saude;
    }
}
