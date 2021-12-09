package Kerho;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Paivat luokka
 * - Pitää yllä varsinaista päivärekisteriä eli osaa lisätä sekä muokata päivää
 * - Lukee ja kirjoittaa paivat tiedostoon
 * - osaa etsiä
 * 
 * @author Karel
 * @version 5.11.2021
 * @version 15.11.2021
 * @version 28.11.2021
 * @version 1.12.2021
 * @version 9.12.2021
 */
public class Paivat {
    
    /**
     * Lista päivistä jota luokka ylläpitää
     */
    private Collection<Paiva> alkiot = new ArrayList<Paiva>();
    
    
    /**
     * Tallentaa päivät
     * -Testattu Kerho-luokassa
     * @param tiedNimi Tiedoston nimi johon tallennetaan
     * @throws IOException jos tiedostoa ei voitu tallentaa
     */
    public void tallenna(String tiedNimi) throws IOException {
        //Varmuuskopioi vanhan tiedoston ennen uuden tallentamista
        File tiedBak = new File(tiedNimi+".bak");
        File tied = new File(tiedNimi+".dat");
        tiedBak.delete();
        tied.renameTo(tiedBak);
        
        //Kirjoittaa uuden tiedoston
        try (PrintWriter fo = new PrintWriter(new FileWriter(tied.getCanonicalPath()))){
            for (Paiva paiva : alkiot) {
                fo.println(paiva.toString());
            }
        } catch (IOException e) {
            System.err.println("Päiviä ei voitu tallentaa! "+ e.getMessage());
        }
    }
    
    
    /**
     * Lukee paivat tiedostosta
     * -Testattu Kerho-luokassa
     * @param tiedNimi tiedostopolku josta luetaan
     */
    public void lueTiedostosta(String tiedNimi) {

        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)), "UTF-8")){
            while (fi.hasNextLine()) {
                lisaa(new Paiva(fi.nextLine()));
            }
        } catch (FileNotFoundException e) {

             System.err.println("Tiedostoa ei voitu lukea! "+e.getMessage());
        }
        

    }
    
    
    /**
     * Lisää päivän
     * -Testattu Kerho-luokassa
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
     *  Paiva maanantai2 = new Paiva("2|00|00|00|00|0"); paivat.lisaa(maanantai2);
     *  Paiva maanantai1 = new Paiva("1|00|00|00|00|0"); paivat.lisaa(maanantai1);
     *  Paiva tiistai2 = new Paiva("2|00|00|00|00|1"); paivat.lisaa(tiistai2);
     *  Paiva keskiviikko1 = new Paiva("1|00|00|00|00|2"); paivat.lisaa(keskiviikko1);
     *  Paiva perjantai2 = new Paiva("2|00|00|00|00|4"); paivat.lisaa(perjantai2);
     *  Paiva maanantai5 = new Paiva("5|00|00|00|00|0"); paivat.lisaa(maanantai5);
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
    
    
    /**
     * Poistaa kaikki tietyn pelurin päivät listasta
     * -Testattu Kerho-luokassa
     * @param peluriId Pelurin id jonka päivät poistetaan
     */
    public void poistaPelurinPaivat(int peluriId) {
        //Väliaikainen lista johon kerätään poistettavat päivät
        Collection<Paiva> poistettavat = new ArrayList<Paiva>();
        //Poistetaan kaikki samat alkiot kun verrataan poistettavat listaa alkioihin
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
