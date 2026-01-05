import java.util.ArrayList;
//ok

public class GameManager {

    private ArrayList<Raketa> rakety;
    private ArrayList<Gulka> gulky;
    private ArrayList<Bombarder> bombardery;
    private ArrayList<Stihacka> stihacky;
    private ArrayList<MaleKamikadze> maleKamikadzeLietadla;
    private ArrayList<VelkeKamikadze> velkeKamikadzeLietadla;
    private ArrayList<Bomba> Bomby;
    private Lod lodHraca;
    private ArrayList<RaketaStihacky> raketyStihaciek;
    private ArrayList<GulkaStihacka> gulkyStihacky;
    private HraciaPlocha plocha;
    private int skore = 0;
    private int hpLode;
    private Hrac hrac;
    private boolean levelDokonceny = false;


    public GameManager(HraciaPlocha plocha, int zaciatocneHpLode){
        this.hpLode = zaciatocneHpLode;
        this.plocha = plocha;
        this.rakety = new ArrayList<>();
        this.gulky = new ArrayList<>();
        this.bombardery = new ArrayList<>();
        this.stihacky = new ArrayList<>();
        this.maleKamikadzeLietadla = new ArrayList<>();
        this.velkeKamikadzeLietadla = new ArrayList<>();
        this.Bomby = new ArrayList<>();
        this.raketyStihaciek = new ArrayList<>();
        this.gulkyStihacky = new ArrayList<>();
        this.plocha.nastavSkore(this.skore);
        this.plocha.nastavHpLode(this.hpLode);
    }

    public void pridajRaketu(Raketa raketa) {
        this.rakety.add(raketa);
    }

    public void pridajGulku(Gulka gulka) {
        this.gulky.add(gulka);
    }

    public void pridajBombarder(Bombarder bombarder) {
        this.bombardery.add(bombarder);
    }

    public void pridajStihacku(Stihacka stihacka) {
        this.stihacky.add(stihacka);
    }

    public void pridajMaleKamikadze(MaleKamikadze maleKamikadze) {
        this.maleKamikadzeLietadla.add(maleKamikadze);
    }

    public void pridajVelkeKamikadze(VelkeKamikadze velkeKamikadze) {
        this.velkeKamikadzeLietadla.add(velkeKamikadze);
    }

    public void pridajLod(Lod lod) {
        this.lodHraca = lod;
        this.lodHraca.setHp(this.hpLode);
    }

    public void tik() {

        kontrolaRakety();
        kontrolaGulky();
        znicenieBombardera();
        ubratieCasuBomby();
        vybuchBomby();

        //Uberanie Času lietadiel
        ubratieCasuMaleKamikadze();
        ubratieCasuVelkeKamikadze();
        ubratieCasuBombardera();


        //Kontrola Času projektilov
        kontrolaCasuRakety();
        kontrolaCasuGulky();


        //Kontrola Kolizie s Lodou
        kontrolaKolizieBombarderLod();
        kontrolaKolizieMaleKamikadzeLod();
        kontrolaKolizieVelkeKamikadzeLod();


        //Kontrola Kolizie Gulky
        kontrolaKolizieGulkaMaleKamikadze();
        kontrolaKolizieGulkaVelkeKamikadze();
        kontrolaKolizieGulkaBombarder();
        kontrolaKolizieGulkaStihacka();

        //Kontrola Kolizie Rakety
        kontrolaKolizieRaketaBombarder();
        kontrolaKolizieRaketaMaleKam();
        kontrolaKolizieRaketaVelkeKamikadze();
        kontrolaKolizieRaketaStihacka();

        //Kontrola preletenia stihacky
        kontrolaPreleteniaStihacky();
        ubratieCasuStihacky();
        znicenieStihacky();


        raketyStihacky();
        kontrolaKolizieRaketyStihackyLode(lodHraca);
        kontrolaCasuRaketyStihacky();
        kontrolaKolizieRakiet();
        kontrolaKolizieRaketyStihackyGulky();

        gulkyStihacky();
        kontrolaKolizieGulkyStihackyLode(lodHraca);
        kontrolaCasuGulkyStihacky();
        kontrolaKolizieRaketyGulkyStihacky();
        kontrolaKolizieGulkyStihackyGulky();


        if (!this.levelDokonceny && uzNiesuZiadneLietadla()){
            this.levelDokonceny = true;
            WinScreen winScreen = new WinScreen(this.skore);
            this.hrac.zablokujPohyb();

        }

        if (this.hpLode <= 0) {
            EndScreen endScreen = new EndScreen(this.skore);
            this.hrac.zablokujPohyb();
        }
    }


