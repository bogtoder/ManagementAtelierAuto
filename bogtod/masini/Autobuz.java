package bogtod.masini;

import java.time.Year;

public class Autobuz extends Masina {

    private Integer nrLocuri;

    public Autobuz(Integer ID, Integer nrKilometri, Integer anFabricatie, Boolean esteDiesel, Integer nrLocuri) {
        super(ID, nrKilometri, anFabricatie, esteDiesel);

        if(nrLocuri <= 0 || nrLocuri > 100) {
            throw new IllegalArgumentException("Nr de locuri invalid.");
        }

        this.nrLocuri = nrLocuri;
    }

    @Override
    public Double calculeazaAsigurare(Boolean discount) {
        Integer valoare = (Year.now().getValue() - this.getAnFabricatie()) * 200;
        // aflu vechimea apoi inmultesc cu 100

        if(this.getEsteDiesel()) valoare += 1000;
        if(this.getNrKilometri() > 200000) valoare += 1000;
        else if(this.getNrKilometri() > 100000) valoare += 500;

        if(discount) return (valoare - 0.1 * valoare);
        return valoare.doubleValue();
    }
}
