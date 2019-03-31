DROP DATABASE IF EXISTS hospital_grup1A;
CREATE DATABASE hospital_grup1A;

DROP USER IF EXISTS 'admin_hospital_grup1A'@'localhost';
CREATE USER 'admin_hospital_grup1A'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON hospital_grup1A.* TO 'admin_hospital_grup1A'@'localhost';

DROP USER IF EXISTS 'usuari_hospital_grup1A'@'localhost';
CREATE USER 'usuari_hospital_grup1A'@'localhost' IDENTIFIED BY 'usuari';
GRANT SELECT ON hospital_grup1A.* TO 'usuari_hospital_grup1A'@'localhost';
#GRANT EXECUTE ON PROCEDURE hospital_grup1A.* TO 'usuari_hospital_grup1A'@'localhost';
#GRANT SELECT ON mysql.proc TO 'usuari_hospital_grup1A'@'localhost';
FLUSH PRIVILEGES;
USE hospital_grup1A;


CREATE TABLE malalties
(
codi SMALLINT(3) AUTO_INCREMENT PRIMARY KEY,
nom CHAR(30) NOT NULL UNIQUE,
causaBaixa boolean,
tratamiento CHAR(50),
duradaTratamentDies INT(5)
);

CREATE TABLE metges
(
nom CHAR(25) NOT NULL,
cognom1 CHAR(30) NOT NULL,
cognom2 CHAR(30) NOT NULL,
DNI CHAR(9) PRIMARY KEY,
numSS CHAR(11) UNIQUE NOT NULL,
telefon CHAR(9) NOT NULL,
ciutat CHAR(25) NOT NULL,
codipostal CHAR(6) NOT NULL,
direccio CHAR(50) NOT NULL,
codiCC CHAR(20),
salariMensual INT(10)
);

CREATE TABLE pacients
(
nom CHAR(25) NOT NULL,
cognom1 CHAR(30) NOT NULL,
cognom2 CHAR(30) NOT NULL,
DNI CHAR(9) PRIMARY KEY,
numSS CHAR(11) UNIQUE NOT NULL,
telefon CHAR(9) NOT NULL,
ciutat CHAR(25) NOT NULL,
codipostal CHAR(6) NOT NULL,
direccio CHAR(50) NOT NULL
);

CREATE TABLE visita
(
dataVisita DATE,
nomMalaltia CHAR(30),
dniPacient CHAR(9) NOT NULL,
dniMetges CHAR(9) NOT NULL,
informe CHAR(200),
FOREIGN KEY (nomMalaltia) REFERENCES malalties(nom),
FOREIGN KEY (dniPacient) REFERENCES pacients(DNI),
FOREIGN KEY (dniMetges) REFERENCES metges(DNI)
);

#Cambiar los valores de tamaño de las tablas de pacientes
#Y QUITAR ESTE ALTER LUEGO

###HACER procedures para ver el num SS?
###Procedures para INSERT
DELIMITER //
CREATE FUNCTION introducir_paciente (nombre char(25), cognom1 char(30), cognom2 char(30),
									 dni char(9), numss char(11), telefono char(9), ciudad char(25),
                                     codipostal CHAR(6), direccio char(40)) RETURNS INT
	BEGIN
		INSERT INTO pacients (nom,cognom1,cognom2,DNI,numSS,telefon,ciutat,codipostal,direccio)
			VALUES (nombre,cognom1,cognom2,dni,numss,telefono,ciudad,codipostal,direccio);
		RETURN 1;
	END ;
//
DELIMITER ;


DELIMITER //
CREATE FUNCTION introducir_medico (nombre char(25), cognom1 char(30), cognom2 char(30),
									 dni char(9), numss char(11), telefono char(9), ciudad char(25),
                                     codipostal CHAR(6), direccio char(40),
                                     codiCC CHAR(20),salariMensual INT(10)) RETURNS INT
	BEGIN
		INSERT INTO metges (nom,cognom1,cognom2,DNI,numSS,telefon,ciutat,codipostal,direccio,codiCC,salariMensual)
			VALUES (nombre,cognom1,cognom2,dni,numss,telefono,ciudad,codipostal,direccio,codiCC,salariMensual);       
		RETURN 1;
	END ;
//
DELIMITER ;


DELIMITER //
CREATE FUNCTION introducir_malaltia (nom CHAR(30),causaBaixa boolean,tratamiento CHAR(50),
										duradaTratamentDies INT(5)) RETURNS INT
	BEGIN
		INSERT INTO malalties (nom,causaBaixa ,tratamiento,duradaTratamentDies)
				VALUES (nom,causaBaixa ,tratamiento,duradaTratamentDies);
		RETURN 1;
	END ;
//
DELIMITER ;


DELIMITER //
CREATE FUNCTION introducir_visita (dataVisita DATE,nomMalaltia CHAR(30),dniPacient CHAR(9),
									dniMetges CHAR(9),informe CHAR(200)) RETURNS INT
	BEGIN
		INSERT INTO visita (dataVisita,nomMalaltia,dniPacient,dniMetges,informe)
				VALUES (dataVisita,nomMalaltia,dniPacient,dniMetges,informe);
		RETURN 1;
	END ;
//
DELIMITER ;
###
###

###
###Procedures para COUNT
DELIMITER //
CREATE FUNCTION existe_paciente (dni CHAR(9))RETURNS INT 
	BEGIN
	RETURN (SELECT COUNT(*) FROM pacients p WHERE p.DNI = dni);
    
    END ;
