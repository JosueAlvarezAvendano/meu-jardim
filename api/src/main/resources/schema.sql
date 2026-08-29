CREATE TABLE IF NOT EXISTS planta (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    especie         VARCHAR(100) NOT NULL,
    tipo            VARCHAR(50)  NOT NULL,
    frequenciaRega  VARCHAR(50)  NOT NULL,
    nivelLuz        VARCHAR(50)  NOT NULL,
    descricao       VARCHAR(255)
);