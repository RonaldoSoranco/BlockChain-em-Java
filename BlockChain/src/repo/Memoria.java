package repo;

import Block.Cadeia;

public class Memoria implements Armazenador { 
    
    private Cadeia cadeiaSalva;

    @Override
    public void salvar(Cadeia cadeia) {
        this.cadeiaSalva = cadeia;
        System.out.println(" Cadeia salva na memória com sucesso!");
    }

    @Override
    public Cadeia carregar() {
        if (this.cadeiaSalva == null) {
            System.out.println("Nenhuma cadeia encontrada na memória.");
            return null;
        }
        System.out.println("Cadeia carregada da memória com sucesso!");
        return this.cadeiaSalva;
    }
}