CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATETIME NOT NULL,
    status VARCHAR(10) NOT NULL,
    data_criacao DATETIME NOT NULL,
    usuario_criacao VARCHAR(255) NOT NULL,
    data_atualizacao DATETIME,
    usuario_atualizacao VARCHAR(255),
    data_remocao DATETIME,
    usuario_remocao VARCHAR(255),
    rua VARCHAR(100) NOT NULL,
    numero INT NOT NULL,
    complemento VARCHAR(50),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL
);

INSERT INTO usuarios (
    cpf, nome, data_nascimento, status, data_criacao, usuario_criacao,
    rua, numero, complemento, bairro, cidade, estado, cep
) VALUES (
    '12345678901',
    'Sirio Libanes',
    '1980-01-01 00:00:00',
    'ATIVO',
    NOW(),
    'admin',
    'Rua das Flores',
    123,
    'Apto 101',
    'Centro',
    'Cidade Exemplo',
    'SP',
    '12345678'
);