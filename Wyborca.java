package wybory;

public abstract class Wyborca {
    protected String pelneImie;
    protected int numerOkregu;
        
    public Wyborca(String imie, String nazwisko, int numerOkregu) {
        this.pelneImie = imie + " " + nazwisko;
        this.numerOkregu = numerOkregu;
    }

    public String dajPelneImie() {
        return this.pelneImie;
    }

    public abstract Kandydat zaglosuj(Kandydat[][] kandydaci);

    public abstract int dajTypWyborcy();

    public abstract void zmienWagi(int[] wektor);

    public abstract int[] dajWagi();
}
