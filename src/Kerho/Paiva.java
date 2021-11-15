package Kerho;

import java.io.OutputStream;
import java.io.PrintStream;

import static Kanta.PuhelinTarkistus.rand;

/**
 * Luokka päiville
 * TODO: crc kortin sisältö
 * @author Karel
 * @version 5.11.2021
 * @version 9.11.2021
 * @version 15.11.2021
 *
 */
public class Paiva {
    
    private int peluriId;
    private String paiva;
    private String alkuAikaH;
    private String alkuAikaM;
    private String loppuAikaH;
    private String loppuAikaM;
    
    //0=maanantai -> 6=sunnuntai
    private int viikonPaiva;
    
    
    /**
     * alustetaan päivä
     */
    public Paiva() {
        //
    }
    
    
    /**
     * 
     * @param Id sdfs
     * @param alkuAH sdfs
     * @param alkuAM sdf
     * @param loppuAH sdf
     * @param loppuAM sdf
     * @param pv sdf
     */
    public Paiva(int Id, String alkuAH, String alkuAM, String loppuAH, String loppuAM, int pv) {
        viikonPaiva = pv;
        alkuAikaH = alkuAH;
        alkuAikaM = alkuAM;
        loppuAikaH = loppuAH;
        loppuAikaM = loppuAM;
        peluriId = Id;
    }
    
    
    /**
     * Alustetaan tietyn pelurin päivä
     * @param peluriId pelurin viitenumero
     */
    public Paiva(int peluriId) {
        this.peluriId = peluriId;
    }
    
    
    /**
     * Apumetodi, joka täyttää päivän valmiiksi testaamista varten
     * @param id viite peluriin jonka päivästä on kyse
     * @param pv päivä joka lisätään
     */
    public void taytaTestiTiedoilla(int id, String pv) {
        peluriId = id;
        paiva = pv;
    }
    
    
    /**
     * Apumetodi, joka täyttää päivän valmiiksi testaamista varten
     * @param id viite peluriin jonka päivästä on kyse
     */
    public void taytaTestiTiedoilla(int id) {
        peluriId = id;
    }
    
    
    /**
     * Tulostetaan päivän tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(peluriId+" "+paiva+" "+alkuAikaH+":"+alkuAikaM+" - "+loppuAikaH+":"+loppuAikaM);
    }
    
    
    /**
     * Tulostetaan päivän tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palauttaa päivän alkuajana tunnit tai minuutit
     * @param minuutit true, jos tahdotaan minuuti ja false jos tahdotaan tunnit
     * @return alkuajan tunnit tai minuutit
     */
    public String getAlkuAika(boolean minuutit) {
        if(minuutit) return alkuAikaM;
        return alkuAikaH;
    }
    
    
    /**
     * Palauttaa päivän loppuajan tunnit tai minuutit
     * @param minuutit  true jos tahdotaan minuutit ja false jos tahdotaan tunnit
     * @return loppuajan tunnit tai minuutit
     */
    public String getLoppuAika(boolean minuutit) {
        if(minuutit) return loppuAikaM;
        return loppuAikaH;
    }
    
    
    
    public void setAlkuAika(String aika, boolean minuutit) {
        if(minuutit) {
            alkuAikaM = aika;
            return;
        }
        alkuAikaH = aika;
    }
    
    
    /**
     * Palauttaa kelle pelurille päivä kuuluu
     * @return pelurin id
     * @example
     * <pre name="test">
     *  Paiva maanantai = new Paiva();
     *  maanantai.taytaTestiTiedoilla(1, "Maanantai");
     *  maanantai.getPeluriId() === 1;
     * </pre>
     */
    public int getPeluriId() {
        return this.peluriId;
    }
    
    
    
    public void setPeluriId(int id) {
        peluriId = id;
    }
    
    
    /**
     * 
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Paiva paiv = new Paiva();
        paiv.taytaTestiTiedoilla(2, "Maanantai");
        paiv.tulosta(System.out);
    }
}
