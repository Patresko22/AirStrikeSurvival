import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;


public class Bombarder {
    private Obrazok obrazokBombardera;
    private int hp = 50;
    private int polohaX;
    private int polohaY;
    private boolean zhodenaBomba = false;
    private int casDoPreletenie = 100;
    private boolean jeZniceny = false;
    private int skore = 3;

    /*
    * Trieda Bombarder reprezentuje nepriatelsky Bombarder.
    * Bombarder sa priblizuje k lodi zhora na dol a po tom co prejde nad lod
    * zhodi bombu. Bombarder moze byt bud zostreleny hracom alebo po prejdeni za hracovu lod
    * je sam zniceny.
    */

    public Bombarder(int polohaX, int polohaY) {
        //velkosť obrazka je 100x100
        this.obrazokBombardera = new Obrazok("assets/bombarder.png");
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.obrazokBombardera.zmenPolohu(polohaX, polohaY);
        this.obrazokBombardera.zobraz();
        new Manazer().spravujObjekt(this);


    }

    /*
    * Metoda je volana kazdy tik a zabezpecuje pohyb bombardera smerom nadol.
    */

    public void tik() {
        this.obrazokBombardera.posunZvisle(1);
        this.polohaY += 1;
    }

    /*
    * Vrati suradnicu x polohy bombardera.
    * @return suradnica x bombardera
    */

    public int getPolohaX () {
        return this.polohaX;
    }

    /*
    * Vrati suradnicu y polohy bombardera.
    * @return suradnica y bombardera
    */

    public int getPolohaY () {
        return this.polohaY;
    }

    /*
    * Znici bombarder a skryje jeho obrazok. Zaroven ho oznaci ako zniceny.
    */

    public void znicenie () {
        this.obrazokBombardera.skry();
        this.jeZniceny = true;
    }

    /*
    * Uberie hp bombardera o zadanu hodnotu.
    * @param ubrateHP je pocet zivotov ktore sa maju bombarderu odpocitat.
    */

    public void uberHP ( int ubrateHP){
        this.hp -= ubrateHP;
    }

    /*
    * Vrati aktualny pocet zivotov bombardera.
    * @return hodnota hp bombardera.
    */

    public int getHp () {
        return this.hp;
    }

    /*
    * Nastavi atribut zhodenaBomba na true.
    * Tym oznaci ze bombarder uz zhodil bombu.
    */

    public void zhodBombu(){
        this.zhodenaBomba = true;
    }

    /*
    * Informuje ci uz bola zhodena bomba.
    * @return vrati true ak bomba bola zhodena, inak false.
    */

    public boolean getZhodenaBomba() {
        return this.zhodenaBomba;
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
   * Informuje ci je bombarder zniceny.
   * @return vrati true ak je bombarder zniceny, inak false
   */

    public boolean getJeZniceny(){
        return this.jeZniceny;
    }

    /*
    * Vrati pocet skore ktore hrac ziska po zniceni bombardera.
    * @return hodnota skore bombardera
    */

    public int getSkore(){
        return this.skore;
    }
}






