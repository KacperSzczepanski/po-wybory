package wybory;

public class WyborcaTyp2 extends Wyborca {
    private int numerPartii;
    private final int numerNaLiscie;

    public WyborcaTyp2(String imie, String nazwisko, int numerOkregu, int numerNaLiscie,
                       String nazwaPartii, String[] nazwyPartii) {
        super(imie, nazwisko, numerOkregu);
        this.numerNaLiscie = numerNaLiscie;

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
        return kandydaci[this.numerPartii][this.numerNaLiscie];
    }

    @Override
    public int dajTypWyborcy() {
        return 2;
    }
}
