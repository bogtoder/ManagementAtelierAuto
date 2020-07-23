package bogtod;

import bogtod.angajati.Angajat;
import bogtod.programari.Programare;

import java.util.*;

public class Raportare {

    public static Raportare instance = null;

    private Raportare() {
    }

    public static Raportare getInstance() {
        if(instance == null) {
            instance = new Raportare();
        }
        return instance;
    }

    public Angajat cerinta1(List<Angajat> listaAngajati,
                            Map<Angajat, Queue<Programare>> programAtelier) {
        if(listaAngajati.isEmpty()) {
            System.out.println("Nu exista angajati.");
            return null;
        }

        int max = -1;
        Angajat angIncarcatMax = null;
        for(Angajat ang : listaAngajati) {
            if(!programAtelier.get(ang).isEmpty())
                if(programAtelier.get(ang).size() >= max) {
                    max = programAtelier.get(ang).size();
                    angIncarcatMax = ang;
                }
        }

        if(max == -1) {
            System.out.println("Nu munceste nimeni inca.");
            return null;
        }

        System.out.println("Cel mai incarcat angajat este: " + angIncarcatMax.toString());
        return angIncarcatMax;
    }
}
