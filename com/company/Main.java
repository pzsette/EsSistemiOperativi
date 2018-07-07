package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        Scanner s = new Scanner(System.in);
        do {
            System.out.print("Inserisci N numero di Cuochi (>0) --> ");
            N = s.nextInt();
        } while(N<=0);

        Cucina c = new Cucina();

        GestoreOrdini g = new GestoreOrdini();

        Cuoco[] cuochiArray = new Cuoco[N];

        for (int i=0; i<N ; i++) {
            cuochiArray[i] = new Cuoco(c, g, i); //
            cuochiArray[i].start();
        }

        Cameriere cam1 = new Cameriere(g);
        Cameriere cam2 = new Cameriere(g);

        cam1.start();
        cam2.start();

        for (int i=0; i<60; i++) {
            int x = (int) (Math.random() * 3)+1;
            int y = (int) (Math.random() * 3)+1;
            int z = (int) (Math.random() * 3)+1;
            int w = (int) (Math.random() * 3)+1;

            Richiesta r = new Richiesta(i, x, y, z, w);
            g.aggiungiRichiesta(r);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }

        cam1.interrupt();
        cam2.interrupt();

        for (int i=0; i<N ; i++) {
            cuochiArray[i].interrupt();
        }
    }
}
