CREATE TABLE financeiro (
    id SERIAL PRIMARY KEY,
    mes_ano DATE NOT NULL,
    total_receitas NUMERIC(15,2) DEFAULT 0,
    total_despesas NUMERIC(15,2) DEFAULT 0,
    saldo NUMERIC(15,2) DEFAULT 0
);