CREATE DATABASE hospital_grup1A;
CREATE USER 'admin_hospital_grup1A'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON hospital_grup1A.* TO 'admin_hospital_grup1A'@'localhost';
CREATE USER 'usuari_hospital_grup1A'@'localhost' IDENTIFIED BY 'usuari';
GRANT SELECT ON hospital_grup1A.* TO 'usuari_hospital_grup1A'@'localhost';
FLUSH PRIVILEGES;
USE hospital_grup1A;


CREATE TABLE malalties
(
codi SMALLINT(3) AUTO_INCREMENT PRIMARY KEY,
nom CHAR(30) NOT NULL UNIQUE,
causaBaixa BIT,
tratamiento CHAR(50),
duradaTratamentDies INT(5)
);

CREATE TABLE metges
(
nom CHAR(15) NOT NULL,
cognom1 CHAR(20) NOT NULL,
cognom2 CHAR(20) NOT NULL,
DNI CHAR(9) PRIMARY KEY,
numSS CHAR(11) UNIQUE NOT NULL,
telefon CHAR(9) NOT NULL,
numEmpleat SMALLINT(2) UNIQUE,
codiCC CHAR(20),
salariMensual INT(10)
);

CREATE TABLE pacients
(
nom CHAR(15) NOT NULL,
cognom1 CHAR(20) NOT NULL,
cognom2 CHAR(20) NOT NULL,
DNI CHAR(9) PRIMARY KEY,
numSS CHAR(11) UNIQUE NOT NULL,
telefon CHAR(9)NOT NULL
);

CREATE TABLE visita
(
dataVisita DATE,
nomMalaltia CHAR(30),
dniPacient CHAR(9) NOT NULL,
dniMetges CHAR(9) NOT NULL,
FOREIGN KEY (nomMalaltia) REFERENCES malalties(nom),
FOREIGN KEY (dniPacient) REFERENCES pacients(DNI),
FOREIGN KEY (dniMetges) REFERENCES metges(DNI)
);

INSERT INTO malalties VALUES
(NULL,'Fiebre',0,'Reposo',20),
(NULL,'Migraña',1,'Mucho reposo',30),
(NULL,'Asma',0,'Inhalador',0),
(NULL, 'Varicela',1,'Reposo y cremitas',300);

INSERT INTO metges VALUES
('Dr.Paco','Ruíz','Millan','23415679N','12SDE34GFA4','663492256',34,'EDASERD4537F49392459',3000),
('Dra.Lisa','Perez','Perez','58273238P','327E4228EFS','661023131',20,'EEFFA733283393892472',3000),
('Dr.Jordi','Montesco','De la Curz','47293742Y','EDF45234523','663421147',01,'EDFA341234562341234',5000),
('Dra.Valentina','Capuleto','Verona','36378234T','D3421342341','661437786',02,'EF45434343434421901',5000);

INSERT INTO pacients VALUES
('José','Do Caroço','Martinez','1839456R','12SDE54GG3R','663745592'),
('Pedro','Pedrez','Caboclo','44324567P','473945DFRES','66341129'),
('Magdalena','Roja','Da Silva','38199237Y','S73HR4378A2','665432287'),
('Jeusalda','Ros','Julià','7439456U','S83HD45381W','664532256');


INSERT INTO visita VALUES
('2009-12-31','Fiebre','1839456R','23415679N'),
('2018-07-12','Migraña','38199237Y','23415679N'),
('2018-05-11','Fiebre','38199237Y','36378234T'),
('2018-10-01','Varicela','7439456U','47293742Y'),
('2019-01-01','Asma','1839456R','58273238P');




