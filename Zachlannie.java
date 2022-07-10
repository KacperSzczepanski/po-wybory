package wybory;

import java.util.Random;
import java.util.Vector;

public class Zachlannie extends Strategia {

    public Zachlannie(int liczbaOkregow, int liczbaDzialan, int liczbaCech, int budzet,
                      int numerPartii, boolean[] scalOkreg, int[] liczbyWyborcow,
                      Wyborca[][] wyborcy, int[][] dzialania, Kandydat[][][] kandydaci) {
        super(liczbaOkregow, liczbaDzialan, liczbaCech, budzet, numerPartii,
                scalOkreg, liczbyWyborcow, wyborcy, dzialania, kandydaci);
    }

    @Override
    public boolean wykonajDzialanie() {
        int zmianaDzialaniem = -1000000000;
        long kosztDzialania = 0;
        int[] sumySum = new int[this.liczbaOkregow + 1];
        Vector numerOkregu = new Vector();
        Vector numerDzialania = new Vector();

        for (int i = 1; i <= this.liczbaOkregow; ++i) {
            for (int j = 1; j <= this.liczbyWyborcow[i]; ++j) {
                int typ = this.wyborcy[i][j].dajTypWyborcy();

                if (typ != 5 && typ != 8) {
                    continue;
                }

                int[] wagi = this.wyborcy[i][j].dajWagi();

                for (int k = 1; k <= this.liczbyWyborcow[i] / 10; ++k) {
                    int[] cechy = this.kandydaci[i][this.numerPartii][k].dajCechy();

                    for (int l = 1; l <= this.liczbaCech; ++l) {
                        sumySum[i] += wagi[l] * cechy[l];
                    }
                }
            }
        }

        for (int i = 1; i <= this.liczbaOkregow; ++i) {
            for (int d = 1; d <= this.liczbaDzialan; ++d) {
                int zmiana = 0;
                long koszt = 0;

                for (int j = 1; j <= this.liczbyWyborcow[i]; ++j) {
                    int typ = this.wyborcy[i][j].dajTypWyborcy();

                    if (typ != 5 && typ != 8) {
                        continue;
                    }

                    int[] wagi = this.wyborcy[i][j].dajWagi();

                    for (int k = 1; k <= this.liczbyWyborcow[i] / 10; ++k) {
                        int cechy[] = this.kandydaci[i][this.numerPartii][k].dajCechy();

                        for (int l = 1; l <= this.liczbaCech; ++l) {
                            zmiana += (wagi[l] + this.dzialania[d][l]) * cechy[l];
                            koszt += Math.abs(this.dzialania[d][l]);
                        }
                    }
                }

                koszt *= this.liczbyWyborcow[i];
                zmiana -= sumySum[i];

                if ((zmiana > zmianaDzialaniem || (zmiana == zmianaDzialaniem && koszt < kosztDzialania))
                    && koszt <= this.budzet) {
                    zmianaDzialaniem = zmiana;
                    kosztDzialania = koszt;
                    numerOkregu.clear();
                    numerDzialania.clear();
                    numerOkregu.add(i);
                    numerDzialania.add(d);
                } else if (zmiana == zmianaDzialaniem && koszt == kosztDzialania && koszt <= this.budzet) {
                    numerOkregu.add(i);
                    numerDzialania.add(d);
                }
            }
        }

        if (zmianaDzialaniem == -1000000000) {
            return false;
        }

        Random R = new Random();
        int losowaLiczba = R.nextInt(numerOkregu.size());

        int nrOkregu = (int)numerOkregu.get(losowaLiczba);
        int nrDzialania = (int)numerDzialania.get(losowaLiczba);

        for (int i = 1; i <= this.liczbyWyborcow[nrOkregu]; ++i) {
            this.wyborcy[nrOkregu][i].zmienWagi(this.dzialania[nrDzialania]);
        }

        this.budzet -= kosztDzialania;

        return true;
    }
}
