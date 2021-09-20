package com.example.Projekat.ObicneKlase;

public class RoleModel {

    private long id;
    private Korisnik korisnik;
    private String roleModel;

    public RoleModel(){}

    public RoleModel(long id, Korisnik korisnik, String roleModel){
        this.id = id;
        this.korisnik = korisnik;
        this.roleModel = roleModel;
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

    public void setIdKorisnika(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(String roleModel) {
        this.roleModel = roleModel;
    }
}
