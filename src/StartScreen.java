import fri.shapesge.Obrazok;

//OK

public class StartScreen {
    private Obrazok pozadie;

    public StartScreen(){
        this.pozadie = new Obrazok("assets/startMenu.png");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();

    }

    public void skry(){
        this.pozadie.skry();
    }

}
