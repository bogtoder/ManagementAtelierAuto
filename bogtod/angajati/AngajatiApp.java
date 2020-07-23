package bogtod.angajati;

import java.util.*;

public class AngajatiApp {

    // clasa de serviciu pentru a lucra cu angajatii
    // poate fi singleton

    public static AngajatiApp instance = null;
    private Scanner scn;

    private AngajatiApp() {
        scn = new Scanner(System.in);
    }

    public static AngajatiApp getInstance() {
        if(instance == null) {
            instance = new AngajatiApp();
        }
        return instance;
    }

    public Angajat cautaAngajatDupaID(List<Angajat> listaAngajati) {
        Angajat temp = null;
        while(temp == null) {
            System.out.print("ID ales: "); Integer ID = scn.nextInt();
            for (Angajat a : listaAngajati) {
                if (a.getID().equals(ID)) {
                    temp = a;
                    break;
                }
            }
            if(temp == null) {
                System.out.println("ID-ul ales nu exista, incercati inca o data.");
            }
        }

        return temp;
    }

    public void afiseazaAngajati(List<Angajat> listaAngajati) {
        System.out.println("Exista urmatorii angajati: ");
        for(Angajat a : listaAngajati) {
            System.out.println(a.getID() + ", " + a.getNume() + ", " + a.getPrenume());
        }
    }

    public Angajat inregistreazaAngajat() {
        // curat consola
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // citesc datele unui nou angajat de la tastatura
        System.out.println("Introduceti datele angajatului:");
        System.out.print("Nume: "); String nume = scn.next();
        System.out.print("Prenume: "); String prenume = scn.next();
        System.out.print("Data nastere (dd/mm/yyyy): "); String dataNastere = scn.next();
        System.out.print("Data angajare (dd/mm/yyyy): "); String dataAngajare = scn.next();
        System.out.print("Pozitia ocupata (director, mecanic, asistent): "); String pozitie = scn.next().toLowerCase();

        while(!pozitie.equals("director") && !pozitie.equals("mecanic") && !pozitie.equals("asistent")) {
            System.out.println(pozitie + " nu este valabil, alegeti una dintre cele 3 optiuni");
            System.out.println("Pozitia ocupata (director, mecanic, asistent): "); pozitie = scn.next().toLowerCase();
        }

        // creez noul angajat
        Angajat angNou = null;
        do {
            try {
                switch (pozitie) {
                    case "director":
                        angNou = new Director(nume, prenume, dataNastere, dataAngajare);
                        break;

                    case "mecanic":
                        angNou = new Mecanic(nume, prenume, dataNastere, dataAngajare);
                        break;

                    case "asistent":
                        angNou = new Asistent(nume, prenume, dataNastere, dataAngajare);
                        break;

                    default:
                        System.out.println("Eroare la introducerea pozitiei.");
                }

                System.out.println("Angajat nou:");
                System.out.println(angNou.toString() + "\n");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Incercati din nou.");
            }
        } while(angNou == null);

        System.out.print("\033[H\033[2J");
        System.out.flush();
        return angNou;
    }

    public Angajat modificaAngajat(List<Angajat> listaAngajati) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Alegeti ID-ul angajatului ce trebuie modificat: ");
        afiseazaAngajati(listaAngajati);

        Angajat temp = cautaAngajatDupaID(listaAngajati);

        System.out.println("Ce modificati? (nume sau prenume)"); String opt = scn.next();
        while(!opt.equals("nume") && !opt.equals("prenume")) {
            System.out.println("va rog alegeti una dintre cele 2 optiuni");
            System.out.println("Ce modificati? (nume sau prenume)"); opt = scn.next();
        }
        switch (opt) {
            case "nume" :
                System.out.print("Introduceti nume nou: "); String numeNou = scn.next();
                while(numeNou == "" || numeNou == null || numeNou == "\n") {
                    System.out.println("Introduceti un nume valid");
                    System.out.print("Introduceti nume nou: "); numeNou = scn.next();
                }
                temp.setNume(numeNou);
                System.out.println("Angajatul modificat: ");
                System.out.println(temp.toString());
                break;

            case "prenume" :
                System.out.print("Introduceti prenume nou: "); String prenumeNou = scn.next();
                while(prenumeNou == "" || prenumeNou == null || prenumeNou == "\n") {
                    System.out.println("Introduceti un nume valid");
                    System.out.print("Introduceti nume nou: "); prenumeNou = scn.next();
                }
                temp.setNume(prenumeNou);
                System.out.println("Angajatul modificat: ");
                System.out.println(temp.toString());
                break;

            default:
                System.out.println("Eroare la alegerea campului de modificat.");
        }

        // dupa ce am facut modifcarea, returnez angajatul modificat
        return temp;
    }

    public List<Angajat> stergeAngajat(List<Angajat> listaAngajati) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Lista cu angajati: ");
        afiseazaAngajati(listaAngajati);

        // caut angajat dupa ID si il sterg
        Angajat temp = cautaAngajatDupaID(listaAngajati);
        System.out.println("Va fi sters: ");
        System.out.println(temp.toString());
        listaAngajati.remove(temp);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        //returnez lista cu angajatul sters
        return listaAngajati;
    }

    public Integer calculeazaSalariu(List<Angajat> listaAngajati) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Lista cu angajati: ");
        afiseazaAngajati(listaAngajati);

        Angajat temp = cautaAngajatDupaID(listaAngajati);
        Integer salariu = temp.calculeazaSalariu();

        System.out.println("Angajatul " + temp.getNume() + " " + temp.getPrenume() +
                " are salariu " + salariu);

        return salariu;
    }

}
