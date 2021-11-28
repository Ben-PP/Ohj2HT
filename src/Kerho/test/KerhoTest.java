package Kerho.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import Kerho.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.11.29 00:48:46 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KerhoTest {



  // Generated by ComTest BEGIN
  /** testGetPaivat114 */
  @Test
  public void testGetPaivat114() {    // Kerho: 114
    Kerho kerho = new Kerho(); 
    Peluri ben1 = new Peluri(), ben2 = new Peluri(), ben3 = new Peluri(); 
    ben1.rekisteroi(); ben2.rekisteroi(); ben3.rekisteroi(); 
    int id1 = ben1.getPeluriId(); 
    int id2 = ben2.getPeluriId(); 
    Paiva maanantai1 = new Paiva(id1, 0); kerho.lisaa(maanantai1); 
    Paiva tiistai1 = new Paiva(id1, 1); kerho.lisaa(tiistai1); 
    Paiva maanantai2 = new Paiva(id2, 0); kerho.lisaa(maanantai2); 
    Paiva tiistai2 = new Paiva(id2, 0); kerho.lisaa(tiistai2); 
    Paiva keskiviikko2 = new Paiva(id2, 3); kerho.lisaa(keskiviikko2); 
    List<Paiva> loytyneet; 
    loytyneet = kerho.getPaivat(ben3); 
    assertEquals("From: Kerho line: 130", 0, loytyneet.size()); 
    loytyneet = kerho.getPaivat(ben1); 
    assertEquals("From: Kerho line: 132", 2, loytyneet.size()); 
    assertEquals("From: Kerho line: 133", true, loytyneet.get(0) == maanantai1); 
    assertEquals("From: Kerho line: 134", true, loytyneet.get(1) == tiistai1); 
    loytyneet = kerho.getPaivat(ben2); 
    assertEquals("From: Kerho line: 136", 3, loytyneet.size()); 
    assertEquals("From: Kerho line: 137", true, loytyneet.get(0) == maanantai2); 
  } // Generated by ComTest END
}