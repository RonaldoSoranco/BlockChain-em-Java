package Block;

import conteudo.Conteudo;

public class Bloco {
    private final int id;
    private final long timestamp;
    private final Conteudo dado;
    private final String hashAnterior;
    private String hashAtual;

    public Bloco(int id, long timestamp, Conteudo dado, String hashAnterior) {
        this.id = id;
        this.timestamp = timestamp;
        this.dado = dado;
        this.hashAnterior = hashAnterior;
    }   

    public String agruparDadosParaHash() {
        return id + Long.toString(timestamp) + dado.serializarHash() + hashAnterior;
    }

        public int getId() { return id; }
        public long getTimestamp() { return timestamp; }
        public Conteudo getDado() { return dado; }
        public String getHashAnterior() { return hashAnterior; }
        public String getHashAtual() { return hashAtual; }
    
        public void setHashAtual(String hashAtual) {
            this.hashAtual = hashAtual;
    }
}
