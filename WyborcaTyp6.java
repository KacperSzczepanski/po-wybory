package wybory;

import java.util.Random;
import java.util.Vector;

public class WyborcaTyp6 extends Wyborca {
    private int numerPartii;
    private final int numerCechy;

    public WyborcaTyp6(String imie, String nazwisko, int numerOkregu,
                       int numerCechy, String nazwaPartii, String[] nazwyPartii) {
        super(imie, nazwisko, numerOkregu);
        this.numerCechy = numerCechy;

        for (int i = 1; i < nazwyPartii.length; ++i) {
            if (nazwaPartii.equals(nazwyPartii[i])) {
                this.numerPartii = i;
                break;
            }
        }
    }

    @Override
    public int[] dajWagi() {
        return new int[1];
    }

    @Override
    public void zmienWagi(int[] wektor) {}

    @Override
    public Kandydat zaglosuj(Kandydat[][] kandydaci) {
        int dlugoscListy = kandydaci[this.numerPartii].length;

        int najmniejszaWartosc = 1000000000;
        Vector numerNaLiscie = new Vector();

        for (int i = 1; i < dlugoscListy; ++i) {
            int[] cechy = kandydaci[this.numerPartii][i].dajCechy();

            if (cechy[this.numerCechy] < najmniejszaWartosc) {
                najmniejszaWartosc = cechy[this.numerCechy];
                numerNaLiscie.clear();
                numerNaLiscie.add(i);
            } else if (cechy[this.numerCechy] == najmniejszaWartosc) {
                numerNaLiscie.add(i);
            }
        }

        Random R = new Random();
        int losowaLiczba = R.nextInt(numerNaLiscie.size());

        return kandydaci[this.numerPartii][(int)numerNaLiscie.get(losowaLiczba)];
    }

    @Override
    public int dajTypWyborcy() {
        return 6;
    }
}
