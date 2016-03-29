/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kme.ans;

import java.util.ArrayList;
import java.util.Collections;
import kme.ans.object.Centroid;
import kme.ans.object.Negara;

/**
 *
 * @author linuxluv
 */
public class Main {

    public static void main(String[] args) {
        Main myMain = new Main();
        myMain.doKmeans();
    }

    private void doKmeans() {
        Centroid centroid1 = new Centroid(20, 5);
        Centroid centroid2 = new Centroid(25, 4);
        Centroid centroid3 = new Centroid(30, 10);
        ArrayList<Negara> arNegara = buatDataDummyUntukKmeans();

        ArrayList<Negara> arNegaraC1 = new ArrayList<>();
        ArrayList<Negara> arNegaraC2 = new ArrayList<>();
        ArrayList<Negara> arNegaraC3 = new ArrayList<>();

        ArrayList<String> arNamaNegaraC1 = new ArrayList<>();
        ArrayList<String> arNamaNegaraC2 = new ArrayList<>();
        ArrayList<String> arNamaNegaraC3 = new ArrayList<>();

        ArrayList<String> arNamaNegaraC1prev = new ArrayList<>();
        ArrayList<String> arNamaNegaraC2prev = new ArrayList<>();
        ArrayList<String> arNamaNegaraC3prev = new ArrayList<>();

        int counter = 0;

//        boolean ngetestWhile = (arNamaNegaraC1.isEmpty() && arNamaNegaraC2.isEmpty() && arNamaNegaraC3.isEmpty())
//                || (!arNamaNegaraC1.containsAll(arNamaNegaraC1prev) || !arNamaNegaraC2.containsAll(arNamaNegaraC2prev)
//                || !arNamaNegaraC3.containsAll(arNamaNegaraC3prev));
//        System.out.println("testWhile: " + ngetestWhile);
        while ((arNamaNegaraC1.isEmpty() && arNamaNegaraC2.isEmpty() && arNamaNegaraC3.isEmpty())
                || (arNamaNegaraC1prev.isEmpty() && arNamaNegaraC2prev.isEmpty() && arNamaNegaraC3prev.isEmpty())
                || (!arNamaNegaraC1.containsAll(arNamaNegaraC1prev)
                || !arNamaNegaraC2.containsAll(arNamaNegaraC2prev)
                || !arNamaNegaraC3.containsAll(arNamaNegaraC3prev))) {
            
            arNamaNegaraC1prev.clear();
            arNamaNegaraC2prev.clear();
            arNamaNegaraC3prev.clear();
            arNamaNegaraC1prev.addAll(arNamaNegaraC1);
            arNamaNegaraC2prev.addAll(arNamaNegaraC2);
            arNamaNegaraC3prev.addAll(arNamaNegaraC3);

//            System.out.println("1");
//            for (int y = 0; y < arNamaNegaraC1.size(); y++) {
//                System.out.println("c1: " + arNamaNegaraC1.get(y).toString());
//            }
//            System.out.println("1");
//            for (int y = 0; y < arNamaNegaraC1prev.size(); y++) {
//                System.out.println("prev c1: " + arNamaNegaraC1prev.get(y).toString());
//            }

            //update centroid
            centroid1 = updateCentroid(arNegaraC1, centroid1);
            centroid2 = updateCentroid(arNegaraC2, centroid2);
            centroid3 = updateCentroid(arNegaraC3, centroid3);

            //kosongkan semua array cluster sebelum diAdd2
            arNegaraC1.clear();
            arNegaraC2.clear();
            arNegaraC3.clear();
            arNamaNegaraC1.clear();
            arNamaNegaraC2.clear();
            arNamaNegaraC3.clear();

            for (int i = 0; i < arNegara.size(); i++) {
                double jarakKeCluster1 = hitungJarak(arNegara.get(i), centroid1);
                double jarakKeCluster2 = hitungJarak(arNegara.get(i), centroid2);
                double jarakKeCluster3 = hitungJarak(arNegara.get(i), centroid3);

//            System.out.println("negara " + arNegara.get(i).getNama() + " | jarak1: " + jarakKeCluster1 + " | jarak2:" + jarakKeCluster2
//                    + " | jarak3:" + jarakKeCluster3);
                tentukanCluster(arNegara.get(i), jarakKeCluster1, jarakKeCluster2, jarakKeCluster3,
                        arNegaraC1, arNegaraC2, arNegaraC3,
                        arNamaNegaraC1, arNamaNegaraC2, arNamaNegaraC3);
            }
            counter++;

//            System.out.println("");
//            for (int y = 0; y < arNamaNegaraC1.size(); y++) {
//                System.out.println("c1: " + arNamaNegaraC1.get(y).toString());
//            }
//            System.out.println("");
//            for (int y = 0; y < arNamaNegaraC1prev.size(); y++) {
//                System.out.println("prev c1: " + arNamaNegaraC1prev.get(y).toString());
//            }
//
//            System.out.println("testC1: " + arNamaNegaraC1.containsAll(arNamaNegaraC1prev));
//            System.out.println("testC2: " + arNamaNegaraC2.containsAll(arNamaNegaraC2prev));
//            System.out.println("testC3: " + arNamaNegaraC3.containsAll(arNamaNegaraC3prev));

        }

        System.out.println("");
        System.out.println("counter: " + counter);
        System.out.println("anggota cluster 1 :");
        for (int i = 0; i < arNegaraC1.size(); i++) {
            System.out.println("" + arNegaraC1.get(i).getNama());
        }

        System.out.println("anggota cluster 2 :");
        for (int i = 0; i < arNegaraC2.size(); i++) {
            System.out.println("" + arNegaraC2.get(i).getNama());
        }

        System.out.println("anggota cluster 3 :");
        for (int i = 0; i < arNegaraC3.size(); i++) {
            System.out.println("" + arNegaraC3.get(i).getNama());
        }
    }

