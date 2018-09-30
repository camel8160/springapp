-- -----------------------------------------------------
-- Schema PresupuestoMD
-- -----------------------------------------------------
CREATE DATABASE presupuestoMD
    WITH 
    OWNER = postgres
    ENCODING = UTF8
    LC_COLLATE = Spanish_Colombia.1252
    LC_CTYPE = Spanish_Colombia.1252
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: presupuestoMD
-- DROP SCHEMA presupuestoMD ;
CREATE SCHEMA presupuestoMD
    AUTHORIZATION postgres;
	
-- -----------------------------------------------------
-- Table presupuestoMD.usuario
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.usuario_seq;
CREATE SEQUENCE presupuestoMD.usuario_seq;
ALTER SEQUENCE presupuestoMD.usuario_seq
    OWNER TO postgres;
    
-- DROP TABLE presupuestoMD.usuario ;
CREATE TABLE presupuestoMD.usuario
(
   id                  INTEGER NOT NULL DEFAULT nextval ('presupuestoMD.usuario_seq'::regclass),
   numero_documento    CHARACTER VARYING (50) NULL,
   nombre              CHARACTER VARYING (50) NULL,
   usuario             CHARACTER VARYING (20) NULL,
   correo              CHARACTER VARYING (80) NULL,
   cargo    		   CHARACTER VARYING (80) NULL,
   rol           	   CHARACTER VARYING (150) NULL,
   CONSTRAINT pk_usuario PRIMARY KEY (id) NOT DEFERRABLE INITIALLY IMMEDIATE
)
WITH (OIDS = FALSE);

ALTER TABLE presupuestoMD.usuario
    OWNER to postgres;		

-- datos
insert into presupuestoMD.usuario  (numero_documento, nombre, usuario, correo, cargo, rol)
values ('1','Jarrison Garcia', 'jarrison','jarrison', 'Gerente XD', 'ADMON');
-- -----------------------------------------------------
-- Table presupuestoMD.gerencia
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.gerencia_seq;
CREATE SEQUENCE presupuestoMD.gerencia_seq;
ALTER SEQUENCE presupuestoMD.gerencia_seq
    OWNER TO postgres; 
    
-- DROP TABLE presupuestoMD.gerencia ;
CREATE TABLE presupuestoMD.gerencia (
  id integer NOT NULL DEFAULT nextval('presupuestoMD.gerencia_seq'::regclass),
  nombre VARCHAR(100) NULL,
  estado boolean not NULL,
  CONSTRAINT pk_gerencia PRIMARY KEY (id));

ALTER TABLE presupuestoMD.gerencia
    OWNER to postgres;
	
-- -----------------------------------------------------
-- Table presupuestoMD.direccion
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.direccion_seq;
CREATE SEQUENCE presupuestoMD.direccion_seq;
ALTER SEQUENCE presupuestoMD.direccion_seq
    OWNER TO postgres; 
    
-- DROP TABLE presupuestoMD.direccion ;
CREATE TABLE presupuestoMD.direccion (
  id integer NOT NULL DEFAULT nextval('presupuestoMD.direccion_seq'::regclass),
  nombre VARCHAR(100) NULL,
  estado boolean not NULL,
  CONSTRAINT pk_direccion PRIMARY KEY (id));

ALTER TABLE presupuestoMD.direccion
    OWNER to postgres;

-- -----------------------------------------------------
-- Table presupuestoMD.jefatura
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.jefatura_seq;
CREATE SEQUENCE presupuestoMD.jefatura_seq;
ALTER SEQUENCE presupuestoMD.jefatura_seq
    OWNER TO postgres; 
    
-- DROP TABLE presupuestoMD.jefatura ;
CREATE TABLE presupuestoMD.jefatura (
  id integer NOT NULL DEFAULT nextval('presupuestoMD.jefatura_seq'::regclass),
  nombre VARCHAR(100) NULL,
  estado boolean not NULL,
  CONSTRAINT pk_jefatura PRIMARY KEY (id));

ALTER TABLE presupuestoMD.jefatura
    OWNER to postgres;
	
-- -----------------------------------------------------
-- Table presupuestoMD.cuenta
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.cuenta_seq;
CREATE SEQUENCE presupuestoMD.cuenta_seq;
ALTER SEQUENCE presupuestoMD.cuenta_seq
    OWNER TO postgres; 
    
-- DROP TABLE presupuestoMD.cuenta ;
CREATE TABLE presupuestoMD.cuenta (
  id integer NOT NULL DEFAULT nextval('presupuestoMD.cuenta_seq'::regclass),
  nombre VARCHAR(100) NULL,
  grupo VARCHAR(100) NULL,
  estado boolean not NULL,
  CONSTRAINT pk_cuenta PRIMARY KEY (id));

ALTER TABLE presupuestoMD.cuenta
    OWNER to postgres;
	
-- -----------------------------------------------------
-- Table presupuestoMD.centrocosto
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.centrocosto_seq;
CREATE SEQUENCE presupuestoMD.centrocosto_seq;
ALTER SEQUENCE presupuestoMD.centrocosto_seq
    OWNER TO postgres; 
    
