package bogtod.programari;

import bogtod.masini.Masina;

public class Programare {

    // fiecare angajat are programari in coada de activitate
    // adica o masina, cu timpul necesar de reparatie, si tipul de masina

    private Masina masina;
    private Integer tip; // 1-standard, 2-camion, 3-autobuz
    private Integer timpNecesarLucrare; // este completat automat de catre angajat

    public Programare(Masina masina, Integer tip) {
        this.masina = masina;
        this.tip = tip;
    }

    public Masina getMasina() {
        return masina;
    }

    public Integer getTip() {
        return tip;
    }

    public Integer getTimpNecesarLucrare() {
        return timpNecesarLucrare;
    }

    public void estimeazaDurata() {
        this.timpNecesarLucrare = (int) Math.ceil(Math.random() * 5);
    }

    public void updateDurata() {
        this.timpNecesarLucrare--;
    }
}
