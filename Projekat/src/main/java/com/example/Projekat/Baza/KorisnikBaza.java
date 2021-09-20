package com.example.Projekat.Baza;

import com.example.Projekat.ObicneKlase.Korisnik;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KorisnikBaza extends baza {

    public KorisnikBaza() throws SQLException{}

    /*
    public Korisnik jedanKorisnik(long id) throws SQLException, UnsupportedEncodingException {
        sql = "call jedanKorisnik(" + id + ");";
        resultset = statement.executeQuery(sql);
        if(resultset.first()) {
            Korisnik korisnik = new Korisnik(
                    resultset.getLong("korisnik.id"),
                    resultset.getString("korisnik.korisnickoIme"),
                    resultset.getString("korisnik.lozinka"),
                    resultset.getString("korisnik.eMail"),
                    resultset.getBoolean("korisnik.aktivan"),
                    resultset.getString("korisnik.ime"),
                    resultset.getString("korisnik.prezime"),
                    resultset.getDate("korisnik.datumRodjenja"),
                    resultset.getString("korisnik.drzava"),
                    resultset.getString("korisnik.grad"),
                    resultset.getString("korisnik.pol")
            );


            return korisnik;
        }
        return null;
    }*/


    public Korisnik korisnikPoImenu(String ime) throws SQLException, UnsupportedEncodingException {
        sql = "call PronadjiKorisnickoIme(" + ime + ");";
        resultset = statement.executeQuery(sql);
        if(resultset.first()) {
            Korisnik korisnik = new Korisnik(
                    resultset.getLong("korisnik.id"),
                    resultset.getString("korisnik.korisnickoIme"),
                    resultset.getString("korisnik.lozinka"),
                    resultset.getString("korisnik.eMail"),
                    resultset.getBoolean("korisnik.aktivan"),
                    resultset.getString("korisnik.ime"),
                    resultset.getString("korisnik.prezime"),
                    resultset.getDate("korisnik.datumRodjenja"),
                    resultset.getString("korisnik.drzava"),
                    resultset.getString("korisnik.grad"),
                    resultset.getString("korisnik.pol")
            );
            return korisnik;
        }
        return null;
    }

    public boolean unesiKorisnika(Korisnik korisnik, String uloga) throws Exception {
        sql = "call PronadjiKorisnickoIme('" + korisnik.getIme() + "');";
        resultset = statement.executeQuery(sql);
        if(resultset.first())
            throw new Exception("greskaKorisnickoIme");

        sql = "call PronadjiEmail('" + korisnik.geteMail() + "');";
        resultset = statement.executeQuery(sql);
        if(resultset.first())
            throw new Exception("greskaEmail");

        sql = "call UnesiKorisnika('" + korisnik.getKorisnickoIme() + ", " + korisnik.getLozinka() + ", " + korisnik.getIme()
                + ", " + korisnik.getPrezime() + ", " + korisnik.geteMail() + ", " + korisnik.getDatumRodjenja()
                + ", " + korisnik.getDrzava() + ", " + korisnik.getGrad() + ", " + korisnik.getPol() + "');";
        int izvrsenKorisnik = statement.executeUpdate(sql);

        sql = "call UnesiRoleModel(" + korisnik.getId() + ", " +  korisnik.getIme() + ", " + uloga + ");";
        int izvrsenRoleModel = statement.executeUpdate(sql);

        if(izvrsenKorisnik > 0 && izvrsenRoleModel > 0)
            return true;
        else
            return false;
    }

    public boolean postaviSlikuKorisnika(long id, byte[] slika)  throws SQLException{
        CallableStatement stmt = dajVezu().prepareCall("call promeniProfilnuSliku(?,?)");
        stmt.setLong(1, id);
        stmt.setBytes(2, slika);
        int izvrseno = stmt.executeUpdate();
        if(izvrseno > 0)
            return true;
        else
            return false;
    }

    public boolean promeniPodatkeKorisnika(Korisnik korisnik) throws SQLException{
        sql = "call promeniPodatkeOsobe(" + korisnik.getId() + ", " + korisnik.getKorisnickoIme() + ", " + korisnik.getLozinka() + ", " + korisnik.getIme()
                + ", " + korisnik.getPrezime() + ", " + korisnik.geteMail() + ", " + korisnik.getDatumRodjenja()
                + ", " + korisnik.getDrzava() + ", " + korisnik.getGrad() + ", " + korisnik.getPol() + "');";
        int izvrseno = statement.executeUpdate(sql);

        if(izvrseno > 0)
            return true;
        else
            return false;
    }

    public List<Korisnik> sviKorisnici() throws SQLException {
        List<Korisnik> korisnikList = new ArrayList<Korisnik>();

        sql = "call sviKorisnici();";
        resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            Korisnik korisnik = new Korisnik(
                    resultset.getLong("korisnik.id"),
                    resultset.getString("korisnik.korisnickoIme"),
                    resultset.getString("korisnik.lozinka"),
                    resultset.getString("korisnik.eMail"),
                    resultset.getBoolean("korisnik.aktivan"),
                    resultset.getString("korisnik.ime"),
                    resultset.getString("korisnik.prezime"),
                    resultset.getDate("korisnik.datumRodjenja"),
                    resultset.getString("korisnik.drzava"),
                    resultset.getString("korisnik.grad"),
                    resultset.getString("korisnik.pol")
            );
            korisnikList.add(korisnik);
        }
        return korisnikList;
    }


    public boolean onemoguciKorisnika(long id) throws SQLException {
        sql = "call onemoguciKorisnika(" + id +");";
        int izvrseno = statement.executeUpdate(sql);
        if(izvrseno > 0)
            return true;
        return false;
    }

    public  boolean omoguciKorisnika(long id) throws SQLException {
        sql = "call omoguciKorisnika(" + id + ");";
        int izvresno = statement.executeUpdate(sql);
        if(izvresno > 0)
            return true;
        return false;
    }

    public boolean poslodavacURadnika(long idKorisnika, long idOsobe) throws SQLException {
        sql = "call poslodavacURadnika(" + idKorisnika + "," + idOsobe + ");";
        int izvrseno = statement.executeUpdate(sql);
        if(izvrseno > 0)
            return true;
        return false;
    }

    public boolean radnikUPoslodavca(long idKorisnika, long idOsobe) throws SQLException {
        sql = "call radnikUPoslodavca(" + idKorisnika + "," + idOsobe + ");";
        int izvresno = statement.executeUpdate(sql);
        if(izvresno > 0)
            return true;
        return false;
    }

    public Long dajIDPoslodavcaOglasa(long idOglas) throws SQLException {
        sql = "call dajIDPoslodavcaOglasa(" + idOglas + ");";
        resultset = statement.executeQuery(sql);
        if(resultset.first()) {
            return resultset.getLong("osoba.id");
        }
        return null;
    }

    public boolean postaviZaAdmina(long idKorisnika) throws SQLException {
        sql = "call postaviZaAdmina(" + idKorisnika + ");";
        int izvrseno = statement.executeUpdate(sql);
        if(izvrseno > 0)
            return true;
        return false;
    }


}
