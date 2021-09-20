create database OGLASI;
use OGLASI;

create table korisnik
(
    id bigint primary key auto_increment,
    korisnickoIme varchar(50) not null,
    lozinka varchar(255) not null,
    aktivan boolean not null,
    ime varchar(50) not null,
    prezime varchar(50) not null,
    eMail varchar(50) not null,
    datumRodjenja date,
    drzava varchar(50),
    grad varchar(50),
    pol varchar(50),
    slika longblob
)
engine = innodb;

create table rolemodel
(
    id bigint primary key auto_increment,
    idKorisnika bigint not null,
    korisnickoIme varchar(50) not null,
    roleModel varchar(50) not null,
    foreign key (idKorisnika) references korisnik(id) on delete cascade
)
engine = innodb;

create table oglas
(
    id bigint primary key auto_increment,
    idPoslodavca bigint not null,
    naslov varchar(50) not null,
    tekst varchar(150) not null,
    mesto varchar(50),
    datumPostavljanja datetime not null,
    datumZavrsetka datetime not null,
    plata double not null,
    kategorija varchar(50) not null,
    podkategorija varchar(50) null,
    radOdKuce boolean,
    aktiviran boolean not null,
    foreign key (idPoslodavca) references korisnik(id) on delete cascade
)
engine = innodb;

create table prijava
(
    id bigint primary key auto_increment,
    idKorisnika bigint not null,
    idOglasa bigint not null,
    datumPrijave datetime not null,
    foreign key (idKorisnika) references korisnik(id) on delete cascade,
    foreign key (idOglasa) references oglas(id) on delete cascade
)
engine = innodb;

create table komentar
(
    id bigint primary key auto_increment,
    idOglasa bigint not null,
    idKorisnika bigint not null,
    tekst varchar(150) not null,
    datumPostavljanja datetime not null,
    foreign key (idOglasa) references oglas(id) on delete cascade,
    foreign key (idKorisnika) references korisnik(id) on delete cascade
)
engine = innodb;

create table lajk
(
    id bigint primary key auto_increment,
    idOglasa bigint not null,
    idKorisnika bigint not null,
    foreign key (idOglasa) references oglas(id) on delete cascade,
    foreign key (idKorisnika) references korisnik(id) on delete cascade
)
engine = innodb;

create table pregled
(
    id bigint primary key auto_increment,
    idOglasa bigint not null,
    idKorisnika bigint not null,
    foreign key (idOglasa) references oglas(id) on delete cascade,
    foreign key (idKorisnika) references korisnik(id) on delete cascade
)
engine = innodb;






delimiter //
create procedure KorisnikPrekoImena(korisnickoImet varchar(255))
begin
	select *
	from korisnik
    where korisnickoIme = korisnickoImet;
end
//
delimiter //
create procedure PronadjiKorisnickoIme(korisnickoImet varchar(255))
begin
	select * from korisnik
    where korisnickoIme = korisnickoImet;
end
//
delimiter //
create procedure sviKorisnici()
begin
    select * from korisnik;
end
//
delimiter //
create procedure sviPoslodavci()
begin
    select * from korisnik inner join rolemodel on rolemodel.roleModel = "POSLODAVAC" and korisnik.id = rolemodel.idKorisnika;
end
//
delimiter //
create procedure PronadjiEmail(emailt varchar(255))
begin
	select * from korisnik
    where eMail = emailt;
end
//
delimiter //
create procedure UnesiKorisnika(korisnickoImet varchar(50), lozinkat varchar(255), imet varchar(50), prezimet varchar(50), emailt varchar(50), datumRodjenjat date, drzavat varchar(50), gradt varchar(50), polt varchar(10))
begin
	insert into korisnik(korisnickoIme, lozinka, aktivan, ime, prezime, eMail, datumRodjenja, drzava, grad, pol, slika)
	values(korisnickoImet, lozinkat, true, imet, prezimet, emailt, datumRodjenjat, drzavat, gradt, polt, NULL);
