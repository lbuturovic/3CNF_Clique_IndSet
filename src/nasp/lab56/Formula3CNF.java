package nasp.lab56;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Formula3CNF {
    static ArrayList<ArrayList<Integer>> klauze = new ArrayList<>();
    static int n = 0;

    public static int verifikacija3CNF(ArrayList<Integer> kombinacija) {
        int verifikacija = 1;
        for (int i = 0; i < klauze.size(); i++) {
            boolean klauzaTacna = false;
            for (int j = 0; j < klauze.get(i).size(); j++) {
                int indeksVarijable = (int) (Math.abs(klauze.get(i).get(j)) - 1);
                if ((klauze.get(i).get(j) > 0 && kombinacija.get(indeksVarijable) == 1) || (klauze.get(i).get(j) < 0 && kombinacija.get(indeksVarijable) == 0)) {
                    klauzaTacna = true; //nema potrebe za daljnjom provjerom
                    break;
                }
            }

            if (!klauzaTacna) { // neka od klauza nije tacna
                // System.out.println("Verifikacija neuspješna");
                verifikacija = 0;
                break;
            }

        }
        return verifikacija;
    }

    public static int rjesenje3CNF() {
        for (int i = 0; i < (int) Math.pow(2, n); i++) {
            String rezultat = Integer.toBinaryString(i);
            String resultWithPadding = String.format("%" + n + "s", rezultat).replaceAll(" ", "0");  // n-bit Integer
            ArrayList<Integer> kombinacija = new ArrayList<>();
            for (int j = 0; j < resultWithPadding.length(); j++) {
                kombinacija.add(Integer.parseInt(String.valueOf(resultWithPadding.charAt(j))));
            }
            if (verifikacija3CNF(kombinacija) == 1) {
                System.out.println("Moguce rješenje je " + kombinacija.stream().map(Object::toString)
                        .collect(Collectors.joining(", ")));
                return 1;
            }
            ;
            //System.out.println(resultWithPadding);
        }
        System.out.println("Formula nije ispunjiva!");
        return 0;
    }

    public static void unos3CNF() {
        klauze = new ArrayList<>();
        n = 0;
        System.out.println("Unesite 3CNF formulu. Varijable imenovati redom (1, 2, 3, ... 24) odustanite pritiskom na 'q':");
        Scanner input = new Scanner(System.in);
        int i = 0;
        while (input.hasNext() && i != 7) {
            String linija = input.next();
            if (linija.equals("q")) {
                break;
            }

            ArrayList<Integer> klauza = new ArrayList<Integer>();
            for (String varijabla : linija.split(",")) {
                Integer var = Integer.parseInt(varijabla);
                if (Math.abs(var) > n) n = Math.abs(var);
                klauza.add(var);
            }
            if (n > 24) {
                System.out.println("Broj varijabli ne smije biti veći od 24!");
                break;
            }
            klauze.add(klauza);
            i++;
        }
        System.out.println();

    }

    static void red3SATtoINDSET() {
        int k = klauze.size();
        int p = n;
        KlikaINDSETGraf.graph = new int[3 * k + 1][3 * k + 1];
        KlikaINDSETGraf.d = new int[3 * k + 1];
        KlikaINDSETGraf.n = 3 * k;
        ArrayList<Integer> klauza;
        int j = 1;
        for (int i = 0; i < k; i++) {
            KlikaINDSETGraf.graph[j][j + 1] = 1;
            KlikaINDSETGraf.d[j]++;
            KlikaINDSETGraf.graph[j + 1][j] = 1;
            KlikaINDSETGraf.d[j + 1]++;
            KlikaINDSETGraf.graph[j][j + 2] = 1;
            KlikaINDSETGraf.d[j]++;
            KlikaINDSETGraf.graph[j + 2][j] = 1;
            KlikaINDSETGraf.d[j + 2]++;
            KlikaINDSETGraf.graph[j + 1][j + 2] = 1;
            KlikaINDSETGraf.d[j + 1]++;
            KlikaINDSETGraf.graph[j + 2][j + 1] = 1;
            KlikaINDSETGraf.d[j + 2]++;
            j = j + 3;
        }
        int indexElement = 1;
        for (int i = 0; i < k; i++) {
            klauza = klauze.get(i);
            for (int l = 0; l < klauza.size(); l++) {
                int element = klauza.get(l);
                int indexElementm = 3 * (i + 1) + 1;
                for (int m = i + 1; m < k; m++) {
                    ArrayList<Integer> klauzam = klauze.get(m);
                    for (int n = 0; n < klauzam.size(); n++) {
                        if (element + klauzam.get(n) == 0) {
                            KlikaINDSETGraf.graph[indexElement][indexElementm] = 1;
                            KlikaINDSETGraf.d[indexElement]++;
                            KlikaINDSETGraf.graph[indexElementm][indexElement] = 1;
                            KlikaINDSETGraf.d[indexElementm]++;
                        }
                        indexElementm++;
                    }

                }
                indexElement++;
            }
        }
    }

    static void red3SATtoClique() {
        int k = klauze.size();
        int p = n;
        KlikaINDSETGraf.graph = new int[3 * k + 1][3 * k + 1];
        KlikaINDSETGraf.d = new int[3 * k + 1];
        KlikaINDSETGraf.n = 3 * k;
        ArrayList<Integer> klauza;
        int indexElement = 1;
        for (int i = 0; i < k; i++) {
            klauza = klauze.get(i);
            for (int l = 0; l < klauza.size(); l++) {
                int element = klauza.get(l);
                int indexElementm = 3 * (i + 1) + 1;
                for (int m = i + 1; m < k; m++) {
                    ArrayList<Integer> klauzam = klauze.get(m);
                    for (int n = 0; n < klauzam.size(); n++) {
                        if (element + klauzam.get(n) != 0) {
                            KlikaINDSETGraf.graph[indexElement][indexElementm] = 1;
                            KlikaINDSETGraf.d[indexElement]++;
                            KlikaINDSETGraf.graph[indexElementm][indexElement] = 1;
                            KlikaINDSETGraf.d[indexElementm]++;
                        }
                        indexElementm++;
                    }
                }
                indexElement++;
            }
        }

    }

}
