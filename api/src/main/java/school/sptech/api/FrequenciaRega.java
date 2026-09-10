package school.sptech.api;

public enum FrequenciaRega {

    DIARIA("Diária"),
    SEMANAL("Semanal"),
    QUINZENAL("Quinzenal");

    private final String descricao;

    FrequenciaRega(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static FrequenciaRega fromDescricao(String descricao) {
        for (FrequenciaRega f : values()) {
            if (f.descricao.equalsIgnoreCase(descricao)) {
                return f;
            }
        }
        return null;
    }
}
