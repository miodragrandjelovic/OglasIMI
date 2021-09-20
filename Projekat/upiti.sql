create database OGLASI;
use OGLASI;

create table korisnik
(
    id bigint primary key auto_increment,
    korisnickoIme varchar(50) not null,
    lozinka varchar(50) not null,
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

////////////////////////////////////////////////////////////////////////

// KORISNIK

delimiter //
create procedure PoslednjiIdKorisnika()
begin
	select max(id)
    from korisnik;
end
//
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
create procedure PronadjiEmail(emailt varchar(255))
begin
	select * from korisnik
    where eMail = emailt;
end
//
delimiter //
create procedure UnesiKorisnika(korisnickoImet varchar(50), lozinkat varchar(50), imet varchar(50), prezimet varchar(50), emailt varchar(50), datumRodjenjat date, drzavat varchar(50), gradt varchar(50), polt varchar(10))
begin
	insert into korisnik(korisnickoIme, lozinka, aktivan, ime, prezime, eMail, datumRodjenja, drzava, grad, pol)
	values(korisnickoImet, lozinkat, true, imet, prezimet, emailt, datumRodjenjat, drzavat, gradt, polt);
end
//
delimiter //
create procedure PromeniPodatkeKorisnika(idKorisnikat bigint, korisnickoImet varchar(50), lozinkat varchar(50), imet varchar(50), prezimet varchar(50), emailt varchar(50), datumRodjenjat date, drzavat varchar(50), grad varchar(50), polt varchar(10))
begin
    update korisnik
    set korisnickoIme = korisnikoImet, lozika = lozinkat, ime = imet, preizme = prezimet, eMail = emailt, datumRodjenja = datumRodjenjat, drzava = drzavat, grad = gradt, pol = polt
    where idKorisnika = idKorisnikat;
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
create procedure UnesiRoleModel(idKorisnikat bigint, korisnickoImet varchar(50), roleModelt varchar(50))
begin
    insert into roleModel(idKorisnika, korisnickoIme, roleModel)
    values(idKorisnikat, korisnickoImet, roleModelt);
end
//

// OGLASI

delimiter //
create procedure UnesiOglas(idPoslodavcat bigint, naslovt varchar(50), tekstt varchar(150), mestot varchar(50), datumPostavljanjat date, datumZavrsetkat date , platat double, kategorijat varchar(50), podkategorijat varchar(50),radOdKucet boolean)
begin
    insert into oglas(idPoslodavca, naslov, tekst, mesto, datumPostavljanja, datumZavrsetka, plata, kategorija, podkategorija,radOdKuce, aktiviran)
    values(idPoslodavcat, naslovt, tekstt, mestot, datumPostavljanjat, datumZavrsetkat, platat, kategorijat, podkategorijat,radOdKucet, true);
end
//
delimiter //
create procedure SviOglasi()
begin
    select * from oglas;
end
//
delimiter //
create procedure DajOglas(idOglasat bigint)
begin
    select * from oglas o join korisnik k on o.idPoslodavca = k.id
    where o.idOglasa = idOglasat;
end
//

// PRIJAVE

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
    select * from prijava
    where idOglasa = idOglasat;
end
//


// PREGLED I LAJK

delimiter //
create procedure UnesiPregled(idKorisnikat bigint, idOglasat bigint)
begin
    insert into pregled(idOglasa, idKorisnika)
    values(idOglasat, idKorisnikat);
end
//
delimiter //
create procedure UnesiLajk(idKorisnikat bigint, idOglasat bigint)
begin
    insert into lajk(idOglasa, idKorisnika)
    values(idOglasat, idKorisnikat);
end
//
delimiter //
create procedure UkloniLajk(idKorisnikat bigint, idOglasat bigint)
begin
    delete from lajk
    where idKorisnika = idKorisnikat and idOglasa = idOglasat;
end
//
delimiter //
create procedure pregledaoOglas(idKorisnikat bigint, idOglasat bigint)
begin
    select * from pregled
    where idKorisnika = idKorisnikat and idOglasa = idOglasat;
end
//
delimiter //
create procedure lajkovoOglas(idKorisnikat bigint, idOglasat bigint)
begin
    select * from lajk
    where idKorisnika = idKorisnikat and idOglasa = idOglasat;
end
//
delimiter //
create procedure DajBrojPregledaOglasa(idOglasat bigint)
begin
    select count(id) from pregled
    where idOglasa = idOglasat;
end
//
delimiter //
create procedure DajBrojLajkovaOglasa(idOglasat bigint)
begin
    select count(id) from lajk
    where idOglasa = idOglasat;
end
//




