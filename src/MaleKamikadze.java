import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import java.util.Random;

/*
* Trieda MaleKamikadze Reprezentuje male kamikadze lietadlo.
* Lietadlo sa pohybuje smerom k lodi, po kolitii spusti animaciu vybuchu
* a po uplynuti casu sposoby poskodenie lodi.
* */

public class MaleKamikadze {
    private Obrazok obrazokMaleKamikadze;
    private int hp = 25;
    private int polohaX;
    private int polohaY;
    private final int damage = 20;
    private int polohaLodeY = 670;
    private int polohaLodeXMinimalna = 0;
    private int polohaLodeXMaximalna = 500;
    private int cielX;
    private int cielY;
    private int casDoVybuchu = 70;
    private boolean vybuch = false;
    private boolean posunutePoVybuchu = false;
    private int skore = 1;

    /*
    * Vytvori male kamikadze lietadlo na zadanej suradnici y.
    * Suradnica x a ciel su zvolene nahodne.
    * Objekt sa zobrazi a zaregistruje do manazera.
    * @param polohaY je pociatocna suradnica y lietadla.
    * */

    public MaleKamikadze(int polohaY){
        //velkosť obrazka  45x35
        this.obrazokMaleKamikadze = new Obrazok("assets/maleKamikadze.png");
        Random random = new Random();
        this.polohaX = random.nextInt(20, 450);
        this.polohaY = polohaY;
        this.obrazokMaleKamikadze.zmenPolohu(this.polohaX, polohaY);
        this.obrazokMaleKamikadze.zobraz();
        new Manazer().spravujObjekt(this);
        this.cielX = random.nextInt(this.polohaLodeXMinimalna + 50, this.polohaLodeXMaximalna - 110);
        this.cielY = this.polohaLodeY;


    }

    /*
    * Metoda volana kazdy tik.
    * Zabezpecuje pohyb lietadla smerom nadol
    * a mieru korekcie smeru k cielu na lodi.
    * Ak uz lietadlo vybuchuje tak sa pohyb zastavi.
    * */

    public void tik() {
        Random random = new Random();
        int cislo = random.nextInt(0, 100);
        if (this.vybuch == true){
            return;
        }else {
            if(cislo < 25){
                if (this.polohaX != this.cielX){
                    if (this.polohaX < this.cielX){
                        this.obrazokMaleKamikadze.posunVodorovne(1);
                        this.polohaX += 1;
                    }
                    if (this.polohaX > this.cielX){
                        this.obrazokMaleKamikadze.posunVodorovne(-1);
                        this.polohaX -= 1;
                    }
                    if (this.polohaX == this.cielX){
                        this.obrazokMaleKamikadze.posunZvisle(1);
                        this.polohaY += 2;
                    }
                }
            }
        }
        this.obrazokMaleKamikadze.posunZvisle(1);
        this.polohaY += 1;
    }

    /*
    * Vrati aktualnu suradnicu x polohy lietadla.
    * @return vrati suradnicu x lietadla.
    * */

    public int getPolohaX () {
        return this.polohaX;
    }

    /*
     * Vrati aktualnu suradnicu y polohy lietadla.
     * @return vrati suradnicu y lietadla.
     * */

    public int getPolohaY () {
        return this.polohaY;
    }

    /*
    * Znici male kamikadze lietadlo a skryje jeho obrazok.
    * */

    public void znicenie () {
        System.out.println("znicene Male Kamikadze");
        this.obrazokMaleKamikadze.skry();
    }

    /*
    * Uberie hp lietadla o zadanu hodnotu.
    * @param ubrateHP je hodnota o ktoru ma znizit hp lietadla.
    *  */

    public void uberHP ( int ubrateHP){
        this.hp -= ubrateHP;
    }

    /*
    * Vrati aktualny pocet hp lietadla.
    * @return vrati aktualne hp lietadla.
    * */

    public int getHp () {
        return this.hp;
    }

    /*
    * Spusti animaciu vybuchu.
    * Zmeni obrazok a zastavi pohyb.
    * */

    public void animaciaVybuchu () {
        this.obrazokMaleKamikadze.zmenObrazok("assets/vybuchBomby.png");
        this.obrazokMaleKamikadze.zobraz();
        this.vybuch = true;
        if (!this.posunutePoVybuchu){
            this.obrazokMaleKamikadze.posunZvisle(10);
            this.posunutePoVybuchu = true;
        }
    }

    /*
    * Vrati damage lietadla.
    * @return vrati damage lietadla.
    * */

    public int getDamage () {
        return this.damage;
    }

    /*
    * Ubereie cas do vybuchu o 1 tik.
    * */

    public void uberCas(){
        this.casDoVybuchu -= 1;
    }

    /*
    * Vrati pocet tikov do vybuchu lietadla.
    * @return cas do vybuchu v tikoch
    * */

    public int getCasDoVybuchu(){
        return this.casDoVybuchu;
    }

    /*
    * Vrati ci lietadlo vybuchuje.
    * @return vrati true ak vybuchuje, inak false.
    *  */

    public boolean getVybuch(){
        return this.vybuch;
    }

    /*
    * Vrati skore ktore je za znicenie lietadla zbranou.
    * @return vrati hodnotu skore.
    * */

    public int getSkore(){
        return this.skore;
    }
}
