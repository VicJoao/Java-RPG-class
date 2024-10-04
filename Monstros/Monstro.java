package Monstros;

import Jogo.Combate;
import Jogo.Item;
import Personagens.Mago;

import java.util.Random;

// Classe base que representa um Monstro
public class Monstro implements Combate {
    private String nome; // Nome do Monstro
    private int saude; // Saúde do Monstro
    private int forca; // Força do Monstro
    private int habilidade; // Habilidade do Monstro
    private int velocidade; // Velocidade do Monstro
    private int defesa; // Defesa do Monstro

    // Construtor para inicializar um Monstro com os atributos fornecidos
    public Monstro(String nome, int saude, int forca, int habilidade, int velocidade, int defesa) {
        this.nome = nome;
        this.saude = saude;
        this.forca = forca;
        this.habilidade = habilidade;
        this.velocidade = velocidade;
        this.defesa = defesa;
    }

    // Getters e setters para os atributos

    public String getNome() {
        return nome;
    }

    public int getSaude() {
        return saude;
    }

    public void setSaude(int saude) {
        this.saude = saude;
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

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    // Método para realizar um ataque do Monstro
    @Override
    public int atacar() {
        Random rand = new Random();
        int dano = rand.nextInt(forca * habilidade) + 1;
        System.out.println(nome + " atacou e causou " + dano + " de dano!");
        return dano;
    }

    // Método para realizar a defesa do Monstro
    @Override
    public int defender(int dano, int velocidade) {
        Random desvio = new Random();
        int chanceDeDesvio;
        if (velocidade > this.velocidade) {
            chanceDeDesvio = this.velocidade / velocidade;
            chanceDeDesvio *= 100;
            if (desvio.nextInt(100) <= chanceDeDesvio) {
                System.out.println(getNome() + " Desviou completamente do ataque!");
                return 0; // Nenhum dano sofrido
            }
        }

        int danoSofrido = dano - (this.defesa * this.habilidade);
        if (danoSofrido > 0) {
            saude -= danoSofrido;
            System.out.println(getNome() + " sofreu " + danoSofrido + " de dano!");
        } else {
            System.out.println(getNome() + " conseguiu se defender completamente do ataque!");
        }

        return danoSofrido;
    }

    // Método para calcular o dano total com base em um dano base e a força e habilidade do Monstro
    public int calcularDano(int baseDano) {
        Random rand = new Random();
        // Calcula um dano aleatório com base na força e habilidade do Monstro
        int dano = rand.nextInt(getForca() * getHabilidade()) + 1 + baseDano;
        return dano;
    }

    // Método para gerar um item aleatório deixado cair pelo Monstro após derrotado
    public Item drop() {
        Item drop = generateRandomItem();
        System.out.println(nome + " DEIXOU CAIR " + drop.getNome());
        return drop;
    }

    // Método para gerar um item aleatório com base nas habilidades do Monstro
    private Item generateRandomItem() {
        Random rand = new Random();
        String itemNome = "poção de ";
        int poder = 0;
        Item item = null;
        int num = rand.nextInt(3);
        if (num <= 1) {
            itemNome += "Saúde ";
            poder += this.habilidade * 2;
            item = new Item(0, poder, 0, itemNome);
        } else if (num == 2) {
            itemNome += "Energia ";
            poder += this.habilidade * 2;
            item = new Item(poder, 0, 0, itemNome);
        } else if (num == 3) {
            itemNome += "Habilidade ";
            if (this.habilidade > 6) {
                poder += 2;
            } else {
                poder += 1;
            }
            item = new Item(0, 0, poder, itemNome);
        }
        return item;
    }
}
