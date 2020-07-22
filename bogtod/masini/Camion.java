package bogtod.masini;

import java.time.Year;

public class Camion extends Masina {

    private Integer tonaj;

    public Camion(Integer ID, Integer nrKilometri, Integer anFabricatie, Boolean esteDiesel, Integer tonaj) {
        super(ID, nrKilometri, anFabricatie, esteDiesel);

        if(tonaj <= 0 || tonaj > 100) {
            throw new IllegalArgumentException("Tonaj invalid.");
        }

        this.tonaj = tonaj;
    }

    @Override
    public Double calculeazaAsigurare(Boolean discount) {
        Integer valoare = (Year.now().getValue() - this.getAnFabricatie()) * 300;
        // aflu vechimea apoi inmultesc cu 100

        if(this.getNrKilometri() > 800000) valoare += 700;

        if(discount) return (valoare - 0.15 * valoare);
        return valoare.doubleValue();
    }
}
