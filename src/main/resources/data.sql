--INSERT INTO LOCATIONS_SPAIN (NAME_LOCATION, LAT, LON, COMUNIDAD, PROVINCIA, HABITANTES) VALUES ('madridd', 40.4168, -3.7038, 'Comunidad de Madrid', 'Madrid', 6642179);
--INSERT INTO LOCATIONS_SPAIN (NAME_LOCATION, LAT, LON, COMUNIDAD, PROVINCIA, HABITANTES) VALUES ('barcelona', 41.3851, 2.1734, 'Cataluña', 'Barcelona', 5664579);


INSERT INTO USUARIOS (USERNAME, PASSWORD_ENCRYPTED, NAME_LOCATION) VALUES ('user1', 'contrasena1', null); 
-- si en un atributo como contraseña ponemos antes '{noop}contraseña' pues a este texto "contraseña" no se leva a Hashear para esconder la contraseña
INSERT INTO USUARIOS (USERNAME, PASSWORD_ENCRYPTED, NAME_LOCATION) VALUES ('user2', 'contrasena2', 'Madrid');
INSERT INTO USUARIOS(USERNAME, PASSWORD_ENCRYPTED, NAME_LOCATION) VALUES ('user0', 'CdK1033/qww=', 'Somo') --la contraseña es user0, pero encriptada