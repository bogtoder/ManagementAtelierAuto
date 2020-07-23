package bogtod.masini;

import bogtod.programari.Programare;

import java.util.List;
import java.util.Scanner;

public class MasiniApp {

    // clasa de serviciu pt a lucra cu masinile

    public static MasiniApp instance = null;
    private Scanner scn;

    private MasiniApp() {
        scn = new Scanner(System.in);
    }

    public static MasiniApp getInstance() {
        if(instance == null) {
            instance = new MasiniApp();
        }
        return instance;
    }

    public Masina cautaMasinaDupaID(List<Masina> listaMasini) {
        Masina temp = null;
        while(temp == null) {
            System.out.print("ID ales: "); Integer ID = scn.nextInt();
            for (Masina a : listaMasini) {
                if (a.getID() == ID) {
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

    public void afiseazaMasini(List<Masina> listaMasini) {
        System.out.println("Exista urmatorii angajati: ");
        for(Masina a : listaMasini) {
            System.out.println(a.toString());
        }
    }

    public Programare inregistreazaMasina() {
        // inregistrez o masina si creez o programare

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Introduceti datele masinii: ");
        System.out.print("Nr. kilometri: ");
        Integer nrKilometri = scn.nextInt();
        System.out.print("An fabricatie: ");
        Integer anFabricatie = scn.nextInt();

        System.out.print("diesel/benzina: ");
        String dieselBenzina = scn.next().toLowerCase();
        while (!dieselBenzina.equals("diesel") && !dieselBenzina.equals("benzina")) {
            System.out.println("Algeti una dintre cele 2 variante.");
            System.out.print("diesel/benzina: ");
            dieselBenzina = scn.next().toLowerCase();
        }

        System.out.println("Tipul de vehicul (standard, autobuz, camion): ");
        String tip = scn.next().toLowerCase();
        while (!tip.equals("standard") && !tip.equals("autobuz") && !tip.equals("camion")) {
            System.out.println("Algeti una dintre cele 3 variante.");
            System.out.print("Tipul de vehicul (standard, autobuz, camion): ");
            tip = scn.next().toLowerCase();
        }

        Masina masNoua = null;
        Integer tipMasina = 0;
        do {
            try {
                switch (tip) {
                    case "standard":
                        System.out.println("Tipul de transmisie (automata, manuala): ");
                        String trans = scn.next().toLowerCase();
                        while (!trans.equals("automata") && !trans.equals("manuala")) {
                            System.out.println("Algeti una dintre variante.");
                            System.out.println("Tipul de transmisie (automata, manuala): ");
                            trans = scn.next().toLowerCase();
                        }
                        masNoua = new MasinaStandard(nrKilometri, anFabricatie, dieselBenzina.equals("diesel"), trans);
                        tipMasina = 1;
                        // folosesc dieselBenzina.equals("diesel") pt ca in obiect memorez boolean in functie de combustibil
                        break;

                    case "camion":
                        System.out.println("Tonaj: ");
                        Integer tonaj = scn.nextInt();
                        masNoua = new Camion(nrKilometri, anFabricatie, dieselBenzina.equals("diesel"), tonaj);
                        tipMasina = 2;
                        break;

                    case "autobuz":
                        System.out.println("Nr locuri: ");
                        Integer nrLocuri = scn.nextInt();
                        masNoua = new Autobuz(nrKilometri, anFabricatie, dieselBenzina.equals("diesel"), nrLocuri);
                        tipMasina = 3;
                        break;

                    default:
                        System.out.println("Eroare la alegerea tipului de vehicul");
                }

                System.out.println("Am introdus masina cu datele: " + masNoua.toString());

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Incercati din nou");
            }
        }while(masNoua == null);


        // returnez masina cu toate datele
        return new Programare(masNoua, tipMasina);
    }

    public void afiseazaPolitaAsigurarePtID(List<Masina> listaMasini) {
        System.out.println("Masinile inregistrate pana acum:");
        afiseazaMasini(listaMasini);

        Masina temp = cautaMasinaDupaID(listaMasini);

        System.out.println("Masina " + temp.toString() + " are polita de asigurare in valoare de "
                + temp.calculeazaAsigurare(false) + " (" + temp.calculeazaAsigurare(true) + " cu discount)");
    }

}
