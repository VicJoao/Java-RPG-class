package Personagens;
import java.util.Random;

import Jogo.Combate;
import Jogo.Item;
import java.util.Scanner;

// Classe base para personagens do jogo
public class Personagem implements Combate {
    private String nome; // Nome do personagem
    private String titulo; // Título do personagem
    private int saude; // Pontos de saúde do personagem
    private int energia; // Nível de energia do personagem
    private int forca; // Força do personagem
    private int habilidade; // Habilidade do personagem
    private int defesa; // Nível de defesa do personagem
    private int velocidade; // Velocidade do personagem

    // Construtor para inicializar um personagem com os atributos fornecidos
    public Personagem(String nome, String titulo, int saude, int energia, int forca, int habilidade, int velocidade) {
        this.nome = nome;
        this.titulo = titulo;
        this.saude = saude;
        this.energia = energia;
        this.forca = forca;
        this.habilidade = habilidade;
        this.defesa = (saude + forca) / 2;
        this.velocidade = velocidade;
    }

    // Método para realizar um ataque
    public int atacar() {
        this.energia -= 5; // Reduz a energia do personagem após o ataque
        System.out.println(getNome() + " usou ataque!");
        return this.forca * this.habilidade; // Retorna o dano do ataque
    }

    // Método para defender um ataque
    public int defender(int dano, int velocidade) {
        this.energia -= 1; // Reduz a energia do personagem ao defender
        Random desvio = new Random(); // Objeto para gerar números aleatórios
        int chanceDeDesvio;
        if (this instanceof Mago) { // Verifica se o personagem é um Mago
            ((Mago) this).recuperaMana(); // Recupera a mana se for um Mago
        }
        if (velocidade > this.velocidade) { // Verifica se o ataque é mais rápido que o personagem
            chanceDeDesvio = this.velocidade / velocidade; // Calcula a chance de desvio com base na velocidade
            chanceDeDesvio *= 100;
            if (desvio.nextInt(100) <= chanceDeDesvio) { // Verifica se o desvio é bem-sucedido
                System.out.println(getNome() + " Desviou completamente do ataque!");
                return 0; // Retorna nenhum dano sofrido
            }
        }

        int danoSofrido = dano - (this.defesa * this.habilidade); // Calcula o dano sofrido após a defesa
        if (danoSofrido > 0) {
            saude -= danoSofrido; // Reduz a saúde do personagem de acordo com o dano sofrido
            System.out.println(getNome() + " sofreu " + danoSofrido + " de dano!");
        } else {
            System.out.println(getNome() + " conseguiu se defender completamente do ataque!");
        }

        return danoSofrido; // Retorna o dano sofrido após a defesa
    }

    // Método para aplicar efeitos de um item ao personagem
    public void aplicarEfeitos(Item item){
        this.saude += item.getSaude(); // Aumenta a saúde do personagem com base no item
        this.energia += item.getEnergia(); // Aumenta a energia do personagem com base no item
        this.habilidade += item.getHabilidade(); // Aumenta a habilidade do personagem com base no item
    }

    // Getters e setters para os atributos
    public int getSaude() {
        return saude;
    }

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
}