    //----Raketa----
    public void kontrolaCasuRakety() {
        ArrayList<Raketa> vybuchnute = new ArrayList<>();
        for (Raketa raketa : this.rakety) {
            if (raketa.getVybuch()) {
                raketa.uberCas();
            }
            if (raketa.getCasDoVybuchu() <= 0) {
                raketa.vybuchla();
                vybuchnute.add(raketa);
            }
        }
        this.rakety.removeAll(vybuchnute);
    }

    public void kontrolaCasuRaketyStihacky() {
        ArrayList<RaketaStihacky> vybuchnute = new ArrayList<>();
        for (RaketaStihacky raketaStihacky : this.raketyStihaciek) {
            if (raketaStihacky.getVybuch()) {
                raketaStihacky.uberCas();
            }
            if (raketaStihacky.getCasDoVybuchu() <= 0) {
                raketaStihacky.vybuchla();
                vybuchnute.add(raketaStihacky);
            }
        }
        this.raketyStihaciek.removeAll(vybuchnute);
    }


    //kontrola ci raketa neopustila hracie pole
    public void kontrolaRakety() {
        for (Raketa raketa : this.rakety) {
            if (raketa.getRaketaY() < 0) {
                raketa.setDamage(0);
            }
            if (raketa.getRaketaY() < -30) {
                raketa.vybuch();
            }
        }
    }

    //kontrola ci gulka neopustila hracie pole
    public void kontrolaGulky() {
        for (Gulka gulka : this.gulky) {
            if (gulka.getGulkaY() < 0) {
                gulka.setDamage(0);
            }
            if (gulka.getGulkaY() < -30) {
                gulka.vybuch();
            }
        }
    }



    //----Gulka----
    public void kontrolaCasuGulky() {
        ArrayList<Gulka> vybuchnute = new ArrayList<>();
        for (Gulka gulka : this.gulky) {
            if (gulka.getVybuch()) {
                gulka.uberCas();
            }
            if (gulka.getCasDoVybuchu() <= 0) {
                gulka.vybuchla();
                vybuchnute.add(gulka);
            }
        }
        this.gulky.removeAll(vybuchnute);
    }


    public void kontrolaCasuGulkyStihacky() {
        ArrayList<GulkaStihacka> vybuchnute = new ArrayList<>();
        for (GulkaStihacka gulkaStihacka : this.gulkyStihacky) {
            if (gulkaStihacka.getVybuch()) {
                gulkaStihacka.uberCas();
            }
            if (gulkaStihacka.getCasDoVybuchu() <= 0) {
                gulkaStihacka.vybuchla();
                vybuchnute.add(gulkaStihacka);
            }
        }
        this.gulkyStihacky.removeAll(vybuchnute);
    }


    //--------------------------------Male Kamikadze-----------------------------------------

    //---------------------------------------------------------------------------------------
    //Male kamikadze -- Raketa

    public boolean koliziaRaketaMaleKamikadze(Raketa raketa, MaleKamikadze maleKamikadze) {

        int stredRaketyX = raketa.getRaketaX() + 5;
        int stredRaketyY = raketa.getRaketaY() + 17;

        int stredMalehoKamikadzeX = maleKamikadze.getPolohaX() + 17;
        int stredMalehoKamikadzeY = maleKamikadze.getPolohaY() + 22;

        return Math.abs(stredRaketyX - stredMalehoKamikadzeX) < 20 &&
                Math.abs(stredRaketyY - stredMalehoKamikadzeY) < 20;

    }

