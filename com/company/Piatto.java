package com.company;

public class Piatto {
    int idRichiesta;
    int tipo;
    int num;
    boolean prelevato = false;
    boolean finito = false;

    Piatto (int id, int x, int y) {
        idRichiesta = id;
        num = x;
        tipo = y;
    }
}
