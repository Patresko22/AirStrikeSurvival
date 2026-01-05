import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;

public class WinScreen {
    private int skore;
    private Obrazok pozadie;
    private BlokTextu skoreText;
    private BlokTextu maximalneSkoreText;


    public WinScreen(int skore){
        this.skore = skore;
        this.pozadie = new Obrazok("assets/endMenu.png");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();

        this.skoreText = new BlokTextu("Skóre: " + skore);
        this.skoreText.zmenFont("Arial", StylFontu.BOLD,50);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(150,400);
        this.skoreText.zobraz();

        this.skoreText = new BlokTextu("Maximalne skóre: " + skore);
        this.skoreText.zmenFont("Arial", StylFontu.BOLD,10);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(220,450);
        this.skoreText.zobraz();
    }




}
