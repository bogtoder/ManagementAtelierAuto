package bogtod.angajati;

import java.util.Date;

public class Angajat {

    private Integer ID;
    private String nume;
    private String prenume;
    private Date dataNastere;
    private Date dataAngajare;
    private Double coeficientAngajat;

    public Angajat(Integer ID, String nume, String prenume, Date dataNastere, Date dataAngajare, Double coeficientAngajat) {
        this.ID = ID;
        this.nume = nume;
        this.prenume = prenume;
        this.dataNastere = dataNastere;
        this.dataAngajare = dataAngajare;
        this.coeficientAngajat = coeficientAngajat;
    }
}
