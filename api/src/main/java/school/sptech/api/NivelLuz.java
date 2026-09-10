package school.sptech.api;

public enum NivelLuz {

    PLENO_SOL("Pleno sol"),
    MEIA_SOMBRA("Meia sombra"),
    SOMBRA("Sombra");

    private final String descricao;

    NivelLuz(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static NivelLuz fromDescricao(String descricao) {
        for (NivelLuz n : values()) {
            if (n.descricao.equalsIgnoreCase(descricao)) {
                return n;
            }
        }
        return null;
    }
}
