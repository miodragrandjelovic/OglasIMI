
insert into korisnik(ime,prezime,eMail,datumRodjenja,pol)
values('Misa', 'Randjelovic', 'dobropamtimsve00@gmail.com','2000-4-2', 'muski');
insert into korisnik(ime,prezime,eMail,datumRodjenja,pol)
values('Katarina', 'Zdravkovic', 'kzdravkovic1@gmail.com','2000-5-17', 'zenski');
insert into korisnik(ime,prezime,eMail,datumRodjenja,pol)
values('Tijana', 'Markovic', 'tijanamarkovic176@gmail.com','2000-6-17', 'zenski');
insert into korisnik(ime,prezime,eMail,datumRodjenja,pol)
values('Tina', 'Petrovic', 'tinapetrovickv@gmail.com','2001-2-15', 'zenski');


insert into nalog(idKorisnika, korisnickoIme, lozinka, onOff)
values(1,'Misa','$2y$12$DQfkNokAQ2TmsWoEJkvYF.CTZ114C2w0LCUYTHxCftwfamQC4MyYm', true);
insert into nalog(idKorisnika, korisnickoIme, lozinka, onOff)
values(2,'Kata','$2y$12$DzuldZGsBaXbgYILqSX4PudyG1T4i6JZm0V9e.7OElTQEqPXuPp2S', true);
insert into nalog(idKorisnika, korisnickoIme, lozinka, onOff)
values(3,'Mare','$2y$12$3s2NGRsAF.HxWqxcJbkGluN3UApwc2/T9CPx8RjGHGNrS.NRp.zjq', true);
insert into nalog(idKorisnika, korisnickoIme, lozinka, onOff)
values(4,'Tina','$2y$12$1BvPsOz4fHB4gIQ1poPLQOe6/kKhmoO2eCnBSpdV./IaYcc5o2Lv2', true);

insert into rolemodel(idNaloga, korisnickoIme, roleModel)
values(1, 'Misa', "ADMIN");
insert into rolemodel(idNaloga, korisnickoIme, roleModel)
values(2, 'Kata', "POSLODAVAC");
insert into rolemodel(idNaloga, korisnickoIme, roleModel)
values(3, 'Mare', "POSLODAVAC");
insert into rolemodel(idNaloga, korisnickoIme, roleModel)
values(4, 'Tina', "RADNIK");


insert into oglas(idPoslodavca, naslov, tekst, mesto, datumPostavljanja, datumZavrsetka, plata, aktiviran, kategorija, podkategorija, radOdKuce)
values(2,'Budi svoj gazda!',"Posao koji mozes da radis i ti! Zapocni svoj posao bez ulaganja! Nauci da zaradjujes! Kompletan sistem je besplatan! PRUZI SEBI SANSU!",'Gornji Milonovac',now(), date_add(now(), interval 5 month),25000,true,'ekonmista','prodaja',0);
insert into oglas(idPoslodavca, naslov, tekst, mesto, datumPostavljanja, datumZavrsetka, plata, aktiviran, kategorija, podkategorija, radOdKuce)
values(2,'Treba ti promena u 2020? Evo kako da dodjes do posla iz snova!',"Trazis posao koji mozes raditi od kuce? Ovo je prava stvar za tebe, radis u svom domu, kad i koliko hoces. Bitna stvar je da se radi o firmi koja postoji preko 20 godina, i mozes imati platu svaki mesec, a kolika će biti zavisi od tebe. Obuka i registracija besplatna. Zainteresovani? Pridruzi se jednom velikom timu i pocni da zaradjujes odmah!",'Kraljevo',now(), date_add(now(), interval 1 month),50000,true,'programer','html',1);
insert into oglas(idPoslodavca, naslov, tekst, mesto, datumPostavljanja, datumZavrsetka, plata, aktiviran, kategorija, podkategorija, radOdKuce)
values(3,'Postani uspesna! Zelis da se ostvaris na poslovnom planu? Evo i kako',"Danas se od zene ocekuje da bude uspesna i u kuci i na poslu. Ja sam nasla nacin za to. Nudim ti pomoc da i ti ostvaris svoj san. Potrebne saradnice za online i katalosku prodaju Limes proizvoda. Prvostepena zarada 33% na svaki prodati proizvod, drugostepena zarada 33%+plata bonusi, nagrade, gratisi, radis kad, koliko i odakle zelis.. Budi deo mog tima, gde se trud isplati!",'Josanicka Banja',now(), date_add(now(), interval 6 month),75000,true,'ekonomista','markteing',1);



