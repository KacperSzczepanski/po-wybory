package wybory;

public abstract class Wybory {
    protected int liczbaOkregow, liczbaPartii, liczbaDzialan, liczbaCech;
    protected boolean[] scalOkreg;
    protected String[] nazwyPartii;
    protected int[] budzeytPartii;
    protected char[] strategiePartii;
    protected int[] liczbyWyborcow;
    protected Kandydat[][][] kandydaci;
    protected Wyborca[][] wyborcy;
    protected Kandydat[][] glosy;
    protected int[][] liczbaMandatow;
    protected int[][] dzialania;

    public Wybory(int liczbaOkregow, int liczbaPartii, int liczbaDzialan,
                    int liczbaCech, boolean[] scalOkreg, String[] nazwyPartii,
                    int[] budzetyPartii, char[] strategiePartii, int[] liczbyWyborcow,
                    Kandydat[][][] kandydaci, Wyborca[][] wyborcy, int[][] dzialania) {
        this.liczbaOkregow = liczbaOkregow;
        this.liczbaPartii = liczbaPartii;
        this.liczbaDzialan = liczbaDzialan;
        this.liczbaCech = liczbaCech;
        this.scalOkreg = scalOkreg;
        this.nazwyPartii = nazwyPartii;
        this.budzeytPartii = budzetyPartii;
        this.strategiePartii = strategiePartii;
        this.liczbyWyborcow = liczbyWyborcow;
        this.kandydaci = kandydaci;
        this.wyborcy = wyborcy;
        this.dzialania = dzialania;

        this.glosy = new Kandydat[this.liczbaOkregow + 1][2020];
        this.liczbaMandatow = new int[liczbaOkregow + 1][liczbaPartii + 1];
    }

    public void przeprowadzKampanie() {
        Strategia[] strategie = new Strategia[this.liczbaPartii + 1];
        for (int i = 1; i <= this.liczbaPartii; ++i) {
            if (strategiePartii[i] == 'R') {
                strategie[i] = new Rozmach(liczbaOkregow, liczbaDzialan, liczbaCech, budzeytPartii[i],
                        i, scalOkreg, liczbyWyborcow, wyborcy, dzialania, kandydaci);
            } else if (strategiePartii[i] == 'S') {
                strategie[i] = new Skromnie(liczbaOkregow, liczbaDzialan, liczbaCech, budzeytPartii[i],
                        i, scalOkreg, liczbyWyborcow, wyborcy, dzialania, kandydaci);
            } else if (strategiePartii[i] == 'Z') {
                strategie[i] = new Zachlannie(liczbaOkregow, liczbaDzialan, liczbaCech, budzeytPartii[i],
                        i, scalOkreg, liczbyWyborcow, wyborcy, dzialania, kandydaci);
            } else if (strategiePartii[i] == 'W') {
                strategie[i] = new Wlasna(liczbaOkregow, liczbaDzialan, liczbaCech, budzeytPartii[i],
                        i, scalOkreg, liczbyWyborcow, wyborcy, dzialania, kandydaci);
            }
        }

        int liczbaWykonanychDzialan = -1;
        while (liczbaWykonanychDzialan != 0) {
            liczbaWykonanychDzialan = 0;

            for (int i = 1; i <= this.liczbaPartii; ++i) {
                boolean ok = strategie[i].wykonajDzialanie();

                if (ok) {
                    ++liczbaWykonanychDzialan;
                }
            }
        }
    }

    public void przeprowadzWybory() {
        for (int i = 1; i <= this.liczbaOkregow; ++i) {
            for (int j = 1; j <= this.liczbyWyborcow[i]; ++j) {
                this.glosy[i][j] = this.wyborcy[i][j].zaglosuj(kandydaci[i]);
                this.glosy[i][j].dostalGlos();
            }
        }
    }

    public abstract void rozdajMandaty();

    public void wypiszWynikiWyborow() {
        this.metoda();

        for (int i = 1; i <= this.liczbaOkregow; ++i) {
            if (this.scalOkreg[i]) {
                continue;
            } else if (i < this.liczbaOkregow && this.scalOkreg[i + 1]) {
                System.out.println("Numery okregow: " + i + " " + (i + 1));
            } else {
                System.out.println("Numer okregu: " + i);
            }

            for (int j = 1; j <= this.liczbyWyborcow[i]; ++j) {
                System.out.println(this.wyborcy[i][j].dajPelneImie() + " zaglosowal(a) na " +
                        this.glosy[i][j].dajPelneImie());
            }

            for (int j = 1; j <= this.liczbaPartii; ++j) {
                for (int k = 1; k <= this.liczbyWyborcow[i] / 10; ++k) {
                    System.out.println(this.kandydaci[i][j][k].dajPelneImie() + " (" +
                            this.nazwyPartii[j] + ", " + k + ") dostal(a) " +
                            this.kandydaci[i][j][k].ileGlosow() + " glosow");

                    this.kandydaci[i][j][k].wyzerujGlosy();
                }
            }

            for (int j = 1; j <= this.liczbaPartii; ++j) {
                System.out.println("Partia " + this.nazwyPartii[j] + " otrzymala "
                        + this.liczbaMandatow[i][j] + " madnatow");
            }
        }

        for (int i = 1; i <= this.liczbaPartii; ++i) {
            int lacznaLiczbaMandatow = 0;

            for (int j = 1; j <= this.liczbaOkregow; ++j) {
                lacznaLiczbaMandatow += this.liczbaMandatow[j][i];
            }

            System.out.println("Partia " + this.nazwyPartii[i] + " otrzymala lacznie " +
                    lacznaLiczbaMandatow + " mandatow");
        }

        System.out.println();
    }

    protected abstract void metoda();
}
