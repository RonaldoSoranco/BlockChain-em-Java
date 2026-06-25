import Block.Cadeia;
import hash.CalculadoraHash;
import hash.SHA256Hash;
import repo.Armazenador;
import repo.Memoria;
import validacao.Validador;
import Terminal.Console;

public class Main {

    public static void main(String[] args) {
     
        CalculadoraHash sha256 = new SHA256Hash();
        Cadeia cadeia = new Cadeia(sha256);
        Validador validador = new Validador(sha256);
        Armazenador repositorio = new Memoria();

        Console cli = new Console(cadeia, validador, repositorio);

     
        cli.iniciarMenu();
    }
}