import  fri.shapesge.Manazer;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();



        new HraciaPlocha();
        Lod lod = new Lod();
        gameManager.pridajLod(lod);
        Hrac Hrac = new Hrac(gameManager);
        Bombarder bombarder1 = new Bombarder(100,-210);
        gameManager.pridajBombarder(bombarder1);

        Bombarder bombarder2 = new Bombarder(300,-310);
        gameManager.pridajBombarder(bombarder2);

        Bombarder bombarder3 = new Bombarder(200,-810);
        gameManager.pridajBombarder(bombarder3);

        Bombarder bombarder4 = new Bombarder(300,-910);
        gameManager.pridajBombarder(bombarder4);

        Bombarder bombarder5 = new Bombarder(600,-1310);
        gameManager.pridajBombarder(bombarder5);

        Bombarder bombarder6 = new Bombarder(100,-1510);
        gameManager.pridajBombarder(bombarder6);

        Bombarder bombarder7 = new Bombarder(800,-1610);
        gameManager.pridajBombarder(bombarder7);

        Bombarder bombarder8 = new Bombarder(600,-1910);
        gameManager.pridajBombarder(bombarder8);


        Manazer manazer = new Manazer();
        manazer.spravujObjekt(Hrac);
        manazer.spravujObjekt(gameManager);
        manazer.spravujObjekt(bombarder1);
        manazer.spravujObjekt(bombarder2);
        manazer.spravujObjekt(bombarder3);
        manazer.spravujObjekt(bombarder4);
        manazer.spravujObjekt(bombarder5);
        manazer.spravujObjekt(bombarder6);
        manazer.spravujObjekt(bombarder7);
        manazer.spravujObjekt(bombarder8);








    }
}