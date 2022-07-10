package wybory;

import java.util.Random;
import java.util.Vector;

public class Skromnie extends Strategia {

    public Skromnie(int liczbaOkregow, int liczbaDzialan, int liczbaCech, int budzet,
                    int numerPartii, boolean[] scalOkreg, int[] liczbyWyborcow,
                    Wyborca[][] wyborcy, int[][] dzialania, Kandydat[][][] kandydaci) {
        super(liczbaOkregow, liczbaDzialan, liczbaCech, budzet, numerPartii,
                scalOkreg, liczbyWyborcow, wyborcy, dzialania, kandydaci);
    }

    @Override
    public boolean wykonajDzialanie() {
        long kosztDzialania = 1000000000000L;
        Vector numerDzialania = new Vector();
        Vector numerOkregu = new Vector();

        for (int i = 1; i <= this.liczbaOkregow; ++i) {
            for (int j = 1; j <= this.liczbaDzialan; ++j) {
                long koszt = 0L;

                for (int k = 1; k <= this.liczbaCech; ++k) {
                    koszt += Math.abs(this.dzialania[j][k]);
                }

                koszt *= this.liczbyWyborcow[i];

                if (koszt < kosztDzialania && koszt <= this.budzet && koszt > 0) {
                    kosztDzialania = koszt;
                    numerDzialania.clear();
                    numerOkregu.clear();
                    numerDzialania.add(j);
                    numerOkregu.add(i);
                } else if (koszt == kosztDzialania && koszt <= this.budzet && koszt > 0) {
                    numerDzialania.add(j);
                    numerOkregu.add(i);
                }
            }
        }

        if (kosztDzialania == 1000000000000L) {
            return false;
        }

        Random R = new Random();
        int losowaLiczba = R.nextInt(numerDzialania.size());
        int nrDzialania = (int)numerDzialania.get(losowaLiczba);
        int nrOkregu = (int)numerOkregu.get(losowaLiczba);

        for (int i = 1; i <= this.liczbyWyborcow[nrOkregu]; ++i) {
            this.wyborcy[nrOkregu][i].zmienWagi(this.dzialania[nrDzialania]);
        }

        this.budzet -= kosztDzialania;

        return true;
    }
}
