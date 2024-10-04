package Personagens;

// Classe que representa um Mago, especializado em magias
public class Mago extends Personagem {
    private int mana; // Mana do Mago

    // Construtor para inicializar um Mago com os atributos fornecidos
    public Mago(String nome, String titulo, int saude, int energia, int forca, int habilidade, int velocidade, int mana) {
        super(nome, titulo, saude, energia, forca, habilidade, velocidade);
        this.mana = mana;
    }

    // Método para executar a habilidade "Bola de Fogo"
    public int habilidade() {
        int energia = this.getEnergia() - 10; // Reduz a energia após o uso da habilidade
        if (this.mana >= 10) { // Verifica se há mana suficiente para usar a habilidade
            this.mana -= 10; // Reduz a mana após o uso da habilidade
            this.setEnergia(energia); // Atualiza o nível de energia após o uso da habilidade
            System.out.println(getNome() + " usou a habilidade 'Bola de Fogo'!");
            return getHabilidade() * 6; // Retorna o dano da habilidade
        } else {
            System.out.println(getNome() + " não possui mana suficiente para usar a habilidade 'Bola de Fogo'!");
            return 0; // Retorna 0 de dano se não houver mana suficiente
        }
    }

    // Método para executar a habilidade "Explosão Arcana"
    public int especial() {
        if (this.mana >= 15) { // Verifica se há mana suficiente para usar a habilidade especial
            this.mana -= 15; // Reduz a mana após o uso da habilidade especial
            System.out.println(getNome() + " usou a habilidade 'Explosão Arcana'!");
            return getHabilidade() * 10; // Retorna o dano da habilidade especial
        } else {
            System.out.println(getNome() + " não possui mana suficiente para usar a habilidade 'Explosão Arcana'!");
            return 0; // Retorna 0 de dano se não houver mana suficiente
        }
    }

    // Método para recuperar a mana do Mago
    public void recuperaMana() {
        this.mana += 5; // Aumenta a mana do Mago
        System.out.println(getNome() + " recuperou 5 de mana!");
    }
}