-- DROP TABLE presupuestoMD.centrocosto ;
CREATE TABLE presupuestoMD.centrocosto (
  id integer NOT NULL DEFAULT nextval('presupuestoMD.centrocosto_seq'::regclass),
  nombre VARCHAR(100) NULL,
  id_gerencia integer not null,
  id_direccion integer not null,
  id_jefatura integer not null,
  estado boolean not NULL,
  CONSTRAINT pk_centrocosto PRIMARY KEY (id),
  CONSTRAINT fk_cc_gerencia FOREIGN KEY (id_gerencia)
        REFERENCES presupuestoMD.gerencia (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_cc_direccion FOREIGN KEY (id_direccion)
        REFERENCES presupuestoMD.direccion (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_cc_jefatura FOREIGN KEY (id_jefatura)
        REFERENCES presupuestoMD.jefatura (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE presupuestoMD.centrocosto
    OWNER to postgres;	
	
-- -----------------------------------------------------
-- Table presupuestoMD.sublink
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.sublink_seq;
CREATE SEQUENCE presupuestoMD.sublink_seq;
ALTER SEQUENCE presupuestoMD.sublink_seq
    OWNER TO postgres; 
    
-- DROP TABLE presupuestoMD.sublink ;
CREATE TABLE presupuestoMD.sublink (
  id integer NOT NULL DEFAULT nextval('presupuestoMD.sublink_seq'::regclass),
  nombre VARCHAR(100) NULL,
  estado boolean not NULL,
  CONSTRAINT pk_sublink PRIMARY KEY (id));

ALTER TABLE presupuestoMD.sublink
    OWNER to postgres;
	
-- -----------------------------------------------------
-- Table presupuestoMD.home
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.home_seq;
CREATE SEQUENCE presupuestoMD.home_seq;
ALTER SEQUENCE presupuestoMD.home_seq
    OWNER TO postgres; 
    
-- DROP TABLE presupuestoMD.home ;
CREATE TABLE presupuestoMD.home (
  id integer NOT NULL DEFAULT nextval('presupuestoMD.home_seq'::regclass),
  url VARCHAR(100) NULL,
  nombre VARCHAR(100) NULL,
  fecha_inicio date NULL,
  fecha_fin date NULL,
  estado boolean not NULL,
  CONSTRAINT pk_home PRIMARY KEY (id));

ALTER TABLE presupuestoMD.home
    OWNER to postgres;	
	
	
-- -----------------------------------------------------
-- Table presupuestoMD.cueta_x_centrocosto
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.cuenta_x_centrocosto_seq;
CREATE SEQUENCE presupuestoMD.cuenta_x_centrocosto_seq;
ALTER SEQUENCE presupuestoMD.cuenta_x_centrocosto_seq
    OWNER TO postgres;
    
-- DROP TABLE presupuestoMD.cuenta_x_centrocosto ;
CREATE TABLE presupuestoMD.cuenta_x_centrocosto (
  id integer NOT NULL DEFAULT nextval('presupuestoMD.cuenta_x_centrocosto_seq'::regclass),
  id_centrocosto integer not NULL,
  id_cuenta integer not NULL,
  id_sublink integer not NULL,
  CONSTRAINT pk_cuenta_x_centrocosto PRIMARY KEY (id),
  CONSTRAINT fk_cxc_cuenta FOREIGN KEY (id_cuenta)
        REFERENCES presupuestoMD.cuenta (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_cxc_ccosto FOREIGN KEY (id_centrocosto)
        REFERENCES presupuestoMD.centrocosto (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_cxc_sublink FOREIGN KEY (id_sublink)
        REFERENCES presupuestoMD.sublink (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE presupuestoMD.cuenta_x_centrocosto
    OWNER to postgres;

-- -----------------------------------------------------
-- Table presupuestoMD.usuaio_x_ccosto
-- -----------------------------------------------------

-- DROP SEQUENCE presupuestoMD.usuario_x_centrocosto_seq;
CREATE SEQUENCE presupuestoMD.usuario_x_centrocosto_seq;
ALTER SEQUENCE presupuestoMD.usuario_x_centrocosto_seq
    OWNER TO postgres; 

-- DROP TABLE presupuestoMD.usuario_x_centrocosto ;
CREATE TABLE presupuestoMD.usuario_x_centrocosto
(
    id integer NOT NULL DEFAULT nextval('presupuestoMD.usuario_x_centrocosto_seq'::regclass),
	id_centrocosto integer not null,
	id_usuario_resp integer not null,
	id_usuario_aprini integer not null,
	id_usuario_aprfin integer not null,
    CONSTRAINT pk_usuario_x_centrocosto PRIMARY KEY (id),
    CONSTRAINT fk_uxc_usuario FOREIGN KEY (id_usuario_resp)
        REFERENCES presupuestoMD.usuario (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_uxc_usuario2 FOREIGN KEY (id_usuario_aprini)
        REFERENCES presupuestoMD.usuario (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_uxc_usuario3 FOREIGN KEY (id_usuario_aprfin)
        REFERENCES presupuestoMD.usuario (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_uxc_centrocosto FOREIGN KEY (id_centrocosto)
        REFERENCES presupuestoMD.centrocosto (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE presupuestoMD.usuario_x_centrocosto
    OWNER to postgres;