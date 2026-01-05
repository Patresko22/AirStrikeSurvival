import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

public class Bombarder {
    private Obrazok obrazokBombardera;
    private int hp = 50;
    private int polohaX;
    private int polohaY;
    private boolean zhodenaBomba = false;
    private int casDoPreletenie = 100;
    private boolean jeZniceny = false;
    private int skore = 3;





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
                this.obrazokBombardera.skry();
                this.jeZniceny = true;
        }


        public void uberHP ( int ubrateHP){
            this.hp -= ubrateHP;
        }

        public int getHp () {
            return this.hp;
        }





        public void zhodBombu(){
            this.zhodenaBomba = true;
        }

        public boolean getZhodenaBomba() {
            return this.zhodenaBomba;
        }

        public int getCasDoPreletenie(){
            return this.casDoPreletenie;
        }

        public void uberCas(){
            this.casDoPreletenie -= 1;
        }

        public boolean getJeZniceny(){
            return this.jeZniceny;
        }
        public int getSkore(){
            return this.skore;
        }



}






