package com.company;

public class Cuoco extends Thread {
    int id; //
    private int[][] ingredienti = new int[4][10];
    private GestoreOrdini gestore;
    private Cucina cucina;

    Cuoco(Cucina x, GestoreOrdini y, int z) { //

        id = z;
        cucina = x;
        gestore = y;

        ingredienti[0] = new int[]{1,2,2,4,0,0,0,1,0,0};
        ingredienti[1] = new int[]{0,0,1,1,0,0,2,2,3,1};
        ingredienti[2] = new int[]{1,0,1,0,1,2,2,2,1,0};
        ingredienti[3] = new int[]{0,0,0,0,3,0,2,1,0,2};

    }

    @Override
    public void run() {
        try {
            while (true) {
                Piatto p = gestore.prendiPiatto();
                System.out.println("Cuoco"+id+" deve cucinare "+p.num+ "del tipo"+p.tipo+" della richiesta id"+p.idRichiesta); //
                System.out.println(); //
                if (p != null) {
                    int ingredientiPresi;
                    int piattiFiniti = 0;
                    boolean broken = false;
                    for (int i = 0; i < p.num; i++) {
                        cucina.prendiFornello();
                        for (int j = 0; j < 10; j++) {
                            if (cucina.getIngrediente(j) >= ingredienti[p.tipo][j]) {
                                cucina.prendiIngrediente(j, ingredienti[p.tipo][j]);
                            } else {
                                ingredientiPresi = j;
                                for (int z = 0; z < ingredientiPresi; z++) {
                                    cucina.rendiIngrediente(z, ingredienti[p.tipo][z]);
                                }
                                broken = true;
                                break;
                            }

                        }
                        cucina.lasciaFornello();
                        if (broken) {
                            break;
                        } else {
                            piattiFiniti++;
                        }
                    }
                    gestore.piattoFinito(p.idRichiesta, p.tipo, piattiFiniti);
                    //System.out.println("Finito di cucinare piatto id"+p.idRichiesta+"; tipo"+p.tipo+ "; completati"+piattiFiniti);
                }
                Thread.sleep(700);
            }
        } catch (InterruptedException e) {}
    }
}
