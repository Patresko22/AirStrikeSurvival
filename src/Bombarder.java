import fri.shapesge.Obrazok;
import fri.shapesge.DataObrazku;
public class Bombarder {
    private Obrazok obrazokBombardera;
    private int hp = 50;
    private int polohaX;
    private int polohaY;


    public Bombarder(int polohaX, int polohaY){
        this.obrazokBombardera = new Obrazok("assets/UK_Lancaster.png");
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        obrazokBombardera.zmenPolohu(polohaX, polohaY);
        obrazokBombardera.zobraz();


    }

    public int getPolohaX(){
        return polohaX;
    }
    public int getPolohaY(){
        return polohaY;
    }

    public void znicenie(){
        System.out.println("znicene");
        obrazokBombardera.skry();
    }

    public void uberHP(int ubrateHP){
        this.hp -= ubrateHP;
    }

    public int getHp(){
        return this.hp;
    }

}
