package Terminal;

import java.util.Date;
import java.util.Scanner;
import Block.Bloco;
import Block.Cadeia;
import conteudo.NotaTexto;
import repo.Armazenador;
import validacao.Validador;

public class Console {

    private final Cadeia cadeia;
    private final Validador validador;
    private final Armazenador repositorio;
    private final Scanner scanner;

    public Console(Cadeia cadeia, Validador validador, Armazenador repositorio) {
        this.cadeia = cadeia;
        this.validador = validador;
        this.repositorio = repositorio;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarMenu() {
        int opcao = -1;

        System.out.println("=========================================");
        System.out.println("              BLOCKCHAIN                 ");
        System.out.println("=========================================");

        while (opcao != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Adicionar nova anotacao");
            System.out.println("2. Listar blocos da cadeia");
            System.out.println("3. Validar integridade da cadeia");
            System.out.println("4. Salvar cadeia na memoria");
            System.out.println("5. Carregar cadeia da memoria");
            System.out.println("6. [MODO HACKER] Adulterar um bloco");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Opcao invalida. Digite um numero.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o texto da anotacao: ");
                    String texto = scanner.nextLine();
                    cadeia.adicionarBloco(new NotaTexto(texto));
                    System.out.println("Sucesso: Bloco adicionado!");
                    break;
                case 2:
                    listarCadeia();
                    break;
                case 3:
                    System.out.println("\nIniciando validacao...");
                    validador.validar(cadeia);
                    break;
                case 4:
                    repositorio.salvar(cadeia);
                    break;
                case 5:
                      repositorio.carregar();
                    break;
                case 6:
                    adulterarBloco();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Aviso: Opcao invalida.");
            }
        }
        scanner.close();
    }

    private void listarCadeia() {
        if (cadeia.getBlocos().isEmpty()) {
            System.out.println("A blockchain esta vazia.");
            return;
        }
        System.out.println("\n--- BLOCOS NA CADEIA ---");
        for (Bloco b : cadeia.getBlocos()) {
            System.out.println("Bloco ID: " + b.getId());
            System.out.println("Data Criacao: " + new Date(b.getTimestamp()));
            System.out.println("Conteudo: " + b.getDado().exibicao());
            System.out.println("Hash Ant: " + b.getHashAnterior());
            System.out.println("Hash Atu: " + b.getHashAtual());
            System.out.println("------------------------");
        }
    }

    private void adulterarBloco() {
        if (cadeia.getBlocos().isEmpty()) {
            System.out.println("Aviso: Nao ha blocos para adulterar.");
            return;
        }
        System.out.print("Digite o ID do bloco que deseja hackear: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (id >= 0 && id < cadeia.getBlocos().size()) {
                System.out.print("Digite o novo hash falso: ");
                String hashFalso = scanner.nextLine();
                cadeia.getBlocos().get(id).setHashAtual(hashFalso);
                System.out.println("Aviso: Bloco " + id + " adulterado!");
            } else {
                System.out.println("Erro: Bloco nao encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID invalido.");
        }
    }
}