    private Centroid updateCentroid(ArrayList<Negara> arNegara, Centroid centroid) {
        if (!arNegara.isEmpty()) {
            double jmlNilaiCbr = 0.0;
            double jmlNilaiCdr = 0.0;
            for (int i = 0; i < arNegara.size(); i++) {
                jmlNilaiCbr += arNegara.get(i).getCbr();
                jmlNilaiCdr += arNegara.get(i).getCdr();
            }
            centroid.setX1(jmlNilaiCbr / arNegara.size());
            centroid.setX2(jmlNilaiCdr / arNegara.size());
        }
        return centroid;
    }

    private void tentukanCluster(Negara negara, double jarakKeCluster1,
            double jarakKeCluster2, double jarakKeCluster3,
            ArrayList<Negara> arNegaraCluster1, ArrayList<Negara> arNegaraCluster2,
            ArrayList<Negara> arNegaraCluster3,
            ArrayList<String> arNamaNegaraC1, ArrayList<String> arNamaNegaraC2,
            ArrayList<String> arNamaNegaraC3) {
        double minimum = jarakKeCluster1;
        if (jarakKeCluster2 < minimum) {
            minimum = jarakKeCluster2;
        }
        if (jarakKeCluster3 < minimum) {
            minimum = jarakKeCluster3;
        }

        //isi cluster
        if (minimum == jarakKeCluster1) {
            negara.setCluster(1);
            arNegaraCluster1.add(negara);
            arNamaNegaraC1.add(negara.getNama());
        } else if (minimum == jarakKeCluster2) {
            negara.setCluster(2);
            arNegaraCluster2.add(negara);
            arNamaNegaraC2.add(negara.getNama());
        } else {
            negara.setCluster(3);
            arNegaraCluster3.add(negara);
            arNamaNegaraC3.add(negara.getNama());
        }
    }

    private double hitungJarak(Negara negara, Centroid centroid) {
        double jarakKeCluster = Math.sqrt(Math.pow((negara.getCbr() - centroid.getX1()), 2)
                + Math.pow((negara.getCdr() - centroid.getX2()), 2));
        return jarakKeCluster;
    }

    //generate dummy data
    private ArrayList<Negara> buatDataDummyUntukKmeans() {
        ArrayList<Negara> arNegara = new ArrayList<>();
        arNegara.add(new Negara("Brunei", 27, 3));
        arNegara.add(new Negara("Kamboja", 38, 14));
        arNegara.add(new Negara("Indonesia", 24, 8));
        arNegara.add(new Negara("Laos", 43, 15));
        arNegara.add(new Negara("Malaysia", 28, 5));
        arNegara.add(new Negara("Myanmar", 32, 11));
        arNegara.add(new Negara("Filipina", 30, 7));
        arNegara.add(new Negara("Singapura", 17, 5));
        arNegara.add(new Negara("Thailand", 20, 6));
        arNegara.add(new Negara("Vietnam", 29, 8));
        return arNegara;
    }
}
