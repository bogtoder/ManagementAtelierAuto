package bogtod.angajati;

import java.util.Date;

public class Director extends Angajat {

    private static Integer directorCounter = 10000;
    // ID-urile directorilor vor incepe cu 1 in stanga

    public Director(String nume, String prenume, Date dataNastere, Date dataAngajare) {
        super(++directorCounter, nume, prenume, dataNastere, dataAngajare, 2d);
        // trimitem in superclasa ID-ul incrementat in directorCounter
        // si punem manual coeficientul 2

    }
}
