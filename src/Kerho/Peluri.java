package Kerho;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

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
 * @version 29.11.2021
 * @version 30.11.2021
 * @version 7.12.2021
 * @version 8.12.2021
 * @version 9.12.2021
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
     * Alustaa pelurin täydellisesti
     * @param nimi Pelurin nimi
     * @param pNimi Pelurin pelinimi
     * @param tTila Pelurin käytössä oleva tallennustila
     * @param puh Pelurin puhelinnumero
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri("Ben Peluri", "Ben_P", "1000", "0501231234");
     *  ben1.rekisteroi();
     *  int n1 = ben1.getPeluriId();
     *  ben1.toString() === n1+"|Ben Peluri|Ben_P|1000|0501231234";
     *  
     *  Peluri ben2 = new Peluri("Ben Pelaaja", "Ben!P2", "", "");
     *  ben2.rekisteroi();
     *  int n2 = ben2.getPeluriId();
     *  ben2.toString() === n2+"|Ben Pelaaja|Ben!P2|0|";
     * </pre>
     */
    public Peluri(String nimi, String pNimi, String tTila, String puh) {
        this.nimi = nimi;
        this.pelaajaNimi = pNimi;
        setTallennusTila(tTila);
        this.puhelin = puh;
    }
    
    
    /**
     * Alustaa pelurin stringillä id|Nimi|P-nimi|T-tila|Puh
     * @param s stringi
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234");
     *  String s1 = ben1.toString();
     *  s1.equals("1|Ben Peluri|Ben_P|1000|0501231234") === true;
     *  ben1 = new Peluri(s1);
     *  ben1.toString().equals(s1) === true;
     *  
     *  Peluri ben2 = new Peluri("5|Ben Pelaaja|Ben!P2|0|");
     *  String s2 = ben2.toString();
     *  s2.equals("5|Ben Pelaaja|Ben!P2|0|") === true;
     *  ben2 = new Peluri(s2);
     *  ben2.toString().equals(s2) === true;
     *  
     *  Peluri ben3 = new Peluri("3|Ben Pelaaja|Ben!P2|0|");
     *  String s3 = ben3.toString();
     *  s3.equals("3|Ben Pelaaja|Ben!P2|0|") === true;
     *  ben3 = new Peluri(s1);
     *  ben3.toString().equals(s1) === true;
     *  
     *  Peluri ben4 = new Peluri();
     *  ben4.rekisteroi();
     *  
     *  int n1 = ben2.getPeluriId();
     *  int n2 = ben4.getPeluriId();
     *  n2 === n1+1;
     * </pre>
     */
    public Peluri(String s) {
        parse(s);
    }
    
    
    /**
     * Parsii pelurin tiedot stringistä id|Nimi|P-nimi|T-tila|Puh
     * -Testattu Peluri(String s) konstruktorissa
     * @param s id|Nimi|P-nimi|T-tila|Puh
     */
    private void parse(String s) {
        StringBuilder sb = new StringBuilder(s);
        peluriId = Mjonot.erotaInt(sb, 0);
        sb.deleteCharAt(0);
        nimi = Mjonot.erota(sb, '|');
        pelaajaNimi = Mjonot.erota(sb,'|');
        setTallennusTila(Mjonot.erota(sb,'|'));
        puhelin = Mjonot.erota(sb, '|');
        if (peluriId >= seuraavaId) seuraavaId = peluriId + 1;
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
     * Muokkaa pelurin tiedot
     * @param tiedot taulukko tiedoista [nimi,pnimi,ttila,puh]
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234");
     *  ben1.toString() === "1|Ben Peluri|Ben_P|1000|0501231234";
     *  
     *  String[] tiedot = {"Ben Pelurimuutettu", "Ben_Muutettu", "1200", "0506664444"};
     *  ben1.muokkaaTietoja(tiedot);
     *  ben1.toString() === "1|Ben Pelurimuutettu|Ben_Muutettu|1200|0506664444";
     * </pre>
     */
    public void muokkaaTietoja(String[] tiedot) {
        this.nimi = tiedot[0];
        this.pelaajaNimi = tiedot[1];
        setTallennusTila(tiedot[2]);
        this.puhelin = tiedot[3];
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
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234");
     *  ben1.getNimi().equals("Ben Peluri") === true;
     * </pre>
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Palauttaa pelurin pelaajanimen
     * @return Pelurin pelaajanimi
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234");
     *  ben1.getPNimi().equals("Ben_P") === true;
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
     *  Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234");
     *  ben1.getTTila() === 1000;
     *  
     *  Peluri ben2 = new Peluri("1|Ben Peluri|Ben_P||0501231234");
     *  ben2.getTTila() === 0;
     * </pre>
     */
    public int getTTila() {
        return this.tallennusTila;
    }
    
    
    /**
     * Palauttaa pelurin puhelinnumeron
     * @return puhelinnumero
     * @example
     * <pre name="test">
     *  Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234");
     *  ben1.getPuh().equals("0501231234") === true;
     *  
     *  Peluri ben2 = new Peluri("1|Ben Peluri|Ben_P||");
     *  ben2.getPuh().equals("") === true;
     * </pre>
     */
    public String getPuh() {
        return this.puhelin;
    }
    
    
    /**
     * Asettaa tallennustilan stringistä
     * -Testattu Peluri(String s) konstruktorissa
     * @param s tallennustila stringinä
     */
    private void setTallennusTila(String s) {
        if(s.equals("")) {
            this.tallennusTila = 0;
        } else {
            this.tallennusTila = Integer.parseInt(s);
        }
    }
    
    
    @Override
    public String toString() {
        return peluriId+"|"+nimi+"|"+pelaajaNimi+"|"+tallennusTila+"|"+puhelin;
    }
    
    
    /**
     * Palauttaa pelaajan tulostettavassa muodossa
     * @return Peluri tulostettavassa muodossa
     * TODO: Testit: Peluri.toPrintable()
     */
    public String toPrintable() {
        String s = "Id: "+peluriId+"\nNimi: "+nimi+"\n"+
                "Pelinimi: "+pelaajaNimi+"\n"+
                "Puh: "+puhelin+"\n";
        return s;
    }
    
    
    /**
     * 
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P||0501231234");
        Peluri ben2 = new Peluri("2|Ben Peluri|Ben_P||0501231234");
        
        ben1.rekisteroi();
        ben2.rekisteroi();
        

        ben1.tulosta(System.out);
        

        ben2.tulosta(System.out);
    }
}
