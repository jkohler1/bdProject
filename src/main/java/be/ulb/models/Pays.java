package be.ulb.models;

public class Pays {
    String isoCode;
    String nom;
    Double hdi;
    int population;
    Double superficie;

    public Pays(String isoCode, String nom, Double hdi, int population, Double superficie) {
        this.isoCode = isoCode;
        this.nom = nom;
        this.hdi = hdi;
        this.population = population;
        this.superficie = superficie;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getHdi() {
        return hdi;
    }

    public void setHdi(Double hdi) {
        this.hdi = hdi;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }
}
