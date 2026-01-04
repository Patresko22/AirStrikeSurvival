import java.util.ArrayList;

public class GameManager {
    private ArrayList<Raketa> rakety;
    private ArrayList<Bombarder> bombardery;
    private ArrayList<MaleKamikadze> maleKamikadzeLietadla;
    private ArrayList<VelkeKamikadze> velkeKamikadzeLietadla;
    private ArrayList<Bomba> Bomby;
    private Lod lodHraca;







    public GameManager(){
        this.rakety = new ArrayList<>();
        this.bombardery = new ArrayList<>();
        this.maleKamikadzeLietadla = new ArrayList<>();
        this.velkeKamikadzeLietadla = new ArrayList<>();
        this.Bomby = new ArrayList<>();





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
    public void pridajVelkeKamikadze(VelkeKamikadze velkeKamikadze){
        velkeKamikadzeLietadla.add(velkeKamikadze);
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
        ubratieCasuMaleKamikadze();
        kontrolaKolizieRakVelKam();
        kontrolaKolizieVelKamLod();
        ubratieCasuVelkeKamikadze();
        ubratieCasuBombardera();
        znicenieBombardera();
        ubratieCasuBomby();
        vybuchBomby();
        kontrolaCasuRakety();






    }



    public void kontrolaCasuRakety(){
        ArrayList<Raketa> vybuchnute = new ArrayList<>();
        for (Raketa raketa : rakety){
            if (raketa.getVybuch()){
                raketa.uberCas();
                System.out.println(raketa.getCasDoVybuchu());
            }
            if (raketa.getCasDoVybuchu() <= 0){
                raketa.vybuchla();
                vybuchnute.add(raketa);
            }
        }
        rakety.removeAll(vybuchnute);
    }










    //--------------------------------Male Kamikadze-----------------------------------------

    //---------------------------------------------------------------------------------------
    //Male kamikadze -- Raketa

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



        for (Raketa raketa : rakety) {
            for (MaleKamikadze maleKamikadze : maleKamikadzeLietadla) {
                if (koliziaRakMalKam(raketa, maleKamikadze)) {
                    maleKamikadze.uberHP(raketa.getDamage());
                    raketa.vybuch();
                    if (maleKamikadze.getHp() <=0 ){
                        znicene.add(maleKamikadze);
                        maleKamikadze.znicenie();

                    }

                }
            }

        }
        maleKamikadzeLietadla.removeAll(znicene);

    }





    //---------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------------

    //Male kamikadze -- Lod

    public boolean koliziaMalKamLod(Lod lod, MaleKamikadze maleKamikadze) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = maleKamikadze.getPolohaY() + 35;

        return Math.abs(vrcholLode - vrcholBombardera) < 35;
    }

    public void kontrolaKolizieMalKamLod(){
        ArrayList<MaleKamikadze> znicene = new ArrayList<>();


        for (MaleKamikadze maleKamikadze : maleKamikadzeLietadla){
            if (koliziaMalKamLod(lodHraca, maleKamikadze)){

                maleKamikadze.animaciaVybuchu();

                if (maleKamikadze.getCasDoVybuchu() < 0){
                    znicene.add(maleKamikadze);
                    lodHraca.uberHP(maleKamikadze.getDamage());
                    maleKamikadze.znicenie();


                }
            }
        }
        maleKamikadzeLietadla.removeAll(znicene);
    }

    public void ubratieCasuMaleKamikadze(){
        for (MaleKamikadze maleKamikadze : maleKamikadzeLietadla){
            if (maleKamikadze.getVybuch() == true){
                maleKamikadze.uberCas();


            }
        }


    }
    //---------------------------------------------------------------------------------------










