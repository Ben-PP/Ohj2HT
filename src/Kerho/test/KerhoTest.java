package Kerho.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import fi.jyu.mit.ohj2.VertaaTiedosto;
import java.util.*;
import Kerho.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.12.10 15:19:41 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KerhoTest {



  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta43 
   * @throws IOException when error
   */
  @Test
  public void testLueTiedostosta43() throws IOException {    // Kerho: 43
    Kerho k = new Kerho(); 
    k.lueTiedostosta(".\\testit\\nimetLukuTest.dat", ".\\testit\\paivatLukuTest.dat"); 
    assertEquals("From: Kerho line: 50", 7, k.getPelureita()); 
    List<Paiva> paivat = k.getPaivat(k.getPeluri(0)); 
    StringBuilder sb = new StringBuilder(); 
    for (Paiva paiva : paivat) {
    sb.append(paiva.toString()); 
    }
    String tulos = "1|Maanantai|12|00|23|45"+
    "1|Tiistai|16|00|16|30"+
    "1|Keskiviikko|00|00|00|00"+
    "1|Torstai|16|45|23|30"+
    "1|Perjantai|00|00|00|00"+
    "1|Lauantai|18|00|20|30"+
    "1|Sunnuntai|12|00|16|30"; 
    assertEquals("From: Kerho line: 65", true, tulos.equals(sb.toString())); 
    paivat = k.getPaivat(k.getPeluri(1)); 
    sb.delete(0, sb.length()); 
    for (Paiva paiva : paivat) {
    sb.append(paiva.toString()); 
    }
    tulos = "2|Maanantai|00|00|00|00"+
    "2|Tiista|00|00|00|00"+
    "2|Keskiviikko|00|00|00|00"+
    "2|Torstai|00|00|00|00"+
    "2|Perjantai|00|00|00|00"+
    "2|Lauantai|18|00|20|30"+
    "2|Sunnunta|12|00|16|30"; 
    assertEquals("From: Kerho line: 79", true, tulos.equals(sb.toString())); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testTallenna93 
   * @throws IOException when error
   */
  @Test
  public void testTallenna93() throws IOException {    // Kerho: 93
    VertaaTiedosto.kirjoitaTiedosto(".\\testit\\nimet.dat",
    "1|Ben One|Ben_O|1000|0509289339\n"+
    "2|Ben Two|Ben_T|1500|0509289339\n"); 
    String tulosNimet =
    "1|Ben One|Ben_O|1000|0509289339\n"+
    "2|Ben Two|Ben_T|1500|0509289000\n"; 
    String tulosPaivat =
    "1|Maanantai|12|00|13|00\n"+
    "1|Tiistaitai|12|00|17|00\n"+
    "1|Keskiviikkko|12|00|17|00\n"+
    "1|Torstai|12|00|17|00\n"+
    "1|Perjantai|12|00|17|00\n"+
    "1|Lauantai|00|00|00|00\n"+
    "1|Sunnnuntai|00|00|00|00\n"+
    "2|Maanantai|12|00|13|00\n"+
    "2|Tiistaitai|00|00|00|00\n"+
    "2|Keskiviikkko|12|00|17|00\n"+
    "2|Torstai|12|00|17|00\n"+
    "2|Perjantai|00|00|00|00\n"+
    "2|Lauantai|12|00|17|00\n"+
    "2|Sunnnuntai|12|00|17|00\n"; 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimet.dat"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivat.dat"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimet.bak"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivat.bak"); 
    Kerho k = new Kerho(); 
    Peluri benOne = new Peluri("1|Ben One|Ben_O|1000|0509289339"); 
    Peluri benTwo = new Peluri("2|Ben Two|Ben_T|1500|0509289000"); 
    k.lisaa(benOne); 
    k.lisaa(benTwo); 
    Paiva[] paivat = {
    new Paiva("1|Maanantai|12|00|13|00"),
    new Paiva("1|Tiistaitai|12|00|17|00"),
    new Paiva("1|Keskiviikkko|12|00|17|00"),
    new Paiva("1|Torstai|12|00|17|00"),
    new Paiva("1|Perjantai|12|00|17|00"),
    new Paiva("1|Lauantai|00|00|00|00"),
    new Paiva("1|Sunnnuntai|00|00|00|00"),
    new Paiva("2|Maanantai|12|00|13|00"),
    new Paiva("2|Tiistaitai|00|00|00|00"),
    new Paiva("2|Keskiviikkko|12|00|17|00"),
    new Paiva("2|Torstai|12|00|17|00"),
    new Paiva("2|Perjantai|00|00|00|00"),
    new Paiva("2|Lauantai|12|00|17|00"),
    new Paiva("2|Sunnnuntai|12|00|17|00")
    } ; 
    k.poistaPelurinPaivat(benOne.getPeluriId()); 
    k.poistaPelurinPaivat(benTwo.getPeluriId()); 
    for (int i = 0; i < paivat.length; i++) {
    k.lisaa(paivat[i]); 
    }
    k.tallenna(".\\testit\\nimet", ".\\testit\\paivat"); 
    assertEquals("From: Kerho line: 154", null, VertaaTiedosto.vertaaFileString(".\\testit\\nimet.dat", tulosNimet)); 
    assertEquals("From: Kerho line: 155", null, VertaaTiedosto.vertaaFileString(".\\testit\\paivat.dat", tulosPaivat)); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimet.dat"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivat.dat"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimet.bak"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivat.bak"); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaa183 */
  @Test
  public void testLisaa183() {    // Kerho: 183
    Kerho k = new Kerho(); 
    Peluri ben1 = new Peluri(); 
    ben1.rekisteroi(); 
    k.lisaa(ben1); 
    Peluri ben2 = new Peluri(); 
    ben2.rekisteroi(); 
    assertEquals("From: Kerho line: 192", false, ben2.equals(k.getPeluri(0))); 
    assertEquals("From: Kerho line: 193", true, k.getPeluri(0).equals(ben1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoistaPeluri233 */
  @Test
  public void testPoistaPeluri233() {    // Kerho: 233
    Kerho k = new Kerho(); 
    Peluri ben1 = new Peluri("1|Ben One|Ben_O|1000|0509289339"); 
    k.lisaa(ben1); 
    Peluri ben2 = new Peluri("2|Ben Two|Ben_T|1000|0509289000"); 
    k.lisaa(ben2); 
    assertEquals("From: Kerho line: 239", 2, k.getPelureita()); 
    assertEquals("From: Kerho line: 240", true, k.getPeluri(0).equals(ben1)); 
    assertEquals("From: Kerho line: 241", true, k.getPeluri(1).equals(ben2)); 
    List<Paiva> l = k.getPaivat(ben1); 
    assertEquals("From: Kerho line: 243", 7, l.size()); 
    k.poistaPeluri(ben1.getPeluriId()); 
    assertEquals("From: Kerho line: 246", 1, k.getPelureita()); 
    assertEquals("From: Kerho line: 247", true, k.getPeluri(0).equals(ben2)); 
    l = k.getPaivat(ben2); 
    assertEquals("From: Kerho line: 249", 7, l.size()); 
    k.poistaPeluri(ben2.getPeluriId()); 
    assertEquals("From: Kerho line: 252", 0, k.getPelureita()); 
    try {
    assertEquals("From: Kerho line: 253", false, k.getPeluri(0).equals(ben1)); 
    fail("Kerho: 253 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    l = k.getPaivat(ben1); 
    assertEquals("From: Kerho line: 255", 0, l.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testMuokkaaTietoja271 */
  @Test
  public void testMuokkaaTietoja271() {    // Kerho: 271
    Kerho k = new Kerho(); 
    Peluri ben1 = new Peluri("1|Ben One|Ben_O|1000|0509289339"); 
    k.lisaa(ben1); 
    String[] tiedot = { "nimi muutettu", "pnimi muutettu", "500", "00000000000"} ; 
    k.muokkaaTietoja(ben1.getPeluriId(), tiedot); 
    assertEquals("From: Kerho line: 277", "1|nimi muutettu|pnimi muutettu|500|00000000000", k.getPeluri(0).toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testMuokkaaAikoja291 */
  @Test
  public void testMuokkaaAikoja291() {    // Kerho: 291
    Kerho k = new Kerho(); 
    Peluri ben1 = new Peluri("1|Ben One|Ben_O|1000|0509289339"); 
    k.lisaa(ben1); 
    List<Paiva> l = k.getPaivat(ben1); 
    String[] paivat = { "Maanantai", "Tiistai", "Keskiviikko", "Torstai", "Perjantai", "Lauantai", "Sunnuntai"} ; 
    int i = 0; 
    for (Paiva paiva : l) {
    assertEquals("From: Kerho line: 300", true, paiva.toString().equals("1|"+paivat[i]+"|00|00|00|00")); 
    i++; 
    }
    String[][] muutetutAjat = {
    {"12","12","13","13"},
    {"12","12","13","13"},
    {"12","12","13","13"},
    {"12","12","13","13"},
    {"12","12","13","13"},
    {"12","12","13","13"},
    {"12","12","13","13"}
    } ; 
    k.muokkaaAikoja(ben1.getPeluriId(), muutetutAjat); 
    l = k.getPaivat(ben1); 
    i = 0; 
    for (Paiva paiva : l) {
    assertEquals("From: Kerho line: 316", true, paiva.toString().equals("1|"+paivat[i]+"|12|12|13|13")); 
    i++; 
    }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPaivat358 */
  @Test
  public void testGetPaivat358() {    // Kerho: 358
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
    assertEquals("From: Kerho line: 374", 0, loytyneet.size()); 
    loytyneet = kerho.getPaivat(ben1); 
    assertEquals("From: Kerho line: 376", 2, loytyneet.size()); 
    assertEquals("From: Kerho line: 377", true, loytyneet.get(0) == maanantai1); 
    assertEquals("From: Kerho line: 378", true, loytyneet.get(1) == tiistai1); 
    loytyneet = kerho.getPaivat(ben2); 
    assertEquals("From: Kerho line: 380", 3, loytyneet.size()); 
    assertEquals("From: Kerho line: 381", true, loytyneet.get(0) == maanantai2); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testOnkoMuutettu393 
   * @throws IOException when error
   */
  @Test
  public void testOnkoMuutettu393() throws IOException {    // Kerho: 393
    Kerho k = new Kerho(); 
    assertEquals("From: Kerho line: 398", false, k.onkoMuutettu()); 
    k.lisaa(new Peluri("1|Ben One|Ben_O|1000|0509289339")); 
    assertEquals("From: Kerho line: 400", true, k.onkoMuutettu()); 
    k.tallenna(".\\testit\\nimetOnkoMuutettu", ".\\testit\\paivatOnkoMuutettu"); 
    assertEquals("From: Kerho line: 402", false, k.onkoMuutettu()); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimetOnkoMuutettu.dat"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivatOnkoMuutettu.dat"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\nimetOnkoMuutettu.bak"); 
    VertaaTiedosto.tuhoaTiedosto(".\\testit\\paivatOnkoMuutettu.bak"); 
  } // Generated by ComTest END
}