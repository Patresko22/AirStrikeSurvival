import fri.shapesge.Obrazok;

/*
* Trieda StartScreen reprezentuje startovaciu plochu hry.
* Zobrazi uvodne menu.
* Umoznuje plochu skryt.
* */

public class StartScreen {
    private Obrazok pozadie;

    /*
    * Vytvori a zobrazi startovaciu obrazovku.
    * */

    public StartScreen(){
        this.pozadie = new Obrazok("assets/startMenu.png");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();

    }

    /*
    * Skryje startovaciu obrazovku
    * */

    public void skry(){
        this.pozadie.skry();
    }

}
