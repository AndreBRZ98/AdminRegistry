DROP SCHEMA IF EXISTS gelr_db;
CREATE SCHEMA gelr_db;
USE gelr_db;

create table gelr_persona (
    id VARCHAR(20),
    nombre VARCHAR(100)  NOT NULL,
    apellidos VARCHAR(100)  NOT NULL,
    genero ENUM('M','F') NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    correo VARCHAR(50),
    celular VARCHAR(15),
    codigo_postal VARCHAR(10),
    direccion VARCHAR(250),
    ciudad VARCHAR(100),
    estado VARCHAR(100),
    familia JSON,
   CONSTRAINT gelr_id_pk PRIMARY KEY (id)
);


create table nelr_nino(
    id VARCHAR(20),
    persona_id VARCHAR(20) NOT NULL,
    alergias TEXT,
    notas TEXT,
    CONSTRAINT nelr_id_pk PRIMARY KEY (id),
    CONSTRAINT nelr_persona_id_pk FOREIGN KEY(persona_id) REFERENCES gelr_persona(id)
);

create table gelr_servidor(
    id VARCHAR(20),
    persona_id VARCHAR(20) NOT NULL,
    nivel_ibc VARCHAR(25),
    bautizo TINYINT(1),
    notas TEXT,
    CONSTRAINT gelr_id_pk PRIMARY KEY(id),
    CONSTRAINT gelr_persona_id_pk FOREIGN KEY (persona_id) REFERENCES gelr_persona(id)
);


CREATE TABLE gelr_login(
    id VARCHAR(20),
    persona_id VARCHAR(20) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100),
    rol_id VARCHAR(20) NOT NULL,
    CONSTRAINT gelr_login_pk PRIMARY KEY(id),
    CONSTRAINT gelr_login_persona_id_pk FOREIGN KEY (persona_id) REFERENCES gelr_persona(id)
);

INSERT INTO gelr_persona VALUES(1001,'Lucas Leon', 'Espinoza Alvarez', 'M', '2020-08-23','','','','','','','{}');
INSERT INTO nelr_nino VALUES(5001,1001,'Nueces y pozole', 'Revisar que tenga agua en su termo');