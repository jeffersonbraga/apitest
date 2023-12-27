# API Teste TEXO

- Requisitos
  - JDK 21
  - sudo apt install openjdk-21-jdk

- Rodar o sistema:
  - DOCKER 
    - docker-compose up --build
  - Diretamente via terminal
    - mvn clean
    - mvn compiler:compile
    - mvn install
    - java -jar target/apitest-0.0.1-SNAPSHOT.jar

- Efetuar chamada para URL http://localhost:8080/movies/awards/interval
