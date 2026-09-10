package school.sptech.api;

public enum TipoPlanta {

    FLOR("Flor"),
    ERVA("Erva"),
    SUCULENTA("Suculenta"),
    ARVORE("Árvore");

    private final String descricao;

    TipoPlanta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPlanta fromDescricao(String descricao) {
        for (TipoPlanta tipo : values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        return null;
    }
}
