import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/*
* Zobrazi vitaznu obrazovku po dokonceni levelu.
* Zobrazuje aktualne skore hraca.
* Zobrazuje maximalne skore hraca.
* Maximalne skore cita zo suboru v pripade prekonania
* ho prepise.
* */

public class WinScreen {
    private int skore;
    private Obrazok pozadie;
    private BlokTextu skoreText;
    private BlokTextu maximalneSkoreText;
    private String highScoreSubor = "highScore.txt";
    private boolean dosiahnuteHighSkore;

    /*
    * Vytvori a zobrazi vitaznu obrazovku s vyslenym skore.
    * Nacita a pripadne aktualizuje maximalne skore.
    * @param skore je vysledne skore hraca
    * */


    public WinScreen(int skore) {

        this.skore = skore;
        int maximalneSkore = this.getMaximalneSkoreZoSuboru();
        this.pozadie = new Obrazok("assets/winMenu.png");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();
        this.skoreText = new BlokTextu("Skóre: " + skore);
        this.skoreText.zmenFont("Arial",  StylFontu.BOLD, 50);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(150, 400);
        this.skoreText.zobraz();

        if (this.skore > maximalneSkore) {
            this.ulozNoveMaximalneSkore(skore);
            maximalneSkore = this.skore;
            this.dosiahnuteHighSkore = true;
        }
        if (this.dosiahnuteHighSkore) {
            this.skoreText = new BlokTextu("NOVÉ HIGH SKÓRE");
            this.skoreText.zmenFont("Arial",  StylFontu.BOLD, 10);
            this.skoreText.zmenFarbu("green");
            this.skoreText.zmenPolohu(220, 415);
            this.skoreText.zobraz();
        }
        this.skoreText = new BlokTextu("Maximalne skóre: " + maximalneSkore);
        this.skoreText.zmenFont("Arial",  StylFontu.BOLD, 10);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(220, 450);
        this.skoreText.zobraz();

    }

    /*
    * Ulozi nove maximalne skore do suboru.
    * @param skore je nove maximalne skore.
    * */

    public void ulozNoveMaximalneSkore(int skore) {
        try (PrintWriter zapisovac = new PrintWriter(this.highScoreSubor)) {
            zapisovac.println(skore);
        } catch (FileNotFoundException e) {
            System.out.println("Subor sa nenasiel.");
        }
    }

    /*
    * Nacita maximalne skore zo suboru.
    * Ak subor neexistuje vytvorí ho a nastavi na 0.
    * @return je ulozene maximalne skore.
    * */

    public int getMaximalneSkoreZoSuboru() {
        File subor = new File(this.highScoreSubor);
        if (!subor.exists()) {
            this.ulozNoveMaximalneSkore(0);
            return 0;
        }
        try (Scanner scanner = new Scanner(subor)) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Subor sa nenasiel.");
        }
        return 0;
    }
}
