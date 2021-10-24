package Kanta;

/**
 * Tarkistaa puhelinnumeron oikeellisuuden
 * @author Karel
 * @version 24.10.2021
 *
 */
public class PuhelinTarkistus {
    
    
    /**
     * Arvotaan satunnainen kokonaisluku välille [ala, yla]
     * @param ala arvonnan alaraja
     * @param yla arvonnan yläraja
     * @return satunnainen luku väliltä [ala, yla]
     */
    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
    }
    
    
    /**
     * Arpoo satunnaisen puhelinnumeron
     * @return satunnainen puhelinnumero
     */
    public static String arvoPuhelin() {
        String apuNumero = String.format("%03d", rand(0, 999)) + " " + String.format("%03d", rand(0, 999)) + " " + String.format("%04d", rand(0, 9999));
        return apuNumero;
    }
}
