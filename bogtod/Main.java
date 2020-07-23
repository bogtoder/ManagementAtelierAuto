package bogtod;

import bogtod.angajati.*;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.println("Test Atelier.");

        Atelier atl = new Atelier();

        // cateva intrari scrie manual pt rapiditate
        atl.adaugaAngajati(new Mecanic("Ion", "Ion", "23/06/1985", "12/07/2010"));
        atl.adaugaAngajati(new Director("Popescu", "Alexandru", "18/07/1990", "15/02/2012"));
        atl.adaugaAngajati(new Asistent("Asis", "Tent", "22/12/2000", "21/12/2019"));

        AngajatiApp.getInstance().afiseazaAngajati(atl.getListaAngajati());


        Integer opt = 0;
        Scanner scn = new Scanner(System.in);

        do {
            afiseazaOptiuni();
            System.out.print("Alegerea dvs: "); opt = scn.nextInt();

            switch (opt) {
                default:
                    System.out.println("Nu exista aceasta optiune.");
                    break;

                case 0:
                    System.out.println("O zi buna.");
                    break;

                case 1:
                    // se avanseaza starea atelierului pe parcursul rularii
                    // programului, aceasta optiune este mai mult un buton de skip
                    break;

                case 2:
                    atl.afiseazaAngajati();
                    break;

                case 3:
                    atl.adaugaAngajati();
                    break;

                case 4:
                    atl.eliminaAngajat();
                    break;

                case 5:
                    atl.modificaAngajat();
                    break;

                case 6:
                    atl.calculeazaSalariuAngajat();
                    break;

                case 7:
                    atl.programeazaMasina();
                    break;

                case 8:
                    atl.calculeazaPolitaAsigurare();
                    break;

                case 9:
                    Raportare.getInstance().cerinta1(
                            atl.getListaAngajati(), atl.getProgramAtelier()
                    );
            }

            atl.updateSituatieAtelier();
        } while (opt != 0);


    }

    public static void afiseazaOptiuni() {
        System.out.println("1. Avanseaza timp. (avanseaza reparatiile)");
        System.out.println("2. Afiseaza angajati.");
        System.out.println("3. Insereaza angajat.");
        System.out.println("4. Sterge angajat.");
        System.out.println("5. Modifica angajat.");
        System.out.println("6. Calculeaza salariu angajat.");
        System.out.println("7. Programeaza masina.");
        System.out.println("8. Calculeaza polita de asigurare pentru o masina.");
        System.out.println("9. RAPORTARE: Angajatul cu cea mai mare incarcare.");
        System.out.println("0. Exit.");
    }
}
