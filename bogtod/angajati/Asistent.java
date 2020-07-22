package bogtod.angajati;

import java.util.Date;

public class Asistent extends Angajat {

    private static Integer asistentCounter = 30000;
    // ID-urile asistentilor incep cu 3

    public Asistent(String nume, String prenume, String dataNastere, String dataAngajare) {
        super(++asistentCounter, nume, prenume, dataNastere, dataAngajare, 1d);
    }
}
