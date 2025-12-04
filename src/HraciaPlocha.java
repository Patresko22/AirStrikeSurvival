import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;


public class HraciaPlocha {
    private Obrazok pozadie;
    private DataObrazku dataObrazku;

    public HraciaPlocha(){
        this.pozadie = new Obrazok("assets/pozadie.jpg");
        this.pozadie.zmenPolohu(0,0);
        this.pozadie.zobraz();

    }
}
