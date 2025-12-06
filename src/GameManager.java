import java.util.ArrayList;

public class GameManager {
    private ArrayList<Raketa> rakety;
    private ArrayList<Bombarder> bombardery;

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

    public void tik(){
        kontrolaKolizieRakBom();
    }

    public boolean koliziaRakBom(Raketa raketa, Bombarder bombarder) {
        int stredRaketyX = raketa.getRaketaX()+5;

        int stredRaketyY = raketa.getRaketaY() + 50;
        int stredBombarderaX = bombarder.getPolohaX() + 50;
        int stredBombarderaY = bombarder.getPolohaY() + 50;

        return Math.abs(stredRaketyX - stredBombarderaX) < 50 &&
                Math.abs(stredRaketyY - stredBombarderaY) < 50;
    }

    public void kontrolaKolizieRakBom(){
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
}
