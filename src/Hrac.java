import fri.shapesge.Obrazok;
import fri.shapesge.DataObrazku;


public class Hrac {
    private Obrazok obrazokHraca;
    int polohaHracaX = 250;
    int polohaHracaY = 650;
    private GameManager gameManager;
    private int pocetZruseni = 2;
    private TypZbrane typZbrane;


    public Hrac(GameManager gameManager){

        this.typZbrane = TypZbrane.GULOMET;

        this.polohaHracaX = 250;
        this.polohaHracaY = 650;

        this.obrazokHraca = new Obrazok("assets/HracZBRAN/hrac_zbran.png");
        this.obrazokHraca.zmenPolohu(250,650);
        this.obrazokHraca.zobraz();
        this.gameManager = gameManager;



    }




    public void tik() {


    }



    public void zrus(){
        this.pocetZruseni -= 1;
        if (this.pocetZruseni == 0) {
            System.exit(0);
        }
    }



    public void posunVpravo() {
        if(getPolohaHracaX() == 470 ||
                getPolohaHracaY() == 630 && getPolohaHracaX() == 390 ||
                getPolohaHracaY() == 640 && getPolohaHracaX() == 420 ||
                getPolohaHracaY() == 650 && getPolohaHracaX() == 440 ||
                getPolohaHracaY() == 660 && getPolohaHracaX() == 450 ||
                getPolohaHracaY() == 670 && getPolohaHracaX() == 460

        ){

        } else {
            this.obrazokHraca.posunVodorovne(10);
            this.polohaHracaX += 10;
            System.out.println("X:" + polohaHracaX);
        }
    }

    public void posunVlavo() {

        //Ošetrenie vypadnutia z lode
        //Skusiť zjednodusit kod
        if(getPolohaHracaX() == -10 ||
                getPolohaHracaY() == 630 && getPolohaHracaX() == 50 ||
                getPolohaHracaY() == 640 && getPolohaHracaX() == 30 ||
                getPolohaHracaY() == 650 && getPolohaHracaX() == 10 ||
                getPolohaHracaY() == 650 && getPolohaHracaX() == 0
        ){

        } else{
            this.obrazokHraca.posunVodorovne(-10);
            this.polohaHracaX -= 10;
            System.out.println("X:" + polohaHracaX);
        }

    }



    //Horna hranica lode je 640 Y


    public void posunHore() {

        if(polohaHracaY < 640 ||

                //Lava
                getPolohaHracaY() == 640 && getPolohaHracaX() == 40 ||
                getPolohaHracaY() == 640 && getPolohaHracaX() == 30 ||
                getPolohaHracaY() == 650 && getPolohaHracaX() == 20 ||
                getPolohaHracaY() == 650 && getPolohaHracaX() == 10 ||
                getPolohaHracaY() == 650 && getPolohaHracaX() == 0  ||
                getPolohaHracaY() == 660 && getPolohaHracaX() == -10||

                //Prava
                 getPolohaHracaY() == 630 && getPolohaHracaX() == 390||
                getPolohaHracaY() == 640 && getPolohaHracaX() == 400 ||
                getPolohaHracaY() == 640 && getPolohaHracaX() == 410 ||
                getPolohaHracaY() == 640 && getPolohaHracaX() == 420 ||
                getPolohaHracaY() == 650 && getPolohaHracaX() == 430 ||
                getPolohaHracaY() == 650 && getPolohaHracaX() == 440 ||
                getPolohaHracaY() == 660 && getPolohaHracaX() == 450 ||
                getPolohaHracaY() == 670 && getPolohaHracaX() == 460 ||
                getPolohaHracaY() == 680 && getPolohaHracaX() == 470

        ) {

        }else {
            this.obrazokHraca.posunZvisle(-10);
            this.polohaHracaY -= 10;
            System.out.println("Y:" + polohaHracaY);
        }
    }

    public void posunDole() {
        //osetrenie aby hrac nepresiel za polku lode
        if (getPolohaHracaY() != 680) {
            this.obrazokHraca.posunZvisle(+10);
            this.polohaHracaY += 10;
            System.out.println("Y:" + polohaHracaY);
        }
    }

    public int getPolohaHracaX(){
        return this.polohaHracaX;
    }

    public int getPolohaHracaY(){
        return this.polohaHracaY;
    }

    public void zmenZbran(){
        if (typZbrane == TypZbrane.GULOMET){
            this.typZbrane = TypZbrane.RAKETOMET;
            this.obrazokHraca.zmenObrazok("assets/HracRPG/hrac_RPG_HORE.png");
            this.obrazokHraca.zobraz();
        }else {
            this.typZbrane = TypZbrane.GULOMET;
            this.obrazokHraca.zmenObrazok("assets/HracZBRAN/hrac_zbran.png");
            this.obrazokHraca.zobraz();
        }
    }

    public void aktivuj() {
        if (this.typZbrane == TypZbrane.RAKETOMET) {
            Raketa raketa = new Raketa(getPolohaHracaX(), getPolohaHracaY());
            this.gameManager.pridajRaketu(raketa);
        }else {
            Gulka gulka = new Gulka(getPolohaHracaX()+5, getPolohaHracaY()-10);
            this.gameManager.pridajGulku(gulka);
        }
    }



}
