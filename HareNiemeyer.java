package wybory;

import java.util.Random;
import java.util.Vector;

public class HareNiemeyer extends Wybory {

    public HareNiemeyer(int liczbaOkregow, int liczbaPartii, int liczbaDzialan,
                       int liczbaCech, boolean[] scalOkreg, String[] nazwyPartii,
                       int[] budzetyPartii, char[] strategiePartii, int[] liczbyWyborcow,
                       Kandydat[][][] kandydaci, Wyborca[][] wyborcy, int[][] dzialania) {
        super(liczbaOkregow, liczbaPartii, liczbaDzialan, liczbaCech, scalOkreg, nazwyPartii,
                budzetyPartii, strategiePartii, liczbyWyborcow, kandydaci, wyborcy, dzialania);
    }

    @Override
    public void rozdajMandaty() {
        for (int i = 1; i <= this.liczbaOkregow; ++i) {
            int[] glosyNaPartie = new int[this.liczbaPartii + 1];
            float[] mandaty = new float[this.liczbaPartii + 1];
            int mandatyDoObsadzenia = this.liczbyWyborcow[i] / 10;

            for (int j = 1; j <= this.liczbyWyborcow[i]; ++j) {
                ++glosyNaPartie[glosy[i][j].dajNumerPartii()];
            }

            for (int j = 1; j <= this.liczbaPartii; ++j) {
                mandaty[j] = (float)glosyNaPartie[j] / 10;
                mandatyDoObsadzenia -= Math.floor(mandaty[j]);
                this.liczbaMandatow[i][j] += Math.floor(mandaty[j]);
                mandaty[j] -= Math.floor(mandaty[j]);
            }

            for (int j = 1; j <= mandatyDoObsadzenia; ++j) {
                float najwiekszaWartosc = -1;
                Vector numerPartii = new Vector();

                for (int k = 1; k <= this.liczbaPartii; ++k) {
                    if (mandaty[j] > najwiekszaWartosc) {
                        najwiekszaWartosc = mandaty[j];
                        numerPartii.clear();
                        numerPartii.add(k);
                    } else if (mandaty[j] == najwiekszaWartosc) {
                        numerPartii.add(k);
                    }
                }

                Random R = new Random();
                int losowaLiczba = R.nextInt(numerPartii.size());
                int ind = (int)numerPartii.get(losowaLiczba);

                ++this.liczbaMandatow[i][ind];
                mandaty[ind] = 0;
            }
        }
    }

    @Override
    protected void metoda() {
        System.out.println("Metoda Hare’a-Niemeyera");
    }
}
