import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import java.util.Random;

/*
* Trieda stihacka reprezentuje nepriatelsku stihacku.
* Stiahcka sa pohybuje smerom nadol.
* Po dosiahnuti urcitej pozicie aktivuje zbrane,
* bud rakety alebo gulky a utoci na lod.
* */

public class Stihacka {
    private Obrazok obrazokStihacky;
    private int hp = 50;
    private int polohaX;
    private int polohaY;
    private int casDoPreletenie = 100;
    private boolean jeZniceny = false;
    private int pocetStrielGuliek;
    private int pocetStrielRakiet;
    private int timeOutGuliek = 8;
    private int timeOutRakiet = 60;
    private boolean preletelaLod = false;
    private boolean aktivujZbran = false;
    private int poziciaYnaAktivovanieZbrane;
    private TypZbraneStihacky typZbraneStihacky;
    private int skore = 5;


    /*
    * Vytvori stihacku na zadanej pozicii.
    * Typ zbrane, pocet striel a Aktivacna pozicia su zvolene nahodne.
    * @param polohaX je pociatocna suradnica x pozicie stihacky
    * @param polohaY je pociatocna suradnica y pozicie stihacky
    * */

    public Stihacka(int polohaX, int polohaY) {
        Random random = new Random();

        int typZbrane = random.nextInt(0,3);
        if (typZbrane < 2){
            this.pocetStrielRakiet = random.nextInt(2, 5);
            this.pocetStrielGuliek = 0;
            System.out.println("Stihacka ma rakiet: " + this.pocetStrielRakiet );
            this.typZbraneStihacky = TypZbraneStihacky.Rakety;
        }
        if (typZbrane == 2){
            this.pocetStrielGuliek = random.nextInt(7, 15);
            this.pocetStrielRakiet = 0;
            System.out.println("Stihacka ma guliek: " + this.pocetStrielGuliek );
            this.typZbraneStihacky = TypZbraneStihacky.Gulomet;
        }
        this.poziciaYnaAktivovanieZbrane = random.nextInt(0, 300);
        //velkosť obrazka je 60 x 50
        this.obrazokStihacky = new Obrazok("assets/Stihacka.png");
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.obrazokStihacky.zmenPolohu(polohaX, polohaY);
        this.obrazokStihacky.zobraz();
        new Manazer().spravujObjekt(this);
    }

    /*
    * Metoda sa vola kazdy tik.
    * Posuva Stihacku smerom nadol.
    * */

    public void tik() {
        this.obrazokStihacky.posunZvisle(1);
        this.polohaY += 1;
    }

    /*
     * Vrati aktualnu suradnicu x pozicie stihacky.
     * @return vrati poziciu x stihacky.
     * */

    public int getPolohaX () {
        return this.polohaX;
    }

    /*
     * Vrati aktualnu suradnicu y pozicie stihacky.
     * @return vrati poziciu y stihacky.
     * */

    public int getPolohaY () {
        return this.polohaY;
    }


    /*
     * Spusti vybuch rakety.
     * Zmeni obrazok na vybuch.
     * Skryje obrazok.
     * Oznaci ju ako znicenu.
     * */

    public void znicenie () {
        System.out.println("znicena Stihacka");
        this.obrazokStihacky.skry();
        this.jeZniceny = true;
    }

    /*
     * Uberie hp stihacky o zadanu hodnotu.
     * @param ubrateHP je pocet zivotov ktore sa maju stihacke odpocitat.
     */


    public void uberHP ( int ubrateHP){
        this.hp -= ubrateHP;
    }

    /*
     * Vrati aktualny pocet zivotov stihacky.
     * @return hodnota hp stihacky.
     */

    public int getHp () {
        return this.hp;
    }

    /*
     * Vrati zostavajuci cas do preletenia mimo hraciu plochu.
     * @return cas do preletenia v tikoch.
     */

    public int getCasDoPreletenie(){
        return this.casDoPreletenie;
    }

    /*
     * Uberie cas do preletenia o 1 tik.
     */

    public void uberCas(){
        this.casDoPreletenie -= 1;
    }

    /*
     * Informuje ci je stihacka znicena.
     * @return vrati true ak je stihacka znicena, inak false
     */

    public boolean getJeZniceny(){
        return this.jeZniceny;
    }

    /*
    * Vrati pocet striel guliek.
    * @return vrati pocet striel guliek.
    * */

    public int getPocetStrielGuliek(){
        return this.pocetStrielGuliek;
    }

    /*
     * Vrati pocet striel rakiet.
     * @return vrati pocet striel rakiet.
     * */

    public int getPocetStrielRakiet(){
        return  this.pocetStrielRakiet;
    }

    /*
    * Vrati timeou rakiet.
    * @return vrati timeout rakiet.
    * */


    public int getTimeOutRakiet(){
        return  this.timeOutRakiet;
    }

    /*
    * Uberie timeout rakiet o 1 tik a pri hodnote 0 sa vrati na pociatocnu hodnotu.
    * */

    public void uberTimeOutRakiet(){
        this.timeOutRakiet -= 1;
        if (this.timeOutRakiet < 1){
            this.timeOutRakiet = 60;
        }
    }

    /*
     * Uberie timeout guliek o 1 tik a pri hodnote 0 sa vrati na pociatocnu hodnotu.
     * */

    public void uberTimeOutGuliek(){
        this.timeOutGuliek -= 1;
        if (this.timeOutGuliek < 1){
            this.timeOutGuliek = 8;
        }
    }

    /*
    * Informuje o tom ci ma stihacka aktivovane zbrane.
    * @return vrati true ak ma stihacka aktivovane zbrane, inak false.
    * */


    public boolean getAktivujZbran(){
        return this.aktivujZbran;
    }

    /*
     * Aktivuje zbrane stihacky.
     * */

    public void aktivujZbran(){
        this.aktivujZbran = true;
    }

    /*
    * Vrati suradnicu y na aktivovanie zbrani.
    * @return vrati suradnicu y na aktivovanie zbrani.
    * */

    public int getPoziciaYnaAktivovanieZbrane(){
        return this.poziciaYnaAktivovanieZbrane;
    }

    /*
    * Uberie pocet rakiet stihacky.
    * */

    public void uberRaketu(){
        this.pocetStrielRakiet -= 1;
    }

    /*
     * Uberie pocet guliek stihacky.
     * */

    public void uberGulku(){
        this.pocetStrielGuliek -= 1;
    }

    /*
     * Vrati timeou guliek.
     * @return vrati timeout guliek.
     * */


    public int getTimeOutGuliek(){
        return this.timeOutGuliek;
    }

    /*
    * Vrati ci stihacka preletela lod.
    * @return vrati true ak preletela, inak false.
    * */

    public boolean getPreletelaLod(){
        return this.preletelaLod;
    }

    /*
    * Nastavi že stihacka preletela lod.
    * */


    public void preletelaLod(){
        this.preletelaLod = true;
    }

    /*
     * Vrati pocet skore ktore hrac ziska po zniceni stihacky.
     * @return hodnota skore stihacky
     */

    public int getSkore(){
        return this.skore;
    }
}






