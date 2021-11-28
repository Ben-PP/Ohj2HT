package Kerho;

import java.io.IOException;
import java.util.List;

/**
 * Kerholuokka
 * 
 * -    Huolehtii pelurit ja Paivat - luokkien välisestä yhteistyöstä ja
 *      välittää näitä tietoja pyydettäessä
 * -    Lukee ja kirjoittaa kerhon tiedostoon pyytämällä apua avustajilta
 * 
 * @author Karel
 * @version 28.10.2021
 * @version 5.11.2021
 * @version 15.11.2021
 * @version 28.11.2021
 */
public class Kerho {
    
    /**
     * Kerhossa olevat peluri oliot
     */
    private final Pelurit pelurit = new Pelurit();
    /**
     * Kerhossa olevien pelureiden kaikki paiva oliot
     */
    private final Paivat paivat = new Paivat();
    
    
    /**
     * Lukee kerhon tiedot tiedostoista
     * @param tiedNimiPelurit tiedoston polku jossa pelurit sijaitsee
     * @param tiedNimiPaivat tiedoston polku jossa paivat sijaitsee
     */
    public void lueTiedostosta(String tiedNimiPelurit, String tiedNimiPaivat){
        pelurit.lueTiedostosta(tiedNimiPelurit);
        paivat.lueTiedostosta(tiedNimiPaivat);
    }
    
    
    /**
     * Tallentaa kerhon tiedot
     * @param tiedNimiPelurit tiedoston polku jossa pelurit sijaitsee
     * @param tiedNimiPaivat tiedNimiPaivat tiedoston polku jossa paivat sijaitsee
     */
    public void tallenna(String tiedNimiPelurit, String tiedNimiPaivat) {
        try {
            pelurit.tallenna(tiedNimiPelurit);
        } catch (IOException e) {
            System.err.println("Pelureita ei voitu tallentaa! " + e.getMessage());
        }
        try {
            paivat.tallenna(tiedNimiPaivat);
        } catch (IOException e){
            System.err.println("Päiviä ei voitu tallentaa! "+ e.getMessage());
        }
    }
    
    
    /**
     * Lisää pelurin kerhoon
     * @param peluri peluri joka lisätään
     * TODO: Testit: Pelurin lisääminen Kerho-luokka
     */
    public void lisaa(Peluri peluri) {
        this.pelurit.lisaa(peluri);
        for (int i = 0; i < 7; i++) {
            this.paivat.lisaa(new Paiva(peluri.getPeluriId(), i));
        }
    }
    
    
    /**
     * Lisää päivän pelurille
     * @param paiv päivä joka lisätään
     * TODO: Testit: Päivän lisääminen pelurille. Onko jo testattu?
     */
    public void lisaa(Paiva paiv) {
        paivat.lisaa(paiv);
    }
    
    
    /**
     * Poistaa kaikki pelurin päivät
     * @param peluriId Pelurin id jolta päivät tahdotaan poistaa
     */
    public void poistaPelurinPaivat(int peluriId) {
        paivat.poistaPelurinPaivat(peluriId);
    }
    
    
    /**
     * Palauttaa kerhon pelureiden määrän
     * @return Kerhon pelureiden määrä
     */
    public int getPelureita() {
        return this.pelurit.getLkm();
    }
    
    
    /**
     * Antaa kerhon i:n Pelurin
     * @param i monesko jäsen (alkaa 0:sta)
     * @return Peluri paikasta i
     */
    public Peluri getPeluri(int i) {
        return this.pelurit.anna(i);
    }
    
    
    /**
     * Palauttaa pelurin pelipäivät
     * @param peluri peluri jonka päivät halutaan
     * @return pelurin päivät
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  
     *  Kerho kerho = new Kerho();
     *  Peluri ben1 = new Peluri(), ben2 = new Peluri(), ben3 = new Peluri();
     *  ben1.rekisteroi(); ben2.rekisteroi(); ben3.rekisteroi();
     *  int id1 = ben1.getPeluriId();
     *  int id2 = ben2.getPeluriId();
     *  Paiva maanantai1 = new Paiva(id1, 0); kerho.lisaa(maanantai1);
     *  Paiva tiistai1 = new Paiva(id1, 1); kerho.lisaa(tiistai1);
     *  Paiva maanantai2 = new Paiva(id2, 0); kerho.lisaa(maanantai2);
     *  Paiva tiistai2 = new Paiva(id2, 0); kerho.lisaa(tiistai2);
     *  Paiva keskiviikko2 = new Paiva(id2, 3); kerho.lisaa(keskiviikko2);
     *  
     *  List<Paiva> loytyneet;
     *  loytyneet = kerho.getPaivat(ben3);
     *  loytyneet.size() === 0;
     *  loytyneet = kerho.getPaivat(ben1);
     *  loytyneet.size() === 2;
     *  loytyneet.get(0) == maanantai1 === true;
     *  loytyneet.get(1) == tiistai1 === true;
     *  loytyneet = kerho.getPaivat(ben2);
     *  loytyneet.size() === 3;
     *  loytyneet.get(0) == maanantai2 === true;
     * </pre>
     */
    public List<Paiva> getPaivat(Peluri peluri){
        return paivat.annaPaivat(peluri.getPeluriId());
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Kerho kerho = new Kerho();
        
        Peluri ben1 = new Peluri();
        Peluri ben2 = new Peluri();
        
        ben1.rekisteroi();
        ben1.taytaTestiTiedoilla();
        
        ben2.rekisteroi();
        ben2.taytaTestiTiedoilla();
        
        kerho.lisaa(ben1);
        kerho.lisaa(ben2);
        kerho.lisaa(ben1);
        kerho.lisaa(ben2);
        kerho.lisaa(ben1);
        kerho.lisaa(ben2);
        
        for (int i = 0; i < kerho.getPelureita(); i++) {
            Peluri peluri = kerho.getPeluri(i);
            peluri.tulosta(System.out);
        }
    
    }

}
