package wybory;

import java.util.Random;
import java.util.Vector;

public class WyborcaTyp5 extends Wyborca {
    private final int[] wagiCech;

    public WyborcaTyp5(String imie, String nazwisko, int numerOkregu, int[] wagiCech) {
        super(imie, nazwisko, numerOkregu);
        this.wagiCech = wagiCech;
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
        int liczbaPartii = kandydaci.length;

        int najwiekszaWartosc = -1000000000;
        Vector numerPartii = new Vector();
        Vector numerNaLiscie = new Vector();

        for (int i = 1; i < liczbaPartii; ++i) {
            int dlugoscListy = kandydaci[i].length;

            for (int j = 1; j < dlugoscListy; ++j) {
                int[] cechy = kandydaci[i][j].dajCechy();
                int suma = 0;

                for (int k = 1; k < cechy.length; ++k) {
                    suma += this.wagiCech[k] * cechy[k];
                }

                if (suma > najwiekszaWartosc) {
                    najwiekszaWartosc = suma;
                    numerPartii.clear();
                    numerNaLiscie.clear();
                    numerPartii.add(i);
                    numerNaLiscie.add(j);
                } else if (suma == najwiekszaWartosc) {
                    numerPartii.add(i);
                    numerNaLiscie.add(j);
                }
            }
        }

        Random R = new Random();
        int losowaLiczba = R.nextInt(numerPartii.size());

        return kandydaci[(int)numerPartii.get(losowaLiczba)][(int)numerNaLiscie.get(losowaLiczba)];
    }

    @Override
    public int dajTypWyborcy() {
        return 5;
    }
}
