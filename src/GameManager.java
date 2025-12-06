import java.util.ArrayList;

public class GameManager {
    private ArrayList<Raketa> rakety;
    private ArrayList<Bombarder> bombardery;
    private Lod lodHraca;

    public GameManager(){
        this.rakety = new ArrayList<>();
        this.bombardery = new ArrayList<>();



    }

    public void pridajRaketu(Raketa raketa){
        rakety.add(raketa);
    }
    public void pridajBombarder(Bombarder bombarder){
        bombardery.add(bombarder);
    }
    public void pridajLod(Lod lod){
        lodHraca = lod;
    }

    public void tik() {
        kontrolaKolizieRakBom();
        kontrolaKolizieBomLod();

    }



    public boolean koliziaRakBom(Raketa raketa, Bombarder bombarder) {
        int stredRaketyX = raketa.getRaketaX()+5;

        int stredRaketyY = raketa.getRaketaY() + 17;
        int stredBombarderaX = bombarder.getPolohaX() + 30;
        int stredBombarderaY = bombarder.getPolohaY() + 50;

        return Math.abs(stredRaketyX - stredBombarderaX) < 50 &&
                Math.abs(stredRaketyY - stredBombarderaY) < 50;
    }

    public void kontrolaKolizieRakBom() {
        ArrayList<Bombarder> znicene = new ArrayList<>();
        ArrayList<Raketa> vybuchnute = new ArrayList<>();


        for (Raketa raketa : rakety) {
            for (Bombarder bombarder : bombardery) {
                if (koliziaRakBom(raketa, bombarder)) {
                    bombarder.uberHP(raketa.getDamage());
                    vybuchnute.add(raketa);
                    raketa.vybuch();

                    if (bombarder.getHp() <=0 ){
                        znicene.add(bombarder);
                        bombarder.znicenie();

                    }

                }
            }

        }
        rakety.removeAll(vybuchnute);
        bombardery.removeAll(znicene);




    }
    public boolean koliziaBomLod(Lod lod, Bombarder bombarder) {
        int vrcholLode = lod.getPolohaY();
        int vrcholBombardera = bombarder.getPolohaY() + 100;

        return Math.abs(vrcholLode - vrcholBombardera) < 20;
    }

    public void kontrolaKolizieBomLod(){
        ArrayList<Bombarder> znicene = new ArrayList<>();


       for (Bombarder bombarder : bombardery){
           if (koliziaBomLod(lodHraca, bombarder)){
               lodHraca.uberHP(bombarder.getDamage());
               bombarder.znicenie();
               znicene.add(bombarder);
           }
       }
       bombardery.removeAll(znicene);




    }
}
