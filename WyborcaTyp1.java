package wybory;

import java.util.Random;

public class WyborcaTyp1 extends Wyborca {
    private int numerPartii;

    public WyborcaTyp1(String imie, String nazwisko, int numerOkregu,
                       String nazwaPartii, String[] nazwyPartii) {
        super(imie, nazwisko, numerOkregu);

        for (int i = 1; i < nazwyPartii.length; ++i) {
            if (nazwaPartii.equals(nazwyPartii[i])) {
                this.numerPartii = i;
                break;
            }
        }
    }

    @Override
    public void zmienWagi(int[] wektor) {}

    @Override
    public int[] dajWagi() {
        return new int[1];
    }

    @Override
    public Kandydat zaglosuj(Kandydat[][] kandydaci) {
        int dlugoscListy = kandydaci[this.numerPartii].length;
        Random R = new Random();
        int losowaLiczba = R.nextInt(dlugoscListy - 1) + 1;

        return kandydaci[numerPartii][losowaLiczba];
    }

    @Override
    public int dajTypWyborcy() {
        return 1;
    }
}