end
//
delimiter //
create procedure PromeniPodatkeKorisnika(idKorisnikat bigint, imet varchar(50), prezimet varchar(50), datumRodjenjat date, drzavat varchar(50), gradt varchar(50), polt varchar(10))
begin
    update korisnik
    set ime = imet, prezime = prezimet, datumRodjenja = datumRodjenjat, drzava = drzavat, grad = gradt, pol = polt
    where id = idKorisnikat;
end
//
delimiter //
create procedure PostaviSlikuKorisnika(idt bigint, slikat longblob)
begin
    update korisnik
    set slika = slikat
    where id = idt;
end
//
delimiter //
create procedure zadnjiIdKorisnika()
begin
    select max(id) from korisnik;
end
//
delimiter //
create procedure UnesiRoleModel(idKorisnikat bigint, korisnickoImet varchar(50), roleModelt varchar(50))
begin
    insert into roleModel(idKorisnika, korisnickoIme, roleModel)
    values(idKorisnikat, korisnickoImet, roleModelt);
end
//



delimiter //
create procedure UnesiOglas(idPoslodavcat bigint, naslovt varchar(50), tekstt varchar(150), mestot varchar(50), platat double, kategorijat varchar(50), podkategorijat varchar(50),radOdKucet boolean)
begin
    insert into oglas(idPoslodavca, naslov, tekst, mesto, datumPostavljanja, datumZavrsetka, plata, kategorija, podkategorija,radOdKuce, aktiviran)
    values(idPoslodavcat, naslovt, tekstt, mestot, now(), now(), platat, kategorijat, podkategorijat,radOdKucet, true);
end
//
delimiter //
create procedure IskljuciOglas(idOglasa bigint)
begin
    update oglas
    set aktiviran = false
    where id = idOglasa;
end
//
delimiter //
create procedure SviOglasi()
begin
    select * from oglas o join korisnik k on o.idPoslodavca = k.id
    where o.aktiviran = true;
end
//
delimiter //
create procedure DajJedanOglas(idOglasat bigint)
begin
    select * from oglas o join korisnik k on o.idPoslodavca = k.id
    where o.id = idOglasat;
end
//

delimiter //
create procedure UnesiPrijavu(idKorisnikat bigint, idOglasat bigint)
begin
    insert into prijava(idKorisnika, idOglasa, datumPrijave)
    values(idKorisnikat, idOglasat, now());
end
//
delimiter //
create procedure SvePrijaveZaOglas(idOglasat bigint)
begin
    select * from prijava join korisnik on prijava.idKorisnika = korisnik.id
    where prijava.idOglasa = idOglasat;
end
//











call UnesiKorisnika('pisa123', '$2a$10$50I6JRMwFGXFe57G3qoBV./dGbKbLW7Nikm/xoRA.BAZrMuupvFce', 'Miodrag', 'Randjelovic', 'misapisa123@gmail.com', now(), 'Srbija', 'Kragujevac', 'Muski');
call UnesiRoleModel(1, 'pisa123', 'ADMIN');

call UnesiKorisnika('misa123', '$2a$10$50I6JRMwFGXFe57G3qoBV./dGbKbLW7Nikm/xoRA.BAZrMuupvFce', 'Miodrag', 'Randjelovic', 'misa123@gmail.com', now(), 'Srbija', 'Kragujevac', 'Muski');
call UnesiKorisnika('darko123', '$2a$10$50I6JRMwFGXFe57G3qoBV./dGbKbLW7Nikm/xoRA.BAZrMuupvFce', 'Darko', 'Darkic', 'darko123@gmail.com', now(), 'Srbija', 'Beograd', 'Muski');
call UnesiRoleModel(2, 'misa123', 'POSLODAVAC');
call UnesiRoleModel(3, 'darko123', 'RADNIK');
call UnesiOglas(1, 'Naslov oglasa', 'Tekst oglasa Tekst oglasa Tekst oglasa Tekst oglasa Tekst oglasa', 'Kragujevac', 30000, 'Programer', 'C programer', true);


