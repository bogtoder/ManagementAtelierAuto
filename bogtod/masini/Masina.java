package bogtod.masini;

public abstract class Masina {

    private static Integer masiniCounter = 0;
    private Integer ID;
    private Integer nrKilometri;
    private Integer anFabricatie;
    private Boolean esteDiesel;

    public Masina(Integer nrKilometri, Integer anFabricatie, Boolean esteDiesel) {
        this.ID = ++masiniCounter;
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

    @Override
    public String toString() {
        return  "ID=" + ID +
                ", nrKilometri=" + nrKilometri +
                ", anFabricatie=" + anFabricatie +
                ", esteDiesel=" + esteDiesel;
    }
}
