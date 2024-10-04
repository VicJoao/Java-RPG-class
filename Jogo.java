import Monstros.Monstro;
import Personagens.Arqueiro;
import Personagens.Guerreiro;
import Personagens.Mago;
import Personagens.Personagem;
import Jogo.Grupo;
import Jogo.Item;
import Jogo.Masmorra;
import Jogo.Combate;

import java.util.Random;
import java.util.Scanner;

// Classe que representa o jogo principal
public class Jogo {
    Grupo grupo; // Grupo de personagens do jogador
    Masmorra masmorra = new Masmorra(); // Masmorra onde ocorrem os combates
    private Scanner scanner;

    // Construtor da classe Jogo
    public Jogo() {
        this.scanner = new Scanner(System.in);
    }

    // Método para iniciar o jogo
    public void iniciarJogo() {
        System.out.println("Bem-vindo ao Jogo!");

        // Escolha da classe do personagem principal
        System.out.print("Escolha o nome do personagem: ");
        String nomeEscolhido = scanner.nextLine();

        // Criação do grupo com personagens ajudantes
        Grupo grupo = new Grupo();
        grupo.criarGrupo(nomeEscolhido);
        this.grupo = grupo;

        // Exibição dos personagens do grupo
        System.out.println("\nPersonagens do grupo:");
        for (Personagem personagem : grupo.getPersonagens()) {
            System.out.println("Nome: " + personagem.getNome());
            System.out.println("Classe: " + personagem.getTitulo());
            System.out.println("Vida: " + personagem.getSaude());
            System.out.println("Força: " + personagem.getForca());
            System.out.println("Habilidade: " + personagem.getHabilidade());
            System.out.println("Velocidade: " + personagem.getVelocidade() + "\n");
        }
    }

    // Método para exibir o menu de inventário
    public void inventarioMenu() {
        this.grupo.getInventario().printInventario();
        System.out.println("1. Usar algum item");
        System.out.println("0. Voltar");
        int usar = scanner.nextInt();
        if (usar == 0) {
            acaoRodada();
        } else {
            this.grupo.usarItem();
        }
    }

