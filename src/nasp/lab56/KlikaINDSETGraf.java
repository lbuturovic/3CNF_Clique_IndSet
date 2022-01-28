package nasp.lab56;

import java.util.Scanner;

public class KlikaINDSETGraf {
    public static Scanner input = new Scanner(System.in);
    static int MAX = 16;
    static int n;
    static int[][] graph = new int[MAX][MAX];
    static int[] d = new int[MAX];

    static void unosGrafa() {
        graph = new int[MAX][MAX];
        d = new int[MAX];
        System.out.println("Unesite broj čvorova grafa (<=15):");
        n = input.nextInt();
        input.nextLine();
        System.out.println("Unesite grane čvorova imenovanih kao 1,2,... Unosite kao broj čvora - enter - broj čvora. Za kraj pritisnite 'q'.");
        int i = 1;
        while (true) {
            System.out.println("Unesite " + i + ". granu:");
            String prvi = input.next();
            if (prvi.equals("q")) {
                break;
            }
            int prviCvor = Integer.parseInt(prvi);
            String drugi = input.next();
            int drugiCvor = Integer.parseInt(drugi);
            if (prviCvor > n || drugiCvor > n) {
                System.out.println("Unijeli ste nepostojeći čvor!");

            } else {
                graph[prviCvor][drugiCvor] = 1;
                graph[drugiCvor][prviCvor] = 1;
                d[prviCvor]++;
                d[drugiCvor]++;
                i++;
            }
        }
    }

    static void print() {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++)
                System.out.print(graph[i][j] + " ");
            System.out.print("\n");
        }
    }

    private static int verifikacija(int[] cvorovi, boolean klika) {
        int k = cvorovi.length;
        int verifikacija = 1;
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                if ((klika && graph[cvorovi[i]][cvorovi[j]] == 0) || (!klika && graph[cvorovi[i]][cvorovi[j]] == 1)) {

                    return 0;
                }
            }
        }
        //System.out.println("Jeste klika unesenog grafa!");
        return 1;
    }

    static int verifikacijaKKlike(int[] cvorovi) {
        return verifikacija(cvorovi, true);
    }

    static int verifikacijaINDSET(int[] cvorovi) {
        return verifikacija(cvorovi, false);
    }

    static int rjesenjeKKlike() {
        return rjesenje(true);
    }

    static int rjesenjeINDSET() {
        return rjesenje(false);
    }

    private static int rjesenje(boolean klika) {
        System.out.println("Unesite K:");
        int k = input.nextInt();
        if (k > n) {
            System.out.println("K ne može biti veće od n!");
            return 0;
        }
        for (int i = 0; i < (int) Math.pow(2, n); i++) {
            String rezultat = Integer.toBinaryString(i);
            String resultWithPadding = String.format("%" + n + "s", rezultat).replaceAll(" ", "0");  // n-bit Integer
            int[] kombinacija = new int[k];
            String pomocni = resultWithPadding.replace("0", "");
            int indeks = 0;
            if (pomocni.length() == k) {
                for (int j = 0; j < resultWithPadding.length(); j++) {
                    if (resultWithPadding.charAt(j) == '1') {
                        if (klika && d[j + 1] < (k - 2)) break;
                        kombinacija[indeks] = j + 1;
                        indeks++;
                    }
                }
                if (indeks == k) {
                    int v = 0;
                    if (klika)
                        v = verifikacijaKKlike(kombinacija);
                    else v = verifikacijaINDSET(kombinacija);
                    if (v == 1) {
                        System.out.println("Rješenje nađeno!");
                        return v;
                    }
                }
            }

        }
        System.out.println("Rješenje nije nađeno!");
        return 0;
    }

}

