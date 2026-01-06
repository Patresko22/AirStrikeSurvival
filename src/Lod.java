import fri.shapesge.Obrazok;
import fri.shapesge.DataObrazku;

/*
* Reprezentuje lod hraca.
* Lod ma poziciu a svoje zivoty.
* Zivoty sa znizuju zasahom nepriatela.
* */

public class Lod {
    private Obrazok pozadie;
    private DataObrazku dataObrazku;
    private int hp;
    private int polohaX = 0;
    private int polohaY = 670 ;

    /*
    * Vytvori lod, nastavi jej poziciu a zobrazi ju na obrazovke.
    * */

    public Lod() {
        this.pozadie = new Obrazok("assets/lod.jpg");
        this.pozadie.zmenPolohu(this.polohaX, this.polohaY);
        this.pozadie.zobraz();
    }

    /*
    * Vrati hp lode.
    * @return pocet hp lode.
    * */

    public int getHp() {
        return this.hp;
    }

    /*
    * Vrati suradnicu x pozicie lode.
    * @return pozicia x lode
    * */

    public int getPolohaX() {
        return this.polohaX;
    }

    /*
     * Vrati suradnicu y pozicie lode.
     * @return pozicia y lode
     * */

    public int getPolohaY() {
        return this.polohaY;
    }

    /*
    * Znizi hp lode o zadanu hodnotu.
    * @param ubratHp je pocet hp ktore ma ubrat.
    */

    public void uberHP(int ubratHP) {
        this.hp -= ubratHP;
    }

    /*
    * Nastavi hp lode na zadanu hodnotu.
    * @param hp je nova hodnota hp lode.
    * */

    public void setHp(int hp) {
        this.hp = hp;
    }
}
