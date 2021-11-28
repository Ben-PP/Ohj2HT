package Kerho;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

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
 * @version 28.10.2021
 * @version 15.11.2021
 * @version 28.11.2021
 */
public class Peluri {
    
    private int     peluriId;
    private String  nimi            = "";
    private String  pelaajaNimi     = "";
    private int     tallennusTila   = 0;
    private String  puhelin         = "";
    
    private static int seuraavaId   = 1;
    
    
    /**
     * Tyhjä alustus pelurille
     */
    public Peluri() {
        
    }
    
    
    /**
     * Alustaa pelurin stringillä id|Nimi|P-nimi|T-tila|Puh
     * @param s stringi
     */
    public Peluri(String s) {
        parse(s);
    }
    
    
    /**
     * Parsii pelurin tiedot stringistä id|Nimi|P-nimi|T-tila|Puh
     * @param s id|Nimi|P-nimi|T-tila|Puh
     * TODO: Testit: peluri.parse()
     */
    private void parse(String s) {
        StringBuilder sb = new StringBuilder(s);
        peluriId = Mjonot.erotaInt(sb, 0);
        sb.deleteCharAt(0);
        nimi = Mjonot.erota(sb, '|');
        pelaajaNimi = Mjonot.erota(sb,'|');
        tallennusTila = Mjonot.erotaInt(sb,0);
        sb.deleteCharAt(0);
        puhelin = Mjonot.erota(sb, '|');
        if (peluriId >= seuraavaId) seuraavaId = peluriId + 1;
    }
    
    // TODO: Poista: getSeuraavaId()
    @SuppressWarnings("javadoc")
    public static int getSeuraavaId() {
        return seuraavaId;
    }
    
    
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
     *  ben1.rekisteroi();
     *  Peluri ben2 = new Peluri();
     *  ben2.rekisteroi();
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
    
    
    /**
     * Palauttaa pelurin pelaajanimen
     * @return Pelurin pelaajanimi
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri();
     *  ben1.taytaTestiTiedoilla();
     *  ben1.getPNimi() === "Ben_P"
     * </pre>
     */
    public String getPNimi() {
        return this.pelaajaNimi;
    }
    
    
    /**
     * Palauttaa pelurin tallennustilan
     * @return Pelurin tallennustila
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri();
     *  ben1.taytaTestiTiedoilla();
     *  ben1.getTTila() === 1000;
     * </pre>
     */
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
     * TODO: Testit: Peluri.toString()
     */
    @Override
    public String toString() {
        return peluriId+"|"+nimi+"|"+pelaajaNimi+"|"+tallennusTila+"|"+puhelin;
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
