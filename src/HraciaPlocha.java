import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;


public class HraciaPlocha {
    private Obrazok pozadie;
    private Obrazok hornyPanel;
    private int skore;
    private int hpLode;
    private BlokTextu skoreText;
    private BlokTextu hpLodeText;

    public HraciaPlocha() {
        this.pozadie = new Obrazok("assets/pozadie.jpg");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();

        this.hornyPanel = new Obrazok("assets/hornyPanel.png");
        this.hornyPanel.zmenPolohu(0, 0);
        this.hornyPanel.zobraz();

        this.skoreText = new BlokTextu("Skóre: 0");
        this.skoreText.zmenFont("Arial", StylFontu.BOLD,20);
        this.skoreText.zmenFarbu("blue");
        this.skoreText.zmenPolohu(410,27);
        this.skoreText.zobraz();

        this.hpLodeText = new BlokTextu("HP Lode: 0");
        this.hpLodeText.zmenFont("Arial", StylFontu.BOLD,10);
        this.hpLodeText.zmenFarbu("blue");
        this.hpLodeText.zmenPolohu(200,25);
        this.hpLodeText.zobraz();



    }

    public void nastavSkore(int skore){
        this.skoreText.zmenText("Skóre: " + skore);
    }

    public void nastavHpLode(int hp){
        this.hpLodeText.zmenText("HP Lode: " + hp);
    }

}
