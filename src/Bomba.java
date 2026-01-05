import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

//OK

public class Bomba {
    private Obrazok obrazokBomby;
    private final int damage = 40;
    private int polohaX;
    private int polohaY;
    private int casDoVybuchu = 150;


    public Bomba(int polohaX, int polohaY){
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.obrazokBomby = new Obrazok("assets/Bomba.png");
        this.obrazokBomby.zmenPolohu(polohaX, polohaY);
        this.obrazokBomby.zobraz();

        new Manazer().spravujObjekt(this);
    }

    public void tik(){


    }

    public void uberCas(){
        this.casDoVybuchu -= 1;

    }
    public int getCasDoVybuchu(){
        return this.casDoVybuchu;
    }

    public void zmenObrazokNavybuch(){
       this.obrazokBomby.zmenObrazok("assets/vybuchBomby.png");
       this.obrazokBomby.zobraz();
    }

    public int getDamage(){
        return this.damage;
    }

    public void znicenie(){
        this.obrazokBomby.skry();
    }

}
