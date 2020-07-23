package bogtod;

import bogtod.angajati.*;
import bogtod.masini.*;
import bogtod.programari.Programare;
import bogtod.programari.ProgramareSpeciala;

import java.util.*;

public class Atelier {

    // fiecare atelier are o lista de angajati
    private List<Angajat> listaAngajati;

    // fiecare angajat are masinile proprii
    // reprezinta programul atelierului
    private Map<Angajat, Queue<Programare>> programAtelier;

    private List<Programare> listaAsteptare;
    private List<ProgramareSpeciala> listAsteptareSpeciala;

    private List<Masina> toateMasinile;

    private Boolean esteDeschis;

    private Scanner scn;

    public Atelier() {
        this.listaAngajati = new LinkedList<>();
        this.programAtelier = new HashMap<>();
        this.listaAsteptare = new LinkedList<>();
        this.listAsteptareSpeciala = new LinkedList<>();
        this.toateMasinile = new LinkedList<>();

        this.esteDeschis = false;
        this.scn = new Scanner(System.in);
    }

    public List<Angajat> getListaAngajati() {
        return listaAngajati;
    }

    public Map<Angajat, Queue<Programare>> getProgramAtelier() {
        return programAtelier;
    }

    public Boolean getEsteDeschis() {
        return esteDeschis;
    }

    public void adaugaAngajati() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print("Cati angajati doriti sa introduceti: "); Integer nrAng = scn.nextInt();

        while(nrAng <= 0) {
            System.out.print("Trebuie un numar mai mare decat 0.");
            System.out.print("Cati angajati doriti sa introduceti: "); nrAng = scn.nextInt();
        }

       for(int i = 0; i < nrAng; ++i) {
           Angajat temp = AngajatiApp.getInstance().inregistreazaAngajat();
           this.listaAngajati.add(temp);
           this.programAtelier.put(temp, new LinkedList<>());
       }


