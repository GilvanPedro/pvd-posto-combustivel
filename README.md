# PDV Posto de Combustível

Aplicação backend em Spring Boot para gestão de produtos, preços, estoque, movimentações e pessoas em um PDV de posto de combustível.

Este README foi elaborado com base no diagrama de domínio/funcionalidades fornecido e no código atual do repositório.

## Stack
- **Java**: 17 (`pom.xml`)
- **Spring Boot**: 3.3.x (`spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`)
- **Banco de dados**: PostgreSQL (driver `org.postgresql:postgresql`)
- **Documentação de API**: SpringDoc OpenAPI (Swagger UI)

## Arquitetura (alto nível)
- Camada de API/Controllers em `src/main/java/com/br/pdvpostocombustivel/controller/` e `api/pessoa/`.
- Camada de Serviço em `src/main/java/com/br/pdvpostocombustivel/service/` e `api/pessoa/`.
- Persistência via Spring Data JPA em `domain/repository/` e entidades em `domain/entity/`.

Principais entidades já modeladas:
- `Produto`, `Estoque`, `MovimentacaoEstoque`, `Preco`, `Pessoa`.

## Como executar
1. Crie um banco PostgreSQL local.
2. Configure as variáveis de ambiente (recomendado) ou ajuste `src/main/resources/application.properties`:
   - SPRING_DATASOURCE_URL (ex.: `jdbc:postgresql://localhost:5432/postgres`)
   - SPRING_DATASOURCE_USERNAME
   - SPRING_DATASOURCE_PASSWORD
   - SERVER_PORT (ex.: `8080`)

   Observação: o arquivo atual define `server.port=5432`, que conflita com a porta padrão do PostgreSQL. Ajuste para `8080` ou outra porta livre.

3. Rodar com Maven:
```bash
mvn spring-boot:run
```

4. Swagger UI:
- Acesse: `http://localhost:8080/swagger-ui/index.html`

## Endpoints atuais
Referências de código: veja os controllers em `controller/` e `api/pessoa/`.

- **Produtos** (`ProdutoController` | base: `/api/produtos`)
  - `POST /api/produtos` — cria produto (cria registro de estoque junto).
  - `GET /api/produtos` — lista produtos.
  - `GET /api/produtos/{id}` — busca por id.
  - `PUT /api/produtos/{id}` — atualiza produto (sincroniza quantidade no `Estoque`).
  - `DELETE /api/produtos/{id}` — remove produto e o `Estoque` vinculado.

- **Estoque** (`EstoqueController` | base: `/api/estoques`)
  - `POST /api/estoques/movimentar/{produtoId}?quantidade={q}&tipoMovimento=ENTRADA|SAIDA` — registra movimento e atualiza saldo.
  - `GET /api/estoques` — lista estoques.
  - `GET /api/estoques/{produtoId}` — detalhe do estoque por produto.
  - `PUT /api/estoques/{produtoId}/minimo?novaMinima={valor}` — define quantidade mínima.

- **Movimentações de Estoque** (`MovimentacaoEstoqueController` | base: `/api/movimentacoes`)
  - `POST /api/movimentacoes/entrada/{produtoId}?quantidade={q}` — registra entrada.
  - `POST /api/movimentacoes/saida/{produtoId}?quantidade={q}` — registra saída.
  - `GET /api/movimentacoes` — lista movimentações.

- **Preços** (`PrecoController` | base: `/api/precos`)
  - `POST /api/precos/definir/{produtoId}?valor={decimal}` — define novo preço (encerra o anterior e ativa o novo).
  - `GET /api/precos/atual/{produtoId}` — retorna preço vigente do produto.
  - `GET /api/precos/historico/{produtoId}` — histórico de preços.
  - `DELETE /api/precos/{precoId}` — remove um preço específico.

- **Pessoas** (`PessoaController` | base: `/api/v1/pessoas`)
  - `POST /api/v1/pessoas` — cria pessoa.
  - `GET /api/v1/pessoas/{id}` — obtém por id.
  - `GET /api/v1/pessoas?cpfCnpj=...` — obtém por CPF/CNPJ.
  - `GET /api/v1/pessoas?page=&size=&sortBy=&dir=` — lista paginado.
  - `PUT /api/v1/pessoas/{id}` — substitui registro.
  - `PATCH /api/v1/pessoas/{id}` — atualiza parcialmente.
  - `DELETE /api/v1/pessoas/{id}` — remove registro.

## Modelo de Dados (resumo)
- `Produto` — nome, categoria, preço (campo na entidade), quantidadeEstoque (espelhado com `Estoque`).
- `Estoque` — 1:1 `Produto`, quantidadeAtual, quantidadeMinima, tipoMovimento, ultimaAtualizacao.
- `MovimentacaoEstoque` — N:1 `Estoque` e N:1 `Produto`, quantidade, tipo (ENTRADA|SAIDA), dataHora.
- `Preco` — N:1 `Produto`, valor, dataInicio, dataFim, ativo (mantém histórico e preço vigente).
- `Pessoa` — nomeCompleto, cpfCnpj (único), numeroCtps, dataNascimento, tipoPessoa.

