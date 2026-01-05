import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;


public class HraciaPlocha {
    private Obrazok pozadie;
    private DataObrazku dataObrazku;
    private BlokTextu skore;
    private Obrazok hornyPanel;

    public HraciaPlocha(){
        this.pozadie = new Obrazok("assets/pozadie.jpg");
        this.pozadie.zmenPolohu(0,0);
        this.pozadie.zobraz();
        this.hornyPanel = new Obrazok("assets/hornyPanel.png");
        this.hornyPanel.zmenPolohu(0,0);
        this.hornyPanel.zobraz();
        this.skore = new BlokTextu("Skóre: " + 0);
        this.skore.zmenFont("Arial", StylFontu.BOLD,20);
        this.skore.zmenFarbu("blue");
        this.skore.zmenPolohu(410,27);
        this.skore.zobraz();
    }


}
