package Kontroleri;

import java.sql.Date;

public class Prijava {

    private long id;
    private Korisnik korisnik;
    private Oglas oglas;
    private Date datumPrijave;

    public Prijava(){}

    public Prijava(long id, Korisnik korisnik, Oglas oglas, Date datumPrijave){
        this.id = id;
        this.korisnik = korisnik;
        this.oglas = oglas;
        this.datumPrijave = datumPrijave;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }

    public Date getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(Date datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

}
