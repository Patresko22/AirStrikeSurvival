import fri.shapesge.Obrazok;

/*
* Trieda Hrac reprezentuje hraca v hre.
* Hrac sa moze pohybovat na palube lode, prepinat zbran a strielat.
* Zaroven spravuje cooldown zbrani a umoznuje spustit hru zo startovacej obrazovky.*/

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
    private boolean zacatHru = false;
    private Hra hra;
    private StartScreen startScreen;

    /*
    * Vytvori hraca, nastavi pociatocnu poziciu a zakladnu zbran (gulomet).
    * Hrac je na zaciatku zablokovany kym sa hra nespusti.
    * @param gameManaget je manazer hry do ktoreho sa pridavaju projektily hraca.
    * @param hra je referencia na hru.
    * @param startScreen je startovacia obrazovka ktora sa po spusteni hry skryje.
    * */

    public Hrac(GameManager gameManager, Hra hra, StartScreen startScreen) {
        this.typZbrane = TypZbrane.GULOMET;
        this.polohaHracaX = 250;
        this.polohaHracaY = 650;
        this.obrazokHraca = new Obrazok("assets/hracGulomet.png");
        this.obrazokHraca.zmenPolohu(250, 650);
        this.gameManager = gameManager;
        this.hra = hra;
        this.startScreen = startScreen;
        this.zablokovanyPohyb = true;
    }


    /*
    * Metoda sa vola kazdy tik.
    * Spravuje odpocitavanie cooldownu zbrani.
    * */

    public void tik() {
        this.kontrolaTimeOut();
    }

    /*
    * Znizi pocet tlaceni ESC.
    * Po vyčerpani ukonci aplikaciu.
    * Nastavene na 2.
    * */

    public void zrus() {
        this.pocetZruseni -= 1;
        if (this.pocetZruseni == 0) {
            System.exit(0);
        }
    }

    /*
    * Posunie hraca doprava o 10, ak pohyb nieje zablokovany,
    * alebo nieje na zakazanej pozicii. (okraj lode)
    * */

    public void posunVpravo() {
        if (!this.zablokovanyPohyb) {
            if (this.getPolohaHracaX() == 470 ||
                    this.getPolohaHracaY() == 630 && this.getPolohaHracaX() == 390 ||
                    this.getPolohaHracaY() == 640 && this.getPolohaHracaX() == 420 ||
                    this.getPolohaHracaY() == 650 && this.getPolohaHracaX() == 440 ||
                    this.getPolohaHracaY() == 660 && this.getPolohaHracaX() == 450 ||
                    this.getPolohaHracaY() == 670 && this.getPolohaHracaX() == 460

                ) {
                return;
            } else {
                this.obrazokHraca.posunVodorovne(10);
                this.polohaHracaX += 10;
                System.out.println("X:" + this.polohaHracaX);
            }
        }

    }

    /*
     * Posunie hraca dolava o 10, ak pohyb nieje zablokovany,
     * alebo nieje na zakazanej pozicii. (okraj lode)
     * */

    public void posunVlavo() {
        if (!this.zablokovanyPohyb) {
            if (this.getPolohaHracaX() == -10 ||
                    this.getPolohaHracaY() == 630 && this.getPolohaHracaX() == 50 ||
                    this.getPolohaHracaY() == 640 && this.getPolohaHracaX() == 30 ||
                    this.getPolohaHracaY() == 650 && this.getPolohaHracaX() == 10 ||
                    this.getPolohaHracaY() == 650 && this.getPolohaHracaX() == 0
            ) {
                return;
            } else {
                this.obrazokHraca.posunVodorovne(-10);
                this.polohaHracaX -= 10;
                System.out.println("X:" + this.polohaHracaX);
            }
        }
    }



    /*
     * Posunie hraca hore o 10, ak pohyb nieje zablokovany,
     * alebo nieje na zakazanej pozicii. (okraj lode)*/


    public void posunHore() {

        if (!this.zablokovanyPohyb) {
            if (this.polohaHracaY < 640 ||

                    //Lava
                    this.getPolohaHracaY() == 640 && this.getPolohaHracaX() == 40 ||
                    this.getPolohaHracaY() == 640 && this.getPolohaHracaX() == 30 ||
                    this.getPolohaHracaY() == 650 && this.getPolohaHracaX() == 20 ||
                    this.getPolohaHracaY() == 650 && this.getPolohaHracaX() == 10 ||
                    this.getPolohaHracaY() == 650 && this.getPolohaHracaX() == 0  ||
                    this.getPolohaHracaY() == 660 && this.getPolohaHracaX() == -10 ||

                    //Prava
                    this.getPolohaHracaY() == 630 && this.getPolohaHracaX() == 390 ||
                    this.getPolohaHracaY() == 640 && this.getPolohaHracaX() == 400 ||
                    this.getPolohaHracaY() == 640 && this.getPolohaHracaX() == 410 ||
                    this.getPolohaHracaY() == 640 && this.getPolohaHracaX() == 420 ||
                    this.getPolohaHracaY() == 650 && this.getPolohaHracaX() == 430 ||
                    this.getPolohaHracaY() == 650 && this.getPolohaHracaX() == 440 ||
                    this.getPolohaHracaY() == 660 && this.getPolohaHracaX() == 450 ||
                    this.getPolohaHracaY() == 670 && this.getPolohaHracaX() == 460 ||
                    this.getPolohaHracaY() == 680 && this.getPolohaHracaX() == 470

            ) {
                return;
            } else {
                this.obrazokHraca.posunZvisle(-10);
                this.polohaHracaY -= 10;
                System.out.println("Y:" + this.polohaHracaY);
            }
        }
    }

    /*
     * Posunie hraca dole o 10, ak pohyb nieje zablokovany,
     * alebo nieje na zakazanej pozicii. (okraj lode)*/

    public void posunDole() {
        if (!this.zablokovanyPohyb) {
            if (this.getPolohaHracaY() != 680) {
                this.obrazokHraca.posunZvisle(+10);
                this.polohaHracaY += 10;
                System.out.println("Y:" + this.polohaHracaY);
            }
        }
    }

    /*
    * Vrati aktualnu suradnicu x pozicie hraca
    * @return suradnica x polohy hraca
    * */

    public int getPolohaHracaX() {
        return this.polohaHracaX;
    }

    /*
     * Vrati aktualnu suradnicu y pozicie hraca
     * @return suradnica y polohy hraca
     * */

    public int getPolohaHracaY() {
        return this.polohaHracaY;
    }

    /*
    * Prepne typ zbrane hraca.
    * Bud gulomet alebo raketomet
    * Aktualizuje podla toho obrazok hraca.
    * */

    public void zmenZbran() {
        if (this.typZbrane == TypZbrane.GULOMET) {
            this.typZbrane = TypZbrane.RAKETOMET;
            this.obrazokHraca.zmenObrazok("assets/hracRaketomet.png");
            this.obrazokHraca.zobraz();
        } else {
            this.typZbrane = TypZbrane.GULOMET;
            this.obrazokHraca.zmenObrazok("assets/hracGulomet.png");
            this.obrazokHraca.zobraz();
        }
    }

    /*
    * Aktivuje strelbu podla aktualne zvolenej zbrane.
    * Ak nieje pohyb zablokovany cooldownom.
    * Vytvori projektil a prida ho do game manazer.
    * */

    public void aktivuj() {
        if (!this.zablokovanyPohyb) {
            if (this.typZbrane == TypZbrane.RAKETOMET) {
                if (this.raketometTimeOut == 30) {
                    Raketa raketa = new Raketa(this.getPolohaHracaX(), this.getPolohaHracaY());
                    this.gameManager.pridajRaketu(raketa);
                    this.jeRaketometTimeOut = true;
                }
            }
            if (this.typZbrane == TypZbrane.GULOMET) {
                if (this.gulometTimeOut == 10) {
                    Gulka gulka = new Gulka(this.getPolohaHracaX() + 5, this.getPolohaHracaY() - 10);
                    this.gameManager.pridajGulku(gulka);
                    this.jeGulometTimeOut = true;
                }
            }
        }
    }

    /*
    * Spravuje odcitanie cooldownu zbrani.
    * Po vyprsani cooldownu sa cooldown vrati na povodnu hodnotu a hrac
    * moze vystrelit.
    * */

    public void kontrolaTimeOut() {
        if (this.jeRaketometTimeOut) {
            this.raketometTimeOut -= 1;
            if (this.raketometTimeOut == 0) {
                this.raketometTimeOut = 30;
                this.jeRaketometTimeOut = false;
            }
        }

        if (this.jeGulometTimeOut) {
            this.gulometTimeOut -= 1;
            if (this.gulometTimeOut == 0) {
                this.gulometTimeOut = 10;
                this.jeGulometTimeOut = false;
            }
        }
    }

    /*
    * Zablokuje pohyb hraca.
    * */

    public void zablokujPohyb() {
        this.zablokovanyPohyb = true;
    }

    /*
    * Spusti hru jeden krat
    * Skryje startovaciu obrazovku, zobrazi hraca a odblokuje mu pohyb.
    * */


    public void start() {
        if (this.zacatHru) {
            return;
        }
        this.zacatHru = true;
        this.hra.spustiLevel();
        this.startScreen.skry();
        this.obrazokHraca.zobraz();
        this.zablokovanyPohyb = false;
    }
}
