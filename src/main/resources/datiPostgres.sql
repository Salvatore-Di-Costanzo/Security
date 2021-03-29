DROP TABLE IF EXISTS utente;
DROP TABLE IF EXISTS bambino;

CREATE TABLE utente (
                        email varchar(255) PRIMARY KEY,
                        cf varchar(13) default NULL,
                        nome varchar(255) default NULL,
                        cognome varchar(255) default NULL,
                        datanascita varchar(255),
                        luogonascita varchar(255),
                        cellulare varchar(100) default NULL,
                        punteggio integer NULL
);

INSERT INTO "utente" (email,cf,nome,cognome,datanascita,luogonascita,cellulare,punteggio) VALUES ('elit@enimcommodo.co.uk','16621204 4900','Oleg','Butler','10-01-2021','Castiglione di Garfagnana','(622) 983-7311',197),('consectetuer.ipsum.nunc@arcuNuncmauris.com','16160519 9015','Asher','Rios','05-01-2020','Castelvecchio Calvisio','(744) 406-8958',149),('porttitor@et.com','16040621 8818','Indigo','Walker','09-19-2021','Vetlanda','(499) 310-5526',230),('Nam@diamDuis.net','16391009 2174','Kevin','Pennington','09-28-2021','Jerzu','(165) 873-5067',176),('egestas.lacinia.Sed@Aliquameratvolutpat.net','16620811 6555','Rhea','Leach','11-25-2020','Kaneohe','(635) 487-6016',107),('lacinia@sociisnatoquepenatibus.org','16790902 2233','Emi','Diaz','02-05-2022','Salon-de-Provence','(152) 574-0883',118),('purus@ligula.co.uk','16970407 3080','Odysseus','Michael','12-25-2021','Friedrichshafen','(982) 421-8041',65),('luctus.ipsum.leo@eratin.com','16270510 7106','Forrest','Savage','06-14-2020','Latinne','(235) 606-8700',37),('dapibus@sodalespurus.org','16250829 8862','Jamalia','Macias','02-24-2022','Helmond','(507) 502-5259',397),('nisi.magna@condimentum.ca','16580613 7344','Camilla','Wise','07-12-2020','Bevilacqua','(775) 790-8880',259);


CREATE TABLE "bambino" (
                           id SERIAL PRIMARY KEY,
                           nomebambino varchar(255) default NULL,
                           cognomebambino varchar(255) default NULL,
                           email varchar(255) default NULL,
                           CONSTRAINT fk_customer
                               FOREIGN KEY(email)
                                   REFERENCES utente(email)
);

INSERT INTO "bambino" (nomebambino,cognomebambino,email) VALUES ('Zia','Christian','elit@enimcommodo.co.uk'),('Elmo','Dale','consectetuer.ipsum.nunc@arcuNuncmauris.com')