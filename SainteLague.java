package wybory;

import java.util.Random;
import java.util.Vector;

public class SainteLague extends Wybory {

    public SainteLague(int liczbaOkregow, int liczbaPartii, int liczbaDzialan,
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

            for (int j = 1; j <= this.liczbyWyborcow[i]; ++j) {
                ++glosyNaPartie[glosy[i][j].dajNumerPartii()];
            }

            int[] kopia = glosyNaPartie;

            for (int mandaty = 0; mandaty < this.liczbyWyborcow[i] / 10; ++mandaty) {
                int najwiecejGlosow = -1000000000;
                Vector numerPartii = new Vector();

                for (int j = 1; j <= liczbaPartii; ++j) {
                    if (glosyNaPartie[j] > najwiecejGlosow) {
                        najwiecejGlosow = glosyNaPartie[j];
                        numerPartii.clear();
                        numerPartii.add(j);
                    } else if (glosyNaPartie[j] == najwiecejGlosow) {
                        numerPartii.add(j);
                    }
                }

                Random R = new Random();
                int losowaLiczba = R.nextInt(numerPartii.size());
                int ind = (int)numerPartii.get(losowaLiczba);

                ++liczbaMandatow[i][ind];
                glosyNaPartie[ind] = kopia[ind] / (2 * liczbaMandatow[i][ind] + 1);
            }
        }
    }

    @Override
    protected void metoda() {
        System.out.println("Metoda Sainte-Laguë");
    }
}
