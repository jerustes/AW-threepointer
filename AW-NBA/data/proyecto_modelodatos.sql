USE 17_appweb_10d;
CREATE TABLE usuario (
	id INT NOT NULL auto_increment,
    mail VARCHAR(100) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    rol ENUM('admin','jugador') NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE deportista (
	id INT NOT NULL auto_increment,
    nombre VARCHAR(50) NOT NULL,
    valor LONG NOT NULL,
    posicion ENUM ('B','E','A','AP','P') NOT NULL,
    equipo VARCHAR(50) NOT NULL,
    puntos_global INT,
    puntos_semanal INT,
    PRIMARY KEY(id)
);
CREATE TABLE jornada (
	id INT NOT NULL auto_increment,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE estado (
	jornada INT NOT NULL,
    fase INT NOT NULL,
    CONSTRAINT FOREIGN KEY (jornada)
		REFERENCES Jornada(id)
);
CREATE TABLE liga (
	id INT NOT NULL auto_increment,
    creador INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    no_usuarios INT NOT NULL,
	saldo LONG NOT NULL,
    estado ENUM('Inscripcion','Preparada','Activa','Finalizada'),
    CONSTRAINT FOREIGN KEY (creador)
		REFERENCES Usuario(id),
	PRIMARY KEY(id)
);
CREATE TABLE plantilla (
	id INT NOT NULL auto_increment,
    liga INT NOT NULL,
    usuario INT NOT NULL,
    saldo LONG NOT NULL,
    puntos INT NOT NULL,
    CONSTRAINT FOREIGN KEY (liga)
		REFERENCES Liga(id),
	CONSTRAINT FOREIGN KEY (usuario)
		REFERENCES Usuario(id),
    PRIMARY KEY(id)
);
CREATE TABLE plantillaDeportista (
	id INT NOT NULL auto_increment,
	plantilla INT NOT NULL,
    deportista INT NOT NULL,
    CONSTRAINT FOREIGN KEY (plantilla)
		REFERENCES Plantilla(id),
	CONSTRAINT FOREIGN KEY (deportista)
		REFERENCES Deportista(id),
	PRIMARY KEY(id)
);

