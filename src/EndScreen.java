import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;

//OK

public class EndScreen {
    private int skore;
    private Obrazok pozadie;
    private BlokTextu skoreText;


    public EndScreen(int skore){
        this.skore = skore;
        this.pozadie = new Obrazok("assets/endMenu.png");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();

        this.skoreText = new BlokTextu("Skóre: " + skore);
        this.skoreText.zmenFont("Arial", StylFontu.BOLD,50);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(150,400);
        this.skoreText.zobraz();
    }




}