insert into prijava(idKorisnika, idOglasa, datumPrijave)
values(4,4,now());
insert into prijava(idKorisnika, idOglasa, datumPrijave)
values(4,6,now());


insert into komentar(idOglasa,idKorisnika,tekst,datum_postavljanja)
values(5,4,"Kada moze da se pocne sa radom?",now());





delimiter //
create procedure SviOglasi()
begin
   select * 
    from oglas join korisnik on oglas.idPoslodavca =  korisnik.id
    where now() between datumPostavljanja and datumZavrsetka and aktiviran = 1;
end
//
delimiter //
create procedure jedanOglas(id bigint)
begin
   select * 
    from oglas join korisnik on oglas.idPoslodavca =  korisnik.id 
    where id = oglas.id and aktiviran = 1;
end
//
delimiter //
create procedure dajGradOglas(grad varchar(500))
begin
   select * 
    from oglas join korisnik on oglas.idPoslodavca =  korisnik.id
    where now() between datumPostavljanja and datumZavrsetka and upper(oglas.mesto) = upper(grad) and aktiviran = 1; 
end
//
delimiter //
create procedure dajKategorijaOglas(kat varchar(500))
begin
   select * 
    from oglas join korisnik on oglas.idPoslodavca =  korisnik.id
    where now() between datumPostavljanja and datumZavrsetka and upper(oglas.kategorija) = upper(kat) and aktiviran = 1; 
end
//
delimiter //
create procedure dajGradKategorijaOglas(grad varchar(500), kat varchar(500))
begin
   select * 
    from oglas join korisnik on oglas.idPoslodavca =  korisnik.id
    where now() between datumPostavljanja and datumZavrsetka and upper(oglas.kategorija) =upper( kat) and upper(oglas.mesto) =upper(grad) and aktiviran = 1; 
end
//
delimiter //
create procedure jednaKorisnik(idK bigint)
begin
   select *
    from korisnik
    where korisnik.id = idK;
end
//


delimiter //
create procedure jedanNalog(idN bigint)
begin 
   select *
    from nalog join korisnik on nalog.idKorisnika = korisnik.id
    where nalog.id = idN;
end
//

delimiter //
create procedure sviNalozi()
begin
   select *
    from nalog join korisnik on nalog.idKorisnika = korisnik.id;
end
//
delimiter //
create procedure oglasePrekoKorImena(imeKorisnika varchar(500))
begin
   select *
    from oglas join korisnik on oglas.idPoslodavca =  korisnik.id join nalog on nalog.idKorisnika = korisnik.id
    where now() between datumPostavljanja and datumZavrsetka and nalog.korisnickoIme = imeKorisnika and aktiviran = 1;
end
//
delimiter //
create procedure dajrm(uloga varchar(500))
begin
   select *
    from korisnik join nalog on korisnik.id = nalog.idKorisnika join rolemodel on nalog.id = rolemodel.idNaloga
    where rolemodel.roleModel = uloga;
end
//


delimiter //
create procedure obrisiKorisnika(idK bigint)
begin
   delete from korisnik where korisnik.id = idK;
end
//

delimiter //
create procedure obrisiOglas(idO bigint)
begin
   delete from oglas where oglas.id = idO;
end
//

delimiter //
create procedure unesiKorisnika(novoIme varchar(500), novoPrezime varchar(500), noveMail varchar(500), novDatumRodjenja date, novPol varchar(50))
begin
   insert into korisnik(ime,prezime,eMail,datumRodjenja,pol,slika)
   values(novoIme,novoPrezime,noveMail,novDatumRodjenja,novPol,null);
end
//

delimiter //
create procedure unesiNalog(idN bigint, imeKorisnika varchar(500), sifra varchar(500))
begin
   insert into nalog(idKorisnika, korisnickoIme, lozinka, onOff)
   values(idN, imeKorisnika, sifra, true);