//
DELIMITER ;

DELIMITER //
CREATE FUNCTION existe_medico (dni CHAR(9)) RETURNS INT
	BEGIN
		RETURN (SELECT COUNT(*) FROM metges m  WHERE m.DNI = dni);
	END ;
//
DELIMITER ;

DELIMITER //
CREATE FUNCTION existe_malaltia (codi SMALLINT(3)) RETURNS INT
	BEGIN
		RETURN (SELECT COUNT(*) FROM malalties ma WHERE ma.codi = codi);
	END ;
//
DELIMITER ;

##Comprobaciones extra - Existe Alguien con el mismo numero de SS

DELIMITER //
CREATE FUNCTION existeSS(SS CHAR(11)) RETURNS INT
	BEGIN
		DECLARE cuenta INT;
		SET cuenta = (SELECT COUNT(*) FROM metges mt WHERE mt.numSS = SS);
        SET cuenta = cuenta + (SELECT COUNT(*) FROM pacients p WHERE p.numSS = SS);
        RETURN cuenta;
	END ;
//
DELIMITER ;


GRANT EXECUTE ON function hospital_grup1A.introducir_paciente TO 'usuari_hospital_grup1A'@'localhost';
GRANT EXECUTE ON function hospital_grup1A.introducir_medico TO 'usuari_hospital_grup1A'@'localhost';
GRANT EXECUTE ON function hospital_grup1A.introducir_malaltia TO 'usuari_hospital_grup1A'@'localhost';
GRANT EXECUTE ON function hospital_grup1A.introducir_visita TO 'usuari_hospital_grup1A'@'localhost';
GRANT EXECUTE ON function hospital_grup1A.existe_paciente TO 'usuari_hospital_grup1A'@'localhost';
GRANT EXECUTE ON function hospital_grup1A.existe_medico TO 'usuari_hospital_grup1A'@'localhost';
GRANT EXECUTE ON function hospital_grup1A.existe_malaltia TO 'usuari_hospital_grup1A'@'localhost';
GRANT EXECUTE ON function hospital_grup1A.existeSS TO 'usuari_hospital_grup1A'@'localhost';


SELECT existe_paciente('1839456R');
SELECT existe_medico('23415679N');
SELECT existe_malaltia(1);
SELECT existeSS('2263616T');






#EJECUCION FUNCIONES EJEMPLO
SELECT introducir_paciente('Raymundo','Montenegro','Sanchez','43562567T','2263616T','661352274','Sevilla','123456','Direccion 3') AS 'hola';
SELECT introducir_medico('Francisco','Montenegro','Sanchez','43562568T','2263626T','661352374','Sevilla','23456','Direccion 3','wwe',10000) AS 'hola';


INSERT INTO malalties VALUES
(NULL,'MalaltiaPrueba',true,'Reposo',20);

SELECT * FROM malalties;


INSERT INTO malalties VALUES
(NULL,'Fiebre',0,'Reposo',20),
(NULL,'Migraña',1,'Mucho reposo',30),
(NULL,'Asma',0,'Inhalador',0),
(NULL, 'Varicela',1,'Reposo y cremitas',300);

INSERT INTO metges VALUES
('Dr.Paco','Ruíz','Millan','23415679N','12SDE34GFA4','663492256','Toledo','3948','Pasadizo Esgargamellà, 232A','EDASERD4537F49392459',3000),
('Dra.Lisa','Perez','Perez','58273238P','327E4228EFS','661023131','Albacete','38369','Alameda Resguardam, 184','EEFFA733283393892472',3000),
('Dr.Jordi','Montesco','De la Curz','47293742Y','EDF45234523','663421147','Huelva','06481','Carrera Dardada, 13B 13ºF','EDFA341234562341234',5000),
('Dra.Valentina','Capuleto','Verona','36378234T','D3421342341','661437786','Guipúzcoa','07311','Carretera engorguésseu trafegàs, 226B 10ºD','EF45434343434421901',5000);

INSERT INTO pacients VALUES
('José','Do Caroço','Martinez','1839456R','12SDE54GG3R','663745592','Albacete','12118','Pasadizo emmatxucament, 256B'),
('Pedro','Pedrez','Caboclo','44324567P','473945DFRES','66341129','Tenerife','10539','Travesía engabiaríem, 235 2ºC'),
('Magdalena','Roja','Da Silva','38199237Y','S73HR4378A2','665432287','Orense','48443','Callejón gallardejaré, 25A 7ºD'),
('Jeusalda','Ros','Julià','7439456U','S83HD45381W','664532256','Murcia','02704','Paseo Alicorn, 144 5ºC');


INSERT INTO visita VALUES
('2009-12-31','Fiebre','1839456R','23415679N','Veniam nihil dolor saepe pariatur molestiae exercitationem rerum, dolorum placeat dolorem earum iure'),
('2018-07-12','Migraña','38199237Y','23415679N','voluptatum dolor ab est asperiores illum inventore quisquam repellendus placeat'),
('2018-05-11','Fiebre','38199237Y','36378234T',''),
('2018-10-01','Varicela','7439456U','47293742Y','nesciunt consequuntur incidunt quidem dolore ducimus'),
('2019-01-01','Asma','1839456R','58273238P','');


INSERT INTO malalties (codi,nom) values(null,"Enfermeda");
SELECT * FROM malalties;

