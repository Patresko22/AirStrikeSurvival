import fri.shapesge.Obrazok;
import fri.shapesge.DataObrazku;

public class Lod {
    private Obrazok pozadie;
    private DataObrazku dataObrazku;

    public Lod(){
        this.pozadie = new Obrazok("assets/lod.jpg");
        this.pozadie.zmenPolohu(0,670);
        this.pozadie.zobraz();

    }
}
