package wybory;

import java.util.Random;
import java.util.Vector;

public class WyborcaTyp3 extends Wyborca {
    private final int numerCechy;

    public WyborcaTyp3(String imie, String nazwisko, int numerOkregu, int numerCechy) {
        super(imie, nazwisko, numerOkregu);
        this.numerCechy = numerCechy;
    }

    @Override
    public int[] dajWagi() {
        return new int[1];
    }

    @Override
    public void zmienWagi(int[] wektor) {}

    @Override
    public Kandydat zaglosuj(Kandydat[][] kandydaci) {
        int liczbaPartii = kandydaci.length;

        int najmniejszaWartosc = 1000000000;
        Vector numerPartii = new Vector();
        Vector numerNaLiscie = new Vector();

        for (int i = 1; i < liczbaPartii; ++i) {
            int dlugoscListy = kandydaci[i].length;

            for (int j = 1; j < dlugoscListy; ++j) {
                int[] cechy = kandydaci[i][j].dajCechy();

                if (cechy[numerCechy] < najmniejszaWartosc) {
                    najmniejszaWartosc = cechy[numerCechy];
                    numerPartii.clear();
                    numerNaLiscie.clear();
                    numerPartii.add(i);
                    numerNaLiscie.add(j);
                } else if (cechy[numerCechy] == najmniejszaWartosc) {
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
        return 3;
    }
}