    public void kontrolaKolizieRaketaMaleKam() {
        ArrayList<MaleKamikadze> znicene = new ArrayList<>();


        for (Raketa raketa : this.rakety) {
            for (MaleKamikadze maleKamikadze : this.maleKamikadzeLietadla) {
                if (koliziaRaketaMaleKamikadze(raketa, maleKamikadze)) {
                    maleKamikadze.uberHP(raketa.getDamage());
                    raketa.vybuch();
                    if (maleKamikadze.getHp() <= 0) {
                        znicene.add(maleKamikadze);
                        maleKamikadze.znicenie();
                        pridajSkore(maleKamikadze.getSkore());

                    }

                }
            }

        }
        this.maleKamikadzeLietadla.removeAll(znicene);

    }


    //---------------------------------------------------------------------------------------
    //--------Male Kamikadze -- Gulka----------------------

    public boolean koliziaGulkaMaleKamikadze(Gulka gulka, MaleKamikadze maleKamikadze) {

        int stredGulkyX = gulka.getGulkaX() + 2;
        int stredGulkyY = gulka.getGulkaY() + 7;

        int stredMalehoKamikadzeX = maleKamikadze.getPolohaX() + 17;
        int stredMalehoKamikadzeY = maleKamikadze.getPolohaY() + 22;

        return Math.abs(stredGulkyX - stredMalehoKamikadzeX) < 20 &&
                Math.abs(stredGulkyY - stredMalehoKamikadzeY) < 20;

    }

    public void kontrolaKolizieGulkaMaleKamikadze() {
        ArrayList<MaleKamikadze> znicene = new ArrayList<>();

        for (Gulka gulka : this.gulky) {
            for (MaleKamikadze maleKamikadze : this.maleKamikadzeLietadla) {
                if (koliziaGulkaMaleKamikadze(gulka, maleKamikadze)) {
                    maleKamikadze.uberHP(gulka.getDamage());
                    gulka.vybuch();
                    if (maleKamikadze.getHp() <= 0) {
                        znicene.add(maleKamikadze);
                        maleKamikadze.znicenie();
                        pridajSkore(maleKamikadze.getSkore());


                    }

                }
            }

        }
        this.maleKamikadzeLietadla.removeAll(znicene);

    }


    //---------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------------

    //Male kamikadze -- Lod

    public boolean koliziaMaleKamikadzeLod(Lod lod, MaleKamikadze maleKamikadze) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = maleKamikadze.getPolohaY() + 35;

        return Math.abs(vrcholLode - vrcholBombardera) < 35;
    }

    public void kontrolaKolizieMaleKamikadzeLod() {
        ArrayList<MaleKamikadze> znicene = new ArrayList<>();


        for (MaleKamikadze maleKamikadze : this.maleKamikadzeLietadla) {
            if (koliziaMaleKamikadzeLod(this.lodHraca, maleKamikadze)) {
                maleKamikadze.animaciaVybuchu();
                if (maleKamikadze.getCasDoVybuchu() < 0) {
                    znicene.add(maleKamikadze);
                    this.lodHraca.uberHP(maleKamikadze.getDamage());
                    uberHpLode(maleKamikadze.getDamage());
                    maleKamikadze.znicenie();
                }
            }
        }
        this.maleKamikadzeLietadla.removeAll(znicene);
    }

    public void ubratieCasuMaleKamikadze() {
        for (MaleKamikadze maleKamikadze : this.maleKamikadzeLietadla) {
            if (maleKamikadze.getVybuch()) {
                maleKamikadze.uberCas();
            }
        }
    }
    //---------------------------------------------------------------------------------------


