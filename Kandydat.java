package wybory;

public class Kandydat {
    private final String pelneImie;
    private final int[] cechy;
    private int liczbaGlosow;
    private final int numerPartii;

    public Kandydat(String imie, String nazwisko, int[] cechy, int numerPartii) {
        this.pelneImie = imie + " " + nazwisko;
        this.cechy = cechy;
        this.numerPartii = numerPartii;

        this.liczbaGlosow = 0;
    }

    public String dajPelneImie() {
        return this.pelneImie;
    }

    public int dajNumerPartii() {
        return this.numerPartii;
    }

    public int[] dajCechy() {
        return this.cechy;
    }

    public void dostalGlos() {
        ++this.liczbaGlosow;
    }

    public int ileGlosow() {
        return this.liczbaGlosow;
    }

    public void wyzerujGlosy() {
        this.liczbaGlosow = 0;
    }
}
