--Client
create table utente(
	nome varchar2(30) not null,
	cognome varchar2(30) not null,
	indirizzo varchar2(50) not null,
	cap char(5) not null,
	nascita date not null,
	username varchar2(10),
	password varchar2(500) not null,
	email varchar(100) not null unique,
	constraint pk_username primary key(username)
);

create table articolo(
	id_articolo int,
	marca varchar2(30) not null,
	modello varchar2(30) not null,
	prezzo number(8,2) not null,
	constraint p_id_articolo primary key(id_articolo)
);

create table ordine(
	id_ordine int,
	totale number(9,2) not null,
	data date not null,
	username varchar2(10),
	constraint f_username foreign key(username) references utente(username)
	on delete cascade,
	constraint p_id_ordine primary key(id_ordine)


);

create table ordine_articolo(
	id_ordine int,
	id_articolo int,
	qta number(3) default 1,
	constraint f_id_ordine foreign key(id_ordine) references ordine(id_ordine)
	on delete cascade,
	constraint f_id_articolo foreign key(id_articolo) references articolo(id_articolo)
	on delete cascade,
	constraint p_oa primary key(id_ordine, id_articolo)
);

create table immagine(
	id_immagine int primary key references articolo(id_articolo),
	url varchar2(500) default 'https://t3.ftcdn.net/jpg/05/79/68/24/360_F_579682465_CBq4AWAFmFT1otwioF5X327rCjkVICyH.jpg',
	descrizione varchar2(1000) not null
);

create sequence ordine_seq;
create sequence articolo_seq;
create sequence immagine_seq;

--Admin
create table amministratore(
	username varchar2(10),
	password varchar2(500) not null,
	email varchar2(100) not null unique,
	constraint p_admin primary key(username)


);

CREATE OR REPLACE VIEW report AS
SELECT u.username, u.email, o.id_ordine, o.totale, SUM(oa.qta) AS qta
FROM utente u
JOIN ordine o ON u.username = o.username
JOIN ordine_articolo oa ON o.id_ordine = oa.id_ordine
JOIN articolo a ON a.id_articolo = oa.id_articolo
GROUP BY u.username, u.email, o.id_ordine, o.totale
ORDER BY u.username, o.id_ordine;

CREATE OR REPLACE VIEW info_articolo_utente AS
SELECT u.nome, u.cognome, u.email, a.id_articolo, a.marca, a.modello, a.prezzo, oa.qta, (a.prezzo * oa.qta) as "Totale parziale"
FROM utente u
JOIN ordine o ON u.username = o.username
JOIN ordine_articolo oa ON o.id_ordine = oa.id_ordine
JOIN articolo a ON a.id_articolo = oa.id_articolo
ORDER BY u.username, o.id_ordine;

CREATE OR REPLACE VIEW articoli_piu_venduti as
SELECT a.id_articolo, a.marca, a.modello, SUM(o.totale) AS totale_vendite, SUM(oa.qta) AS quantita_venduta
FROM articolo a
JOIN ordine_articolo oa ON a.id_articolo = oa.id_articolo
JOIN ordine o ON o.id_ordine = oa.id_ordine
GROUP BY a.id_articolo, a.marca, a.modello
ORDER BY quantita_venduta DESC

create sequence admin_seq;