//--------------------------------Velke Kamikadze-----------------------------------------

    //---------------------------------------------------------------------------------------
    //Velke kamikadze -- Raketa

    public boolean koliziaRaketaVelkeKamikadze(Raketa raketa, VelkeKamikadze velkeKamikadze) {

        int stredRaketyX = raketa.getRaketaX() + 5;
        int stredRaketyY = raketa.getRaketaY() + 17;

        int stredVelkehoKamikadzeX = velkeKamikadze.getPolohaX() + 17;
        int stredVelkehoKamikadzeY = velkeKamikadze.getPolohaY() + 22;

        return Math.abs(stredRaketyX - stredVelkehoKamikadzeX) < 20 &&
                Math.abs(stredRaketyY - stredVelkehoKamikadzeY) < 20;

    }

    public void kontrolaKolizieRaketaVelkeKamikadze() {
        ArrayList<VelkeKamikadze> znicene = new ArrayList<>();
        for (Raketa raketa : this.rakety) {
            for (VelkeKamikadze velkeKamikadze : this.velkeKamikadzeLietadla) {
                if (koliziaRaketaVelkeKamikadze(raketa, velkeKamikadze)) {
                    velkeKamikadze.uberHP(raketa.getDamage());
                    raketa.vybuch();

                    if (velkeKamikadze.getHp() <= 0) {
                        znicene.add(velkeKamikadze);
                        velkeKamikadze.znicenie();
                        pridajSkore(velkeKamikadze.getSkore());

                    }

                }
            }

        }
        this.velkeKamikadzeLietadla.removeAll(znicene);

    }


    //---------------------------------------------------------------------------------------

    //Velke kamikadze -- Gulka

    public boolean koliziaGulkaVelkeKamikadze(Gulka gulka, VelkeKamikadze velkeKamikadze) {

        int stredGulkyX = gulka.getGulkaX() + 2;
        int stredGulkyY = gulka.getGulkaY() + 7;

        int stredMalehoKamikadzeX = velkeKamikadze.getPolohaX() + 17;
        int stredMalehoKamikadzeY = velkeKamikadze.getPolohaY() + 22;

        return Math.abs(stredGulkyX - stredMalehoKamikadzeX) < 20 &&
                Math.abs(stredGulkyY - stredMalehoKamikadzeY) < 20;

    }

    public void kontrolaKolizieGulkaVelkeKamikadze() {
        ArrayList<VelkeKamikadze> znicene = new ArrayList<>();
        for (Gulka gulka : this.gulky) {
            for (VelkeKamikadze velkeKamikadze : this.velkeKamikadzeLietadla) {
                if (koliziaGulkaVelkeKamikadze(gulka, velkeKamikadze)) {
                    velkeKamikadze.uberHP(gulka.getDamage());
                    gulka.vybuch();
                    if (velkeKamikadze.getHp() <= 0) {
                        znicene.add(velkeKamikadze);
                        velkeKamikadze.znicenie();
                        pridajSkore(velkeKamikadze.getSkore());
                    }
                }
            }
        }
        this.velkeKamikadzeLietadla.removeAll(znicene);
    }


    //---------------------------------------------------------------------------------------

    //Velke kamikadze -- Lod

    public boolean koliziaVelkeKamikadzeLod(Lod lod, VelkeKamikadze velkeKamikadze) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = velkeKamikadze.getPolohaY() + 35;

        return Math.abs(vrcholLode - vrcholBombardera) < 35;
    }

    public void kontrolaKolizieVelkeKamikadzeLod() {
        ArrayList<VelkeKamikadze> znicene = new ArrayList<>();


        for (VelkeKamikadze velkeKamikadze : this.velkeKamikadzeLietadla) {
            if (koliziaVelkeKamikadzeLod(this.lodHraca, velkeKamikadze)) {
                velkeKamikadze.animaciaVybuchu();
                if (velkeKamikadze.getCasDoVybuchu() < 0) {
                    znicene.add(velkeKamikadze);
                    this.lodHraca.uberHP(velkeKamikadze.getDamage());
                    uberHpLode(velkeKamikadze.getDamage());
                    velkeKamikadze.znicenie();
                }
            }
        }
        this.velkeKamikadzeLietadla.removeAll(znicene);
    }

    public void ubratieCasuVelkeKamikadze() {
        for (VelkeKamikadze velkeKamikadze : this.velkeKamikadzeLietadla) {
            if (velkeKamikadze.getVybuch()) {
                velkeKamikadze.uberCas();


            }
        }


    }
    //---------------------------------------------------------------------------------------


    //----------------------------Bombarder------------------------------------------------


    //---------------------------------------------------------------------------------------
    // Bombarder -- Lod

    public boolean koliziaBombarderLod(Lod lod, Bombarder bombarder) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = bombarder.getPolohaY() + 100;
        return Math.abs(vrcholLode - vrcholBombardera) < 15;
    }

    public void kontrolaKolizieBombarderLod() {
        for (Bombarder bombarder : this.bombardery) {
            if (koliziaBombarderLod(this.lodHraca, bombarder)) {
                if (!bombarder.getZhodenaBomba()) {
                    Bomba bomba = new Bomba(bombarder.getPolohaX() + 30, bombarder.getPolohaY() + 70);
                    bombarder.zhodBombu();
                    this.Bomby.add(bomba);
                }
            }
        }
    }
    //---------------------------------------------------------------------------------------


    public void ubratieCasuBomby() {
        for (Bomba bomba : this.Bomby) {
            bomba.uberCas();
        }
    }

    public void vybuchBomby() {
        ArrayList<Bomba> znicene = new ArrayList<>();
        for (Bomba bomba : this.Bomby) {
            if (bomba.getCasDoVybuchu() == 50) {
                bomba.zmenObrazokNavybuch();
            }
            if (bomba.getCasDoVybuchu() == 0) {
                this.lodHraca.uberHP(bomba.getDamage());
                uberHpLode(bomba.getDamage());
                bomba.znicenie();
                znicene.add(bomba);
            }
        }
        this.Bomby.removeAll(znicene);
    }


    public void ubratieCasuBombardera() {
        for (Bombarder bombarder : this.bombardery) {
            if (bombarder.getZhodenaBomba()) {
                bombarder.uberCas();
            }

        }
    }

    public void znicenieBombardera() {
        ArrayList<Bombarder> znicene = new ArrayList<>();
        for (Bombarder bombarder : this.bombardery) {
            if (bombarder.getCasDoPreletenie() < 0) {
                if (!bombarder.getJeZniceny()) {
                    bombarder.znicenie();
                    znicene.add(bombarder);
                }
            }
        }
        this.bombardery.removeAll(znicene);
    }

    //---------------------------------------------------------------------------------------
    // Bombarder -- raketa


    public boolean koliziaRaketaBombarder(Raketa raketa, Bombarder bombarder) {
        int stredRaketyX = raketa.getRaketaX() + 5;
        int stredRaketyY = raketa.getRaketaY() + 17;
        int stredBombarderaX = bombarder.getPolohaX() + 30;
        int stredBombarderaY = bombarder.getPolohaY() + 50;
        return Math.abs(stredRaketyX - stredBombarderaX) < 50 && Math.abs(stredRaketyY - stredBombarderaY) < 50;
    }

    public void kontrolaKolizieRaketaBombarder() {
        ArrayList<Bombarder> znicene = new ArrayList<>();
        for (Raketa raketa : this.rakety) {
            for (Bombarder bombarder : this.bombardery) {
                if (koliziaRaketaBombarder(raketa, bombarder)) {
                    bombarder.uberHP(raketa.getDamage());
                    raketa.vybuch();
                    if (bombarder.getHp() <= 0) {
                        znicene.add(bombarder);
                        bombarder.znicenie();
                        pridajSkore(bombarder.getSkore());
                    }
                }
            }
        }
        this.bombardery.removeAll(znicene);
    }

    //---------------------------------------------------------------------------------------


    //Bombarder -- Gulka

    public boolean koliziaGulkaBombarder(Gulka gulka, Bombarder bombarder) {
        int stredGulkyX = gulka.getGulkaX() + 2;
        int stredGulkyY = gulka.getGulkaY() + 7;
        int stredBombarderaX = bombarder.getPolohaX() + 30;
        int stredBombarderaY = bombarder.getPolohaY() + 50;
        return Math.abs(stredGulkyX - stredBombarderaX) < 50 && Math.abs(stredGulkyY - stredBombarderaY) < 50;
    }

    public void kontrolaKolizieGulkaBombarder() {
        ArrayList<Bombarder> znicene = new ArrayList<>();
        for (Gulka gulka : this.gulky) {
            for (Bombarder bombarder : this.bombardery) {
                if (koliziaGulkaBombarder(gulka, bombarder)) {
                    bombarder.uberHP(gulka.getDamage());
                    gulka.vybuch();
                    if (bombarder.getHp() <= 0) {
                        znicene.add(bombarder);
                        bombarder.znicenie();
                        pridajSkore(bombarder.getSkore());
                    }
                }
            }
        }
        this.bombardery.removeAll(znicene);
    }


    //------Stihacka----


    //Stihacka -- Raketa
    public boolean koliziaRaketaStihacka(Raketa raketa, Stihacka stihacka) {
        int stredRaketyX = raketa.getRaketaX() + 5;
        int stredRaketyY = raketa.getRaketaY() + 17;
        int stredStihackyX = stihacka.getPolohaX() + 30;
        int stredStihackyY = stihacka.getPolohaY() + 10;
        return Math.abs(stredRaketyX - stredStihackyX) < 40 && Math.abs(stredRaketyY - stredStihackyY) < 40;
    }

    public void kontrolaKolizieRaketaStihacka() {
        ArrayList<Stihacka> znicene = new ArrayList<>();
        for (Raketa raketa : this.rakety) {
            for (Stihacka stihacka : this.stihacky) {
                if (koliziaRaketaStihacka(raketa, stihacka)) {
                    stihacka.uberHP(raketa.getDamage());
                    raketa.vybuch();
                    if (stihacka.getHp() <= 0) {
                        znicene.add(stihacka);
                        stihacka.znicenie();
                        pridajSkore(stihacka.getSkore());
                    }
                }
            }
        }
        this.stihacky.removeAll(znicene);
    }

    //-------------------------------------------------------------------------------------
    //Stihacka -- Gulka

    public boolean koliziaGulkaStihacka(Gulka gulka, Stihacka stihacka) {
        int stredGulkyX = gulka.getGulkaX() + 2;
        int stredGulkyY = gulka.getGulkaY() + 7;
        int stredStihackyX = stihacka.getPolohaX() + 30;
        int stredStihackyY = stihacka.getPolohaY() + 10;
        return Math.abs(stredGulkyX - stredStihackyX) < 40 && Math.abs(stredGulkyY - stredStihackyY) < 40;
    }

    public void kontrolaKolizieGulkaStihacka() {
        ArrayList<Stihacka> znicene = new ArrayList<>();
        for (Gulka gulka : this.gulky) {
            for (Stihacka stihacka : this.stihacky) {
                if (koliziaGulkaStihacka(gulka, stihacka)) {
                    stihacka.uberHP(gulka.getDamage());
                    gulka.vybuch();
                    if (stihacka.getHp() <= 0) {
                        znicene.add(stihacka);
                        stihacka.znicenie();
                        pridajSkore(stihacka.getSkore());
                    }
                }
            }
        }
        this.stihacky.removeAll(znicene);
    }

    public void kontrolaPreleteniaStihacky() {
        for (Stihacka stihacka : this.stihacky) {
            if (stihacka.getPolohaY() > 670) {
                stihacka.preletelaLod();
            }
        }
    }

    public void ubratieCasuStihacky() {
        for (Stihacka stihacka : this.stihacky) {
            if (stihacka.getPreletelaLod()) {
                stihacka.uberCas();
            }
        }
    }

    public void znicenieStihacky() {
        ArrayList<Stihacka> znicene = new ArrayList<>();
        for (Stihacka stihacka : this.stihacky) {
            if (stihacka.getCasDoPreletenie() < 0) {
                if (!stihacka.getJeZniceny()) {
                    stihacka.znicenie();
                    znicene.add(stihacka);
                }
            }
        }
        this.stihacky.removeAll(znicene);
    }

    public void raketyStihacky() {
        for (Stihacka stihacka : this.stihacky) {
            if (stihacka.getPolohaY() > stihacka.getPoziciaYnaAktivovanieZbrane()) {
                if (!stihacka.getAktivujZbran()) {
                    stihacka.aktivujZbran();
                }
            }
            if (stihacka.getAktivujZbran()) {
                if (stihacka.getPocetStrielRakiet() > 0) {
                    if (stihacka.getTimeOutRakiet() == 60) {
                        RaketaStihacky raketaStihacky = new RaketaStihacky(stihacka.getPolohaX(), stihacka.getPolohaY());
                        this.raketyStihaciek.add(raketaStihacky);
                        stihacka.uberTimeOutRakiet();
                        stihacka.uberRaketu();
                        System.out.println("Este ma: " + stihacka.getPocetStrielRakiet());
                    } else {
                        stihacka.uberTimeOutRakiet();
                    }
                }
            }
        }
    }

    public void kontrolaKolizieRaketyStihackyLode(Lod lodHraca) {
        for (RaketaStihacky raketaStihacky : this.raketyStihaciek) {
            if (raketaStihacky.getRaketaY() > lodHraca.getPolohaY() - 20) {
                lodHraca.uberHP(raketaStihacky.getDamage());
                uberHpLode(raketaStihacky.getDamage());
                raketaStihacky.vybuch();
            }
        }
    }

    public void kontrolaKolizieRakiet(){
        for (RaketaStihacky raketaStihacky : this.raketyStihaciek){
            for (Raketa raketa : this.rakety){
                if (Math.abs(raketaStihacky.getRaketaY() - raketa.getRaketaY()) < 10 && Math.abs(raketaStihacky.getRaketaX() - raketa.getRaketaX()) < 10){
                    raketa.vybuch();
                    raketaStihacky.vybuch();
                }
            }
        }
    }

    public void kontrolaKolizieRaketyStihackyGulky(){
        for (Gulka gulka : this.gulky){
            for (RaketaStihacky raketaStihacky : this.raketyStihaciek){
            if (Math.abs(gulka.getGulkaY() - raketaStihacky.getRaketaY()) < 10 && Math.abs(gulka.getGulkaX() - raketaStihacky.getRaketaX()) < 10){
                    raketaStihacky.vybuch();
                    gulka.vybuch();
                }
            }
        }
    }

    public void gulkyStihacky() {
        for (Stihacka stihacka : this.stihacky) {
            if (stihacka.getPolohaY() > stihacka.getPoziciaYnaAktivovanieZbrane()) {
                if (!stihacka.getAktivujZbran()) {
                    stihacka.aktivujZbran();
                }
            }
            if (stihacka.getAktivujZbran()) {
                if (stihacka.getPocetStrielGuliek() > 0) {
                    if (stihacka.getTimeOutGuliek() == 8) {
                        GulkaStihacka gulkaStihacka = new GulkaStihacka(stihacka.getPolohaX(), stihacka.getPolohaY());
                        this.gulkyStihacky.add(gulkaStihacka);
                        stihacka.uberTimeOutGuliek();
                        stihacka.uberGulku();
                        System.out.println("Este ma: " + stihacka.getPocetStrielGuliek());
                    } else {
                        stihacka.uberTimeOutGuliek();
                    }
                }
            }
        }
    }

    public void kontrolaKolizieGulkyStihackyLode(Lod lodHraca) {
        for (GulkaStihacka gulkaStihacka : this.gulkyStihacky) {
            if (gulkaStihacka.getGulkaY() > lodHraca.getPolohaY() - 20) {
                lodHraca.uberHP(gulkaStihacka.getDamage());
                uberHpLode(gulkaStihacka.getDamage());
                gulkaStihacka.vybuch();
            }
        }
    }

    public void kontrolaKolizieRaketyGulkyStihacky(){
        for (GulkaStihacka gulkaStihacka : this.gulkyStihacky){
            for (Raketa raketa : this.rakety){
                if (Math.abs(gulkaStihacka.getGulkaY() - raketa.getRaketaY()) < 10 && Math.abs(gulkaStihacka.getGulkaX() - raketa.getRaketaX()) < 10){
                    raketa.vybuch();
                    gulkaStihacka.vybuch();
                }
            }
        }
    }

    public void kontrolaKolizieGulkyStihackyGulky(){
        for (Gulka gulka : this.gulky){
            for (GulkaStihacka gulkaStihacka : this.gulkyStihacky){
                if (Math.abs(gulka.getGulkaY() - gulkaStihacka.getGulkaY()) < 10 && Math.abs(gulka.getGulkaX() - gulkaStihacka.getGulkaX()) < 10){
                    gulkaStihacka.vybuch();
                    gulka.vybuch();
                }
            }
        }
    }

    public void pridajSkore(int skore){
        this.skore += skore;
        this.plocha.nastavSkore(this.skore);
    }

    public void uberHpLode(int hp){
        this.hpLode -= hp;
        this.plocha.nastavHpLode(this.hpLode);
    }

    public boolean jeLevelDokonceny(){
        return this.levelDokonceny;
    }

    private boolean uzNiesuZiadneLietadla(){
        return this.maleKamikadzeLietadla.isEmpty() && this.velkeKamikadzeLietadla.isEmpty() && this.bombardery.isEmpty() && this.stihacky.isEmpty();
    }

    public void nastavHraca(Hrac hrac){
        this.hrac = hrac;
    }
}


