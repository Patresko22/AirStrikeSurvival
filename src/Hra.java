import fri.shapesge.Manazer;

public class Hra {


    public Hra(){

        HraciaPlocha plocha = new HraciaPlocha();
        GameManager gameManager = new GameManager(plocha, 500);

        Lod lod = new Lod();
        gameManager.pridajLod(lod);
        Hrac Hrac = new Hrac(gameManager);
        gameManager.nastavHraca(Hrac);
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(Hrac);
        manazer.spravujObjekt(gameManager);



        //--------RAID 1-----------------
        MaleKamikadze maleKamikadze = new MaleKamikadze(-230);
        gameManager.pridajMaleKamikadze(maleKamikadze);

        MaleKamikadze maleKamikadze1 = new MaleKamikadze(-260);
        gameManager.pridajMaleKamikadze(maleKamikadze1);

        MaleKamikadze maleKamikadze2 = new MaleKamikadze(-430);
        gameManager.pridajMaleKamikadze(maleKamikadze2);

        MaleKamikadze maleKamikadze3 = new MaleKamikadze(-460);
        gameManager.pridajMaleKamikadze(maleKamikadze3);

        MaleKamikadze maleKamikadze4 = new MaleKamikadze(-650);
        gameManager.pridajMaleKamikadze(maleKamikadze4);

        MaleKamikadze maleKamikadze5 = new MaleKamikadze(-700);
        gameManager.pridajMaleKamikadze(maleKamikadze5);

        VelkeKamikadze velkeKamikadze = new VelkeKamikadze(-1000);
        gameManager.pridajVelkeKamikadze(velkeKamikadze);

        Bombarder bombarder1 = new Bombarder(100,-1100);
        gameManager.pridajBombarder(bombarder1);
        Bombarder bombarder2 = new Bombarder(300,-1150);
        gameManager.pridajBombarder(bombarder2);


        Stihacka stihacka = new Stihacka(100 ,-1700);
        gameManager.pridajStihacku(stihacka);



        manazer.spravujObjekt(maleKamikadze);
        manazer.spravujObjekt(maleKamikadze1);
        manazer.spravujObjekt(maleKamikadze2);
        manazer.spravujObjekt(maleKamikadze3);
        manazer.spravujObjekt(maleKamikadze4);
        manazer.spravujObjekt(maleKamikadze5);
        manazer.spravujObjekt(velkeKamikadze);
        manazer.spravujObjekt(bombarder1);
        manazer.spravujObjekt(bombarder2);
        manazer.spravujObjekt(stihacka);

        //--------RAID 1-----------------




        //--------RAID 2-----------------




        VelkeKamikadze velkeKamikadze2 = new VelkeKamikadze(-2700);
        gameManager.pridajVelkeKamikadze(velkeKamikadze2);

        MaleKamikadze maleKamikadze6 = new MaleKamikadze(-2800);
        gameManager.pridajMaleKamikadze(maleKamikadze6);

        MaleKamikadze maleKamikadze7 = new MaleKamikadze(-2900);
        gameManager.pridajMaleKamikadze(maleKamikadze7);

        VelkeKamikadze velkeKamikadze3 = new VelkeKamikadze(-3000);
        gameManager.pridajVelkeKamikadze(velkeKamikadze3);

        Bombarder bombarder3 = new Bombarder(200,-3500);
        gameManager.pridajBombarder(bombarder3);

        Bombarder bombarder4 = new Bombarder(320,-3600);
        gameManager.pridajBombarder(bombarder4);

        Bombarder bombarder5 = new Bombarder(420,-4000);
        gameManager.pridajBombarder(bombarder5);


        Stihacka stihacka2 = new Stihacka(400 ,-5000);
        gameManager.pridajStihacku(stihacka2);

        Stihacka stihacka3 = new Stihacka(300 ,-5100);
        gameManager.pridajStihacku(stihacka3);

        Stihacka stihacka4 = new Stihacka(200 ,-5300);
        gameManager.pridajStihacku(stihacka4);


        manazer.spravujObjekt(velkeKamikadze2);
        manazer.spravujObjekt(maleKamikadze6);
        manazer.spravujObjekt(maleKamikadze7);
        manazer.spravujObjekt(velkeKamikadze3);
        manazer.spravujObjekt(bombarder3);
        manazer.spravujObjekt(bombarder4);
        manazer.spravujObjekt(bombarder5);
        manazer.spravujObjekt(stihacka2);
        manazer.spravujObjekt(stihacka3);
        manazer.spravujObjekt(stihacka4);

        //--------RAID 2-----------------






        //--------RAID 3-----------------

        Stihacka stihacka5 = new Stihacka(100 ,-6300);
        gameManager.pridajStihacku(stihacka5);

        Stihacka stihacka6 = new Stihacka(300 ,-6700);
        gameManager.pridajStihacku(stihacka6);

        Bombarder bombarder6 = new Bombarder(200,-7000);
        gameManager.pridajBombarder(bombarder6);

        Stihacka stihacka7 = new Stihacka(400 ,-7400);
        gameManager.pridajStihacku(stihacka7);

        MaleKamikadze maleKamikadze8 = new MaleKamikadze(-8000);
        gameManager.pridajMaleKamikadze(maleKamikadze8);

        MaleKamikadze maleKamikadze9 = new MaleKamikadze(-8500);
        gameManager.pridajMaleKamikadze(maleKamikadze9);

        MaleKamikadze maleKamikadze10 = new MaleKamikadze(-8700);
        gameManager.pridajMaleKamikadze(maleKamikadze10);

        MaleKamikadze maleKamikadze11 = new MaleKamikadze(-9000);
        gameManager.pridajMaleKamikadze(maleKamikadze11);

        MaleKamikadze maleKamikadze12 = new MaleKamikadze(-9200);
        gameManager.pridajMaleKamikadze(maleKamikadze12);

        MaleKamikadze maleKamikadze13 = new MaleKamikadze(-9300);
        gameManager.pridajMaleKamikadze(maleKamikadze13);

        Bombarder bombarder7 = new Bombarder(420,-10000);
        gameManager.pridajBombarder(bombarder7);

        Bombarder bombarder8 = new Bombarder(120,-10000);
        gameManager.pridajBombarder(bombarder8);

        Bombarder bombarder9 = new Bombarder(300,-10400);
        gameManager.pridajBombarder(bombarder9);

        Stihacka stihacka8 = new Stihacka(300 ,-11000);
        gameManager.pridajStihacku(stihacka8);

        Bombarder bombarder10 = new Bombarder(420,-11500);
        gameManager.pridajBombarder(bombarder10);

        Bombarder bombarder11 = new Bombarder(100,-11700);
        gameManager.pridajBombarder(bombarder11);

        VelkeKamikadze velkeKamikadze4 = new VelkeKamikadze(-13000);
        gameManager.pridajVelkeKamikadze(velkeKamikadze4);

        VelkeKamikadze velkeKamikadze5 = new VelkeKamikadze(-13300);
        gameManager.pridajVelkeKamikadze(velkeKamikadze5);

        VelkeKamikadze velkeKamikadze6 = new VelkeKamikadze(-13400);
        gameManager.pridajVelkeKamikadze(velkeKamikadze6);

        VelkeKamikadze velkeKamikadze7 = new VelkeKamikadze(-13800);
        gameManager.pridajVelkeKamikadze(velkeKamikadze7);

        Stihacka stihacka9 = new Stihacka(300 ,-15000);
        gameManager.pridajStihacku(stihacka9);


        manazer.spravujObjekt(stihacka5);
        manazer.spravujObjekt(stihacka6);
        manazer.spravujObjekt(bombarder6);
        manazer.spravujObjekt(stihacka7);
        manazer.spravujObjekt(maleKamikadze8);
        manazer.spravujObjekt(maleKamikadze9);
        manazer.spravujObjekt(maleKamikadze10);
        manazer.spravujObjekt(maleKamikadze11);
        manazer.spravujObjekt(maleKamikadze12);
        manazer.spravujObjekt(maleKamikadze13);
        manazer.spravujObjekt(bombarder7);
        manazer.spravujObjekt(bombarder8);
        manazer.spravujObjekt(bombarder9);
        manazer.spravujObjekt(stihacka8);
        manazer.spravujObjekt(bombarder10);
        manazer.spravujObjekt(bombarder11);
        manazer.spravujObjekt(velkeKamikadze4);
        manazer.spravujObjekt(velkeKamikadze5);
        manazer.spravujObjekt(velkeKamikadze6);
        manazer.spravujObjekt(velkeKamikadze7);
        manazer.spravujObjekt(stihacka9);













        //--------RAID 3-----------------





















    }





}
