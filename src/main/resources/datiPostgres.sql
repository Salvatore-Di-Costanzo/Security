DROP TABLE IF EXISTS "utente";

CREATE TABLE "utente"
(
    email     varchar(255) PRIMARY KEY,
    nome      varchar(255) default NULL,
    cognome   varchar(255) default NULL,
    punteggio integer NULL
);
INSERT INTO "utente" (email, nome, cognome, punteggio)
VALUES ('ADMIN', 'admin', 'admin', 0);


DROP TABLE IF EXISTS "sequenziale";

CREATE TABLE "sequenziale"
(
    id     integer PRIMARY KEY,
    numero integer default NULL

);
INSERT INTO "sequenziale" (id, numero)
VALUES (1, 0);