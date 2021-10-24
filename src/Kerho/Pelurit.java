package Kerho;

/**
 * Pelurit luokka
 * 
 * -    Pitää yllä varsinaista pelurirekisteriä, eli osaa lisätä ja poistaa pelurin
 * -    Lukee ja kirjoittaa peluriston tiedostoon
 * -    Osaa etsiä ja lajitella
 * 
 * @author Karel
 * @version 24.10.2021
 *
 */
public class Pelurit {
    
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
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     *  #THROWS SailoException
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
     *  pelurit.lisaa(ben1); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Peluri peluri) throws SailoException {
        
        if(lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        
        this.alkiot[this.lkm] = peluri;
        lkm++;
    }
    
    
    /**
     * Palauttaa pelurin
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
     * 
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelurit pelurit = new Pelurit();
        Peluri ben1 = new Peluri();
        Peluri ben2 = new Peluri();
        
        ben1.rekisteroi();
        ben1.taytaTestiTiedoilla();
        
        ben2.rekisteroi();
        ben2.taytaTestiTiedoilla();
        
        try {
            pelurit.lisaa(ben1);
            pelurit.lisaa(ben2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("================= Jäsenet testi ====================");
        
        for (int i = 0; i < pelurit.getLkm(); i++) {
            Peluri peluri = pelurit.anna(i);
            System.out.println("Peluri indeksi: " + i);
            peluri.tulosta(System.out);
        }
    }
}
