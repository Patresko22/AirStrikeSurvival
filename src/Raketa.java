import fri.shapesge.Obrazok;
import  fri.shapesge.Manazer;
//OK

public class Raketa {
    private Obrazok obrazokRakety;
    private  int damage = 25;
    private int polohaX;
    private int polohaY;
    private int casDoVybuchu = 10;
    private boolean vybuch = false;



    public Raketa(int x, int y){
        this.polohaX = x + 14;
        this.polohaY = y;
        this.obrazokRakety = new Obrazok("assets/raketa.png");
        this.obrazokRakety.zmenPolohu(polohaX, polohaY);
        this.obrazokRakety.zobraz();
        new Manazer().spravujObjekt(this);
    }

    public  void  tik(){
        if (this.vybuch){
            return;
        }else {
            this.obrazokRakety.posunZvisle(-7);
            this.polohaY -= 7;
        }
    }

    public int getRaketaX(){
        return this.polohaX;
    }
    public int getRaketaY(){
        return this.polohaY;
    }

    public void vybuch(){
        this.vybuch = true;
        this.obrazokRakety.zmenObrazok("assets/vybuchRaketa.png");
        this.obrazokRakety.zobraz();
        this.damage = 0;
    }

    public void vybuchla(){
        this.obrazokRakety.skry();
        System.out.println("Raketa Vybuchla");
    }

    public int getDamage(){
        return this.damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void uberCas(){
        this.casDoVybuchu -= 1;
    }

    public boolean getVybuch(){
        return this.vybuch;
    }

    public int getCasDoVybuchu(){
        return this.casDoVybuchu;
    }
}
