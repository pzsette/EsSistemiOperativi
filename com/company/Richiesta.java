package com.company;

public class Richiesta {
    private int id;
    Piatto[] piatti = new Piatto[4];

    Richiesta (int id, int x, int y, int w, int z) {
        this.id = id;
        piatti[0] = new Piatto(id,x,0);
        piatti[1] = new Piatto(id,y,1);
        piatti[2] = new Piatto(id,w,2);
        piatti[3] = new Piatto(id,z,3);
    }

    int getId () {
        return id;
    }


}
