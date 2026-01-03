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
    private int casDoVybuchu = 70;
    private boolean vybuch = false;



    public MaleKamikadze(){
        //velkosť obrazka  45x35
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

        if (this.vybuch == true){
            return;


        }else {
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
                        polohaY += 2;
                    }
                }


            }
        }






        this.obrazokMaleKamikadze.posunZvisle(1);
        this.polohaY += 1;


    }

    public int getPolohaX () {
        return this.polohaX;
    }
    public int getPolohaY () {
        return this.polohaY;
    }

    public void znicenie () {
        System.out.println("znicene Male Kamikadze");
        this.obrazokMaleKamikadze.skry();
    }


    public void uberHP ( int ubrateHP){
        this.hp -= ubrateHP;
    }

    public int getHp () {
        return this.hp;
    }

    public void animaciaVybuchu () {
        this.obrazokMaleKamikadze.zmenObrazok("assets/vybuchBomby.png");
        this.obrazokMaleKamikadze.zobraz();
        this.vybuch = true;
    }


    public int getDamage () {
        return this.damage;
    }
    public void uberCas(){
        this.casDoVybuchu -= 1;

    }
    public int getCasDoVybuchu(){
        return this.casDoVybuchu;
    }

    public boolean getVybuch(){
        return this.vybuch;
    }




}
