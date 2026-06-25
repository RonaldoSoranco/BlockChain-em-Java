package repo;

import Block.Cadeia;

public interface Armazenador {
    void salvar(Cadeia cadeia);
    Cadeia carregar();
}