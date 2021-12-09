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
 * @version 29.11.2021
 * @version 1.12.2021
 * @version 7.12.2021
 * @version 8.12.2021
 * @version 9.12.2021
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
    
    private boolean pelureitaMuutettu = false;
    private boolean paiviaMuutettu = false;
    
    
    /**
     * Lukee kerhon tiedot tiedostoista
     * @param tiedNimiPelurit tiedoston polku jossa pelurit sijaitsee
     * @param tiedNimiPaivat tiedoston polku jossa paivat sijaitsee
     * @example
     * <pre name="test">
     *  #THROWS IOException
     *  #import java.io.IOException;
     *  #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *  
     *  Kerho k = new Kerho();
     *  k.lueTiedostosta(".\\testit\\nimetLukuTest.dat", ".\\testit\\paivatLukuTest.dat");
     *  k.getPelureita() === 7;
     *  List<Paiva> paivat = k.getPaivat(k.getPeluri(0));
     *  
     *  StringBuilder sb = new StringBuilder();
     *  for (Paiva paiva : paivat) {
     *      sb.append(paiva.toString());
     *  }
     *  
     *  String tulos = "1|Maanantai|12|00|23|45"+
     *                 "1|Tiistai|16|00|16|30"+
     *                 "1|Keskiviikko|00|00|00|00"+
     *                 "1|Torstai|16|45|23|30"+
     *                 "1|Perjantai|00|00|00|00"+
     *                 "1|Lauantai|18|00|20|30"+
     *                 "1|Sunnuntai|12|00|16|30";
     *  tulos.equals(sb.toString()) === true;
     *   
     *  paivat = k.getPaivat(k.getPeluri(1));
     *  sb.delete(0, sb.length());
     *  for (Paiva paiva : paivat) {
     *      sb.append(paiva.toString());
     *  }
     *  tulos = "2|Maanantai|00|00|00|00"+
     *          "2|Tiista|00|00|00|00"+
     *          "2|Keskiviikko|00|00|00|00"+
     *          "2|Torstai|00|00|00|00"+
     *          "2|Perjantai|00|00|00|00"+
     *          "2|Lauantai|18|00|20|30"+
     *          "2|Sunnunta|12|00|16|30";
     *  tulos.equals(sb.toString()) === true;
     * </pre>
     */
    public void lueTiedostosta(String tiedNimiPelurit, String tiedNimiPaivat){
        pelurit.lueTiedostosta(tiedNimiPelurit);
        paivat.lueTiedostosta(tiedNimiPaivat);
    }
    
    
    /**
     * Tallentaa kerhon tiedot
     * @param tiedNimiPelurit tiedoston polku jossa pelurit sijaitsee
     * @param tiedNimiPaivat tiedNimiPaivat tiedoston polku jossa paivat sijaitsee
     * @example
     * <pre name="test">
     *  #THROWS IOException
     *  #import java.io.IOException;
     *  #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *  VertaaTiedosto.kirjoitaTiedosto(".\\testit\\nimet.dat",
     *      "1|Ben One|Ben_O|1000|0509289339\n"+
     *      "2|Ben Two|Ben_T|1500|0509289339\n");
     *  String tulosNimet =
     *      "1|Ben One|Ben_O|1000|0509289339\n"+
     *      "2|Ben Two|Ben_T|1500|0509289000\n";
     *  String tulosPaivat =
     *      "1|Maanantai|12|00|13|00\n"+
     *      "1|Tiistaitai|12|00|17|00\n"+
     *      "1|Keskiviikkko|12|00|17|00\n"+
     *      "1|Torstai|12|00|17|00\n"+
     *      "1|Perjantai|12|00|17|00\n"+
     *      "1|Lauantai|00|00|00|00\n"+
     *      "1|Sunnnuntai|00|00|00|00\n"+
     *      "2|Maanantai|12|00|13|00\n"+
     *      "2|Tiistaitai|00|00|00|00\n"+
     *      "2|Keskiviikkko|12|00|17|00\n"+
     *      "2|Torstai|12|00|17|00\n"+
     *      "2|Perjantai|00|00|00|00\n"+
     *      "2|Lauantai|12|00|17|00\n"+
     *      "2|Sunnnuntai|12|00|17|00\n";
     *      
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimet.dat");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivat.dat");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimet.bak");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivat.bak");
     *  
     *  Kerho k = new Kerho();
     *  Peluri benOne = new Peluri("1|Ben One|Ben_O|1000|0509289339");
     *  Peluri benTwo = new Peluri("2|Ben Two|Ben_T|1500|0509289000");
     *  k.lisaa(benOne);
     *  k.lisaa(benTwo);
     *  
     *  Paiva[] paivat = {
     *      new Paiva("1|Maanantai|12|00|13|00"),
     *      new Paiva("1|Tiistaitai|12|00|17|00"),
     *      new Paiva("1|Keskiviikkko|12|00|17|00"),
     *      new Paiva("1|Torstai|12|00|17|00"),
     *      new Paiva("1|Perjantai|12|00|17|00"),
     *      new Paiva("1|Lauantai|00|00|00|00"),
     *      new Paiva("1|Sunnnuntai|00|00|00|00"),
     *      new Paiva("2|Maanantai|12|00|13|00"),
     *      new Paiva("2|Tiistaitai|00|00|00|00"),
     *      new Paiva("2|Keskiviikkko|12|00|17|00"),
     *      new Paiva("2|Torstai|12|00|17|00"),
     *      new Paiva("2|Perjantai|00|00|00|00"),
     *      new Paiva("2|Lauantai|12|00|17|00"),
     *      new Paiva("2|Sunnnuntai|12|00|17|00")
     *      };
     *  
     *  k.poistaPelurinPaivat(benOne.getPeluriId());
     *  k.poistaPelurinPaivat(benTwo.getPeluriId());
     *  for (int i = 0; i < paivat.length; i++) {
     *      k.lisaa(paivat[i]);
     *  }
     *  k.tallenna(".\\testit\\nimet", ".\\testit\\paivat");
     *  
     *  VertaaTiedosto.vertaaFileString(".\\testit\\nimet.dat", tulosNimet) === null;
     *  VertaaTiedosto.vertaaFileString(".\\testit\\paivat.dat", tulosPaivat) === null;
     *  
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimet.dat");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivat.dat");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimet.bak");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivat.bak");
     * </pre>
     */
    public void tallenna(String tiedNimiPelurit, String tiedNimiPaivat) {
        try {
            if (pelureitaMuutettu) pelurit.tallenna(tiedNimiPelurit);
        } catch (IOException e) {
            System.err.println("Pelureita ei voitu tallentaa! " + e.getMessage());
        }
        try {
            if (paiviaMuutettu) paivat.tallenna(tiedNimiPaivat);
        } catch (IOException e){
            System.err.println("Päiviä ei voitu tallentaa! "+ e.getMessage());
        }
        pelureitaMuutettu = false;
        paiviaMuutettu = false;
    }
    
    
    /**
     * Lisää pelurin kerhoon
     * @param peluri peluri joka lisätään
     * @example
     * <pre name="test">
     *  Kerho k = new Kerho();
     *  Peluri ben1 = new Peluri();
     *  ben1.rekisteroi();
     *  k.lisaa(ben1);
     *  
     *  Peluri ben2 = new Peluri();
     *  ben2.rekisteroi();
     *  
     *  ben2.equals(k.getPeluri(0)) === false;
     *  k.getPeluri(0).equals(ben1) === true;
     * </pre>
     */
    public void lisaa(Peluri peluri) {
        this.pelurit.lisaa(peluri);
        for (int i = 0; i < 7; i++) {
            this.paivat.lisaa(new Paiva(peluri.getPeluriId(), i));
        }
        pelureitaMuutettu = true;
        paiviaMuutettu = true;
    }
    
    
    /**
     * Lisää päivän pelurille
     * -Testattu lueTiedostosta() testeissä
     * @param paiv päivä joka lisätään
     * </pre>
     */
    public void lisaa(Paiva paiv) {
        paivat.lisaa(paiv);
        paiviaMuutettu = true;
    }
    
    
    /**
     * Poistaa kaikki pelurin päivät
     * -Testattu tallenna() testeissä
     * @param peluriId Pelurin id jolta päivät tahdotaan poistaa
     */
    public void poistaPelurinPaivat(int peluriId) {
        paivat.poistaPelurinPaivat(peluriId);
        paiviaMuutettu = true;
    }
    
    
    /**
     * Poistaa halutun pelurin
     * @param peluriId Poistettavan pelurin id
     * @example
     * <pre name="test">
     *  Kerho k = new Kerho();
     *  Peluri ben1 = new Peluri("1|Ben One|Ben_O|1000|0509289339");
     *  k.lisaa(ben1);
     *  Peluri ben2 = new Peluri("2|Ben Two|Ben_T|1000|0509289000");
     *  k.lisaa(ben2);
     *  k.getPelureita() === 2;
     *  k.getPeluri(0).equals(ben1) === true;
     *  k.getPeluri(1).equals(ben2) === true;
     *  List<Paiva> l = k.getPaivat(ben1);
     *  l.size() === 7;
     *  
     *  k.poistaPeluri(ben1.getPeluriId());
     *  k.getPelureita() === 1;
     *  k.getPeluri(0).equals(ben2) === true;
     *  l = k.getPaivat(ben2);
     *  l.size() === 7;
     *  
     *  k.poistaPeluri(ben2.getPeluriId());
     *  k.getPelureita() === 0;
     *  k.getPeluri(0).equals(ben1) === false; #THROWS IndexOutOfBoundsException
     *  l = k.getPaivat(ben1);
     *  l.size() === 0;
     * </pre>
     */
    public void poistaPeluri(int peluriId) {
        paivat.poistaPelurinPaivat(peluriId);
        pelurit.poistaPeluri(peluriId);
        pelureitaMuutettu = true;
        paiviaMuutettu = true;
    }
    
    
    /**
     * Muokkaa pelurin tiedot
     * @param peluriId peluri kenen tiedot muokataan
     * @param tiedot taulukko tiedoista [nimi,pnimi,ttila,puh]
     * @example
     * <pre name="test">
     *  Kerho k = new Kerho();
     *  Peluri ben1 = new Peluri("1|Ben One|Ben_O|1000|0509289339");
     *  k.lisaa(ben1);
     *  String[] tiedot = {"nimi muutettu", "pnimi muutettu", "500", "00000000000"};
     *  k.muokkaaTietoja(ben1.getPeluriId(), tiedot);
     *  k.getPeluri(0).toString() === "1|nimi muutettu|pnimi muutettu|500|00000000000";
     * </pre>
     */
    public void muokkaaTietoja(int peluriId, String[] tiedot) {
        pelurit.muokkaaTietoja(peluriId, tiedot);
        pelureitaMuutettu = true;
    }
    
    
    /**
     * Muokkaa pelurin ajat
     * @param peluriId Pelurin id, jonka aikoja muokataan
     * @param aikaTaulukko Taulukko uusista ajoista
     * @example
     * <pre name="test">
     *  Kerho k = new Kerho();
     *  Peluri ben1 = new Peluri("1|Ben One|Ben_O|1000|0509289339");
     *  k.lisaa(ben1);
     *  List<Paiva> l = k.getPaivat(ben1);
     *  
     *  String[] paivat = {"Maanantai", "Tiistai", "Keskiviikko", "Torstai", "Perjantai", "Lauantai", "Sunnuntai"};
     *  int i = 0;
     *  for (Paiva paiva : l) {
     *      paiva.toString().equals("1|"+paivat[i]+"|00|00|00|00") === true;
     *      i++;
     *  }
     *  String[][] muutetutAjat = {
     *      {"12","12","13","13"},
     *      {"12","12","13","13"},
     *      {"12","12","13","13"},
     *      {"12","12","13","13"},
     *      {"12","12","13","13"},
     *      {"12","12","13","13"},
     *      {"12","12","13","13"}
     *  };
     *  k.muokkaaAikoja(ben1.getPeluriId(), muutetutAjat);
     *  l = k.getPaivat(ben1);
     *  i = 0;
     *  for (Paiva paiva : l) {
     *      paiva.toString().equals("1|"+paivat[i]+"|12|12|13|13") === true;
     *      i++;
     *  }
     * </pre>
     */
    public void muokkaaAikoja(int peluriId, String[][] aikaTaulukko) {
        //Poistaa kaikki pelurin päivät paivat luokasta
        poistaPelurinPaivat(peluriId);
        //Tekee uudet päivät pelurille
        for (int i = 0; i < aikaTaulukko.length; i++) {
            Paiva pv = new Paiva(peluriId, aikaTaulukko[i][0], aikaTaulukko[i][1], aikaTaulukko[i][2], aikaTaulukko[i][3], i);
            lisaa(pv);
        }
    }
    
    
    /**
     * Palauttaa kerhon pelureiden määrän
     * -Testattu
     * @return Kerhon pelureiden määrä
     */
    public int getPelureita() {
        return this.pelurit.getLkm();
    }
    
    
    /**
     * Antaa kerhon i:n Pelurin
     * -Testattu
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
     * Palauttaa tiedon onko tietoja muutettu viime tallennuksen jälkeen
     * @return true jos on tallentamattomia tietoja, muuten false
     * @example
     * <pre name="test">
     *  #THROWS IOException
     *  #import java.io.IOException;
     *  #import fi.jyu.mit.ohj2.VertaaTiedosto;
     *  Kerho k = new Kerho();
     *  k.onkoMuutettu() === false;
     *  k.lisaa(new Peluri("1|Ben One|Ben_O|1000|0509289339"));
     *  k.onkoMuutettu() === true;
     *  k.tallenna(".\\testit\\nimetOnkoMuutettu", ".\\testit\\paivatOnkoMuutettu");
     *  k.onkoMuutettu() === false;
     *  
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimetOnkoMuutettu.dat");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivatOnkoMuutettu.dat");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimetOnkoMuutettu.bak");
     *  VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivatOnkoMuutettu.bak");
     * </pre>
     */
    public boolean onkoMuutettu() {
        if (pelureitaMuutettu || paiviaMuutettu) {
            return true;
        }
        return false;
    }
    
    
    /**
     * Palauttaa kerhon peluri tulostettavassa muodossa
     * @return kerhon pelurit tulostettavassa muodossa
     */
    public String tulosta() {
        StringBuilder sb = new StringBuilder();
        for (Peluri peluri : pelurit) {
            sb.append(peluri.toPrintable());
            sb.append("\nPelipäivät:\n");
            List<Paiva> l = getPaivat(peluri);
            for (Paiva paiva : l) {
                sb.append(paiva.toPrintable());
            }
            sb.append("\n==========================================\n");
        }
        return sb.toString();
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Kerho kerho = new Kerho();
        
        Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P||0501231234");
        Peluri ben2 = new Peluri("2|Ben Peluri|Ben_P||0501231234");
        
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
