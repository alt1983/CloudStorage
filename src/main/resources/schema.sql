CREATE SCHEMA netology;

CREATE TABLE netology.FILES(
                               id           SERIAL PRIMARY KEY,
                               name         VARCHAR(30) NOT NULL,
                               data         bytea NOT NULL,
                               active       BOOLEAN NOT NULL
);

CREATE TABLE netology.USERS(
                               id           SERIAL PRIMARY KEY,
                               login         VARCHAR(30) NOT NULL,
                               password         VARCHAR(30) NOT NULL,
                               firstname      VARCHAR(30) NOT NULL,
                               lastname        VARCHAR(30) NOT NULL,
                               role          VARCHAR(10) NOT NULL,
                               active       BOOLEAN NOT NULL
);

CREATE TABLE netology.TOKENS(
                                id           SERIAL PRIMARY KEY,
                                token           VARCHAR(240) NOT NULL,
                                active         BOOLEAN  NOT NULL
);
