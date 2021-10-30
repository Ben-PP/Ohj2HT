package Kerho;

/**
 * Kerholuokka
 * 
 * -    Huolehtii pelurit ja Paivat - luokkien välisestä yhteistyöstä ja
 *      välittää näitä tietoja pyydettäessä
 * -    Lukee ja kirjoittaa kerhon tiedostoon pyytämällä apua avustajilta
 * 
 * @author Karel
 * @version 28.10.2021
 *
 */
public class Kerho {
    
    private Pelurit pelurit = new Pelurit();
    
    
    /**
     * Lisää pelurin kerhoon
     * @param peluri peluri joka lisätään
     * @throws SailoException jos lisääminen ei onnistu
     * TODO: testit
     */
    public void lisaa(Peluri peluri) throws SailoException {
        this.pelurit.lisaa(peluri);
    }
    
    
    /**
     * Palauttaa kerhon pelureiden määrän
     * @return Kerhon pelureiden määrä
     * TODO: Testit
     */
    public int getPelureita() {
        return this.pelurit.getLkm();
    }
    
    
    /**
     * Antaa kerhon i:n jäsenen
     * @param i monesko jäsen (alkee 0:sta)
     * @return jäsen paikasta i
     */
    public Peluri getPeluri(int i) {
        return this.pelurit.anna(i);
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
        
        try {
            kerho.lisaa(ben1);
            kerho.lisaa(ben2);
            kerho.lisaa(ben1);
            kerho.lisaa(ben2);
            kerho.lisaa(ben1);
            kerho.lisaa(ben2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        for (int i = 0; i < kerho.getPelureita(); i++) {
            Peluri peluri = kerho.getPeluri(i);
            peluri.tulosta(System.out);
        }
    
    }

}
