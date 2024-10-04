package Jogo;

import Personagens.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grupo {
    private ArrayList<Personagem> personagens; // Lista de personagens no grupo
    private Inventario inventario; // Inventario do grupo

    // Construtor da classe Grupo
    public Grupo() {
        this.personagens = new ArrayList<>();
        this.inventario = new Inventario();
    }

    // Método para usar um item do inventário
    public int usarItem() {
        Scanner scanner = new Scanner(System.in);
        this.inventario.printInventario(); // Exibe o inventário para o jogador

        // Solicita ao usuário que escolha um índice do item a ser usado
        System.out.print("Escolha o índice do item a ser usado: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < this.inventario.tamanho()) {
            Item item = this.inventario.getItemIndex(index);

            // Solicita ao usuário que escolha um personagem do grupo para aplicar o item
            System.out.println("Escolha o personagem do grupo: ");
            for (int i = 0; i < personagens.size(); i++) {
                System.out.println(i + ". " + personagens.get(i).getNome() + " " + personagens.get(i).getTitulo());
            }
            int escolhido = scanner.nextInt();

            if (escolhido >= 0 && escolhido < personagens.size()) {
                personagens.get(escolhido).aplicarEfeitos(item); // Aplica os efeitos do item ao personagem escolhido
                this.inventario.useItem(index); // Remove o item do inventário após o uso
                return 1; // Retorna 1 se o item foi usado com sucesso
            } else {
                System.out.println("Personagem escolhido inválido.");
                return 0; // Retorna 0 se a escolha do personagem foi inválida
            }
        } else {
            System.out.println("Índice do item incorreto.");
            return 0; // Retorna 0 se o índice do item foi incorreto
        }
    }

    // Método para adicionar um item ao inventário do grupo
    public void ganhaItem(Item item) {
        this.inventario.adicionarItem(item);
    }

    // Método para criar um grupo de personagens
    public void criarGrupo(String nomePersonagemPrincipal) {
        Scanner scanner = new Scanner(System.in);

        // Solicita ao jogador que escolha a classe do personagem principal
        System.out.println("Escolha a classe do personagem (Guerreiro, Arqueiro, Mago): ");
        String classeEscolhida = scanner.nextLine().toLowerCase();

        // Criação do grupo com base na classe escolhida
        switch (classeEscolhida) {
            case "guerreiro":
                personagens.add(new Guerreiro(nomePersonagemPrincipal, "Guerreiro", 30, 100, 13, new Random().nextInt(3) + 1, 4));
                personagens.add(new Arqueiro("Ajudante1","Arqueiro", 20, 100, 10, new Random().nextInt(4) + 1, new Random().nextInt(3) + 1, 6));
                personagens.add(new Mago("Ajudante2","Mago", 15, 100, 10, new Random().nextInt(3) + 1, 3, 50));
                break;
            case "arqueiro":
                personagens.add(new Arqueiro(nomePersonagemPrincipal,"Arqueiro", 20, 100, 10, new Random().nextInt(4) + 1, new Random().nextInt(3) + 1, 6));
                personagens.add(new Guerreiro("Ajudante1","Guerreiro", 30, 100, 13, new Random().nextInt(3) + 1, 4));
                personagens.add(new Mago("Ajudante2","Mago", 15, 100, 10, new Random().nextInt(3) + 1, 3, 50));
                break;
            case "mago":
                personagens.add(new Mago(nomePersonagemPrincipal, "Mago",15, 100, 10, new Random().nextInt(3) + 1, 3, 50));
                personagens.add(new Guerreiro("Ajudante1", "Guerreiro",30, 100, 13, new Random().nextInt(3) + 1, 4));
                personagens.add(new Arqueiro("Ajudante2", "Arqueiro",20, 100, 10, new Random().nextInt(4) + 1, new Random().nextInt(3) + 1, 6));
                break;
            default:
                System.out.println("Classe inválida. O grupo será criado com Guerreiro, Arqueiro e Mago.");
                personagens.add(new Guerreiro(nomePersonagemPrincipal, "Guerreiro",30, 100, 13, new Random().nextInt(3) + 1, 4));
                personagens.add(new Arqueiro("Ajudante1", "Arqueiro", 20, 100, 10, new Random().nextInt(4) + 1, new Random().nextInt(3) + 1, 6));
                personagens.add(new Mago("Ajudante2", "Mago",15, 100, 10, new Random().nextInt(3) + 1, 3, 50));
                break;
        }
    }

    // Método para receber dano em um personagem específico do grupo
    public void receberDano(int poss, int dano, int vel) {
        personagens.get(poss).defender(dano, vel);
    }

    // Método para calcular a velocidade média do grupo
    public int getVelocidade() {
        int velocidade = 0;
        for (Personagem personagem : personagens) {
            velocidade += personagem.getVelocidade();
        }
        return velocidade / 3; // Retorna a média de velocidade dos personagens do grupo
    }

    // Método para exibir o status de todos os personagens do grupo
    public String status() {
        StringBuilder status = new StringBuilder();
        String nome, titulo;
        int saude, mana, habilidade, energia;

        // Itera sobre todos os personagens do grupo e monta a string de status
        for (Personagem personagem : personagens) {
            nome = personagem.getNome();
            saude = personagem.getSaude();
            titulo = personagem.getTitulo();
            habilidade = personagem.getHabilidade();
            energia = personagem.getEnergia();

            status.append("\n\n").append(titulo).append(" ").append(nome).append(" LV.").append(habilidade)
                    .append("\n Saude:").append(saude).append("\n Energia:").append(energia);
        }
        return status.toString(); // Retorna a string de status do grupo
    }

    // Método para remover um personagem morto do grupo
    public void matarPersonagem(int poss) {
        System.out.println("ALIADO " + personagens.get(poss).getNome() + " MORTO!");
        personagens.remove(poss); // Remove o personagem morto do grupo
    }

    // Método para verificar se algum personagem do grupo morreu
    public boolean verificaMortos() {
        for (Personagem personagem : personagens) {
            if (personagem.getSaude() <= 0) {
                matarPersonagem(personagens.indexOf(personagem)); // Remove o personagem morto do grupo
                return true; // Retorna true se algum personagem morreu
            }
        }
        return false; // Retorna false se nenhum personagem morreu
    }

    // Método para verificar se o grupo venceu
    public boolean venceu() {
        return personagens.size() <= 0; // Retorna true se o grupo não possui mais personagens vivos
    }

    // Método para receber um item do drop e adicioná-lo ao inventário do grupo
    public void recebeItem(Item drop) {
        System.out.println("Item recebido: ");
        drop.printItem();
        inventario.adicionarItem(drop); // Adiciona o item ao inventário do grupo
    }

    // Getter para obter a lista de personagens do grupo
    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    // Getter para obter o inventário do grupo
    public Inventario getInventario() {
        return inventario;
    }

    // Getter para obter o número de personagens no grupo
    public int getLength() {
        return personagens.size();
    }
}
