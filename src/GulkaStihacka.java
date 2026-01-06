import fri.shapesge.Obrazok;
import  fri.shapesge.Manazer;


/*
 * Trieda Gulka reprezentuje gulku vystrelenu stihackou.
 * Gulka sa pohybuje smerom dole, po kolizii vybuchne a
 *  kratko po tom sa odstrani.
 *  */

public class GulkaStihacka {
    private Obrazok obrazokGulky;
    private  int damage = 7;
    private int polohaX;
    private int polohaY;
    private int casDoVybuchu = 4;
    private boolean vybuch = false;

    /*
     * Vytvori gulku na pozicii stihacky s malou korekciou aby to bolo vycentrovane.
     * Zobrazi ju a zaregistruje do manazera.
     * @param x je pociatocna suradnica x
     * @param y je pociatocna suradnica y
     * */

    public GulkaStihacka(int x, int y) {
        this.polohaX = x + 14;
        this.polohaY = y;
        this.obrazokGulky = new Obrazok("assets/gulka.png");
        this.obrazokGulky.zmenPolohu(this.polohaX, this.polohaY);
        this.obrazokGulky.zobraz();
        new Manazer().spravujObjekt(this);
    }

    /*
     * Metoda volana kazdy tik.
     * Ak gulka este nevybuchla posunie sa smerom hore.
     * Ak vybuchla tak sa neposuva.
     * Je to kvôli tomu aby sa pri animacii vybuchu nepohybovala
     * */

    public  void  tik() {
        if (this.vybuch) {
            return;
        } else {
            this.obrazokGulky.posunZvisle(+10);
            this.polohaY += 10;
        }
    }

    /*
     * Vrati aktualnu suradnicu x pozicie gulky
     * @return poloha x gulky
     * */

    public int getGulkaX() {
        return this.polohaX;
    }

    /*
     * Vrati aktualnu suradnicu y pozicie gulky
     * @return poloha y gulky
     * */

    public int getGulkaY() {
        return this.polohaY;
    }

    /*
     * Spusti vybuch gulky
     * zmeni obrazok na vybuch gulky a nastavi atribut vybuch na true.
     * Nastavi damage na 0
     * */

    public void vybuch() {
        this.vybuch = true;
        this.obrazokGulky.zmenObrazok("assets/vybuchGulky.png");
        this.obrazokGulky.zobraz();
        this.damage = 0;
    }

    /*
     * Dokonci vybuch gulky tým že ju skryje
     * */

    public void vybuchla() {
        this.obrazokGulky.skry();
        System.out.println("Gulka Stihacky Vybuchla");
    }

    /*
     * Vrati damage aky dava gulka
     * @return hodnota damage gulky
     * */

    public int getDamage() {
        return this.damage;
    }

    /*
     * Nastavi damage gulky.
     * @param damage je nova hodnota
     * */

    public void setDamage(int damage) {
        this.damage = damage;
    }

    /*
     * Znizi zostavajuci cas do vybuchu.
     * */

    public void uberCas() {
        this.casDoVybuchu -= 1;
    }

    /*
     * Informuje ci uz gulka vybuchla.
     * @return vrati true ak vybuchla, inak false.
     * */

    public boolean getVybuch() {
        return this.vybuch;
    }

    /*
     * Vrati zostavajuci cas do vybuchu gulky.
     * @return cas do vybuchu v tikoch
     * */

    public int getCasDoVybuchu() {
        return this.casDoVybuchu;
    }
}
