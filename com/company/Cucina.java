package com.company;

public class Cucina {
    private int[] dispensa;
    private int fornelli = 4;


    Cucina() {
        dispensa = new int[]{200,200,400,500,400,200,600,600,400,300};
    }

    synchronized void prendiFornello() { //assegna un fornello per cucinare
        try {
            while (fornelli <= 0) {
                wait();
            }
            fornelli--;
        } catch (InterruptedException e) {}

    }

    synchronized void lasciaFornello() { //rilascia un fornello
        fornelli++;
        notifyAll();
    }

    synchronized void prendiIngrediente(int i, int qnt) { //prende una quantità "qnt" del ingediente "i"
        dispensa[i] -= qnt;
    }

    synchronized int getIngrediente (int i) {
        return dispensa[i];
    }

    synchronized void rendiIngrediente(int i, int qnt) { //prende una quantità "qnt" del ingediente "i"
        dispensa[i] += qnt;
    }
}
