import fri.shapesge.Obrazok;
import fri.shapesge.DataObrazku;

public class Lod {
    private Obrazok pozadie;
    private DataObrazku dataObrazku;
    private int hp = 300;
    private int polohaX = 0;
    private int polohaY = 670 ;

    public Lod(){
        this.pozadie = new Obrazok("assets/lod.jpg");
        this.pozadie.zmenPolohu(polohaX,polohaY);
        this.pozadie.zobraz();

    }
    public int getHp(){
        return hp;
    }

    public int getPolohaX(){
        return polohaX;
    }

    public int getPolohaY(){
        return polohaY;
    }

    public void uberHP(int ubratHP){
        hp -= ubratHP;
        System.out.println("HP lode: " + hp);
    }
}
