import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

//OK

public class WinScreen {
    private int skore;
    private Obrazok pozadie;
    private BlokTextu skoreText;
    private BlokTextu maximalneSkoreText;
    private String highScoreSubor = "highScore.txt";
    private boolean dosiahnuteHighSkore;


    public WinScreen(int skore){

        this.skore = skore;
        int maximalneSkore = getHighSkoreZoSuboru();
        this.pozadie = new Obrazok("assets/winMenu.png");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();
        this.skoreText = new BlokTextu("Skóre: " + skore);
        this.skoreText.zmenFont("Arial", StylFontu.BOLD,50);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(150,400);
        this.skoreText.zobraz();

        if (this.skore > maximalneSkore){
            ulozNoveHighSkore(skore);
            maximalneSkore = this.skore;
            this.dosiahnuteHighSkore = true;
        }
        if (dosiahnuteHighSkore){
            this.skoreText = new BlokTextu("NOVÉ HIGH SKÓRE");
            this.skoreText.zmenFont("Arial", StylFontu.BOLD,10);
            this.skoreText.zmenFarbu("green");
            this.skoreText.zmenPolohu(220,415);
            this.skoreText.zobraz();
        }
        this.skoreText = new BlokTextu("Maximalne skóre: " + maximalneSkore);
        this.skoreText.zmenFont("Arial", StylFontu.BOLD,10);
        this.skoreText.zmenFarbu("black");
        this.skoreText.zmenPolohu(220,450);
        this.skoreText.zobraz();

    }

    public void ulozNoveHighSkore(int skore){
        try (PrintWriter zapisovac = new PrintWriter(this.highScoreSubor)){
            zapisovac.println(skore);
        } catch (FileNotFoundException e) {
            System.out.println("Subor sa nenasiel.");
        }
    }

    public int getHighSkoreZoSuboru(){
        File subor = new File(this.highScoreSubor);
        if (!subor.exists()){
            ulozNoveHighSkore(0);
            return 0;
        }
        try (Scanner scanner = new Scanner(subor)){
            if (scanner.hasNextInt()){
                return scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Subor sa nenasiel.");
        }
        return 0;
    }
}
