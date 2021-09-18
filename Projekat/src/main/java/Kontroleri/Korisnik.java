package Kontroleri;

import java.sql.Date;

public class Korisnik {

    private long id;
    private String ime;
    private String prezime;
    private String eMail;
    private Date datumRodjenja;
    private String pol;

    private String korisnickoIme;
    private String lozinka;

    private boolean aktivan;

    private String slika;

    public Korisnik(){}

    public Korisnik(long id, String korisnickoIme, String lozinka, String eMail, boolean aktivan, String ime, String prezime, Date datumRodjenja, String drzava, String grad, String pol, String slika) {
        this.id = id; this.korisnickoIme = korisnickoIme; this.lozinka = lozinka; this.eMail = eMail; this.aktivan = aktivan; this.ime = ime; this.prezime = prezime; this.datumRodjenja = datumRodjenja; this.pol = pol; this.slika = slika;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public String getIme() {
        return ime;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

}
