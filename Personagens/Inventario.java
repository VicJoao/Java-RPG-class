package Personagens;

import java.util.ArrayList;
import java.util.List;
import Jogo.Item;

// Classe que representa o inventário de um personagem
public class Inventario {
    private List<Item> itens; // Lista de itens no inventário

    // Construtor para inicializar um inventário vazio
    public Inventario() {
        this.itens = new ArrayList<>();
    }

    // Método para adicionar um item ao inventário
    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    // Método para remover um item do inventário
    public void removerItem(Item item) {
        this.itens.remove(item);
    }

    // Método para imprimir os itens do inventário
    public void printInventario() {
        System.out.println("Itens no inventário:");
        int index = 0;
        for (Item item : itens) {
            System.out.print(index + " :");
            item.printItem(); // Chamada da função printItem() de cada item
            index++;
        }
    }

    // Método para usar um item do inventário com base no índice
    public void useItem(int index) {
        if (index >= 0 && index < itens.size()) { // Verifica se o índice é válido
            System.out.println("Usando o item: " + itens.get(index).getNome());
            itens.remove(index); // Remove o item do inventário após o uso
        } else {
            System.out.println("Item inválido!");
        }
    }

    // Método para obter o tamanho do inventário
    public int tamanho() {
        return this.itens.size();
    }

    // Método para obter um item do inventário com base no índice
    public Item getItemIndex(int index) {
        return this.itens.get(index);
    }
}
