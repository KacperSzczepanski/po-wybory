package wybory;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private static int liczbaOkregow, liczbaPartii, liczbaDzialan, liczbaCech, liczbaScalan;
    private static boolean[] scalOkreg; //true jezeli scalam okreg i z poprzednim
    private static String[] nazwyPartii;
    private static int[] budzetyPartii;
    private static char[] strategiePartii;
    private static int[] liczbyWyborcow;
    private static Kandydat[][][] kandydaci;
    private static Wyborca[][] wyborcy;
    private static int[][] dzialania;

    public static void main (String[] args) {
        File file = new File(args[0]);
        try {
            Scanner scan = new Scanner(file);

            liczbaOkregow = scan.nextInt();
            liczbaPartii = scan.nextInt();
            liczbaDzialan = scan.nextInt();
            liczbaCech = scan.nextInt();

            scalOkreg = new boolean[liczbaOkregow + 1];
            nazwyPartii = new String[liczbaPartii + 1];
            budzetyPartii = new int[liczbaPartii + 1];
            strategiePartii = new char[liczbaPartii + 1];
            liczbyWyborcow = new int[liczbaOkregow + 1];
            kandydaci = new Kandydat[liczbaOkregow + 1][liczbaPartii + 1][205];
            wyborcy = new Wyborca[liczbaOkregow + 1][2005];
            dzialania = new int[liczbaDzialan + 1][liczbaCech + 1];

            liczbaScalan = scan.nextInt();
            for (int i = 1; i <= liczbaScalan; ++i) {
                String para = scan.next();
                String[] pom = para.split(",");
                pom[1] = pom[1].substring(0, pom[1].length() - 1);
                int okreg = Integer.parseInt(pom[1]);
                scalOkreg[okreg] = true;
            }

            for (int i = 1; i <= liczbaPartii; ++i) {
                String nazwa = scan.next();
                nazwyPartii[i] = nazwa;
            }

            for (int i = 1; i <= liczbaPartii; ++i) {
                int budzet = scan.nextInt();
                budzetyPartii[i] = budzet;
            }

            for (int i = 1; i <= liczbaPartii; ++i) {
                String strategia = scan.next();
                strategiePartii[i] = strategia.charAt(0);
            }

            for (int i = 1; i <= liczbaOkregow; ++i) {
                int liczba = scan.nextInt();
                liczbyWyborcow[i] = liczba;

                if (scalOkreg[i]) {
                    liczbyWyborcow[i - 1] += liczba;
                }
            }

            //ustawianie odpowiednich rozmiarow tablic
            for (int i = 1; i <= liczbaOkregow; ++i) {
                if (scalOkreg[i]) {
                    wyborcy[i] = new Wyborca[1];
                    for (int j = 1; j <= liczbaPartii; ++j) {
                        kandydaci[i][j] = new Kandydat[1];
                    }
                } else {
                    wyborcy[i] = new Wyborca[liczbyWyborcow[i] + 1];
                    for (int j = 1; j <= liczbaPartii; ++j) {
                        kandydaci[i][j] = new Kandydat[liczbyWyborcow[i] / 10  + 1];
                    }
                }
            }

            for (int i = 1; i <= liczbaOkregow; ++i) {
                for (int j = 1; j <= liczbaPartii; ++j) {
                    int liczbaKandydatow = liczbyWyborcow[i] / 10;
                    if (i < liczbaOkregow && scalOkreg[i + 1]) {
                        liczbaKandydatow -= liczbyWyborcow[i + 1] / 10;
                    }

                    for (int k = 1; k <= liczbaKandydatow; ++k) {
                        String imie = scan.next();
                        String nazwisko = scan.next();
                        int numerOkregu = scan.nextInt();
                        String nazwaPartii = scan.next();
                        int numerNaLiscie = scan.nextInt();
                        int[] cechy = new int[liczbaCech + 1];

                        for (int l = 1; l <= liczbaCech; ++l) {
                            cechy[l] = scan.nextInt();
                        }

                        if (scalOkreg[i]) {
                            kandydaci[i - 1][j][(liczbyWyborcow[i - 1] - liczbyWyborcow[i]) / 10 + k] =
                                    new Kandydat(imie, nazwisko, cechy, j);
                        } else {
                            kandydaci[i][j][k] = new Kandydat(imie, nazwisko, cechy, j);
                        }
                    }
                }
            }

            for (int i = 1; i <= liczbaOkregow; ++i) {
                int liczbaWyborcow = liczbyWyborcow[i];
                if (i < liczbaOkregow && scalOkreg[i + 1]) {
                    liczbaWyborcow -=liczbyWyborcow[i + 1];
                }

                for (int j = 1; j <= liczbaWyborcow; ++j) {
                    String imie = scan.next();
                    String nazwisko = scan.next();
                    int numerOkregu = scan.nextInt();
                    int typ = scan.nextInt();

                    int ind1, ind2;

                    if (scalOkreg[i]) {
                        ind1 = i - 1;
                        ind2 = liczbyWyborcow[i - 1] - liczbyWyborcow[i];
                    } else {
                        ind1 = i;
                        ind2 = 0;
                    }

                    if (typ == 1) {
                        String nazwa = scan.next();
                        wyborcy[ind1][ind2 + j] = new WyborcaTyp1(imie, nazwisko, i, nazwa, nazwyPartii);
                    } else if (typ == 2) {
                        String nazwa = scan.next();
                        int numerNaLiscie = scan.nextInt();
                        if (scalOkreg[i]) {
                            numerNaLiscie += (liczbyWyborcow[i - 1] - liczbyWyborcow[i]) / 10;
                        }
                        wyborcy[ind1][ind2 + j] =
                                new WyborcaTyp2(imie, nazwisko, i, numerNaLiscie, nazwa, nazwyPartii);
                    } else if (typ == 3) {
                        int numerCechy = scan.nextInt();
                        wyborcy[ind1][ind2 + j] = new WyborcaTyp3(imie, nazwisko, i, numerCechy);
                    } else if (typ == 4) {
                        int numerCechy = scan.nextInt();
                        wyborcy[ind1][ind2 + j] = new WyborcaTyp4(imie, nazwisko, i, numerCechy);
                    } else if (typ == 5) {
                        int[] wagiCech = new int[liczbaCech + 1];
                        for (int k = 1; k <= liczbaCech; ++k) {
                            wagiCech[k] = scan.nextInt();
                        }
                        wyborcy[ind1][ind2 + j] = new WyborcaTyp5(imie, nazwisko, i, wagiCech);
                    } else if (typ == 6) {
                        int numerCechy = scan.nextInt();
                        String nazwa = scan.next();
                        wyborcy[ind1][ind2 + j] =
                                new WyborcaTyp6(imie, nazwisko, i, numerCechy, nazwa, nazwyPartii);
                    } else if (typ == 7) {
                        int numerCechy = scan.nextInt();
                        String nazwa = scan.next();
                        wyborcy[ind1][ind2 + j] =
                                new WyborcaTyp7(imie, nazwisko, i, numerCechy, nazwa, nazwyPartii);
                    } else if (typ == 8) {
                        int[] wagiCech = new int[liczbaCech + 1];
                        for (int k = 1; k <= liczbaCech; ++k) {
                            wagiCech[k] = scan.nextInt();
                        }
                        String nazwa = scan.next();
                        wyborcy[ind1][ind2 + j] =
                                new WyborcaTyp8(imie, nazwisko, i, wagiCech, nazwa, nazwyPartii);
                    }
                }
            }

            for (int i = 1; i <= liczbaDzialan; ++i) {
                for (int j = 1; j <= liczbaCech; ++j) {
                    int wartosc = scan.nextInt();
                    dzialania[i][j] = wartosc;
                }
            }

            scan.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= liczbaOkregow; ++i) {
            if (scalOkreg[i]) {
                liczbyWyborcow[i] = 0;
            }
        }

        Wybory[] symulacje = new Wybory[3];
        symulacje[0] = new DHondt(liczbaOkregow, liczbaPartii, liczbaDzialan, liczbaCech,
                scalOkreg, nazwyPartii, budzetyPartii, strategiePartii, liczbyWyborcow,
                kandydaci, wyborcy, dzialania);
        symulacje[1] = new SainteLague(liczbaOkregow, liczbaPartii, liczbaDzialan, liczbaCech,
                scalOkreg, nazwyPartii, budzetyPartii, strategiePartii, liczbyWyborcow,
                kandydaci, wyborcy, dzialania);
        symulacje[2] = new HareNiemeyer(liczbaOkregow, liczbaPartii, liczbaDzialan, liczbaCech,
                scalOkreg, nazwyPartii, budzetyPartii, strategiePartii, liczbyWyborcow,
                kandydaci, wyborcy, dzialania);

        for (int i = 0; i < 3; ++i) {
            symulacje[i].przeprowadzKampanie();
            symulacje[i].przeprowadzWybory();
            symulacje[i].rozdajMandaty();
            symulacje[i].wypiszWynikiWyborow();
        }
    }
}