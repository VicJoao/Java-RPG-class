package Monstros;

import java.util.Random;

// Classe que representa um Dragão, um tipo de Monstro
public class Dragao extends Monstro {
    private int fogo; // Nível de dano de fogo do Dragão

    // Construtor para inicializar um Dragão com os atributos fornecidos
    public Dragao(String nome, int saude, int forca, int habilidade, int velocidade, int defesa, int fogo) {
        super(nome, saude, forca, habilidade, velocidade, defesa);
        this.fogo = fogo;
    }

    // Getters e setters para o atributo 'fogo'
    public int getFogo() {
        return fogo;
    }

    public void setFogo(int fogo) {
        this.fogo = fogo;
    }

    // Método para realizar a habilidade especial "Sopro de Fogo" do Dragão
    public int habilidade() {
        // Habilidade do Dragão: causa dano de fogo
        int dano = calcularDano(fogo); // Calcula o dano de fogo com base no atributo 'fogo'
        System.out.println(getNome() + " usou a habilidade 'Sopro de Fogo' e causou " + dano + " de dano!");
        return dano; // Retorna o dano causado pela habilidade
    }

    // Método para realizar a habilidade especial "Chamas Devastadoras" do Dragão
    public int especial() {
        // Especial do Dragão: causa dano de fogo dobrado
        int dano = this.calcularDano(fogo * 2); // Calcula o dano de fogo dobrado com base no atributo 'fogo'
        System.out.println(getNome() + " usou a habilidade 'Chamas Devastadoras' e causou " + dano + " de dano!");
        return dano; // Retorna o dano causado pela habilidade
    }
}
