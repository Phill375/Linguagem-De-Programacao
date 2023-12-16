CREATE TABLE "PUBLIC".aluno (
	id                   integer   NOT NULL,
	nome                 varchar(45)   NOT NULL,
	CONSTRAINT pk_aluno_id PRIMARY KEY ( id )
 );

CREATE TABLE "PUBLIC".chave (
	id                   varchar(20)   NOT NULL,
	valor                integer   NOT NULL,
	CONSTRAINT pk_chave_id PRIMARY KEY ( id )
 );

CREATE TABLE "PUBLIC".materia (
	id                   integer   NOT NULL,
	nome                 varchar(35)   NOT NULL,
	CONSTRAINT pk_curso_id PRIMARY KEY ( id )
 );

CREATE TABLE "PUBLIC".nota (
	id                   integer   NOT NULL,
	id_aluno             integer   NOT NULL,
	id_curso             integer   NOT NULL,
	nota                 integer   NOT NULL,
	CONSTRAINT pk_notas_id PRIMARY KEY ( id )
 );

ALTER TABLE "PUBLIC".nota ADD CONSTRAINT fk_nota_aluno FOREIGN KEY ( id_aluno ) REFERENCES "PUBLIC".aluno( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "PUBLIC".nota ADD CONSTRAINT fk_nota_materia FOREIGN KEY ( id_curso ) REFERENCES "PUBLIC".materia( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