end
//

delimiter //
create procedure dajNalogKorisnika(imeKorisnika varchar(500))
begin
   select *
    from korisnik join nalog on korisnik.id = nalog.idKorisnika
    where nalog.korisnickoIme= imeKorisnika;
end
//

delimiter //
create procedure unesiRoleModel(idK bigint, imeKorisnika varchar(500), uloga varchar(500))
begin
   insert into rolemodel(idNaloga, korisnickoIme, roleModel)
   values(idK, imeKorisnika, uloga);
end
//

delimiter //
create procedure dajPoslednjiIDKorisnika()
begin
   select max(id)
    from korisnik;
end
//

delimiter //
create procedure dajPoslednjiIDNaloga()
begin
   select max(id)
    from nalog;
end
//

delimiter //
create procedure dajeMail(mail varchar(500))
begin
   select * from korisnik
    where eMail = mail;
end
//

delimiter //
create procedure prijaviKorisnikaNaOglas(idK bigint, idO bigint)
begin
   insert into prijava(idKorisnika, idOglasa ,datumPrijave)
   values(idK, idO, now());
end
//

delimiter //
create procedure dajPrijavuKorisnikaNaOglas(idK bigint, idO bigint)
begin
   select *
    from prijava
    where idKorisnika = idK and idOglasa = idO;
end
//

delimiter //
create procedure postaviOglas(idP bigint, naziv varchar(500), opis varchar(1500), grad varchar(500), novac double,kat varchar(500), podkat varchar(500),rOdK tinyint)
begin
   insert into oglas(idPoslodavca, naslov, tekst, mesto, datumPostavljanja, datumZavrsetka, plata, aktiviran,kategorija,podkategodrija,radOdKuce)
   values(idP, naziv, opis, grad, now(), date_add(now(), interval 1 month), novac, 1,kat,podkat,rOdK);
end
//

delimiter //
create procedure promeniPodatkeKorisnika(idK bigint, novoIme varchar(500), novoPrezime varchar(500), noveMail varchar(500),novDatum date, novPol varchar(50))
begin
   update korisnik
   set ime = novoIme, prezime = novoPrezime, eMail = noveMail,  datumRodjenja = novDatum, pol = novPol
   where id = idK;
end
//

delimiter //
create procedure promeniSliku(idK bigint, novaSlika longblob)
begin
   update korisnik
   set  slika= novaSlika
   where id = idK;
end
//

-- FUNKCIJE ZA KOMENTAR

delimiter //
create procedure dajPoslednjiIDKomentara()
begin
   select max(id)
    from komentar;
end
//

delimiter //
create procedure dodajKomentar(idO bigint, idK bigint, kom varchar(1500))
begin
    insert into komentar(idOglasa, idKorisnika,tekst, datum_postavljanja)
    values(idO, idK,kom, now());
end
//

delimiter //
create procedure obrisiKomentar(idK bigint)
begin
    delete from komentar where id = idK;
end
//

delimiter //
create procedure jedanKomentar(idK bigint)
begin
    select * from komentar join oglas on komentar.idOglasa = oglas.id join korisnik on komentar.idKorisnika = korisnik.id
    where komentar.id = idK;
end
//

delimiter //
create procedure dajSveKomentare()
begin
    select * from komentar join oglas on komentar.idOglasa = oglas.id join korisnik on komentar.idKorisnika = korisnik.id 
    order by convert(komentar.datum_postavljanja, datetime) desc;
end
//

-- KRAJ FUNKCIJA ZA KOMENTAR

delimiter //
create procedure vratiUlogu(idK bigint)
begin
    select rolemodel.roleModel
    from korisnik join nalog on korisnik.id = nalog.idKorisnika join rolemodel on nalog.id = rolemodel.idNaloga
    where korisnik.id = idK;
end
//




delimiter //
create procedure onemoguciNalog(idN bigint)
begin
   update nalog set onOff = 0 where id = idN;
end
//

delimiter //
create procedure omoguciKorisnika(idN bigint)
begin
   update nalog set onOff = 1 where id = idN;
end
//

