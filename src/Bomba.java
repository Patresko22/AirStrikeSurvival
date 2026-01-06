import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

/*
* Trieda Bomba reprezentuje bombu zhodenu bombardérom.
* Bomba po uplynuti nastaveneho casu exploduje a uberie hracovej lodi hp.
* Bomba spravuje svoj obrázok.
*/

public class Bomba {
    private Obrazok obrazokBomby;
    private final int damage = 40;
    private int polohaX;
    private int polohaY;
    private int casDoVybuchu = 150;


    /*
    * Vytvorí bombu na zadanej pozicii a zaroven ju zaregistruje do manazera aby mohla reagovat na tik
    * a odpocitavat cas do vybuchu
    * @param polohaX je suradnica x kde sa bomba zobrazí.
    * @param polohaY je suradnica y kde sa bomba zobrazí.
    */

    public Bomba(int polohaX, int polohaY){
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.obrazokBomby = new Obrazok("assets/Bomba.png");
        this.obrazokBomby.zmenPolohu(polohaX, polohaY);
        this.obrazokBomby.zobraz();

        new Manazer().spravujObjekt(this);
    }


    /*
    * Metoda volana manazerom kazdy tik. Zatial je prazdna. Vytvoril som ju dopredu
    * pre dalsiu skalovatelnost projektu ktorú mam v pláne.
    */

    public void tik(){


    }

    /*
    * Znizi zostavajuci cas do vybuchu o 1 tik.
    */

    public void uberCas(){
        this.casDoVybuchu -= 1;
    }

    /*
    * Vrati zostavajuci cas do vybuchu.
    * @return pocet tikov do vybuchu.
    */

    public int getCasDoVybuchu(){
        return this.casDoVybuchu;
    }

    /*
    * Zmeni obrazok bomby na obrazok vybuchnutej bomby a zobrazi ho.
    * Nastane tak kratko pred vybuchom pre efekt vybuchu.
    */

    public void zmenObrazokNavybuch(){
       this.obrazokBomby.zmenObrazok("assets/vybuchBomby.png");
       this.obrazokBomby.zobraz();
    }

   /*
   * Vrati damage ktorý bomba spôsobuje po vybuchu.
   * @return hodnota damage bomby
   */

    public int getDamage(){
        return this.damage;
    }

    /*
    * Skryje bombu po vybuchu
    */

    public void znicenie(){
        this.obrazokBomby.skry();
    }

}
