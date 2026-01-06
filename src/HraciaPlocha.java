import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;


/*
* Trieda HraciaPlocha reprezentuje vizualnnu hraciu plochu.
* Zobrazuje pozadie, horny panel a textove informacie ako skore a hp lode.
* */


public class HraciaPlocha {
    private Obrazok pozadie;
    private Obrazok hornyPanel;
    private int skore;
    private int hpLode;
    private BlokTextu skoreText;
    private BlokTextu hpLodeText;

    /*
    * Vytvori hraciu plochu.
    * Zobrazi pozadie, horny panel a inicializuje textove polia pre skore a hp lode.
    * */

    public HraciaPlocha() {
        this.pozadie = new Obrazok("assets/pozadie.jpg");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();
        this.hornyPanel = new Obrazok("assets/hornyPanel.png");
        this.hornyPanel.zmenPolohu(0, 0);
        this.hornyPanel.zobraz();
        this.skoreText = new BlokTextu("Skóre: 0");
        this.skoreText.zmenFont("Arial", StylFontu.BOLD,20);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(400,27);
        this.skoreText.zobraz();
        this.hpLodeText = new BlokTextu("HP Lode: 0");
        this.hpLodeText.zmenFont("Arial", StylFontu.BOLD,10);
        this.hpLodeText.zmenFarbu("black");
        this.hpLodeText.zmenPolohu(200,25);
        this.hpLodeText.zobraz();
    }

    /*
    * Aktualizuje zobrazenu hodnotu skore na hornom paneli.
    * @param skore je aktualne skore ktore sa ma zobrazit.
    * */

    public void nastavSkore(int skore){
        this.skoreText.zmenText("Skóre: " + skore);
    }

    /*
    * Aktualizuje zobrazenu hodnotu hp lode na hornom paneli.
    * @param hp je aktualne hp lode ktore sa ma zobrazit.
    * */

    public void nastavHpLode(int hp){
        this.hpLodeText.zmenText("HP Lode: " + hp);
    }
}
