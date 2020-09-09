CREATE TABLE ayudas (
    ayudaid         INTEGER NOT NULL,
    beneficiarioid  INTEGER NOT NULL,
    monto           INTEGER NOT NULL,
    motivo          VARCHAR2(25 CHAR)
);

ALTER TABLE ayudas ADD CONSTRAINT ayudas_pk PRIMARY KEY ( ayudaid );

CREATE TABLE beneficiarios (
    beneficiarioid  INTEGER NOT NULL,
    nombre          VARCHAR2(75 CHAR) NOT NULL,
    edad            INTEGER,
    ciudadid        INTEGER NOT NULL
);

ALTER TABLE beneficiarios ADD CONSTRAINT beneficiarios_pk PRIMARY KEY ( beneficiarioid );

CREATE TABLE ciudades (
    ciudadid      INTEGER NOT NULL,
    nombreciudad  VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE ciudades ADD CONSTRAINT ciudades_pk PRIMARY KEY ( ciudadid );

ALTER TABLE ayudas
    ADD CONSTRAINT ayudas_beneficiarios_fk FOREIGN KEY ( beneficiarioid )
        REFERENCES beneficiarios ( beneficiarioid );

ALTER TABLE beneficiarios
    ADD CONSTRAINT beneficiarios_ciudades_fk FOREIGN KEY ( ciudadid )
        REFERENCES ciudades ( ciudadid );

CREATE SEQUENCE ayudas_ayudaid_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER ayudas_ayudaid_trg BEFORE
    INSERT ON ayudas
    FOR EACH ROW
    WHEN ( new.ayudaid IS NULL )
BEGIN
    :new.ayudaid := ayudas_ayudaid_seq.nextval;
END;
