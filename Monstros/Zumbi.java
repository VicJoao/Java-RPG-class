package Monstros;

import java.util.Random;

// Classe que representa um Zumbi, um tipo de Monstro
public class Zumbi extends Monstro {
    private int decomposicao; // Nível de decomposição do Zumbi

    // Construtor para inicializar um Zumbi com os atributos fornecidos
    public Zumbi(String nome, int saude, int forca, int habilidade, int velocidade, int defesa, int decomposicao) {
        super(nome, saude, forca, habilidade, velocidade, defesa);
        this.decomposicao = decomposicao;
    }

    // Getters e setters para o atributo 'decomposicao'
    public int getDecomposicao() {
        return decomposicao;
    }

    public void setDecomposicao(int decomposicao) {
        this.decomposicao = decomposicao;
    }

    // Método para realizar um ataque do Zumbi
    @Override
    public int atacar() {
        if (this.decomposicao > 0) { // Verifica se o Zumbi ainda está em decomposição
            this.decomposicao--; // Reduz o nível de decomposição
            System.out.println(getNome() + " atacou! Decomposição restante: " + this.decomposicao);
            return super.atacar(); // Retorna o dano do ataque do Zumbi
        } else {
            System.out.println(getNome() + " apodreceu"); // Mensagem caso o Zumbi esteja totalmente decomposto
            this.setSaude(0); // Define a saúde do Zumbi como 0
            return 0; // Retorna 0 de dano
        }
    }

    // Método para realizar a habilidade especial "Ataque de Decomposição" do Zumbi
    public int habilidade() {
        // Usa o ataque * decomposicao
        int ataque = super.atacar();
        int habilidade = ataque * decomposicao;
        System.out.println(getNome() + " usou a habilidade 'Ataque de Decomposição' e causou " + habilidade + " de dano!");
        return habilidade; // Retorna o dano causado pela habilidade especial
    }

    // Método para realizar a habilidade especial "Ataque Especial de Decomposição" do Zumbi
    public int especial() {
        // Usa o ataque * (decomposicao - 10)
        int ataque = super.atacar();
        int especial = ataque * (decomposicao - 10);
        System.out.println(getNome() + " usou a habilidade 'Ataque Especial de Decomposição' e causou " + especial + " de dano!");
        return especial; // Retorna o dano causado pela habilidade especial
    }
}