delimiter //
create procedure poslodavacURadnika(idN bigint, idP bigint)
begin
   update rolemodel set roleModel = 'RADNIK' where idNaloga = idN;
    update oglas set aktiviran = 0 where idPoslodavca = idP;
end
//

delimiter //
create procedure radnikUPoslodavca(idN bigint, idP bigint)
begin
 update rolemodel set roleModel = 'POSLODAVAC' where idNaloga = idN;
    update oglas set aktiviran = 1 where idPoslodavca = idP;
end
//

delimiter //
create procedure postaviZaAdmina(idN bigint)
begin
   update autoritet set roleModel = 'ADMIN' where idNaloga = idN;
end
//
delimiter //
create procedure dajPrijaveNaOglasPoslodavca(idP bigint, idO bigint)
begin
   select *
    from prijava join korisnik on prijava.idKorisnika = korisnik.id 
    join nalog on korisnik.id = nalog.idKorisnika 
    join rolemodel on nalog.id = rolemodel.idNaloga
    join oglas on prijava.idOglasa = oglas.id
    where (rolemodel.roleModel = 'RADNIK' or rolemodel.roleModel = 'ADMIN') and idP = oglas.idPoslodavca and idO = prijava.idOglasa;
end
//

delimiter //
create procedure dajIDPoslodavcaOglasa(idO bigint)
begin
   select korisnik.id
    from oglas join korisnik on oglas.idPoslodavca = korisnik.id
    join nalog on korisnik.id = nalog.idKorisnika
    where oglas.id = idO;
end
//
-- FUNKCIJE ZA PREGLEDE i LAJKOVANJE

delimiter //
create procedure dajPregled(idP bigint)
begin
    select * from pregled join oglas on pregled.idOglasa = oglas.id join korisnik on pregled.idKorisnika = korisnik.id
    where pregled.id = idP;
end
//

delimiter //
create procedure dajSvePreglede()
begin
    select * from pregled join oglas on pregled.idOglasa = oglas.id join korisnik on pregled.idKorisnika = korisnik.id;
end
//
delimiter //
create procedure dajLajk(idL bigint)
begin
    select * from lajk join oglas on lajk.idOglasa = oglas.id join korisnik on lajk.idKorisnika = korisnik.id
    where lajk.id = idL;
end
//

delimiter //
create procedure dajSveLajkove()
begin
    select * from lajk join oglas on lajk.idOglasa = oglas.id join korisnik on lajk.idKorisnika = korisnik.id;
end
//

delimiter //
create procedure dajSvePregledeKorisnika(idK bigint)
begin
    select * from pregled join oglas on pregled.idOglasa = oglas.id join korisnik on pregled.idKorisnika = korisnik.id
    where pregled.idKorisnika = idK;
end
//

delimiter //
create procedure dajSveLajkoveOsobe(idK bigint)
begin
    select * from lajk join oglas on pregled.idOglasa = lajk.id join korisnik on lajk.idKorisnika = korisnik.id
    where lajk.idKorisnika = idK;
end
//

delimiter //
create procedure dajBrojPregledaOglasa(idO bigint)
begin
    select count(id) from pregled
    where pregled.idOglasa = idO;
end
//

delimiter //
create procedure dajBrojLajkovaOglasa(idO bigint)
begin
    select count(id) from lajk
    where lajk.idOglasa = idO;
end
//

delimiter //
create procedure dodajLajk(idO bigint, idK bigint)
begin
    insert into lajk(idOglasa, idKorisnika)
    values(idO, idK);
end
//
delimiter //
create procedure dodajPregled(idO bigint, idK bigint)
begin
    insert into pregled(idOglasa, idKorisnika)
    values(idO, idK);
end
//

delimiter //
create procedure obrisiLajk(idO bigint, idK bigint)
begin
	delete from lajk 
    where idOglasa = idO and idKorisnika = idK;
end
//

delimiter //
create procedure pregledaoOglas(idO bigint, idK bigint)
begin
    select * from pregled
    where idOglasa = idO and idKorisnika = idK;
end
//

delimiter //
create procedure lajkovoOglas(idO bigint, idK bigint)
begin
    select * from lajk
    where idKorisnika = idK and idOglasa = idO;
end
//
-- KRAJ FUNKCIJA ZA PREGLEDE/LAJKOVANJE