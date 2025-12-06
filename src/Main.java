import  fri.shapesge.Manazer;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();



        new HraciaPlocha();
        Lod lod = new Lod();
        gameManager.pridajLod(lod);
        Hrac Hrac = new Hrac(gameManager);
        Bombarder bombarder1 = new Bombarder(50,50);
        gameManager.pridajBombarder(bombarder1);


        Manazer manazer = new Manazer();
        manazer.spravujObjekt(Hrac);
        manazer.spravujObjekt(gameManager);
        manazer.spravujObjekt(bombarder1);








    }
}