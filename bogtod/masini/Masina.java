package bogtod.masini;

public abstract class Masina {

    private Integer ID;
    private Integer nrKilometri;
    private Integer anFabricatie;
    private Boolean esteDiesel;

    public Masina(Integer ID, Integer nrKilometri, Integer anFabricatie, Boolean esteDiesel) {
        this.ID = ID;
        this.nrKilometri = nrKilometri;
        this.anFabricatie = anFabricatie;
        this.esteDiesel = esteDiesel;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setNrKilometri(Integer nrKilometri) {
        this.nrKilometri = nrKilometri;
    }

    public void setAnFabricatie(Integer anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public void setEsteDiesel(Boolean esteDiesel) {
        this.esteDiesel = esteDiesel;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getNrKilometri() {
        return nrKilometri;
    }

    public Integer getAnFabricatie() {
        return anFabricatie;
    }

    public Boolean getEsteDiesel() {
        return esteDiesel;
    }

    public abstract Double calculeazaAsigurare(Boolean discount);

}
