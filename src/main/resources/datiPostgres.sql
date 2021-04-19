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

DROP TABLE IF EXISTS "negozio";

CREATE TABLE "negozio"
(
    id     integer PRIMARY KEY,
    nomenegozio varchar(255) default NULL,
    categoria varchar(255) default NULL,
    email varchar(255) default NULL

);
INSERT INTO "negozio" (id, nomenegozio,categoria,email)
VALUES (0, 'Fior Di Latte','bottone1','perlabinca@hotmail.it');

DROP TABLE IF EXISTS "tipo";

CREATE TABLE "tipo"
(
    id     integer PRIMARY KEY,
    tipo varchar(255) default NULL

);
INSERT INTO "tipo" (id, tipo)
VALUES (0, 'mozzarella'),(1,'pizza');

