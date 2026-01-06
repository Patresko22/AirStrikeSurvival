import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;

/*
* Trieda EndScreen zobrazi koncovu obrazovku po prehre hraca
* Na obrazovke vykresli pozadie a dosiahnute skore.
*/
public class EndScreen {
    private int skore;
    private Obrazok pozadie;
    private BlokTextu skoreText;


    /*
    * Vytvorí a zobrazi koncovu obrazovku s danym skore.
    * @param skore je vysledne skore hraca ktore sa ma na obrazovke zobrazit.
    */

    public EndScreen(int skore) {
        this.skore = skore;
        this.pozadie = new Obrazok("assets/endMenu.png");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();

        this.skoreText = new BlokTextu("Skóre: " + skore);
        this.skoreText.zmenFont("Arial", StylFontu.BOLD, 50);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(150, 400);
        this.skoreText.zobraz();
    }




}
