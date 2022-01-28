package nasp.lab56;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int opcija;
        do {
            System.out.println("Odaberite jednu od ponuđenih opcija:");
            System.out.println("1) Unos formule (Opcija omogućuje unos trenutne logičke formule) ");
            System.out.println("2) Unos grafa (Opcija omogućuje unos trenutnog grafa) ");
            System.out.println("3) Je li formula ispunjiva (Opcija omogućuje ispitivanje da li je formula ispunjiva)");
            System.out.println("4) Postoji li k-nezavisnih čvorova (Opcija ispituje da li u grafu postoji skup sa barem k nezavisnih \n" +
                    "čvorova)");
            System.out.println("5) Postoji li k-klika (Opcija ispituje da li u grafu postoji k-klika)");
            System.out.println("6) Verifikacija formule (Opcija omogućuje verifikaciju nekog pridruživanja vrijednosti logičkim \n" +
                    "varijablama )");
            System.out.println("7) Verifikacija skupa nezavisnih čvorova (Opcija omogućuje verifikaciju skupa nezavisnih čvorova) ");
            System.out.println("8) Verifikacija k-klike (Opcija omogućuje verifikaciju k-klike) ");
            System.out.println("9) Redukcija 3-SAT-TO-INDSET");
            System.out.println("10) Redukcija 3-SAT-TO-CLIQUE");
            System.out.println("11) Izlaz");
            Scanner input = new Scanner(System.in);
            opcija = input.nextInt();
            switch (opcija) {
                case 1:
                    Formula3CNF.unos3CNF();
                    break;
                case 2:
                    KlikaINDSETGraf.unosGrafa();
                    break;
                case 3:
                    Formula3CNF.rjesenje3CNF();
                    break;
                case 4:
                    KlikaINDSETGraf.rjesenjeINDSET();
                    break;
                case 5:
                    KlikaINDSETGraf.rjesenjeKKlike();
                    break;
                case 6:
                    System.out.println("Unesite vrijednosti " + Formula3CNF.n + "literala:");
                    ArrayList<Integer> kombinacija = new ArrayList<>();
                    for (int i = 0; i < Formula3CNF.n; i++) {
                        kombinacija.add(input.nextInt());
                    }
                    int v = Formula3CNF.verifikacija3CNF(kombinacija);
                    if (v == 1) System.out.println("Verifikacija uspješna");
                    else System.out.println("Verifikacija neuspješna");
                    break;
                case 7:
                    System.out.println("Unesite broj nezavisnih čvorova: ");
                    int n = input.nextInt();
                    int[] cvorovi = new int[n];
                    System.out.println("Unesite čvorove:");
                    for (int i = 0; i < n; i++) {
                        cvorovi[i] = input.nextInt();
                    }
                    v = KlikaINDSETGraf.verifikacijaINDSET(cvorovi);
                    if (v == 1) System.out.println("Verifikacija uspješna");
                    else System.out.println("Verifikacija neuspješna");
                    break;
                case 8:
                    System.out.println("Unesite broj čvorova klike: ");
                    n = input.nextInt();
                    int[] cvoroviKlike = new int[n];
                    System.out.println("Unesite čvorove:");
                    for (int i = 0; i < n; i++) {
                        cvoroviKlike[i] = input.nextInt();
                    }
                    v = KlikaINDSETGraf.verifikacijaKKlike(cvoroviKlike);
                    if (v == 1) System.out.println("Verifikacija uspješna");
                    else System.out.println("Verifikacija neuspješna");
                    break;
                default:
                    break;
                case 9:
                    Formula3CNF.red3SATtoINDSET();
                    //KlikaINDSETGraf.print();
                    break;
                case 10:
                    Formula3CNF.red3SATtoClique();
                    //KlikaINDSETGraf.print();
                    break;
            }
        } while (opcija != 11);
      /* Formula3CNF.unos3CNF();
       Formula3CNF.red3SATtoINDSET();
       KlikaINDSETGraf.rjesenjeINDSET();
       Formula3CNF.rjesenje3CNF();
        ArrayList<Integer> kombinacija = new ArrayList<>();
        kombinacija.add(0);
        kombinacija.add(0);
        kombinacija.add(0);
        kombinacija.add(0);
        kombinacija.add(1);
        Formula3CNF.verifikacija3CNF(kombinacija);
        KlikaINDSETGraf.unosGrafa();
        int[] klika1 = {1,2,3};
        int[] klika2 = {3,4,5};
        int[] klika3 = {2,5,1};
        System.out.println(KlikaINDSETGraf.verifikacijaKKlike(klika1));
        System.out.println(KlikaINDSETGraf.verifikacijaKKlike(klika2));
        System.out.println(KlikaINDSETGraf.verifikacijaKKlike(klika3));
        int[] nez1 = {1,2,3}; //0
        int[] nez2 = {1,4}; //1
        int[] nez3 = {1,3,5};//0
        int[] nez4 = {2,5};//1
        int[] nez5 = {2,4};//1
        System.out.println(KlikaINDSETGraf.verifikacijaINDSET(nez1));
        System.out.println(KlikaINDSETGraf.verifikacijaINDSET(nez2));
        System.out.println(KlikaINDSETGraf.verifikacijaINDSET(nez3));
        System.out.println(KlikaINDSETGraf.verifikacijaINDSET(nez4));
        System.out.println(KlikaINDSETGraf.verifikacijaINDSET(nez5));*/
    }
}
