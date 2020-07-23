package bogtod.masini;

import java.time.Year;

public class MasinaStandard extends Masina {

    private String transmisie;

    public MasinaStandard(Integer nrKilometri, Integer anFabricatie, Boolean esteDiesel, String transmisie) {
        super(nrKilometri, anFabricatie, esteDiesel);

        if(!transmisie.toLowerCase().equals("manuala") && !transmisie.toLowerCase().equals("automata"))
            throw new IllegalArgumentException("Transmisia poate fi doar manuala sau automata.");

        this.transmisie = transmisie;
    }

    public void setTransmisie(String transmisie) {
        this.transmisie = transmisie;
    }

    public String getTransmisie() {
        return transmisie;
    }

    @Override
    public Double calculeazaAsigurare(Boolean discount) {
        Integer valoare = (Year.now().getValue() - this.getAnFabricatie()) * 100;
        // aflu vechimea apoi inmultesc cu 100

        if(this.getEsteDiesel()) valoare += 500;
        if(this.getNrKilometri() > 200000) valoare += 500;

        if(discount) return (valoare - 0.05 * valoare);
        return valoare.doubleValue();
    }
}
