import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

public class Bombarder {
    private Obrazok obrazokBombardera;
    private int hp = 50;
    private int polohaX;
    private int polohaY;
    private final int damage = 40;





    public Bombarder(int polohaX, int polohaY) {
        //velkosť obrazka je 100x100
        this.obrazokBombardera = new Obrazok("assets/UK_Lancaster.png");
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        obrazokBombardera.zmenPolohu(polohaX, polohaY);
        obrazokBombardera.zobraz();
        new Manazer().spravujObjekt(this);


    }

    public void tik() {
        obrazokBombardera.posunZvisle(1);
        polohaY += 1;


    }

        public int getPolohaX () {
            return polohaX;
        }
        public int getPolohaY () {
            return polohaY;
        }

        public void znicenie () {
            System.out.println("znicene Bombarder");
            obrazokBombardera.skry();
        }


        public void uberHP ( int ubrateHP){
            this.hp -= ubrateHP;
        }

        public int getHp () {
            return this.hp;
        }

        public void animaciaVybuchu () {
            obrazokBombardera.zmenObrazok("assets/vybuch.png");
        }


        public void vratPovodnyObrazok () {
            obrazokBombardera.zmenObrazok("assets/UK_Lancaster.png");
        }

        public int getDamage () {
            return damage;
        }

    }


