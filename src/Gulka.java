import fri.shapesge.Obrazok;
import  fri.shapesge.Manazer;
//OK

public class Gulka {
    private Obrazok obrazokGulky;
    private  int damage = 7;
    private int polohaX;
    private int polohaY;
    private int casDoVybuchu = 4;
    private boolean vybuch = false;

    public Gulka(int x, int y){
        this.polohaX = x + 14;
        this.polohaY = y;
        this.obrazokGulky = new Obrazok("assets/gulka.png");
        this.obrazokGulky.zmenPolohu(polohaX, polohaY);
        this.obrazokGulky.zobraz();
        new Manazer().spravujObjekt(this);
    }

    public  void  tik(){
        if (this.vybuch){
            return;
        }else {
            this.obrazokGulky.posunZvisle(-10);
            this.polohaY -= 10;
        }
    }

    public int getGulkaX(){
        return this.polohaX;
    }
    public int getGulkaY(){
        return this.polohaY;
    }

    public void vybuch(){
        this.vybuch = true;
        this.obrazokGulky.zmenObrazok("assets/vybuchGulky.png");
        this.obrazokGulky.zobraz();
        this.damage = 0;
    }

    public void vybuchla(){
        this.obrazokGulky.skry();
        System.out.println("Gulka Vybuchla");
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
