package Block;

import java.util.ArrayList;
import java.util.List;
import hash.CalculadoraHash;
import conteudo.Conteudo;

public class Cadeia {
    private final List<Bloco> blocos;
    private final CalculadoraHash calculadoraHash;

    
    public Cadeia(CalculadoraHash calculadoraHash) {
        this.blocos = new ArrayList<>();
        this.calculadoraHash = calculadoraHash;
    }

    
    public void adicionarBloco(Conteudo dado) {
        int id = blocos.size();
        long timestamp = System.currentTimeMillis();
        String hashAnterior;

        if (blocos.isEmpty()) {
            hashAnterior = "0";
        } else {
            hashAnterior = getUltimoBloco().getHashAtual();
        }

        Bloco novoBloco = new Bloco(id, timestamp, dado, hashAnterior);
        
        String hashCalculado = calculadoraHash.calcular(novoBloco.agruparDadosParaHash());
        novoBloco.setHashAtual(hashCalculado);

        blocos.add(novoBloco);
    }

    public Bloco getUltimoBloco() {
        if (blocos.isEmpty()) {
            return null;
        }
        return blocos.get(blocos.size() - 1);
    }

    
    public List<Bloco> getBlocos() {
        return new ArrayList<>(blocos);
    }
}