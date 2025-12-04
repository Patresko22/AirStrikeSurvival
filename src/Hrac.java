import fri.shapesge.Obrazok;
import fri.shapesge.DataObrazku;

public class Hrac {
    private Obrazok obrazokHraca;

    public Hrac(){
        this.obrazokHraca = new Obrazok("assets/hrac_RPG.png");
        obrazokHraca.zmenPolohu(250,650);
        obrazokHraca.zobraz();



    }




    public void tik() {

    }

    public void posunVpravo() {
        this.obrazokHraca.posunVodorovne(10);
    }

    public void posunVlavo() {
        this.obrazokHraca.posunVodorovne(-10);
    }

    public void posunHore() {
        this.obrazokHraca.posunZvisle(-10);
    }

    public void posunDole() {
        this.obrazokHraca.posunZvisle(+10);
    }


}
