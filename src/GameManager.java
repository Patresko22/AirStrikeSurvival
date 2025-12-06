import java.util.ArrayList;

public class GameManager {
    private ArrayList<Raketa> rakety;
    private ArrayList<Bombarder> bombardery;
    private ArrayList<MaleKamikadze> maleKamikadzeLietadla;
    private Lod lodHraca;






    public GameManager(){
        this.rakety = new ArrayList<>();
        this.bombardery = new ArrayList<>();
        this.maleKamikadzeLietadla = new ArrayList<>();



    }

    public void pridajRaketu(Raketa raketa){
        rakety.add(raketa);
    }
    public void pridajBombarder(Bombarder bombarder){
        bombardery.add(bombarder);
    }
    public void pridajMaleKamikadze(MaleKamikadze maleKamikadze){
        maleKamikadzeLietadla.add(maleKamikadze);
    }

    public void pridajLod(Lod lod){
        lodHraca = lod;
    }

    public void tik() {
        if (lodHraca.getHp() < 0){
            System.exit(0);
        }
        kontrolaKolizieRakBom();
        kontrolaKolizieBomLod();
        kontrolaKolizieMalKamLod();
        kontrolaKolizieRakMalKam();





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



    public boolean koliziaRakMalKam(Raketa raketa, MaleKamikadze maleKamikadze) {

        int stredRaketyX = raketa.getRaketaX()+5;
        int stredRaketyY = raketa.getRaketaY() + 17;

        int stredMalehoKamikadzeX = maleKamikadze.getPolohaX() + 17;
        int stredMalehoKamikadzeY = maleKamikadze.getPolohaY() + 22;

        return Math.abs(stredRaketyX - stredMalehoKamikadzeX) < 20 &&
                Math.abs(stredRaketyY - stredMalehoKamikadzeY) < 20;

    }

    public void kontrolaKolizieRakMalKam() {
        ArrayList<MaleKamikadze> znicene = new ArrayList<>();
        ArrayList<Raketa> vybuchnute = new ArrayList<>();


        for (Raketa raketa : rakety) {
            for (MaleKamikadze maleKamikadze : maleKamikadzeLietadla) {
                if (koliziaRakMalKam(raketa, maleKamikadze)) {
                    maleKamikadze.uberHP(raketa.getDamage());
                    vybuchnute.add(raketa);
                    raketa.vybuch();

                    if (maleKamikadze.getHp() <=0 ){
                        znicene.add(maleKamikadze);
                        maleKamikadze.znicenie();

                    }

                }
            }

        }
        rakety.removeAll(vybuchnute);
        maleKamikadzeLietadla.removeAll(znicene);

    }













    public boolean koliziaBomLod(Lod lod, Bombarder bombarder) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = bombarder.getPolohaY() + 100;

        return Math.abs(vrcholLode - vrcholBombardera) < 15;
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

    public boolean koliziaMalKamLod(Lod lod, MaleKamikadze maleKamikadze) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = maleKamikadze.getPolohaY() + 35;

        return Math.abs(vrcholLode - vrcholBombardera) < 35;
    }

    public void kontrolaKolizieMalKamLod(){
        ArrayList<MaleKamikadze> znicene = new ArrayList<>();


        for (MaleKamikadze maleKamikadze : maleKamikadzeLietadla){
            if (koliziaMalKamLod(lodHraca, maleKamikadze)){
                lodHraca.uberHP(maleKamikadze.getDamage());
                maleKamikadze.znicenie();
                znicene.add(maleKamikadze);
            }
        }
        maleKamikadzeLietadla.removeAll(znicene);
    }
}
