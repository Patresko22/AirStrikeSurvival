import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import java.util.Random;

public class Stihacka {
    private Obrazok obrazokStihacky;
    private int hp = 50;
    private int polohaX;
    private int polohaY;
    private int casDoPreletenie = 100;
    private boolean jeZniceny = false;
    private int pocetStrielGuliek;
    private int pocetStrielRakiet;
    private int timeOutGuliek = 8;
    private int timeOutRakiet = 60;
    private boolean preletelaLod = false;
    private boolean aktivujZbran = false;
    private int poziciaYnaAktivovanieZbrane;
    private TypZbraneStihacky typZbraneStihacky;
    private int skore = 5;




    public Stihacka(int polohaX, int polohaY) {
        Random random = new Random();

        int typZbrane = random.nextInt(0,3);
        if (typZbrane < 2){
            this.pocetStrielRakiet = random.nextInt(2, 5);
            this.pocetStrielGuliek = 0;
            System.out.println("Stihacka ma rakiet: " + this.pocetStrielRakiet );
        }
        if (typZbrane == 2){
            this.pocetStrielGuliek = random.nextInt(7, 15);
            this.pocetStrielRakiet = 0;
            System.out.println("Stihacka ma guliek: " + this.pocetStrielGuliek );
        }
        this.poziciaYnaAktivovanieZbrane = random.nextInt(0, 300);
        //velkosť obrazka je 60 x 50
        this.obrazokStihacky = new Obrazok("assets/Stihacka.png");
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.obrazokStihacky.zmenPolohu(polohaX, polohaY);
        this.obrazokStihacky.zobraz();
        new Manazer().spravujObjekt(this);
    }

    public void tik() {
        this.obrazokStihacky.posunZvisle(1);
        this.polohaY += 1;
    }

    public int getPolohaX () {
        return this.polohaX;
    }
    public int getPolohaY () {
        return this.polohaY;
    }

    public void znicenie () {
        System.out.println("znicene Stihacka");
        this.obrazokStihacky.skry();
        this.jeZniceny = true;
    }


    public void uberHP ( int ubrateHP){
        this.hp -= ubrateHP;
    }

    public int getHp () {
        return this.hp;
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

    public int getPocetStrielGuliek(){
        return this.pocetStrielGuliek;
    }
    public int getPocetStrielRakiet(){
        return  this.pocetStrielRakiet;
    }
    public int getTimeOutRakiet(){
        return  this.timeOutRakiet;
    }

    public void uberTimeOutRakiet(){
        this.timeOutRakiet -= 1;
        if (this.timeOutRakiet < 1){
            this.timeOutRakiet = 60;
        }
    }

    public void uberTimeOutGuliek(){
        this.timeOutGuliek -= 1;
        if (this.timeOutGuliek < 1){
            this.timeOutGuliek = 8;
        }
    }

    public boolean getAktivujZbran(){
        return this.aktivujZbran;
    }

    public void aktivujZbran(){
        this.aktivujZbran = true;
    }

    public int getPoziciaYnaAktivovanieZbrane(){
        return this.poziciaYnaAktivovanieZbrane;
    }

    public void uberRaketu(){
        this.pocetStrielRakiet -= 1;
    }

    public void uberGulku(){
        this.pocetStrielGuliek -= 1;
    }


    public int getTimeOutGuliek(){
        return this.timeOutGuliek;
    }

    public boolean getPreletelaLod(){
        return this.preletelaLod;
    }
    public void preletelaLod(){
        this.preletelaLod = true;
    }

    public int getSkore(){
        return this.skore;
    }
}






