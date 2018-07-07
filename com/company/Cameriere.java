package com.company;

public class Cameriere extends Thread {
    private static int ordiniConsegnati = 0;
    private GestoreOrdini g;

    Cameriere (GestoreOrdini x) {
        g = x;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Richiesta x = g.prendiRichiestaCompletata();
                if (x != null) {
                    stampaOrdine(x);

                }
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {}
    }

    synchronized void ordineConsegnato () {
        ordiniConsegnati++;
    }

    synchronized void stampaOrdine (Richiesta x) {
        ordineConsegnato();
        System.out.println();
        System.out.println("ORDINE "+ordiniConsegnati+" COMPLETATO, id:" +x.getId());
        System.out.println("Piatto A: " + x.piatti[0].num);
        System.out.println("Piatto B: " + x.piatti[1].num);
        System.out.println("Piatto C: " + x.piatti[2].num);
        System.out.println("Piatto D: " + x.piatti[3].num);
        System.out.println();
    }
}