    // Método para exibir o menu de ataque
    public int ataqueMenu() {
        int dano = 0;

        for (Personagem personagem : grupo.getPersonagens()) {
            if (!personagem.getNome().equals("Ajudante1") && !personagem.getNome().equals("Ajudante2")) {
                System.out.println("-------------" +
                        "\n1. Ataque normal" +
                        "\n2. Usar habilidade" +
                        "\n3. Ataque Especial");

                Scanner scanner = new Scanner(System.in);
                int escolha = scanner.nextInt();

                switch (escolha) {
                    case 1:
                        int ataqueNormal = personagem.atacar();
                        System.out.println(personagem.getNome() + " Causou " + ataqueNormal + " de dano");
                        dano += ataqueNormal;
                        break;
                    case 2:
                        if (personagem instanceof Mago) {
                            int danoHabilidadeMago = ((Mago) personagem).habilidade();
                            System.out.println(personagem.getNome() + " Usou habilidade de Mago e causou " + danoHabilidadeMago + " de dano");
                            dano += danoHabilidadeMago;
                        } else if (personagem instanceof Arqueiro) {
                            int danoHabilidadeArqueiro = ((Arqueiro) personagem).habilidade();
                            System.out.println(personagem.getNome() + " Usou habilidade de Arqueiro e causou " + danoHabilidadeArqueiro + " de dano");
                            dano += danoHabilidadeArqueiro;
                        } else if (personagem instanceof Guerreiro) {
                            int danoHabilidadeGuerreiro = ((Guerreiro) personagem).habilidade();
                            System.out.println(personagem.getNome() + " Usou habilidade de Guerreiro e causou " + danoHabilidadeGuerreiro + " de dano");
                            dano += danoHabilidadeGuerreiro;
                        } else {
                            System.out.println("Personagem não reconhecido.");
                        }
                        break;
                    case 3:
                        if (personagem instanceof Mago) {
                            int danoHabilidadeMago = ((Mago) personagem).especial();
                            System.out.println(personagem.getNome() + " Usou Especial de Mago e causou " + danoHabilidadeMago + " de dano");
                            dano += danoHabilidadeMago;
                        } else if (personagem instanceof Arqueiro) {
                            int danoHabilidadeArqueiro = ((Arqueiro) personagem).especial();
                            System.out.println(personagem.getNome() + " Usou Especial de Arqueiro e causou " + danoHabilidadeArqueiro + " de dano");
                            dano += danoHabilidadeArqueiro;
                        } else if (personagem instanceof Guerreiro) {
                            int danoHabilidadeGuerreiro = ((Guerreiro) personagem).especial();
                            System.out.println(personagem.getNome() + " Usou Especial de Guerreiro e causou " + danoHabilidadeGuerreiro + " de dano");
                            dano += danoHabilidadeGuerreiro;
                        } else {
                            System.out.println("Personagem não reconhecido.");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } else {
                int ataque = personagem.atacar();
                System.out.println(personagem.getNome() + " Causou " + ataque + " de dano");
                dano += ataque;
            }
        }

        return dano;
    }

    // Método para tentar fugir do combate
    public void fugir() {
        int velocidadeTT = 0;
        Monstro monstroAtual = this.masmorra.getMonstros().get(0);
        for (Personagem personagem : this.grupo.getPersonagens()) {
            velocidadeTT += personagem.getVelocidade();
        }
        if (velocidadeTT >= monstroAtual.getVelocidade() * 3) {
            System.out.println("VOCÊ FUGIU");
            masmorra.matarMonstro();
        } else {
            int dano = monstroAtual.atacar();
            int vel = monstroAtual.getVelocidade();
            for (Personagem personagem : grupo.getPersonagens()) {
                personagem.defender(dano, vel);
            }
        }
    }

    // Método para realizar uma ação na rodada de combate
    public int acaoRodada() {
        int dano = 0;
        System.out.println(
                "----------------" +
                        "\nO que você irá fazer?" +
                        "\n0. Sair do Jogo" +
                        "\n1. Inventário" +
                        "\n2. Atacar" +
                        "\n3. Tentar Fugir" +
                        "\n----------------");
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 0:
                System.exit(0);
                break;
            case 1:
                this.inventarioMenu();
                break;
            case 2:
                dano = this.ataqueMenu();
                break;
            case 3:
                this.fugir();
                dano = -1;
                break;
            default:
                System.out.println("Escolha errada");
                acaoRodada();
        }
        return dano;
    }

    // Método para iniciar uma rodada de combate
    public void iniciarRodada() {
        while (true) {
            System.out.println(
                    "--------NOVO ROUND--------" + grupo.status() + masmorra.status() +
                            "\n-------------------------");
            int danoGrupo = 0;
            Item drop = new Item();
            int danoMonstro = 0;
            int velocidadeGrupo;
            int velocidadeMonstro;
            Random rand = new Random();
            int alvo;
            // Jogador escolhe ação
            danoGrupo += acaoRodada();
            if (danoGrupo == -1) {
                grupo.verificaMortos();
                masmorra.verificaMortos(drop);
                return;
            }
            danoMonstro += this.masmorra.getMonstros().get(0).atacar();
            velocidadeMonstro = this.masmorra.getMonstros().get(0).getVelocidade();
            velocidadeGrupo = this.grupo.getVelocidade();
            alvo = rand.nextInt(grupo.getLength());
            this.grupo.receberDano(alvo, danoMonstro, velocidadeMonstro);
            this.masmorra.recebeDano(danoGrupo, velocidadeGrupo);
            // Verifica quem ganhou e exclui o morto do Grupo ou da Masmorra.
            if (grupo.verificaMortos()) {
                return;
            }
            if (masmorra.verificaMortos(drop)) {
                grupo.recebeItem(drop);
            }
        }
    }

    // Método principal para iniciar o jogo
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
        jogo.masmorra.criarMonstros();
        System.out.println("Prepare-se para enfrentar a masmorra:");
        jogo.masmorra.printMasmorra();
        while (!jogo.masmorra.venceu() || jogo.grupo.venceu()) {
            jogo.iniciarRodada();
            if (jogo.masmorra.venceu()) {
                System.out.println("FIM DE JOGO, VOCÊ GANHOU");
            } else if (jogo.grupo.venceu()) {
                System.out.println("FIM DE JOGO, VOCÊ PERDEU");
            }
        }
    }
}
