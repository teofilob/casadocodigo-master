## Ambiente 

 A aplicação mantém Java 8 e APIs `javax.*`. Use Maven 3.9.x e Tomcat 9 atualizado para compilar e implantar o WAR. Tomcat 7/8 não são mais a referência; Tomcat 10/11 exigem migração para Jakarta.

 As dependências foram atualizadas dentro dessa compatibilidade. Spring 5.3, Spring Security 5.8 e Hibernate 5.6 são linhas legadas sem suporte comunitário; esta atualização não substitui uma futura migração para versões principais suportadas. Consulte `.agente/dependencias.md` para versões, critérios e verificações.
 
## MySQL

O driver atualizado exige MySQL 8.0 ou superior. O banco `casadocodigo` deve existir. Confira as credenciais em `JPAConfiguration`; há uma senha configurada no código. No perfil `dev`, o Hibernate usa `update`, não recriação das tabelas. O perfil `prod` usa PostgreSQL e ainda está configurado com `create-drop`: revise essa política antes de usar dados persistentes.

## Compilação

 Para compilar immporte o projeto no Eclipse (*Import as Maven Projeto*) ou compile na linha de comando usando Maven:

	mvn clean package

Para executar apenas os testes:

    mvn test

O perfil Spring `test` utiliza H2 em memória e não acessa o MySQL local. Os testes verificam persistência, MVC, login, autorização, CSRF e JSON; não substituem a validação de implantação com MySQL/PostgreSQL e integrações externas.

O pacote gerado é `target/casadocodigo.war`. O build também copia o runner Tomcat 9 para `target/dependency/webapp-runner.jar`. Para execução local, após configurar o banco:

    java -jar target/dependency/webapp-runner.jar --path /casadocodigo target/casadocodigo.war

## Profile DEV

O projeto sobe automaticamente ativando o profile "dev". Isso foi configurado através da classe ServletSpringMVC no método onStartup(..).

	servletContext.setInitParameter("spring.profiles.active", "dev");

Para não usar o profile "dev" basta comentar o InitParameter, no entanto é preciso um paramentro de inicialização no Tomcat (dentro das "Run Configurations...")

	 "-Dspring.profiles.active=dev"

## URL e Inicialização

Ao rodar no Eclipse pelo  Tomcat acesse:

	http://localhost:8080/casadocodigo
	
Execute a "URL Mágica" para cadastrar produtos e um usuario padrão (Login: admin@casadocodigo.com.br, Senha: 123456)	

## SQL para geração das tables

Se preferir gerar o banco, seguem os comandos SQL para o banco MySQL:

```SQL
drop table if exists Produto;
drop table if exists Produto_precos;
drop table if exists Usuario_Role;
drop table if exists Role;
drop table if exists Usuario;
create table Produto (id integer not null auto_increment, dataLancamento datetime, descricao varchar(255), paginas integer not null, sumarioPath varchar(255), titulo varchar(255), primary key (id));
create table Produto_precos (Produto_id integer not null, tipo integer, valor decimal(19,2));
create table Role (nome varchar(255) not null, primary key (nome));
create table Usuario (email varchar(255) not null, nome varchar(255), senha varchar(255), primary key (email));
create table Usuario_Role (email varchar(255) not null, role_nome varchar(255) not null);
alter table Produto_precos add constraint FK_hl4xdmygc7v2x607r4rbs6x3a foreign key (Produto_id) references Produto (id);
alter table Usuario_Role add constraint FK_5nbp4m2sk65w2mq9rfn680cx2 foreign key (role_nome) references Role (nome);
alter table Usuario_Role add constraint FK_4w45e3buitnd4f3ok8jdlrqkh foreign key (email) references Usuario (email);
```
