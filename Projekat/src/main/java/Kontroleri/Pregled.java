package Kontroleri;

public class Pregled {

    private long id;
    private Korisnik korisnik;
    private Oglas oglas;

    public long getId() {
        return id;
    }

    public Pregled(){}

    public Pregled(long id, Korisnik korisnik, Oglas oglas){
        this.id = id;
        this.korisnik = korisnik;
        this.oglas = oglas;
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

}
