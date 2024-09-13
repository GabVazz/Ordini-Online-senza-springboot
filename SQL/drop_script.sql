drop table utente cascade constraints;
drop table ordine cascade constraints;
drop table ordine_articolo cascade constraints;
drop table articolo cascade constraints;
drop table immagine cascade constraints;
drop table amministratore cascade constraints;

drop view report;
drop view info_articole_utente;

drop sequence ordine_seq;
drop sequence articolo_seq;
drop sequence admin_seq;
drop sequence immagine_seq;

alter sequence ordine_seq restart;
alter sequence articolo_seq restart;
alter sequence admin_seq restart;
alter sequence immagine_seq restart;