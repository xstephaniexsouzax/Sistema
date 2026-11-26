-- Clinica Veterinaria - estrutura do banco
-- MySQL 8.0+
-- Execute no MySQL Workbench ou phpMyAdmin.
-- O Spring Boot tambem pode atualizar o schema via ddl-auto=update.
-- IMPORTANTE: nao inserir CPF em texto puro. Os campos cpf_criptografado e
-- cpf_indice devem ser gerados pelo CriptografiaService da aplicacao.

CREATE DATABASE IF NOT EXISTS clinica_veterinaria
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE clinica_veterinaria;

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_usuarios_username UNIQUE (username)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tutores (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf_criptografado TEXT NOT NULL,
    cpf_indice VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    email VARCHAR(255),
    endereco VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT uk_tutores_cpf_indice UNIQUE (cpf_indice)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS pets (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    especie VARCHAR(255),
    raca VARCHAR(255),
    sexo VARCHAR(255),
    data_nascimento DATE,
    tutor_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pets_tutores
      FOREIGN KEY (tutor_id) REFERENCES tutores(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS agendamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data_agendamento DATE,
    horario TIME,
    motivo VARCHAR(255),
    status VARCHAR(255),
    pet_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_agendamentos_pets
      FOREIGN KEY (pet_id) REFERENCES pets(id)
) ENGINE=InnoDB;

-- Dados de exemplo:
-- Para manter o CPF protegido, cadastre 3 tutores pela tela da aplicacao.
-- O TutorService gera o valor AES-GCM (cpf_criptografado) e o indice HMAC
-- (cpf_indice). Depois disso, cadastre ao menos 3 pets e 3 agendamentos
-- pelas telas correspondentes.
--
-- Para usuarios, a senha deve ser armazenada com BCrypt, nunca em texto puro.
-- Cadastre o usuario administrador por um inicializador da aplicacao ou
-- pelo fluxo administrativo, usando PasswordEncoder.
--
-- Consultas para conferir a populacao:
SELECT COUNT(*) AS total_usuarios FROM usuarios;
SELECT COUNT(*) AS total_tutores FROM tutores;
SELECT COUNT(*) AS total_pets FROM pets;
SELECT COUNT(*) AS total_agendamentos FROM agendamentos;
