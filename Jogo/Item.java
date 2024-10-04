package Jogo;

// Classe que representa um item no jogo
public class Item {
    private int energia;
    private int saude;
    private int habilidade;
    private String nome;

    // Construtor padrão vazio
    public Item() {
        // O construtor padrão está vazio, mas pode ser útil se houver necessidade de inicialização
    }

    // Construtor com parâmetros para criar um item com valores específicos
    public Item(int energia, int saude, int habilidade, String nome) {
        this.energia = energia;
        this.saude = saude;
        this.habilidade = habilidade;
        this.nome = nome;
    }

    // Métodos de acesso aos atributos
    public int getEnergia() {
        return energia;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public int getSaude() {
        return saude;
    }

    public String getNome() {
        return nome;
    }

    // Método para imprimir informações do item
    public void printItem() {
        System.out.println("Nome: " + nome + ", Energia: " + energia + ", Saúde: " + saude + ", Habilidade: " + habilidade);
    }

    // Método para definir o drop do item (cópia dos valores de outro item)
    public void setDrop(Item drop) {
        nome = drop.getNome();
        energia = drop.getEnergia();
        saude = drop.getSaude();
        habilidade = drop.getHabilidade();
    }
}
