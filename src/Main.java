import  fri.shapesge.Manazer;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();



        new HraciaPlocha();
        Lod lod = new Lod();
        gameManager.pridajLod(lod);
        Hrac Hrac = new Hrac(gameManager);
        Bombarder bombarder1 = new Bombarder(100,-230);
        gameManager.pridajBombarder(bombarder1);
        Bombarder bombarder2 = new Bombarder(300,-30);
        gameManager.pridajBombarder(bombarder2);
        MaleKamikadze maleKamikadze1 = new MaleKamikadze(-190);
        gameManager.pridajMaleKamikadze(maleKamikadze1);
        VelkeKamikadze velkeKamikadze = new VelkeKamikadze(-20);
        gameManager.pridajVelkeKamikadze(velkeKamikadze);




        Manazer manazer = new Manazer();
        manazer.spravujObjekt(Hrac);
        manazer.spravujObjekt(gameManager);
        manazer.spravujObjekt(bombarder1);
        manazer.spravujObjekt(bombarder2);
        manazer.spravujObjekt(maleKamikadze1);
        manazer.spravujObjekt(velkeKamikadze);






    }
}