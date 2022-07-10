package wybory;

public abstract class Strategia {
    protected int liczbaOkregow, liczbaDzialan, liczbaCech, budzet, numerPartii;
    protected boolean[] scalOkreg;
    protected int[] liczbyWyborcow;
    protected Wyborca[][] wyborcy;
    protected int[][] dzialania;
    protected Kandydat[][][] kandydaci;

    public Strategia(int liczbaOkregow, int liczbaDzialan, int liczbaCech, int budzet,
                     int numerPartii, boolean[] scalOkreg, int[] liczbyWyborcow,
                     Wyborca[][] wyborcy, int[][] dzialania, Kandydat[][][] kandydaci) {
        this.liczbaOkregow = liczbaOkregow;
        this.liczbaDzialan = liczbaDzialan;
        this.liczbaCech = liczbaCech;
        this.budzet = budzet;
        this.scalOkreg = scalOkreg;
        this.liczbyWyborcow = liczbyWyborcow;
        this.wyborcy = wyborcy;
        this.dzialania = dzialania;
        this.kandydaci = kandydaci;
        this.numerPartii = numerPartii;
    }

    abstract boolean wykonajDzialanie();
}
