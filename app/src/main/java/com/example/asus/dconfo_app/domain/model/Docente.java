package com.example.asus.dconfo_app.domain.model;

public class Docente {
    private Integer iddocente;
    private String namedocente;
    private String emaildocente;
    private String passdocente;
    private Integer dnidocente;

    public Integer getIddocente() {
        return iddocente;
    }

    public void setIddocente(Integer iddocente) {
        this.iddocente = iddocente;
    }

    public String getNamedocente() {
        return namedocente;
    }

    public void setNamedocente(String namedocente) {
        this.namedocente = namedocente;
    }

    public Integer getDnidocente() {
        return dnidocente;
    }

    public void setDnidocente(Integer dnidocente) {
        this.dnidocente = dnidocente;
    }

    public String getEmaildocente() {
        return emaildocente;
    }

    public void setEmaildocente(String emaildocente) {
        this.emaildocente = emaildocente;
    }

    public String getPassdocente() {
        return passdocente;
    }

    public void setPassdocente(String passdocente) {
        this.passdocente = passdocente;
    }
}
