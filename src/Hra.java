import fri.shapesge.Manazer;

/*
* Trieda Hra reprezentuje hru ako celok.
* Zodpoveda za inicializaciu hernych objektov.
* Vytvara hraca.
* Spravuje startovaci obrazovku.
* Spusta level.
* */

public class Hra {
    private GameManager gameManager;
    private Manazer manazer;
    private StartScreen startScreen;

    /*
    * Vytvorí novú hru.
    * Inicializuje hraciu plochu, manazera hry, startovaciu obrazovku a hraca.
    * Pohyb hraca na zaciatku zablokuje.
    * */


    public Hra() {
        HraciaPlocha plocha = new HraciaPlocha();
        this.gameManager = new GameManager(plocha, 350);
        this.startScreen = new StartScreen();
        Hrac hrac = new Hrac(this.gameManager, this, this.startScreen);
        this.gameManager.nastavHraca(hrac);
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(hrac);
    }

    /*
    * Spusti level hry.
    * Vytvori lod hraca, zaregistruje game manazera.
    * Vytvori a zaregistruje vsetkych nepriatelov.
    * */

    public void spustiLevel() {

        Lod lod = new Lod();
        this.gameManager.pridajLod(lod);
        this.manazer.spravujObjekt(this.gameManager);

        //--------RAID 1-----------------
        MaleKamikadze maleKamikadze = new MaleKamikadze(-230);
        this.gameManager.pridajMaleKamikadze(maleKamikadze);

        MaleKamikadze maleKamikadze1 = new MaleKamikadze(-260);
        this.gameManager.pridajMaleKamikadze(maleKamikadze1);

        MaleKamikadze maleKamikadze2 = new MaleKamikadze(-430);
        this.gameManager.pridajMaleKamikadze(maleKamikadze2);

        MaleKamikadze maleKamikadze3 = new MaleKamikadze(-460);
        this.gameManager.pridajMaleKamikadze(maleKamikadze3);

        MaleKamikadze maleKamikadze4 = new MaleKamikadze(-650);
        this.gameManager.pridajMaleKamikadze(maleKamikadze4);

        MaleKamikadze maleKamikadze5 = new MaleKamikadze(-700);
        this.gameManager.pridajMaleKamikadze(maleKamikadze5);

        VelkeKamikadze velkeKamikadze = new VelkeKamikadze(-1000);
        this.gameManager.pridajVelkeKamikadze(velkeKamikadze);

        Bombarder bombarder1 = new Bombarder(100, -1100);
        this.gameManager.pridajBombarder(bombarder1);
        Bombarder bombarder2 = new Bombarder(300, -1150);
        this.gameManager.pridajBombarder(bombarder2);


        Stihacka stihacka = new Stihacka(100 , -1700);
        this.gameManager.pridajStihacku(stihacka);



        this.manazer.spravujObjekt(maleKamikadze);
        this.manazer.spravujObjekt(maleKamikadze1);
        this.manazer.spravujObjekt(maleKamikadze2);
        this.manazer.spravujObjekt(maleKamikadze3);
        this.manazer.spravujObjekt(maleKamikadze4);
        this.manazer.spravujObjekt(maleKamikadze5);
        this.manazer.spravujObjekt(velkeKamikadze);
        this.manazer.spravujObjekt(bombarder1);
        this.manazer.spravujObjekt(bombarder2);
        this.manazer.spravujObjekt(stihacka);

        //--------RAID 1-----------------




        //--------RAID 2-----------------




        VelkeKamikadze velkeKamikadze2 = new VelkeKamikadze(-2700);
        this.gameManager.pridajVelkeKamikadze(velkeKamikadze2);

        MaleKamikadze maleKamikadze6 = new MaleKamikadze(-2800);
        this.gameManager.pridajMaleKamikadze(maleKamikadze6);

        MaleKamikadze maleKamikadze7 = new MaleKamikadze(-2900);
        this.gameManager.pridajMaleKamikadze(maleKamikadze7);

        VelkeKamikadze velkeKamikadze3 = new VelkeKamikadze(-3000);
        this.gameManager.pridajVelkeKamikadze(velkeKamikadze3);

        Bombarder bombarder3 = new Bombarder(200, -3500);
        this.gameManager.pridajBombarder(bombarder3);

        Bombarder bombarder4 = new Bombarder(320, -3600);
        this.gameManager.pridajBombarder(bombarder4);

        Bombarder bombarder5 = new Bombarder(420, -4000);
        this.gameManager.pridajBombarder(bombarder5);


        Stihacka stihacka2 = new Stihacka(400 , -5000);
        this.gameManager.pridajStihacku(stihacka2);

        Stihacka stihacka3 = new Stihacka(300 , -5100);
        this.gameManager.pridajStihacku(stihacka3);

        Stihacka stihacka4 = new Stihacka(200 , -5300);
        this.gameManager.pridajStihacku(stihacka4);


        this.manazer.spravujObjekt(velkeKamikadze2);
        this.manazer.spravujObjekt(maleKamikadze6);
        this.manazer.spravujObjekt(maleKamikadze7);
        this.manazer.spravujObjekt(velkeKamikadze3);
        this.manazer.spravujObjekt(bombarder3);
        this.manazer.spravujObjekt(bombarder4);
        this.manazer.spravujObjekt(bombarder5);
        this.manazer.spravujObjekt(stihacka2);
        this.manazer.spravujObjekt(stihacka3);
        this.manazer.spravujObjekt(stihacka4);

        //--------RAID 2-----------------






        //--------RAID 3-----------------

        Stihacka stihacka5 = new Stihacka(100 , -6300);
        this.gameManager.pridajStihacku(stihacka5);

        Stihacka stihacka6 = new Stihacka(300 , -6700);
        this.gameManager.pridajStihacku(stihacka6);

        Bombarder bombarder6 = new Bombarder(200, -7000);
        this.gameManager.pridajBombarder(bombarder6);

        Stihacka stihacka7 = new Stihacka(400 , -7400);
        this.gameManager.pridajStihacku(stihacka7);

        MaleKamikadze maleKamikadze8 = new MaleKamikadze(-8000);
        this.gameManager.pridajMaleKamikadze(maleKamikadze8);

        MaleKamikadze maleKamikadze9 = new MaleKamikadze(-8500);
        this.gameManager.pridajMaleKamikadze(maleKamikadze9);

        MaleKamikadze maleKamikadze10 = new MaleKamikadze(-8700);
        this.gameManager.pridajMaleKamikadze(maleKamikadze10);

        MaleKamikadze maleKamikadze11 = new MaleKamikadze(-9000);
        this.gameManager.pridajMaleKamikadze(maleKamikadze11);

        MaleKamikadze maleKamikadze12 = new MaleKamikadze(-9200);
        this.gameManager.pridajMaleKamikadze(maleKamikadze12);

        MaleKamikadze maleKamikadze13 = new MaleKamikadze(-9300);
        this.gameManager.pridajMaleKamikadze(maleKamikadze13);

        Bombarder bombarder7 = new Bombarder(420, -10000);
        this.gameManager.pridajBombarder(bombarder7);

        Bombarder bombarder8 = new Bombarder(120, -10000);
        this.gameManager.pridajBombarder(bombarder8);

        Bombarder bombarder9 = new Bombarder(300, -10400);
        this.gameManager.pridajBombarder(bombarder9);

        Stihacka stihacka8 = new Stihacka(300 , -11000);
        this.gameManager.pridajStihacku(stihacka8);

        Bombarder bombarder10 = new Bombarder(420, -11500);
        this.gameManager.pridajBombarder(bombarder10);

        Bombarder bombarder11 = new Bombarder(100, -11700);
        this.gameManager.pridajBombarder(bombarder11);

        VelkeKamikadze velkeKamikadze4 = new VelkeKamikadze(-13000);
        this.gameManager.pridajVelkeKamikadze(velkeKamikadze4);

        VelkeKamikadze velkeKamikadze5 = new VelkeKamikadze(-13300);
        this.gameManager.pridajVelkeKamikadze(velkeKamikadze5);

        VelkeKamikadze velkeKamikadze6 = new VelkeKamikadze(-13400);
        this.gameManager.pridajVelkeKamikadze(velkeKamikadze6);

        VelkeKamikadze velkeKamikadze7 = new VelkeKamikadze(-13800);
        this.gameManager.pridajVelkeKamikadze(velkeKamikadze7);

        Stihacka stihacka9 = new Stihacka(300 , -15000);
        this.gameManager.pridajStihacku(stihacka9);


        this.manazer.spravujObjekt(stihacka5);
        this.manazer.spravujObjekt(stihacka6);
        this.manazer.spravujObjekt(bombarder6);
        this.manazer.spravujObjekt(stihacka7);
        this.manazer.spravujObjekt(maleKamikadze8);
        this.manazer.spravujObjekt(maleKamikadze9);
        this.manazer.spravujObjekt(maleKamikadze10);
        this.manazer.spravujObjekt(maleKamikadze11);
        this.manazer.spravujObjekt(maleKamikadze12);
        this.manazer.spravujObjekt(maleKamikadze13);
        this.manazer.spravujObjekt(bombarder7);
        this.manazer.spravujObjekt(bombarder8);
        this.manazer.spravujObjekt(bombarder9);
        this.manazer.spravujObjekt(stihacka8);
        this.manazer.spravujObjekt(bombarder10);
        this.manazer.spravujObjekt(bombarder11);
        this.manazer.spravujObjekt(velkeKamikadze4);
        this.manazer.spravujObjekt(velkeKamikadze5);
        this.manazer.spravujObjekt(velkeKamikadze6);
        this.manazer.spravujObjekt(velkeKamikadze7);
        this.manazer.spravujObjekt(stihacka9);

    }


}
