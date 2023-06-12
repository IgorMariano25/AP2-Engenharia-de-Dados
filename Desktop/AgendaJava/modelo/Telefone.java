package modelo;
public class Telefone{

    private int id;
    private TipoTelefone tipo;
    private int codigoPais;
    private int codigoArea;
    private int numero;


    public Telefone(TipoTelefone tipo, int codigoPais, int codigoArea, int numero) {
        this.tipo = tipo;
        this.codigoPais = codigoPais;
        this.codigoArea = codigoArea;
        this.numero = numero;
    }

    public Telefone(int id, TipoTelefone tipo, int codigoPais, int codigoArea, int numero) {
        this.id = id;
        this.tipo = tipo;
        this.codigoPais = codigoPais;
        this.codigoArea = codigoArea;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public int getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(int codigoArea) {
        this.codigoArea = codigoArea;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "{'telefone':{'id': "+this.id+", 'tipo': '"+this.tipo+"', 'numero': '+"+this.codigoPais+this.codigoArea+this.numero+"'}}";
    }
}