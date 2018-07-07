package com.company;

import java.util.ArrayList;

public class GestoreOrdini {
    private ArrayList<Richiesta> ordini; //lista ordinni da fare
    private ArrayList<Richiesta> ordiniTerminati;

    GestoreOrdini() {
        ordini = new ArrayList();
        ordiniTerminati = new ArrayList();
    }

    synchronized void aggiungiRichiesta(Richiesta x) { //aggiunge una richiesta alla lista
        ordini.add(x);
        notifyAll();
    }

    synchronized Piatto prendiPiatto () {
        try {
            while (ordini.size() == 0 || !tartaruga()) {
                wait();
            }
            for (Richiesta r : ordini) {
                for (Piatto p : r.piatti) {
                    if (!p.prelevato) {
                        p.prelevato = true;

                        return p;
                    }
                }
            }
        } catch (InterruptedException e) {}
        return null;
    }

    synchronized boolean tartaruga() {
        for (Richiesta r : ordini) {
            for(Piatto p: r.piatti) {
                if(!p.prelevato) {
                    return true;
                }
            }
        }
        return false;
    }

    synchronized void piattoFinito (int id, int indice, int quantita) {
        Richiesta r = getRichiesta(id);
        if (r == null) {
            return;
        }
        r.piatti[indice].num = quantita;
        r.piatti[indice].finito = true;
        boolean finito = true;
        for (Piatto p : r.piatti) {
            if (p.finito == false) {
                finito = false;
                break;
            }
        }
        if (finito) {
            ordini.remove(r);
            ordiniTerminati.add(r);
            notifyAll();
        }
    }

    synchronized Richiesta getRichiesta (int id) {
        for (Richiesta r : ordini) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    synchronized Richiesta prendiRichiestaCompletata () {
        Richiesta r = null;
        try {
            while (ordiniTerminati.size() == 0) {
                wait();
            }
            r = ordiniTerminati.get(0);
            ordiniTerminati.remove(r);
            notifyAll();
        } catch (InterruptedException e) {}
        return r;
    }
}
