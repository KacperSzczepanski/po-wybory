package wybory;

import java.util.Random;
import java.util.Vector;

public class WyborcaTyp8 extends Wyborca {
    private final int[] wagiCech;
    private int numerPartii;

    public WyborcaTyp8(String imie, String nazwisko, int numerOkregu,
                       int[] wagiCech, String nazwaPartii, String[] nazwyPartii) {
        super(imie, nazwisko, numerOkregu);
        this.wagiCech = wagiCech;

        for (int i = 1; i < nazwyPartii.length; ++i) {
            if (nazwaPartii.equals(nazwyPartii[i])) {
                this.numerPartii = i;
                break;
            }
        }
    }

    @Override
    public int[] dajWagi() {
        return this.wagiCech;
    }

    @Override
    public void zmienWagi(int[] wektor) {
        for (int i = 1; i < this.wagiCech.length; ++i) {
            this.wagiCech[i] += wektor[i];

            if (this.wagiCech[i] > 100) {
                this.wagiCech[i] = 100;
            }
            if (this.wagiCech[i] < -100) {
                this.wagiCech[i] = -100;
            }
        }
    }

    @Override
    public Kandydat zaglosuj(Kandydat[][] kandydaci) {
        int dlugoscListy = kandydaci[this.numerPartii].length;

        int najwiekszaWartosc = -1000000000;
        Vector numerNaLiscie = new Vector();

        for (int i = 1; i < dlugoscListy; ++i) {
            int[] cechy = kandydaci[this.numerPartii][i].dajCechy();
            int suma = 0;

            for (int k = 1; k < cechy.length; ++k) {
                suma += this.wagiCech[k] * cechy[k];
            }

            if (suma > najwiekszaWartosc) {
                najwiekszaWartosc = suma;
                numerNaLiscie.clear();
                numerNaLiscie.add(i);
            } else if (suma == najwiekszaWartosc) {
                numerNaLiscie.add(i);
            }
        }

        Random R = new Random();
        int losowaLiczba = R.nextInt(numerNaLiscie.size());

        return kandydaci[this.numerPartii][(int)numerNaLiscie.get(losowaLiczba)];
    }

    @Override
    public int dajTypWyborcy() {
        return 8;
    }
}
