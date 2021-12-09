package Kerho;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

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
 * @version 28.11.2021
 * @version 7.12.2021
 * @version 8.12.2021
 * @version 9.12.2021
 */
public class Paiva {
    
    private int peluriId;
    private String paiva;
    private String alkuAikaH;
    private String alkuAikaM;
    private String loppuAikaH;
    private String loppuAikaM;
    
    //0=maanantai -> 6=sunnuntai
    private static final String[] paivat = {"Maanantai", "Tiistai", "Keskiviikko", "Torstai", "Perjantai", "Lauantai", "Sunnuntai"};
    
    
    /**
     * alustetaan päivä
     */
    public Paiva() {
        
    }
    
    
    /**
     * Alustaa paivan stringillä id|paiva|alkuAikaH|AlkuAikaM|LoppuAikaH|LoppuAikaM
     * @param s id|paiva|alkuAikaH|AlkuAikaM|LoppuAikaH|LoppuAikaM
     * @example
     * <pre name="test">
     *  Paiva p1 = new Paiva("5|Maanantai|12|00|13|00");
     *  String s = p1.toString();
     *  s === "5|Maanantai|12|00|13|00";
     *  Paiva p2 = new Paiva(s);
     *  p2.toString() === "5|Maanantai|12|00|13|00";
     *  
     * </pre>
     */
    public Paiva(String s) {
        parse(s);
    }
    
    
    /**
     * Alustaa paivan täydellisesti
     * @param Id sdfs
     * @param alkuAH sdfs
     * @param alkuAM sdf
     * @param loppuAH sdf
     * @param loppuAM sdf
     * @param pv sdf
     * @example
     * <pre name="test">
     *  Paiva p1 = new Paiva(5, "12","00","13","00",0);
     *  p1.toString() === "5|Maanantai|12|00|13|00";
     * </pre>
     */
    public Paiva(int Id, String alkuAH, String alkuAM, String loppuAH, String loppuAM, int pv) {
        paiva = paivat[pv];
        alkuAikaH = alkuAH;
        alkuAikaM = alkuAM;
        loppuAikaH = loppuAH;
        loppuAikaM = loppuAM;
        peluriId = Id;
    }
    
    
    /**
     * Alustetaan päivä pelurille tyhjänä
     * @param peluriId pelurin viitenumero
     * @param pv mistä indeksistä paiva otetaan 0=maanantai 6=sunnuntai
     * @example
     * <pre name="test">
     * #import java.util.ArrayList;
     *  String[] s = {"Maanantai", "Tiistai", "Keskiviikko", "Torstai", "Perjantai", "Lauantai", "Sunnuntai"};
     *  ArrayList<ArrayList<Paiva>> p = new ArrayList<ArrayList<Paiva>>();
     *  for (int i = 0; i < 4; i++) {
     *      p.add(new ArrayList<Paiva>());
     *      for (int a = 0; a < 7; a++) {
     *          p.get(i).add(new Paiva(i,a));
     *      }
     *  }
     *  for (int i = 0; i < 4; i++) {
     *      for (int a = 0; a < 7; a++) {
     *          p.get(i).get(a).toString() === i+"|"+s[a]+"|00|00|00|00";
     *      }
     *  }
     * </pre>
     */
    public Paiva(int peluriId, int pv) {
        this.peluriId = peluriId;
        this.paiva = paivat[pv];
        this.alkuAikaH = "00";
        this.alkuAikaM = "00";
        this.loppuAikaH = "00";
        this.loppuAikaM = "00";
    }
    
    
    /**
     * Parsii paivan tiedot stringistä id|paiva|alkuAikaH|AlkuAikaM|LoppuAikaH|LoppuAikaM
     * @param s id|paiva|alkuAikaH|AlkuAikaM|LoppuAikaH|LoppuAikaM
     */
    private void parse(String s) {
        StringBuilder sb = new StringBuilder(s);
        peluriId = Mjonot.erotaInt(sb, 0);
        sb.deleteCharAt(0);
        paiva = Mjonot.erota(sb, '|');
        alkuAikaH = Mjonot.erota(sb, '|');
        alkuAikaM = Mjonot.erota(sb, '|');
        loppuAikaH = Mjonot.erota(sb, '|');
        loppuAikaM = Mjonot.erota(sb, '|');
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
     * @example
     * <pre name="test">
     *  Paiva p1 = new Paiva("5|Maanantai|12|00|13|00");
     *  p1.getAlkuAika(false) === "12";
     *  p1.getAlkuAika(true) === "00";
     *  Paiva p2 = new Paiva("5|Maanantai|12|45|13|30");
     *  p2.getAlkuAika(false) === "12";
     *  p2.getAlkuAika(true) === "45";
     * </pre>
     */
    public String getAlkuAika(boolean minuutit) {
        if(minuutit) return alkuAikaM;
        return alkuAikaH;
    }
    
    
    /**
     * Palauttaa päivän loppuajan tunnit tai minuutit
     * @param minuutit  true jos tahdotaan minuutit ja false jos tahdotaan tunnit
     * @return loppuajan tunnit tai minuutit
     * @example
     * <pre name="test">
     *  Paiva p1 = new Paiva("5|Maanantai|12|00|13|00");
     *  p1.getLoppuAika(false) === "13";
     *  p1.getLoppuAika(true) === "00";
     *  Paiva p2 = new Paiva("5|Maanantai|12|45|13|30");
     *  p2.getLoppuAika(false) === "13";
     *  p2.getLoppuAika(true) === "30";
     * </pre>
     */
    public String getLoppuAika(boolean minuutit) {
        if(minuutit) return loppuAikaM;
        return loppuAikaH;
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
     * Palauttaa paiva stringinä
     * -Testattu Paiva(String) konstruktorissa
     */
    @Override
    public String toString() {
        return peluriId+"|"+paiva+"|"+alkuAikaH+"|"+alkuAikaM+"|"+loppuAikaH+"|"+loppuAikaM;
    }
    
    
    /**
     * Palauttaa päivän printattavassa muodossa
     * @return päivä printattavassa muodossa
     * TODO: Testit: Paiva.toPrintable
     */
    public String toPrintable() {
        if (alkuAikaH.equals("00") && alkuAikaM.equals("00") && loppuAikaH.equals("00") && loppuAikaM.equals("00")) return String.format("%-13s", (paiva+":"))+"Ei käy!\n";
        String s = String.format("%-13s", (paiva+":"))+alkuAikaH+":"+alkuAikaM+" - "+loppuAikaH+":"+loppuAikaM+"\n";
        
        return s;
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
