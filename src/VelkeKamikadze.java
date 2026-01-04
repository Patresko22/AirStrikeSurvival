import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import java.util.Random;
public class VelkeKamikadze {
    private Obrazok obrazokVelkeKamikadze;
    private int hp = 25;
    private int polohaX;
    private int polohaY;
    private final int damage = 50;
    private int polohaLodeY = 670;
    private int polohaLodeXMinimalna = 0;
    private int polohaLodeXMaximalna = 500;
    private int cielX;
    private int cielY;
    private int casDoVybuchu = 70;
    private boolean vybuch = false;
    private boolean posunutePoVybuchu = false;



    public VelkeKamikadze(int polohaY){
        //velkosť obrazka  45x35
        this.obrazokVelkeKamikadze = new Obrazok("assets/velkeKamikadze.png");
        Random random = new Random();
        this.polohaX = random.nextInt(20, 450);
        this.polohaY = polohaY;
        this.obrazokVelkeKamikadze.zmenPolohu(polohaX, polohaY);
        this.obrazokVelkeKamikadze.zobraz();
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
            if(cislo < 70){
                if (polohaX != cielX){
                    if (polohaX < cielX){
                        obrazokVelkeKamikadze.posunVodorovne(1);
                        polohaX += 1;
                    }if (polohaX > cielX){
                        obrazokVelkeKamikadze.posunVodorovne(-1);
                        polohaX -= 1;
                    }if (polohaX == cielX){
                        obrazokVelkeKamikadze.posunZvisle(1);
                        polohaY += 2;
                    }
                }


            }
        }






        this.obrazokVelkeKamikadze.posunZvisle(1);
        this.polohaY += 1;


    }

    public int getPolohaX () {
        return this.polohaX;
    }
    public int getPolohaY () {
        return this.polohaY;
    }

    public void znicenie () {
        System.out.println("znicene Velke Kamikadze");
        this.obrazokVelkeKamikadze.skry();
    }


    public void uberHP ( int ubrateHP){
        this.hp -= ubrateHP;
    }

    public int getHp () {
        return this.hp;
    }

    public void animaciaVybuchu () {
        this.obrazokVelkeKamikadze.zmenObrazok("assets/vybuchBomby.png");
        this.obrazokVelkeKamikadze.zobraz();
        this.vybuch = true;
        if (posunutePoVybuchu == false){
            this.obrazokVelkeKamikadze.posunZvisle(10);
            this.posunutePoVybuchu = true;
        }
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
