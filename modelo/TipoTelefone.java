package modelo;
public enum TipoTelefone{

    Celular(0), Residencial(1), Profissional(2);

    public int valor;

    TipoTelefone(int valor){
        this.valor = valor;
    }

} 