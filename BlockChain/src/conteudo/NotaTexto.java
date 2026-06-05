package conteudo;

public class NotaTexto implements Conteudo {

    private String texto;

    public NotaTexto(String texto){
        this.texto = texto;
    }

    @Override
    public String serializarHash(){
        return this.texto;
    }

    @Override public String exibicao() {
        return this.texto;
    }

}
