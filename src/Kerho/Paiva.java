package Kerho;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Luokka päiville
 * - Tietää Paivan kentät
 * - Osaa tarkistaa tietyn kentän oikeellisuuden
 * - Osaa muuttaa 1|0|12:00|14:00|...| merkkijonon päivän tiedoiksi
 * - Osaa antaa merkkijonona i:n kentän tiedot
 * - Osaa laittaa merkkijonon i:neksi kentäksi
 * 
 * @author Karel
 * @version 5.11.2021
 * @version 9.11.2021
 * @version 15.11.2021
 * TODO: Toimivaksi: Oikeellisuustarkistus
 * TODO: Toimivaksi: Merkkijonojen lukeminen ja kirjoittaminen. Paiva-luokka
 */
public class Paiva {
    
    private int peluriId;
    private String paiva;
    private String alkuAikaH;
    private String alkuAikaM;
    private String loppuAikaH;
    private String loppuAikaM;
    
    //0=maanantai -> 6=sunnuntai
    //TODO: Tarvitaanko? int viikonPaiva Paiva-luokassa
    private int viikonPaiva;
    
    
    /**
     * alustetaan päivä
     */
    public Paiva() {
        //
    }
    
    
    /**
     * Alustaa paivan täydellisesti
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
     * TODO: Tarvitaanko? Paivan alustus pelkällä pelurin id:llä
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
     * Palauttaa päivän alkuajan tunnit tai minuutit
     * @param minuutit true, jos tahdotaan minuuti ja false jos tahdotaan tunnit
     * @return alkuajan tunnit tai minuutit
     * TODO: Testit: getAlkuAika
     */
    public String getAlkuAika(boolean minuutit) {
        if(minuutit) return alkuAikaM;
        return alkuAikaH;
    }
    
    
    /**
     * Palauttaa päivän loppuajan tunnit tai minuutit
     * @param minuutit  true jos tahdotaan minuutit ja false jos tahdotaan tunnit
     * @return loppuajan tunnit tai minuutit
     * TODO: Testit: getLoppuAika
     */
    public String getLoppuAika(boolean minuutit) {
        if(minuutit) return loppuAikaM;
        return loppuAikaH;
    }
    
    
    /**
     * Asettaa alkuajan tunnit tai minuutit
     * @param aika Aika joka asetetaan
     * @param minuutit true jos tahdotaan asettaa minuutit, false jos tunnit
     * TODO: Tarvitaanko? setAlkuAika, 0 referenssiä 15.11.2021
     */
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
    
    
    /**
     * Asettaa päivän peluriId:n
     * @param id pelurin id
     * TODO: Tarvitaanko? setPeluriId, 0 referenssiä 15.11.2021
     */
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
