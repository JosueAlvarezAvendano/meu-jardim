package school.sptech.api;

public class Planta {

    private Integer id;
    private String nome;
    private String especie;
    private String tipo;
    private String frequenciaRega;
    private String nivelLuz;
    private String descricao;

    public Planta() {
    }

    public Planta(Integer id, String nome, String especie, String tipo, String frequenciaRega, String nivelLuz, String descricao) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.tipo = tipo;
        this.frequenciaRega = frequenciaRega;
        this.nivelLuz = nivelLuz;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFrequenciaRega() {
        return frequenciaRega;
    }

    public void setFrequenciaRega(String frequenciaRega) {
        this.frequenciaRega = frequenciaRega;
    }

    public String getNivelLuz() {
        return nivelLuz;
    }

    public void setNivelLuz(String nivelLuz) {
        this.nivelLuz = nivelLuz;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
