import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import java.util.Random;
public class MaleKamikadze {
    private Obrazok obrazokMaleKamikadze;
    private int hp = 25;
    private int polohaX;
    private int polohaY;
    private final int damage = 20;
    private int polohaLodeY = 670;
    private int polohaLodeXMinimalna = 0;
    private int polohaLodeXMaximalna = 500;
    private int cielX;
    private int cielY;

    public MaleKamikadze(){
        //velkosť obrazka je 45x35
        this.obrazokMaleKamikadze = new Obrazok("assets/maleKamikadze.png");
        Random random = new Random();
        polohaX = random.nextInt(20, 450);
        polohaY = random.nextInt(-10, -5);
        obrazokMaleKamikadze.zmenPolohu(polohaX, polohaY);
        obrazokMaleKamikadze.zobraz();
        new Manazer().spravujObjekt(this);
        cielX = random.nextInt(polohaLodeXMinimalna, polohaLodeXMaximalna);
        cielY = polohaLodeY;


    }

    public void tik() {
        Random random = new Random();
        int cislo = random.nextInt(0, 100);

        if(cislo < 25){
            if (polohaX != cielX){
                if (polohaX < cielX){
                    obrazokMaleKamikadze.posunVodorovne(1);
                    polohaX += 1;
                }if (polohaX > cielX){
                    obrazokMaleKamikadze.posunVodorovne(-1);
                    polohaX -= 1;
                }if (polohaX == cielX){
                    obrazokMaleKamikadze.posunZvisle(1);
                    polohaY += 1;
                }
            }


        }



        obrazokMaleKamikadze.posunZvisle(1);
        polohaY += 1;


    }

    public int getPolohaX () {
        return polohaX;
    }
    public int getPolohaY () {
        return polohaY;
    }

    public void znicenie () {
        System.out.println("znicene Male Kamikadze");
        obrazokMaleKamikadze.skry();
    }


    public void uberHP ( int ubrateHP){
        this.hp -= ubrateHP;
    }

    public int getHp () {
        return this.hp;
    }

    public void animaciaVybuchu () {
        obrazokMaleKamikadze.zmenObrazok("assets/vybuch.png");
    }


    public void vratPovodnyObrazok () {
        obrazokMaleKamikadze.zmenObrazok("assets/maleKamikadze.png");
    }

    public int getDamage () {
        return damage;
    }


}
