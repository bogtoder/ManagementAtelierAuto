package bogtod;

import bogtod.angajati.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Atelier atl1 = new Atelier();

        atl1.adaugaAngajati(new Mecanic("Ion", "Ion", "23/06/1985", "12/07/2010"));
        atl1.adaugaAngajati(new Director("Popescu", "Alexandru", "18/07/1990", "15/02/2012"));
        atl1.adaugaAngajati(new Asistent("Asis", "Tent", "22/12/2000", "21/12/2019"));

        AngajatiApp.getInstance().afiseazaAngajati(atl1.getListaAngajati());

        atl1.programeazaMasina();

        while(atl1.existaLucrari()) {
            System.out.println("Avansez timpu");
            atl1.updateSituatieAtelier();
        }
    }
}
