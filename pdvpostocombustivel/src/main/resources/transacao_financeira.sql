CREATE TABLE transacao_financeira (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    tipo VARCHAR(20) CHECK (tipo IN ('RECEITA', 'DESPESA')),
    valor NUMERIC(15,2) NOT NULL,
    data DATE NOT NULL,
    financeiro_id INTEGER REFERENCES financeiro(id) ON DELETE CASCADE
);
