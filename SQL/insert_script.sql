insert into articolo values(articolo_seq.nextval, 'Apple', 'Mac Pro', 2700);
insert into articolo values(articolo_seq.nextval, 'Apple', 'iphoneX', 600);
insert into articolo values(articolo_seq.nextval, 'Apple', 'iphone15', 1200);
insert into articolo values(articolo_seq.nextval, 'Samsung', 's10', 200);

insert into immagine values(immagine_seq.nextval, 'img/macpro.png', 'Prodotto nuovo');
insert into immagine values(immagine_seq.nextval, 'img/iphonex.png', 'Prodotto ricondizionato');
insert into immagine values(immagine_seq.nextval, 'img/iphone15.png', 'Prodotto nuovo');

insert into amministratore values('admin', 'b0R2d436b$ab1fNb3p96%6dae2299J89c3X760259f', 'admin@site.com');
--delete from amministratore;
commit