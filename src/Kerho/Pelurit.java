package Kerho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Pelurit luokka
 * 
 * -    Pitää yllä varsinaista pelurirekisteriä, eli osaa lisätä ja poistaa pelurin
 * -    Lukee ja kirjoittaa peluriston tiedostoon
 * -    Osaa etsiä ja lajitella
 * 
 * @author Karel
 * @version 28.10.2021
 * @version 15.11.2021
 * @version 28.11.2021
 * @version 1.12.2021
 * @version 9.12.2021
 */
public class Pelurit implements Iterable<Peluri> {
    
    
    private static final int MAX_PELUREITA  = 5;
    private int lkm = 0;
    private Peluri[] alkiot;
    
    
    /**
     * Luodaan alustava taulukko
     */
    public Pelurit() {
        alkiot = new Peluri[MAX_PELUREITA];
    }
    
    
    /**
     * Lisää pelurin taulukkoon
     * @param peluri peluri joka lisätään
     * @example
     * <pre name="test">
     *  Pelurit pelurit = new Pelurit();
     *  Peluri ben1 = new Peluri();
     *  Peluri ben2 = new Peluri();
     *  pelurit.getLkm() === 0;
     *  pelurit.lisaa(ben1); pelurit.getLkm() === 1;
     *  pelurit.lisaa(ben2); pelurit.getLkm() === 2;
     *  pelurit.lisaa(ben1); pelurit.getLkm() === 3;
     *  pelurit.anna(0) === ben1;
     *  pelurit.anna(1) === ben2;
     *  pelurit.anna(2) === ben1;
     *  pelurit.anna(1) == ben1 === false;
     *  pelurit.anna(1) == ben2 === true;
     *  pelurit.anna(3) === ben1; #THROWS IndexOutOfBoundsException
     *  pelurit.lisaa(ben1); pelurit.getLkm() === 4;
     *  pelurit.lisaa(ben1); pelurit.getLkm() === 5;
     *  pelurit.lisaa(ben1); pelurit.getLkm() === 6;
     * </pre>
     */
    public void lisaa(Peluri peluri) {
        
        if(lkm >= alkiot.length) {
            Peluri[] uusi = new Peluri[lkm+5];
            for(int i = 0; i < lkm; i++) {
                uusi[i] = alkiot[i];
            }
            alkiot = uusi;
        }
        this.alkiot[this.lkm] = peluri;
        lkm++;
    }
    
    
    /**
     * Poistaa halutun pelurin
     * @param peluriId Poistettavan pelurin Id
     */
    public void poistaPeluri(int peluriId) {
        int yliKirjoitettava = 0;
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i].getPeluriId() == peluriId) {
                continue;
            }
            alkiot[(yliKirjoitettava)] = alkiot[i];
            yliKirjoitettava++;
        }
        lkm -= 1;
    }
    
    
    /**
     * Muokkaa pelurin tiedot
     * -Testattu Kerho-luokassa
     * @param peluriId peluri jonka tietoja muokataan
     * @param tiedot taulukko tiedoista [nimi,pnimi,ttila,puh]
     */
    public void muokkaaTietoja(int peluriId, String[] tiedot) {
        for (int i = 0; i < lkm; i++) {
            if(alkiot[i].getPeluriId() == peluriId) {
                alkiot[i].muokkaaTietoja(tiedot);
                return;
            }
        }
    }
    
    
    /**
     * Palauttaa pelurin
     * -Testattu lisaa() metodissa
     * @param i paikka josta peluri palautetaan
     * @return peluri
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Peluri anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || this.lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        
        return alkiot[i];
    }
    
    
    /**
     * Palauttaa pelurien lukumäärän
     * @return pelurien lukumäärä
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     *  Pelurit pelurit = new Pelurit();
     *  Peluri ben1 = new Peluri();
     *  Peluri ben2 = new Peluri();
     *  pelurit.getLkm() === 0;
     *  pelurit.lisaa(ben1); pelurit.getLkm() === 1;
     *  pelurit.lisaa(ben2); pelurit.getLkm() === 2;
     * </pre>
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
    /**
     * Tallentaa kerhon pelurit tiedostoon
     * -Testattu Kerho-luokassa
     * @param tiedNimi tiedoston nimi johon tallennetaan
     * @throws IOException Jos tiedostoa ei voitu tallentaa
     */
    public void tallenna(String tiedNimi) throws IOException {
        File tiedBak = new File(tiedNimi+".bak");
        File tied = new File(tiedNimi+".dat");
        tiedBak.delete();
        tied.renameTo(tiedBak);
        
        try (PrintWriter fo = new PrintWriter(new FileWriter(tied.getCanonicalPath()))) {
            for (Peluri peluri : this) {
                fo.println(peluri.toString());
            }
        } catch (IOException e) {
            System.err.println("Pelureita ei voitu tallentaa! "+e.getMessage());
        }
    }
    
    
    /**
     * Lukee kerhon pelurit tiedostosta
     * -Testattu Kerho-luokassa
     * @param tiedNimi Tiedosto josta luetaan
     */
    public void lueTiedostosta(String tiedNimi) {
        
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)), "UTF-8")){
            while (fi.hasNextLine()) {
                lisaa(new Peluri(fi.nextLine()));
            }
        } catch (FileNotFoundException e) {
             System.err.println("Tiedostoa ei voitu lukea! "+e.getMessage());
        }
    }
    
    
    /**
     * Iteraattori Pelurit luokalle
     * @author Karel
     * @version 28.11.2021
     */
    public class PeluritIterator implements Iterator<Peluri>{

        private int kohdalla = 0;
        
        
        /**
         * Onko olemassa vielä seuraavaa jäsentä
         * @see java.util.Iterator#hasNext()
         * @return true jos on vielä pelureita
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }

        
        /**
         * Annetaan seuraava jäsen
         * @return seuraava jäsen
         * @throws NoSuchElementException jos seuraavaa alkiota ei enää ole
         * @see java.util.Iterator#next()
         */
        @Override
        public Peluri next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("Ei ole seuraavaa elementtiä");
            return anna(kohdalla++);
        }
        
    }
    
    
    /**
     * Palautetaan iteraattori Pelureista
     * @example
     * <pre name="test">
     *  #import java.util.Iterator;
     *  Pelurit pelurit = new Pelurit();
     *  pelurit.lisaa(new Peluri("1|Ben One|Ben_O|1000|0509998888"));
     *  pelurit.lisaa(new Peluri("2|Ben Two|Ben_Tw|1000|0509998888"));
     *  pelurit.lisaa(new Peluri("3|Ben Three|Ben_Th|1000|0509998888"));
     *  pelurit.lisaa(new Peluri("4|Ben Four|Ben_F|1000|0509998888"));
     *  Iterator<Peluri> iter = pelurit.iterator();
     *  int i = 1;
     *  for (;iter.hasNext();) {
     *      iter.next().getPeluriId() === i;
     *      i++;
     *  }
     * </pre>
     */
    @Override
    public Iterator<Peluri> iterator() {
        return new PeluritIterator();
    }
    
    
    /**
     * 
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelurit pelurit = new Pelurit();
        Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P||0501231234");
        Peluri ben2 = new Peluri("2|Ben Peluri|Ben_P||0501231234");
        Peluri ben3 = new Peluri("3|Ben Peluri|Ben_P||0501231234");
        Peluri ben4 = new Peluri("4|Ben Peluri|Ben_P||0501231234");
        Peluri ben5 = new Peluri("5|Ben Peluri|Ben_P||0501231234");
        Peluri ben6 = new Peluri("6|Ben Peluri|Ben_P||0501231234");
        
        pelurit.lisaa(ben1);
        pelurit.lisaa(ben2);
        pelurit.lisaa(ben3);
        pelurit.lisaa(ben4);
        pelurit.lisaa(ben5);
        pelurit.lisaa(ben6);
        
        System.out.println("================= Jäsenet testi ====================");
        
        for (int i = 0; i < pelurit.getLkm(); i++) {
            Peluri peluri = pelurit.anna(i);
            System.out.println("Peluri indeksi: " + i);
            peluri.tulosta(System.out);
        }
    }
}
