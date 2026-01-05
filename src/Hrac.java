import fri.shapesge.Obrazok;
//OK

public class Hrac {
    private Obrazok obrazokHraca;
    private int polohaHracaX = 250;
    private int polohaHracaY = 650;
    private GameManager gameManager;
    private int pocetZruseni = 2;
    private TypZbrane typZbrane;
    private int raketometTimeOut = 30;
    private boolean jeRaketometTimeOut = false;
    private int gulometTimeOut = 10;
    private boolean jeGulometTimeOut = false;
    private boolean zablokovanyPohyb = false;




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
        kontrolaTimeOut();
    }

    public void zrus(){
        this.pocetZruseni -= 1;
        if (this.pocetZruseni == 0) {
            System.exit(0);
        }
    }

    public void posunVpravo() {
        if (!zablokovanyPohyb){
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

    }

    public void posunVlavo() {
        if (!this.zablokovanyPohyb){
            if(getPolohaHracaX() == -10 ||
                    getPolohaHracaY() == 630 && getPolohaHracaX() == 50 ||
                    getPolohaHracaY() == 640 && getPolohaHracaX() == 30 ||
                    getPolohaHracaY() == 650 && getPolohaHracaX() == 10 ||
                    getPolohaHracaY() == 650 && getPolohaHracaX() == 0
            ){} else{
                this.obrazokHraca.posunVodorovne(-10);
                this.polohaHracaX -= 10;
                System.out.println("X:" + this.polohaHracaX);
            }
        }
    }



    //Horna hranica lode je 640 Y


    public void posunHore() {

        if (!this.zablokovanyPohyb){
            if(this.polohaHracaY < 640 ||

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
                System.out.println("Y:" + this.polohaHracaY);
            }
        }
    }

    public void posunDole() {
        if (!this.zablokovanyPohyb){
            if (getPolohaHracaY() != 680) {
                this.obrazokHraca.posunZvisle(+10);
                this.polohaHracaY += 10;
                System.out.println("Y:" + this.polohaHracaY);
            }
        }
    }

    public int getPolohaHracaX(){
        return this.polohaHracaX;
    }

    public int getPolohaHracaY(){
        return this.polohaHracaY;
    }

    public void zmenZbran(){
        if (this.typZbrane == TypZbrane.GULOMET){
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
        if (!this.zablokovanyPohyb){
            if (this.typZbrane == TypZbrane.RAKETOMET) {
                if (this.raketometTimeOut == 30){
                    Raketa raketa = new Raketa(getPolohaHracaX(), getPolohaHracaY());
                    this.gameManager.pridajRaketu(raketa);
                    this.jeRaketometTimeOut = true;
                }
            }
            if (this.typZbrane == TypZbrane.GULOMET) {
                if (this.gulometTimeOut == 10) {
                    Gulka gulka = new Gulka(getPolohaHracaX() + 5, getPolohaHracaY() - 10);
                    this.gameManager.pridajGulku(gulka);
                    this.jeGulometTimeOut = true;
                }
            }
        }
    }

    public void kontrolaTimeOut(){
        if (this.jeRaketometTimeOut){
            this.raketometTimeOut -= 1;
            if (this.raketometTimeOut == 0){
                this.raketometTimeOut = 30;
                this.jeRaketometTimeOut = false;
            }
        }

        if (this.jeGulometTimeOut){
            this.gulometTimeOut -= 1;
            if (this.gulometTimeOut == 0){
                this.gulometTimeOut = 10;
                this.jeGulometTimeOut = false;
            }
        }
    }

    public void zablokujPohyb(){
        this.zablokovanyPohyb = true;
    }
}