## Alinhamento com o diagrama de funcionalidades
Do diagrama apresentado (Cliente/Usuário, Funcionário, Gerente/Admin), os seguintes blocos estão cobertos ou iniciados:

- **Cliente/Usuário**
  - **Consultar Preço**: coberto via `GET /api/precos/atual/{produtoId}`.
  - **Consultar Produto**: coberto via `GET /api/produtos` e `GET /api/produtos/{id}`.

- **Funcionário**
  - **Gerenciar Inventário / Estoque**: coberto com endpoints de movimentação e consulta em `EstoqueController` e `MovimentacaoEstoqueController`.
  - **Atualizar Preço**: coberto em `PrecoController`.

- **Gerente/Admin**
  - **Gerenciar Produtos**: CRUD em `ProdutoController`.
  - **Gerenciar Pessoas**: CRUD + paginação em `PessoaController` (base para clientes, funcionários, fornecedores).

Itens ainda não implementados (presentes no diagrama):
- **Gerenciar Acesso** (autenticação/autorização, perfis/roles).
- **Gerenciar Contatos** (contatos de pessoas/fornecedores/cliente).
- **Cadastrar Funcionário** (vínculo `Pessoa` com papel Funcionário, dados contratuais).
- **Consultar Rendimento** (dashboards KPI, vendas, margem).
- **Gerenciar Custos**: custos fixos, custos de produtos, consolidação de custos gerais.
- **Gerenciar Balanço Patrimonial**: relatórios e ajustes de valores.
- **Registrar Local/Validade** de itens (para produtos com validade e localização física de estoque).
- **Controlar Quantidade** com regras avançadas (alertas abaixo do mínimo, reservas, contagens cíclicas).

## Roadmap (próximos passos)
1. Segurança e Acesso
   - Spring Security + JWT; criação de perfis: `CLIENTE`, `FUNCIONARIO`, `GERENTE`, `ADMIN`.
   - Políticas de acesso por endpoint.
2. Pessoas/Contatos/Funcionários
   - Tabelas de contatos (telefone, e-mail), endereços.
   - Vínculo de `Pessoa` com papel (cliente/funcionário/fornecedor) e dados trabalhistas para funcionário.
3. Inventário avançado
   - Contagem cíclica e auditoria de inventário.
   - Localização de estoque (ex.: tanque/bomba/depósito) e validade de produtos.
   - Alertas (estoque abaixo do mínimo) e reservas.
4. Custos e Financeiro
   - Modelos: `CustoFixo`, `CustoProduto`, consolidação de custos gerais.
   - Relatórios de margem, rentabilidade e balanço patrimonial.
5. Vendas/PDV
   - Ordem de venda, itens, pagamento, integração com preço vigente.
   - Consulta de rendimento/relatórios por período.
6. Qualidade e Observabilidade
   - Tratamento global de exceções (ControllerAdvice) + mensagens de erro padronizadas.
   - Validações de entrada (Bean Validation) com respostas amigáveis.
   - Migrações com Flyway (`spring-boot-starter-flyway`).
   - Testes: unitários (JUnit/Mockito) e integração (Spring Boot Test, Testcontainers para PostgreSQL).
7. Configuração
   - Padronizar `server.port=8080` em `application.properties`.
   - Externalizar segredos via variáveis de ambiente (não versionar senhas). 
   - Perfis `dev`/`prod` (`application-dev.properties`, `application-prod.properties`).

## Exemplos de uso (curl)
```bash
# Criar produto
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Gasolina Comum","categoria":"COMBUSTIVEL","preco":5.79,"quantidadeEstoque":100}'

# Definir preço
curl -X POST "http://localhost:8080/api/precos/definir/1?valor=5.99"

# Entrada em estoque
curl -X POST "http://localhost:8080/api/movimentacoes/entrada/1?quantidade=50"

# Consultar preço atual
curl http://localhost:8080/api/precos/atual/1
```

## Estrutura do projeto (resumo)
```
src/main/java/com/br/pdvpostocombustivel/
  PdvpostocombustivelApplication.java
  controller/
    ProdutoController.java
    EstoqueController.java
    MovimentacaoEstoqueController.java
    PrecoController.java
  api/pessoa/
    PessoaController.java
    PessoaService.java
    dto/
      PessoaRequest.java
      PessoaResponse.java
  service/
    ProdutoService.java
    EstoqueService.java
    PrecoService.java
    MovimentacaoEstoqueService.java
  domain/entity/
    Produto.java
    Estoque.java
    MovimentacaoEstoque.java
    Preco.java
    Pessoa.java
  domain/repository/
    ProdutoRepository.java
    EstoqueRepository.java
    MovimentacaoEstoqueRepository.java (existe no projeto)
    PrecoRepository.java
    PessoaRepository.java
```

## Boas práticas e observações
- Evite manter credenciais no `application.properties` versionado. Prefira variáveis de ambiente.
- Considere mover o preço atual para `Preco` como fonte de verdade (já implementado via flag `ativo`), e remover o campo `preco` de `Produto` no futuro para evitar divergência.
- Ative logs somente em desenvolvimento.
