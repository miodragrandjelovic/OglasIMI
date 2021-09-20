package com.example.Projekat.Baza;

import com.example.Projekat.ObicneKlase.Korisnik;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class KorisnikBaza extends baza {

    public KorisnikBaza() throws SQLException{}


    public Korisnik korisnikPoImenu(String ime) throws SQLException, UnsupportedEncodingException {
        sql = "call PronadjiKorisnickoIme('" + ime + "');";
        resultset = statement.executeQuery(sql);
        resultset.next();
        if(resultset.getBytes("korisnik.slika") != null)
        {
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
                    resultset.getString("korisnik.pol"),
                    "data:image/;base64," + new String(resultset.getBytes("korisnik.slika"), "UTF-8")
            );
            return korisnik;
        }
        else if(resultset.getBytes("korisnik.slika") == null){
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
                    resultset.getString("korisnik.pol"),
                    null
            );
            return korisnik;
        }
        else
            return null;
    }

    public boolean unesiKorisnika(Korisnik korisnik, String uloga, byte[] slika) throws Exception {
        sql = "call PronadjiKorisnickoIme('" + korisnik.getIme() + "');";
        resultset = statement.executeQuery(sql);
        if(resultset.first())
            throw new Exception("greskaKorisnickoIme");

        sql = "call PronadjiEmail('" + korisnik.geteMail() + "');";
        resultset = statement.executeQuery(sql);
        if(resultset.first())
            throw new Exception("greskaEmail");

        sql = "call UnesiKorisnika('" + korisnik.getKorisnickoIme() + "', '" + korisnik.getLozinka() + "', '" + korisnik.getIme()
                + "', '" + korisnik.getPrezime() + "', '" + korisnik.geteMail() + "', '" + korisnik.getDatumRodjenja()
                + "', '" + korisnik.getDrzava() + "', '" + korisnik.getGrad() + "', '" + korisnik.getPol() + "');";
        int izvrsenKorisnik = statement.executeUpdate(sql);

        sql = "call zadnjiIdKorisnika();";
        resultset = statement.executeQuery(sql);
        resultset.first();
        long maxId = resultset.getLong("max(id)");

        sql = "call UnesiRoleModel(" + maxId + ", '" +  korisnik.getKorisnickoIme() + "', '" + uloga + "');";
        int izvrsenRoleModel = statement.executeUpdate(sql);

        if(slika != null)
            postaviSlikuKorisnika(maxId, Base64.getEncoder().encode(slika));

        if(izvrsenKorisnik > 0 && izvrsenRoleModel > 0)
            return true;
        else
            return false;
    }

    public boolean postaviSlikuKorisnika(long id, byte[] slika)  throws SQLException{
        CallableStatement stmt = dajVezu().prepareCall("call PostaviSlikuKorisnika(?,?)");
        stmt.setLong(1, id);
        stmt.setBytes(2, slika);
        int izvrseno = stmt.executeUpdate();
        if(izvrseno > 0) return true;
        else return false;
    }

    public boolean promeniPodatkeKorisnika(Korisnik korisnik) throws SQLException{
        sql = "call PromeniPodatkeKorisnika("   + korisnik.getId() + ", '" + korisnik.getIme()
                                                + "', '" + korisnik.getPrezime() + "', '"  + korisnik.getDatumRodjenja()
                                                + "', '"  + korisnik.getDrzava() + "', '"  + korisnik.getGrad() + "', '"
                                                + korisnik.getPol() + "');";
        int izvrseno = statement.executeUpdate(sql);

        if(izvrseno > 0)
            return true;
        else
            return false;
    }

    public List<Korisnik> sviKorisnici() throws SQLException, UnsupportedEncodingException {
        List<Korisnik> korisnikList = new ArrayList<Korisnik>();

        sql = "call sviKorisnici();";
        resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            if(resultset.getBytes("korisnik.slika") != null)
            {
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
                        resultset.getString("korisnik.pol"),
                        "data:image/;base64," + new String(resultset.getBytes("korisnik.slika"), "UTF-8")
                );
                korisnikList.add(korisnik);
            }
            else{
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
                        resultset.getString("korisnik.pol"),
                        null
                        );
                korisnikList.add(korisnik);
            }
        }
        return korisnikList;
    }

    public List<Korisnik> sviPoslodavci() throws SQLException, UnsupportedEncodingException {
        List<Korisnik> poslodavciList = new ArrayList<Korisnik>();

        sql = "call sviPoslodavci();";
        resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            if(resultset.getBytes("korisnik.slika") != null)
            {
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
                        resultset.getString("korisnik.pol"),
                        "data:image/;base64," + new String(resultset.getBytes("korisnik.slika"), "UTF-8")
                );
                poslodavciList.add(korisnik);
            }
            else{
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
                        resultset.getString("korisnik.pol"),
                        null
                );
                poslodavciList.add(korisnik);
            }
        }
        return poslodavciList;
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