//--------------------------------Male Kamikadze-----------------------------------------

    //---------------------------------------------------------------------------------------
    //Male kamikadze -- Raketa

    public boolean koliziaRakVelKam(Raketa raketa, VelkeKamikadze velkeKamikadze) {

        int stredRaketyX = raketa.getRaketaX()+5;
        int stredRaketyY = raketa.getRaketaY() + 17;

        int stredVelkehoKamikadzeX = velkeKamikadze.getPolohaX() + 17;
        int stredVelkehoKamikadzeY = velkeKamikadze.getPolohaY() + 22;

        return Math.abs(stredRaketyX - stredVelkehoKamikadzeX) < 20 &&
                Math.abs(stredRaketyY - stredVelkehoKamikadzeY) < 20;

    }

    public void kontrolaKolizieRakVelKam() {
        ArrayList<VelkeKamikadze> znicene = new ArrayList<>();


        for (Raketa raketa : rakety) {
            for (VelkeKamikadze velkeKamikadze : velkeKamikadzeLietadla) {
                if (koliziaRakVelKam(raketa, velkeKamikadze)) {
                    velkeKamikadze.uberHP(raketa.getDamage());
                    raketa.vybuch();

                    if (velkeKamikadze.getHp() <=0 ){
                        znicene.add(velkeKamikadze);
                        velkeKamikadze.znicenie();

                    }

                }
            }

        }
        velkeKamikadzeLietadla.removeAll(znicene);

    }





    //---------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------------

    //Velke kamikadze -- Lod

    public boolean koliziaVelKamLod(Lod lod, VelkeKamikadze velkeKamikadze) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = velkeKamikadze.getPolohaY() + 35;

        return Math.abs(vrcholLode - vrcholBombardera) < 35;
    }

    public void kontrolaKolizieVelKamLod(){
        ArrayList<VelkeKamikadze> znicene = new ArrayList<>();


        for (VelkeKamikadze velkeKamikadze : velkeKamikadzeLietadla){
            if (koliziaVelKamLod(lodHraca, velkeKamikadze)){

                velkeKamikadze.animaciaVybuchu();

                if (velkeKamikadze.getCasDoVybuchu() < 0){
                    znicene.add(velkeKamikadze);
                    lodHraca.uberHP(velkeKamikadze.getDamage());
                    velkeKamikadze.znicenie();


                }
            }
        }
        velkeKamikadzeLietadla.removeAll(znicene);
    }

    public void ubratieCasuVelkeKamikadze(){
        for (VelkeKamikadze velkeKamikadze : velkeKamikadzeLietadla){
            if (velkeKamikadze.getVybuch() == true){
                velkeKamikadze.uberCas();


            }
        }


    }
    //---------------------------------------------------------------------------------------








































    //----------------------------Bombarder------------------------------------------------



    //---------------------------------------------------------------------------------------
    // Bombarder -- Lod

    public boolean koliziaBomLod(Lod lod, Bombarder bombarder) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = bombarder.getPolohaY() + 100;

        return Math.abs(vrcholLode - vrcholBombardera) < 15;
    }

    public void kontrolaKolizieBomLod() {
       for (Bombarder bombarder : bombardery){
           if (koliziaBomLod(lodHraca, bombarder)){

               if (bombarder.getZhodenaBomba() == false) {

                   Bomba bomba = new Bomba(bombarder.getPolohaX()+30, bombarder.getPolohaY()+70);
                   bombarder.zhodBombu();
                   this.Bomby.add(bomba);
               }

           }
       }
    }
    //---------------------------------------------------------------------------------------


    public void ubratieCasuBomby(){
        for (Bomba bomba : Bomby){
            bomba.uberCas();
            System.out.println(bomba.getCasDoVybuchu());
        }
    }

    public void vybuchBomby(){
        ArrayList<Bomba> znicene = new ArrayList<>();
        for (Bomba bomba : Bomby){
            if(bomba.getCasDoVybuchu() == 50){
                bomba.zmenObrazokNavybuch();
            }
            if (bomba.getCasDoVybuchu() == 0){
                lodHraca.uberHP(bomba.getDamage());
                bomba.znicenie();
                znicene.add(bomba);
            }
        }
        Bomby.removeAll(znicene);
    }



    public void ubratieCasuBombardera(){
        for (Bombarder bombarder : bombardery){
            if (bombarder.getZhodenaBomba() == true){
                bombarder.uberCas();
            }

        }
    }

    public void znicenieBombardera(){
        for (Bombarder bombarder : bombardery){
            if (bombarder.getCasDoPreletenie() < 0){
                if (bombarder.getJeZniceny() == false) {
                    bombarder.znicenie();
                }
            }
        }
    }

    //---------------------------------------------------------------------------------------
    // Bombarder -- raketa



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


        for (Raketa raketa : rakety) {
            for (Bombarder bombarder : bombardery) {
                if (koliziaRakBom(raketa, bombarder)) {
                    bombarder.uberHP(raketa.getDamage());
                    raketa.vybuch();

                    if (bombarder.getHp() <=0 ){
                        znicene.add(bombarder);
                        bombarder.znicenie();

                    }

                }
            }

        }
        bombardery.removeAll(znicene);

    }

    //---------------------------------------------------------------------------------------




}