        // la orice adaugare de angajati, atelierul se poate deschide
        this.esteDeschis = true;
    }

    public void afiseazaAngajati() {
        AngajatiApp.getInstance().afiseazaAngajati(listaAngajati);
    }

    public void adaugaAngajati(Angajat angManual) {
        // functie folosita la test

        this.listaAngajati.add(angManual);
        this.programAtelier.put(angManual, new LinkedList<>());

        // la orice adaugare de angajati, atelierul se poate deschide
        this.esteDeschis = true;
    }

    public void eliminaAngajat() {
        if(listaAngajati.isEmpty()) {
            System.out.println("Atelierul este gol.");
            return;
        }
        AngajatiApp.getInstance().stergeAngajat(listaAngajati);

        if(listaAngajati.isEmpty()) {
            this.esteDeschis = false; // daca au plecat toti, atelierul este inchis
        }
    }

    public void modificaAngajat() {
        if(listaAngajati.isEmpty()) {
            System.out.println("Atelierul este gol.");
            return;
        }
        AngajatiApp.getInstance().modificaAngajat(listaAngajati);
    }

    public void calculeazaSalariuAngajat() {
        if(listaAngajati.isEmpty()) {
            System.out.println("Nu exista angajati.");
            return;
        }
        AngajatiApp.getInstance().calculeazaSalariu(listaAngajati);
    }

    public void programeazaMasina() {
        if(!this.esteDeschis) {
            System.out.println("Ne pare rau, momentan este inchis.");
            return;
        }

        Programare progNoua = MasiniApp.getInstance().inregistreazaMasina();
        toateMasinile.add(progNoua.getMasina());

        System.out.println("Doriti un angajat anume? (da/nu): "); String rasp = scn.next().toLowerCase();

        while(!rasp.equals("da") && !rasp.equals("nu")) {
            System.out.println("Alegeti una dintre cele 2 variante.");
            System.out.println("Doriti un angajat anume? (da/nu): "); rasp = scn.next().toLowerCase();
        }

        if(rasp.equals("da")) {
            // se incearca alegerea unui angajat
            Angajat ales;
            System.out.println("Alegeti ID-ul:");
            AngajatiApp.getInstance().afiseazaAngajati(listaAngajati);
            ales = AngajatiApp.getInstance().cautaAngajatDupaID(listaAngajati);

            while(true) {
                // se repeta aceasta bucla pana cand clientul gaseste un angajat liber
                // sau pana cand se satura din cautat
                if (sePoateProgramaLa(ales, progNoua)) {
                    System.out.println("Angajatul este liber, sunteti programat.");
                    progNoua.estimeazaDurata(); // estimam cat va dura reparatia
                    programAtelier.get(ales).add(progNoua); // angajatul poate lua masina
                    break;
                }
                else {
                    System.out.print("Angajatul ales este ocupat, reincercati? (da/nu).");
                    String retry = scn.next().toLowerCase();

                    while (!retry.equals("da") && !retry.equals("nu")) {
                        System.out.println("Alegeti una dintre variante.");
                        System.out.println("Angajatul ales este ocupat, reincercati? (da/nu).");
                        retry = scn.next().toLowerCase();
                    }

                    if (retry.equals("nu")) {
                        break;
                    }
                }
                System.out.print("Puteti ramane in lista de asteptare pt ultimul angajat ales" +
                        ", sunteti de acord? (da/nu): ");
                String retry = scn.next().toLowerCase();
                while (!retry.equals("da") && !retry.equals("nu")) {
                    System.out.println("Alegeti una dintre variante.");
                    System.out.print("Puteti ramane in lista de asteptare pt ultimul angajat ales" +
                            ", sunteti de acord? (da/nu): ");
                    retry = scn.next().toLowerCase();
                }

                if(retry.equals("da")) {
                    // cazul in care asteapta un anumit angajat
                    listAsteptareSpeciala.add(new ProgramareSpeciala(progNoua, ales));
                }
                else {
                    System.out.println("Doriti sa fiti repartizat la primul angajat disponibil? (da/nu):");
                    String retry2 = scn.next().toLowerCase();
                    while (!retry2.equals("da") && !retry2.equals("nu")) {
                        System.out.println("Alegeti una dintre variante.");
                        System.out.println("Doriti sa fiti repartizat la primul angajat disponibil? (da/nu):");
                        retry2 = scn.next().toLowerCase();
                    }

                    if(retry2.equals("da")) {
                        // clientul intra in lista de asteptare normala
                        listaAsteptare.add(progNoua);
                    }
                    else {
                        // nu a ales nicio alta optiune, inseamna ca pleaca
                        System.out.println("Va uram o zi buna :).");
                    }
                }
            }
        }
        else {
            // clientul nu doreste un angajat specific
            System.out.println("Veti fi repartizat la primul angajat disponibil, " +
                    "urmariti ID-ul primit la inregistrarea masinii dvs.");
            listaAsteptare.add(progNoua);
        }
    }

    public void calculeazaPolitaAsigurare() {
        MasiniApp.getInstance().afiseazaPolitaAsigurarePtID(toateMasinile);
    }

    private Boolean sePoateProgramaLa(Angajat ang, Programare prog) {
        // sunt verificate restrictiile cu privire la aglomerarea unui angajat

        if (programAtelier.get(ang).size() == 5) {
            // angajatul are 3 standard si cate 1 din celelalte 2 tipuri, este plin
            return false;
        }

        Integer count = 0;
        for (Programare p : programAtelier.get(ang)) {
            if (p.getTip() == prog.getTip()) {
                count++;
            }
        }

        //caz cu 3 masini standard
        if(prog.getTip() == 1 && count == 3) return false;

        // cazuri pt autobuze si camioane
        return (prog.getTip() != 2 && prog.getTip() != 3) || count != 1;
    }

    public void updateSituatieAtelier() {
        // functie ce simuleaza "avansarea" timpului cu o unitate
        // aici se termina programari si se iau altele de catre angajati

        // se efectuaza lucrari la reparatii si vedem daca s-a eliberat ceva
        // reparatiile evolueaza secvential in ordinea cozii

        for(Angajat ang : listaAngajati) {
            // progresez la reparatiile existente
            if(!(programAtelier.get(ang).peek() == null))
                programAtelier.get(ang).peek().updateDurata();

            // elimin lucrarile care s-au terminat acum
            if(programAtelier.get(ang).peek() == null) continue;
            if(programAtelier.get(ang).peek().getTimpNecesarLucrare() == 0) {
                System.out.println("S-au finalizat reparatiile la masina cu ID-ul " +
                        programAtelier.get(ang).peek().getMasina().getID() + ", va uram o zi buna.");
                programAtelier.get(ang).remove();
            }
        }

        // mai intai sunt luate la reparatii cele din lista normala de asteptare
        for(Programare prog : listaAsteptare) {
            for(Angajat ang : listaAngajati) {
                if(sePoateProgramaLa(ang, prog)) {

                    prog.estimeazaDurata(); // estimam cat va dura reparatia

                    programAtelier.get(ang).add(prog);
                    listaAsteptare.remove(prog);

                    System.out.println("Masina cu ID " + prog.getMasina().getID() + " a fost alocata " +
                            "angajatului " + ang.getID() + "-" + ang.getNume());

                    break;
                }
            }
        }

        // apoi se verifica cele cu angajati alesi
        for(ProgramareSpeciala progSpec : listAsteptareSpeciala) {
            if(sePoateProgramaLa(progSpec.getAngajatAles(), progSpec.getProgSimpla())) {
                programAtelier.get(progSpec.getAngajatAles()).add(progSpec.getProgSimpla());

                progSpec.getProgSimpla().estimeazaDurata(); // estimam durata reparatiilor
                listAsteptareSpeciala.remove(progSpec);

                System.out.println("Masina cu ID " + progSpec.getProgSimpla().getMasina().getID() + " a fost alocata " +
                        "angajatului " + progSpec.getAngajatAles().getID() + "-" + progSpec.getAngajatAles().getNume());

                break;
            }
        }
    }

}
