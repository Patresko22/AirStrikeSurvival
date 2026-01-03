import fri.shapesge.Obrazok;
import  fri.shapesge.Manazer;

public class Raketa {
    private Obrazok obrazokRakety;
    private final int damage = 25;
    private int polohaX;
    private int polohaY;


    public Raketa(int x, int y){
        this.polohaX = x + 14;
        this.polohaY = y;

        this.obrazokRakety = new Obrazok("assets/raketa.png");
        obrazokRakety.zmenPolohu(polohaX, polohaY);
        obrazokRakety.zobraz();
        new Manazer().spravujObjekt(this);



    }

    public  void  tik(){
        this.obrazokRakety.posunZvisle(-7);
        this.polohaY -= 7;
    }

    public int getRaketaX(){
        return this.polohaX;
    }
    public int getRaketaY(){
        return this.polohaY;
    }

    public void vybuch(){
        this.obrazokRakety.skry();
        System.out.println("Raketa Vybuchla");
    }

    public int getDamage(){
        return this.damage;
    }



}
