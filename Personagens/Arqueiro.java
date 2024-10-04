package Personagens;

// Classe que representa um Arqueiro, especializado em combate à distância
public class Arqueiro extends Personagem {
    private int pontaria; // Nível de pontaria do Arqueiro

    // Construtor para inicializar um Arqueiro com os atributos fornecidos
    public Arqueiro(String nome, String titulo, int saude, int energia, int forca, int habilidade, int velocidade, int pontaria) {
        super(nome, titulo, saude, energia, forca, habilidade, velocidade);
        this.pontaria = pontaria;
    }

    // Método para executar a habilidade "Ataque Rápido"
    public int habilidade() {
        int energia = this.getEnergia() - 10; // Reduz a energia após o uso da habilidade
        this.setEnergia(energia); // Atualiza o nível de energia após o uso da habilidade
        System.out.println(getNome() + " usou a habilidade 'Ataque Rápido'!"); // Mensagem de uso da habilidade
        return this.pontaria * getHabilidade(); // Retorna o dano do ataque rápido
    }

    // Método para executar a habilidade "Tiro Certeiro"
    public int especial() {
        int energia = this.getEnergia() - 15; // Reduz a energia após o uso da habilidade especial
        this.setEnergia(energia); // Atualiza o nível de energia após o uso da habilidade especial
        System.out.println(getNome() + " usou a habilidade 'Tiro Certeiro'!"); // Mensagem de uso da habilidade especial
        return this.pontaria * getHabilidade() * 2; // Retorna o dano do tiro certeiro
    }
}
