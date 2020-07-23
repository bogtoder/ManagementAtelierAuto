package bogtod.programari;

import bogtod.angajati.Angajat;

public class ProgramareSpeciala {

    // ca o programare simpla, dar depinde de un angajat anume

    private Angajat angajatAles;
    private Programare progSimpla;

    public ProgramareSpeciala(Programare progSimpla, Angajat angajatAles) {
        this.progSimpla = progSimpla;
        this.angajatAles = angajatAles;
    }

    public Angajat getAngajatAles() {
        return angajatAles;
    }

    public Programare getProgSimpla() {
        return progSimpla;
    }
}
