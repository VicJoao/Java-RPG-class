package Personagens;

// Classe que representa um Guerreiro, especializado em combate físico
public class Guerreiro extends Personagem {

    // Construtor para inicializar um Guerreiro com os atributos fornecidos
    public Guerreiro(String nome, String titulo, int saude, int energia, int forca, int habilidade, int velocidade) {
        super(nome, titulo, saude, energia, forca, habilidade, velocidade);
    }

    // Método para executar a habilidade "Fortificar"
    public int habilidade() {
        int energia = this.getEnergia() - 10; // Reduz a energia após o uso da habilidade
        this.setEnergia(energia); // Atualiza o nível de energia após o uso da habilidade
        System.out.println(getNome() + " usou a habilidade 'Fortificar'!"); // Mensagem de uso da habilidade
        this.setForca(getForca() + 2); // Aumenta a força do Guerreiro em 2
        return 0; // Não retorna dano
    }

    // Método para executar a habilidade "Fúria"
    public int especial() {
        int energia = this.getEnergia() - 15; // Reduz a energia após o uso da habilidade especial
        this.setEnergia(energia); // Atualiza o nível de energia após o uso da habilidade especial
        int vida = this.getSaude() - 3; // Reduz a saúde do Guerreiro em 3
        this.setSaude(vida); // Atualiza o nível de saúde após o uso da habilidade especial
        this.setForca(getForca() + 5); // Aumenta a força do Guerreiro em 5
        System.out.println(getNome() + " usou a habilidade 'Fúria'!"); // Mensagem de uso da habilidade especial
        return 0; // Não retorna dano
    }
}
