create database OGLASI;
use OGLASI;

create table korisnik
(
   id bigint primary key auto_increment,
    ime varchar(500) not null,
    prezime varchar(500) not null,
    eMail varchar(500) not null,
    datumRodjenja date,
    pol varchar(50),
    slika longblob
)
engine = innodb;

create table nalog
(
   id bigint primary key auto_increment,
    idKorisnika bigint not null,
   korisnickoIme varchar(500) not null,
    lozinka varchar(500) not null,
    onOff boolean not null,
    foreign key (idKorisnika) references korisnik(id) on delete cascade
)
engine = innodb;

create table rolemodel
(
   id bigint primary key auto_increment,
    idNaloga bigint not null,
   korisnickoIme varchar(500) not null,
    roleModel varchar(500) not null,
    foreign key (idNaloga) references nalog(id) on delete cascade
)
engine = innodb;

create table oglas
(
   id bigint primary key auto_increment,
    idPoslodavca bigint not null,
    naslov varchar(500) not null,
    tekst varchar(1500) not null,
    mesto varchar(500) not null,
    datumPostavljanja datetime not null,
    datumZavrsetka datetime not null,
    plata double not null,
    aktiviran tinyint not null,
    kategorija varchar(500) not null,
    podkategorija varchar(500) null,
    radOdKuce tinyint,
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
    tekst varchar(1500) not null,
    datum_postavljanja datetime not null,
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

