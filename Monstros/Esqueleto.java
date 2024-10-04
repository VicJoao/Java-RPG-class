package Monstros;

import java.util.Random;

// Classe que representa um Esqueleto, um tipo de Monstro
public class Esqueleto extends Monstro {

    // Construtor para inicializar um Esqueleto com os atributos fornecidos
    public Esqueleto(String nome, int saude, int forca, int habilidade, int velocidade, int defesa) {
        super(nome, saude, forca, habilidade, velocidade, defesa);
    }

    // Método para realizar a habilidade especial "Ataque Rápido" do Esqueleto
    public int habilidade() {
        // Habilidade do Esqueleto: causa dano baseado na força e habilidade
        int dano = calcularDano(1); // Calcula o dano com base em um dano base de 1
        System.out.println(getNome() + " usou a habilidade 'Ataque Rápido' e causou " + dano + " de dano!");
        return dano; // Retorna o dano causado pela habilidade
    }

    // Método para realizar a habilidade especial "Sacrifício" do Esqueleto
    public int especial() {
        // Especial do Esqueleto: causa dano a si mesmo e aumenta o dano do próximo ataque
        int dano = calcularDano(-3); // O Esqueleto tira 3 de sua própria vida
        System.out.println(getNome() + " usou a habilidade 'Sacrifício' e causou " + dano + " de dano a si mesmo!");
        return dano; // Retorna o dano causado pela habilidade
    }
}
