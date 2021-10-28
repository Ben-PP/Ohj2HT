package Kerho;

import java.io.OutputStream;
import java.io.PrintStream;

import static Kanta.PuhelinTarkistus.*;

/**
 * Peluri luokka
 * 
 * -    Tietää pelurin kentät (nimi, pnimi, ttila, jne.)
 * -    Osaa tarkistaa tietyn kentän oikeellisuuden (syntaksin)
 * -    Osaa muuttaa 1|Karel Peluri|...| merkkijonon pelurin tiedoiksi
 * -    Osaa antaa merkkijonona i:n kentän tiedot
 * -    Osaa laittaa merkkijonon i:neksi kentäksi
 * 
 * @author Karel
 * @version 24.10.2021
 *
 */
public class Peluri {
    
    private int     peluriId;
    private String  nimi            = "";
    private String  pelaajaNimi     = "";
    private int     tallennusTila   = 0;
    private String  puhelin         = "";
    
    private static int seuraavaId   = 1;
    
    
    /**
     * Tulostaa jäsenen tiedot
     * @param out Tietovirta johon tiedot tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", peluriId) + " " + nimi + " " + "\""+ pelaajaNimi +"\"");
        out.println(tallennusTila);
        out.println("Tallennustilaa on " + tallennusTila + " Gigatavua.");
        out.println("Puh: " + puhelin);
        out.println("-------------------------------------------------------------------");
    }
    
    
    /**
     * Tulostaa jäsenen tiedot
     * @param os Tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa pelurille uniikin id:n
     * @return Annettu id
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri();
     *  ben1.getPeluriId() === 0;
     *  ben1.rekisteroi();
     *  Peluri ben2 = new Peluri();
     *  ben2.rekisteroi();
     *  int n1 = ben1.getPeluriId();
     *  int n2 = ben2.getPeluriId();
     *  n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.peluriId = seuraavaId;
        seuraavaId++;
        return this.peluriId;
    }
    
    
    /**
     * Palauttaa pelurin id:n
     * @return Pelurin id
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri();
     *  Peluri ben2 = new Peluri();
     *  int n1 = ben1.getPeluriId();
     *  int n2 = ben2.getPeluriId();
     *  n1 === n2-1
     * </pre>
     */
    public int getPeluriId() {
        return this.peluriId;
    }
    
    
    /**
     * Palauttaa pelurin nimen
     * @return pelurin nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    
    public String getPNimi() {
        return this.pelaajaNimi;
    }
    
    
    
    public int getTTila() {
        return this.tallennusTila;
    }
    
    
    /**
     * Täyttää pelurin tiedot testitiedoilla
     */
    public void taytaTestiTiedoilla() {
        this.nimi = "Ben Peluri" + rand(1000, 9999);
        this.pelaajaNimi = "Ben_P";
        this.tallennusTila = 1000;
        this.puhelin = arvoPuhelin();
        //this.puhelin = "050 732 8211";
    }
    
    
    /**
     * 
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Peluri ben1 = new Peluri();
        Peluri ben2 = new Peluri();
        
        ben1.rekisteroi();
        ben2.rekisteroi();
        
        ben1.tulosta(System.out);
        ben1.taytaTestiTiedoilla();
        ben1.tulosta(System.out);
        
        ben2.tulosta(System.out);
        ben2.taytaTestiTiedoilla();
        ben2.tulosta(System.out);
    }
}
