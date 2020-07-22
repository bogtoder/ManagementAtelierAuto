package bogtod.angajati;

import java.util.Date;

public class Mecanic extends Angajat {

    private static Integer mecanicCounter = 20000;
    // ID-urile mecanicilor incep cu 2

    public Mecanic(String nume, String prenume, Date dataNastere, Date dataAngajare) {
        super(++mecanicCounter, nume, prenume, dataNastere, dataAngajare, 1.5d);
    }



}
