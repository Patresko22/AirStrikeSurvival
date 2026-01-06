import java.util.ArrayList;



/*
* Trieda GameManazer zabezpecuje hlavnu logiku hry.
* Udrziava v sebe zoznamy vsetky hernych objektov.
* Kontroluje kolízie.
* Odpocitava casovace.
* Spravuje hp lode.
* Spravuje skore hraca.
* Vyhodnocuje koniec hry.
* */

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

    /*
    * Vytvori manazera hry a inicializuje vsetky zoznamy hernych objektov.
    * Nastavi pociatocne hodnoty skore, hp lode.
    * @param plocha je vlastne objekt hracej plochy na ktory sa vypisuje skore a hp lode.
    * @param ziaciatocneHpLode je pociatocne hp lode na zaciatku levelu
    */


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

    /*
    * Prida raketu vystrelenu hracom do zoznamu spravovanych rakiet.
    */

    public void pridajRaketu(Raketa raketa) {
        this.rakety.add(raketa);
    }

    /*
     * Prida gulku vystrelenu hracom do zoznamu spravovanych guliek.
    */

    public void pridajGulku(Gulka gulka) {
        this.gulky.add(gulka);
    }

    /*
     * Prida Bombarder do zoznamu spravovanych bombarderov.
    */

    public void pridajBombarder(Bombarder bombarder) {
        this.bombardery.add(bombarder);
    }

    /*
     * Prida Stihacku do zoznamu spravovanych stihaciek.
    */

    public void pridajStihacku(Stihacka stihacka) {
        this.stihacky.add(stihacka);
    }

    /*
     * Prida male kamikadze lietadlo do zoznamu spravovanych malych kamikadze lietadiel.
     */

    public void pridajMaleKamikadze(MaleKamikadze maleKamikadze) {
        this.maleKamikadzeLietadla.add(maleKamikadze);
    }

    /*
     * Prida velke kamikadze lietadlo do zoznamu spravovanych velkych kamikadze lietadiel.
     */

    public void pridajVelkeKamikadze(VelkeKamikadze velkeKamikadze) {
        this.velkeKamikadzeLietadla.add(velkeKamikadze);
    }

    /*
    * Nastavi lod hraca a inicializuje jej HP podľa nastavenia manazera hry.
    * @param lod je lod hraca
    * */

    public void pridajLod(Lod lod) {
        this.lodHraca = lod;
        this.lodHraca.setHp(this.hpLode);
    }

    /*
    * Je to tik volaný manazerom.
    * Spusta kontrolu projektilov, casovacov, kolizii, vybuchov a vyhodnotenia konca levelu.
    * */

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


        //Kontrola rakety stihacky
        raketyStihacky();
        kontrolaKolizieRaketyStihackyLode(lodHraca);
        kontrolaCasuRaketyStihacky();
        kontrolaKolizieRakiet();
        kontrolaKolizieRaketyStihackyGulky();

        //Kontrola gulky stihacky
        gulkyStihacky();
        kontrolaKolizieGulkyStihackyLode(lodHraca);
        kontrolaCasuGulkyStihacky();
        kontrolaKolizieRaketyGulkyStihacky();
        kontrolaKolizieGulkyStihackyGulky();


        //Vyhodnotenie
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





   /*
   * Spravuje casovanie rakiet hraca po ich vybuchu.
   * Po skonceni animacie vybuchu ich odstrani.
   * */
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

    /*
    * Spravuje casovanie rakiet stihacky.
    * Po skonceni animacie vybuchu ich odstrani.
    * */

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



    /*
    * Kontroluje ci hracova raketa neopustila hernu plochu.
    * Ak opustila hernu plochu tak jej nastavi damage na 0 aby hrac nepalil do prazdna a nahodou netrafila
    * lietadla ktore uz su spawnute ale este niesu viditelne na hracej ploche. Potom ju necha vybuchnut.
    * */
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

    /*
     * Kontroluje ci hracova gulka neopustila hernu plochu.
     * Ak opustila hernu plochu tak jej nastavi damage na 0 aby hrac nepalil do prazdna a nahodou netrafila
     * lietadla ktore uz su spawnute ale este niesu viditelne na hracej ploche. Potom ju necha vybuchnut.
     * */
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



    /*
    * Spravuje casovac guliek hraca po ich vybuchu.
    * Po skonceni animacie ich odstrani.
    * */
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

    /*
     * Spravuje casovac guliek stihacky po ich vybuchu.
     * Po skonceni animacie ich odstrani.
     * */

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



    /*
    * Zisti koliziu hracskej rakety s malym kamikadze lietadlom podla vzdialenosti ich stredov.
    *
    * @param raketa je hracska raketa.
    * @param maleKamikadze je male kamikadze lietadlo.
    * @return vrati true ak doslo ku kolizii, inak false.
    * */

    public boolean koliziaRaketaMaleKamikadze(Raketa raketa, MaleKamikadze maleKamikadze) {

        int stredRaketyX = raketa.getRaketaX() + 5;
        int stredRaketyY = raketa.getRaketaY() + 17;

        int stredMalehoKamikadzeX = maleKamikadze.getPolohaX() + 17;
        int stredMalehoKamikadzeY = maleKamikadze.getPolohaY() + 22;

        return Math.abs(stredRaketyX - stredMalehoKamikadzeX) < 20 && Math.abs(stredRaketyY - stredMalehoKamikadzeY) < 20;
    }

    /*
    * Skontroluje kolizie hracsky riakiet a malych kamikadze lietadiel.
    * Aplikuje poskodenie a v pripade znicenia prida skore.
    * */

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

    /*
     * Zisti koliziu hracskej gulky s malym kamikadze lietadlom podla vzdialenosti ich stredov.
     *
     * @param gulka je hracska gulka.
     * @param maleKamikadze je male kamikadze lietadlo.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */



    public boolean koliziaGulkaMaleKamikadze(Gulka gulka, MaleKamikadze maleKamikadze) {

        int stredGulkyX = gulka.getGulkaX() + 2;
        int stredGulkyY = gulka.getGulkaY() + 7;

        int stredMalehoKamikadzeX = maleKamikadze.getPolohaX() + 17;
        int stredMalehoKamikadzeY = maleKamikadze.getPolohaY() + 22;

        return Math.abs(stredGulkyX - stredMalehoKamikadzeX) < 20 && Math.abs(stredGulkyY - stredMalehoKamikadzeY) < 20;
    }

    /*
     * Skontroluje kolizie hracsky guliek a malych kamikadze lietadiel.
     * Aplikuje poskodenie a v pripade znicenia prida skore.
     * */

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


    /*
     * Zisti koliziu hracskej Lode s malym kamikadze lietadlom.
     *
     * @param lod je hracova lod.
     * @param maleKamikadze je male kamikadze lietadlo.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */




    public boolean koliziaMaleKamikadzeLod(Lod lod, MaleKamikadze maleKamikadze) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = maleKamikadze.getPolohaY() + 35;

        return Math.abs(vrcholLode - vrcholBombardera) < 35;
    }

    /*
     * Skontroluje kolizie hracovej lode a malych kamikadze lietadiel.
     * Po kolizii spusti animaciu vybuchu a po skonceni aplikuje damage na lod.
     * */

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

    /*
    * Odpocitava cas do skoncenia vybuchu malých kamikadze lietadiel v pripade ze uz vybuchujú.
    * */

    public void ubratieCasuMaleKamikadze() {
        for (MaleKamikadze maleKamikadze : this.maleKamikadzeLietadla) {
            if (maleKamikadze.getVybuch()) {
                maleKamikadze.uberCas();
            }
        }
    }

    /*
     * Zisti koliziu hracskej rakety s velkym kamikadze lietadlom podla vzdialenosti ich stredov.
     *
     * @param raketa je hracska raketa.
     * @param velkeKamikadze je velke kamikadze lietadlo.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */

    public boolean koliziaRaketaVelkeKamikadze(Raketa raketa, VelkeKamikadze velkeKamikadze) {

        int stredRaketyX = raketa.getRaketaX() + 5;
        int stredRaketyY = raketa.getRaketaY() + 17;

        int stredVelkehoKamikadzeX = velkeKamikadze.getPolohaX() + 17;
        int stredVelkehoKamikadzeY = velkeKamikadze.getPolohaY() + 22;

        return Math.abs(stredRaketyX - stredVelkehoKamikadzeX) < 20 && Math.abs(stredRaketyY - stredVelkehoKamikadzeY) < 20;

    }

    /*
     * Skontroluje kolizie hracsky riakiet a velkych kamikadze lietadiel.
     * Aplikuje poskodenie a v pripade znicenia prida skore.
     * */

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


    /*
     * Zisti koliziu hracskej gulky s velkym kamikadze lietadlom podla vzdialenosti ich stredov.
     *
     * @param gulka je hracska gulka.
     * @param velkeKamikadze je velke kamikadze lietadlo.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */

    public boolean koliziaGulkaVelkeKamikadze(Gulka gulka, VelkeKamikadze velkeKamikadze) {

        int stredGulkyX = gulka.getGulkaX() + 2;
        int stredGulkyY = gulka.getGulkaY() + 7;

        int stredMalehoKamikadzeX = velkeKamikadze.getPolohaX() + 17;
        int stredMalehoKamikadzeY = velkeKamikadze.getPolohaY() + 22;

        return Math.abs(stredGulkyX - stredMalehoKamikadzeX) < 20 &&
                Math.abs(stredGulkyY - stredMalehoKamikadzeY) < 20;

    }


    /*
     * Skontroluje kolizie hracsky guliek a velkych kamikadze lietadiel.
     * Aplikuje poskodenie a v pripade znicenia prida skore.
     * */


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


    /*
     * Zisti koliziu hracskej Lode s velkym kamikadze lietadlom.
     *
     * @param lod je hracova lod.
     * @param velkeKamikadze je velke kamikadze lietadlo.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */

    public boolean koliziaVelkeKamikadzeLod(Lod lod, VelkeKamikadze velkeKamikadze) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = velkeKamikadze.getPolohaY() + 35;

        return Math.abs(vrcholLode - vrcholBombardera) < 35;
    }

    /*
     * Skontroluje kolizie hracovej lode a velkych kamikadze lietadiel.
     * Po kolizii spusti animaciu vybuchu a po skonceni aplikuje damage na lod.
     * */


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

    /*
     * Odpocitava cas do skoncenia vybuchu velkych kamikadze lietadiel v pripade ze uz vybuchujú.
     * */

    public void ubratieCasuVelkeKamikadze() {
        for (VelkeKamikadze velkeKamikadze : this.velkeKamikadzeLietadla) {
            if (velkeKamikadze.getVybuch()) {
                velkeKamikadze.uberCas();
            }
        }


    }


    /*
    * Zisti koliziu bombardera s hracovou lodou na zaklade vertikalnej vzdialenosti.
    * @param lod je hracova lod
    * @param bombarder je bombarder
    * @return vrati true ak doslo ku kolizii, inak false
    * */

    public boolean koliziaBombarderLod(Lod lod, Bombarder bombarder) {
        int vrcholLode = lod.getPolohaY() + 30;
        int vrcholBombardera = bombarder.getPolohaY() + 100;
        return Math.abs(vrcholLode - vrcholBombardera) < 15;
    }

    /*
    * Skontroluje ci bombarder dosiahol poziciu na zhodenie bomby.
    * Ak bombu este nezhodil tak ju zhodi na jeho aktualnej pozicii a prida do zoznamu bomb.
    * */

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


    /*
    * Odpocitava cas do vybuchu bomby.
    * */

    public void ubratieCasuBomby() {
        for (Bomba bomba : this.Bomby) {
            bomba.uberCas();
        }
    }

    /*
    * Spracuvava logiku vybuchu bomby.
    * Najskor zobrazi animaciu vybuchu a potom uberie hp lodi.
    * */

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


    /*
    * Ubera čas pre znicenie bombardera v pripade ze zhodil bombu.
    * */
    public void ubratieCasuBombardera() {
        for (Bombarder bombarder : this.bombardery) {
            if (bombarder.getZhodenaBomba()) {
                bombarder.uberCas();
            }

        }
    }

    /*
    * Znici bombarder ak mu uz vyprsal cas po zhodeni bomby.
    * */

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


    /*
     * Zisti koliziu hracskej rakety s bombarderom podla vzdialenosti ich stredov.
     *
     * @param raketa je hracska raketa.
     * @param bombarder je bombarder.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */



    public boolean koliziaRaketaBombarder(Raketa raketa, Bombarder bombarder) {
        int stredRaketyX = raketa.getRaketaX() + 5;
        int stredRaketyY = raketa.getRaketaY() + 17;
        int stredBombarderaX = bombarder.getPolohaX() + 30;
        int stredBombarderaY = bombarder.getPolohaY() + 50;
        return Math.abs(stredRaketyX - stredBombarderaX) < 50 && Math.abs(stredRaketyY - stredBombarderaY) < 50;
    }

    /*
     * Skontroluje kolizie hracsky riakiet a bombardera.
     * Aplikuje poskodenie a v pripade znicenia prida skore.
     * */



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

    /*
     * Zisti koliziu hracskej gulky s bombarderom podla vzdialenosti ich stredov.
     *
     * @param raketa je hracska gulka.
     * @param bombarder je bombarder.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */


    public boolean koliziaGulkaBombarder(Gulka gulka, Bombarder bombarder) {
        int stredGulkyX = gulka.getGulkaX() + 2;
        int stredGulkyY = gulka.getGulkaY() + 7;
        int stredBombarderaX = bombarder.getPolohaX() + 30;
        int stredBombarderaY = bombarder.getPolohaY() + 50;
        return Math.abs(stredGulkyX - stredBombarderaX) < 50 && Math.abs(stredGulkyY - stredBombarderaY) < 50;
    }

    /*
     * Skontroluje kolizie hracsky guliek a bombardera.
     * Aplikuje poskodenie a v pripade znicenia prida skore.
     * */


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


    /*
     * Zisti koliziu hracskej rakety so stihackou podla vzdialenosti ich stredov.
     *
     * @param raketa je hracska raketa.
     * @param stihacka je stihacka.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */

    public boolean koliziaRaketaStihacka(Raketa raketa, Stihacka stihacka) {
        int stredRaketyX = raketa.getRaketaX() + 5;
        int stredRaketyY = raketa.getRaketaY() + 17;
        int stredStihackyX = stihacka.getPolohaX() + 30;
        int stredStihackyY = stihacka.getPolohaY() + 10;
        return Math.abs(stredRaketyX - stredStihackyX) < 40 && Math.abs(stredRaketyY - stredStihackyY) < 40;
    }

    /*
     * Skontroluje kolizie hracsky riakiet a stihacky.
     * Aplikuje poskodenie a v pripade znicenia prida skore.
     * */


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

    /*
     * Zisti koliziu hracskej gulky so stihackou podla vzdialenosti ich stredov.
     *
     * @param raketa je hracska gulka.
     * @param stihacka je stihacka.
     * @return vrati true ak doslo ku kolizii, inak false.
     * */

    public boolean koliziaGulkaStihacka(Gulka gulka, Stihacka stihacka) {
        int stredGulkyX = gulka.getGulkaX() + 2;
        int stredGulkyY = gulka.getGulkaY() + 7;
        int stredStihackyX = stihacka.getPolohaX() + 30;
        int stredStihackyY = stihacka.getPolohaY() + 10;
        return Math.abs(stredGulkyX - stredStihackyX) < 40 && Math.abs(stredGulkyY - stredStihackyY) < 40;
    }

    /*
     * Skontroluje kolizie hracsky guliek a stihacky.
     * Aplikuje poskodenie a v pripade znicenia prida skore.
     * */

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

    /*
    * kontroluje ci stihacka preletela lod.
    * Ak ano oznaci ju ako preletenu.
    * */

    public void kontrolaPreleteniaStihacky() {
        for (Stihacka stihacka : this.stihacky) {
            if (stihacka.getPolohaY() > 670) {
                stihacka.preletelaLod();
            }
        }
    }

    /*
    * Odpocitava cas do odstranenia stihacky v pripade ze uz preletela lod.
    * */

    public void ubratieCasuStihacky() {
        for (Stihacka stihacka : this.stihacky) {
            if (stihacka.getPreletelaLod()) {
                stihacka.uberCas();
            }
        }
    }

    /*
    * Odstrani stihacky ktorym vyprsal cas do preletenia a este neboli znicene.
    * */

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

    /*
    * Spravuje strielanie rakiet zo stihacky po aktivovani zbrane.
    * Pri splneni podmienok vytvori raketu na pozicii stihacky a prida ju na zoznam.
    * */

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

    /*
    * Kontroluje ci raketa stihacky narazila do lode.
    * Ak ano tak aplikuje damage a necha raketu vybuchnut.
    * @param lodHraca je lod hraca
    * */

    public void kontrolaKolizieRaketyStihackyLode(Lod lodHraca) {
        for (RaketaStihacky raketaStihacky : this.raketyStihaciek) {
            if (raketaStihacky.getRaketaY() > lodHraca.getPolohaY() - 20) {
                lodHraca.uberHP(raketaStihacky.getDamage());
                uberHpLode(raketaStihacky.getDamage());
                raketaStihacky.vybuch();
            }
        }
    }

    /*
    * Kontroluje kolizie medzi hracskymi raketami a raketami stihaciek.
    * Pri strete necha obe rakety vybuchnut.
    * */

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

    /*
    * Kontroluje kolizie medzi hracskymi gulkami a raketami stihaciek.
    * Pri strete necha raketu stihacky a aj gulku hraca vybuchnut.
    * */

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

    /*
     * Spravuje strielanie guliek zo stihacky po aktivovani zbrane.
     * Pri splneni podmienok vytvori gulku na pozicii stihacky a prida ju na zoznam.
     * */


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

    /*
     * Kontroluje ci gulka stihacky narazila do lode.
     * Ak ano tak aplikuje damage a necha gulku vybuchnut.
     * @param lodHraca je lod hraca
     * */

    public void kontrolaKolizieGulkyStihackyLode(Lod lodHraca) {
        for (GulkaStihacka gulkaStihacka : this.gulkyStihacky) {
            if (gulkaStihacka.getGulkaY() > lodHraca.getPolohaY() - 20) {
                lodHraca.uberHP(gulkaStihacka.getDamage());
                uberHpLode(gulkaStihacka.getDamage());
                gulkaStihacka.vybuch();
            }
        }
    }

    /*
     * Kontroluje kolizie medzi hracskymi raketami a gulkami stihaciek.
     * Pri strete necha raketu hraca a aj gulku stihacky vybuchnut.
     * */

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

    /*
     * Kontroluje kolizie medzi hracskymi gulkami a gulkami stihaciek.
     * Pri strete necha obe gulky vybuchnut.
     * */

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

    /*
    * Prida ziskane skore k celkovemu skore.
    * @param skore je pocet skore ktore ma k celkovemu skore pridat.
    * */

    public void pridajSkore(int skore){
        this.skore += skore;
        this.plocha.nastavSkore(this.skore);
    }

    /*
    * Odoberie hp lode a aktualizuje zobrazenie na hracej ploche.
    * */

    public void uberHpLode(int hp){
        this.hpLode -= hp;
        this.plocha.nastavHpLode(this.hpLode);
    }

    /*
    * Informuje ci bol level uspesne dokonceny v pripade ze už niesu ziadne nepriatelske lietadla.
    * @return vrati true ak je level dokonceny, inak false*/

    public boolean jeLevelDokonceny(){
        return this.levelDokonceny;
    }

    /*
    * Zisti ci uz niesu v hre ziadne nepriatelske lietadla.
    * @return vrati true ak su vsetky zoznami lietadiel prazdne, inak false.*/

    private boolean uzNiesuZiadneLietadla(){
        return this.maleKamikadzeLietadla.isEmpty() && this.velkeKamikadzeLietadla.isEmpty() && this.bombardery.isEmpty() && this.stihacky.isEmpty();
    }

    /*
    * Nastavi referenciu na hraca.
    * @param hrac je objekt hrac
    * */

    public void nastavHraca(Hrac hrac){
        this.hrac = hrac;
    }
}


