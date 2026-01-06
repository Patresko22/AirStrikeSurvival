import fri.shapesge.Obrazok;
import  fri.shapesge.Manazer;

/*
 * Trieda raketa reprezentuje raketu vystrelenu stihackou.
 * Raketa sa pohybuje smerom nadol.
 * Po zasahu vybuchne a kratko po tom sa odstrani.
 * */

public class RaketaStihacky {
    private Obrazok obrazokRakety;
    private  int damage = 25;
    private int polohaX;
    private int polohaY;
    private int casDoVybuchu = 10;
    private boolean vybuch = false;

    /*
     * Vytvori raketu na pozicii stihacky ktora je trocha upravena.
     * Zobrazi ju a zaregistruje do manazera.
     * @param x je pociatocna suradnica x pozicie rakety
     * @param y je pociatocna suradnica y pozicie rakety
     * */

    public RaketaStihacky(int x, int y){
        this.polohaX = x + 14;
        this.polohaY = y;
        this.obrazokRakety = new Obrazok("assets/raketaStihacky.png");
        this.obrazokRakety.zmenPolohu(this.polohaX, this.polohaY);
        this.obrazokRakety.zobraz();
        new Manazer().spravujObjekt(this);

    }

    /*
     * Metoda sa vola kazdy tik.
     * Ak raketa este nevybuchla tak sa posunie smerom nahor.
     * Ak uz vybuchla tak sa dalej nepohybuje.
     * */

    public  void  tik(){
        if (this.vybuch){
            return;
        }else {
            this.obrazokRakety.posunZvisle(+7);
            this.polohaY += 7;
        }
    }

    /*
     * Vrati aktualnu suradnicu x pozicie rakety.
     * @return vrati poziciu x rakety.
     * */

    public int getRaketaX(){
        return this.polohaX;
    }

    /*
     * Vrati aktualnu suradnicu y pozicie rakety.
     * @return vrati poziciu y rakety.
     * */

    public int getRaketaY(){
        return this.polohaY;
    }


    /*
     * Spusti vybuch rakety.
     * Zmeni obrazok na vybuch.
     * Nastavi atribut vybuch na true.
     * Nastavi damage na 0
     * */

    public void vybuch(){
        this.vybuch = true;
        this.obrazokRakety.zmenObrazok("assets/vybuchRaketa.png");
        this.obrazokRakety.zobraz();
        this.damage = 0;
    }

    /*
     * Dokonci vybuch rakety tym ze ju skryje.
     * */


    public void vybuchla(){
        this.obrazokRakety.skry();
        System.out.println("Raketa Vybuchla");
    }

    /*
     * Vrati damage rakety.
     * @return hodnota damage rakety
     * */

    public int getDamage(){
        return this.damage;
    }

    /*
     * Nastavi hodnotu damage rakety podla zadanej hodnoty.
     * @param damage je hodnota na aku ma damage rakety zmenit.
     * */

    public void setDamage(int damage){
        this.damage = damage;
    }

    /*
     * Uberie cas do vybuchu rakety o 1 tik.
     * */

    public void uberCas(){
        this.casDoVybuchu -= 1;
    }

    /*
     * Vrati ci uz raketa vybuchla.
     * @return vrati true ak vybuchla, inak false.
     * */

    public boolean getVybuch(){
        return this.vybuch;
    }

    /*
     * Vrati cas do vybuchu rakety.
     * @return vrati cas do vybuchu rakety v tikoch
     * */

    public int getCasDoVybuchu(){
        return this.casDoVybuchu;
    }
}
