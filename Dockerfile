# Etapa de Build
FROM ubuntu:latest AS build

# Definir o diretório de trabalho
WORKDIR /app

# Atualizar pacotes e instalar dependências necessárias
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    rm -rf /var/lib/apt/lists/*

# Copiar arquivos de configuração
COPY pom.xml .

# Baixar dependências antes de copiar o código fonte
RUN mvn dependency:go-offline

# Copiar o restante do código fonte
COPY src ./src

# Compilar a aplicação
RUN mvn clean install

# Etapa de Runtime
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Expor a porta que a aplicação usa
EXPOSE 8080

# Copiar o artefato construído da etapa de build
COPY --from=build /app/target/todolist-1.0.0.jar app.jar

# Definir o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
