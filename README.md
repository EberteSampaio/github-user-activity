# GitHub User Activity

CLI em Java que busca a atividade recente de um usuário do GitHub e exibe os eventos no terminal, usando a API pública do GitHub (`https://api.github.com/users/{username}/events`).

Exemplo de saída:

```
Pushed to eberte/github-user-activity
Opened a issue in eberte/algum-repo
The repository outro-usuario/projeto has been favorited.
Forked a repository
```

## Eventos suportados

Push, criação/exclusão de branch ou tag, issues, comentários em issues e commits, pull requests, reviews e comentários de review, fork, watch (favoritar), release, discussão, wiki (Gollum), novo membro e repositório tornado público. Eventos fora dessa lista aparecem como `Unknown event was made`.

## Requisitos

- JDK 25 ou superior (o projeto usa arquivo-fonte compacto, sem declaração de classe no `Main.java`)
- Maven 3.9 ou superior
- Conexão com a internet (a API do GitHub é consultada em tempo real, sem autenticação)

## Como rodar

### 1. Clonar o projeto

```bash
git clone <url-do-repositorio>
cd github-user-activity
```

### 2. Compilar

```bash
mvn clean compile
```

As classes compiladas ficam em `target/classes`.

### 3. Copiar as dependências

O projeto depende do Jackson para converter o JSON da API. Este comando baixa os jars para `target/libs`:

```bash
mvn dependency:copy-dependencies -DoutputDirectory=target/libs
```

### 4. Executar

Passe o nome do usuário do GitHub como argumento.

Linux/macOS:

```bash
java -cp "target/classes:target/libs/*" Main <username>
```

Windows (o separador do classpath é `;`):

```bash
java -cp "target/classes;target/libs/*" Main <username>
```

Exemplo:

```bash
java -cp "target/classes:target/libs/*" Main torvalds
```

### Alternativa: rodar direto pelo Maven

Sem precisar montar o classpath manualmente:

```bash
mvn compile exec:java -Dexec.mainClass=Main -Dexec.args="<username>"
```

Também é possível rodar pelo IntelliJ IDEA: abra o projeto, configure o argumento de programa na run configuration do `Main` e execute.

## Tratamento de erros

- Sem argumento: `Username is required`
- Usuário inexistente (HTTP 404): lança `UserNotFoundException`
- Usuário sem eventos recentes: `There are no events for this user.`
- Demais erros da API (HTTP >= 400): lança `GitHubApiException` com o status e o corpo da resposta

A API do GitHub sem autenticação tem limite de 60 requisições por hora por IP. Ao estourar o limite, a resposta vem com HTTP 403 e o programa reporta o erro.

## Estrutura do projeto

```
src/main/java/
├── Main.java                          # ponto de entrada, lê o username dos argumentos
├── client/
│   └── ClientHttpConfiguration.java   # HttpClient com timeout e tratamento de status HTTP
├── command/
│   ├── Command.java                   # interface dos comandos
│   ├── CommandExecutor.java           # executa um comando
│   └── impl/
│       └── FetchGithubActivityUserCommand.java
├── domain/
│   ├── Event.java                     # evento retornado pela API
│   ├── EventType.java                 # enum com os tipos de evento conhecidos
│   ├── Actor.java
│   └── Repository.java
├── exceptions/                        # exceções de domínio e de API
├── service/
│   └── GetActivityUseCase.java        # chama a API e converte a resposta em eventos
└── utils/
    ├── GitHubActivityFormatter.java   # formata cada evento em uma linha legível
    └── MapperUtils.java               # desserialização JSON com Jackson
```
---

## Inspiração

Projeto baseado no desafio [Task Tracker](https://roadmap.sh/projects/github-user-activity) do roadmap.sh.