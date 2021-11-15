package Kerho;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Paivat luokka
 * TODO: crc kolrtin sisältö
 * @author Karel
 * @version 5.11.2021
 * @version 15.11.2021
 *
 */
public class Paivat {
    
    private Collection<Paiva> alkiot = new ArrayList<Paiva>();
    
    
    /**
     * Lisää päivän
     * TODO: testit
     * @param paiv päivä joka lisätään
     */
    public void lisaa(Paiva paiv) {
        alkiot.add(paiv);
    }
    
    
    /**
     * Etsitään pelurin päivät
     * @param id pelurin id
     * @return lista päivistä
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  
     *  Paivat paivat = new Paivat();
     *  Paiva maanantai2 = new Paiva(2); paivat.lisaa(maanantai2);
     *  Paiva maanantai1 = new Paiva(1); paivat.lisaa(maanantai1);
     *  Paiva tiistai2 = new Paiva(2); paivat.lisaa(tiistai2);
     *  Paiva keskiviikko1 = new Paiva(1); paivat.lisaa(keskiviikko1);
     *  Paiva perjantai2 = new Paiva(2); paivat.lisaa(perjantai2);
     *  Paiva maanantai5 = new Paiva(5); paivat.lisaa(maanantai5);
     *  
     *  List<Paiva> loytyneet;
     *  loytyneet = paivat.annaPaivat(3);
     *  loytyneet.size() === 0;
     *  loytyneet = paivat.annaPaivat(1);
     *  loytyneet.size() === 2;
     *  loytyneet.get(0) == maanantai1 === true;
     *  loytyneet.get(1) == keskiviikko1 === true;
     *  loytyneet = paivat.annaPaivat(5);
     *  loytyneet.size() === 1;
     *  loytyneet.get(0) == maanantai5 === true;
     * </pre>
     */
    public List<Paiva> annaPaivat(int id) {
        List<Paiva> loydetyt = new ArrayList<Paiva>();
        
        for (Paiva paiv : alkiot) {
            if(paiv.getPeluriId() == id) loydetyt.add(paiv);
        }
        
        return loydetyt;
    }
    
    
    public void poistaPelurinPaivat(int peluriId) {
        Collection<Paiva> poistettavat = new ArrayList<Paiva>();
        for (Paiva paiv : alkiot) {
            if(paiv.getPeluriId() == peluriId) poistettavat.add(paiv);
        }
        alkiot.removeAll(poistettavat);
    }
    
    
    /**
     * 
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Paivat paivat = new Paivat();
        Paiva maanantaiKaveri = new Paiva();
        maanantaiKaveri.taytaTestiTiedoilla(2, "Maanantai");
        Paiva tiistaiBen = new Paiva();
        tiistaiBen.taytaTestiTiedoilla(1, "Tiistai");
        Paiva tiistaiKaveri = new Paiva();
        tiistaiKaveri.taytaTestiTiedoilla(2, "Tiistai");
        Paiva sunnuntaiKaveri = new Paiva();
        sunnuntaiKaveri.taytaTestiTiedoilla(2, "Sunnuntai");
        
        paivat.lisaa(maanantaiKaveri);
        paivat.lisaa(tiistaiBen);
        paivat.lisaa(tiistaiKaveri);
        paivat.lisaa(sunnuntaiKaveri);
        
        System.out.println("================= Harrastukset testi =====================");
        
        List<Paiva> paivat2 = paivat.annaPaivat(2);
        
        for (Paiva paiv : paivat2) {
            System.out.print(paiv.getPeluriId()+" ");
            paiv.tulosta(System.out);
        }
    }
}
