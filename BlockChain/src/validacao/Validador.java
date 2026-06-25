package validacao;

import Block.Bloco;
import Block.Cadeia;
import hash.CalculadoraHash;
import java.util.List;

public class Validador { 

    private final CalculadoraHash calculadoraHash;

    
    public Validador(CalculadoraHash calculadoraHash) {
        this.calculadoraHash = calculadoraHash;
    }

    public boolean validar(Cadeia cadeia) {
        List<Bloco> blocos = cadeia.getBlocos();

        if (blocos.isEmpty()) {
            return true;
        }

        Bloco genesis = blocos.get(0);
        String hashGenesisRecalculado = calculadoraHash.calcular(genesis.agruparDadosParaHash());
        if (!genesis.getHashAtual().equals(hashGenesisRecalculado)) {
            System.out.println(" INVÁLIDA: Os dados do Bloco Gênesis (0) foram adulterados!");
            return false;
        }

        for (int i = 1; i < blocos.size(); i++) {
            Bloco blocoAtual = blocos.get(i);
            Bloco blocoAnterior = blocos.get(i - 1);

            if (!blocoAtual.getHashAnterior().equals(blocoAnterior.getHashAtual())) {
                return false;
            }

            String hashRecalculado = calculadoraHash.calcular(blocoAtual.agruparDadosParaHash());
            if (!blocoAtual.getHashAtual().equals(hashRecalculado)) {
                System.out.println("INVÁLIDA: Os dados do Bloco " + blocoAtual.getId() + " foram adulterados!");
                return false;
            }
        }

        System.out.println(" VÁLIDA: A blockchain está 100% íntegra.");
        return true;
    }
}