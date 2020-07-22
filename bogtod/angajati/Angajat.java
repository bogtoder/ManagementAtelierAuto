package bogtod.angajati;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Angajat {

    private Integer ID;
    private String nume;
    private String prenume;
    private Date dataNastere;
    private Date dataAngajare;
    private Double coeficientAngajat;

    public Angajat(Integer ID, String nume, String prenume, String dataNastere,
                   String dataAngajare, Double coeficientAngajat) {

        // validari pentru nume si prenume, trebuie sa fie string-uri nenule
        // si cu lungime <= 30
        if(nume == "" || nume == null) {
            throw new IllegalArgumentException("Nume nu poate fi gol.");
        }
        else if (nume.length() > 30) {
            throw new IllegalArgumentException("Nume nu poate fi mai lung de 30 caractere.");
        }
        if(prenume == "" || prenume == null) {
            throw new IllegalArgumentException("Prenume nu poate fi gol.");
        }
        else if (prenume.length() > 30) {
            throw new IllegalArgumentException("Prenume nu poate fi mai lung de 30 caractere.");
        }

        // primesc datele de nastere si angajare in format string
        // trebuie convertite in Date
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date angTemp = null, nasTemp = null;
        try {
            nasTemp = sdf.parse(dataNastere);
            angTemp = sdf.parse(dataAngajare);
            if(angTemp.after(currentDate)) {
                // hire date is after current date
                throw new IllegalArgumentException("Data angajare nu poate fi in viitor.");
            }
            if(aniBetweenDates(angTemp, nasTemp) < 18) {
                throw new IllegalArgumentException("Varsta nu paote fi mai mica de 18 ani.");
            }
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        this.ID = ID;
        this.nume = nume;
        this.prenume = prenume;
        this.dataNastere = nasTemp;
        this.dataAngajare = angTemp;
        this.coeficientAngajat = coeficientAngajat;
    }

    private Long aniBetweenDates(Date date1, Date date2) {
        Long diff = Math.abs(date1.getTime() - date2.getTime());
        // milisecunde de la date1 pana la date2

        Long secunde = diff / 1000;
        Long ore = secunde / 3600;
        Long zile = ore / 24;

        return zile / 365;
    }

    private Integer calculeazaSalariu() {
        Integer aniVechime = aniBetweenDates(this.dataAngajare, new Date())
                                .intValue();

        Double salariu = aniVechime * this.coeficientAngajat * 1000;

        return salariu.intValue();
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getDataNastere() {
        return dataNastere.toString();
    }

    public String getDataAngajare() {
        return dataAngajare.toString();
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setDataNastere(Date dataNastere) {
        this.dataNastere = dataNastere;
    }

    public void setDataAngajare(Date dataAngajare) {
        this.dataAngajare = dataAngajare;
    }
}
