# Task Management CLI

Este é um gerenciador de tarefas em linha de comando (CLI) desenvolvido em Java, utilizando as bibliotecas **Picocli** para a interface CLI e **Gson** para manipulação de dados JSON. O objetivo é oferecer uma ferramenta simples para criar, ler, atualizar e deletar tarefas (CRUD). Além disso, ele pode ser compilado em um executável nativo usando o **GraalVM**.

## Requisitos

- **Java 17** ou superior
- **Maven**
- **GraalVM** (opcional, para compilar em um executável nativo)

## Instalação

1. Clone o repositório:

   ```bash
   git clone https://github.com/andre-xizto/task-management-cli.git
   cd task-management-cli
   ```

2. Compile o projeto:

   ```bash
   mvn clean package
   ```

3. (Opcional) Compile como um executável nativo com o GraalVM:

   Certifique-se de que o GraalVM esteja configurado corretamente no seu sistema e instale o Native Image:

   ```bash
   gu install native-image
   ```

   Em seguida, execute:

   ```bash
   mvn package -Pnative
   ```

   O executável nativo será gerado no diretório `target`.

## Uso

Após a compilação, você pode executar o programa usando o comando:

```bash
java -jar target/task-management-cli.jar
```

Ou, se você gerou o executável nativo:

```bash
./target/task-management-cli
```

### Comandos Disponíveis

Abaixo estão os comandos principais do CLI:

- **Adicionar uma nova tarefa:**

  ```bash
  java -jar target/task-management-cli.jar add "Nome da Tarefa"
  ```

- **Listar todas as tarefas:**

  ```bash
  java -jar target/task-management-cli.jar list
  ```

- **Atualizar uma tarefa existente:**

  ```bash
  java -jar target/task-management-cli.jar complete <id>
  ```
    ```bash
  java -jar target/task-management-cli.jar inprogress <id>
  ```
    ```bash
  java -jar target/task-management-cli.jar todo <id>
  ```

- **Remover uma tarefa:**

  ```bash
  java -jar target/task-management-cli.jar delete <id>
  ```

- **Ajuda:**

  Para ver todos os comandos e suas opções:

  ```bash
  java -jar target/task-management-cli.jar --help
  ```

## Configuração do Banco de Dados JSON

As tarefas são armazenadas em um arquivo JSON. Por padrão, o arquivo é criado no diretório do usuário com o nome `tasks.json`.

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. Fork o repositório
2. Crie uma nova branch: `git checkout -b minha-nova-feature`
3. Faça suas modificações e commit: `git commit -m 'Minha nova feature'`
4. Faça o push para a branch: `git push origin minha-nova-feature`
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.